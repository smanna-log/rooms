package com.mobile.framework.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidBy;

/**
 * Dashboard Page class representing the home/dashboard screen of the VD App.
 * Elements sourced from visual comparison of Figma design vs actual app.
 *
 * Screen Elements:
 *  - App Bar: "VD App" title + Notification Bell
 *  - "Your Business at a Glance" section
 *  - Stat Cards: Business List, Total Views, Total Clicks
 *  - Promo Card: "Expand your presence across locations"
 *  - "Add More Branches" button
 *  - Quick Action cards: Boost Listing, Manage Ads
 *  - Bottom Navigation: HOME, Listings, Analytics, More
 *  - FAB (+) button
 */
public class DashboardPage extends BasePage {

    // ─── App Bar ──────────────────────────────────────────────────────────────
    @AndroidBy(accessibility = "notificationBell")
    private WebElement notificationBell;

    // ─── Business at a Glance Section ─────────────────────────────────────────
    @AndroidBy(accessibility = "businessAtAGlanceTitle")
    private WebElement businessAtAGlanceTitle;

    @AndroidBy(accessibility = "businessAtAGlanceSubtitle")
    private WebElement businessAtAGlanceSubtitle;

    // ─── Stat Cards ───────────────────────────────────────────────────────────
    @AndroidBy(accessibility = "statBusinessList")
    private WebElement statBusinessList;

    @AndroidBy(accessibility = "statTotalViews")
    private WebElement statTotalViews;

    @AndroidBy(accessibility = "statTotalClicks")
    private WebElement statTotalClicks;

    // ─── Promo Card ───────────────────────────────────────────────────────────
    @AndroidBy(accessibility = "promoCard")
    private WebElement promoCard;

    @AndroidBy(accessibility = "promoCardTitle")
    private WebElement promoCardTitle;

    @AndroidBy(accessibility = "addMoreBranchesButton")
    private WebElement addMoreBranchesButton;

    // ─── Quick Action ─────────────────────────────────────────────────────────
    @AndroidBy(accessibility = "quickActionSection")
    private WebElement quickActionSection;

    @AndroidBy(accessibility = "boostListingCard")
    private WebElement boostListingCard;

    @AndroidBy(accessibility = "manageAdsCard")
    private WebElement manageAdsCard;

    // ─── Bottom Navigation ────────────────────────────────────────────────────
    @AndroidBy(accessibility = "bottomNavHome")
    private WebElement bottomNavHome;

    @AndroidBy(accessibility = "bottomNavListings")
    private WebElement bottomNavListings;

    @AndroidBy(accessibility = "bottomNavAnalytics")
    private WebElement bottomNavAnalytics;

    @AndroidBy(accessibility = "bottomNavMore")
    private WebElement bottomNavMore;

    // ─── FAB ──────────────────────────────────────────────────────────────────
    @AndroidBy(accessibility = "fabButton")
    private WebElement fabButton;

    // ─── Constructor ──────────────────────────────────────────────────────────

    public DashboardPage(AppiumDriver driver) {
        super(driver);
    }

    // ─── Verification Methods ─────────────────────────────────────────────────

