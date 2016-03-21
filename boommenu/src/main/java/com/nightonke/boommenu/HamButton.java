package com.nightonke.boommenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Weiping on 2016/3/19.
 */

public class HamButton extends FrameLayout {

    private Context mContext;

    private ShadowLayout shadowLayout;
    private LinearLayout linearLayout;
    private ImageView imageView;
    private TextView textView;

    private int width = 0;
    private int height = (int)Util.getInstance().dp2px(56);

    public HamButton(Context context) {
        this(context, null);
    }

    public HamButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.ham_button, this, true);
        shadowLayout = (ShadowLayout)findViewById(R.id.shadow_layout);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        imageView = (ImageView)findViewById(R.id.image);
        textView = (TextView)findViewById(R.id.text);

        width = Util.getInstance().getScreenWidth(getContext()) * 5 / 7;
        height = (int)Util.getInstance().dp2px(56);

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.width = width - (int)Util.getInstance().dp2px(8);
        linearLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams1 = shadowLayout.getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = height + (int)Util.getInstance().dp2px(4);
        shadowLayout.setLayoutParams(layoutParams1);
    }

    public void setOnHamButtonClickListener(
            final OnHamButtonClickListener onHamButtonClickListener, final int index) {
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onHamButtonClickListener.onClick(index);
            }
        });
    }

    public void setDrawable(Drawable drawable) {
        if (imageView != null) imageView.setImageDrawable(drawable);
    }

    public void setText(String text) {
        if (textView != null) textView.setText(text);
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setColor(int pressedColor, int normalColor) {
        Util.getInstance().setHamButtonStateListDrawable(
                linearLayout, width, height, pressedColor, normalColor);
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

    public interface OnHamButtonClickListener {
        void onClick(int index);
    }
}
