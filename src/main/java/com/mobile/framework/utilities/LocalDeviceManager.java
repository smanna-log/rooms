package com.mobile.framework.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility to manage and list local Android devices via ADB.
 */
public class LocalDeviceManager {
    private static final LoggerUtility logger = new LoggerUtility(LocalDeviceManager.class);
    private static final String ADB_PATH = "/home/wadmin/Android/Sdk/platform-tools/adb";

    /**
     * Gets a list of UDIDs for all connected Android devices/emulators.
     * @return List of device UDIDs
     */
    public static List<String> getConnectedDevices() {
        List<String> devices = new ArrayList<>();
        try {
            Process process = new ProcessBuilder(ADB_PATH, "devices").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // Skip the first line "List of devices attached"
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("device")) {
                    devices.add(line.split("\t")[0]);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to list connected devices: " + e.getMessage());
        }
        return devices;
    }

    /**
     * Gets the screen density (DPI) of a device.
     * @param udid Device UDID
     * @return Density value
     */
    public static String getDeviceDensity(String udid) {
        try {
            Process process = new ProcessBuilder(ADB_PATH, "-s", udid, "shell", "wm", "density").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                return line.split(":")[1].trim();
            }
        } catch (Exception e) {
            logger.error("Failed to get density for " + udid + ": " + e.getMessage());
        }
        return "unknown";
    }

    /**
     * Gets the screen resolution of a device.
     * @param udid Device UDID
     * @return Resolution string (e.g. 1080x1920)
     */
    public static String getDeviceResolution(String udid) {
        try {
            Process process = new ProcessBuilder(ADB_PATH, "-s", udid, "shell", "wm", "size").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                return line.split(":")[1].trim();
            }
        } catch (Exception e) {
            logger.error("Failed to get resolution for " + udid + ": " + e.getMessage());
        }
        return "unknown";
    }

    /**
     * Resets the screen resolution and density to the device default.
     * @param udid Device UDID
     */
    public static void resetDeviceDisplay(String udid) {
        try {
            new ProcessBuilder(ADB_PATH, "-s", udid, "shell", "wm", "size", "reset").start();
            new ProcessBuilder(ADB_PATH, "-s", udid, "shell", "wm", "density", "reset").start();
            logger.info("Reset display for " + udid);
        } catch (Exception e) {
            logger.error("Failed to reset display for " + udid + ": " + e.getMessage());
        }
    }

    /**
     * Sets the screen resolution.
     * @param udid Device UDID
     * @param resolution Resolution string (e.g. 1080x1920)
     */
    public static void setDeviceResolution(String udid, String resolution) {
        try {
            new ProcessBuilder(ADB_PATH, "-s", udid, "shell", "wm", "size", resolution).start();
            logger.info("Set resolution to " + resolution + " for " + udid);
        } catch (Exception e) {
            logger.error("Failed to set resolution for " + udid + ": " + e.getMessage());
        }
    }

    /**
     * Sets the screen density.
     * @param udid Device UDID
     * @param density Density value (e.g. 440)
     */
    public static void setDeviceDensity(String udid, int density) {
        try {
            new ProcessBuilder(ADB_PATH, "-s", udid, "shell", "wm", "density", String.valueOf(density)).start();
            logger.info("Set density to " + density + " for " + udid);
        } catch (Exception e) {
            logger.error("Failed to set density for " + udid + ": " + e.getMessage());
        }
    }
}
