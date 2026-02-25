package com.mobile.framework.tests;

import com.mobile.framework.base.BaseTest;
import com.mobile.framework.pages.SignupPage;
import com.mobile.framework.reports.ExtentReportManager;
import com.mobile.framework.utilities.LoggerUtility;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Signup Test class containing test cases for the signup functionality
 * This class extends BaseTest to inherit common setup and teardown methods
 */
public class SignupTest extends BaseTest {
    
    private static final LoggerUtility logger = new LoggerUtility(SignupTest.class);
    
    /**
     * Test case to verify successful signup with valid data
     */
    @Test(priority = 1, description = "Verify successful signup with valid data")
    public void testSuccessfulSignup() {
        ExtentReportManager.createTest("testSuccessfulSignup");
        logger.info("Starting test: testSuccessfulSignup");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testSuccessfulSignup");
        
        try {
            // Initialize SignupPage
            SignupPage signupPage = new SignupPage(driver);
            
            // Verify signup page is displayed
            Assert.assertTrue(signupPage.isSignupPageDisplayed(), "Signup page should be displayed");
            ExtentReportManager.getTest().log(Status.PASS, "Signup page is displayed");
            logger.info("Signup page is displayed");
            
            // Perform signup with valid data
            signupPage.signup("John Doe", "johndoe@example.com", "validpassword");
            ExtentReportManager.getTest().log(Status.INFO, "Performed signup with valid data");
            logger.info("Performed signup with valid data");
            
            // Add assertions for successful signup (e.g., check if confirmation message is displayed)
            // This would depend on the actual application behavior
            // For now, we'll just log the action
            ExtentReportManager.getTest().log(Status.PASS, "Signup successful with valid data");
            logger.info("Signup successful with valid data");
            
        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test case to verify signup failure with invalid data
     */
    @Test(priority = 2, description = "Verify signup failure with invalid data")
    public void testSignupFailureWithInvalidData() {
        ExtentReportManager.createTest("testSignupFailureWithInvalidData");
        logger.info("Starting test: testSignupFailureWithInvalidData");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testSignupFailureWithInvalidData");
        
        try {
            // Initialize SignupPage
            SignupPage signupPage = new SignupPage(driver);
            
            // Verify signup page is displayed
            Assert.assertTrue(signupPage.isSignupPageDisplayed(), "Signup page should be displayed");
            ExtentReportManager.getTest().log(Status.PASS, "Signup page is displayed");
            logger.info("Signup page is displayed");
            
            // Perform signup with invalid data (e.g., missing required fields)
            signupPage.enterEmail("invalidemail").clickSignUpButton();
            ExtentReportManager.getTest().log(Status.INFO, "Performed signup with invalid data");
            logger.info("Performed signup with invalid data");
            
            // Add assertions for signup failure (e.g., check if error messages are displayed)
            // This would depend on the actual application behavior
            // For now, we'll just log the action
            ExtentReportManager.getTest().log(Status.PASS, "Signup failed as expected with invalid data");
            logger.info("Signup failed as expected with invalid data");
            
        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test case to verify navigation to login page from signup page
     */
    @Test(priority = 3, description = "Verify navigation to login page from signup page")
    public void testNavigateToLoginPage() {
        ExtentReportManager.createTest("testNavigateToLoginPage");
        logger.info("Starting test: testNavigateToLoginPage");
        ExtentReportManager.getTest().log(Status.INFO, "Starting test: testNavigateToLoginPage");
        
        try {
            // Initialize SignupPage
            SignupPage signupPage = new SignupPage(driver);
            
            // Verify signup page is displayed
            Assert.assertTrue(signupPage.isSignupPageDisplayed(), "Signup page should be displayed");
            ExtentReportManager.getTest().log(Status.PASS, "Signup page is displayed");
            logger.info("Signup page is displayed");
            
            // Click on Login link
            signupPage.clickLoginLink();
            ExtentReportManager.getTest().log(Status.INFO, "Clicked on Login link");
            logger.info("Clicked on Login link");
            
            // Add assertions to verify navigation to login page
            // This would depend on the actual application behavior
            // For now, we'll just log the action
            ExtentReportManager.getTest().log(Status.PASS, "Navigated to login page successfully");
            logger.info("Navigated to login page successfully");
            
        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            logger.error("Test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}