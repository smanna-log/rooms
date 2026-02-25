package com.mobile.framework.drivers;

import com.mobile.framework.config.ConfigManager;
import com.mobile.framework.utilities.LoggerUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Driver Factory to create and manage Appium driver instances
 * This class provides methods to initialize and quit driver instances
 */
public class DriverFactory {
    
    private static final LoggerUtility logger = new LoggerUtility(DriverFactory.class);
    private static AppiumDriver driver;
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    
    /**
     * Initialize Appium driver with desired capabilities
     * 
     * @return AppiumDriver instance
     */
    public static AppiumDriver initDriver() {
        logger.info("Initializing Appium Driver...");
        
        try {
            // Get configuration values
            String appPath = ConfigManager.getInstance().getProperty("app.path");
            String deviceName = ConfigManager.getInstance().getProperty("android.device.name");
            String platformName = ConfigManager.getInstance().getProperty("android.platform.name");
            String platformVersion = ConfigManager.getInstance().getProperty("android.platform.version");
            String automationName = ConfigManager.getInstance().getProperty("android.automation.name");
            String udid = ConfigManager.getInstance().getProperty("android.udid");
            String appPackage = ConfigManager.getInstance().getProperty("appPackage");
            String appActivity = ConfigManager.getInstance().getProperty("appActivity");
            int implicitWait = ConfigManager.getInstance().getIntProperty("implicit.wait", 10);
            
            // Set up desired capabilities
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("automationName", automationName);
            caps.setCapability("udid", udid);
            caps.setCapability("app", appPath);
            caps.setCapability("appPackage", appPackage);
            caps.setCapability("appActivity", appActivity);
            caps.setCapability("newCommandTimeout", 300);
            
            // Get Appium server URL
            String appiumHost = ConfigManager.getInstance().getProperty("appium.server.host", "127.0.0.1");
            int appiumPort = ConfigManager.getInstance().getIntProperty("appium.server.port", 4723);
            URL appiumServerUrl = new URL("http://" + appiumHost + ":" + appiumPort + "/wd/hub");
            
            // Initialize driver based on platform
            if (platformName.equalsIgnoreCase("Android")) {
                driver = new AndroidDriver(appiumServerUrl, caps);
            } else {
                logger.error("Unsupported platform: " + platformName);
                throw new RuntimeException("Unsupported platform: " + platformName);
            }
            
            // Set implicit wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            
            // Set driver in thread local for parallel execution support
            driverThreadLocal.set(driver);
            
            logger.info("Appium Driver initialized successfully for " + platformName + " platform");
            return driver;
        } catch (MalformedURLException e) {
            logger.error("Invalid Appium server URL: " + e.getMessage());
            throw new RuntimeException("Invalid Appium server URL", e);
        } catch (Exception e) {
            logger.error("Failed to initialize Appium Driver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize Appium Driver", e);
        }
    }
    
    /**
     * Get the current driver instance
     * 
     * @return AppiumDriver instance
     */
    public static AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }
    
    /**
     * Quit the current driver instance
     */
    public static void quitDriver() {
        logger.info("Quitting Appium Driver...");
        
        try {
            AppiumDriver driver = driverThreadLocal.get();
            if (driver != null) {
                driver.quit();
                driverThreadLocal.remove();
                logger.info("Appium Driver quit successfully");
            } else {
                logger.warn("No driver instance found to quit");
            }
        } catch (Exception e) {
            logger.error("Failed to quit Appium Driver: " + e.getMessage());
        }
    }
}