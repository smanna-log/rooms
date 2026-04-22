package com.mobile.framework.utilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

/**
 * Responsive scroll utility using W3C Actions.
 */
public class ScrollUtility {
    private static final LoggerUtility logger = new LoggerUtility(ScrollUtility.class);

    /**
     * Scroll vertically from top to bottom.
     * @param driver Appium driver instance
     */
    public static void scrollDown(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.8);
        int endY = (int) (size.getHeight() * 0.2);

        swipe(driver, startX, startY, startX, endY);
    }

    /**
     * Perform a swipe gesture.
     */
    private static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 1);
        sequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
        
        logger.info("Swiped from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
    }
}
