package com.mobile.framework.utilities;

import com.mobile.framework.utilities.LoggerUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Emulator Manager to handle Android emulator operations
 * This class provides methods to start and stop Android emulators
 */
public class EmulatorManager {

    private static final LoggerUtility logger = new LoggerUtility(EmulatorManager.class);
    private static Process emulatorProcess = null;

    // Full paths to Android SDK tools to avoid PATH issues when launched by Java
    private static final String ADB_PATH = "/home/wadmin/Android/Sdk/platform-tools/adb";
    private static final String EMULATOR_PATH = "/home/wadmin/Android/Sdk/emulator/emulator";

    /**
     * Start Android emulator
     * 
     * @param avdName Name of the Android Virtual Device to start
     * @param port    Port number for the emulator (e.g., 5554)
     * @return true if emulator started successfully, false otherwise
     */
    public static boolean startEmulator(String avdName, int port) {
        logger.info("Starting Android emulator: " + avdName + " on port: " + port);

        try {
            // Use full path to emulator binary so Java can find it regardless of PATH
            String[] command = {
                    EMULATOR_PATH, "-avd", avdName,
                    "-port", String.valueOf(port),
                    "-no-boot-anim", "-no-snapshot"
            };

            // Start the emulator process
            emulatorProcess = Runtime.getRuntime().exec(command);

            // Wait for emulator to boot
            waitForEmulatorBoot(port);

            logger.info("Emulator started successfully: " + avdName + " on port: " + port);
            return true;
        } catch (IOException e) {
            logger.error("Failed to start emulator: " + e.getMessage());
            return false;
        }
    }

    /**
     * Wait for emulator to boot completely
     * 
     * @param port Port number of the emulator
     */
    private static void waitForEmulatorBoot(int port) {
        logger.info("Waiting for emulator to boot...");

        try {
            // Wait for a while to allow emulator to start
            Thread.sleep(30000); // Wait for 30 seconds initially

            // Check if device is ready
            boolean isDeviceReady = false;
            int attempts = 0;
            int maxAttempts = 30; // 5 minutes max wait time

            while (!isDeviceReady && attempts < maxAttempts) {
                Process process = Runtime.getRuntime().exec(ADB_PATH + " shell getprop sys.boot_completed");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = reader.readLine();

                if (line != null && line.trim().equals("1")) {
                    isDeviceReady = true;
                    break;
                }

                if (!isDeviceReady) {
                    Thread.sleep(10000); // Wait for 10 more seconds
                    attempts++;
                    logger.info("Waiting for emulator to boot... attempt " + attempts + "/" + maxAttempts);
                }
            }

            if (isDeviceReady) {
                logger.info("Emulator is ready for use");
            } else {
                throw new RuntimeException("Emulator failed to boot within " + (maxAttempts * 10 / 60) + " minutes");
            }
        } catch (Exception e) {
            logger.error("Error waiting for emulator to boot: " + e.getMessage());
        }
    }

    /**
     * Check if emulator is running
     * 
     * @param port Port number of the emulator
     * @return true if emulator is running, false otherwise
     */
    public static boolean isEmulatorRunning(int port) {
        try {
            Process process = Runtime.getRuntime().exec(ADB_PATH + " devices");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("emulator-" + port) && line.contains("device")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("Error checking emulator status: " + e.getMessage());
            return false;
        }
    }

    /**
     * Stop the emulator
     */
    public static void stopEmulator() {
        logger.info("Stopping Android emulator...");

        try {
            if (emulatorProcess != null && emulatorProcess.isAlive()) {
                emulatorProcess.destroy();
                logger.info("Emulator stopped successfully");
            } else {
                logger.info("No emulator process to stop");
            }
        } catch (Exception e) {
            logger.error("Error stopping emulator: " + e.getMessage());
        }
    }

    /**
     * Start ADB server
     * 
     * @return true if ADB server started successfully, false otherwise
     */
    public static boolean startAdbServer() {
        logger.info("Starting ADB server...");

        try {
            // Use full path to adb so Java can find it regardless of PATH
            Process process = Runtime.getRuntime().exec(ADB_PATH + " start-server");
            process.waitFor();
            logger.info("ADB server started successfully");
            return true;
        } catch (Exception e) {
            logger.error("Failed to start ADB server: " + e.getMessage());
            return false;
        }
    }
}