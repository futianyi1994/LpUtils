package com.leapmotor.utils.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.leapmotor.lputils.utils.DialogUtils;
import com.leapmotor.lputils.utils.FindViewUtlis;
import com.leapmotor.lputils.utils.ToastUtils;
import com.leapmotor.lputils.widget.ShadowDialog;
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
        FindViewUtlis.findViewById(this, R.id.ShowShadowDialog).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvFullDialogMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvFullDialogVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialogMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialogByContext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialogAppointMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvDialogAppointVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvHeadDialogByContext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvHeadDialogAppointMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvHeadDialogAppointVice).setOnClickListener(this);

        FindViewUtlis.findViewById(this, R.id.tvFullToastMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvFullToastVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastByContext).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastAppointMain).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvToastAppointVice).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvJump).setOnClickListener(this);
        FindViewUtlis.findViewById(this, R.id.tvFinish).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ShowShadowDialog) {
            //参数enableAnimation为是否开启弹出框动画效果（此方法会在设置动画后失效）
            ShadowDialog shadowDialog = new ShadowDialog(this, true)
                    //设置标题文字
                    .setTitleText("吟诗一首")
                    //设置内容文字
                    .setContentText(TEST_TITLE)
                    //设置左边按钮文字
                    .setConfirmText("确定")
                    //设置右边内容文字
                    .setCancelText("取消")
                    //设置弹出框弹出时动画
                    .setAnimIn(R.anim.modal_in)
                    //设置弹出框退出时动画
                    .setAnimOut(R.anim.modal_out)
                    //设置弹出框弹出和退出时动画
                    //.setAnim(R.anim.modal_in,R.anim.modal_out)
                    //设置是否启动动画（此方法会在设置动画后失效）
                    //.setEnableAnimation(true)
                    //设置是否主屏全屏居中显示（设置为false时弹框仅在主屏应用区域内居中显示）
                    .setFullScreen(true)
                    //设置弹出框背景
                    .setBgResid(R.mipmap.popup_bg_light)
                    //设置内容区文字颜色
                    .setTextColorRes(R.color.blue)
                    //设置弹出框的WindowManager.LayoutParams
                    //.setLayoutParams(null)
                    //设置X偏移，即WindowManager.LayoutParams中的x
                    //.setxOffset(0)
                    //设置Y偏移，即WindowManager.LayoutParams中的y
                    //.setyOffset(0)
                    //设置Gravity，即WindowManager.LayoutParams中的gravity
                    //.setGravity(Gravity.CENTER)
                    //统一设置白天黑夜遮罩背景色
                    //.setMaskResourceId(R.color.mask_popup_night)
                    //单独设置白天和黑夜遮罩背景色
                    .setMaskResourceId(R.drawable.mask_popup_light_radius40, R.drawable.mask_popup_night_radius40)
                    //设置弹出框的Windows窗口类型
                    //.setWindowType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                    //设置左边按钮点击事件
                    .setConfirmClickListener((dialog, view) -> {
                        ToastUtils.showShort(this, "点击确定");
                        //返回true时点击后自动消失；返回false时点击后不自动消失
                        return false;
                    })
                    //设置右边按钮点击事件
                    .setCancelClickListener((dialog, view) -> {
                        ToastUtils.showShort(this, "点击取消");
                        //返回true时点击后自动消失；返回false时点击后不自动消失
                        return true;
                    });
            //设置是否点击弹框遮罩部分可消失
            shadowDialog.setCanceledOnTouchOutside(true);
            shadowDialog.show();
            //设置左边和右边两个按钮的点击事件
            shadowDialog.setOnClickListener(new DialogUtils.OnClickListener() {
                @Override
                public boolean onLeftClick(View v) {
                    ToastUtils.showShort(MainActivity.this, "点击左边按钮");
                    //返回true时点击后自动消失；返回false时点击后不自动消失
                    return false;
                }

                @Override
                public boolean onRightClick(View v) {
                    ToastUtils.showShort(MainActivity.this, "点击右边按钮");
                    //返回true时点击后自动消失；返回false时点击后不自动消失
                    return DialogUtils.OnClickListener.super.onRightClick(v);
                }
            });
        } else if (id == R.id.tvFullDialogMain) {
            DialogUtils.showFullScreen(0, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvFullDialogVice) {
            DialogUtils.showFullScreen(1, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvDialogMain) {
            DialogUtils.show(TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvDialogByContext) {
            DialogUtils.show(this, TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvDialogAppointMain) {
            DialogUtils.clearUsedConfig();
            DialogUtils
                    .getConfig()
                    .setFullScreen(true)
                    .setFullScreenVice(true)
                    .setTextColorRes(R.color.green)
                    .setGravity(Gravity.START | Gravity.TOP)
                    .setxOffset(0)
                    .setyOffset(0)
                    .create();
            DialogUtils.show(0, TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvDialogAppointVice) {
            DialogUtils.show(1, TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogByContext) {
            DialogUtils.show(this, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogAppointMain) {
            DialogUtils.clearUsedConfig();
            DialogUtils
                    .getConfig()
                    .setFullScreen(false)
                    .setFullScreenVice(false)
                    .setTextColorRes(R.color.red)
                    .setGravity(Gravity.BOTTOM)
                    .setxOffsetVice(0)
                    .setyOffsetVice(0)
                    .create();
            DialogUtils.show(0, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvHeadDialogAppointVice) {
            DialogUtils.show(1, "吟诗一首", TEST_TITLE, "确定", "取消", this);
        } else if (id == R.id.tvFullToastMain) {
            ToastUtils
                    .getConfig()
                    .setTextColorRes(R.color.green)
                    .setHideViewBeforeShow(true)
                    .setyOffset(500)
                    .setFullScreen(true)
                    .create();
            ToastUtils.showFullScreen(0, "Hello", true);
        } else if (id == R.id.tvFullToastVice) {
            ToastUtils.showFullScreen(1, "Hello", true);
        } else if (id == R.id.tvToastMain) {
            ToastUtils.clearUsedConfig();
            ToastUtils
                    .getConfig()
                    .setTextColorRes(R.color.red)
                    .setHideViewBeforeShow(true)
                    .setFullScreen(false)
                    .setyOffset(600)
                    .create();
            ToastUtils.showShort(TEST_TITLE2);
        } else if (id == R.id.tvToastByContext) {
            ToastUtils.showShort(this, TEST_TITLE2);
        } else if (id == R.id.tvToastAppointMain) {
            ToastUtils.showShort(0, TEST_TITLE2);
        } else if (id == R.id.tvToastAppointVice) {
            ToastUtils.showShort(1, TEST_TITLE2);
        } else if (id == R.id.tvJump) {
            CarUtil.startDisplay(getApplicationContext(), new Intent(this, SecoundActivity.class), 1);
        } else if (id == R.id.tvKotlinTest) {
            Test test = new Test();
            test.vars(1, 2, 3, 4, 5, 6);
            test.convert();
            String tag1 = Test.TAG1;
            String tag2 = Test.TAG2;
            Test.Companion.test();
            Test.test1();
            Test.Companion.getTAG3();
            Test.Companion.getTAG4();
            Test.getTAG3();
        } else if (id == R.id.tvFinish) {
            finish();
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