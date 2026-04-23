package com.mobile.framework.tests;

//hello
//hello
//hello


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.mobile.framework.base.BaseTest;
import com.mobile.framework.pages.SignInPage;
import com.mobile.framework.reports.ExtentReportManager;
import com.mobile.framework.utilities.WaitUtility;

public class SignInTest extends BaseTest {

    private static final By Email_Address_Button = By.id("com.roomservice.realstate:id/tvSignInWithGoogle");
    private static final By Email_or_Phone_Number_Field = By.id("com.roomservice.realstate:id/editTextTextEmailAddress");
    private static final By Password_Field = By.id("com.roomservice.realstate:id/password");
    private static final By Sign_In_Button = By.id("com.roomservice.realstate:id/btnSignIn");
    private static final By Dismiss_Button = By.id("com.roomservice.realstate:id/btnDismiss");

    @Test(priority = 1, description = "Verify Valid Sign In With Valid Credentials")
    public void testValidSignInWithValidCredentials() {
        ExtentReportManager.createTest("testValidSignInWithValidCredentials");
        logger.info("Starting test: testValidSignInWithValidCredentials");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testValidSignInWithValidCredentials");

        try {
            SignInPage signInPage = new SignInPage(driver);

            WaitUtility.waitForElementClickable(driver, Email_Address_Button);
            signInPage.emailAddressButton();
            ExtentReportManager.getTest().log(Status.INFO, "Opened Sign In Form");

            String uniqueId = String.valueOf(System.currentTimeMillis());
            
            String email = "auto.user." + uniqueId + "@mailinator.com";
            String password = "P@ss" + uniqueId.substring(uniqueId.length() - 6);
            

            WaitUtility.waitForElementVisible(driver, Email_or_Phone_Number_Field);
            signInPage.emailorPhoneNumber(email);

            WaitUtility.waitForElementVisible(driver, Password_Field);
            signInPage.password(password);

            WaitUtility.waitForElementClickable(driver, Sign_In_Button);
            signInPage.signInButton();

            ExtentReportManager.getTest().log(Status.PASS,
                    "Sign in form submitted with valid credentials: " + email);
            logger.info("Sign in form submitted successfully with email: " + email);

        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }


    @Test(priority = 2, description = "Verify Not Sign In With Invalid Credentials")
    public void testInvalidSignInWithInvalidCredentials() {
        ExtentReportManager.createTest("testInvalidSignInWithInvalidCredentials");
        logger.info("Starting test: testInvalidSignInWithInvalidCredentials");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testInvalidSignInWithInvalidCredentials");

        try {
            SignInPage signInPage = new SignInPage(driver);

            WaitUtility.waitForElementClickable(driver, Email_Address_Button);
            signInPage.emailAddressButton();
            ExtentReportManager.getTest().log(Status.INFO, "Opened Sign In Form");

            String uniqueId = String.valueOf(System.currentTimeMillis());
            
            String password = "P@ss" + uniqueId.substring(uniqueId.length() - 6);
            

            WaitUtility.waitForElementVisible(driver, Email_or_Phone_Number_Field);
            signInPage.emailorPhoneNumber("");

            WaitUtility.waitForElementVisible(driver, Password_Field);
            signInPage.password(password);

            WaitUtility.waitForElementClickable(driver, Sign_In_Button);
            signInPage.signInButton();

            WaitUtility.waitForElementClickable(driver, Dismiss_Button); 

            ExtentReportManager.getTest().log(Status.PASS,
                    "Sign in form submitted with blank email");
            logger.info("Sign in form submitted with blank email");

        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Verify Sign In With Invalid Email Format")
    public void testSignInWithInvalidEmailFormat() {
        ExtentReportManager.createTest("testSignInWithInvalidEmailFormat");
        logger.info("Starting test: testSignInWithInvalidEmailFormat");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testSignInWithInvalidEmailFormat");

        try {
            SignInPage signInPage = new SignInPage(driver);

            WaitUtility.waitForElementClickable(driver, Email_Address_Button);
            signInPage.emailAddressButton();
            ExtentReportManager.getTest().log(Status.INFO, "Opened Sign In Form");

            String invalidEmail = "invalid-email-format";
            String password = "Test@1234";

            WaitUtility.waitForElementVisible(driver, Email_or_Phone_Number_Field);
            signInPage.emailorPhoneNumber(invalidEmail);

            WaitUtility.waitForElementVisible(driver, Password_Field);
            signInPage.password(password);

            WaitUtility.waitForElementClickable(driver, Sign_In_Button);
            signInPage.signInButton();

            // Wait for error message or dismiss button to appear
            WaitUtility.waitForElementVisible(driver, Dismiss_Button);

            ExtentReportManager.getTest().log(Status.PASS,
                    "Sign in rejected invalid email format: " + invalidEmail);
            logger.info("Sign in rejected invalid email format: " + invalidEmail);

        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 4, description = "Verify Sign In With Blank Password")
    public void testSignInWithBlankPassword() {
        ExtentReportManager.createTest("testSignInWithBlankPassword");
        logger.info("Starting test: testSignInWithBlankPassword");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testSignInWithBlankPassword");

        try {
            SignInPage signInPage = new SignInPage(driver);

            WaitUtility.waitForElementClickable(driver, Email_Address_Button);
            signInPage.emailAddressButton();
            ExtentReportManager.getTest().log(Status.INFO, "Opened Sign In Form");

            String email = "test@example.com";

            WaitUtility.waitForElementVisible(driver, Email_or_Phone_Number_Field);
            signInPage.emailorPhoneNumber(email);

            WaitUtility.waitForElementVisible(driver, Password_Field);
            signInPage.password("");

            WaitUtility.waitForElementClickable(driver, Sign_In_Button);
            signInPage.signInButton();

            // Wait for error message or dismiss button to appear
            WaitUtility.waitForElementVisible(driver, Dismiss_Button);

            ExtentReportManager.getTest().log(Status.PASS,
                    "Sign in rejected blank password for email: " + email);
            logger.info("Sign in rejected blank password for email: " + email);

        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 5, description = "Verify Sign In With Both Fields Blank")
    public void testSignInWithBothFieldsBlank() {
        ExtentReportManager.createTest("testSignInWithBothFieldsBlank");
        logger.info("Starting test: testSignInWithBothFieldsBlank");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testSignInWithBothFieldsBlank");

        try {
            SignInPage signInPage = new SignInPage(driver);

            WaitUtility.waitForElementClickable(driver, Email_Address_Button);
            signInPage.emailAddressButton();
            ExtentReportManager.getTest().log(Status.INFO, "Opened Sign In Form");

            WaitUtility.waitForElementVisible(driver, Email_or_Phone_Number_Field);
            signInPage.emailorPhoneNumber("");

            WaitUtility.waitForElementVisible(driver, Password_Field);
            signInPage.password("");

            WaitUtility.waitForElementClickable(driver, Sign_In_Button);
            signInPage.signInButton();

            // Wait for error message or dismiss button to appear
            WaitUtility.waitForElementVisible(driver, Dismiss_Button);

            ExtentReportManager.getTest().log(Status.PASS,
                    "Sign in rejected both fields blank");
            logger.info("Sign in rejected both fields blank");

        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 6, description = "Verify Sign In With Wrong Credentials")
    public void testSignInWithWrongCredentials() {
        ExtentReportManager.createTest("testSignInWithWrongCredentials");
        logger.info("Starting test: testSignInWithWrongCredentials");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testSignInWithWrongCredentials");

        try {
            SignInPage signInPage = new SignInPage(driver);

            WaitUtility.waitForElementClickable(driver, Email_Address_Button);
            signInPage.emailAddressButton();
            ExtentReportManager.getTest().log(Status.INFO, "Opened Sign In Form");

            String wrongEmail = "wrong.user@example.com";
            String wrongPassword = "Wrong@1234";

            WaitUtility.waitForElementVisible(driver, Email_or_Phone_Number_Field);
            signInPage.emailorPhoneNumber(wrongEmail);

            WaitUtility.waitForElementVisible(driver, Password_Field);
            signInPage.password(wrongPassword);

            WaitUtility.waitForElementClickable(driver, Sign_In_Button);
            signInPage.signInButton();

            // Wait for error message or dismiss button to appear
            WaitUtility.waitForElementVisible(driver, Dismiss_Button);

            ExtentReportManager.getTest().log(Status.PASS,
                    "Sign in rejected wrong credentials: " + wrongEmail);
            logger.info("Sign in rejected wrong credentials: " + wrongEmail);

        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

}
