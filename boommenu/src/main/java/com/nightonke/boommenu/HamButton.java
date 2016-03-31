package com.nightonke.boommenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.Types.ClickEffectType;

/**
 * Created by Weiping on 2016/3/19.
 */

public class HamButton extends FrameLayout {

    private Context mContext;

    private ShadowLayout shadowLayout;
    private FrameLayout frameLayout;
    private View ripple;
    private ImageView imageView;
    private TextView textView;

    private ClickEffectType clickEffectType = ClickEffectType.RIPPLE;
    private OnHamButtonClickListener onHamButtonClickListener;
    private int index;

    private int width = 0;
    private int height = (int)Util.getInstance().dp2px(66);

    public HamButton(Context context) {
        this(context, null);
    }

    public HamButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LayoutInflater.from(mContext).inflate(R.layout.ham_button, this, true);
        } else {
            LayoutInflater.from(mContext).inflate(R.layout.ham_button_below_lollipop, this, true);
        }
        shadowLayout = (ShadowLayout)findViewById(R.id.shadow_layout);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        ripple = findViewById(R.id.ripple);
        imageView = (ImageView)findViewById(R.id.image);
        textView = (TextView)findViewById(R.id.text);

        width = Util.getInstance().getScreenWidth(getContext()) * 8 / 9;
        height = (int)Util.getInstance().dp2px(66);

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.width = width - (int)Util.getInstance().dp2px(8);
        frameLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams1 = shadowLayout.getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = height + (int)Util.getInstance().dp2px(4);
        shadowLayout.setLayoutParams(layoutParams1);
    }

    public void setOnHamButtonClickListener(
            final OnHamButtonClickListener onHamButtonClickListener, final int index) {
        this.onHamButtonClickListener = onHamButtonClickListener;
        this.index = index;
        setRipple(clickEffectType);
    }

    public void setDrawable(Drawable drawable) {
        if (imageView != null) imageView.setImageDrawable(drawable);
    }

    public void setText(String text) {
        if (textView != null) textView.setText(text);
    }

    public FrameLayout getFrameLayout() {
        return frameLayout;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public ShadowLayout getShadowLayout() {
        return shadowLayout;
    }

    public void setRipple(ClickEffectType clickEffectType) {
        this.clickEffectType = clickEffectType;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && clickEffectType.equals(ClickEffectType.RIPPLE)) {
            ripple.setVisibility(VISIBLE);
            ripple.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHamButtonClickListener.onClick(index);
                }
            });
        } else {
            ripple.setVisibility(GONE);
            frameLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHamButtonClickListener.onClick(index);
                }
            });
        }
    }

    public void setColor(int pressedColor, int normalColor) {
        Util.getInstance().setHamButtonStateListDrawable(
                frameLayout, width, height, pressedColor, normalColor);
    }

    public void setShadowColor(int mShadowColor) {
        shadowLayout.setShadowColor(mShadowColor);
    }

    public void setShadowDx(float mDx) {
        shadowLayout.setmDx(mDx);
    }

    public void setShadowDy(float mDy) {
        shadowLayout.setmDy(mDy);
    }

    public interface OnHamButtonClickListener {
        void onClick(int index);
    }
}
