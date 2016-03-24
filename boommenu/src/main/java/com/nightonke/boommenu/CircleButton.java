package com.nightonke.boommenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.Types.ClickEffectType;

/**
 * Created by Weiping on 2016/3/19.
 */

public class CircleButton extends FrameLayout {

    private Context mContext;

    private ShadowLayout shadowLayout;
    private FrameLayout frameLayout;
    private ImageButton imageButton;
    private View ripple;
    private ImageView imageView;
    private TextView textView;

    private ClickEffectType clickEffectType = ClickEffectType.RIPPLE;
    private OnCircleButtonClickListener onCircleButtonClickListener;
    private int index;

    private int radius = (int)Util.getInstance().dp2px(80) / 2;

    public CircleButton(Context context) {
        this(context, null);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LayoutInflater.from(mContext).inflate(R.layout.circle_button, this, true);
        } else {
            LayoutInflater.from(mContext).inflate(R.layout.circle_button_below_lollipop, this, true);
        }
        shadowLayout = (ShadowLayout)findViewById(R.id.shadow_layout);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        imageButton = (ImageButton)findViewById(R.id.image_button);
        ripple = findViewById(R.id.ripple);
        imageView = (ImageView)findViewById(R.id.image_view);
        textView = (TextView)findViewById(R.id.text);
    }

    public void setOnCircleButtonClickListener(
            OnCircleButtonClickListener onCircleButtonClickListener,
            int index) {
        this.onCircleButtonClickListener = onCircleButtonClickListener;
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

    public ImageButton getImageButton() {
        return imageButton;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setColor(int pressedColor, int normalColor) {
        Util.getInstance().setCircleButtonStateListDrawable(
                imageButton, radius, pressedColor, normalColor);
    }

    public void setRipple(ClickEffectType clickEffectType) {
        this.clickEffectType = clickEffectType;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && clickEffectType.equals(ClickEffectType.RIPPLE)) {
            ripple.setVisibility(VISIBLE);
            ripple.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCircleButtonClickListener.onClick(index);
                }
            });
        } else {
            ripple.setVisibility(GONE);
            imageButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCircleButtonClickListener.onClick(index);
                }
            });
        }
    }

    public void setShadowColor(int mShadowColor) {
        shadowLayout.setmShadowColor(mShadowColor);
    }

    public void setShadowDx(float mDx) {
        shadowLayout.setmDx(mDx);
    }

    public void setShadowDy(float mDy) {
        shadowLayout.setmDy(mDy);
    }

    public interface OnCircleButtonClickListener {
        void onClick(int index);
    }
}
