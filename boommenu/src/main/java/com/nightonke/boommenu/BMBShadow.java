package com.nightonke.boommenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * Created by Weiping Huang at 19:09 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class BMBShadow extends FrameLayout {

    private boolean shadowEffect = true;
    private int shadowOffsetX;
    private int shadowOffsetY;
    private int shadowRadius;
    private int shadowCornerRadius;
    private int shadowColor;

    public BMBShadow(Context context) {
        super(context);
    }

    public BMBShadow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BMBShadow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPadding() {
        int xPadding = shadowRadius + Math.abs(shadowOffsetX);
        int yPadding = shadowRadius + Math.abs(shadowOffsetY);
        setPadding(xPadding, yPadding, xPadding, yPadding);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w > 0 && h > 0) {
            createShadow();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        createShadow();
    }

    private void createShadow() {
        if (shadowEffect) {
            Bitmap shadowBitmap = createShadowBitmap();
            BitmapDrawable shadowDrawable = new BitmapDrawable(getResources(), shadowBitmap);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
                //noinspection deprecation
                setBackgroundDrawable(shadowDrawable);
            } else {
                setBackground(shadowDrawable);
            }
        } else {
            clearShadow();
        }
    }

    private Bitmap createShadowBitmap() {
        Bitmap shadowBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(shadowBitmap);
        RectF shadowRect = new RectF(
                shadowRadius + Math.abs(shadowOffsetX),
                shadowRadius + Math.abs(shadowOffsetY),
                getWidth() - shadowRadius - Math.abs(shadowOffsetX),
                getHeight() - shadowRadius - Math.abs(shadowOffsetY));
        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(Color.TRANSPARENT);
        shadowPaint.setStyle(Paint.Style.FILL);
        if (!isInEditMode()) {
            shadowPaint.setShadowLayer(shadowRadius, shadowOffsetX, shadowOffsetY, shadowColor);
        }
        canvas.drawRoundRect(shadowRect, shadowCornerRadius, shadowCornerRadius, shadowPaint);
        return shadowBitmap;
    }

    public void setShadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        initPadding();
    }

    public void setShadowOffsetY(int shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        initPadding();
    }

    public void setShadowRadius(int shadowRadius) {
        this.shadowRadius = shadowRadius;
        initPadding();
    }

    public void setShadowCornerRadius(int shadowCornerRadius) {
        this.shadowCornerRadius = shadowCornerRadius;
        initPadding();
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
    }

    public void setShadowEffect(boolean shadowEffect) {
        this.shadowEffect = shadowEffect;
    }

    public void clearShadow() {
        Util.setDrawable(this, null);
    }
}
