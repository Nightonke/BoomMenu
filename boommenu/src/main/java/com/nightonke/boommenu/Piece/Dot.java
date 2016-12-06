package com.nightonke.boommenu.Piece;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.nightonke.boommenu.R;
import com.nightonke.boommenu.Util;

/**
 * Created by Weiping Huang at 01:03 on 16/11/7
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

final class Dot extends BoomPiece {

    public Dot(Context context) {
        super(context);
    }

    @Override
    public void init(int color) {
        Drawable backgroundDrawable = Util.getDrawable(this, R.drawable.piece_dot, null);
        ((GradientDrawable)backgroundDrawable).setColor(color);
        Util.setDrawable(this, backgroundDrawable);
    }

    @Override
    public void setColor(int color) {
        ((GradientDrawable)getBackground()).setColor(color);
    }
}
