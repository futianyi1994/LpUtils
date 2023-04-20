package com.leapmotor.xmly.annotation;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * good programmer.
 *
 * @date : 2020-05-28 9:38
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
@StringDef({SortType.ASC, SortType.DESC, SortType.TIME_ASC, SortType.TIME_DESC})
@Retention(RetentionPolicy.SOURCE)
public @interface SortType {
    /**
     * 喜马拉雅正序
     */
    String ASC = "asc";
    /**
     * 喜马拉雅倒叙
     */
    String DESC = "desc";
    /**
     * 时间正序
     */
    @Deprecated
    String TIME_ASC = "time_asc";
    /**
     * 时间倒序
     */
    @Deprecated
    String TIME_DESC = "time_desc";
}
