package com.leapmotor.utils.ui;

import android.os.Bundle;
import android.view.Display;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.lputils.utils.ToastUtils;
import com.leapmotor.utils.R;

public class SecoundActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);
        FindViewUtlis.findViewById(this, R.id.tvToastMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastDynamic).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvToastMain) {
            ToastUtils.show(null, Display.DEFAULT_DISPLAY, "hello!", 40, false, false);
        } else if (id == R.id.tvToastVice) {
            ToastUtils.show(null, 1, "hello!", 40, false, false);
        } else if (id == R.id.tvToastDynamic) {
            ToastUtils.show(this, Display.INVALID_DISPLAY, "hello!", 40, false, false);
        } else if (id == R.id.tvBack) {
            finish();
        }
    }
}