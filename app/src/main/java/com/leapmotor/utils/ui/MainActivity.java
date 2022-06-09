package com.leapmotor.utils.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leapmotor.lputils.utils.C11Util;
import com.leapmotor.lputils.utils.DialogUtils;
import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.lputils.utils.ToastUtils;
import com.leapmotor.utils.R;
import com.leapmotor.utils.utils.CarUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogUtils.OnClickListener {

    public static final String TEST_TITLE = "君不见，黄河之水天上来，奔流到海不复回。\n"
            + "\n"
            + "君不见，高堂明镜悲白发，朝如青丝暮成雪。\n"
            + "\n"
            + "人生得意须尽欢，莫使金樽空对月。\n"
            + "\n"
            + "天生我材必有用，千金散尽还复来。\n"
            + "\n"
            + "烹羊宰牛且为乐，会须一饮三百杯。\n"
            + "\n"
            + "岑夫子，丹丘生，将进酒，杯莫停。\n"
            + "\n"
            + "与君歌一曲，请君为我倾耳听。\n"
            + "\n"
            + "钟鼓馔玉不足贵，但愿长醉不愿醒。\n"
            + "\n"
            + "古来圣贤皆寂寞，惟有饮者留其名。\n"
            + "\n"
            + "陈王昔时宴平乐，斗酒十千恣欢谑。\n"
            + "\n"
            + "主人何为言少钱，径须沽取对君酌。\n"
            + "\n"
            + "五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。";
    public static final String TEST_TITLE2 = "君不见，黄河之水天上来，奔流到海不复回。"
            + "君不见，高堂明镜悲白发，朝如青丝暮成雪。"
            + "人生得意须尽欢，莫使金樽空对月。"
            + "天生我材必有用，千金散尽还复来。"
            + "烹羊宰牛且为乐，会须一饮三百杯。"
            + "岑夫子，丹丘生，将进酒，杯莫停。"
            + "与君歌一曲，请君为我倾耳听。"
            + "钟鼓馔玉不足贵，但愿长醉不愿醒。"
            + "古来圣贤皆寂寞，惟有饮者留其名。"
            + "陈王昔时宴平乐，斗酒十千恣欢谑。"
            + "主人何为言少钱，径须沽取对君酌。"
            + "五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        C11Util.windowFlag(this);
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
            DialogUtils.show(TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvDialogByContext) {
            DialogUtils.show(this, TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvDialogAppointMain) {
            DialogUtils.show(0, TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvDialogAppointVice) {
            DialogUtils.show(1, TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogByContext) {
            DialogUtils.show(this, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogAppointMain) {
            DialogUtils.show(0, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogAppointVice) {
            DialogUtils.show(1, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvToastMain) {
            ToastUtils.showShort(TEST_TITLE2);
        } else if (id == R.id.tvToastByContext) {
            ToastUtils.showShort(this, TEST_TITLE2);
        } else if (id == R.id.tvToastAppointMain) {
            ToastUtils.showShort(0, TEST_TITLE2);
        } else if (id == R.id.tvToastAppointVice) {
            ToastUtils.showShort(1, TEST_TITLE2);
        } else if (id == R.id.tvJump) {
            CarUtil.startDisplay(getApplicationContext(), new Intent(this, SecoundActivity.class), 1);
        }
    }

    @Override
    public boolean onLeftClick(View v) {
        return true;
    }

    @Override
    public boolean onRightClick(View v) {
        return true;
    }
}