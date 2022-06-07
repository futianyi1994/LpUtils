package com.leapmotor.lputils.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.text.TextPaint;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.leapmotor.lputils.R;
import com.leapmotor.lputils.annotation.ScreenModeType;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * good programmer.
 *
 * @date : 2020-07-27 17:25
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ToastUtils {
    public static final int TXT_MAX_WIDTH = 800;
    public static final int TXT_BG_SHADOW_PADING = 40;
    public static final int TXT_PADING_START = 80, TXT_PADING_TOP = 80, TXT_PADING_END = 80, TXT_PADING_BOTTOM = 80;
    public static final int LENGTH_SHORT = 2000;
    public static final int LENGTH_LONG = 3500;
    private static final String TAG = "ToastUtils";
    private static final Map<Integer, WindowManager> WINDOW_MANAGER_MAP = new HashMap<>();
    private static final Map<Integer, View> VIEW_MANAGER_MAP = new HashMap<>();
    private static final Map<Integer, ThreadUtils.Task<Integer>> TASK_MAP = new HashMap<>();

    /**
     * Show the toast for a short period of time.
     *
     * @param msg The text.
     */
    public static void showShort(CharSequence msg) {
        show(null, Display.DEFAULT_DISPLAY, msg, TXT_MAX_WIDTH, false, false);
    }

    /**
     * Show the toast for a short period of time.
     *
     * @param msg     The text.
     * @param bgResid Background resource id.
     * @param yOffset Y offset.
     */
    public static void showShort(CharSequence msg, @DrawableRes int bgResid, @ColorRes int textColor, int yOffset) {
        show(null, Display.DEFAULT_DISPLAY, msg, TXT_MAX_WIDTH, false, bgResid, textColor, yOffset, false);
    }

    /**
     * Show the toast for a long period of time.
     *
     * @param msg The text.
     */
    public static void showLong(CharSequence msg) {
        show(null, Display.DEFAULT_DISPLAY, msg, TXT_MAX_WIDTH, true, false);
    }

    /**
     * Show the toast for a long period of time.
     *
     * @param msg     The text.
     * @param bgResid Background resource id.
     * @param yOffset Y offset.
     */
    public static void showLong(CharSequence msg, @DrawableRes int bgResid, @ColorRes int textColor, int yOffset) {
        show(null, Display.DEFAULT_DISPLAY, msg, TXT_MAX_WIDTH, true, bgResid, textColor, yOffset, false);
    }

    /**
     * Show the toast for a short period of time.
     *
     * @param context The content.
     * @param msg     The text.
     */
    public static void showShort(@NonNull Context context, CharSequence msg) {
        show(context, Display.INVALID_DISPLAY, msg, TXT_MAX_WIDTH, false, false);
    }

    /**
     * Show the toast for a short period of time.
     *
     * @param context The content.
     * @param msg     The text.
     * @param bgResid Background resource id.
     * @param yOffset Y offset.
     */
    public static void showShort(@NonNull Context context, CharSequence msg, @DrawableRes int bgResid, @ColorRes int textColor, int yOffset) {
        show(context, Display.INVALID_DISPLAY, msg, TXT_MAX_WIDTH, false, bgResid, textColor, yOffset, false);
    }

    /**
     * Show the toast for a long period of time.
     *
     * @param context The content.
     * @param msg     The text.
     */
    public static void showLong(@NonNull Context context, CharSequence msg) {
        show(context, Display.INVALID_DISPLAY, msg, TXT_MAX_WIDTH, true, false);
    }

    /**
     * Show the toast for a long period of time.
     *
     * @param context The content.
     * @param msg     The text.
     * @param bgResid Background resource id.
     * @param yOffset Y offset.
     */
    public static void showLong(@NonNull Context context, CharSequence msg, @DrawableRes int bgResid, @ColorRes int textColor, int yOffset) {
        show(context, Display.INVALID_DISPLAY, msg, TXT_MAX_WIDTH, true, bgResid, textColor, yOffset, false);
    }

    /**
     * Show the toast according to the display or context.
     *
     * @param context   The context.
     * @param displayId The displayId.
     * @param msg       The text.
     * @param maxWidth  The text maxWidth.
     * @param isLong    True is show the toast for a long period of time, false otherwise.
     * @param isRecycle True is recycle view,task,and window, false otherwise.
     */
    public static void show(@Nullable Context context, int displayId, CharSequence msg, int maxWidth, boolean isLong, boolean isRecycle) {
        show(context, displayId, msg, maxWidth, isLong, getBgResourceIdByTheme(), getToastTextColor(), -C11Util.Y_TOP_OFFSET / 2, isRecycle);
    }

    /**
     * Show the toast according to the display or context.
     *
     * @param context      The context.
     * @param displayId    The displayId.
     * @param msg          The text.
     * @param maxWidth     The text maxWidth.
     * @param isLong       True is show the toast for a long period of time, false otherwise.
     * @param bgResid      Background resource id.
     * @param textColorRes The text color resource.
     * @param yOffset      Y offset.
     * @param isRecycle    True is recycle view,task,and window, false otherwise.
     */
    public static void show(@Nullable Context context, int displayId, CharSequence msg, int maxWidth, boolean isLong, @DrawableRes int bgResid, @ColorRes int textColorRes, int yOffset, boolean isRecycle) {
        Application app = LpUtils.getApp();
        int currentDisplayId = Display.INVALID_DISPLAY;
        WindowManager wm;
        if (displayId != Display.INVALID_DISPLAY) {
            Display display = DisplayUtils.getDisplay(app, displayId);
            if (display != null) {
                wm = (WindowManager) app.createDisplayContext(display).getSystemService(Context.WINDOW_SERVICE);
                currentDisplayId = display.getDisplayId();
            } else {
                Log.e(TAG, "showOnDisplay: not find display please check displayId !");
                return;
            }
        } else {
            if (context != null) {
                wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                currentDisplayId = wm.getDefaultDisplay().getDisplayId();
            } else {
                Log.e(TAG, String.format(Locale.getDefault(), "showOnDisplay error : contexy is null, displayId can't be %d !", Display.INVALID_DISPLAY));
                return;
            }
        }
        if (isRecycle) {
            removeToastLayout(currentDisplayId);
        } else {
            hideToastLayout(currentDisplayId);
        }

        AtomicBoolean isAddView = new AtomicBoolean(true);
        WindowManager windowManager = WINDOW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> {
            isAddView.set(false);
            return wm;
        });
        View customToastView = VIEW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> getView(app, R.layout.layout_custom_toast));
        customToastView.setVisibility(View.VISIBLE);
        TextView tvMsg = FindViewUtlis.findViewById(customToastView, R.id.tvMsg);
        float msgWidth = 0;
        if (tvMsg != null) {
            tvMsg.setText(msg);
            tvMsg.setMaxWidth(maxWidth);
            tvMsg.setTextColor(ContextCompat.getColor(app, textColorRes));
            tvMsg.setBackgroundResource(bgResid);
            tvMsg.setPadding(TXT_PADING_START, TXT_PADING_TOP, TXT_PADING_END, TXT_PADING_BOTTOM);
            FrameLayout.LayoutParams tvLayoutParams = (FrameLayout.LayoutParams) tvMsg.getLayoutParams();
            tvLayoutParams.setMargins(0, 0, 0, 0);
            TextPaint textPaint = tvMsg.getPaint();
            msgWidth = textPaint.measureText(msg.toString());
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (windowManager.getDefaultDisplay().getDisplayId() == Display.DEFAULT_DISPLAY) {
            layoutParams.gravity = Gravity.TOP;
            layoutParams.x = C11Util.X_OFFSET / 2;
            layoutParams.y = Math.max(C11Util.Y_TOP_OFFSET + yOffset, 0);
        } else {
            layoutParams.gravity = Gravity.CENTER;
        }
        int contentWidth = (int) (msgWidth + TXT_PADING_START + TXT_PADING_END + TXT_BG_SHADOW_PADING * 2);
        layoutParams.width = Math.min(contentWidth, TXT_MAX_WIDTH);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.type = Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        layoutParams.format = PixelFormat.RGBA_8888;

        if (!isAddView.getAndSet(true)) {
            windowManager.addView(customToastView, layoutParams);
        }

        if (TASK_MAP.get(currentDisplayId) != null) {
            ThreadUtils.cancel(TASK_MAP.get(currentDisplayId));
        }
        ThreadUtils.Task<Integer> integerTask = new ThreadUtils.SimpleTask<Integer>() {
            @Override
            public Integer doInBackground() {
                List<Integer> collect = TASK_MAP
                        .entrySet()
                        .stream()
                        .filter(integerTaskEntry -> integerTaskEntry.getValue() == this)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                return collect.size() > 0 ? collect.get(0) : Display.INVALID_DISPLAY;
            }

            @Override
            public void onSuccess(Integer displayId) {
                if (isRecycle) {
                    removeToastLayout(displayId);
                } else {
                    hideToastLayout(displayId);
                    WINDOW_MANAGER_MAP.remove(displayId);
                    VIEW_MANAGER_MAP.remove(displayId);
                    TASK_MAP.remove(displayId);
                }
            }
        };
        TASK_MAP.put(currentDisplayId, integerTask);
        ThreadUtils.executeBySingleWithDelay(integerTask, isLong ? LENGTH_LONG : LENGTH_SHORT, TimeUnit.MILLISECONDS);
    }

    public static void removeToastLayout(int displayId) {
        WindowManager windowManager = WINDOW_MANAGER_MAP.get(displayId);
        View customToastView = VIEW_MANAGER_MAP.get(displayId);
        if (windowManager != null && customToastView != null) {
            windowManager.removeView(customToastView);
        }
        WINDOW_MANAGER_MAP.remove(displayId);
        VIEW_MANAGER_MAP.remove(displayId);
        TASK_MAP.remove(displayId);
    }

    public static void hideToastLayout(int displayId) {
        WindowManager windowManager = WINDOW_MANAGER_MAP.get(displayId);
        View customToastView = VIEW_MANAGER_MAP.get(displayId);
        if (windowManager != null && customToastView != null) {
            customToastView.setVisibility(View.GONE);
        }
    }

    private static View getView(Context context, @LayoutRes final int layoutId) {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflate.inflate(layoutId, null);
    }

    @DrawableRes
    private static int getBgResourceIdByTheme() {
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
    private static int getToastTextColor() {
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return R.color.text_primary;
            case ScreenModeType.DAY:
                return R.color.text_primary_light;
            default:
                return R.color.text_primary;
        }
    }
}