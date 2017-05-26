package com.nightonke.boommenu.BoomButtons;

/**
 * Created by Weiping Huang at 20:34 on 16/11/19
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public enum ButtonPlaceEnum {

    SC_1  (0),
    SC_2_1(1),
    SC_2_2(2),
    SC_3_1(3),
    SC_3_2(4),
    SC_3_3(5),
    SC_3_4(6),
    SC_4_1(7),
    SC_4_2(8),
    SC_5_1(9),
    SC_5_2(10),
    SC_5_3(11),
    SC_5_4(12),
    SC_6_1(13),
    SC_6_2(14),
    SC_6_3(15),
    SC_6_4(16),
    SC_6_5(17),
    SC_6_6(18),
    SC_7_1(19),
    SC_7_2(20),
    SC_7_3(21),
    SC_7_4(22),
    SC_7_5(23),
    SC_7_6(24),
    SC_8_1(25),
    SC_8_2(26),
    SC_8_3(27),
    SC_8_4(28),
    SC_8_5(29),
    SC_8_6(30),
    SC_8_7(31),
    SC_9_1(32),
    SC_9_2(33),
    SC_9_3(34),
    HAM_1 (35),
    HAM_2 (36),
    HAM_3 (37),
    HAM_4 (38),
    HAM_5 (39),
    HAM_6 (40),

    Horizontal(Integer.MAX_VALUE - 2),
    Vertical(Integer.MAX_VALUE - 1),

    Custom(Integer.MAX_VALUE),

    Unknown(-1);

    private final int value;

    ButtonPlaceEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ButtonPlaceEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }

    /**
     * Get the number of boom-button for button-place-enum.
     * -1 for unknown, and MAX_INT for horizontal or vertical place-enum.
     *
     * @return the number of boom-button
     */
    public int buttonNumber() {
        switch (this) {
            case SC_1:
            case HAM_1: return 1;
            case SC_2_1:
            case SC_2_2:
            case HAM_2: return 2;
            case SC_3_1:
            case SC_3_2:
            case SC_3_3:
            case SC_3_4:
            case HAM_3: return 3;
            case SC_4_1:
            case SC_4_2:
            case HAM_4: return 4;
            case SC_5_1:
            case SC_5_2:
            case SC_5_3:
            case SC_5_4:
            case HAM_5: return 5;
            case SC_6_1:
            case SC_6_2:
            case SC_6_3:
            case SC_6_4:
            case SC_6_5:
            case SC_6_6:
            case HAM_6: return 6;
            case SC_7_1:
            case SC_7_2:
            case SC_7_3:
            case SC_7_4:
            case SC_7_5:
            case SC_7_6: return 7;
            case SC_8_1:
            case SC_8_2:
            case SC_8_3:
            case SC_8_4:
            case SC_8_5:
            case SC_8_6:
            case SC_8_7: return 8;
            case SC_9_1:
            case SC_9_2:
            case SC_9_3: return 9;
            case Unknown: return 0;
            default: return -1;
        }
    }

    /**
     * Get the minimum button number for this buttonPlaceEnum
     *
     * @return minimum button number
     */
    public int minButtonNumber() {
        switch (this) {
            case Horizontal:
            case Vertical:
            case Custom: return 1;
            case Unknown: return 0;
            default: return -1;
        }
    }

    /**
     * Get the maximum button number for this buttonPlaceEnum
     *
     * @return maximum button number
     */
    public int maxButtonNumber() {
        switch (this) {
            case Horizontal:
            case Vertical:
            case Custom: return Integer.MAX_VALUE;
            case Unknown: return 0;
            default: return -1;
        }
    }
}
