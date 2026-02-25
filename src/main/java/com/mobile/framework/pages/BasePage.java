package com.mobile.framework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.mobile.framework.utilities.CommonActionsUtility;
import com.mobile.framework.utilities.LoggerUtility;
import com.mobile.framework.utilities.WaitUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Base Page class that provides common methods and utilities for all page classes
 * This class initializes page elements and provides common interactions
 */
public class BasePage {
    
    protected AppiumDriver driver;
    protected static final LoggerUtility logger = new LoggerUtility(BasePage.class);
    
    /**
     * Constructor to initialize page factory and driver
     * 
     * @param driver AppiumDriver instance
     */
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        // Initialize page factory with AppiumFieldDecorator for mobile elements
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }
    
    /**
     * Wait for element to be visible
     * 
     * @param locator By locator for the element
     * @return WebElement once it is visible
     */
    protected WebElement waitForElementVisible(By locator) {
        return WaitUtility.waitForElementVisible(driver, locator);
    }
    
    /**
     * Wait for element to be clickable
     * 
     * @param locator By locator for the element
     * @return WebElement once it is clickable
     */
    protected WebElement waitForElementClickable(By locator) {
        return WaitUtility.waitForElementClickable(driver, locator);
    }
    
    /**
     * Wait for element to be present
     * 
     * @param locator By locator for the element
     * @return WebElement once it is present
     */
    protected WebElement waitForElementPresent(By locator) {
        return WaitUtility.waitForElementPresent(driver, locator);
    }
    
    /**
     * Tap on element
     * 
     * @param element WebElement to tap on
     */
    protected void tapElement(WebElement element) {
        CommonActionsUtility.tapElement(driver, element);
    }
    
    /**
     * Tap on element identified by locator
     * 
     * @param locator By locator for the element
     */
    protected void tapElement(By locator) {
        CommonActionsUtility.tapElement(driver, locator);
    }
    
    /**
     * Send text to element
     * 
     * @param element WebElement to send text to
     * @param text Text to send
     */
    protected void sendText(WebElement element, String text) {
        CommonActionsUtility.sendText(driver, element, text);
    }
    
    /**
     * Send text to element identified by locator
     * 
     * @param locator By locator for the element
     * @param text Text to send
     */
    protected void sendText(By locator, String text) {
        CommonActionsUtility.sendText(driver, locator, text);
    }
    
    /**
     * Scroll down on the screen
     */
    protected void scrollDown() {
        CommonActionsUtility.scrollDown(driver);
    }
    
    /**
     * Scroll up on the screen
     */
    protected void scrollUp() {
        CommonActionsUtility.scrollUp(driver);
    }
}