package com.leapmotor.lputils.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leapmotor.lputils.R;
import com.leapmotor.lputils.animation.OptAnimationLoader;
import com.leapmotor.lputils.content.ContextCompat;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
    public static final int TXT_MAX_LINES = 2;
    public static final int TXT_MAX_WIDTH = 800;
    public static final int TXT_BG_SHADOW_PADING = 40;
    public static final int TXT_PADING_START = 80 + TXT_BG_SHADOW_PADING, TXT_PADING_TOP = 40 + TXT_BG_SHADOW_PADING, TXT_PADING_END = 80 + TXT_BG_SHADOW_PADING, TXT_PADING_BOTTOM = 40 + TXT_BG_SHADOW_PADING;
    public static final int LENGTH_SHORT = 2000;
    public static final int LENGTH_LONG = 3500;
    public static final int DEFAULT_Y_TOP_OFFSET_MAIN = 0;
    private static final int DEFAULT_X_OFFSET = -1;
    private static final int MAX_WINDOW_SIZE = 10;
    private static final String TAG = "ToastUtils";
    private static final Map<Integer, WindowManager> WINDOW_MANAGER_MAP = new HashMap<>(MAX_WINDOW_SIZE);
    private static final Map<Integer, View> VIEW_MANAGER_MAP = new HashMap<>(MAX_WINDOW_SIZE);
    private static final Map<Integer, ThreadUtils.Task<Integer>> TASK_MAP = new HashMap<>(MAX_WINDOW_SIZE);

    private static final Config CONFIG = new Config();
    private static AnimationSet animModalShow;
    private static AnimationSet animModalHide;

    public static Config getConfig() {
        return CONFIG;
    }

    /**
     * Show the toast for a short period of time on default display.
     *
     * @param msg      The text.
     * @param duration The toast display duration.
     */
    public static void show(CharSequence msg, int duration) {
        show(null, Display.DEFAULT_DISPLAY, msg, duration);
    }

    /**
     * Show the toast for a short period of time according to the context.
     *
     * @param context  The content.
     * @param msg      The text.
     * @param duration The toast display duration.
     */
    public static void show(@NonNull Context context, CharSequence msg, int duration) {
        show(context, Display.INVALID_DISPLAY, msg, duration);
    }

    /**
     * Show the toast for a short period of time according to the display.
     *
     * @param displayId The displayId.
     * @param msg       The text.
     * @param duration  The toast display duration.
     */
    public static void show(int displayId, CharSequence msg, int duration) {
        show(null, displayId, msg, duration);
    }

    /**
     * Show the toast for a short period of time on default display.
     *
     * @param msg The text.
     */
    public static void showShort(CharSequence msg) {
        show(null, Display.DEFAULT_DISPLAY, msg, false);
    }

    /**
     * Show the toast for a long period of time on default display.
     *
     * @param msg The text.
     */
    public static void showLong(CharSequence msg) {
        show(null, Display.DEFAULT_DISPLAY, msg, true);
    }

    /**
     * Show the toast for a short period of time according to the context.
     *
     * @param context The content.
     * @param msg     The text.
     */
    public static void showShort(@NonNull Context context, CharSequence msg) {
        show(context, Display.INVALID_DISPLAY, msg, false);
    }

    /**
     * Show the toast for a long period of time according to the context.
     *
     * @param context The content.
     * @param msg     The text.
     */
    public static void showLong(@NonNull Context context, CharSequence msg) {
        show(context, Display.INVALID_DISPLAY, msg, true);
    }

    /**
     * Show the toast for a short period of time according to the display.
     *
     * @param displayId The displayId.
     * @param msg       The text.
     */
    public static void showShort(int displayId, CharSequence msg) {
        show(null, displayId, msg, false);
    }

    /**
     * Show the toast for a long period of time according to the display.
     *
     * @param displayId The displayId.
     * @param msg       The text.
     */
    public static void showLong(int displayId, CharSequence msg) {
        show(null, displayId, msg, true);
    }

    /**
     * Show the toast according to the display or context.
     *
     * @param context   The context.
     * @param displayId The displayId.
     * @param msg       The text.
     * @param isLong    True is show the toast for a long period of time, false otherwise.
     */
    private static void show(@Nullable Context context, int displayId, CharSequence msg, boolean isLong) {
        show(context, displayId, msg, TXT_MAX_WIDTH, isLong ? LENGTH_LONG : LENGTH_SHORT, ThemeUtils.getBgToast(), ThemeUtils.getTextPrimaryColor(), DEFAULT_X_OFFSET, DEFAULT_Y_TOP_OFFSET_MAIN, false, false);
    }

    /**
     * Show the toast according to the display or context.
     *
     * @param context   The context.
     * @param displayId The displayId.
     * @param msg       The text.
     * @param duration  The toast display duration.
     */
    private static void show(@Nullable Context context, int displayId, CharSequence msg, int duration) {
        show(context, displayId, msg, TXT_MAX_WIDTH, duration, ThemeUtils.getBgToast(), ThemeUtils.getTextPrimaryColor(), DEFAULT_X_OFFSET, DEFAULT_Y_TOP_OFFSET_MAIN, false, false);
    }

    /**
     * Show the full screen toast according to the display.
     *
     * @param displayId The displayId.
     * @param msg       The text.
     * @param isLong    True is show the toast for a long period of time, false otherwise.
     */
    public static void showFullScreen(int displayId, CharSequence msg, boolean isLong) {
        show(null, displayId, msg, TXT_MAX_WIDTH, isLong ? LENGTH_LONG : LENGTH_SHORT, ThemeUtils.getBgToast(), ThemeUtils.getTextPrimaryColor(), 0, 0, false, false);
    }

    /**
     * Show the toast according to the display or context.
     *
     * @param context            The context.
     * @param displayId          The displayId.
     * @param msg                The text.
     * @param maxWidth           The text maxWidth.
     * @param duration           The toast display duration.
     * @param bgResid            Background resource id.
     * @param textColorRes       The text color resource.
     * @param xOffset            X offset.
     * @param yOffset            Y offset.
     * @param isRecycle          True is recycle view,task,and window, false otherwise.
     * @param hideViewBeforeShow True is hide the previous view layout before each show toast, false otherwise.
     */
    public static void show(@Nullable Context context, int displayId, CharSequence msg, int maxWidth, int duration, @DrawableRes int bgResid, @ColorRes int textColorRes, int xOffset, int yOffset, boolean isRecycle, boolean hideViewBeforeShow) {
        Application app = LpUtils.getApp();
        ThreadUtils.runOnUiThread(() -> {
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
            if (CONFIG.isUseIsRecycleConfig() ? CONFIG.isRecycle() : isRecycle) {
                removeLayout(currentDisplayId);
            } else {
                if (CONFIG.isUseHideViewBeforeShowConfig() ? CONFIG.isHideViewBeforeShow() : hideViewBeforeShow) {
                    hideLayout(currentDisplayId);
                }
            }

            WindowManager windowManager = WINDOW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> wm);
            View customToastView = VIEW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> ViewUtils.layoutId2View(app, R.layout.layout_custom_toast));
            TextView tvMsg = FindViewUtlis.findViewById(customToastView, R.id.tvMsg);
            float msgWidth = 0;
            if (tvMsg != null) {
                tvMsg.setText(msg);
                tvMsg.setMaxWidth(CONFIG.isUseMaxWidthConfig() ? CONFIG.getMaxWidth() : maxWidth);
                tvMsg.setMaxLines(TXT_MAX_LINES);
                tvMsg.setMovementMethod(ScrollingMovementMethod.getInstance());
                tvMsg.setTextColor(ContextCompat.getColor(app, CONFIG.isUseTextColorResConfig() ? CONFIG.getTextColorRes() : textColorRes));
                tvMsg.setBackgroundResource(CONFIG.isUseBgResidConfig() ? CONFIG.getBgResid() : bgResid);
                tvMsg.setPadding(TXT_PADING_START, TXT_PADING_TOP, TXT_PADING_END, TXT_PADING_BOTTOM);
                FrameLayout.LayoutParams tvLayoutParams = (FrameLayout.LayoutParams) tvMsg.getLayoutParams();
                tvLayoutParams.setMargins(0, 0, 0, 0);
                msgWidth = tvMsg.getPaint().measureText(msg.toString());
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            if (windowManager.getDefaultDisplay().getDisplayId() == Display.DEFAULT_DISPLAY) {
                layoutParams.gravity = Gravity.TOP;
                layoutParams.x = CONFIG.isUseXOffsetConfig() ? CONFIG.getxOffset() : xOffset == DEFAULT_X_OFFSET ? C11Util.X_OFFSET / 2 : xOffset;
                layoutParams.y = CONFIG.isUseYOffsetConfig() ? CONFIG.getyOffset() : Math.max(C11Util.Y_TOP_OFFSET + yOffset, 0);
            } else {
                layoutParams.gravity = Gravity.TOP;
                layoutParams.x = CONFIG.isUseXOffsetViceConfig() ? CONFIG.getxOffsetVice() : xOffset == DEFAULT_X_OFFSET ? -C11Util.X_OFFSET_VICE / 2 : xOffset;
                layoutParams.y = CONFIG.isUseYOffsetViceConfig() ? CONFIG.getyOffsetVice() : Math.max(yOffset, 0);
            }
            int contentWidth = (int) (msgWidth + TXT_PADING_START + TXT_PADING_END + TXT_BG_SHADOW_PADING * 2);
            layoutParams.width = Math.min(contentWidth, TXT_MAX_WIDTH);
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            layoutParams.type = CONFIG.getWindowType();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            layoutParams.format = PixelFormat.RGBA_8888;
            if (customToastView.getParent() == null) {
                windowManager.addView(customToastView, layoutParams);
                if (CONFIG.isEnableAnimation()) {
                    animModalShow = (AnimationSet) OptAnimationLoader.loadAnimation(LpUtils.getApp(), CONFIG.getAnimShow());
                    animModalHide = (AnimationSet) OptAnimationLoader.loadAnimation(LpUtils.getApp(), CONFIG.getAnimHide());
                    animModalShow.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            Log.v(TAG, "show onAnimationStart");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Log.v(TAG, "show onAnimationEnd");
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            Log.v(TAG, "show onAnimationRepeat");
                        }
                    });
                    FrameLayout layoutCustomToastRootView = FindViewUtlis.findViewById(customToastView, R.id.layout_custom_toast_root);
                    layoutCustomToastRootView.startAnimation(animModalShow);
                }
                Log.d(TAG, "start show toast !");
            } else {
                Log.d(TAG, "already show toast !");
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
                    Log.d(TAG, "integerTask onSuccess ：" + displayId);
                    if (displayId == Display.INVALID_DISPLAY) {
                        clearView();
                        clearMapData(displayId);
                        return;
                    }
                    if (CONFIG.isEnableAnimation()) {
                        removeLayoutWithAnim(displayId);
                    } else {
                        removeLayout(displayId);
                    }
                }
            };
            TASK_MAP.put(currentDisplayId, integerTask);
            ThreadUtils.executeBySingleWithDelay(integerTask, duration, TimeUnit.MILLISECONDS);
        });
    }

    private static void clearView() {
        WINDOW_MANAGER_MAP.forEach((displayId, windowManager) -> {
            Log.w(TAG, "clearView ：" + displayId);
            View customToastView = VIEW_MANAGER_MAP.get(displayId);
            try {
                windowManager.removeView(customToastView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void clearMapData(int displayId) {
        if (displayId == Display.INVALID_DISPLAY) {
            WINDOW_MANAGER_MAP.clear();
            VIEW_MANAGER_MAP.clear();
            TASK_MAP.clear();
        } else {
            WINDOW_MANAGER_MAP.remove(displayId);
            VIEW_MANAGER_MAP.remove(displayId);
            TASK_MAP.remove(displayId);
        }
    }

    /**
     * Remove the toast layout according to the display.
     *
     * @param displayId The displayId.
     */
    public static void removeLayout(int displayId) {
        hideLayout(displayId);
        clearMapData(displayId);
    }

    /**
     * Hide the toast layout according to the display.
     *
     * @param displayId The displayId.
     */
    public static void hideLayout(int displayId) {
        WindowManager windowManager = WINDOW_MANAGER_MAP.get(displayId);
        View customToastView = VIEW_MANAGER_MAP.get(displayId);
        if (windowManager != null && customToastView != null) {
            ThreadUtils.runOnUiThread(() -> windowManager.removeView(customToastView));
            Log.d(TAG, "hide toast !");
        }
    }

    /**
     * Remove the toast layout with anim according to the display.
     *
     * @param displayId The displayId.
     */
    public static void removeLayoutWithAnim(int displayId) {
        hideLayoutWithAnim(displayId);
        clearMapData(displayId);
    }

    /**
     * Hide the toast layout with anim according to the display.
     *
     * @param displayId The displayId.
     */
    public static void hideLayoutWithAnim(int displayId) {
        WindowManager windowManager = WINDOW_MANAGER_MAP.get(displayId);
        View customToastView = VIEW_MANAGER_MAP.get(displayId);
        if (windowManager != null && customToastView != null) {
            ThreadUtils.runOnUiThread(() -> {
                animModalHide.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.v(TAG, "hide onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        customToastView.post(() -> windowManager.removeView(customToastView));
                        Log.v(TAG, "hide onAnimationEnd");
                        Log.d(TAG, "hide toast !");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.v(TAG, "hide onAnimationRepeat");
                    }
                });
                FrameLayout layoutCustomToastRootView = FindViewUtlis.findViewById(customToastView, R.id.layout_custom_toast_root);
                layoutCustomToastRootView.startAnimation(animModalHide);
            });
        }
    }

    /**
     * Refresh the toast layout theme.
     */
    public static void refresh() {
        VIEW_MANAGER_MAP.forEach((integer, view) -> {
            TextView tvMsg = FindViewUtlis.findViewById(view, R.id.tvMsg);
            if (tvMsg != null) {
                tvMsg.setTextColor(ContextCompat.getColor(LpUtils.getApp(), ThemeUtils.getTextPrimaryColor()));
                tvMsg.setBackgroundResource(ThemeUtils.getBgToast());
                tvMsg.setPadding(TXT_PADING_START, TXT_PADING_TOP, TXT_PADING_END, TXT_PADING_BOTTOM);
            }
        });
        Log.i(TAG, "refresh toast theme success !");
    }

    /**
     * clear used configuration.
     */
    public static void clearUsedConfig() {
        CONFIG.clearUsedConfig();
    }

    /**
     * Created by futia on 2022/11/29 13:47
     *
     * @Description: Toast Parameter Configuration.
     */
    public static final class Config {
        public static final int DEFAULT_VALUE = -10000;
        private boolean useMaxWidthConfig = false;
        private boolean useBgResidConfig = false;
        private boolean useTextColorResConfig = false;
        private boolean useXOffsetConfig = false;
        private boolean useYOffsetConfig = false;
        private boolean useXOffsetViceConfig = false;
        private boolean useYOffsetViceConfig = false;
        private boolean useIsRecycleConfig = false;
        private boolean useHideViewBeforeShowConfig = false;
        private int maxWidth = TXT_MAX_WIDTH;
        @DrawableRes
        private int bgResid = 0;
        @ColorRes
        private int textColorRes = 0;
        private int xOffset = DEFAULT_VALUE;
        private int yOffset = DEFAULT_VALUE;
        private int xOffsetVice = DEFAULT_VALUE;
        private int yOffsetVice = DEFAULT_VALUE;
        private boolean isRecycle = false;
        private boolean hideViewBeforeShow = false;
        private boolean isFullScreen = false;
        private boolean isFullScreenVice = false;
        private int windowType = Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
        private boolean enableAnimation = true;
        @AnimatorRes
        @AnimRes
        private int animShow = R.anim.toast_show_scale, animHide = R.anim.toast_hide_scale;

        private Config() {
        }

        public boolean isUseMaxWidthConfig() {
            return useMaxWidthConfig;
        }

        public boolean isUseBgResidConfig() {
            return useBgResidConfig;
        }

        public boolean isUseTextColorResConfig() {
            return useTextColorResConfig;
        }

        public boolean isUseXOffsetConfig() {
            return useXOffsetConfig;
        }

        public boolean isUseYOffsetConfig() {
            return useYOffsetConfig;
        }

        public boolean isUseXOffsetViceConfig() {
            return useXOffsetViceConfig;
        }

        public boolean isUseYOffsetViceConfig() {
            return useYOffsetViceConfig;
        }

        public boolean isUseIsRecycleConfig() {
            return useIsRecycleConfig;
        }

        public boolean isUseHideViewBeforeShowConfig() {
            return useHideViewBeforeShowConfig;
        }

        public int getMaxWidth() {
            return maxWidth;
        }

        public Config setMaxWidth(int maxWidth) {
            useMaxWidthConfig = true;
            this.maxWidth = maxWidth;
            return this;
        }

        @DrawableRes
        public int getBgResid() {
            return bgResid;
        }

        public Config setBgResid(@DrawableRes int bgResid) {
            useBgResidConfig = true;
            this.bgResid = bgResid;
            return this;
        }

        @ColorRes
        public int getTextColorRes() {
            return textColorRes;
        }

        public Config setTextColorRes(@ColorRes int textColorRes) {
            useTextColorResConfig = true;
            this.textColorRes = textColorRes;
            return this;
        }

        public int getxOffset() {
            return xOffset;
        }

        public Config setxOffset(int xOffset) {
            useXOffsetConfig = true;
            this.xOffset = xOffset;
            return this;
        }

        public int getyOffset() {
            return yOffset;
        }

        public Config setyOffset(int yOffset) {
            useYOffsetConfig = true;
            this.yOffset = yOffset;
            return this;
        }

        public int getxOffsetVice() {
            return xOffsetVice;
        }

        public Config setxOffsetVice(int xOffsetVice) {
            useXOffsetViceConfig = true;
            this.xOffsetVice = xOffsetVice;
            return this;
        }

        public int getyOffsetVice() {
            return yOffsetVice;
        }

        public Config setyOffsetVice(int yOffsetVice) {
            useYOffsetViceConfig = true;
            this.yOffsetVice = yOffsetVice;
            return this;
        }

        public boolean isRecycle() {
            return isRecycle;
        }

        public Config setRecycle(boolean recycle) {
            useIsRecycleConfig = true;
            isRecycle = recycle;
            return this;
        }

        public boolean isHideViewBeforeShow() {
            return hideViewBeforeShow;
        }

        public Config setHideViewBeforeShow(boolean hideViewBeforeShow) {
            useHideViewBeforeShowConfig = true;
            this.hideViewBeforeShow = hideViewBeforeShow;
            return this;
        }

        public boolean isFullScreen() {
            return isFullScreen;
        }

        public Config setFullScreen(boolean fullScreen) {
            isFullScreen = fullScreen;
            return this;
        }

        public boolean isFullScreenVice() {
            return isFullScreenVice;
        }

        public Config setFullScreenVice(boolean fullScreenVice) {
            isFullScreenVice = fullScreenVice;
            return this;
        }

        public int getWindowType() {
            return windowType;
        }

        public Config setWindowType(int windowType) {
            this.windowType = windowType;
            return this;
        }

        public boolean isEnableAnimation() {
            return enableAnimation;
        }

        public Config setEnableAnimation(boolean enableAnimation) {
            this.enableAnimation = enableAnimation;
            return this;
        }

        public int getAnimShow() {
            return animShow;
        }

        public Config setAnimShow(@AnimatorRes @AnimRes int animShow) {
            this.animShow = animShow;
            return this;
        }

        public int getAnimHide() {
            return animHide;
        }

        public Config setAnimHide(@AnimatorRes @AnimRes int animHide) {
            this.animHide = animHide;
            return this;
        }

        public Config setAnimShowHide(@AnimatorRes @AnimRes int animShow, @AnimatorRes @AnimRes int animHide) {
            this.animShow = animShow;
            this.animHide = animHide;
            return this;
        }

        public Config clearUsedConfig() {
            useMaxWidthConfig = false;
            useBgResidConfig = false;
            useTextColorResConfig = false;
            useXOffsetConfig = false;
            useYOffsetConfig = false;
            useXOffsetViceConfig = false;
            useYOffsetViceConfig = false;
            useIsRecycleConfig = false;
            useHideViewBeforeShowConfig = false;
            isFullScreen = false;
            isFullScreenVice = false;
            windowType = Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
            enableAnimation = true;
            animShow = R.anim.toast_show_scale;
            animHide = R.anim.toast_hide_scale;
            return this;
        }

        public void create() {
            if (isFullScreen) {
                if (!isUseXOffsetConfig()) {
                    setxOffset(0);
                }
                if (!isUseYOffsetConfig()) {
                    setyOffset(0);
                }
            }
            if (isFullScreenVice) {
                if (!isUseXOffsetViceConfig()) {
                    setxOffsetVice(0);
                }
                if (!isUseYOffsetViceConfig()) {
                    setyOffsetVice(0);
                }
            }
        }
    }
}