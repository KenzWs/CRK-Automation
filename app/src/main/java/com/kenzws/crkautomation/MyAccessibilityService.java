package com.kenzws.crkautomation;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.os.Handler;

public class MyAccessibilityService extends AccessibilityService {

    private Handler handler = new Handler();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            automateGame();
        }
    }

    private void automateGame() {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return;

        // Find and tap "Place Blocks" button
        clickButtonByText(rootNode, "Place Blocks");

        // Find and tap "Merge" button
        clickButtonByText(rootNode, "Merge");

        // TODO: Add logic to detect level 4 blocks and restart
    }

    private void clickButtonByText(AccessibilityNodeInfo rootNode, String buttonText) {
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            AccessibilityNodeInfo child = rootNode.getChild(i);
            if (child != null && child.getText() != null && child.getText().toString().equals(buttonText)) {
                child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            clickButtonByText(child, buttonText); // Recursive search
        }
    }

    @Override
    public void onInterrupt() {
    }
}
