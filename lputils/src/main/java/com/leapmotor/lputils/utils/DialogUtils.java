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
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.leapmotor.lputils.R;
import com.leapmotor.lputils.view.MTextView;

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
     */
    public static void show(@Nullable Activity activity, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
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
     */
    private static void show(@Nullable Activity activity, int displayId, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(activity, displayId, null, title, leftTitle, rightTitle, ColorUtils.getBgToastOrDialog(), ColorUtils.getTextPrimaryColor(), onClickListener);
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
    public static void show(@Nullable Activity activity, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
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
    public static void show(int displayId, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
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
    private static void show(@Nullable Activity activity, int displayId, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @Nullable OnClickListener onClickListener) {
        show(activity, displayId, headTitle, title, leftTitle, rightTitle, ColorUtils.getBgToastOrDialog(), ColorUtils.getTextPrimaryColor(), onClickListener);
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
    public static void show(@Nullable Activity activity, int displayId, @Nullable CharSequence headTitle, CharSequence title, @Nullable CharSequence leftTitle, @Nullable CharSequence rightTitle, @DrawableRes int bgResid, @ColorRes int textColorRes, @Nullable OnClickListener onClickListener) {
        Application app = LpUtils.getApp();
        boolean haveWindowToken;
        int currentDisplayId = Display.INVALID_DISPLAY;
        WindowManager wm;
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
                Log.e(TAG, String.format(Locale.getDefault(), "showOnDisplay error : contexy is null, displayId can't be %d !", Display.INVALID_DISPLAY));
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
        MTextView tvLeft = FindViewUtlis.findViewById(customDialogView, R.id.tvLeft);
        TextView tvRight = FindViewUtlis.findViewById(customDialogView, R.id.tvRight);
        if (llRoot != null && llConfirm != null && tvTitle != null && tvHeadTitle != null && vLine != null && tvLeft != null && tvRight != null) {
            llRoot.setBackgroundResource(bgResid);
            llRoot.setPadding(0, 0, 0, 0);
            tvTitle.setText(title);
            int tvTitleMaxHeight = currentDisplayId == Display.DEFAULT_DISPLAY ? TXT_TITLE_MAX_HEIGHT_DISPLAY0 : TXT_TITLE_MAX_HEIGHT_DISPLAY1;
            tvTitle.setMaxHeight(headTitle == null ? tvTitleMaxHeight : tvTitleMaxHeight - TXT_TITLE_HEAD_HEIGHT);
            tvTitle.setMovementMethod(ScrollingMovementMethod.getInstance());
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
            tvHeadTitle.setSelected(true);
            tvLeft.setSelected(true);
            tvRight.setSelected(true);
            tvHeadTitle.setTextColor(ContextCompat.getColor(app, textColorRes));
            tvLeft.setTextColor(ContextCompat.getColor(app, ColorUtils.getBtnTextHighlightColor()));
            tvRight.setTextColor(ContextCompat.getColor(app, textColorRes));
            tvHeadTitle.setVisibility(headTitle == null ? View.GONE : View.VISIBLE);
            tvLeft.setVisibility(leftTitle == null ? View.GONE : View.VISIBLE);
            tvRight.setVisibility(rightTitle == null ? View.GONE : View.VISIBLE);

            OnClickListener listener = LISTENER_MAP.computeIfAbsent(currentDisplayId, integer -> onClickListener);
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
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (windowManager.getDefaultDisplay().getDisplayId() == Display.DEFAULT_DISPLAY) {
            layoutParams.gravity = Gravity.CENTER;
            layoutParams.x = C11Util.X_OFFSET / 2;
            layoutParams.y = (C11Util.Y_TOP_OFFSET - C11Util.Y_BOTTOM_OFFSET) / 2;
        } else {
            layoutParams.gravity = Gravity.CENTER;
        }
        layoutParams.width = DIALOG_WIDTH;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.type = haveWindowToken ? WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG : Build.VERSION.SDK_INT > Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY : WindowManager.LayoutParams.TYPE_PHONE;
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
            AppCompatTextView tvLeft = FindViewUtlis.findViewById(view, R.id.tvLeft);
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
         * @return True is click dismmss, false otherwise.
         */
        boolean onLeftClick(View v);

        /**
         * Called when a right view has been clicked.
         *
         * @param v The view that was clicked.
         * @return True is click dismmss, false otherwise.
         */
        boolean onRightClick(View v);
    }
}