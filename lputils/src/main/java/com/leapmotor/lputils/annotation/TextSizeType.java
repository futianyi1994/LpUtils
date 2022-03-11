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
@IntDef({TextSizeType.SMALLL, TextSizeType.BIG})
@Retention(RetentionPolicy.SOURCE)
public @interface TextSizeType {
    /**
     * 小字体
     */
    int SMALLL = 0;
    /**
     * 大字体
     */
    int BIG = 1;

}
