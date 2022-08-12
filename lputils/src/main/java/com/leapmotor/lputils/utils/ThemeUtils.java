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
public class ThemeUtils {

    @DrawableRes
    public static int getRadiusMaskPopup() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.drawable.mask_popup_night_radius40;
            case ScreenModeType.DAY:
                return R.drawable.mask_popup_light_radius40;
            default:
                return R.drawable.mask_popup_night_radius40;
        }
    }

    @DrawableRes
    public static int getMaskPopup() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.color.mask_popup_night;
            case ScreenModeType.DAY:
                return R.color.mask_popup_light;
            default:
                return R.color.mask_popup_night;
        }
    }

    @DrawableRes
    public static int getbgPopup() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.mipmap.popup_bg_night;
            case ScreenModeType.DAY:
                return R.mipmap.popup_bg_light;
            default:
                return R.mipmap.popup_bg_night;
        }
    }

    @DrawableRes
    public static int getBgToast() {
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
                return R.color.text_primary_night;
            case ScreenModeType.DAY:
                return R.color.text_primary_light;
            default:
                return R.color.text_primary_night;
        }
    }

    @ColorRes
    public static int getBtnTextHighlightColor() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.color.btn_text_highlight_night;
            case ScreenModeType.DAY:
                return R.color.btn_text_highlight_light;
            default:
                return R.color.btn_text_highlight_night;
        }
    }

    @ColorRes
    public static int getLineColor() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.color.line_night;
            case ScreenModeType.DAY:
                return R.color.line_light;
            default:
                return R.color.line_night;
        }
    }
}
