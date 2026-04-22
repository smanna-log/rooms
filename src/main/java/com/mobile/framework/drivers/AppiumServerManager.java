package com.mobile.framework.drivers;

import java.io.File;
import java.time.Duration;

import com.mobile.framework.config.ConfigManager;
import com.mobile.framework.utilities.LoggerUtility;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * Appium Server Manager to start and stop Appium server programmatically.
 * Uses explicit full paths to Node.js and Appium because the framework runs
 * via Java which does not inherit the shell's fnm-managed PATH.
 */
public class AppiumServerManager {

    private static final LoggerUtility logger = new LoggerUtility(AppiumServerManager.class);
    private static AppiumDriverLocalService service;

    // Full paths to fnm-managed Node.js and Appium installation
    private static final String NODE_PATH = "/home/wadmin/.nvm/versions/node/v24.11.1/bin/node";
    private static final String APPIUM_JS_PATH = "/home/wadmin/.nvm/versions/node/v24.11.1/lib/node_modules/appium/index.js";
    /**
     * Start Appium server
     */
    public static void startServer() {
        logger.info("Starting Appium Server...");

        try {
            String appiumHost = ConfigManager.getInstance().getProperty("appium.server.host", "127.0.0.1");
            int appiumPort = ConfigManager.getInstance().getIntProperty("appium.server.port", 4723);

            File nodeExe = new File(NODE_PATH);
            File appiumJs = new File(APPIUM_JS_PATH);

            if (!nodeExe.exists()) {
                throw new RuntimeException("Node.js binary not found at: " + nodeExe.getAbsolutePath());
            }
            if (!appiumJs.exists()) {
                throw new RuntimeException("Appium index.js not found at: " + appiumJs.getAbsolutePath());
            }

            AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .usingDriverExecutable(nodeExe)
                    .withAppiumJS(appiumJs)
                    .withIPAddress(appiumHost)
                    .usingPort(appiumPort)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                    .withTimeout(Duration.ofSeconds(60));

            service = AppiumDriverLocalService.buildService(builder);
            service.start();

            if (service == null || !service.isRunning()) {
                throw new RuntimeException("Appium server failed to start!");
            }

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
}