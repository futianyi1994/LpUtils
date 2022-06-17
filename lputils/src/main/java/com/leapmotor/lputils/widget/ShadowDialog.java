package com.leapmotor.lputils.widget;

import android.app.Dialog;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.leapmotor.lputils.R;
import com.leapmotor.lputils.animation.OptAnimationLoader;
import com.leapmotor.lputils.annotation.ScreenModeType;
import com.leapmotor.lputils.content.ContextCompat;
import com.leapmotor.lputils.utils.C11Util;
import com.leapmotor.lputils.utils.DialogUtils;
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
 *          //设置是否主屏全屏居中显示（设置为true时弹框仅在主屏应用区域内居中显示）
 *          .setFullScreen(false)
 *          //统一设置白天黑夜遮罩背景色
 *          //.setMaskResourceId(R.color.mask_popup_night)
 *          //单独设置白天和黑夜遮罩背景色
 *          .setMaskResourceId(R.color.mask_popup_light, R.color.mask_popup_night)
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
 *      @Override
 *      public boolean onLeftClick(View v) {
 *          ToastUtils.showShort(MainActivity.this, "点击左边按钮");
 *          //返回true时点击后自动消失；返回false时点击后不自动消失
 *          return false;
 *      }
 *      @Override
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
    public static final int DIALOG_WIDTH = 720;
    public static final int TXT_CONTENT_MAX_WIDTH = 560;
    public static final int TXT_CONTENT_MAX_HEIGHT_DISPLAY0 = 720;
    public static final int TXT_CONTENT_MAX_HEIGHT_DISPLAY1 = 560;
    public static final int TXT_CONTENT_HEAD_HEIGHT = 95;
    public static final int TXT_BG_SHADOW_PADING = 40;
    private static final String TAG = "ShadowDialog";
    private AnimationSet animModalIn;
    private AnimationSet animModalOut;
    private Animation overlayOutAnim;
    private View dialogView;
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
    private boolean isFullScreen = false;
    private boolean isCanceledOnTouchOutside = true;
    private boolean enableAnimation = true;
    @ColorRes
    private int maskResourceIdLight, maskResourceIdNight;
    private final ContentObserver observer = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            refresh();
        }
    };

    public ShadowDialog(Context context) {
        this(context, true);
    }

    public ShadowDialog(Context context, boolean enableAnimation) {
        this(context, enableAnimation ? R.anim.modal_in : 0, enableAnimation ? R.anim.modal_out : 0);
    }

    public ShadowDialog(Context context, @AnimatorRes @AnimRes int animIn, @AnimatorRes @AnimRes int animOut) {
        super(context, R.style.shadow_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        setAnim(animIn, animOut);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shadow_dialog);
        dialogView = getWindow().getDecorView().findViewById(android.R.id.content);
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
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (isFullScreen) {
            flRoot.setBackgroundResource(maskPopup);
            window.setDimAmount(0f);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawableResource(R.color.transparent);
            //设置背景之后设置才生效
            window.getDecorView().setPadding(0, 0, 0, 0);
        } else {
            if (isDefaultScreen) {
                flRoot.setBackgroundResource(maskPopup);
                window.setDimAmount(0f);
                window.setLayout(C11Util.WIDTH, C11Util.HEIGHT);
                window.setBackgroundDrawableResource(R.color.transparent);
                //设置背景之后设置才生效
                window.getDecorView().setPadding(0, 0, 0, 0);
                attributes.x = C11Util.X_OFFSET;
                attributes.y = C11Util.Y_TOP_OFFSET;
                attributes.gravity = Gravity.START | Gravity.TOP;
                attributes.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            } else {
                flRoot.setBackgroundResource(maskPopup);
                window.setDimAmount(0f);
                window.setLayout(C11Util.WIDTH_VICE, WindowManager.LayoutParams.MATCH_PARENT);
                window.setBackgroundDrawableResource(R.color.transparent);
                //设置背景之后设置才生效
                window.getDecorView().setPadding(0, 0, 0, 0);
                attributes.x = C11Util.X_OFFSET_VICE;
                attributes.gravity = Gravity.END;
                attributes.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            }
        }
        window.setAttributes(attributes);

        if (isCanceledOnTouchOutside) {
            flRoot.setOnClickListener(this);
        }
        tvConfirm.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        int textColorRes = ThemeUtils.getTextPrimaryColor();
        llRoot.setBackgroundResource(ThemeUtils.getBgToastOrDialog());
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

        float contentWidth = tvContent.getPaint().measureText(content);
        if (contentWidth >= TXT_CONTENT_MAX_WIDTH) {
            tvContent.setGravity(Gravity.START);
        } else {
            tvContent.setGravity(Gravity.CENTER);
        }
        tvTitle.setSelected(true);
        tvConfirm.setSelected(true);
        tvCancel.setSelected(true);
        tvTitle.setTextColor(ContextCompat.getColor(getContext(), textColorRes));
        vLine.setBackgroundResource(ThemeUtils.getLineColor());
        tvConfirm.setTextColor(ContextCompat.getColor(getContext(), ThemeUtils.getBtnTextHighlightColor()));
        tvCancel.setTextColor(ContextCompat.getColor(getContext(), textColorRes));

        setTitleText(title);
        setContentText(content);
        setCancelText(cancel);
        setConfirmText(confirm);

        getContext().getContentResolver().registerContentObserver(Settings.Global.getUriFor(SettingsUtils.SCREEN_MODE), true, observer);
    }

    @Override
    protected void onStart() {
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
        return setAnim(animIn, R.anim.modal_out);
    }

    public ShadowDialog setAnimOut(@AnimatorRes @AnimRes int animOut) {
        return setAnim(R.anim.modal_in, animOut);
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
                }

                @Override
                public void onAnimationEnd(Animation animation) {
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
                    WindowManager.LayoutParams wlp = getWindow().getAttributes();
                    wlp.alpha = 1 - interpolatedTime;
                    getWindow().setAttributes(wlp);
                }
            };
            overlayOutAnim.setDuration(animModalOut.getDuration());
        }
        return this;
    }

    public ShadowDialog setTitleText(@Nullable String text) {
        title = text;
        if (tvTitle != null) {
            tvTitle.setText(title);
            tvTitle.setVisibility(title == null ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    public ShadowDialog setContentText(@NonNull String text) {
        content = text;
        if (tvContent != null) {
            tvContent.setText(content);
        }
        return this;
    }

    public ShadowDialog setConfirmText(@Nullable String text) {
        confirm = text;
        if (tvConfirm != null) {
            tvConfirm.setText(confirm);
            tvConfirm.setVisibility(confirm == null ? View.GONE : View.VISIBLE);
        }
        return this;
    }

    public ShadowDialog setCancelText(@Nullable String text) {
        cancel = text;
        if (tvCancel != null) {
            tvCancel.setText(cancel);
            tvCancel.setVisibility(cancel == null ? View.GONE : View.VISIBLE);
        }
        return this;
    }

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

    public ShadowDialog setMaskResourceId(@ColorRes int maskResourceIdLight, @ColorRes int maskResourceIdNight) {
        this.maskResourceIdLight = maskResourceIdLight;
        this.maskResourceIdNight = maskResourceIdNight;
        return this;
    }

    public ShadowDialog setEnableAnimation(boolean enableAnimation) {
        this.enableAnimation = enableAnimation;
        return this;
    }

    public ShadowDialog setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
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
                llRoot.startAnimation(animModalOut);
            }
            if (overlayOutAnim != null) {
                tvConfirm.startAnimation(overlayOutAnim);
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
            llRoot.setBackgroundResource(ThemeUtils.getBgToastOrDialog());
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