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
@IntDef({AlbumType.SONG, AlbumType.RANK, AlbumType.DAILY, AlbumType.OTHER, AlbumType.LIKE, AlbumType.COLLECT, AlbumType.SELF, AlbumType.LEAPMOTOR_MUSIC, AlbumType.LEAPMOTOR_HOT, AlbumType.PLAY_LIST, AlbumType.BLUETOOTH, AlbumType.HISTORY, AlbumType.LP_RADIO_TIME_PROGRAM, AlbumType.KG_GUESS_LIKE})
@Retention(RetentionPolicy.SOURCE)
public @interface AlbumType {
    int OTHER = -100;
    /**
     * 歌单类型
     */
    int SONG = 1;
    /**
     * 榜单类型
     */
    int RANK = 2;
    /**
     * 每日推荐
     */
    int DAILY = 3;
    /**
     * 喜欢
     */
    int LIKE = 4;
    /**
     * 收藏歌单
     */
    int COLLECT = 5;
    /**
     * 自建歌单
     */
    int SELF = 6;
    /**
     * 零跑歌单
     */
    int LEAPMOTOR_MUSIC = 7;
    /**
     * 零跑排行版
     */
    int LEAPMOTOR_HOT = 8;
    /**
     * 传入的播放列表（如搜索出来的播放列表）
     */
    int PLAY_LIST = 9;
    /**
     * 蓝牙音乐
     */
    int BLUETOOTH = 10;
    /**
     * 历史记录
     */
    int HISTORY = 11;
    /**
     * 零跑电台时段节目
     */
    int LP_RADIO_TIME_PROGRAM = 12;
    /**
     * 酷狗音乐猜你喜欢
     */
    int KG_GUESS_LIKE = 13;

}
