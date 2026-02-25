package com.mobile.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mobile.framework.config.ConfigManager;
import com.mobile.framework.utilities.LoggerUtility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Extent Report Manager to handle ExtentReports initialization and management
 * This class follows Singleton pattern to ensure only one instance exists
 */
public class ExtentReportManager {
    
    private static final LoggerUtility logger = new LoggerUtility(ExtentReportManager.class);
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
    /**
     * Initialize ExtentReports
     */
    public static void initReports() {
        if (extent == null) {
            logger.info("Initializing Extent Reports...");
            
            try {
                // Create reports directory if it doesn't exist
                String reportPath = ConfigManager.getInstance().getProperty("report.path", "./reports/");
                File reportDir = new File(reportPath);
                if (!reportDir.exists()) {
                    reportDir.mkdirs();
                }
                
                // Generate timestamp for unique report filename
                String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
                String reportFilePath = reportPath + "AutomationReport_" + timestamp + ".html";
                
                // Initialize ExtentSparkReporter
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
                sparkReporter.config().setReportName("Flutter Mobile Automation Report");
                sparkReporter.config().setDocumentTitle("Test Automation Report");
                
                // Initialize ExtentReports
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("Framework", "Flutter Mobile Automation Framework");
                extent.setSystemInfo("Author", "SDET Automation Tester");
                
                logger.info("Extent Reports initialized successfully. Report will be saved to: " + reportFilePath);
            } catch (Exception e) {
                logger.error("Failed to initialize Extent Reports: " + e.getMessage());
                throw new RuntimeException("Failed to initialize Extent Reports", e);
            }
        }
    }
    
    /**
     * Create a new test in the report
     * 
     * @param testName Name of the test
     * @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName) {
        initReports();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }
    
    /**
     * Get the current test instance
     * 
     * @return ExtentTest instance
     */
    public static ExtentTest getTest() {
        return test.get();
    }
    
    /**
     * Remove the current test instance
     */
    public static void removeTest() {
        test.remove();
    }
    
    /**
     * Flush and close all reports
     */
    public static void flushReports() {
        if (extent != null) {
            logger.info("Flushing Extent Reports...");
            extent.flush();
            logger.info("Extent Reports flushed successfully");
        }
    }
}