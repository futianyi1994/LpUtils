package com.leapmotor.lputils.utils;

import android.app.Activity;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

/**
 * good programmer.
 *
 * @date : 2020-12-23 14:56
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class DisplayUtils {
    private static final String TAG = "DisplayUtils";

    /**
     * get display[] by context
     *
     * @param context The content
     * @return Display[0]: primary diaplay;Display[1]: secondary diaplay
     */
    public static Display[] getDisplay(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();
        for (int i = 0; i < displays.length; i++) {
            Log.i(TAG, String.format(Locale.getDefault(), "find display[%d] : %s", i, displays[i].toString()));
        }
        return displays;
    }

    /**
     * get display by specify index
     *
     * @param context      The content
     * @param displayIndex specify index
     * @return display for specify index
     */
    @Nullable
    public static Display getDisplay(Context context, int displayIndex) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();
        if (displays.length > displayIndex) {
            Log.i(TAG, "find this diaplay  : " + displays[displayIndex].toString());
            return displays[displayIndex];
        }
        return null;
    }

    /**
     * get second display by context
     *
     * @param context The content
     * @return The second display
     */
    @Nullable
    public static Display getSecondaryDisplay(Context context) {
        Display[] displays = getDisplay(context);
        return displays.length > 1 ? displays[1] : null;
    }

    /**
     * get displayId by activity
     *
     * @param activity The activity
     * @return The displayId
     */
    public static int getDisplayId(@NonNull Activity activity) {
        Display display = activity.getWindow().getDecorView().getDisplay();
        int displayId = display != null ? display.getDisplayId() : Display.DEFAULT_DISPLAY;
        Log.i(TAG, "getDisplayId : " + displayId);
        return displayId;
    }

    /**
     * get displayId by view
     *
     * @param view The content
     * @return The displayId
     */
    public static int getDisplayId(@NonNull View view) {
        Display display = view.getDisplay();
        int displayId = display != null ? display.getDisplayId() : Display.DEFAULT_DISPLAY;
        Log.i(TAG, "getDisplayId : " + displayId);
        return displayId;
    }

    /**
     * judge current display is default by activity
     *
     * @param activity The activity
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean curDisplayIsDefault(@NonNull Activity activity) {
        return curDisplayIsDefault(getDisplayId(activity));
    }

    /**
     * judge current display is default by view
     *
     * @param view The view
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean curDisplayIsDefault(@NonNull View view) {
        return curDisplayIsDefault(getDisplayId(view));
    }

    /**
     * judge current display is default by specify displayId
     *
     * @param curDisplayId specify displayId
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean curDisplayIsDefault(int curDisplayId) {
        return curDisplayId == Display.DEFAULT_DISPLAY;
    }
}