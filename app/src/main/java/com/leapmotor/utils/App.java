package com.leapmotor.utils;


import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.leapmotor.lputils.utils.CrashUtils;
import com.leapmotor.utils.utils.TLog;

/**
 * good programmer.
 *
 * @date : 2022/11/18 17:27
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        CrashUtils.init(Constant.CRASH_PATH);
        TLog.init("Utils", Log.DEBUG, true);
    }
}
