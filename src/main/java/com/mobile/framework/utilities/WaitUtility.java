package com.mobile.framework.utilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Wait Utility class to provide explicit wait functionality
 * This utility provides common wait methods for mobile elements
 */
public class WaitUtility {
    
    private static final LoggerUtility logger = new LoggerUtility(WaitUtility.class);
    private static final int DEFAULT_TIMEOUT = 30;
    
    /**
     * Wait for element to be visible
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     * @return WebElement once it is visible
     */
    public static WebElement waitForElementVisible(AppiumDriver driver, By locator) {
        return waitForElementVisible(driver, locator, DEFAULT_TIMEOUT);
    }
    
    /**
     * Wait for element to be visible
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     * @param timeout Timeout in seconds
     * @return WebElement once it is visible
     */
    public static WebElement waitForElementVisible(AppiumDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not visible within " + timeout + " seconds: " + locator.toString());
            throw e;
        }
    }
    
    /**
     * Wait for element to be clickable
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     * @return WebElement once it is clickable
     */
    public static WebElement waitForElementClickable(AppiumDriver driver, By locator) {
        return waitForElementClickable(driver, locator, DEFAULT_TIMEOUT);
    }
    
    /**
     * Wait for element to be clickable
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     * @param timeout Timeout in seconds
     * @return WebElement once it is clickable
     */
    public static WebElement waitForElementClickable(AppiumDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Element not clickable within " + timeout + " seconds: " + locator.toString());
            throw e;
        }
    }
    
    /**
     * Wait for element to be present
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     * @return WebElement once it is present
     */
    public static WebElement waitForElementPresent(AppiumDriver driver, By locator) {
        return waitForElementPresent(driver, locator, DEFAULT_TIMEOUT);
    }
    
    /**
     * Wait for element to be present
     * 
     * @param driver AppiumDriver instance
     * @param locator By locator for the element
     * @param timeout Timeout in seconds
     * @return WebElement once it is present
     */
    public static WebElement waitForElementPresent(AppiumDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not present within " + timeout + " seconds: " + locator.toString());
            throw e;
        }
    }
}