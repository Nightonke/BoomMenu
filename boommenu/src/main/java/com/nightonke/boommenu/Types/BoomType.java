package com.nightonke.boommenu.Types;

/**
 * Created by Weiping on 2016/3/19.
 */
public enum BoomType {

    LINE(0),
    PARABOLA(1),
    HORIZONTAL_THROW(2),
    PARABOLA_2(3),
    HORIZONTAL_THROW_2(4);

    int type;

    BoomType(int type) {
        this.type = type;
    }
}
