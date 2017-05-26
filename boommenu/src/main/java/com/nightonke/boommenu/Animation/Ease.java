package com.nightonke.boommenu.Animation;

import android.animation.TimeInterpolator;
import android.graphics.PointF;

import java.util.ArrayList;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

/**
 * Created by Weiping Huang at 01:41 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

public class Ease implements TimeInterpolator {

    private EaseEnum easeEnum;
    private PointF start = new PointF(0, 0);
    private PointF end = new PointF(1, 1);
    private PointF a = new PointF();
    private PointF b = new PointF();
    private PointF c = new PointF();
    private Boolean ableToDefineWithControlPoints = true;

    private static ArrayList<Ease> eases;

    public static Ease getInstance(EaseEnum easeEnum) {
        if (eases == null) {
            eases = new ArrayList<>(EaseEnum.values().length);
            for (int length = EaseEnum.values().length; length > 0; length--) eases.add(null);
        }
        Ease ease = eases.get(easeEnum.getValue());
        if (ease == null) {
            ease = new Ease(easeEnum);
            eases.set(easeEnum.getValue(), ease);
        }
        return ease;
    }

    private Ease(EaseEnum easeEnum) {
        switch (easeEnum) {
            case Linear:
                init(0.000, 0.000, 1.000, 1.000);
                break;
            case EaseInSine:
                init(0.470, 0.000, 0.745, 0.715);
                break;
            case EaseOutSine:
                init(0.390, 0.575, 0.565, 1.000);
                break;
            case EaseInOutSine:
                init(0.445, 0.050, 0.550, 0.950);
                break;
            case EaseInQuad:
                init(0.550, 0.085, 0.680, 0.530);
                break;
            case EaseOutQuad:
                init(0.250, 0.460, 0.450, 0.940);
                break;
            case EaseInOutQuad:
                init(0.455, 0.030, 0.515, 0.955);
                break;
            case EaseInCubic:
                init(0.550, 0.055, 0.675, 0.190);
                break;
            case EaseOutCubic:
                init(0.215, 0.610, 0.355, 1.000);
                break;
            case EaseInOutCubic:
                init(0.645, 0.045, 0.335, 1.000);
                break;
            case EaseInQuart:
                init(0.895, 0.030, 0.685, 0.220);
                break;
            case EaseOutQuart:
                init(0.165, 0.840, 0.440, 1.000);
                break;
            case EaseInOutQuart:
                init(0.770, 0.000, 0.175, 1.000);
                break;
            case EaseInQuint:
                init(0.755, 0.050, 0.855, 0.060);
                break;
            case EaseOutQuint:
                init(0.230, 1.000, 0.320, 1.000);
                break;
            case EaseInOutQuint:
                init(0.860, 0.000, 0.070, 1.000);
                break;
            case EaseInCirc:
                init(0.600, 0.040, 0.980, 0.335);
                break;
            case EaseOutCirc:
                init(0.075, 0.820, 0.165, 1.000);
                break;
            case EaseInOutCirc:
                init(0.785, 0.135, 0.150, 0.860);
                break;
            case EaseInExpo:
                init(0.950, 0.050, 0.795, 0.035);
                break;
            case EaseOutExpo:
                init(0.190, 1.000, 0.220, 1.000);
                break;
            case EaseInOutExpo:
                init(1.000, 0.000, 0.000, 1.000);
                break;
            case EaseInBack:
                init(0.600, -0.20, 0.735, 0.045);
                break;
            case EaseOutBack:
                init(0.174, 0.885, 0.320, 1.275);
                break;
            case EaseInOutBack:
                init(0.680, -0.55, 0.265, 1.550);
                break;
            case EaseInBounce:
            case EaseOutBounce:
            case EaseInOutBounce:
            case EaseInElastic:
            case EaseOutElastic:
            case EaseInOutElastic:
                ableToDefineWithControlPoints = false;
                break;
            default:
                throw new RuntimeException("Ease-enum not found!");
        }
        this.easeEnum = easeEnum;
    }

    private void init(float startX, float startY, float endX, float endY) {
        ableToDefineWithControlPoints = true;
        start = new PointF(startX, startY);
        end = new PointF(endX, endY);
    }

    private void init(double startX, double startY, double endX, double endY) {
        init((float) startX, (float) startY, (float) endX, (float) endY);
    }

    @Override
    public float getInterpolation(float offset) {
        if (ableToDefineWithControlPoints) {
            return getBezierCoordinateY(offset);
        } else {
            switch (easeEnum) {
                case EaseInBounce:
                    return getEaseInBounceOffset(offset);
                case EaseInElastic:
                    return getEaseInElasticOffset(offset);
                case EaseOutBounce:
                    return getEaseOutBounceOffset(offset);
                case EaseOutElastic:
                    return getEaseOutElasticOffset(offset);
                case EaseInOutBounce:
                    return getEaseInOutBounceOffset(offset);
                case EaseInOutElastic:
                    return getEaseInOutElasticOffset(offset);
                default:
                    throw new RuntimeException("Wrong ease-enum initialize method.");
            }
        }
    }

    private float getBezierCoordinateY(float time) {
        if (start.x == 0 && start.y == 0 && end.x == 1 && end.y == 1) return time;
        c.y = 3 * start.y;
        b.y = 3 * (end.y - start.y) - c.y;
        a.y = 1 - c.y - b.y;
        return time * (c.y + time * (b.y + time * a.y));
    }

    private float getEaseInBounceOffset(float offset) {
        float b = 0;
        float c = 1;
        float d = 1;
        return c - getEaseBounceOffsetHelper2(d - offset, 0, c, d) + b;
    }

    private float getEaseBounceOffsetHelper1(float t, float b, float c, float d) {
        return c - getEaseBounceOffsetHelper2(d - t, 0, c, d) + b;
    }

    private float getEaseBounceOffsetHelper2(float t, float b, float c, float d) {
        if ((t /= d) < (1 / 2.75)) {
            return c * (7.5625f * t * t) + b;
        } else if (t < (2 / 2.75)) {
            t -= 1.5 / 2.75;
            return c * (7.5625f * t * t + 0.75f) + b;
        } else if (t < (2 / 2.75)) {
            t -= 1.5 / 2.75;
            return c * (7.5625f * t * t + 0.9375f) + b;
        } else {
            t -= 2.625 / 2.75;
            return c * (7.5625f * t * t + 0.984375f) + b;
        }
    }

    private float getEaseOutBounceOffset(float t) {
        float b = 0;
        float c = 1;
        float d = 1;
        if ((t /= d) < (1 / 2.75)) {
            return c * (7.5625f * t * t) + b;
        } else if (t < (2 / 2.75)) {
            t -= (1.5 / 2.75);
            return c * (7.5625f * t * t + 0.75f) + b;
        } else if (t < (2.5 / 2.75)) {
            t -= (2.25 / 2.75);
            return c * (7.5625f * t * t + 0.9375f) + b;
        } else {
            t -= 2.625 / 2.75;
            return c * (7.5625f * t * t + 0.984375f) + b;
        }
    }

    private float getEaseInOutBounceOffset(float offset) {
        float b = 0;
        float c = 1;
        float d = 1;
        if (offset < d / 2) {
            return getEaseBounceOffsetHelper1(offset * 2, 0, c, d) * 0.5f + b;
        } else {
            return getEaseBounceOffsetHelper2(offset * 2, 0, c, d) * 0.5f + c * 0.5f + b;
        }
    }

    private float getEaseInElasticOffset(float offset) {
        float b = 0;
        float c = 1;
        float d = 1;
        if (offset == 0) return b;
        if ((offset /= d) == 1) return b + c;
        float p = d * 0.3f;
        float s = p / 4;
        offset -= 1;
        return - (c * (float)pow(2, 10 * offset)
                * (float)sin((offset * d - s) * (2 * (float)PI) / p)) + b;
    }

    private float getEaseOutElasticOffset(float offset) {
        float b = 0;
        float c = 1;
        float d = 1;
        if (offset == 0) return b;
        if ((offset /= d) == 1) return b + c;
        float p = d * 0.3f;
        float s = p / 4;
        return (c * (float)pow(2, -10 * offset)
                * (float)sin((offset * d - s) * (2 * (float)PI) / p) + c + b);
    }

    private float getEaseInOutElasticOffset(float offset) {
        float b = 0;
        float c = 1;
        float d = 1;
        if (offset == 0) return b;
        if ((offset /= d / 2) == 2) return b + c;
        float p = d * 0.45f;
        float s = p / 4;
        if (offset < 1) {
            offset -= 1;
            return -0.5f * (c * (float)pow(2, 10 * offset)
                    * (float)sin((offset * d - s) * (2 * (float)PI) / p)) + b;
        } else {
            offset -= 1;
            return c * (float)pow(2, -10 * offset)
                    * (float)sin((offset * d - s) * (2 * (float)PI) / p) * 0.5f + c + b;
        }
    }
}
