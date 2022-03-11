package com.leapmotor.lputils.utils;

import android.provider.Settings;

import com.leapmotor.lputils.annotation.ScreenModeType;
import com.leapmotor.lputils.annotation.TextSizeType;


/**
 * good programmer.
 *
 * @date : 2021-06-10 20:26
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class SettingsUtils {
    public static final String SCREEN_MODE = "C11_SREENMODE";
    public static final String TEXT_SIZE = "C11_SREENTEXTSIZE";

    @ScreenModeType
    public static int screenMode() {
        return Settings.Global.getInt(LpUtils.getApp().getContentResolver(), SCREEN_MODE, ScreenModeType.NIGHT);
    }

    @TextSizeType
    public static int textSize() {
        return Settings.Global.getInt(LpUtils.getApp().getContentResolver(), TEXT_SIZE, TextSizeType.SMALLL);
    }

}
