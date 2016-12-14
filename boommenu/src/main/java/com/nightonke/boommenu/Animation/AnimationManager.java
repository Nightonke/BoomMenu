package com.nightonke.boommenu.Animation;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.view.View;

import com.nightonke.boommenu.BoomButtons.BoomButton;

import java.util.ArrayList;
import java.util.Random;

import static com.nightonke.boommenu.Animation.BoomEnum.LINE;

/**
 * Created by Weiping Huang at 03:27 on 16/7/26
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

public class AnimationManager {

    public static ObjectAnimator animate(Object target,
                                         String property,
                                         long delay,
                                         long duration,
                                         TimeInterpolator interpolator,
                                         AnimatorListenerAdapter listenerAdapter,
                                         float... values) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, property, values);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        if (interpolator != null) animator.setInterpolator(interpolator);
        if (listenerAdapter != null) animator.addListener(listenerAdapter);
        animator.start();
        return animator;
    }

    public static ObjectAnimator animate(Object target,
                                         String property,
                                         long delay,
                                         long duration,
                                         TimeInterpolator interpolator,
                                         float... values) {
        return animate(
                target,
                property,
                delay,
                duration,
                interpolator,
                null,
                values);
    }

    public static void animate(String property,
                               long delay,
                               long duration,
                               float[] values,
                               TimeInterpolator interpolator,
                               ArrayList<View> targets) {
        for (Object target : targets) {
            animate(
                    target,
                    property,
                    delay,
                    duration,
                    interpolator,
                    null,
                    values);
        }
    }

    public static void rotate(BoomButton boomButton,
                              long delay,
                              long duration,
                              TimeInterpolator interpolator,
                              float... degrees) {
        boomButton.setRotateAnchorPoints();
        for (int i = 0; i < boomButton.rotateViews().size(); i++) {
            View view = boomButton.rotateViews().get(i);
            animate(
                    view,
                    "rotation",
                    delay,
                    duration,
                    interpolator,
                    null,
                    degrees);
        }
    }

    public static ObjectAnimator animate(
            Object target,
            String property,
            long delay,
            long duration,
            TypeEvaluator evaluator,
            int... values) {
        return animate(target, property, delay, duration, evaluator, null, values);
    }

    public static ObjectAnimator animate(
            Object target,
            String property,
            long delay,
            long duration,
            TypeEvaluator evaluator,
            AnimatorListenerAdapter listenerAdapter,
            int... values) {
        ObjectAnimator animator = ObjectAnimator.ofInt(target, property, values);
        animator.setStartDelay(delay);
        animator.setDuration(duration);
        animator.setEvaluator(evaluator);
        if (listenerAdapter != null) animator.addListener(listenerAdapter);
        animator.start();
        return animator;
    }

    public static ArrayList<Integer> getOrderIndex(OrderEnum orderEnum, int size) {
        ArrayList<Integer> indexes = new ArrayList<>();
        switch (orderEnum) {
            case DEFAULT:
                for (int i = 0; i < size; i++) indexes.add(i);
                break;
            case REVERSE:
                for (int i = 0; i < size; i++) indexes.add(size - i - 1);
                break;
            case RANDOM:
                boolean[] used = new boolean[size];
                for (int i = 0; i < used.length; i++) used[i] = false;
                int count = 0;
                Random random = new Random();
                while (count < size) {
                    int r = random.nextInt(size);
                    if (!used[r]) {
                        used[r] = true;
                        indexes.add(r);
                        count++;
                    }
                }
                break;
        }
        return indexes;
    }

    public static void calculateShowXY(BoomEnum boomEnum,
                                       Point parentSize,
                                       int frames,
                                       Point startPosition,
                                       Point endPosition,
                                       float[] xs,
                                       float[] ys) {
        if (startPosition.x == endPosition.x) boomEnum = LINE;

        float x1 = startPosition.x;
        float y1 = startPosition.y;
        float x2 = endPosition.x;
        float y2 = endPosition.y;
        float p = 1.0f / frames;
        float xOffset = x2 - x1;
        float yOffset = y2 - y1;
        float x3, y3, a, b, c;

        switch (boomEnum) {
            case LINE:
                for (int i = 0; i <= frames; i++)
                {
                    float offset = i * p;
                    xs[i] = x1 + offset * xOffset;
                    ys[i] = y1 + offset * yOffset;
                }
                break;
            case PARABOLA_1:
                x3 = (x1 + x2) / 2.0f;
                y3 = Math.min(y1, y2) * 3.0f / 4;
                a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2)) / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
                b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
                c = y1 - (x1 * x1) * a - x1 * b;
                for (int i = 0; i <= frames; i++)
                {
                    float offset = i * p;
                    xs[i] = x1 + offset * xOffset;
                    ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
                }
                break;
            case PARABOLA_2:
                x3 = (x1 + x2) / 2.0f;
                y3 = (parentSize.y + Math.max(y1, y2)) / 2.0f;
                a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2)) / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
                b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
                c = y1 - (x1 * x1) * a - x1 * b;
                for (int i = 0; i <= frames; i++)
                {
                    float offset = i * p;
                    xs[i] = x1 + offset * xOffset;
                    ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
                }
                break;
            case PARABOLA_3:
                y3 = (y1 + y2) / 2.0f;
                x3 = Math.min(x1, x2) / 2.0f;
                a = (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / (y1 * y1 * (y2 - y3) + y2 * y2 * (y3 - y1) + y3 * y3 * (y1 - y2));
                b = (x1 - x2) / (y1 - y2) - a * (y1 + y2);
                c = x1 - (y1 * y1) * a - y1 * b;
                for (int i = 0; i <= frames; i++)
                {
                    float offset = i * p;
                    ys[i] = y1 + offset * yOffset;
                    xs[i] = a * ys[i] * ys[i] + b * ys[i] + c;
                }
                break;
            case PARABOLA_4:
                y3 = (y1 + y2) / 2.0f;
                x3 = (parentSize.x + Math.max(x1, x2)) / 2.0f;
                a = (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / (y1 * y1 * (y2 - y3) + y2 * y2 * (y3 - y1) + y3 * y3 * (y1 - y2));
                b = (x1 - x2) / (y1 - y2) - a * (y1 + y2);
                c = x1 - (y1 * y1) * a - y1 * b;
                for (int i = 0; i <= frames; i++)
                {
                    float offset = i * p;
                    ys[i] = y1 + offset * yOffset;
                    xs[i] = a * ys[i] * ys[i] + b * ys[i] + c;
                }
                break;
            case HORIZONTAL_THROW_1:
                x3 = x2 * 2 - x1;
                y3 = y1;
                a = (y1 * (x3 - x2) + y3 * (x2 - x1) + y2 * (x1 - x3)) / (x1 * x1 * (x3 - x2) + x3 * x3 * (x2 - x1) + x2 * x2 * (x1 - x3));
                b = (y1 - y3) / (x1 - x3) - a * (x1 + x3);
                c = y1 - (x1 * x1) * a - x1 * b;
                for (int i = 0; i <= frames; i++)
                {
                    float offset = i * p;
                    xs[i] = x1 + offset * xOffset;
                    ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
                }
                break;
            case HORIZONTAL_THROW_2:
                x2 = startPosition.x;
                y2 = startPosition.y;
                x1 = endPosition.x;
                y1 = endPosition.y;
                x3 = x2 * 2 - x1;
                y3 = y1;
                a = (y1 * (x3 - x2) + y3 * (x2 - x1) + y2 * (x1 - x3)) / (x1 * x1 * (x3 - x2) + x3 * x3 * (x2 - x1) + x2 * x2 * (x1 - x3));
                b = (y1 - y3) / (x1 - x3) - a * (x1 + x3);
                c = y1 - (x1 * x1) * a - x1 * b;
                for (int i = 0; i <= frames; i++)
                {
                    float offset = i * p;
                    xs[i] = x2 + offset * xOffset;
                    ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
                }
                break;
            case RANDOM:
                calculateShowXY(
                        BoomEnum.values()[new Random().nextInt(BoomEnum.RANDOM.getValue())],
                        parentSize,
                        frames,
                        startPosition,
                        endPosition,
                        xs,
                        ys);
                break;
            case Unknown:
                throw new RuntimeException("Unknown boom-enum!");
        }
    }

    public static void calculateHideXY(BoomEnum boomEnum,
                                       Point parentSize,
                                       int frames,
                                       Point startPosition,
                                       Point endPosition,
                                       float[] xs,
                                       float[] ys) {
        if (startPosition.x == endPosition.x) boomEnum = LINE;

        float x1 = startPosition.x;
        float y1 = startPosition.y;
        float x2 = endPosition.x;
        float y2 = endPosition.y;
        float p = 1.0f / frames;
        float xOffset = x2 - x1;
        float yOffset = y2 - y1;
        float x3, y3, a, b, c;

        switch (boomEnum) {
            case LINE:
            case PARABOLA_1:
            case PARABOLA_2:
            case PARABOLA_3:
            case PARABOLA_4:
            case RANDOM:
            case Unknown:
                calculateShowXY(
                        boomEnum,
                        parentSize,
                        frames,
                        startPosition,
                        endPosition,
                        xs,
                        ys);
                break;
            case HORIZONTAL_THROW_1:
                x2 = startPosition.x;
                y2 = startPosition.y;
                x1 = endPosition.x;
                y1 = endPosition.y;
                x3 = x2 * 2 - x1;
                y3 = y1;
                a = (y1 * (x3 - x2) + y3 * (x2 - x1) + y2 * (x1 - x3)) / (x1 * x1 * (x3 - x2) + x3 * x3 * (x2 - x1) + x2 * x2 * (x1 - x3));
                b = (y1 - y3) / (x1 - x3) - a * (x1 + x3);
                c = y1 - (x1 * x1) * a - x1 * b;
                for (int i = 0; i <= frames; i++) {
                    float offset = i * p;
                    xs[i] = x2 + offset * xOffset;
                    ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
                }
                break;
            case HORIZONTAL_THROW_2:
                x3 = x2 * 2 - x1;
                y3 = y1;
                a = (y1 * (x3 - x2) + y3 * (x2 - x1) + y2 * (x1 - x3)) / (x1 * x1 * (x3 - x2) + x3 * x3 * (x2 - x1) + x2 * x2 * (x1 - x3));
                b = (y1 - y3) / (x1 - x3) - a * (x1 + x3);
                c = y1 - (x1 * x1) * a - x1 * b;
                for (int i = 0; i <= frames; i++) {
                    float offset = i * p;
                    xs[i] = x1 + offset * xOffset;
                    ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
                }
                break;
        }
    }

    private static AnimationManager ourInstance = new AnimationManager();

    public static AnimationManager getInstance() {
        return ourInstance;
    }

    private AnimationManager() {
    }
}
