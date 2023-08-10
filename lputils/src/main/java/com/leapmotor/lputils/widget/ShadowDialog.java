package com.leapmotor.lputils.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leapmotor.lputils.R;
import com.leapmotor.lputils.animation.OptAnimationLoader;
import com.leapmotor.lputils.annotation.ScreenModeType;
import com.leapmotor.lputils.content.ContextCompat;
import com.leapmotor.lputils.utils.BarUtils;
import com.leapmotor.lputils.utils.C11Util;
import com.leapmotor.lputils.utils.DialogUtils;
import com.leapmotor.lputils.utils.LpUtils;
import com.leapmotor.lputils.utils.ScreenUtils;
import com.leapmotor.lputils.utils.SettingsUtils;
import com.leapmotor.lputils.utils.ThemeUtils;


/**
 * <p>Example:
 * <pre><code>
 *  //参数enableAnimation为是否开启弹出框动画效果（此方法会在设置动画后失效）
 *  ShadowDialog shadowDialog = new ShadowDialog(this, true)
 *          //设置标题文字
 *          .setTitleText("吟诗一首")
 *          //设置内容文字
 *          .setContentText("君不见，黄河之水天上来，奔流到海不复回。")
 *          //设置左边按钮文字
 *          .setConfirmText("确定")
 *          //设置右边内容文字
 *          .setCancelText("取消")
 *          //设置弹出框弹出时动画
 *          .setAnimIn(R.anim.modal_in)
 *          //设置弹出框退出时动画
 *          .setAnimOut(R.anim.modal_out)
 *          //设置弹出框弹出和退出时动画
 *          //.setAnim(R.anim.modal_in,R.anim.modal_out)
 *          //设置是否启动动画（此方法会在设置动画后失效）
 *          //.setEnableAnimation(true)
 *          //设置是否主屏全屏居中显示（设置为false时弹框仅在主屏应用区域内居中显示）
 *          .setFullScreen(false)
 *          //设置是否沉浸式体验
 *          .setImmersion(true)
 *          //设置弹出框背景
 *          .setBgResid(R.mipmap.popup_bg_light)
 *          //设置内容区文字颜色
 *          .setTextColorRes(R.color.blue)
 *          //设置弹出框的WindowManager.LayoutParams
 *          //.setLayoutParams(null)
 *          //设置X偏移，即WindowManager.LayoutParams中的x
 *          //.setxOffset(0)
 *          //设置Y偏移，即WindowManager.LayoutParams中的y
 *          //.setyOffset(0)
 *          //设置Gravity，即WindowManager.LayoutParams中的gravity
 *          //.setGravity(Gravity.CENTER)
 *          //统一设置白天黑夜遮罩背景色
 *          //.setMaskResourceId(R.color.mask_popup_night)
 *          //单独设置白天和黑夜遮罩背景色
 *          .setMaskResourceId(R.drawable.mask_popup_light_radius40, R.drawable.mask_popup_night_radius40)
 *          //设置弹出框的Windows窗口类型
 *          //.setWindowType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
 *          //设置左边按钮点击事件
 *          .setConfirmClickListener((dialog, view) -> {
 *              ToastUtils.showShort(this, "点击确定");
 *              //返回true时点击后自动消失；返回false时点击后不自动消失
 *              return false;
 *          })
 *          //设置右边按钮点击事件
 *          .setCancelClickListener((dialog, view) -> {
 *              ToastUtils.showShort(this, "点击取消");
 *              //返回true时点击后自动消失；返回false时点击后不自动消失
 *              return true;
 *          });
 *  //设置是否点击弹框遮罩部分可消失
 *  shadowDialog.setCanceledOnTouchOutside(true);
 *  shadowDialog.show();
 *  //设置左边和右边两个按钮的点击事件
 *  shadowDialog.setOnClickListener(new DialogUtils.OnClickListener() {
 *      <code>@Override</code>
 *      public boolean onLeftClick(View v) {
 *          ToastUtils.showShort(MainActivity.this, "点击左边按钮");
 *          //返回true时点击后自动消失；返回false时点击后不自动消失
 *          return false;
 *      }
 *      <code>@Override</code>
 *      public boolean onRightClick(View v) {
 *          ToastUtils.showShort(MainActivity.this, "点击右边按钮");
 *          //返回true时点击后自动消失；返回false时点击后不自动消失
 *          return DialogUtils.OnClickListener.super.onRightClick(v);
 *      }
 *  });
 * </code></pre>
 * <p>
 * good programmer.
 *
 * @date : 2022/6/11 15:55
 * @author: futia
 * @email : futianyi1994@126.com
 * @description :
 */
