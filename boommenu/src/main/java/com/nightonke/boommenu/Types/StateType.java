package com.nightonke.boommenu.Types;

/**
 * Created by Weiping on 2016/3/21.
 */
public enum StateType {

    CLOSED(0),
    OPENING(1),
    OPEN(2),
    CLOSING(3);

    int type;

    StateType(int type) {
        this.type = type;
    }
}
