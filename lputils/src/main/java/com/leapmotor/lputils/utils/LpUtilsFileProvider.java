package com.leapmotor.lputils.utils;

import android.app.Application;

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

    @Override
    public boolean onCreate() {
        LpUtils.init((Application) getContext().getApplicationContext());
        return true;
    }
}