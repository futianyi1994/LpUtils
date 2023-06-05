package com.leapmotor.lputils.widget.blur;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.leapmotor.lputils.R;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * good programmer.
 *
 * @date : 2023/5/30 20:08
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ViewAddBlurWindow {
    private final ArrayList<View> list = new ArrayList<>();
    private final View mDecorView;
    private final BlurWindowController controller;

    public ViewAddBlurWindow(Context context, WindowManager windowManager, View decorView, int blurRadius, int corners) {
        mDecorView = decorView;
        controller = new BlurWindowController(context, windowManager, blurRadius, corners);
    }

    /**
     * 遍历view树
     */
    private void traverseViewGroup(View view) {
        if (null == view) {
            return;
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                isNeedAddBlurWindow(child);
                traverseViewGroup(child);
            }
        } else {
            isNeedAddBlurWindow(view);
        }
    }

    /**
     * 通过View的tag值判断是否需要添加窗口模糊
     */
    private void isNeedAddBlurWindow(View view) {
        if (view.getTag(R.id.blur) != null && view.isShown()) {
            if (!list.contains(view)) {
                view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {

                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        list.remove(v);
                        controller.removeBlurWindow(v);
                    }
                });
                list.add(view);
            }
        }
    }

    /**
     * 监听布局变化
     */
    public void startListener() {
        mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            traverseViewGroup(mDecorView.findViewById(android.R.id.content));
            Iterator<View> iterator = list.iterator();
            while (iterator.hasNext()) {
                View view = iterator.next();
                if (view.isShown()) {
                    int[] location = new int[2];
                    view.getLocationOnScreen(location);
                    //view距离 屏幕左边的距离（即x轴方向）
                    int x = location[0];
                    //view距离 屏幕顶边的距离（即y轴方向）
                    int y = location[1];
                    int shadowWidth = Integer.parseInt((String) view.getTag(R.id.blur));
                    controller.addBlurWindow(x + shadowWidth, y + shadowWidth, view.getWidth() - shadowWidth * 2, view.getHeight() - shadowWidth * 2, view);
                } else {
                    controller.removeBlurWindow(view);
                    iterator.remove();
                }
            }
        });
    }

}
