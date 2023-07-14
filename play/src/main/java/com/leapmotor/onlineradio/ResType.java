package com.leapmotor.onlineradio;

/**
 * good programmer.
 *
 * @date : 2023-07-13 19:55
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public final class ResType {
    /**
     * 无类型
     */
    public static final int TYPE_INVALID = -1;
    /**
     * 专辑
     */
    public static final int TYPE_ALBUM = 0;
    /**
     * 单曲
     */
    public static final int TYPE_AUDIO = 1;
    /**
     * 专题包
     */
    public static final int TYPE_TOPIC = 2;
    /**
     * 电台
     */
    public static final int TYPE_RADIO = 3;
    /**
     * URL
     */
    public static final int TYPE_URL = 4;
    /**
     * 直播
     */
    public static final int TYPE_LIVE = 5;
    /**
     * 新闻碎片
     */
    public static final int TYPE_NEWS = 6;

    /**
     * 在线广播
     */
    public static final int TYPE_BROADCAST = 11;
    /**
     * 听电视
     */
    public static final int TYPE_TV = 12;
    /**
     * 专题
     */
    public static final int TYPE_FEATURE = 13;
    /**
     * 综合
     */
    public static final int TYPE_ALL = 30;
    /**
     * 搜索结果
     */
    public static final int TYPE_SEARCH = 106;
    /**
     * 分类
     */
    public static final int TYPE_CATEGORY = 109;
    /**
     * 栏目
     */
    public static final int TYPE_COLUMN = 110;

    /**
     * 排行榜
     */
    public static final int TYPE_RANK_LIST = 201;
    /**
     * 电台列表
     */
    public static final int TYPE_RADIO_LIST = 202;
    /**
     * 直播列表
     */
    public static final int TYPE_LIVE_LIST = 203;
    /**
     * 专题列表
     */
    public static final int TYPE_TOPIC_LIST = 204;
    /**
     * 传统电台列表
     */
    public static final int TYPE_BROADCAST_LIST = 205;

    /**
     * kradio电台功能入口
     */
    public static final int FUNCTION_KRADIO_RADIO = 301;
    /**
     * kradio音乐功能入口
     */
    public static final int FUNCTION_KRADIO_MUSIC = 302;

    /**
     * QQ音乐
     */
    public static final int TYPE_QQ_MUSIC = 100;
    /**
     * QQ音乐歌单
     */
    public static final int TYPE_SONGMENU = 1001;
    /**
     * QQ音乐排行榜
     */
    public static final int TYPE_SONG_CHARTS = 1002;
    /**
     * QQ 音乐电台 标签电台，相当于QQ音乐文档里的分类电台
     */
    public static final int TYPE_MUSIC_RADIO_LABEL = 1003;
    /**
     * QQ 音乐电台 场景电台,相当于QQ音乐文档里的公共电台
     */
    public static final int TYPE_MUSIC_RADIO_SCENE = 1004;
    /**
     * 语音搜索QQ音乐资源
     */
    public static final int TYPE_MUSIC_VOICE_RESULT = 1005;
    /**
     * 私人fm
     */
    public static final int MUSIC_MINE_PRIVATE_FM = 1010;
    /**
     * 每日30首
     */
    public static final int MUSIC_MINE_DAY = 1011;
    /**
     * 我的红心/我喜欢的
     */
    public static final int MUSIC_MINE_LIKE = 1012;
    /**
     * 我的收藏
     */
    public static final int MUSIC_MINE_FAVOURITE = 1013;
    /**
     * 音乐最近收听
     */
    public static final int MUSIC_MINE_HISTORY = 1014;
    /**
     * 电台收听历史
     */
    public static final int RADIO_MINE_HISTORY = 1015;
}