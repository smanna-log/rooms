package com.mobile.framework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;


/**
 * Login Page class representing the login screen of the Flutter application
 * This class contains all the elements and actions related to the login functionality
 */
public class LoginPage extends BasePage {
    
    // Flutter locators for login page elements
    // Using byValueKey locator strategy for Flutter elements
    
    @AndroidBy(accessibility = "emailField")
    private WebElement emailField;
    
    @AndroidBy(accessibility = "passwordField")
    private WebElement passwordField;
    
    @AndroidBy(accessibility = "loginButton")
    private WebElement loginButton;
    
    @AndroidBy(accessibility = "forgotPasswordLink")
    private WebElement forgotPasswordLink;
    
    @AndroidBy(accessibility = "signUpLink")
    private WebElement signUpLink;
    
    /**
     * Constructor to initialize the login page
     * 
     * @param driver AppiumDriver instance
     */
    public LoginPage(AppiumDriver driver) {
        super(driver);
    }
    
    /**
     * Enter email in the email field
     * 
     * @param email Email to enter
     * @return LoginPage instance for method chaining
     */
    public LoginPage enterEmail(String email) {
        sendText(emailField, email);
        logger.info("Entered email: " + email);
        return this;
    }
    
    /**
     * Enter password in the password field
     * 
     * @param password Password to enter
     * @return LoginPage instance for method chaining
     */
    public LoginPage enterPassword(String password) {
        sendText(passwordField, password);
        logger.info("Entered password");
        return this;
    }
    
    /**
     * Click on the login button
     * 
     * @return LoginPage instance for method chaining
     */
    public LoginPage clickLoginButton() {
        tapElement(loginButton);
        logger.info("Clicked on login button");
        return this;
    }
    
    /**
     * Click on the sign up link
     * 
     * @return LoginPage instance for method chaining
     */
    public LoginPage clickSignUpLink() {
        tapElement(signUpLink);
        logger.info("Clicked on sign up link");
        return this;
    }
    
    /**
     * Click on the forgot password link
     * 
     * @return LoginPage instance for method chaining
     */
    public LoginPage clickForgotPasswordLink() {
        tapElement(forgotPasswordLink);
        logger.info("Clicked on forgot password link");
        return this;
    }
    
    /**
     * Perform login with email and password
     * 
     * @param email User's email
     * @param password User's password
     * @return LoginPage instance for method chaining
     */
    public LoginPage login(String email, String password) {
        enterEmail(email)
            .enterPassword(password)
            .clickLoginButton();
        return this;
    }
    
    /**
     * Check if login page is displayed
     * 
     * @return true if login page is displayed, false otherwise
     */
    public boolean isLoginPageDisplayed() {
        try {
            return waitForElementVisible(By.xpath("//android.widget.Button[@text='Login']")) != null;
        } catch (Exception e) {
            logger.error("Login page is not displayed: " + e.getMessage());
            return false;
        }
    }
}