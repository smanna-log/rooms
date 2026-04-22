package com.mobile.framework.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsive Validator to detect visual issues like clipping, overlaps, and out-of-bounds.
 */
public class ResponsiveValidator {

    /**
     * Report class for layout issues.
     */
    public static class LayoutIssue {
        public String type;
        public String description;
        public String elementName;

        public LayoutIssue(String type, String description, String elementName) {
            this.type = type;
            this.description = description;
            this.elementName = elementName;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s: %s", type, elementName, description);
        }
    }

    /**
     * Validates if an element is within the screen bounds.
     */
    public static List<LayoutIssue> validateBounds(WebElement element, String elementName, Dimension screen) {
        List<LayoutIssue> issues = new ArrayList<>();
        Rectangle rect = element.getRect();

        if (rect.x < 0 || rect.y < 0 || (rect.x + rect.width) > screen.width || (rect.y + rect.height) > screen.height) {
            issues.add(new LayoutIssue("OUT_OF_BOUNDS", 
                String.format("Element at (%d,%d) with size %dx%d is outside screen %dx%d", 
                rect.x, rect.y, rect.width, rect.height, screen.width, screen.height), 
                elementName));
        }
        return issues;
    }

    /**
     * Detects if two elements overlap.
     */
    public static List<LayoutIssue> validateOverlap(WebElement el1, String name1, WebElement el2, String name2) {
        List<LayoutIssue> issues = new ArrayList<>();
        Rectangle r1 = el1.getRect();
        Rectangle r2 = el2.getRect();

        // Check for overlap
        if (r1.x < r2.x + r2.width && r1.x + r1.width > r2.x &&
            r1.y < r2.y + r2.height && r1.y + r1.height > r2.y) {
            issues.add(new LayoutIssue("OVERLAP", 
                String.format("Elements %s and %s are overlapping", name1, name2), 
                name1 + " & " + name2));
        }
        return issues;
    }

    /**
     * Validates if an element is "clipped" by its parent or has improper scaling.
     * Note: This usually requires comparing element size vs container size if available.
     */
    public static List<LayoutIssue> validateClipping(WebElement element, String elementName) {
        List<LayoutIssue> issues = new ArrayList<>();
        Rectangle rect = element.getRect();
        
        // Simple heuristic: elements with 0 width or height are effectively hidden or clipped
        if (rect.width <= 0 || rect.height <= 0) {
            issues.add(new LayoutIssue("HIDDEN/CLIPPED", "Element has zero or negative dimension", elementName));
        }
        return issues;
    }
}
