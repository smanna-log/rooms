package com.mobile.framework.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.By;

/**
 * Signup Page class representing the signup screen of the Flutter application
 * This class contains all the elements and actions related to the signup functionality
 */
public class SignupPage extends BasePage {
    
    // Flutter locators for signup page elements
    // Using byValueKey locator strategy for Flutter elements
    
    @AndroidBy(accessibility = "fullNameField")
    private WebElement fullNameField;
    
    @AndroidBy(accessibility = "emailField")
    private WebElement emailField;
    
    @AndroidBy(accessibility = "passwordField")
    private WebElement passwordField;
    
    @AndroidBy(accessibility = "confirmPasswordField")
    private WebElement confirmPasswordField;
    
    @AndroidBy(accessibility = "signUpButton")
    private WebElement signUpButton;
    
    @AndroidBy(accessibility = "loginLink")
    private WebElement loginLink;
    
    /**
     * Constructor to initialize the signup page
     * 
     * @param driver AppiumDriver instance
     */
    public SignupPage(AppiumDriver driver) {
        super(driver);
    }
    
    /**
     * Enter full name in the full name field
     * 
     * @param fullName Full name to enter
     * @return SignupPage instance for method chaining
     */
    public SignupPage enterFullName(String fullName) {
        sendText(fullNameField, fullName);
        logger.info("Entered full name: " + fullName);
        return this;
    }
    
    /**
     * Enter email in the email field
     * 
     * @param email Email to enter
     * @return SignupPage instance for method chaining
     */
    public SignupPage enterEmail(String email) {
        sendText(emailField, email);
        logger.info("Entered email: " + email);
        return this;
    }
    
    /**
     * Enter password in the password field
     * 
     * @param password Password to enter
     * @return SignupPage instance for method chaining
     */
    public SignupPage enterPassword(String password) {
        sendText(passwordField, password);
        logger.info("Entered password");
        return this;
    }
    
    /**
     * Enter confirm password in the confirm password field
     * 
     * @param confirmPassword Confirm password to enter
     * @return SignupPage instance for method chaining
     */
    public SignupPage enterConfirmPassword(String confirmPassword) {
        sendText(confirmPasswordField, confirmPassword);
        logger.info("Entered confirm password");
        return this;
    }
    
    /**
     * Click on the sign up button
     * 
     * @return SignupPage instance for method chaining
     */
    public SignupPage clickSignUpButton() {
        tapElement(signUpButton);
        logger.info("Clicked on sign up button");
        return this;
    }
    
    /**
     * Click on the login link
     * 
     * @return SignupPage instance for method chaining
     */
    public SignupPage clickLoginLink() {
        tapElement(loginLink);
        logger.info("Clicked on login link");
        return this;
    }
    
    /**
     * Perform signup with full name, email, and password
     * 
     * @param fullName User's full name
     * @param email User's email
     * @param password User's password
     * @return SignupPage instance for method chaining
     */
    public SignupPage signup(String fullName, String email, String password) {
        return enterFullName(fullName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .clickSignUpButton();
    }
    
    /**
     * Check if signup page is displayed
     * 
     * @return true if signup page is displayed, false otherwise
     */
    public boolean isSignupPageDisplayed() {
        try {
            return waitForElementVisible(By.xpath("//android.widget.Button[@text='Sign Up']")) != null;
        } catch (Exception e) {
            logger.error("Signup page is not displayed: " + e.getMessage());
            return false;
        }
    }
}