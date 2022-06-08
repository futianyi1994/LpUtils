package com.leapmotor.lputils.utils;

import android.app.Application;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import androidx.core.content.FileProvider;

/**
 * good programmer.
 *
 * @date : 2022-03-11 10:45
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class LpUtilsFileProvider extends FileProvider {
    private static final String TAG = "LpUtilsFileProvider";

    private final ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            String lastPathSegment = uri.getLastPathSegment();
            if (lastPathSegment != null) {
                switch (lastPathSegment) {
                    case SettingsUtils.SCREEN_MODE:
                        LpUtils.refreshScreenThemeMode();
                        break;
                    default:
                        break;
                }
            } else {
                Log.e(TAG, "lastPathSegment is null : " + uri);
            }
        }
    };

    @Override
    public boolean onCreate() {
        LpUtils.init((Application) getContext().getApplicationContext());
        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor(SettingsUtils.SCREEN_MODE), true, observer);
        return true;
    }
}