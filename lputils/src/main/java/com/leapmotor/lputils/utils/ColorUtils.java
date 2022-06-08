package com.leapmotor.lputils.utils;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.leapmotor.lputils.R;
import com.leapmotor.lputils.annotation.ScreenModeType;

/**
 * good programmer.
 *
 * @date : 2022-06-08 19:48
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
class ColorUtils {
    @DrawableRes
    public static int getBgToastOrDialog() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.mipmap.bg_toast_night;
            case ScreenModeType.DAY:
                return R.mipmap.bg_toast_light;
            default:
                return R.mipmap.bg_toast_night;
        }
    }

    @ColorRes
    public static int getTextPrimaryColor() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.color.text_primary;
            case ScreenModeType.DAY:
                return R.color.text_primary_light;
            default:
                return R.color.text_primary;
        }
    }

    @ColorRes
    public static int getBtnTextHighlightColor() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.color.btn_text_highlight;
            case ScreenModeType.DAY:
                return R.color.btn_text_highlight_light;
            default:
                return R.color.btn_text_highlight;
        }
    }
}
