package com.nightonke.boommenu.Piece;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.nightonke.boommenu.R;
import com.nightonke.boommenu.Util;

/**
 * Created by Weiping Huang at 00:00 on 16/11/27
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

final class Ham extends BoomPiece {

    public Ham(Context context) {
        super(context);
    }

    @Override
    public void init(int color) {
        Drawable backgroundDrawable = Util.getDrawable(this, R.drawable.piece_ham, null);
        ((GradientDrawable)backgroundDrawable).setColor(color);
        Util.setDrawable(this, backgroundDrawable);
    }

    @Override
    public void setColor(int color) {
        ((GradientDrawable)getBackground()).setColor(color);
    }
}
