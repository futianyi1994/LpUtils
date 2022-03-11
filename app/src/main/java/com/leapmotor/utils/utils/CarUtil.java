package com.leapmotor.utils.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


/**
 * good programmer.
 *
 * @date : 2020/11/12 10:19
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class CarUtil {
    /**
     * 启动指定屏幕
     *
     * @param context
     * @param intent
     * @param launchDisplayId 这里一直display0是第一块屏；display1是第二块屏
     */
    public static void startDisplay(Context context, Intent intent, int launchDisplayId) {
        startDisplay(context, intent, launchDisplayId, false);
    }

    public static void startDisplay(Context context, Intent intent, int launchDisplayId, boolean withFlag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ActivityOptions options = ActivityOptions.makeBasic();
            options.setLaunchDisplayId(launchDisplayId);
            intent.setAction(Intent.ACTION_MAIN);
            if (withFlag) {
                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ActivityUtils.startActivity(context, intent, options.toBundle());
        }
    }
}