public class ShadowDialog extends Dialog implements View.OnClickListener {
    public static final int LAYOUT_FULLSCREEN = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    public static final int DEFAULT_VALUE = -10000;
    public static final int DIALOG_WIDTH = LpUtils.getApp().getResources().getDimensionPixelOffset(R.dimen.x600);
    public static final int TXT_CONTENT_MAX_WIDTH = LpUtils.getApp().getResources().getDimensionPixelOffset(R.dimen.x504);
    public static final int TXT_CONTENT_MAX_HEIGHT_DISPLAY0 = LpUtils.getApp().getResources().getDimensionPixelOffset(R.dimen.x599);
    public static final int TXT_CONTENT_MAX_HEIGHT_DISPLAY1 = LpUtils.getApp().getResources().getDimensionPixelOffset(R.dimen.x419);
    public static final int TXT_CONTENT_HEAD_HEIGHT = LpUtils.getApp().getResources().getDimensionPixelOffset(R.dimen.y78);
    public static final int TXT_BG_SHADOW_PADING = 40;
    public static final int TXT_BG_SHADOW_ROUNDED_CORNER = 24;
    public static final int TXT_BG_SHADOW_BLUR_RADIUS = 25;
    private static final String TAG = "ShadowDialog";
    private static final int DEFAULT_DIALOG_SHOW_ANIM = R.anim.dialog_show;
    private static final int DEFAULT_DIALOG_HIDE_ANIM = R.anim.dialog_hide;

