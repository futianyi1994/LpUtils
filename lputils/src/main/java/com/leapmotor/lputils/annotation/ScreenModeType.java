package com.leapmotor.lputils.annotation;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * good programmer.
 *
 * @date : 2021/6/11 14:13
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
@IntDef({ScreenModeType.NIGHT, ScreenModeType.DAY})
@Retention(RetentionPolicy.SOURCE)
public @interface ScreenModeType {
    /**
     * 黑夜
     */
    int NIGHT = 0;
    /**
     * 白天
     */
    int DAY = 1;

}
