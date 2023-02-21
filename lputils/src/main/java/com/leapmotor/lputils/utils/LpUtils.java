package com.leapmotor.lputils.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

/**
 * good programmer.
 *
 * @date : 2022-03-02 11:33
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class LpUtils {
    @SuppressLint("StaticFieldLeak")
    private static Application sApp;

    private LpUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Init utils.
     * <p>Init it in the class of UtilsFileProvider.</p>
     *
     * @param app application
     */
    public static void init(final Application app) {
        if (app == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        if (sApp == null) {
            sApp = app;
            return;
        }
        if (sApp.equals(app)) {
            return;
        }
        sApp = app;
    }

    /**
     * Return the Application object.
     * <p>Main process get app by UtilsFileProvider,
     * and other process get app by reflect.</p>
     *
     * @return the Application object
     */
    public static Application getApp() {
        if (sApp != null) {
            return sApp;
        }
        init(UtilsBridge.getApplicationByReflect());
        if (sApp == null) {
            throw new NullPointerException("reflect failed.");
        }
        return sApp;
    }

    /**
     * Refresh the dialog and toast layout theme.
     */
    public static void refreshScreenThemeMode() {
        WinDialogUtils.refresh();
        ToastUtils.refresh();
    }

    /**
     * adapter to full-screen mode, for dialog and toast.
     */
    public static void adapterFullScreen() {
        adapterFullScreen(false);
    }

    /**
     * adapter to full-screen mode, for dialog and toast.
     *
     * @param isImmersion is immersion.
     */
    public static void adapterFullScreen(boolean isImmersion) {
        WinDialogUtils
                .getConfig()
                .setFullScreen(true)
                .setImmersion(isImmersion)
                .create();
        ToastUtils
                .getConfig()
                .setFullScreen(true)
                .create();
    }
}
