package com.mobile.framework.drivers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.mobile.framework.config.ConfigManager;
import com.mobile.framework.utilities.LoggerUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * Driver Factory to create and manage Appium driver instances This class
 * provides methods to initialize and quit driver instances locally or on
 * BrowserStack
 */
public class DriverFactory {

    private static final LoggerUtility logger = new LoggerUtility(DriverFactory.class);
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Initialize Appium driver with capabilities reading from config.properties
     * or TestNG parameters. Support parallel execution on BrowserStack or local
     * emulator.
     *
     * @param deviceName Device name parameter
     * @param platformVersion Platform version parameter
     * @param platformName Platform name parameter (e.g. Android)
     * @param udid Device UDID for local execution
     * @return AppiumDriver instance
     */
    public static AppiumDriver initDriver(String deviceName, String platformVersion, String platformName, String udid) {
        logger.info("Initializing Appium Driver for " + deviceName + "...");

        try {
            String executionType = ConfigManager.getInstance().getProperty("execution.type", "local");
            int implicitWait = ConfigManager.getInstance().getIntProperty("implicit.wait", 10);

            AppiumDriver driver;

            if (executionType.equalsIgnoreCase("browserstack")) {
                driver = createBrowserStackDriver(deviceName, platformVersion, platformName);
            } else {
                driver = createLocalDriver(deviceName, platformVersion, platformName, udid);
            }

            // Set implicit wait
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

            // Set driver in thread local for parallel execution support
            driverThreadLocal.set(driver);

            logger.info("Appium Driver initialized successfully for " + deviceName);
            return driver;
        } catch (Exception e) {
            logger.error("Failed to initialize Appium Driver: " + e.getMessage());
            throw new RuntimeException("Failed to initialize Appium Driver", e);
        }
    }

    private static AppiumDriver createBrowserStackDriver(String deviceName, String platformVersion, String platformName) throws MalformedURLException {
        String username = ConfigManager.getInstance().getProperty("browserstack.username");
        String accessKey = ConfigManager.getInstance().getProperty("browserstack.accesskey");
        String appUrl = ConfigManager.getInstance().getProperty("browserstack.appUrl");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserstack.user", username);
        caps.setCapability("browserstack.key", accessKey);
        caps.setCapability("app", appUrl);
        caps.setCapability("device", deviceName);
        caps.setCapability("os_version", platformVersion);
        caps.setCapability("project", "VD App Automation");
        caps.setCapability("build", "BrowserStack Build");
        caps.setCapability("name", "Parallel Test on " + deviceName);
        caps.setCapability("interactiveDebugging", "true");
        caps.setCapability("browserstack.networkLogs", "true");

        URL bsUrl = new URL("https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub");

        if (platformName.equalsIgnoreCase("Android")) {
            return new AndroidDriver(bsUrl, caps);
        } else if (platformName.equalsIgnoreCase("iOS")) {
            return new IOSDriver(bsUrl, caps);
        } else {
            throw new RuntimeException("Unsupported platform: " + platformName);
        }
    }

    private static AppiumDriver createLocalDriver(String deviceName, String platformVersion, String platformName, String udid) throws MalformedURLException {
        String appPath = ConfigManager.getInstance().getProperty("app.path");
        String automationName = ConfigManager.getInstance().getProperty("android.automation.name", "UiAutomator2");
        String appPackage = ConfigManager.getInstance().getProperty("appPackage");
        String appActivity = ConfigManager.getInstance().getProperty("appActivity");

        if (appPath == null || appPath.isBlank()) {
            throw new RuntimeException("The app.path property is not configured in config.properties");
        }

        File appFile = new File(appPath);
        if (!appFile.isAbsolute()) {
            File relativeFile = new File(System.getProperty("user.dir"), appPath);
            if (relativeFile.exists()) {
                appFile = relativeFile;
                appPath = appFile.getAbsolutePath();
            }
        }

        if (!appFile.exists()) {
            throw new RuntimeException("App file not found at path: " + appPath);
        }

        DesiredCapabilities caps = new DesiredCapabilities();

// 🔥 Platform (standard value)
        caps.setCapability("platformName",
                platformName != null ? platformName : "Android");

// 🔥 Device + UDID (prefer detected UDID)
        String finalUdid = (udid != null && !udid.isEmpty()) ? udid : deviceName;

        caps.setCapability("deviceName", finalUdid);
        caps.setCapability("udid", finalUdid);

// 🔥 Automation
        caps.setCapability("appium:automationName", automationName);

// 🔥 App details
        caps.setCapability("appium:app", appPath);
        caps.setCapability("appium:appPackage", appPackage);
        caps.setCapability("appium:appActivity", appActivity);

// 🔥 Timeout
        caps.setCapability("appium:newCommandTimeout", 300);

// ❌ REMOVE THIS (very important)
// caps.setCapability("platformVersion", ...);
        String appiumHost = ConfigManager.getInstance().getProperty("appium.server.host", "127.0.0.1");
        int appiumPort = ConfigManager.getInstance().getIntProperty("appium.server.port", 4723);
        URL appiumServerUrl = new URL("http://" + appiumHost + ":" + appiumPort + "/");

        String platform = caps.getCapability("platformName").toString();
        if (platform.equalsIgnoreCase("Android")) {
            return new AndroidDriver(appiumServerUrl, caps);
        } else if (platform.equalsIgnoreCase("iOS")) {
            return new IOSDriver(appiumServerUrl, caps);
        } else {
            throw new RuntimeException("Unsupported platform: " + platform);
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
