package com.mobile.framework.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.mobile.framework.base.BaseTest;
import com.mobile.framework.pages.RegistrationPage;
import com.mobile.framework.reports.ExtentReportManager;
import com.mobile.framework.utilities.WaitUtility;

public class RegistrationTest extends BaseTest {

    private static final By CREATE_NEW_ACCOUNT_TITLE = By.id("com.roomservice.realstate:id/CreateNewAccount");
    private static final By FIRST_NAME_FIELD = By.id("com.roomservice.realstate:id/first_name");
    private static final By LAST_NAME_FIELD = By.id("com.roomservice.realstate:id/last_name");
    private static final By EMAIL_FIELD = By.id("com.roomservice.realstate:id/email");
    private static final By PASSWORD_FIELD = By.id("com.roomservice.realstate:id/password");
    private static final By REGION_FIELD = By.id("com.roomservice.realstate:id/etRegion");
    private static final By CREATE_ACCOUNT_BUTTON = By.id("com.roomservice.realstate:id/btnCreateAccount");

    @Test(priority = 1, description = "Verify dynamic registration with explicit waits")
    public void testDynamicRegistrationWithExplicitWait() {
        ExtentReportManager.createTest("testDynamicRegistrationWithExplicitWait");
        logger.info("Starting test: testDynamicRegistrationWithExplicitWait");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testDynamicRegistrationWithExplicitWait");

        try {
            RegistrationPage registrationPage = new RegistrationPage(driver);

            WaitUtility.waitForElementClickable(driver, CREATE_NEW_ACCOUNT_TITLE);
            registrationPage.clickCreateNewAccount();
            ExtentReportManager.getTest().log(Status.INFO, "Opened registration form");

            String uniqueId = String.valueOf(System.currentTimeMillis());
            String firstName = "Auto" + uniqueId.substring(uniqueId.length() - 4);
            String lastName = "User";
            String email = "auto.user." + uniqueId + "@mailinator.com";
            String password = "P@ss" + uniqueId.substring(uniqueId.length() - 6);
            String region = "India";

            WaitUtility.waitForElementVisible(driver, FIRST_NAME_FIELD);
            registrationPage.enterFirstName(firstName);

            WaitUtility.waitForElementVisible(driver, LAST_NAME_FIELD);
            registrationPage.enterLastName(lastName);

            WaitUtility.waitForElementVisible(driver, EMAIL_FIELD);
            registrationPage.enterEmail(email);

            WaitUtility.waitForElementVisible(driver, PASSWORD_FIELD);
            registrationPage.enterPassword(password);

            WaitUtility.waitForElementVisible(driver, REGION_FIELD);
            registrationPage.enterRegion(region);

            WaitUtility.waitForElementClickable(driver, CREATE_ACCOUNT_BUTTON);
            registrationPage.clickCreateButton();

            ExtentReportManager.getTest().log(Status.PASS,
                    "Registration form submitted with dynamic email: " + email);
            logger.info("Registration form submitted successfully with email: " + email);

        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

}
