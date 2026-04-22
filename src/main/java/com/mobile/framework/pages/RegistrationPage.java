package com.mobile.framework.pages;

// import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RegistrationPage extends BasePage {

    // private static final By FIRST_NAME_FIELD = By.id("com.roomservice.realstate:id/first_name");
    // private static final By LAST_NAME_FIELD = By.id("com.roomservice.realstate:id/last_name");
    // private static final By EMAIL_FIELD = By.id("com.roomservice.realstate:id/email");
    // private static final By PASSWORD_FIELD = By.id("com.roomservice.realstate:id/password");
    // private static final By REGION_FIELD = By.id("com.roomservice.realstate:id/etRegion");
    // private static final By CREATE_ACCOUNT_BUTTON = By.id("com.roomservice.realstate:id/btnCreateAccount");
    // private static final By PAGE_TITLE = By.id("com.roomservice.realstate:id/CreateNewAccount");

    public RegistrationPage(AppiumDriver driver) {
        super(driver);
        acceptPermissionIfPresent();

    }

    // First Name field
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.roomservice.realstate:id/first_name']")
    private WebElement firstNameField;

    // Last Name field
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.roomservice.realstate:id/last_name']")
    private WebElement lastNameField;

    // Email field
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.roomservice.realstate:id/email']")
    private WebElement emailField;

    // Password field
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.roomservice.realstate:id/password']")
    private WebElement passwordField;

    // Region dropdown (AutoCompleteTextView)
    @AndroidFindBy(xpath = "//android.widget.AutoCompleteTextView[@resource-id='com.roomservice.realstate:id/etRegion']")
    private WebElement regionField;

    // Create New Account button
    // Create Account Button (CORRECT BUTTON - Clickable)
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.roomservice.realstate:id/btnCreateAccount']")
    private WebElement createAccountButton;


    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.roomservice.realstate:id/CreateNewAccount']")
    private WebElement pageTitleText;
    // ------------------------------------------------------

   

    public RegistrationPage clickCreateNewAccount() {
        tapElement(pageTitleText);
        logger.info("Clicked create new account title");
        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        sendText(firstNameField, firstName);
        logger.info("Entered First name");
        return this;

    }

    public RegistrationPage enterLastName(String lastName) {
        sendText(lastNameField, lastName);
        logger.info("Entered Last Name");
        return this;

    }

    public RegistrationPage enterEmail(String email) {
        sendText(emailField, email);
        logger.info("Entered email Name");
        return this;

    }

    public RegistrationPage enterPassword(String password) {
        sendText(passwordField, password);
        logger.info("Entered email Name");
        return this;

    }

    public RegistrationPage enterRegion(String region) {
        sendText(regionField, region);
        logger.info("Entered  region");
        return this;

    }

    

    public RegistrationPage clickCreateButton() {
        tapElement(createAccountButton);
        logger.info("Clicked on create account button");
        return this;
    }

    public RegistrationPage register(String firstName, String lastName, String email, String password, String region) {
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterRegion(region)
                .clickCreateButton();
    }





    // Backward-compatible wrappers for existing naming
    
}
