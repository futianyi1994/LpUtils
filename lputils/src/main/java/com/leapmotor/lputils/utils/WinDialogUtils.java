package com.leapmotor.lputils.utils;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leapmotor.lputils.R;
import com.leapmotor.lputils.content.ContextCompat;
import com.leapmotor.lputils.widget.ShadowDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * good programmer.
 *
 * @date : 2022/6/7 15:39
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class WinDialogUtils {
    public static final int DIALOG_WIDTH = 720;
    public static final int TXT_TITLE_MAX_WIDTH = 560;
    public static final int TXT_TITLE_MAX_HEIGHT_DISPLAY0 = 720;
    public static final int TXT_TITLE_MAX_HEIGHT_DISPLAY1 = 560;
    public static final int TXT_TITLE_HEAD_HEIGHT = 95;
    public static final int TXT_BG_SHADOW_PADING = 40;
    private static final int MAX_WINDOW_SIZE = 10;
    private static final String TAG = "DialogUtils";
    private static final Map<Integer, WindowManager> WINDOW_MANAGER_MAP = new HashMap<>(MAX_WINDOW_SIZE);
    private static final Map<Integer, View> VIEW_MANAGER_MAP = new HashMap<>(MAX_WINDOW_SIZE);
    private static final Map<Integer, ThreadUtils.Task<Integer>> TASK_MAP = new HashMap<>(MAX_WINDOW_SIZE);
    private static final Map<Integer, DialogUtils.OnClickListener> LISTENER_MAP = new HashMap<>(MAX_WINDOW_SIZE);

    private static final Config CONFIG = new Config();

    public static Config getConfig() {
        return CONFIG;
    }

    /**
     * Show the dialog on default display.
     *
     * @param title           The title text.
     * @param leftTitle       The left title text.
     * @param rightTitle      The right title text.
     * @param onClickListener The callback that will run.
     */
    public static void show(CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
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
     */
    public static void show(@Nullable Activity activity, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
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
    public static void show(int displayId, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
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
     */
    private static void show(@Nullable Activity activity, int displayId, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
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
     */
    public static void show(@Nullable Activity activity, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
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
    public static void show(int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
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
     */
    private static void show(@Nullable Activity activity, int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
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
    public static void showFullScreen(int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable DialogUtils.OnClickListener onClickListener) {
        WindowManager.LayoutParams layoutParams = getDefaultLayoutParams(displayId);
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.type = CONFIG.getWindowType();
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
     */
    public static void show(@Nullable Activity activity, int displayId, @Nullable CharSequence headTitle, @NonNull CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @DrawableRes int bgResid, @ColorRes int textColorRes, @Nullable WindowManager.LayoutParams layoutParams, @Nullable DialogUtils.OnClickListener onClickListener) {
        Application app = LpUtils.getApp();
        ThreadUtils.runOnUiThread(() -> {
            boolean haveWindowToken;
            int currentDisplayId = Display.INVALID_DISPLAY;
            WindowManager wm;
            WindowManager.LayoutParams lp = CONFIG.isUseLayoutParamsConfig() ? CONFIG.getLayoutParams() : layoutParams;
            if (displayId != Display.INVALID_DISPLAY) {
                haveWindowToken = false;
                Display display = DisplayUtils.getDisplay(app, displayId);
                if (display != null) {
                    wm = (WindowManager) app.createDisplayContext(display).getSystemService(Context.WINDOW_SERVICE);
                    currentDisplayId = display.getDisplayId();
                } else {
                    Log.e(TAG, "showOnDisplay: not find display please check displayId !");
                    return;
                }
            } else {
                if (activity != null) {
                    haveWindowToken = true;
                    wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
                    currentDisplayId = wm.getDefaultDisplay().getDisplayId();
                } else {
                    Log.e(TAG, String.format(Locale.getDefault(), "showOnDisplay error : context is null, displayId can't be %d !", Display.INVALID_DISPLAY));
                    return;
                }
            }
            removeLayout(currentDisplayId);

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
                    removeLayout(displayId);
                }
            };
            TASK_MAP.put(currentDisplayId, integerTask);

            WindowManager windowManager = WINDOW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> wm);
            View customDialogView = VIEW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> ViewUtils.layoutId2View(app, R.layout.layout_custom_dialog));
            LinearLayout llRoot = FindViewUtlis.findViewById(customDialogView, R.id.llRoot);
            LinearLayout llConfirm = FindViewUtlis.findViewById(customDialogView, R.id.llConfirm);
            TextView tvHeadTitle = FindViewUtlis.findViewById(customDialogView, R.id.tvHeadTitle);
            TextView tvTitle = FindViewUtlis.findViewById(customDialogView, R.id.tvTitle);
            View vLine = FindViewUtlis.findViewById(customDialogView, R.id.vLine);
            TextView tvLeft = FindViewUtlis.findViewById(customDialogView, R.id.tvLeft);
            TextView tvRight = FindViewUtlis.findViewById(customDialogView, R.id.tvRight);
            if (llRoot != null && llConfirm != null && tvTitle != null && tvHeadTitle != null && vLine != null && tvLeft != null && tvRight != null) {
                @ColorRes int mTextColorRes = CONFIG.isUseTextColorResConfig() ? CONFIG.getTextColorRes() : textColorRes;
                llRoot.setBackgroundResource(CONFIG.isUseBgResidConfig() ? CONFIG.getBgResid() : bgResid);
                llRoot.setPadding(0, 0, 0, 0);
                tvTitle.setText(title);
                int tvTitleMaxHeight = currentDisplayId == Display.DEFAULT_DISPLAY ? TXT_TITLE_MAX_HEIGHT_DISPLAY0 : TXT_TITLE_MAX_HEIGHT_DISPLAY1;
                tvTitle.setMaxHeight(headTitle == null ? tvTitleMaxHeight : tvTitleMaxHeight - TXT_TITLE_HEAD_HEIGHT);
                tvTitle.setMovementMethod(ScrollingMovementMethod.getInstance());
                tvTitle.setTextColor(ContextCompat.getColor(app, mTextColorRes));
                LinearLayout.LayoutParams tvHeadTitleLayoutParams = (LinearLayout.LayoutParams) tvHeadTitle.getLayoutParams();
                tvHeadTitleLayoutParams.setMargins(TXT_BG_SHADOW_PADING, TXT_BG_SHADOW_PADING, TXT_BG_SHADOW_PADING, 0);
                LinearLayout.LayoutParams tvTitleLayoutParams = (LinearLayout.LayoutParams) tvTitle.getLayoutParams();
                tvTitleLayoutParams.setMargins(TXT_BG_SHADOW_PADING, headTitle == null ? TXT_BG_SHADOW_PADING : 0, TXT_BG_SHADOW_PADING, 0);
                LinearLayout.LayoutParams llConfirmLayoutParams = (LinearLayout.LayoutParams) llConfirm.getLayoutParams();
                llConfirmLayoutParams.setMargins(TXT_BG_SHADOW_PADING, 0, TXT_BG_SHADOW_PADING, TXT_BG_SHADOW_PADING);
                LinearLayout.LayoutParams vLineLayoutParams = (LinearLayout.LayoutParams) vLine.getLayoutParams();
                vLineLayoutParams.setMargins(TXT_BG_SHADOW_PADING, 0, TXT_BG_SHADOW_PADING, 0);
                float titleWidth = tvTitle.getPaint().measureText(title.toString());
                if (titleWidth >= TXT_TITLE_MAX_WIDTH) {
                    tvTitle.setGravity(Gravity.START);
                } else {
                    tvTitle.setGravity(Gravity.CENTER);
                }
                tvHeadTitle.setText(headTitle);
                tvLeft.setText(leftTitle);
                tvRight.setText(rightTitle);
                tvHeadTitle.setSelected(true);
                tvLeft.setSelected(true);
                tvRight.setSelected(true);
                tvHeadTitle.setTextColor(ContextCompat.getColor(app, mTextColorRes));
                vLine.setBackgroundResource(ThemeUtils.getLineColor());
                tvLeft.setTextColor(ContextCompat.getColor(app, ThemeUtils.getBtnTextHighlightColor()));
                tvRight.setTextColor(ContextCompat.getColor(app, mTextColorRes));
                tvHeadTitle.setVisibility(headTitle == null ? View.GONE : View.VISIBLE);
                tvLeft.setVisibility(leftTitle == null ? View.GONE : View.VISIBLE);
                tvRight.setVisibility(rightTitle == null ? View.GONE : View.VISIBLE);

                DialogUtils.OnClickListener listener = LISTENER_MAP.computeIfAbsent(currentDisplayId, integer -> onClickListener);
                tvLeft.setOnClickListener(v -> {
                    if (listener != null) {
                        if (listener.onLeftClick(v)) {
                            ThreadUtils.executeBySingle(integerTask);
                        }
                    }
                });
                tvRight.setOnClickListener(v -> {
                    if (listener != null) {
                        if (listener.onRightClick(v)) {
                            ThreadUtils.executeBySingle(integerTask);
                        }
                    }
                });
            }
            if (lp == null) {
                lp = getDefaultLayoutParams(currentDisplayId);
                lp.type = haveWindowToken ? WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG : CONFIG.getWindowType();
            }

            WindowManager.LayoutParams finalLayoutParams = lp;
            windowManager.addView(customDialogView, finalLayoutParams);
        });
    }

    /**
     * Remove the dialog layout according to the display.
     *
     * @param displayId The displayId.
     */
    public static void removeLayout(int displayId) {
        WindowManager windowManager = WINDOW_MANAGER_MAP.get(displayId);
        View customDialogView = VIEW_MANAGER_MAP.get(displayId);
        if (windowManager != null && customDialogView != null) {
            ThreadUtils.runOnUiThread(() -> windowManager.removeView(customDialogView));
        }
        WINDOW_MANAGER_MAP.remove(displayId);
        VIEW_MANAGER_MAP.remove(displayId);
        TASK_MAP.remove(displayId);
        LISTENER_MAP.remove(displayId);
    }

    /**
     * Refresh the dialog layout theme.
     */
    public static void refresh() {
        VIEW_MANAGER_MAP.forEach((integer, view) -> {
            LinearLayout llRoot = FindViewUtlis.findViewById(view, R.id.llRoot);
            TextView tvHeadTitle = FindViewUtlis.findViewById(view, R.id.tvHeadTitle);
            TextView tvTitle = FindViewUtlis.findViewById(view, R.id.tvTitle);
            View vLine = FindViewUtlis.findViewById(view, R.id.vLine);
            TextView tvLeft = FindViewUtlis.findViewById(view, R.id.tvLeft);
            TextView tvRight = FindViewUtlis.findViewById(view, R.id.tvRight);
            if (llRoot != null && tvHeadTitle != null && tvTitle != null && vLine != null && tvLeft != null && tvRight != null) {
                int textPrimaryColor = ContextCompat.getColor(LpUtils.getApp(), ThemeUtils.getTextPrimaryColor());
                llRoot.setBackgroundResource(ThemeUtils.getbgPopup());
                llRoot.setPadding(0, 0, 0, 0);
                tvHeadTitle.setTextColor(textPrimaryColor);
                tvTitle.setTextColor(textPrimaryColor);
                vLine.setBackgroundResource(ThemeUtils.getLineColor());
                tvLeft.setTextColor(ContextCompat.getColor(LpUtils.getApp(), ThemeUtils.getBtnTextHighlightColor()));
                tvRight.setTextColor(textPrimaryColor);
            }
        });
        Log.i(TAG, "refresh dialog theme success !");
    }

    /**
     * Get defarult windowManager layoutParams.
     *
     * @param displayId The displayId.
     * @return The default windowManager layoutParams.
     */
    public static WindowManager.LayoutParams getDefaultLayoutParams(int displayId) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        int configGravity = CONFIG.getGravity();
        if (displayId == Display.DEFAULT_DISPLAY) {
            layoutParams.gravity = configGravity != Config.DEFAULT_VALUE ? configGravity : Gravity.CENTER;
            int xOffset = CONFIG.getxOffset();
            int yOffset = CONFIG.getyOffset();
            boolean fullScreen = CONFIG.isFullScreen();
            layoutParams.x = fullScreen ? 0 : xOffset != Config.DEFAULT_VALUE ? xOffset : C11Util.X_OFFSET / 2;
            layoutParams.y = fullScreen ? 0 : yOffset != Config.DEFAULT_VALUE ? yOffset : (C11Util.Y_TOP_OFFSET - C11Util.Y_BOTTOM_OFFSET) / 2;
        } else {
            layoutParams.gravity = configGravity != Config.DEFAULT_VALUE ? configGravity : Gravity.CENTER;
            int xOffsetVice = CONFIG.getxOffsetVice();
            int yOffsetVice = CONFIG.getyOffsetVice();
            boolean fullScreenVice = CONFIG.isFullScreenVice();
            layoutParams.x = fullScreenVice ? 0 : xOffsetVice != Config.DEFAULT_VALUE ? xOffsetVice : 0;
            layoutParams.y = fullScreenVice ? 0 : yOffsetVice != Config.DEFAULT_VALUE ? yOffsetVice : 0;
        }
        layoutParams.width = DIALOG_WIDTH;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        layoutParams.format = PixelFormat.RGBA_8888;
        return layoutParams;
    }

    /**
     * clear used configuration.
     */
    public static void clearUsedConfig() {
        CONFIG.clearUsedConfig();
    }

    /**
     * Created by futia on 2022/11/29 13:46
     *
     * @Description: WinDialog Parameter Configuration.
     */
    public static final class Config {
        public static final int DEFAULT_VALUE = ShadowDialog.DEFAULT_VALUE;
        private boolean useBgResidConfig = false;
        private boolean useTextColorResConfig = false;
        private boolean useLayoutParamsConfig = false;
        @DrawableRes
        private int bgResid = 0;
        @ColorRes
        private int textColorRes = 0;
        private WindowManager.LayoutParams layoutParams = null;
        private int xOffset = DEFAULT_VALUE;
        private int yOffset = DEFAULT_VALUE;
        private int xOffsetVice = DEFAULT_VALUE;
        private int yOffsetVice = DEFAULT_VALUE;
        private int gravity = DEFAULT_VALUE;
        private boolean isFullScreen = false, isImmersion = true;
        private boolean isFullScreenVice = false;
        private int windowType = Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;

        Config() {
        }

        public boolean isUseBgResidConfig() {
            return useBgResidConfig;
        }

        public boolean isUseTextColorResConfig() {
            return useTextColorResConfig;
        }

        public boolean isUseLayoutParamsConfig() {
            return useLayoutParamsConfig;
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

        public WindowManager.LayoutParams getLayoutParams() {
            return layoutParams;
        }

        public Config setLayoutParams(WindowManager.LayoutParams layoutParams) {
            useLayoutParamsConfig = true;
            this.layoutParams = layoutParams;
            return this;
        }

        public int getxOffset() {
            return xOffset;
        }

        public Config setxOffset(int xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public int getyOffset() {
            return yOffset;
        }

        public Config setyOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public int getxOffsetVice() {
            return xOffsetVice;
        }

        public Config setxOffsetVice(int xOffsetVice) {
            this.xOffsetVice = xOffsetVice;
            return this;
        }

        public int getyOffsetVice() {
            return yOffsetVice;
        }

        public Config setyOffsetVice(int yOffsetVice) {
            this.yOffsetVice = yOffsetVice;
            return this;
        }

        public int getGravity() {
            return gravity;
        }

        public Config setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public boolean isImmersion() {
            return isImmersion;
        }

        public Config setImmersion(boolean immersion) {
            isImmersion = immersion;
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

        public Config clearUsedConfig() {
            useBgResidConfig = false;
            useTextColorResConfig = false;
            useLayoutParamsConfig = false;
            bgResid = 0;
            textColorRes = 0;
            layoutParams = null;
            xOffset = DEFAULT_VALUE;
            yOffset = DEFAULT_VALUE;
            xOffsetVice = DEFAULT_VALUE;
            yOffsetVice = DEFAULT_VALUE;
            gravity = DEFAULT_VALUE;
            isImmersion = true;
            isFullScreen = false;
            isFullScreenVice = false;
            windowType = Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
            return this;
        }

        public void create() {
        }
    }
}