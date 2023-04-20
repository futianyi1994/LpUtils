package com.leapmotor.play.annotation;

import android.support.v4.media.session.PlaybackStateCompat;

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
@IntDef({PlayMode.NONE, PlayMode.RANDOM, PlayMode.ONE, PlayMode.CYCLE})
@Retention(RetentionPolicy.SOURCE)
public @interface PlayMode {
    int NONE = PlaybackStateCompat.REPEAT_MODE_NONE;
    /**
     * 单曲播放
     */
    int ONE = PlaybackStateCompat.REPEAT_MODE_ONE;
    /**
     * 循环播放
     */
    int CYCLE = PlaybackStateCompat.REPEAT_MODE_ALL;
    /**
     * 随机播放
     */
    int RANDOM = PlaybackStateCompat.REPEAT_MODE_GROUP;

}
