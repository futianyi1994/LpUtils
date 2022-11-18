package com.leapmotor.lputils.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.List;

/**
 * good programmer.
 *
 * @date : 2022-11-18 15:35
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public final class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * Return whether the app is installed.
     *
     * @param pkgName The name of the package.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppInstalled(final String pkgName) {
        if (UtilsBridge.isSpace(pkgName)) {
            return false;
        }
        PackageManager pm = LpUtils.getApp().getPackageManager();
        try {
            return pm.getApplicationInfo(pkgName, 0).enabled;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Return whether it is a debug application.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppDebug() {
        return isAppDebug(LpUtils.getApp().getPackageName());
    }

    /**
     * Return whether it is a debug application.
     *
     * @param packageName The name of the package.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppDebug(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return false;
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return (ai.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return whether it is a system application.
     *
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppSystem() {
        return isAppSystem(LpUtils.getApp().getPackageName());
    }

    /**
     * Return whether it is a system application.
     *
     * @param packageName The name of the package.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppSystem(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return false;
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            return (ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return whether application is running.
     *
     * @param pkgName The name of the package.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isAppRunning(final String pkgName) {
        if (UtilsBridge.isSpace(pkgName)) {
            return false;
        }
        ActivityManager am = (ActivityManager) LpUtils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(Integer.MAX_VALUE);
            if (taskInfo != null && taskInfo.size() > 0) {
                for (ActivityManager.RunningTaskInfo aInfo : taskInfo) {
                    if (aInfo.baseActivity != null) {
                        if (pkgName.equals(aInfo.baseActivity.getPackageName())) {
                            return true;
                        }
                    }
                }
            }
            List<ActivityManager.RunningServiceInfo> serviceInfo = am.getRunningServices(Integer.MAX_VALUE);
            if (serviceInfo != null && serviceInfo.size() > 0) {
                for (ActivityManager.RunningServiceInfo aInfo : serviceInfo) {
                    if (pkgName.equals(aInfo.service.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return the application's icon.
     *
     * @return the application's icon
     */
    @Nullable
    public static Drawable getAppIcon() {
        return getAppIcon(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's icon.
     *
     * @param packageName The name of the package.
     * @return the application's icon
     */
    @Nullable
    public static Drawable getAppIcon(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return null;
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return the application's icon resource identifier.
     *
     * @return the application's icon resource identifier
     */
    public static int getAppIconId() {
        return getAppIconId(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's icon resource identifier.
     *
     * @param packageName The name of the package.
     * @return the application's icon resource identifier
     */
    public static int getAppIconId(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return 0;
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? 0 : pi.applicationInfo.icon;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Return the application's package name.
     *
     * @return the application's package name
     */
    @NonNull
    public static String getAppPackageName() {
        return LpUtils.getApp().getPackageName();
    }

    /**
     * Return the application's name.
     *
     * @return the application's name
     */
    @NonNull
    public static String getAppName() {
        return getAppName(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's name.
     *
     * @param packageName The name of the package.
     * @return the application's name
     */
    @NonNull
    public static String getAppName(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return "";
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? "" : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Return the application's path.
     *
     * @return the application's path
     */
    @NonNull
    public static String getAppPath() {
        return getAppPath(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's path.
     *
     * @param packageName The name of the package.
     * @return the application's path
     */
    @NonNull
    public static String getAppPath(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return "";
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? "" : pi.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Return the application's version name.
     *
     * @return the application's version name
     */
    @NonNull
    public static String getAppVersionName() {
        return getAppVersionName(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's version name.
     *
     * @param packageName The name of the package.
     * @return the application's version name
     */
    @NonNull
    public static String getAppVersionName(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return "";
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? "" : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Return the application's version code.
     *
     * @return the application's version code
     */
    public static int getAppVersionCode() {
        return getAppVersionCode(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's version code.
     *
     * @param packageName The name of the package.
     * @return the application's version code
     */
    public static int getAppVersionCode(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return -1;
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? -1 : pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Return the application's signature.
     *
     * @return the application's signature
     */
    @Nullable
    public static Signature[] getAppSignatures() {
        return getAppSignatures(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's signature.
     *
     * @param packageName The name of the package.
     * @return the application's signature
     */
    @Nullable
    public static Signature[] getAppSignatures(final String packageName) {
        if (UtilsBridge.isSpace(packageName)) {
            return null;
        }
        try {
            PackageManager pm = LpUtils.getApp().getPackageManager();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES);
                if (pi == null) {
                    return null;
                }

                SigningInfo signingInfo = pi.signingInfo;
                if (signingInfo.hasMultipleSigners()) {
                    return signingInfo.getApkContentsSigners();
                } else {
                    return signingInfo.getSigningCertificateHistory();
                }
            } else {
                PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                if (pi == null) {
                    return null;
                }

                return pi.signatures;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Return the application's signature.
     *
     * @param file The file.
     * @return the application's signature
     */
    @Nullable
    public static Signature[] getAppSignatures(final File file) {
        if (file == null) {
            return null;
        }
        PackageManager pm = LpUtils.getApp().getPackageManager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            PackageInfo pi = pm.getPackageArchiveInfo(file.getAbsolutePath(), PackageManager.GET_SIGNING_CERTIFICATES);
            if (pi == null) {
                return null;
            }

            SigningInfo signingInfo = pi.signingInfo;
            if (signingInfo.hasMultipleSigners()) {
                return signingInfo.getApkContentsSigners();
            } else {
                return signingInfo.getSigningCertificateHistory();
            }
        } else {
            PackageInfo pi = pm.getPackageArchiveInfo(file.getAbsolutePath(), PackageManager.GET_SIGNATURES);
            if (pi == null) {
                return null;
            }

            return pi.signatures;
        }
    }

    /**
     * Return the application's user-ID.
     *
     * @return the application's signature for MD5 value
     */
    public static int getAppUid() {
        return getAppUid(LpUtils.getApp().getPackageName());
    }

    /**
     * Return the application's user-ID.
     *
     * @param pkgName The name of the package.
     * @return the application's signature for MD5 value
     */
    public static int getAppUid(String pkgName) {
        try {
            return LpUtils.getApp().getPackageManager().getApplicationInfo(pkgName, 0).uid;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}