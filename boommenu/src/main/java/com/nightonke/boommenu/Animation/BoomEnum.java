package com.nightonke.boommenu.Animation;

/**
 * Created by Weiping Huang at 02:04 on 2016/3/19
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

public enum BoomEnum {

    LINE              (0),
    PARABOLA_1        (1),
    PARABOLA_2        (2),
    PARABOLA_3        (3),
    PARABOLA_4        (4),
    HORIZONTAL_THROW_1(5),
    HORIZONTAL_THROW_2(6),
    RANDOM            (7),

    Unknown           (-1);

    private final int value;

    BoomEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BoomEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }

}
