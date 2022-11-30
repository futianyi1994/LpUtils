package com.leapmotor.utils.utils;

import android.util.Log;

import com.leapmotor.lputils.utils.LogUtils;
import com.leapmotor.utils.Constant;

/**
 * good programmer.
 *
 * @date : 2020/3/1 20:34
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :log里面不能出现\r，否则打印不出log,\r是字符串结束标志
 */
public class TLog {
    private static final String LOG_TAG = "UtilsTlog";
    private static boolean DEBUG = false;

    public TLog() {
    }

    public static void v(String log) {
        v(LOG_TAG, log);
    }

    public static void v(String tag, String log) {
        v(tag, log, null);
    }

    public static void v(String tag, Throwable throwable) {
        v(tag, "", throwable);
    }

    public static void v(String tag, String msg, Throwable throwable) {
        if (DEBUG) {
            if (throwable != null) {
                Log.v(addGloalTag(tag), msg, throwable);
            } else {
                LogUtils.vTag(addGloalTag(tag), msg);
            }
            LogUtils.file(LogUtils.V, tag, msg);
        }
    }

    public static void d(String log) {
        d(LOG_TAG, log);
    }

    public static void d(String tag, String log) {
        d(tag, log, null);
    }

    public static void d(String tag, Throwable throwable) {
        d(tag, "", throwable);
    }

    public static void d(String tag, String msg, Throwable throwable) {
        if (DEBUG) {
            if (throwable != null) {
                Log.d(addGloalTag(tag), msg, throwable);
            } else {
                LogUtils.dTag(addGloalTag(tag), msg);
            }
            LogUtils.file(LogUtils.D, tag, msg);
        }
    }

    public static void i(String log) {
        i(LOG_TAG, log);
    }

    public static void i(String tag, String log) {
        i(tag, log, null);
    }

    public static void i(String tag, Throwable throwable) {
        i(tag, "", throwable);
    }

    public static void i(String tag, String msg, Throwable throwable) {
        if (DEBUG) {
            if (throwable != null) {
                Log.i(addGloalTag(tag), msg, throwable);
            } else {
                LogUtils.iTag(addGloalTag(tag), msg);
            }
            LogUtils.file(LogUtils.I, tag, msg);
        }
    }

    public static void w(String log) {
        w(LOG_TAG, log);
    }

    public static void w(String tag, String log) {
        w(tag, log, null);
    }

    public static void w(String tag, Throwable throwable) {
        w(tag, "", throwable);
    }

    public static void w(String tag, String msg, Throwable throwable) {
        if (DEBUG) {
            if (throwable != null) {
                Log.w(addGloalTag(tag), msg, throwable);
            } else {
                LogUtils.wTag(addGloalTag(tag), msg);
            }
            LogUtils.file(LogUtils.W, tag, msg);
        }
    }

    public static void e(String log) {
        e(LOG_TAG, log);
    }

    public static void e(String tag, String log) {
        e(tag, log, null);
    }

    public static void e(String tag, Throwable throwable) {
        e(tag, "", throwable);
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (DEBUG) {
            if (throwable != null) {
                Log.e(addGloalTag(tag), msg, throwable);
            } else {
                LogUtils.eTag(addGloalTag(tag), msg);
            }
            LogUtils.file(LogUtils.E, tag, msg);
        }
    }

    private static String addGloalTag(String tag) {
        return LOG_TAG.concat(" - ").concat(tag);
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void init(String logerName, int fileFilter, boolean openLog) {
        DEBUG = openLog;
        LogUtils
                .getConfig()
                .setDir(Constant.LOG_PATH)
                //关闭头部日志
                .setLogHeadSwitch(false)
                //关闭日志边界
                .setBorderSwitch(false)
                //日志写入文件过滤器
                .setFileFilter(LogUtils.E)
                //日志的文件名前缀
                .setFilePrefix(logerName);
    }
}
