package com.mobile.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration Manager to read properties from config.properties file
 * This class follows Singleton pattern to ensure only one instance exists
 */
public class ConfigManager {
    private static ConfigManager configManager;
    private static final Properties properties = new Properties();

    // Private constructor to prevent instantiation
    private ConfigManager() {
        loadProperties();
    }

    /**
     * Get the singleton instance of ConfigManager
     *
     * @return ConfigManager instance
     */
    public static ConfigManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigManager();
        }
        return configManager;
    }

    /**
     * Load properties from config.properties file
     */
    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get property value by key
     *
     * @param key Property key
     * @return Property value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value by key with default value
     *
     * @param key          Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default value
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get integer property value by key
     *
     * @param key Property key
     * @return Integer property value
     */
    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    /**
     * Get integer property value by key with default value
     *
     * @param key          Property key
     * @param defaultValue Default value if key not found
     * @return Integer property value or default value
     */
    public int getIntProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}