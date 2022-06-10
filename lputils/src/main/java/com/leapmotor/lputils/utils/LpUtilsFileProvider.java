package com.leapmotor.lputils.utils;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * good programmer.
 *
 * @date : 2022-03-11 10:45
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class LpUtilsFileProvider extends ContentProvider {
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

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}