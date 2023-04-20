package com.leapmotor.play.annotation;


import androidx.annotation.IntDef;

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
@IntDef({PrivilegeType.FREE, PrivilegeType.VIP, PrivilegeType.BUY, PrivilegeType.SAMPLE, PrivilegeType.UNPRIVILEGED})
@Retention(RetentionPolicy.SOURCE)
public @interface PrivilegeType {
    /**
     * 免费
     */
    int FREE = 0;
    /**
     * vip
     */
    int VIP = 1;
    /**
     * 付费
     */
    int BUY = 2;
    /**
     * 试听
     */
    int SAMPLE = 3;
    /**
     * 无版权(无版权暂时只有酷狗用到，因为喜马拉雅能获取到的专辑中不会出现无版权声音，特例：收藏的专辑会出现下架的)
     */
    int UNPRIVILEGED = 4;

}
