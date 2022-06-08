package com.leapmotor.lputils.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
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
import androidx.core.content.ContextCompat;

import com.leapmotor.lputils.R;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
    public static final int TXT_BG_SHADOW_PADING = 40;
    private static final String TAG = "DialogUtils";
    private static final Map<Integer, WindowManager> WINDOW_MANAGER_MAP = new HashMap<>();
    private static final Map<Integer, View> VIEW_MANAGER_MAP = new HashMap<>();
    private static final Map<Integer, ThreadUtils.Task<Integer>> TASK_MAP = new HashMap<>();
    private static OnClickListener onClickListener;
    private static boolean isClickDismiss;

    /**
     * Register a callback to be invoked when this view is clicked. Default is dismiss after click.
     *
     * @param onClickListener The callback that will run.
     */
    public static void setOnClickListener(@NonNull OnClickListener onClickListener) {
        setOnClickListener(onClickListener, true);
    }

    /**
     * Register a callback to be invoked when this view is clicked.
     *
     * @param onClickListener The callback that will run.
     * @param isClickDismiss  True is click dismmss, false otherwise.
     */
    public static void setOnClickListener(@NonNull OnClickListener onClickListener, boolean isClickDismiss) {
        DialogUtils.onClickListener = onClickListener;
        DialogUtils.isClickDismiss = isClickDismiss;
    }

    /**
     * Show the dialog on default display.
     *
     * @param title      The title text.
     * @param leftTitle  The left title text.
     * @param rightTitle The right title text.
     */
    public static void show(CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle) {
        show(null, Display.DEFAULT_DISPLAY, title, leftTitle, rightTitle);
    }

    /**
     * Show the dialog according to the context.
     *
     * @param context    The context.
     * @param title      The title text.
     * @param leftTitle  The left title text.
     * @param rightTitle The right title text.
     */
    public static void show(@Nullable Context context, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle) {
        show(context, Display.INVALID_DISPLAY, title, leftTitle, rightTitle);
    }

    /**
     * Show the dialog according to the display.
     *
     * @param displayId  The displayId.
     * @param title      The title text.
     * @param leftTitle  The left title text.
     * @param rightTitle The right title text.
     */
    public static void show(int displayId, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle) {
        show(null, displayId, title, leftTitle, rightTitle);
    }

    /**
     * Show the dialog according to the display or context.
     *
     * @param context    The context.
     * @param displayId  The displayId.
     * @param title      The title text.
     * @param leftTitle  The left title text.
     * @param rightTitle The right title text.
     */
    private static void show(@Nullable Context context, int displayId, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle) {
        show(context, displayId, null, title, leftTitle, rightTitle, ColorUtils.getBgToastOrDialog(), ColorUtils.getTextPrimaryColor(), false);
    }

    /**
     * Show the dialog according to the context.
     *
     * @param context    The context.
     * @param headTitle  The head title text.
     * @param title      The title text.
     * @param leftTitle  The left title text.
     * @param rightTitle The right title text.
     */
    public static void show(@Nullable Context context, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle) {
        show(context, Display.INVALID_DISPLAY, headTitle, title, leftTitle, rightTitle);
    }

    /**
     * Show the dialog according to the display.
     *
     * @param displayId  The displayId.
     * @param headTitle  The head title text.
     * @param title      The title text.
     * @param leftTitle  The left title text.
     * @param rightTitle The right title text.
     */
    public static void show(int displayId, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle) {
        show(null, displayId, headTitle, title, leftTitle, rightTitle);
    }

    /**
     * Show the dialog according to the display or context.
     *
     * @param context    The context.
     * @param displayId  The displayId.
     * @param headTitle  The head title text.
     * @param title      The title text.
     * @param leftTitle  The left title text.
     * @param rightTitle The right title text.
     */
    private static void show(@Nullable Context context, int displayId, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle) {
        show(context, displayId, headTitle, title, leftTitle, rightTitle, ColorUtils.getBgToastOrDialog(), ColorUtils.getTextPrimaryColor(), false);
    }

    /**
     * Show the dialog according to the display or context.
     *
     * @param context      The context.
     * @param displayId    The displayId.
     * @param headTitle    The head title text.
     * @param title        The title text.
     * @param leftTitle    The left title text.
     * @param rightTitle   The right title text.
     * @param bgResid      Background resource id.
     * @param textColorRes The text color resource.
     * @param isRecycle    True is recycle view,task,and window, false otherwise.
     */
    public static void show(@Nullable Context context, int displayId, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @DrawableRes int bgResid, @ColorRes int textColorRes, boolean isRecycle) {
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
            removeLayout(currentDisplayId);
        } else {
            hideLayout(currentDisplayId);
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
                    removeLayout(displayId);
                } else {
                    hideLayout(displayId);
                    WINDOW_MANAGER_MAP.remove(displayId);
                    VIEW_MANAGER_MAP.remove(displayId);
                    TASK_MAP.remove(displayId);
                }
            }
        };
        TASK_MAP.put(currentDisplayId, integerTask);

        AtomicBoolean isAddView = new AtomicBoolean(true);
        WindowManager windowManager = WINDOW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> {
            isAddView.set(false);
            return wm;
        });
        View customDialogView = VIEW_MANAGER_MAP.computeIfAbsent(currentDisplayId, k -> ViewUtils.layoutId2View(app, R.layout.layout_custom_dialog));
        customDialogView.setVisibility(View.VISIBLE);
        LinearLayout llRoot = FindViewUtlis.findViewById(customDialogView, R.id.llRoot);
        LinearLayout llConfirm = FindViewUtlis.findViewById(customDialogView, R.id.llConfirm);
        TextView tvHeadTitle = FindViewUtlis.findViewById(customDialogView, R.id.tvHeadTitle);
        TextView tvTitle = FindViewUtlis.findViewById(customDialogView, R.id.tvTitle);
        View vLine = FindViewUtlis.findViewById(customDialogView, R.id.vLine);
        TextView tvLeft = FindViewUtlis.findViewById(customDialogView, R.id.tvLeft);
        TextView tvRight = FindViewUtlis.findViewById(customDialogView, R.id.tvRight);
        if (llRoot != null && llConfirm != null && tvTitle != null && tvHeadTitle != null && vLine != null && tvLeft != null && tvRight != null) {
            llRoot.setBackgroundResource(bgResid);
            llRoot.setPadding(0, 0, 0, 0);
            tvTitle.setText(title);
            tvTitle.setTextColor(ContextCompat.getColor(app, textColorRes));
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
            tvHeadTitle.setTextColor(ContextCompat.getColor(app, textColorRes));
            tvLeft.setTextColor(ContextCompat.getColor(app, ColorUtils.getBtnTextHighlightColor()));
            tvRight.setTextColor(ContextCompat.getColor(app, textColorRes));
            tvHeadTitle.setVisibility(headTitle == null ? View.GONE : View.VISIBLE);
            tvLeft.setVisibility(leftTitle == null ? View.GONE : View.VISIBLE);
            tvRight.setVisibility(rightTitle == null ? View.GONE : View.VISIBLE);
            tvLeft.setOnClickListener(v -> {
                if (onClickListener != null) {
                    onClickListener.onLeftClick(v);
                }
                if (isClickDismiss) {
                    ThreadUtils.executeBySingle(integerTask);
                }
            });
            tvRight.setOnClickListener(v -> {
                if (onClickListener != null) {
                    onClickListener.onRightClick(v);
                }
                if (isClickDismiss) {
                    ThreadUtils.executeBySingle(integerTask);
                }
            });
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (windowManager.getDefaultDisplay().getDisplayId() == Display.DEFAULT_DISPLAY) {
            layoutParams.x = C11Util.X_OFFSET / 2;
        }
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width = DIALOG_WIDTH;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.type = Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.format = PixelFormat.RGBA_8888;

        if (!isAddView.getAndSet(true)) {
            windowManager.addView(customDialogView, layoutParams);
        }
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
            windowManager.removeView(customDialogView);
        }
        WINDOW_MANAGER_MAP.remove(displayId);
        VIEW_MANAGER_MAP.remove(displayId);
    }

    /**
     * Hide the dialog layout according to the display.
     *
     * @param displayId The displayId.
     */
    public static void hideLayout(int displayId) {
        WindowManager windowManager = WINDOW_MANAGER_MAP.get(displayId);
        View customDialogView = VIEW_MANAGER_MAP.get(displayId);
        if (windowManager != null && customDialogView != null) {
            customDialogView.setVisibility(View.GONE);
        }
    }

    /**
     * Refresh the dialog layout theme.
     */
    public static void refresh() {
        VIEW_MANAGER_MAP.forEach((integer, view) -> {
            LinearLayout llRoot = FindViewUtlis.findViewById(view, R.id.llRoot);
            TextView tvHeadTitle = FindViewUtlis.findViewById(view, R.id.tvHeadTitle);
            TextView tvTitle = FindViewUtlis.findViewById(view, R.id.tvTitle);
            TextView tvLeft = FindViewUtlis.findViewById(view, R.id.tvLeft);
            TextView tvRight = FindViewUtlis.findViewById(view, R.id.tvRight);
            if (llRoot != null && tvTitle != null && tvHeadTitle != null && tvLeft != null && tvRight != null) {
                int textPrimaryColor = ContextCompat.getColor(LpUtils.getApp(), ColorUtils.getTextPrimaryColor());
                int textLeftColor = ContextCompat.getColor(LpUtils.getApp(), ColorUtils.getBtnTextHighlightColor());
                llRoot.setBackgroundResource(ColorUtils.getBgToastOrDialog());
                llRoot.setPadding(0, 0, 0, 0);
                tvHeadTitle.setTextColor(textPrimaryColor);
                tvTitle.setTextColor(textPrimaryColor);
                tvLeft.setTextColor(textLeftColor);
                tvRight.setTextColor(textPrimaryColor);
            }
        });
        Log.i(TAG, "refresh dialog theme success !");
    }

    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     */
    public interface OnClickListener {
        /**
         * Called when a left view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onLeftClick(View v);

        /**
         * Called when a right view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onRightClick(View v);
    }
}