package com.mobile.framework.base;

import com.mobile.framework.config.ConfigManager;
import com.mobile.framework.drivers.AppiumServerManager;
import com.mobile.framework.drivers.DriverFactory;
import com.mobile.framework.reports.ExtentReportManager;
import com.mobile.framework.utilities.EmulatorManager;
import com.mobile.framework.utilities.LoggerUtility;
import com.mobile.framework.utilities.ScreenshotUtility;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Base Test class that provides common setup and teardown methods for all test classes
 * This class handles Appium server startup/shutdown conditionally (local vs browserstack), 
 * driver initialization via TestNG parameters, and reporting
 */
public class BaseTest {
    
    protected AppiumDriver driver;
    protected static final LoggerUtility logger = new LoggerUtility(BaseTest.class);
    
    /**
     * Setup method to conditionally start Appium server before suite execution
     */
    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        logger.info("Setting up test suite...");
        
        try {
            String executionType = ConfigManager.getInstance().getProperty("execution.type", "local");
            
            if (executionType.equalsIgnoreCase("local")) {
                logger.info("Execution type is LOCAL. Initiating local dependencies.");
                
                // Start ADB server
                EmulatorManager.startAdbServer();
                logger.info("ADB server started successfully");
                
                // Check if device is connected, if not start emulator
                String udid = ConfigManager.getInstance().getProperty("android.udid");
                int port = 5554;
                if (udid != null && udid.contains("-")) {
                    try {
                        port = Integer.parseInt(udid.substring(udid.lastIndexOf("-") + 1));
                    } catch (NumberFormatException e) {
                        logger.warn("Could not parse emulator port from UDID, defaulting to 5554");
                    }
                }
                
                if (!EmulatorManager.isEmulatorRunning(port)) {
                    logger.info("No device connected, starting emulator...");
                    String avdName = ConfigManager.getInstance().getProperty("android.avd.name", "Pixel_9_Pro_API_33");
                    EmulatorManager.startEmulator(avdName, port);
                } else {
                    logger.info("Device already connected: " + udid);
                }
                
                // Start Appium server
                AppiumServerManager.startServer();
                logger.info("Appium server started successfully");
            } else {
                logger.info("Execution type is REMOTE (" + executionType + "). Skipping local server setup.");
            }
        } catch (Exception e) {
            logger.error("Failed to set up test suite: " + e.getMessage());
            throw new RuntimeException("Failed to set up test suite", e);
        }
    }
    
    /**
     * Setup method to initialize driver before each test method using TestNG XML parameters
     */
    @Parameters({"deviceName", "platformVersion", "platformName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String deviceName, @Optional String platformVersion, @Optional String platformName) {
        logger.info("Setting up test method...");
        
        try {
            // Initialize driver with dynamic parameters for parallel execution
            driver = DriverFactory.initDriver(deviceName, platformVersion, platformName, null);
            logger.info("Driver initialized successfully for device: " + deviceName);
        } catch (Exception e) {
            logger.error("Failed to initialize driver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }
    
    /**
     * Teardown method to capture screenshot on test failure and quit driver after each test method
     * 
     * @param result Test result information
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        logger.info("Tearing down test method...");
        
        try {
            // Capture screenshot if test failed
            if (result.getStatus() == ITestResult.FAILURE) {
                String testName = result.getMethod().getMethodName();
                ScreenshotUtility.captureScreenshot(driver, testName);
                logger.info("Screenshot captured for failed test: " + testName);
            }
            
            // Quit driver
            DriverFactory.quitDriver();
            logger.info("Driver quit successfully");
        } catch (Exception e) {
            logger.error("Error during teardown: " + e.getMessage());
        }
    }
    
    /**
     * Teardown method to conditionally stop Appium server after suite execution
     */
    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        logger.info("Tearing down test suite...");
        
        try {
            String executionType = ConfigManager.getInstance().getProperty("execution.type", "local");
            
            if (executionType.equalsIgnoreCase("local")) {
                // Stop Appium server
                AppiumServerManager.stopServer();
                logger.info("Appium server stopped successfully");
                
                // Stop emulator if it was started by the framework
                EmulatorManager.stopEmulator();
                logger.info("Emulator stopped if it was started by framework");
            }
            
            // Flush Extent Reports
            ExtentReportManager.flushReports();
            logger.info("Extent reports flushed successfully");
        } catch (Exception e) {
            logger.error("Error during suite teardown: " + e.getMessage());
        }
    }
}