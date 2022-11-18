package com.leapmotor.utils;

import android.os.Environment;

import java.io.File;

/**
 * good programmer.
 *
 * @date : 2020-09-05 13:32
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public interface Constant {
    /**
     * Crash文件夹名称
     */
    String CRASH_NAME = "crash";
    /**
     * Crash文件夹路径
     */
    String CRASH_PATH = Environment.getExternalStorageDirectory().getPath() + File.separatorChar + CRASH_NAME + File.separatorChar;
    /**
     * Log文件夹名称
     */
    String LOG_NAME = "log";
    /**
     * Log文件夹路径
     */
    String LOG_PATH = Environment.getExternalStorageDirectory().getPath() + File.separatorChar + LOG_NAME + File.separatorChar;

}
