package com.mobile.framework.utilities;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Common Actions Utility class to provide common mobile interactions
 * This utility provides methods for common mobile actions like tap, swipe, scroll, etc.
 */
public class CommonActionsUtility {
    
    private static final LoggerUtility logger = new LoggerUtility(CommonActionsUtility.class);
    
    /**
     * Tap on element
     * 
     * @param driver AppiumDriver instance
     * @param element WebElement to tap on
     */
    public static void tapElement(AppiumDriver driver, WebElement element) {
        try {
            element.click();
            logger.info("Tapped on element: " + element.toString());
        } catch (Exception e) {
            logger.error("Failed to tap on element: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Tap on element identified by locator
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     */
    public static void tapElement(AppiumDriver driver, By locator) {
        try {
            WebElement element = WaitUtility.waitForElementClickable(driver, locator);
            element.click();
            logger.info("Tapped on element: " + locator.toString());
        } catch (Exception e) {
            logger.error("Failed to tap on element: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Send text to element
     * 
     * @param driver AppiumDriver instance
     * @param element WebElement to send text to
     * @param text Text to send
     */
    public static void sendText(AppiumDriver driver, WebElement element, String text) {
        try {
            element.sendKeys(text);
            logger.info("Sent text '" + text + "' to element: " + element.toString());
        } catch (Exception e) {
            logger.error("Failed to send text to element: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Send text to element identified by locator
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     * @param text Text to send
     */
    public static void sendText(AppiumDriver driver, By locator, String text) {
        try {
            WebElement element = WaitUtility.waitForElementVisible(driver, locator);
            element.sendKeys(text);
            logger.info("Sent text '" + text + "' to element: " + locator.toString());
        } catch (Exception e) {
            logger.error("Failed to send text to element: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Scroll down on the screen
     * 
     * @param driver AppiumDriver instance
     */
    public static void scrollDown(AppiumDriver driver) {
        try {
            // Simple scroll down implementation using JavaScript executor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "down");
            js.executeScript("mobile: scroll", params);
            logger.info("Scrolled down on the screen");
        } catch (Exception e) {
            logger.error("Failed to scroll down: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Scroll up on the screen
     * 
     * @param driver AppiumDriver instance
     */
    public static void scrollUp(AppiumDriver driver) {
        try {
            // Simple scroll up implementation using JavaScript executor
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "up");
            js.executeScript("mobile: scroll", params);
            logger.info("Scrolled up on the screen");
        } catch (Exception e) {
            logger.error("Failed to scroll up: " + e.getMessage());
            throw e;
        }
    }
}