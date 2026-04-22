package com.mobile.framework.pages;

// import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SignInPage extends BasePage {

    
    public SignInPage(AppiumDriver driver) {
        super(driver);
        acceptPermissionIfPresent();

    }

    // Email Address Button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.roomservice.realstate:id/tvSignInWithGoogle']")
    private WebElement EmailAddressButton;

    // Email or Phone Number field
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.roomservice.realstate:id/editTextTextEmailAddress']")
    private WebElement EmailOrPhoneNumberField;

    // Please enter email address popup
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.roomservice.realstate:id/tvContent']")
    private WebElement PleaseEnterEmailAddressPopup;

    // Click Dismiss Button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.roomservice.realstate:id/btnDismiss']")
    private WebElement DismissButton;

    // Password Field
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.roomservice.realstate:id/password']")
    private WebElement PasswordField;

    // Sign In Button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.roomservice.realstate:id/btnSignIn']")
    private WebElement SignInButton;

   
    // ------------------------------------------------------

   

    public SignInPage emailAddressButton() {
        tapElement(EmailAddressButton);
        logger.info("Email Address Button Clicked");
        return this;
    }

    public SignInPage emailorPhoneNumber(String emailorPhoneNumbers) {
        sendText(EmailOrPhoneNumberField, emailorPhoneNumbers);
        logger.info("Entered Email or Phone Number");
        return this;

    }

    public SignInPage password(String password) {
        sendText(PasswordField, password);
        logger.info("Entered Password");
        return this;

    }

    public SignInPage signInButton() {
        tapElement(SignInButton);
        logger.info("Clicked on sign in button");
        return this;

    }

    public SignInPage dismissButton() {
        tapElement(DismissButton);
        logger.info("Clicked on dismiss button");
        return this;

    }

    public SignInPage register(String emailorPhoneNumber, String password) {
        return emailAddressButton()
                .emailorPhoneNumber(emailorPhoneNumber)
                .password(password)
                .signInButton();
    }





    // Backward-compatible wrappers for existing naming
    
}