    private final int[] textSizeUnits;
    private final int[] textSizes;
    private final int[] textGravities;
    private AnimationSet animModalIn;
    private AnimationSet animModalOut;
    private Animation overlayOutAnim;
    private View decorView, dialogView;
    private FrameLayout flRoot;
    private LinearLayout llRoot;
    private TextView tvTitle, tvContent;
    private View vLine;
    private LinearLayout llConfirm;
    private TextView tvConfirm, tvCancel;
    private String title;
    private String content;
    private String confirm;
    private String cancel;
    private OnClickListener confirmClickListener, cancelClickListener;
    private DialogUtils.OnClickListener clickListener;
    private boolean closeFromCancel;
    private boolean isFullScreen = false, isImmersion = true;
    private boolean isCanceledOnTouchOutside = true, isDismissingAnimation = false;
    private boolean enableAnimation = true;
    @DrawableRes
    private int bgResid = 0;
    @ColorRes
    private int textColorRes = 0;
    private WindowManager.LayoutParams layoutParams = null;
    private int xOffset = DEFAULT_VALUE;
    private int yOffset = DEFAULT_VALUE;
    private int gravity = DEFAULT_VALUE;
    @DrawableRes
    private int maskResourceIdLight, maskResourceIdNight;
    private final ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            refresh();
        }
    };
    private int windowType = 0;

    public ShadowDialog(Context context) {
        this(context, true);
    }

    public ShadowDialog(Context context, boolean enableAnimation) {
        this(context, enableAnimation ? DEFAULT_DIALOG_SHOW_ANIM : 0, enableAnimation ? DEFAULT_DIALOG_HIDE_ANIM : 0);
    }

    public ShadowDialog(Context context, @AnimatorRes @AnimRes int animIn, @AnimatorRes @AnimRes int animOut) {
        super(context, R.style.shadow_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setAnim(animIn, animOut);
        textSizeUnits = new int[]{TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_PX, TypedValue.COMPLEX_UNIT_PX};
        textSizes = new int[]{getContext().getResources().getDimensionPixelSize(R.dimen.sizeL), getContext().getResources().getDimensionPixelSize(R.dimen.sizeL), getContext().getResources().getDimensionPixelSize(R.dimen.sizeL), getContext().getResources().getDimensionPixelSize(R.dimen.sizeL)};
        textGravities = new int[]{Gravity.CENTER, Gravity.START};
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shadow_dialog);
        decorView = getWindow().getDecorView();
        dialogView = decorView.findViewById(android.R.id.content);
        flRoot = findViewById(R.id.flRoot);
        llRoot = findViewById(R.id.llRoot);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        vLine = findViewById(R.id.vLine);
        llConfirm = findViewById(R.id.llConfirm);
        tvConfirm = findViewById(R.id.tvConfirm);
        tvCancel = findViewById(R.id.tvCancel);

        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int currentDisplayId = windowManager.getDefaultDisplay().getDisplayId();
        boolean isDefaultScreen = currentDisplayId == Display.DEFAULT_DISPLAY;
        int maskPopup = getMaskResourceId();
        int radiusMaskPopup = getRadiusMaskResourceId();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (isFullScreen) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                decorView.setSystemUiVisibility(LAYOUT_FULLSCREEN | decorView.getSystemUiVisibility());
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            int screenHeight = ScreenUtils.getScreenHeight();
            int statusBarHeight = BarUtils.getStatusBarHeight();
            int navBarHeight = BarUtils.getNavBarHeight();
            flRoot.setBackgroundResource(maskPopup);
            window.setDimAmount(0f);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, isImmersion ? WindowManager.LayoutParams.MATCH_PARENT : screenHeight - statusBarHeight - navBarHeight);
            window.setBackgroundDrawableResource(R.color.transparent);
            //设置背景之后设置才生效
            decorView.setPadding(0, 0, 0, 0);
            attributes.x = xOffset != DEFAULT_VALUE ? xOffset : 0;
            attributes.y = yOffset != DEFAULT_VALUE ? yOffset : isImmersion ? 0 : statusBarHeight;
            attributes.gravity = gravity != DEFAULT_VALUE ? gravity : isImmersion ? Gravity.NO_GRAVITY : Gravity.TOP;
        } else {
            if (isDefaultScreen) {
                flRoot.setBackgroundResource(radiusMaskPopup);
                window.setDimAmount(0f);
                window.setLayout(C11Util.WIDTH, C11Util.HEIGHT);
                window.setBackgroundDrawableResource(R.color.transparent);
                //设置背景之后设置才生效
                decorView.setPadding(0, 0, 0, 0);
                attributes.x = xOffset != DEFAULT_VALUE ? xOffset : C11Util.X_OFFSET;
                attributes.y = yOffset != DEFAULT_VALUE ? yOffset : C11Util.Y_TOP_OFFSET;
                attributes.gravity = gravity != DEFAULT_VALUE ? gravity : Gravity.START | Gravity.TOP;
            } else {
                flRoot.setBackgroundResource(maskPopup);
                window.setDimAmount(0f);
                window.setLayout(C11Util.WIDTH_VICE, WindowManager.LayoutParams.MATCH_PARENT);
                window.setBackgroundDrawableResource(R.color.transparent);
                //设置背景之后设置才生效
                decorView.setPadding(0, 0, 0, 0);
                attributes.x = xOffset != DEFAULT_VALUE ? xOffset : C11Util.X_OFFSET_VICE;
                attributes.y = yOffset != DEFAULT_VALUE ? yOffset : 0;
                attributes.gravity = gravity != DEFAULT_VALUE ? gravity : Gravity.END;
            }
            window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        }
        if (windowType != 0) {
            attributes.type = windowType;
        }
        if (layoutParams != null) {
            attributes.copyFrom(layoutParams);
        }
        window.setAttributes(attributes);

        if (isCanceledOnTouchOutside) {
            flRoot.setOnClickListener(this);
        }
        tvConfirm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        @ColorRes int textColorRes = this.textColorRes != 0 ? this.textColorRes : ThemeUtils.getTextPrimaryColor();
        llRoot.setBackgroundResource(bgResid != 0 ? bgResid : ThemeUtils.getbgPopup());
        llRoot.setPadding(0, 0, 0, 0);
        int tvContentMaxHeight = isDefaultScreen ? TXT_CONTENT_MAX_HEIGHT_DISPLAY0 : TXT_CONTENT_MAX_HEIGHT_DISPLAY1;
        tvContent.setMaxHeight(title == null ? tvContentMaxHeight : tvContentMaxHeight - TXT_CONTENT_HEAD_HEIGHT);
        tvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvContent.setTextColor(ContextCompat.getColor(getContext(), textColorRes));

        LinearLayout.LayoutParams tvTitleLayoutParams = (LinearLayout.LayoutParams) tvTitle.getLayoutParams();
        tvTitleLayoutParams.setMargins(TXT_BG_SHADOW_PADING, TXT_BG_SHADOW_PADING, TXT_BG_SHADOW_PADING, 0);
        LinearLayout.LayoutParams tvContentLayoutParams = (LinearLayout.LayoutParams) tvContent.getLayoutParams();
        tvContentLayoutParams.setMargins(TXT_BG_SHADOW_PADING, title == null ? TXT_BG_SHADOW_PADING : 0, TXT_BG_SHADOW_PADING, 0);
        LinearLayout.LayoutParams llConfirmLayoutParams = (LinearLayout.LayoutParams) llConfirm.getLayoutParams();
        llConfirmLayoutParams.setMargins(TXT_BG_SHADOW_PADING, 0, TXT_BG_SHADOW_PADING, TXT_BG_SHADOW_PADING);
        LinearLayout.LayoutParams vLineLayoutParams = (LinearLayout.LayoutParams) vLine.getLayoutParams();
        vLineLayoutParams.setMargins(TXT_BG_SHADOW_PADING, 0, TXT_BG_SHADOW_PADING, 0);

        if (title != null) {
            float titleWidth = tvTitle.getPaint().measureText(title);
            if (textGravities[0] == Gravity.NO_GRAVITY) {
                textGravities[0] = titleWidth >= TXT_CONTENT_MAX_WIDTH ? Gravity.START : Gravity.CENTER;
            }
        }
        float contentWidth = tvContent.getPaint().measureText(content);
        if (textGravities[1] == Gravity.NO_GRAVITY) {
            textGravities[1] = contentWidth >= TXT_CONTENT_MAX_WIDTH ? Gravity.START : Gravity.CENTER;
        }
        tvTitle.setSelected(true);
        tvConfirm.setSelected(true);
        tvCancel.setSelected(true);
        tvTitle.setTextColor(ContextCompat.getColor(getContext(), textColorRes));
        vLine.setBackgroundResource(ThemeUtils.getLineColor());
        tvConfirm.setTextColor(ContextCompat.getColor(getContext(), ThemeUtils.getBtnTextHighlightColor()));
        tvCancel.setTextColor(ContextCompat.getColor(getContext(), textColorRes));

        setTitleText(title, textSizeUnits[0], textSizes[0], textGravities[0]);
        setContentText(content, textSizeUnits[1], textSizes[1], textGravities[1]);
        setConfirmText(confirm, textSizeUnits[2], textSizes[2]);
        setCancelText(cancel, textSizeUnits[3], textSizes[3]);

        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor(SettingsUtils.SCREEN_MODE), true, observer);
    }

    @Override
    protected void onStart() {
        isDismissingAnimation = false;
        if (enableAnimation && animModalIn != null) {
            llRoot.startAnimation(animModalIn);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (observer != null) {
            getContext().getContentResolver().unregisterContentObserver(observer);
        }
    }

    @Override
    public void cancel() {
        dismissWithAnimation(true);
    }

    @Override
    public void dismiss() {
        dismissWithAnimation(false);
    }

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
        isCanceledOnTouchOutside = cancel;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.flRoot) {
            dismissWithAnimation(false);
        } else if (v.getId() == R.id.tvCancel) {
            if (cancelClickListener != null) {
                if (cancelClickListener.onClick(ShadowDialog.this, v)) {
                    dismissWithAnimation(false);
                }
            } else {
                if (clickListener != null) {
                    if (clickListener.onRightClick(v)) {
                        dismissWithAnimation(false);
                    }
                } else {
                    dismissWithAnimation(false);
                }
            }
        } else if (v.getId() == R.id.tvConfirm) {
            if (confirmClickListener != null) {
                if (confirmClickListener.onClick(ShadowDialog.this, v)) {
                    dismissWithAnimation(false);
                }
            } else {
                if (clickListener != null) {
                    if (clickListener.onLeftClick(v)) {
                        dismissWithAnimation(false);
                    }
                } else {
                    dismissWithAnimation(false);
                }
            }
        }
    }

    public ShadowDialog setAnimIn(@AnimatorRes @AnimRes int animIn) {
        return setAnim(animIn, DEFAULT_DIALOG_HIDE_ANIM);
    }

    public ShadowDialog setAnimOut(@AnimatorRes @AnimRes int animOut) {
        return setAnim(DEFAULT_DIALOG_SHOW_ANIM, animOut);
    }

    public ShadowDialog setAnim(@AnimatorRes @AnimRes int animIn, @AnimatorRes @AnimRes int animOut) {
        enableAnimation = animIn != 0 || animOut != 0;
        if (animIn != 0) {
            animModalIn = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), animIn);
        }
        if (animOut != 0) {
            animModalOut = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), animOut);
            animModalOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    isDismissingAnimation = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isDismissingAnimation = false;
                    dialogView.setVisibility(View.GONE);
                    dialogView.post(() -> {
                        if (closeFromCancel) {
                            ShadowDialog.super.cancel();
                        } else {
                            ShadowDialog.super.dismiss();
                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            overlayOutAnim = new Animation() {
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    float alpha = 1 - interpolatedTime;
                    // FIX: 2023/2/21 Fty: 操作window会导致状态栏黑一下
                    /*WindowManager.LayoutParams wlp = getWindow().getAttributes();
                    wlp.alpha = alpha;
                    getWindow().setAttributes(wlp);*/
                    flRoot.setAlpha(alpha);
                }
            };
            overlayOutAnim.setDuration(animModalOut.getDuration());
        }
        return this;
    }

    public ShadowDialog setTitleText(@Nullable String text) {
        return setTitleText(text, -1, -1);
    }

    public ShadowDialog setTitleText(@Nullable String text, int unit, float size) {
        return setTitleText(text, unit, size, Gravity.NO_GRAVITY);
    }

    public ShadowDialog setTitleText(@Nullable String text, int unit, float size, int gravity) {
        title = text;
        textGravities[0] = gravity;
        if (unit >= 0 && size >= 0) {
            textSizeUnits[0] = unit;
            textSizes[0] = (int) size;
        }
        if (tvTitle != null) {
            tvTitle.setText(title);
            tvTitle.setGravity(textGravities[0]);
            tvTitle.setVisibility(title == null ? View.GONE : View.VISIBLE);
            if (textSizes[0] >= 0) {
                tvTitle.setTextSize(textSizeUnits[0], textSizes[0]);
            }
        }
        return this;
    }

    public ShadowDialog setContentText(@NonNull String text) {
        return setContentText(text, -1, -1);
    }

    public ShadowDialog setContentText(@NonNull String text, int unit, float size) {
        return setContentText(text, unit, size, Gravity.NO_GRAVITY);
    }

    public ShadowDialog setContentText(@NonNull String text, int unit, float size, int gravity) {
        content = text;
        textGravities[1] = gravity;
        if (unit >= 0 && size >= 0) {
            textSizeUnits[1] = unit;
            textSizes[1] = (int) size;
        }
        if (tvContent != null) {
            tvContent.setText(content);
            tvContent.setGravity(textGravities[1]);
            if (textSizes[1] >= 0) {
                tvContent.setTextSize(textSizeUnits[1], textSizes[1]);
            }
        }
        return this;
    }

    public ShadowDialog setConfirmText(@Nullable String text) {
        return setConfirmText(text, -1, -1);
    }

    public ShadowDialog setConfirmText(@Nullable String text, int unit, float size) {
        confirm = text;
        if (unit >= 0 && size >= 0) {
            textSizeUnits[2] = unit;
            textSizes[2] = (int) size;
        }
        if (tvConfirm != null) {
            tvConfirm.setText(confirm);
            tvConfirm.setVisibility(confirm == null ? View.GONE : View.VISIBLE);
            if (textSizes[2] >= 0) {
                tvConfirm.setTextSize(textSizeUnits[2], textSizes[2]);
            }
        }
        return this;
    }

    public ShadowDialog setCancelText(@Nullable String text) {
        return setCancelText(text, -1, -1);
    }

    public ShadowDialog setCancelText(@Nullable String text, int unit, float size) {
        cancel = text;
        if (unit >= 0 && size >= 0) {
            textSizeUnits[3] = unit;
            textSizes[3] = (int) size;
        }
        if (tvCancel != null) {
            tvCancel.setText(cancel);
            tvCancel.setVisibility(cancel == null ? View.GONE : View.VISIBLE);
            if (textSizes[3] >= 0) {
                tvCancel.setTextSize(textSizeUnits[3], textSizes[3]);
            }
        }
        return this;
    }

    public ShadowDialog setWindowType(int windowType) {
        this.windowType = windowType;
        return this;
    }

    public int getBgResid() {
        return bgResid;
    }

    public ShadowDialog setBgResid(int bgResid) {
        this.bgResid = bgResid;
        return this;
    }

    public int getTextColorRes() {
        return textColorRes;
    }

    public ShadowDialog setTextColorRes(int textColorRes) {
        this.textColorRes = textColorRes;
        return this;
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    public ShadowDialog setLayoutParams(WindowManager.LayoutParams layoutParams) {
        this.layoutParams = layoutParams;
        return this;
    }

    public int getxOffset() {
        return xOffset;
    }

    public ShadowDialog setxOffset(int xOffset) {
        this.xOffset = xOffset;
        return this;
    }

    public int getyOffset() {
        return yOffset;
    }

    public ShadowDialog setyOffset(int yOffset) {
        this.yOffset = yOffset;
        return this;
    }

    public int getGravity() {
        return gravity;
    }

    public ShadowDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    @DrawableRes
    private int getRadiusMaskResourceId() {
        if (maskResourceIdLight == 0 && maskResourceIdNight == 0) {
            return ThemeUtils.getRadiusMaskPopup();
        }
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return maskResourceIdNight;
            case ScreenModeType.DAY:
                return maskResourceIdLight;
            default:
                return maskResourceIdNight;
        }
    }

    @DrawableRes
    private int getMaskResourceId() {
        if (maskResourceIdLight == 0 && maskResourceIdNight == 0) {
            return ThemeUtils.getMaskPopup();
        }
        switch (SettingsUtils.screenMode()) {
            case ScreenModeType.NIGHT:
                return maskResourceIdNight;
            case ScreenModeType.DAY:
                return maskResourceIdLight;
            default:
                return maskResourceIdNight;
        }
    }

    public ShadowDialog setMaskResourceId(@ColorRes int maskResourceId) {
        return setMaskResourceId(maskResourceId, maskResourceId);

    }

    public ShadowDialog setMaskResourceId(@DrawableRes int maskResourceIdLight, @DrawableRes int maskResourceIdNight) {
        this.maskResourceIdLight = maskResourceIdLight;
        this.maskResourceIdNight = maskResourceIdNight;
        return this;
    }

    public ShadowDialog setEnableAnimation(boolean enableAnimation) {
        this.enableAnimation = enableAnimation;
        return this;
    }

    public ShadowDialog setFullScreen(boolean isFullScreen) {
        return setFullScreen(isFullScreen, true);
    }

    public ShadowDialog setFullScreen(boolean isFullScreen, boolean isImmersion) {
        this.isFullScreen = isFullScreen;
        this.isImmersion = isImmersion;
        return this;
    }

    public boolean isImmersion() {
        return isImmersion;
    }

    public ShadowDialog setImmersion(boolean isImmersion) {
        this.isImmersion = isImmersion;
        return this;
    }

    public void setOnClickListener(@Nullable DialogUtils.OnClickListener listener) {
        clickListener = listener;
    }

    public ShadowDialog setConfirmClickListener(@Nullable OnClickListener listener) {
        confirmClickListener = listener;
        return this;
    }

    public ShadowDialog setCancelClickListener(@Nullable OnClickListener listener) {
        cancelClickListener = listener;
        return this;
    }

    private void dismissWithAnimation(boolean fromCancel) {
        if (enableAnimation) {
            closeFromCancel = fromCancel;
            if (animModalOut != null) {
                if (!isDismissingAnimation) {
                    llRoot.startAnimation(animModalOut);
                } else {
                    Log.w(TAG, "dismissWithAnimation : isDismissingAnimation");
                }
            }
            if (overlayOutAnim != null) {
                if (!isDismissingAnimation) {
                    tvConfirm.startAnimation(overlayOutAnim);
                }
            }
        } else {
            if (fromCancel) {
                super.cancel();
            } else {
                super.dismiss();
            }
        }
    }

    public void refresh() {
        if (isShowing()) {
            int textPrimaryColor = ContextCompat.getColor(getContext(), ThemeUtils.getTextPrimaryColor());
            flRoot.setBackgroundResource(getMaskResourceId());
            llRoot.setBackgroundResource(ThemeUtils.getbgPopup());
            llRoot.setPadding(0, 0, 0, 0);
            tvTitle.setTextColor(textPrimaryColor);
            tvContent.setTextColor(textPrimaryColor);
            vLine.setBackgroundResource(ThemeUtils.getLineColor());
            tvConfirm.setTextColor(ContextCompat.getColor(getContext(), ThemeUtils.getBtnTextHighlightColor()));
            tvCancel.setTextColor(textPrimaryColor);
            Log.i(TAG, "refresh shadow dialog theme success !");
        }
    }

    public interface OnClickListener {
        boolean onClick(ShadowDialog dialog, View view);
    }
}