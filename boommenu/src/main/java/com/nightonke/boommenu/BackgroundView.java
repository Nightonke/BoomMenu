package com.nightonke.boommenu;

import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nightonke.boommenu.Animation.AnimationManager;


/**
 * Created by Weiping Huang at 11:47 on 2017/5/15
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

@SuppressLint("ViewConstructor")
class BackgroundView extends FrameLayout {

    private int dimColor;

    protected BackgroundView(Context context, final BoomMenuButton bmb) {
        super(context);

        dimColor = bmb.getDimColor();

        ViewGroup rootView = bmb.getParentView();
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                rootView.getWidth(),
                rootView.getHeight());
        setLayoutParams(params);
        setBackgroundColor(Color.TRANSPARENT);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bmb.onBackgroundClicked();
            }
        });
        setMotionEventSplittingEnabled(false);
        rootView.addView(this);
    }

    protected void reLayout(final BoomMenuButton bmb) {
        ViewGroup rootView = bmb.getParentView();
        FrameLayout.LayoutParams params = (LayoutParams) getLayoutParams();
        params.width = rootView.getWidth();
        params.height = rootView.getHeight();
        setLayoutParams(params);
    }

    protected void dim(long duration, AnimatorListenerAdapter completeListener) {
        setVisibility(VISIBLE);
        AnimationManager.animate(
                this, "backgroundColor", 0, duration, new ArgbEvaluator(), completeListener,
                Color.TRANSPARENT, dimColor);
    }

    protected void light(long duration, AnimatorListenerAdapter completeListener) {
        AnimationManager.animate(
                this, "backgroundColor", 0, duration, new ArgbEvaluator(), completeListener,
                dimColor, Color.TRANSPARENT);
    }
}
