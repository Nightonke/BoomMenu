package com.nightonke.boommenu.Types;

/**
 * Created by Weiping on 2016/3/20.
 */
public enum OrderType {

    DEFAULT(0),
    REVERSE(1),
    RANDOM(2);

    int type;

    OrderType(int type) {
        this.type = type;
    }
}
