package com.leapmotor.lputils.widget.blur;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.leapmotor.lputils.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * good programmer.
 *
 * @date : 2023/5/30 20:09
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class BlurWindowController {

    private static final int MAX_COUNT = 5;
    private final List<BlurWindow> showingWindowList = new ArrayList<>();
    private BlurWindow blurWindowPool;
    private Context context;
    private int statusBarHeight;

    public BlurWindowController(Context context, WindowManager windowManager, int blurRadius, int corners) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            this.context = context;
            statusBarHeight = windowManager.getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.statusBars()).top;

            // 预创建放池子里
            for (int i = 0; i < MAX_COUNT; i++) {
                BlurWindow blurWindow = new BlurWindow(context, windowManager, blurRadius, corners);
                recycleWindow(blurWindow);
                blurWindow.show();
            }
        }
    }

    /**
     * 添加模糊窗口
     *
     * @param x      窗口X的屏幕坐标
     * @param y      窗口Y的屏幕坐标
     * @param width  窗口宽度
     * @param height 窗口高度
     * @return 返回窗口id，后续移除等操作依赖此id
     */
    public int addBlurWindow(int x, int y, int width, int height) {
        return addBlurWindow(x, y, width, height, null);
    }

    /**
     * 添加模糊窗口
     *
     * @param x      窗口X的屏幕坐标
     * @param y      窗口Y的屏幕坐标
     * @param width  窗口宽度
     * @param height 窗口高度
     * @param view   关联的view
     * @return 返回窗口id，后续移除等操作依赖此id
     */
    public int addBlurWindow(int x, int y, int width, int height, View view) {
        if (context == null) {
            return -1;
        }

        BlurWindow blurWindow = containBlurWindow(view);
        if (blurWindow != null) {
            // 更新大小位置
            WindowManager.LayoutParams params = blurWindow.getAttributes();
            if (params.x != x || params.y != y - statusBarHeight || params.width != width || params.height != height) {
                params.x = x;
                params.y = y - statusBarHeight;
                params.width = width;
                params.height = height;
                blurWindow.setAttributes(params);
            }
            return -blurWindow.id;
        }

        blurWindow = obtain(context);
        if (blurWindow == null) {
            return -1;
        }
        blurWindow.view = view;
        showingWindowList.add(blurWindow);

        WindowManager.LayoutParams params = blurWindow.getAttributes();
        params.gravity = Gravity.START | Gravity.TOP;
        params.x = x;
        params.y = y - statusBarHeight;
        params.width = width;
        params.height = height;
        blurWindow.setAttributes(params);
        return blurWindow.id;
    }

    private BlurWindow containBlurWindow(View view) {
        for (BlurWindow blurWindow : showingWindowList) {
            if (blurWindow.view == view) {
                return blurWindow;
            }
        }
        return null;
    }

    public void removeBlurWindow(int id) {
        for (BlurWindow blurWindow : showingWindowList) {
            if (blurWindow.id == id) {
                if (showingWindowList.contains(blurWindow)) {
                    blurWindow.hide();
                    showingWindowList.remove(blurWindow);
                    recycleWindow(blurWindow);
                }
                return;
            }
        }
    }

    public void removeBlurWindow(View view) {
        for (BlurWindow blurWindow : showingWindowList) {
            if (blurWindow.view == null) {
                continue;
            }
            if (blurWindow.view == view) {
                WindowManager.LayoutParams params = blurWindow.getAttributes();
                params.width = 1;
                params.height = 1;
                blurWindow.setAttributes(params);
                showingWindowList.remove(blurWindow);
                recycleWindow(blurWindow);
                return;
            }
        }
    }

    public void removeBlurWindowByGroupId(int groupId) {
        for (BlurWindow blurWindow : showingWindowList) {
            if (blurWindow.groupId == groupId) {
                if (showingWindowList.contains(blurWindow)) {
                    blurWindow.hide();
                    showingWindowList.remove(blurWindow);
                    recycleWindow(blurWindow);
                }
                return;
            }
        }
    }

    @Nullable
    private BlurWindow obtain(Context context) {
        if (blurWindowPool == null) {
            return null;
        }
        BlurWindow window = blurWindowPool;
        blurWindowPool = blurWindowPool.next;
        window.next = null;
        return window;
    }

    private void recycleWindow(BlurWindow blurWindow) {
        blurWindow.next = blurWindowPool;
        blurWindowPool = blurWindow;
    }

    /**
     * 模糊窗口，模糊背景的绘制使用原生DecorView模糊背景的绘制方式
     */
    public static class BlurWindow {

        private static int ids;
        private final int id;
        private final WindowManager windowManager;
        private final View rootView;
        private int groupId;
        private BlurWindow next;
        private View view;
        private WindowManager.LayoutParams layoutParams;
        private boolean mCrossWindowBlurEnabled;

        /**
         * 当前模糊半径
         */
        private int mBackgroundBlurRadius;

        /**
         * 需要设置的模糊半径
         */
        private int mOriginalBackgroundBlurRadius;

        /**
         * 当前模糊背景圆角半径
         */
        private int mBackgroundBlurCorners;

        /**
         * 需要设置的模糊背景圆角半径
         */
        private int mOriginalBackgroundBlurCorners;

        private Consumer<Boolean> mCrossWindowBlurEnabledListener;

        private Drawable mBackgroundBlurDrawable;
        private final ViewTreeObserver.OnPreDrawListener mBackgroundBlurOnPreDrawListener = () -> {
            updateBackgroundBlurCorners();
            return true;
        };
        private Drawable mOriginalBackgroundDrawable;

        @RequiresApi(api = Build.VERSION_CODES.S)
        public BlurWindow(Context context, WindowManager windowManager, int blurRadius, int corners) {
            this.id = generateId();
            layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    PixelFormat.TRANSLUCENT);

            // 反射修改window私有flag，关闭位移动画
            try {
                Field privateFlagsField = layoutParams.getClass().getField("privateFlags");
                int privateFlags = (int) privateFlagsField.get(layoutParams);
                privateFlagsField.set(layoutParams, privateFlags | 0x00000040);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            rootView = new FrameLayout(context);
            rootView.setVisibility(View.GONE);

            rootView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    updateBackgroundBlurRadius();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {

                }
            });

            this.windowManager = windowManager;
            // 设置模糊
            mCrossWindowBlurEnabled = windowManager.isCrossWindowBlurEnabled();
            setBackgroundBlurRadius(blurRadius);
            setBackgroundBlurCorners(corners);

            windowManager.addView(rootView, layoutParams);
        }

        private static int generateId() {
            ids++;
            return ids;
        }

        public static int getIds() {
            return ids;
        }

        /**
         * 设置模糊背景半径
         *
         * @param blurRadius
         */
        @RequiresApi(api = Build.VERSION_CODES.S)
        private void setBackgroundBlurRadius(int blurRadius) {
            mOriginalBackgroundBlurRadius = blurRadius;
            if (blurRadius > 0) {
                if (mCrossWindowBlurEnabledListener == null) {
                    mCrossWindowBlurEnabledListener = aBoolean -> {
                        mCrossWindowBlurEnabled = aBoolean;
                        updateBackgroundBlurRadius();
                    };
                    windowManager.addCrossWindowBlurEnabledListener(mCrossWindowBlurEnabledListener);
                    rootView.getViewTreeObserver().addOnPreDrawListener(mBackgroundBlurOnPreDrawListener);
                } else {
                    updateBackgroundBlurRadius();
                }
            } else if (mCrossWindowBlurEnabledListener != null) {
                updateBackgroundBlurRadius();
                removeBackgroundBlurDrawable();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.S)
        private void removeBackgroundBlurDrawable() {

            if (mCrossWindowBlurEnabledListener != null) {
                windowManager.removeCrossWindowBlurEnabledListener(mCrossWindowBlurEnabledListener);
                mCrossWindowBlurEnabledListener = null;
            }
            rootView.getViewTreeObserver().removeOnPreDrawListener(mBackgroundBlurOnPreDrawListener);
            mBackgroundBlurDrawable = null;
            updateBackgroundDrawable();
        }

        private void setBackgroundBlurCorners(int corners) {
            mOriginalBackgroundBlurCorners = corners;
        }

        private void updateBackgroundBlurCorners() {
            if (mBackgroundBlurDrawable == null) {
                return;
            }
            if (mBackgroundBlurCorners == mOriginalBackgroundBlurCorners) {
                return;
            }

            float cornerRadius = 0;
            // If the blur radius is 0, the blur region won't be sent to surface flinger, so we don't
            // need to calculate the corner radius.
            if (mOriginalBackgroundBlurCorners != 0) {
                cornerRadius = mOriginalBackgroundBlurCorners;
            }
            ReflectUtils.reflect(mBackgroundBlurDrawable).method("setCornerRadius", cornerRadius);
            mBackgroundBlurCorners = mOriginalBackgroundBlurCorners;
        }

        private void updateBackgroundBlurRadius() {
            if (rootView.getParent() == null) {
                return;
            }
            mBackgroundBlurRadius = mCrossWindowBlurEnabled ? mOriginalBackgroundBlurRadius : 0;
            if (mBackgroundBlurDrawable == null && mBackgroundBlurRadius > 0) {
                mBackgroundBlurDrawable = createBackgroundBlurDrawable(rootView.getParent());
                updateBackgroundDrawable();
            }
            if (mBackgroundBlurDrawable != null) {
                ReflectUtils.reflect(mBackgroundBlurDrawable).method("setBlurRadius", mBackgroundBlurRadius);
            }
        }

        private Drawable createBackgroundBlurDrawable(Object viewRootImpl) {
            // 反射创建模糊drawable
            return ReflectUtils.reflect(viewRootImpl).method("createBackgroundBlurDrawable").get();
        }

        /**
         * 更新背景，会使用模糊背景和原始背景合成新背景
         */
        private void updateBackgroundDrawable() {
            Drawable destDrawable = rootView.getBackground();
            if (destDrawable instanceof LayerDrawable) {
                return;
            }

            if (mBackgroundBlurDrawable != null) {
                mOriginalBackgroundDrawable = destDrawable;
                destDrawable = new LayerDrawable(new Drawable[]{mBackgroundBlurDrawable, mOriginalBackgroundDrawable});
            }
            rootView.setBackground(destDrawable);
        }

        public void show() {
            rootView.setVisibility(View.VISIBLE);
        }

        public void hide() {
            rootView.setVisibility(View.GONE);
        }

        public void setFlags(int flags, int mask) {
            layoutParams.flags = (layoutParams.flags & ~mask) | (flags & mask);
        }

        public void addFlags(int flags) {
            setFlags(flags, flags);
        }

        public WindowManager.LayoutParams getAttributes() {
            return layoutParams;
        }

        public void setAttributes(WindowManager.LayoutParams params) {
            layoutParams = params;
            windowManager.updateViewLayout(rootView, layoutParams);
        }

        public int getId() {
            return id;
        }

        public int getGroupId() {
            return groupId;
        }

        public BlurWindow getNext() {
            return next;
        }

        public View getView() {
            return view;
        }
    }

}
