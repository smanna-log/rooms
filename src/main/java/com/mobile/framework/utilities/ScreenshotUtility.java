package com.mobile.framework.utilities;

import com.mobile.framework.config.ConfigManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot Utility class to capture screenshots during test execution
 * This utility provides methods to take screenshots and save them to a specified location
 */
public class ScreenshotUtility {
    
    private static final LoggerUtility logger = new LoggerUtility(ScreenshotUtility.class);
    private static final String SCREENSHOT_PATH = ConfigManager.getInstance().getProperty("screenshot.path", "./reports/screenshots/");
    
    /**
     * Capture screenshot and save to file
     * 
     * @param driver WebDriver instance
     * @param testName Name of the test for screenshot filename
     * @return Path of the saved screenshot file
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        // Create screenshot directory if it doesn't exist
        File screenshotDir = new File(SCREENSHOT_PATH);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        
        // Generate timestamp for unique filename
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String filePath = SCREENSHOT_PATH + fileName;
        
        // Take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        
        try {
            // Copy screenshot to destination
            FileUtils.copyFile(source, new File(filePath));
            logger.info("Screenshot captured: " + filePath);
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}