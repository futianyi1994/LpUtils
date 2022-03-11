package com.leapmotor.lputils.utils;

import android.app.Activity;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * good programmer.
 *
 * @date : 2020-09-09 9:56
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class C11Util {

    public static final int X_OFFSET = 280;
    public static final int Y_TOP_OFFSET = 80;
    public static final int Y_BOTTOM_OFFSET = 120;
    public static final int WIDTH = 1640;
    public static final int HEIGHT = 880;

    public static void windowFlag(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.dimAmount = 0.0f;
        attributes.x = X_OFFSET;
        attributes.y = Y_TOP_OFFSET;
        attributes.width = WIDTH;
        attributes.height = HEIGHT;
        attributes.gravity = Gravity.TOP;
        activity.getWindow().setAttributes(attributes);
        //BarUtils.setNavBarVisibility(activity, false);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

}
