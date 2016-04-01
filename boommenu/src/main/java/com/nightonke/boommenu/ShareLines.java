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

    private float lineWidth = 3f;
    private int line1Color = Color.WHITE;
    private int line2Color = Color.WHITE;

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
        paint.setColor(line1Color);
        paint.setStrokeWidth(lineWidth);
        paint.setAntiAlias(true);
        canvas.drawLine(
                locations[1][0],
                locations[1][1],
                (locations[0][0] - locations[1][0]) * offset + locations[1][0],
                (locations[0][1] - locations[1][1]) * offset + locations[1][1], paint);
        paint.setColor(line2Color);
        canvas.drawLine(
                locations[1][0],
                locations[1][1],
                (locations[2][0] - locations[1][0]) * offset + locations[1][0],
                (locations[2][1] - locations[1][1]) * offset + locations[1][1], paint);
        super.onDraw(canvas);
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        invalidate();
    }

    public void setLine1Color(int line1Color) {
        this.line1Color = line1Color;
        invalidate();
    }

    public void setLine2Color(int line2Color) {
        this.line2Color = line2Color;
        invalidate();
    }
}
