package com.nightonke.boommenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Weiping on 2016/4/1.
 */
public class ShareLines extends View {
    
    private float[][] locations;
    private float offset = 1;
    
    public ShareLines(Context context) {
        this(context, null);
    }

    public ShareLines(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLocations(float[][] locations) {
        this.locations = locations;
    }

    public void setOffset(float offset) {
        this.offset = Util.getInstance().round(offset, 2);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3f);
        paint.setAntiAlias(true);
        canvas.drawLine(
                locations[1][0],
                locations[1][1],
                (locations[0][0] - locations[1][0]) * offset + locations[1][0],
                (locations[0][1] - locations[1][1]) * offset + locations[1][1], paint);
        canvas.drawLine(
                locations[1][0],
                locations[1][1],
                (locations[2][0] - locations[1][0]) * offset + locations[1][0],
                (locations[2][1] - locations[1][1]) * offset + locations[1][1], paint);
        super.onDraw(canvas);
    }
}
