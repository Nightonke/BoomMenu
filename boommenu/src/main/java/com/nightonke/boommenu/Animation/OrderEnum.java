package com.nightonke.boommenu.Animation;

/**
 * Created by Weiping Huang at 02:05 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public enum OrderEnum {

    DEFAULT(0),
    REVERSE(1),
    RANDOM (2),

    Unknown(-1);

    private final int value;

    OrderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }
}
