package com.leapmotor.lputils.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leapmotor.lputils.widget.ShadowDialog;

import java.util.Locale;

/**
 * good programmer.
 *
 * @date : 2022/6/7 15:39
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class DialogUtils {
    private static final String TAG = "DialogUtils";

    public static WinDialogUtils.Config getConfig() {
        return WinDialogUtils.getConfig();
    }

    public static void clearUsedConfig() {
        WinDialogUtils.clearUsedConfig();
    }

    /**
     * Show the dialog on default display.
     *
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     */
    public static void show(CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(null, Display.DEFAULT_DISPLAY, title, leftTitle, rightTitle, onClickListener);
    }

    /**
     * Show the dialog according to the context.
     *
     * @param activity        The context.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     * @deprecated use {@link  com.leapmotor.lputils.widget.ShadowDialog}
     */
    @Deprecated
    public static void show(@Nullable Activity activity, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(activity, Display.INVALID_DISPLAY, title, leftTitle, rightTitle, onClickListener);
    }

    /**
     * Show the dialog according to the display.
     *
     * @param displayId       The displayId.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     */
    public static void show(int displayId, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(null, displayId, title, leftTitle, rightTitle, onClickListener);
    }

    /**
     * Show the dialog according to the display or context.
     *
     * @param activity        The context.
     * @param displayId       The displayId.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     * @deprecated use {@link  com.leapmotor.lputils.widget.ShadowDialog}
     */
    @Deprecated
    private static void show(@Nullable Activity activity, int displayId, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(activity, displayId, null, title, leftTitle, rightTitle, ThemeUtils.getbgPopup(), ThemeUtils.getTextPrimaryColor(), null, onClickListener);
    }

    /**
     * Show the dialog according to the context.
     *
     * @param activity        The context.
     * @param headTitle       The head title text.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     * @deprecated use {@link  com.leapmotor.lputils.widget.ShadowDialog}
     */
    @Deprecated
    public static void show(@Nullable Activity activity, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(activity, Display.INVALID_DISPLAY, headTitle, title, leftTitle, rightTitle, onClickListener);
    }

    /**
     * Show the dialog according to the display.
     *
     * @param displayId       The displayId.
     * @param headTitle       The head title text.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     */
    public static void show(int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(null, displayId, headTitle, title, leftTitle, rightTitle, onClickListener);
    }

    /**
     * Show the dialog according to the display or context.
     *
     * @param activity        The context.
     * @param displayId       The displayId.
     * @param headTitle       The head title text.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     * @deprecated use {@link  com.leapmotor.lputils.widget.ShadowDialog}
     */
    @Deprecated
    private static void show(@Nullable Activity activity, int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(activity, displayId, headTitle, title, leftTitle, rightTitle, ThemeUtils.getbgPopup(), ThemeUtils.getTextPrimaryColor(), null, onClickListener);
    }

    /**
     * Show the full screen dialog according to the display.
     *
     * @param displayId       The displayId.
     * @param headTitle       The head title text.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     */
    public static void showFullScreen(int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        WindowManager.LayoutParams layoutParams = WinDialogUtils.getDefaultLayoutParams(displayId);
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.type = getConfig().getWindowType();
        show(null, displayId, headTitle, title, leftTitle, rightTitle, ThemeUtils.getbgPopup(), ThemeUtils.getTextPrimaryColor(), layoutParams, onClickListener);
    }

    /**
     * Show the dialog according to the display or context.
     *
     * @param activity        The context.
     * @param displayId       The displayId.
     * @param headTitle       The head title text.
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param bgResid         Background resource id.
     * @param textColorRes    The text color resource.
     * @param onClickListener The callback that will run.
     * @deprecated use {@link  com.leapmotor.lputils.widget.ShadowDialog}
     */
    @Deprecated
    public static void show(@Nullable Activity activity, int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @DrawableRes int bgResid, @ColorRes int textColorRes, @Nullable WindowManager.LayoutParams layoutParams, @Nullable OnClickListener onClickListener) {
        if (displayId != Display.INVALID_DISPLAY) {
            WinDialogUtils.show(activity, displayId, headTitle, title, leftTitle, rightTitle, bgResid, textColorRes, layoutParams, onClickListener);
        } else {
            if (activity != null) {
                int currentDisplayId = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getDisplayId();
                WinDialogUtils.Config config = getConfig();
                boolean isFullScreen = currentDisplayId == Display.DEFAULT_DISPLAY ? config.isFullScreen() : config.isFullScreenVice();
                new ShadowDialog(activity)
                        .setTitleText(headTitle == null ? null : headTitle.toString())
                        .setContentText(title.toString())
                        .setConfirmText(leftTitle == null ? null : leftTitle.toString())
                        .setCancelText(rightTitle == null ? null : rightTitle.toString())
                        .setFullScreen(isFullScreen)
                        .setImmersion(config.isImmersion())
                        .setBgResid(config.getBgResid())
                        .setTextColorRes(config.getTextColorRes())
                        .setLayoutParams(config.getLayoutParams())
                        .setxOffset(config.getxOffset())
                        .setyOffset(config.getyOffset())
                        .setGravity(config.getGravity())
                        .setConfirmClickListener((dialog, view) -> {
                            if (onClickListener != null) {
                                return onClickListener.onLeftClick(view);
                            }
                            return false;
                        })
                        .setCancelClickListener((dialog, view) -> {
                            if (onClickListener != null) {
                                return onClickListener.onRightClick(view);
                            }
                            return false;
                        })
                        .show();
            } else {
                Log.e(TAG, String.format(Locale.getDefault(), "showOnDisplay error : context is null, displayId can't be %d !", Display.INVALID_DISPLAY));
            }
        }
    }

    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     */
    public interface OnClickListener {

        /**
         * Called when a left view has been clicked.
         *
         * @param v The view that was clicked.
         * @return True is click dismmss, false otherwise.
         */
        boolean onLeftClick(View v);

        /**
         * Called when a right view has been clicked.
         *
         * @param v The view that was clicked.
         * @return True is click dismmss, false otherwise.
         */
        default boolean onRightClick(View v) {
            return true;
        }
    }
}