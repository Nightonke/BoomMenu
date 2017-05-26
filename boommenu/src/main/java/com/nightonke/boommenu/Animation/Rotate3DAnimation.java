package com.nightonke.boommenu.Animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.ArrayList;


/**
 * Created by Weiping Huang at 20:12 on 2017/5/24
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class Rotate3DAnimation extends Animation {

    private float startX;
    private float startY;
    private float centerX;
    private float centerY;

    private ArrayList<Float> xs;
    private ArrayList<Float> ys;

    private Camera camera;
    private View view;

    public Rotate3DAnimation(float centerX, float centerY, ArrayList<Float> xs,
                             ArrayList<Float> ys) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.xs = xs;
        this.ys = ys;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final Camera camera = this.camera;
        final Matrix matrix = t.getMatrix();

//        int index = (int) (xs.size() * interpolatedTime);
//        if (index == xs.size()) index--;
//        float x = xs.get(index);
//        float y = ys.get(index);

        float x = 0, y = 0;
        if (interpolatedTime != 1) {
            float xOffset = interpolatedTime * xs.size();
            int lXIndex = (int) xOffset;
            int rXIndex = lXIndex + 1;
            if (rXIndex >= xs.size()) rXIndex = xs.size() - 1;
            x = xs.get(lXIndex) + (xs.get(rXIndex) - xs.get(lXIndex)) * (xOffset - lXIndex);

            float yOffset = interpolatedTime * ys.size();
            int lYIndex = (int) yOffset;
            int rYIndex = lYIndex + 1;
            if (rYIndex >= ys.size()) rYIndex = ys.size() - 1;
            y = ys.get(lYIndex) + (ys.get(rYIndex) - ys.get(lYIndex)) * (yOffset - lYIndex);
        }

        camera.save();
        camera.rotateX(x);
        camera.rotateY(y);
        camera.getMatrix(matrix);
        camera.restore();

        float offsetX = view.getX() - startX;
        float offsetY = view.getY() - startY;

        matrix.preTranslate(-offsetX - centerX, -offsetY - centerY);
        matrix.postTranslate(offsetX + centerX, offsetY + centerY);
    }

    public void set(View view, float startX, float startY) {
        this.view = view;
        this.startX = startX;
        this.startY = startY;
    }
}
