package com.leapmotor.lputils.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * good programmer.
 *
 * @date : 2022-06-10 10:33
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
@SuppressLint("AppCompatCustomView")
public class MTextView extends TextView {
    public MTextView(Context context) {
        super(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
