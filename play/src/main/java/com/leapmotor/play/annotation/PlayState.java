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
@IntDef({PlayState.NONE, PlayState.STATE_PAUSE, PlayState.STATE_PLAY, PlayState.STATE_STOP})
@Retention(RetentionPolicy.SOURCE)
public @interface PlayState {
    int NONE = -1;
    /**
     * 暂停：该值不可改变因为作为广播值发送给桌面
     */
    int STATE_PAUSE = 0;
    /**
     * 播放：该值不可改变因为作为广播值发送给桌面
     */
    int STATE_PLAY = 1;
    int STATE_STOP = 2;
}
