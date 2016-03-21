package com.nightonke.boommenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Weiping on 2016/3/19.
 */
public class CircleButton extends FrameLayout {

    private Context mContext;

    private ShadowLayout shadowLayout;
    private FrameLayout frameLayout;
    private ImageButton imageButton;
    private TextView textView;

    private int radius = (int)Util.getInstance().dp2px(80) / 2;

    public CircleButton(Context context) {
        this(context, null);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.circle_button, this, true);
        shadowLayout = (ShadowLayout)findViewById(R.id.shadow_layout);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        imageButton = (ImageButton)findViewById(R.id.image);
        textView = (TextView)findViewById(R.id.text);
    }

    public void setOnCircleButtonClickListener(
            final OnCircleButtonClickListener onCircleButtonClickListener,
            final int index) {
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onCircleButtonClickListener.onClick(index);
            }
        });
    }

    public void setDrawable(Drawable drawable) {
        if (imageButton != null) imageButton.setImageDrawable(drawable);
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

    public TextView getTextView() {
        return textView;
    }

    public void setColor(int pressedColor, int normalColor) {
        Util.getInstance().setCircleButtonStateListDrawable(
                imageButton, radius, pressedColor, normalColor);
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
