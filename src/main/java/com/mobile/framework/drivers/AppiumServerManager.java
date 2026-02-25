package com.mobile.framework.drivers;

import com.mobile.framework.config.ConfigManager;
import com.mobile.framework.utilities.LoggerUtility;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

/**
 * Appium Server Manager to start and stop Appium server programmatically
 * This class manages the lifecycle of the Appium server
 */
public class AppiumServerManager {
    
    private static final LoggerUtility logger = new LoggerUtility(AppiumServerManager.class);
    private static AppiumDriverLocalService service;
    
    /**
     * Start Appium server
     */
    public static void startServer() {
        logger.info("Starting Appium Server...");
        
        try {
            // Get configuration values
            String appiumHost = ConfigManager.getInstance().getProperty("appium.server.host", "127.0.0.1");
            int appiumPort = ConfigManager.getInstance().getIntProperty("appium.server.port", 4723);
            
            // Check if Appium is installed
            if (!isAppiumInstalled()) {
                logger.error("Appium is not installed or not in PATH. Please install Appium before running tests.");
                throw new RuntimeException("Appium is not installed or not in PATH");
            }
            
            // Build Appium service
            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .withIPAddress(appiumHost)
                    .usingPort(appiumPort)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info");
            
            // Start the service
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
            
            logger.info("Appium Server started successfully on " + appiumHost + ":" + appiumPort);
        } catch (Exception e) {
            logger.error("Failed to start Appium Server: " + e.getMessage());
            throw new RuntimeException("Failed to start Appium Server", e);
        }
    }
    
    /**
     * Stop Appium server
     */
    public static void stopServer() {
        logger.info("Stopping Appium Server...");
        
        try {
            if (service != null && service.isRunning()) {
                service.stop();
                logger.info("Appium Server stopped successfully");
            } else {
                logger.warn("Appium Server is not running or already stopped");
            }
        } catch (Exception e) {
            logger.error("Failed to stop Appium Server: " + e.getMessage());
        }
    }
    
    /**
     * Check if Appium server is running
     * 
     * @return true if server is running, false otherwise
     */
    public static boolean isServerRunning() {
        return service != null && service.isRunning();
    }
    
    /**
     * Check if Appium is installed and available in PATH
     * 
     * @return true if Appium is installed, false otherwise
     */
    private static boolean isAppiumInstalled() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                processBuilder.command("cmd.exe", "/c", "npm list -g appium");
            } else {
                processBuilder.command("npm", "list", "-g", "appium");
            }
            
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            logger.error("Error checking Appium installation: " + e.getMessage());
            return false;
        }
    }
}