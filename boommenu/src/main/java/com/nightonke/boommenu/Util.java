package com.nightonke.boommenu;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Weiping on 2016/3/19.
 */
public class Util {

    public int getScreenWidth(Context context) {
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int getScreenHeight(Context context) {
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public float dp2px(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.9f;
        return Color.HSVToColor(hsv);
    }

    public int getLighterColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 1.1f;
        return Color.HSVToColor(hsv);
    }

    public int getPressedColor(int color) {
        if (getLighterColor(color) == color) return getDarkerColor(color);
        else return getLighterColor(color);
    }

    @SuppressWarnings("deprecation")
    public void setCircleButtonStateListDrawable(
            View circleButton, int radius, int pressedColor, int normalColor) {
        Bitmap imagePressed = Bitmap.createBitmap(
                2 * radius,
                2 * radius,
                Bitmap.Config.ARGB_8888);
        Canvas canvasPressed = new Canvas(imagePressed);
        Paint paintPressed = new Paint();
        paintPressed.setAntiAlias(true);
        paintPressed.setColor(pressedColor);
        canvasPressed.drawCircle(
                radius,
                radius,
                radius, paintPressed);

        Bitmap imageNormal = Bitmap.createBitmap(
                2 * radius,
                2 * radius,
                Bitmap.Config.ARGB_8888);
        Canvas canvasNormal = new Canvas(imageNormal);
        Paint paintNormal = new Paint();
        paintNormal.setAntiAlias(true);
        paintNormal.setColor(normalColor);
        canvasNormal.drawCircle(
                radius,
                radius,
                radius, paintNormal);

        StateListDrawable stateListDrawable= new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},
                new BitmapDrawable(circleButton.getContext().getResources(), imagePressed));
        stateListDrawable.addState(StateSet.WILD_CARD,
                new BitmapDrawable(circleButton.getContext().getResources(), imageNormal));

        if(android.os.Build.VERSION.SDK_INT >= 16){
            circleButton.setBackground(stateListDrawable);
        }else{
            circleButton.setBackgroundDrawable(stateListDrawable);
        }
    }

    @SuppressWarnings("deprecation")
    public void setHamButtonStateListDrawable(
            View linearLayout, int width, int height, int pressedColor, int normalColor) {
        Bitmap imagePressed = Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888);
        Canvas canvasPressed = new Canvas(imagePressed);
        Paint paintPressed = new Paint();
        paintPressed.setAntiAlias(true);
        paintPressed.setColor(pressedColor);
        canvasPressed.drawRoundRect(new RectF(0, 0, width, height), dp2px(2), dp2px(2), paintPressed);

        Bitmap imageNormal = Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888);
        Canvas canvasNormal = new Canvas(imageNormal);
        Paint paintNormal = new Paint();
        paintNormal.setAntiAlias(true);
        paintNormal.setColor(normalColor);
        canvasNormal.drawRoundRect(new RectF(0, 0, width, height), dp2px(2), dp2px(2), paintNormal);

        StateListDrawable stateListDrawable= new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},
                new BitmapDrawable(linearLayout.getContext().getResources(), imagePressed));
        stateListDrawable.addState(StateSet.WILD_CARD,
                new BitmapDrawable(linearLayout.getContext().getResources(), imageNormal));

        if(android.os.Build.VERSION.SDK_INT >= 16){
            linearLayout.setBackground(stateListDrawable);
        }else{
            linearLayout.setBackgroundDrawable(stateListDrawable);
        }
    }

    private static Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }
}
