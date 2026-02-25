package com.mobile.framework.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry Analyzer to retry failed tests
 * This class implements IRetryAnalyzer interface to provide retry functionality for failed tests
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    
    private static final LoggerUtility logger = new LoggerUtility(RetryAnalyzer.class);
    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Retry failed tests up to 2 times
    
    /**
     * Retry method to determine if a test should be retried
     * 
     * @param result ITestResult instance containing test result information
     * @return true if test should be retried, false otherwise
     */
    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.info("Retrying test method: " + result.getName() + ", retry count: " + retryCount);
            return true;
        }
        return false;
    }
}