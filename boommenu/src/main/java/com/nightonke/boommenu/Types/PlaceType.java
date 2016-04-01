package com.nightonke.boommenu.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Weiping on 2016/3/19.
 */
public enum PlaceType {

    CIRCLE_1_1(0),
    CIRCLE_2_1(1),
    CIRCLE_2_2(2),
    CIRCLE_3_1(3),
    CIRCLE_3_2(4),
    CIRCLE_3_3(5),
    CIRCLE_3_4(6),
    CIRCLE_4_1(7),
    CIRCLE_4_2(8),
    CIRCLE_5_1(9),
    CIRCLE_5_2(10),
    CIRCLE_5_3(11),
    CIRCLE_5_4(12),
    CIRCLE_6_1(13),
    CIRCLE_6_2(14),
    CIRCLE_6_3(15),
    CIRCLE_6_4(16),
    CIRCLE_6_5(17),
    CIRCLE_6_6(18),
    CIRCLE_7_1(19),
    CIRCLE_7_2(20),
    CIRCLE_7_3(21),
    CIRCLE_7_4(22),
    CIRCLE_8_1(23),
    CIRCLE_8_2(24),
    CIRCLE_8_3(25),
    CIRCLE_9_1(26),
    CIRCLE_9_2(27),

    HAM_1_1(28),
    HAM_2_1(29),
    HAM_3_1(30),
    HAM_4_1(31),

    SHARE_3_1(32),
    SHARE_3_2(33),
    SHARE_3_3(34),
    SHARE_3_4(35),
    SHARE_4_1(36),
    SHARE_4_2(37),
    SHARE_5_1(38),
    SHARE_5_2(39),
    SHARE_5_3(40),
    SHARE_5_4(41),
    SHARE_6_1(42),
    SHARE_6_2(43),
    SHARE_6_3(44),
    SHARE_6_4(45),
    SHARE_6_5(46),
    SHARE_6_6(47),
    SHARE_7_1(48),
    SHARE_7_2(49),
    SHARE_7_3(50),
    SHARE_7_4(51),
    SHARE_8_1(52),
    SHARE_8_2(53),
    SHARE_8_3(54),
    SHARE_9_1(55),
    SHARE_9_2(56);

    public int v;

    PlaceType(int v) {
        this.v = v;
    }

    private static Map<Integer, PlaceType> map = new HashMap<Integer, PlaceType>();

    static {
        for (PlaceType placeType : PlaceType.values()) {
            map.put(placeType.v, placeType);
        }
    }

    public static PlaceType valueOf(int v) {
        return map.get(v);
    }
}
