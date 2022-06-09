package com.leapmotor.utils.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leapmotor.lputils.utils.DialogUtils;
import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.lputils.utils.ToastUtils;
import com.leapmotor.utils.R;
import com.leapmotor.utils.utils.CarUtil;

public class SecoundActivity extends AppCompatActivity implements View.OnClickListener, DialogUtils.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secound);
        FindViewUtlis.findViewById(this, R.id.tvDialogMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialogByContext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialogAppointMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialogAppointVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvHeadDialogByContext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvHeadDialogAppointMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvHeadDialogAppointVice).setOnClickListener(this);

        FindViewUtlis.findViewById(this, R.id.tvToastMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastByContext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastAppointMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastAppointVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvJump).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tvDialogMain) {
            DialogUtils.show("Title", "确定", "取消", this);
        } else if (id == R.id.tvDialogByContext) {
            DialogUtils.show(this, "Title", "确定", "取消", this);
        } else if (id == R.id.tvDialogAppointMain) {
            DialogUtils.show(0, "Title", "确定", "取消", this);
        } else if (id == R.id.tvDialogAppointVice) {
            DialogUtils.show(1, "Title", "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogByContext) {
            DialogUtils.show(this, "HeadTitle", "Title", "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogAppointMain) {
            DialogUtils.show(0, "HeadTitle", "Title", "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogAppointVice) {
            DialogUtils.show(1, "HeadTitle", "Title", "确定", "取消", this);
        } else if (id == R.id.tvToastMain) {
            ToastUtils.showShort("Hello");
        } else if (id == R.id.tvToastByContext) {
            ToastUtils.showShort(this, "Hello");
        } else if (id == R.id.tvToastAppointMain) {
            ToastUtils.showShort(0, "Hello");
        } else if (id == R.id.tvToastAppointVice) {
            ToastUtils.showShort(1, "Hello");
        } else if (id == R.id.tvJump) {
            CarUtil.startDisplay(getApplicationContext(), new Intent(this, SecoundActivity.class), 1);
        }
    }

    @Override
    public boolean onLeftClick(View v) {
        return false;
    }

    @Override
    public boolean onRightClick(View v) {
        return true;
    }
}