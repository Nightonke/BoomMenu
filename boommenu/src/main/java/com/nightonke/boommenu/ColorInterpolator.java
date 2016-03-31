package com.nightonke.boommenu;

import android.graphics.Color;
import android.view.animation.Interpolator;

/**
 * Created by Weiping on 2016/4/1.
 */
public class ColorInterpolator implements Interpolator {

    private int fromColor;
    private int targetColor;
    private int targetA = -1;
    private int targetR = -1;
    private int targetG = -1;
    private int targetB = -1;
    private int fromA = -1;
    private int fromR = -1;
    private int fromG = -1;
    private int fromB = -1;

    public ColorInterpolator(int fromColor, int targetColor) {
        this.fromColor = fromColor;
        this.targetColor = targetColor;
        setARGB();
    }

    @Override
    public float getInterpolation(float input) {
        int nowColor = Color.argb(
                fromA + (int)((targetA - fromA) * input),
                fromR + (int)((targetR - fromR) * input),
                fromG + (int)((targetG - fromG) * input),
                fromB + (int)((targetB - fromB) * input));
        return (nowColor - fromColor) / fromColor;
    }

    private void setARGB() {
        targetA = Color.alpha(targetColor);
        targetR = Color.red(targetColor);
        targetG = Color.green(targetColor);
        targetB = Color.blue(targetColor);
        fromA = Color.alpha(fromColor);
        fromR = Color.red(fromColor);
        fromG = Color.green(fromColor);
        fromB = Color.blue(fromColor);
    }
}
