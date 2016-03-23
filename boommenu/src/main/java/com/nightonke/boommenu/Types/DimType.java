package com.nightonke.boommenu.Types;

import android.graphics.Color;

/**
 * Created by Weiping on 2016/3/22.
 */

public enum DimType {

    DIM_0(Color.parseColor("#00000000")),
    DIM_1(Color.parseColor("#11000000")),
    DIM_2(Color.parseColor("#22000000")),
    DIM_3(Color.parseColor("#33000000")),
    DIM_4(Color.parseColor("#44000000")),
    DIM_5(Color.parseColor("#55000000")),
    DIM_6(Color.parseColor("#66000000")),
    DIM_7(Color.parseColor("#77000000")),
    DIM_8(Color.parseColor("#88000000")),
    DIM_9(Color.parseColor("#99000000"));
    
    public int value;

    DimType(int value) {
        this.value = value;
    }
}
