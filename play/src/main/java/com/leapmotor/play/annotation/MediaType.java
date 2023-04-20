package com.leapmotor.play.annotation;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * good programmer.
 *
 * @date : 2020/6/4 10:45
 * @author: futia
 * @email : futianyi1994@126.com
 * @description : 多媒体音源的类型
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        MediaType.TYPE_CURRENT,
        MediaType.TYPE_ONLINE,
        MediaType.TYPE_ULTIMATETV,
        MediaType.TYPE_LOCAL,
        MediaType.TYPE_BLUETOOTH,
        MediaType.TYPE_XMLY,
        MediaType.TYPE_FM,
        MediaType.TYPE_USB,
        MediaType.TYPE_WANG_YI_YUN,
        MediaType.TYPE_LS_KTV,
        MediaType.TYPE_ULTIMATE_MV,
        MediaType.TYPE_ONLINE_RADIO,
        MediaType.TYPE_YUNTING_RADIO,
        MediaType.TYPE_LP_RADIO,
        MediaType.TYPE_MIGU,
        MediaType.TYPE_CAR_LINK,
        MediaType.TYPE_FANDENG_READ,
        MediaType.TYPE_HICAR_MUSIC
})
public @interface MediaType {
    /**
     * 默认类型
     */
    int TYPE_CURRENT = -1;
    /**
     * 在线音乐
     */
    @Deprecated
    int TYPE_ONLINE = 6;
    /**
     * 酷狗SDK
     */
    int TYPE_ULTIMATETV = 0;
    /**
     * 本地音乐
     */
    @Deprecated
    int TYPE_LOCAL = 1;
    /**
     * 蓝牙音乐
     */
    int TYPE_BLUETOOTH = 2;
    /**
     * 喜马拉雅
     */
    int TYPE_XMLY = 3;
    /**
     * FM收音机
     */
    int TYPE_FM = 4;
    /**
     * U盘音乐
     */
    int TYPE_USB = 5;
    /**
     * 网易云（三方实现，这里只做占位用）
     */
    @Deprecated
    int TYPE_WANG_YI_YUN = 7;
    /**
     * 雷石KTV（三方实现，这里只做占位用）
     */
    @Deprecated
    int TYPE_LS_KTV = 8;
    /**
     * 酷狗Mv
     */
    int TYPE_ULTIMATE_MV = 9;
    /**
     * 在线广播（云听）
     */
    int TYPE_ONLINE_RADIO = 10;
    /**
     * 公版云听（三方实现，这里只做占位用）
     */
    @Deprecated
    int TYPE_YUNTING_RADIO = 11;
    /**
     * 零跑电台
     */
    int TYPE_LP_RADIO = 12;
    /**
     * 咪咕音乐（三方实现，这里只做占位用）
     */
    @Deprecated
    int TYPE_MIGU = 13;
    /**
     * Car-Link（内部应用实现，这里只做占位用）
     */
    @Deprecated
    int TYPE_CAR_LINK = 14;
    /**
     * 樊登读书（三方实现，这里只做占位用）
     */
    @Deprecated
    int TYPE_FANDENG_READ = 15;
    /**
     * HiCar音乐（内部应用实现，这里只做占位用）
     */
    @Deprecated
    int TYPE_HICAR_MUSIC = 16;

}
