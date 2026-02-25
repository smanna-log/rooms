package com.mobile.framework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger Utility class to provide centralized logging functionality
 * This class wraps Log4j2 logger and provides easy-to-use methods
 */
public class LoggerUtility {
    
    private final Logger logger;
    
    /**
     * Constructor to initialize logger with class name
     * 
     * @param clazz Class for which logger is created
     */
    public LoggerUtility(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }
    
    /**
     * Log info message
     * 
     * @param message Message to log
     */
    public void info(String message) {
        logger.info(message);
    }
    
    /**
     * Log debug message
     * 
     * @param message Message to log
     */
    public void debug(String message) {
        logger.debug(message);
    }
    
    /**
     * Log warning message
     * 
     * @param message Message to log
     */
    public void warn(String message) {
        logger.warn(message);
    }
    
    /**
     * Log error message
     * 
     * @param message Message to log
     */
    public void error(String message) {
        logger.error(message);
    }
    
    /**
     * Log error message with exception
     * 
     * @param message Message to log
     * @param throwable Exception to log
     */
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
    
    /**
     * Log fatal message
     * 
     * @param message Message to log
     */
    public void fatal(String message) {
        logger.fatal(message);
    }
}