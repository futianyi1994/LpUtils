package com.leapmotor.lputils.utils;

import android.content.res.Resources;

/**
 * good programmer.
 *
 * @date : 2023-03-14 10:19
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class BarUtils {
    private BarUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Return the status bar's height.
     *
     * @return the status bar's height
     */
    public static int getStatusBarHeight() {
        Resources resources = LpUtils.getApp().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * Return the navigation bar's height.
     *
     * @return the navigation bar's height
     */
    public static int getNavBarHeight() {
        Resources res = LpUtils.getApp().getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }
}
