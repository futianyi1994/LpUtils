package com.leapmotor.lputils.utils;

import android.app.Activity;
import android.os.Build;
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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * good programmer.
 *
 * @date : 2022/6/7 15:39
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class DialogUtils {
    public static final int DIALOG_WIDTH = 720;
    public static final int TXT_TITLE_MAX_WIDTH = 560;
    public static final int TXT_TITLE_MAX_HEIGHT_DISPLAY0 = 720;
    public static final int TXT_TITLE_MAX_HEIGHT_DISPLAY1 = 560;
    public static final int TXT_TITLE_HEAD_HEIGHT = 95;
    public static final int TXT_BG_SHADOW_PADING = 40;
    private static final String TAG = "DialogUtils";
    private static final Map<Integer, WindowManager> WINDOW_MANAGER_MAP = new HashMap<>();
    private static final Map<Integer, View> VIEW_MANAGER_MAP = new HashMap<>();
    private static final Map<Integer, ThreadUtils.Task<Integer>> TASK_MAP = new HashMap<>();
    private static final Map<Integer, OnClickListener> LISTENER_MAP = new HashMap<>();


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
        show(activity, displayId, null, title, leftTitle, rightTitle, ThemeUtils.getBgToastOrDialog(), ThemeUtils.getTextPrimaryColor(), null, onClickListener);
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
        show(activity, displayId, headTitle, title, leftTitle, rightTitle, ThemeUtils.getBgToastOrDialog(), ThemeUtils.getTextPrimaryColor(), null, onClickListener);
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
        layoutParams.type = Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
        show(null, displayId, headTitle, title, leftTitle, rightTitle, ThemeUtils.getBgToastOrDialog(), ThemeUtils.getTextPrimaryColor(), layoutParams, onClickListener);
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
                new ShadowDialog(activity)
                        .setTitleText(headTitle == null ? null : headTitle.toString())
                        .setContentText(title.toString())
                        .setConfirmText(leftTitle == null ? null : leftTitle.toString())
                        .setCancelText(rightTitle == null ? null : rightTitle.toString())
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