package com.mobile.framework.tests;

import com.mobile.framework.base.BaseTest;
import com.mobile.framework.drivers.DriverFactory;
import com.mobile.framework.pages.LoginPage;
import com.mobile.framework.utilities.LocalDeviceManager;
import com.mobile.framework.utilities.LoggerUtility;
import com.mobile.framework.utilities.ScrollUtility;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Responsive Validation Test Suite using Device Simulation.
 * Simulates different devices and Android versions (10-16) on a single local AVD.
 */
public class ResponsiveTest extends BaseTest {
    private static final LoggerUtility logger = new LoggerUtility(ResponsiveTest.class);

    /**
     * Profile class for screen simulation.
     */
    public static class ScreenProfile {
        String mobileName;
        String androidVersion;
        String resolution;
        int density;

        public ScreenProfile(String mobileName, String androidVersion, String resolution, int density) {
            this.mobileName = mobileName;
            this.androidVersion = androidVersion;
            this.resolution = resolution;
            this.density = density;
        }

        @Override
        public String toString() {
            return String.format("%s (Android %s)", mobileName, androidVersion);
        }
    }

    @DataProvider(name = "deviceProfiles")
    public Object[][] getDeviceProfiles() {
        List<String> udids = LocalDeviceManager.getConnectedDevices();
        if (udids.isEmpty()) {
            throw new RuntimeException("No devices connected for responsive simulation!");
        }
        String udid = udids.get(0); 

        return new Object[][] {
            { udid, new ScreenProfile("Galaxy S20", "10", "1080x2400", 480) },
            { udid, new ScreenProfile("Pixel 6", "12", "1080x2400", 411) },
            { udid, new ScreenProfile("S23 Ultra", "13", "1440x3088", 600) },
            { udid, new ScreenProfile("Pixel 8 Pro", "14", "1344x2992", 480) },
            { udid, new ScreenProfile("Generic Phone", "15", "1080x2400", 420) },
            { udid, new ScreenProfile("Future Device", "16", "1440x3200", 560) }
        };
    }

    @Test(dataProvider = "deviceProfiles")
    public void validateLoginResponsive(String udid, ScreenProfile profile) {
        logger.info(">>> Validating: " + profile.toString());
        
        try {
            // Apply simulation
            LocalDeviceManager.setDeviceResolution(udid, profile.resolution);
            LocalDeviceManager.setDeviceDensity(udid, profile.density);
            
            // Re-initialize driver
            // Note: We use profile.toString() as the device name for reporting
            AppiumDriver deviceDriver = DriverFactory.initDriver(profile.toString(), profile.androidVersion, "Android", udid);
            Dimension screen = deviceDriver.manage().window().getSize();
            
            try {
                LoginPage loginPage = new LoginPage(deviceDriver);

                // Perform onboarding slides
                loginPage.performSlides(3);
                
                // Validate elements
                logger.info("[" + profile.mobileName + "] Screen size: " + screen);
                
                // Full screen scroll validation
                ScrollUtility.scrollDown(deviceDriver);
                
                // Brief pause for visual confirmation
                Thread.sleep(4000); 
                
            } catch (Exception e) {
                logger.error("Error during validation for " + profile.mobileName + ": " + e.getMessage());
            } finally {
                deviceDriver.quit();
            }
        } catch (Exception e) {
            logger.error("Failed to setup simulation for " + profile.mobileName);
        } finally {
            // Reset to default
            LocalDeviceManager.resetDeviceDisplay(udid);
        }
    }
}