    /**
     * Check if the dashboard page is displayed.
     * Uses the "Your Business at a Glance" heading as the page anchor.
     */
    public boolean isDashboardDisplayed() {
        try {
            return waitForElementVisible(
                    By.xpath("//*[contains(@content-desc,'businessAtAGlanceTitle') or contains(@text,'Your Business at a Glance')]")
            ) != null;
        } catch (Exception e) {
            logger.error("Dashboard not displayed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if the notification bell is displayed.
     */
    public boolean isNotificationBellDisplayed() {
        try {
            return waitForElementVisible(AppiumBy.accessibilityId("notificationBell")) != null;
        } catch (Exception e) {
            logger.error("Notification bell not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if the Business at a Glance title is visible.
     */
    public boolean isBusinessAtAGlanceSectionVisible() {
        try {
            return waitForElementVisible(
                    By.xpath("//*[contains(@text,'Your Business at a Glance') or contains(@content-desc,'businessAtAGlanceTitle')]")
            ) != null;
        } catch (Exception e) {
            logger.error("Business at a Glance section not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if all three stat cards are visible.
     */
    public boolean areStatCardsDisplayed() {
        try {
            boolean business = waitForElementVisible(AppiumBy.accessibilityId("statBusinessList")) != null;
            boolean views   = waitForElementVisible(AppiumBy.accessibilityId("statTotalViews")) != null;
            boolean clicks  = waitForElementVisible(AppiumBy.accessibilityId("statTotalClicks")) != null;
            return business && views && clicks;
        } catch (Exception e) {
            logger.error("Stat cards not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get the numeric value text of a stat card by accessibility ID.
     * @param statAccessibilityId accessibility ID of the stat card
     * @return text content of the stat value
     */
    public String getStatValue(String statAccessibilityId) {
        try {
            WebElement el = waitForElementVisible(AppiumBy.accessibilityId(statAccessibilityId));
            return el != null ? el.getText() : "";
        } catch (Exception e) {
            logger.error("Could not get stat value for: " + statAccessibilityId);
            return "";
        }
    }

    /**
     * Check if the promo card is displayed.
     */
    public boolean isPromoCardDisplayed() {
        try {
            return waitForElementVisible(
                    By.xpath("//*[contains(@text,'Expand your presence') or contains(@content-desc,'promoCard')]")
            ) != null;
        } catch (Exception e) {
            logger.error("Promo card not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if the "Add More Branches" button is visible.
     */
    public boolean isAddMoreBranchesButtonDisplayed() {
        try {
            return waitForElementVisible(
                    By.xpath("//*[contains(@text,'Add More Branches') or contains(@content-desc,'addMoreBranchesButton')]")
            ) != null;
        } catch (Exception e) {
            logger.error("Add More Branches button not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if the Quick Action section is visible.
     */
    public boolean isQuickActionSectionVisible() {
        try {
            return waitForElementVisible(
                    By.xpath("//*[contains(@text,'Quick Action') or contains(@content-desc,'quickActionSection')]")
            ) != null;
        } catch (Exception e) {
            logger.error("Quick Action section not found: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if the bottom navigation bar is visible.
     */
    public boolean isBottomNavBarVisible() {
        try {
            return waitForElementVisible(
                    By.xpath("//*[contains(@text,'HOME') or contains(@content-desc,'bottomNavHome')]")
            ) != null;
        } catch (Exception e) {
            logger.error("Bottom nav bar not found: " + e.getMessage());
            return false;
        }
    }

    // ─── Actions ──────────────────────────────────────────────────────────────

    /**
     * Tap the notification bell icon.
     */
    public DashboardPage tapNotificationBell() {
        tapElement(notificationBell);
        logger.info("Tapped notification bell");
        return this;
    }

    /**
     * Tap the "Add More Branches" button.
     */
    public DashboardPage tapAddMoreBranches() {
        tapElement(addMoreBranchesButton);
        logger.info("Tapped Add More Branches button");
        return this;
    }

    /**
     * Tap a bottom navigation item by its accessibility ID.
     * @param accessibilityId one of: bottomNavHome, bottomNavListings, bottomNavAnalytics, bottomNavMore
     */
    public DashboardPage tapBottomNavItem(String accessibilityId) {
        tapElement(AppiumBy.accessibilityId(accessibilityId));
        logger.info("Tapped bottom nav: " + accessibilityId);
        return this;
    }

    /**
     * Tap the FAB (+) button.
     */
    public DashboardPage tapFabButton() {
        tapElement(fabButton);
        logger.info("Tapped FAB button");
        return this;
    }

    /**
     * Tap the Boost Listing quick action card.
     */
    public DashboardPage tapBoostListing() {
        tapElement(boostListingCard);
        logger.info("Tapped Boost Listing card");
        return this;
    }

    /**
     * Tap the Manage Ads quick action card.
     */
    public DashboardPage tapManageAds() {
        tapElement(manageAdsCard);
        logger.info("Tapped Manage Ads card");
        return this;
    }
}
