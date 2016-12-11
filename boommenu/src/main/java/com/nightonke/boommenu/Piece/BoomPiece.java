package com.nightonke.boommenu.Piece;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by Weiping Huang at 00:57 on 16/11/7
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public abstract class BoomPiece extends View {

    private boolean requestLayoutNotFinish = false;

    public BoomPiece(Context context) {
        super(context);
    }

    public abstract void init(int color);

    public abstract void setColor(int color);

    public void place(int left, int top, int width, int height) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            layoutParams.leftMargin = left;
            layoutParams.topMargin = top;
            layoutParams.width = width;
            layoutParams.height = height;
            setLayoutParams(layoutParams);
        }
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
