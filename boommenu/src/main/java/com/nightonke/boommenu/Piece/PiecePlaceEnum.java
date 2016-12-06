package com.nightonke.boommenu.Piece;

/**
 * Created by Weiping Huang at 23:48 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */
public enum PiecePlaceEnum {

    DOT_1  ( 0),
    DOT_2_1( 1),
    DOT_2_2( 2),
    DOT_3_1( 3),
    DOT_3_2( 4),
    DOT_3_3( 5),
    DOT_3_4( 6),
    DOT_4_1( 7),
    DOT_4_2( 8),
    DOT_5_1( 9),
    DOT_5_2(10),
    DOT_5_3(11),
    DOT_5_4(12),
    DOT_6_1(13),
    DOT_6_2(14),
    DOT_6_3(15),
    DOT_6_4(16),
    DOT_6_5(17),
    DOT_6_6(18),
    DOT_7_1(19),
    DOT_7_2(20),
    DOT_7_3(21),
    DOT_7_4(22),
    DOT_7_5(23),
    DOT_7_6(24),
    DOT_8_1(25),
    DOT_8_2(26),
    DOT_8_3(27),
    DOT_8_4(28),
    DOT_8_5(29),
    DOT_8_6(30),
    DOT_8_7(31),
    DOT_9_1(32),
    DOT_9_2(33),
    DOT_9_3(34),

    HAM_1  (35),
    HAM_2  (36),
    HAM_3  (37),
    HAM_4  (38),
    HAM_5  (39),
    HAM_6  (40),

    Share  (99999),

    Unknown(-1);

    private final int value;

    PiecePlaceEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PiecePlaceEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }

    /**
     * Get number of pieces from a piece-place-enum.
     *
     * @return the number of pieces
     */
    public int pieceNumber() {
        switch (this) {
            case DOT_1:
            case HAM_1:
                return 1;
            case DOT_2_1:
            case DOT_2_2:
            case HAM_2:
                return 2;
            case DOT_3_1:
            case DOT_3_2:
            case DOT_3_3:
            case DOT_3_4:
            case HAM_3:
                return 3;
            case DOT_4_1:
            case DOT_4_2:
            case HAM_4:
                return 4;
            case DOT_5_1:
            case DOT_5_2:
            case DOT_5_3:
            case DOT_5_4:
            case HAM_5:
                return 5;
            case DOT_6_1:
            case DOT_6_2:
            case DOT_6_3:
            case DOT_6_4:
            case DOT_6_5:
            case DOT_6_6:
            case HAM_6:
                return 6;
            case DOT_7_1:
            case DOT_7_2:
            case DOT_7_3:
            case DOT_7_4:
            case DOT_7_5:
            case DOT_7_6:
                return 7;
            case DOT_8_1:
            case DOT_8_2:
            case DOT_8_3:
            case DOT_8_4:
            case DOT_8_5:
            case DOT_8_6:
            case DOT_8_7:
                return 8;
            case DOT_9_1:
            case DOT_9_2:
            case DOT_9_3:
                return 9;
            default:
                return -1;
        }
    }

    /**
     * Get the minimum piece number for this piecePlaceEnum
     *
     * @return minimum piece number
     */
    public int minPieceNumber() {
        switch (this) {
            case Share:
                return 3;
            default:
                return -1;
        }
    }

    /**
     * Get the maximum piece number for this piecePlaceEnum
     *
     * @return maximum piece number
     */
    public int maxPieceNumber() {
        switch (this) {
            case Share:
                return 9;
            default:
                return -1;
        }
    }
}
