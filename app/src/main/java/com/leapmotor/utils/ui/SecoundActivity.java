package com.leapmotor.utils.ui;

import android.os.Bundle;
import android.view.Display;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leapmotor.lputils.utils.DialogUtils;
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
        FindViewUtlis.findViewById(this, R.id.tvDialog).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvToastMain) {
            ToastUtils.showShort(Display.DEFAULT_DISPLAY, "hello!");
        } else if (id == R.id.tvToastVice) {
            ToastUtils.showShort(1, "hello!");
        } else if (id == R.id.tvToastDynamic) {
            ToastUtils.showShort(this, "hello!");
        } else if (id == R.id.tvDialog) {
            DialogUtils.show(this, "TitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitleTitle", "确定", "取消");
        } else if (id == R.id.tvBack) {
            finish();
        }
    }
}