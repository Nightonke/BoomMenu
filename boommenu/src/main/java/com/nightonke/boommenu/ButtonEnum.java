package com.nightonke.boommenu;

/**
 * Created by Weiping Huang at 01:21 on 16/11/7
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public enum ButtonEnum {

    /**
     * Boom-buttons which are just simple circles with an image for each.
     */
    SimpleCircle(0),

    /**
     * Boom-buttons which are circles with a text and image inside for each.
     */
    TextInsideCircle(1),

    /**
     * Boom-buttons which are circles with a text outside and image inside for each.
     */
    TextOutsideCircle(2),

    /**
     * Boom-buttons which are rectangles with a title, subtitle and image inside for each.
     */
    Ham(3),

    Unknown(-1);

    private final int value;

    ButtonEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ButtonEnum getEnum(int value) {
        if (value < 0 || value > values().length) return Unknown;
        else return values()[value];
    }
}
