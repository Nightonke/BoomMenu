package com.nightonke.boommenu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * Created by Weiping Huang at 23:50 on 16/12/11
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

class BMBFrameLayout extends FrameLayout {

    private boolean requestLayoutNotFinish = false;

    public BMBFrameLayout(Context context) {
        super(context);
    }

    public BMBFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BMBFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void requestLayout() {
        if (requestLayoutNotFinish) return;
        requestLayoutNotFinish = true;
        super.requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        requestLayoutNotFinish = false;
    }

}
