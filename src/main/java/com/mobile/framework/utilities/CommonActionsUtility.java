package com.mobile.framework.utilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

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

    /**
     * Accept Android system permission if present
     * 
     * @param driver AppiumDriver instance
     */
    public static void acceptPermissionIfPresent(AppiumDriver driver) {
        By permissionBtn = By.id("com.android.permissioncontroller:id/permission_allow_button");
        try {
            WebElement element = WaitUtility.waitForElementVisible(driver, permissionBtn, 5);
            element.click();
            logger.info("Clicked on permission allow button");
        } catch (Exception e) {
            logger.info("Permission dialog not present or already handled");
        }
    }

    /**
     * Swipe from right to left (next screen in onboarding)
     */
    public static void swipeLeft(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int y = size.height / 2;
        swipe(driver, startX, y, endX, y);
    }

    /**
     * Swipe from left to right (previous screen)
     */
    public static void swipeRight(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int endX = (int) (size.width * 0.8);
        int y = size.height / 2;
        swipe(driver, startX, y, endX, y);
    }

    private static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(Duration.ZERO, org.openqa.selenium.interactions.PointerInput.Origin.viewport(), startX, startY));
        sequence.addAction(finger.createPointerDown(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), org.openqa.selenium.interactions.PointerInput.Origin.viewport(), endX, endY));
        sequence.addAction(finger.createPointerUp(org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
        logger.info("Swiped from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
    }
}
