package com.leapmotor.utils.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leapmotor.lputils.utils.C11Util;
import com.leapmotor.lputils.utils.DialogUtils;
import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.lputils.utils.ToastUtils;
import com.leapmotor.utils.R;
import com.leapmotor.utils.utils.CarUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogUtils.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        C11Util.windowFlag(this);
        FindViewUtlis.findViewById(this, R.id.tvToastMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastDynamic).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialog).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvJump).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvToastMain) {
            ToastUtils.showShort(Display.DEFAULT_DISPLAY, "hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!hello!");
        } else if (id == R.id.tvToastVice) {
            ToastUtils.showShort(1, "hello!");
        } else if (id == R.id.tvToastDynamic) {
            ToastUtils.showShort(this, "hello!");
        } else if (id == R.id.tvDialog) {
            DialogUtils.show(this, null, "Title", "确定", null);
            DialogUtils.setOnClickListener(this);
        } else if (id == R.id.tvJump) {
            CarUtil.startDisplay(getApplicationContext(), new Intent(this, SecoundActivity.class), 1);
        }
    }

    @Override
    public void onLeftClick(View v) {

    }

    @Override
    public void onRightClick(View v) {

    }
}