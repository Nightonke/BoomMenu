package com.nightonke.boommenu.Animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;


/**
 * Created by Weiping Huang at 01:21 on 16/12/2
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class ShareLinesView extends View {

    private long animationShowDelay1;
    private long animationShowDuration1;
    private long animationShowDelay2;
    private long animationShowDuration2;
    private long animationShowDelay3;
    private long animationShowTotalDuration;

    private long animationHideDelay1;
    private long animationHideDuration1;
    private long animationHideDelay2;
    private long animationHideDuration2;
    private long animationHideDelay3;
    private long animationHideTotalDuration;

    private float processForLine1 = 1;
    private float processForLine2 = 1;

    private ArrayList<PointF> piecePositions;

    private int line1Color = Color.WHITE;
    private int line2Color = Color.WHITE;
    private int lineWidth = 3;

    private Paint linePaint;

    public ShareLinesView(Context context) {
        super(context);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
    }

    public void setData(ArrayList<Point> piecePositions,
                        int dotRadius,
                        long showDuration,
                        long showDelay,
                        long hideDuration,
                        long hideDelay) {
        float xOffset = dotRadius - lineWidth / 4f;
        float yOffset = (float) (dotRadius - lineWidth * Math.sqrt(3) / 4f) + 0.5f;

        this.piecePositions = new ArrayList<>();
        for (Point piecePosition : piecePositions) {
            boolean existed = false;
            for (PointF p : this.piecePositions) {
                if (p.equals(piecePosition.x, piecePosition.y)) {
                    existed = true;
                    break;
                }
            }
            if (!existed) this.piecePositions.add(new PointF(piecePosition));
        }
        for (PointF piecePosition : this.piecePositions) piecePosition.offset(xOffset, yOffset);

        int[] pieceNumbers = new int[3];
        int pieceNumber = piecePositions.size();
        for (int i = 0; i < pieceNumber; i++) pieceNumbers[i % 3]++;
        animationShowDelay1 = showDelay * (pieceNumbers[0] - 1);
        animationShowDuration1 = pieceNumbers[0] * showDelay;
        animationShowDelay2 = (pieceNumbers[0] - 1 + pieceNumbers[1]) * showDelay;
        animationShowDuration2 = (pieceNumbers[0] + pieceNumbers[1]) * showDelay;
        animationShowDelay3 = showDelay * (pieceNumbers[2] - 1 + pieceNumbers[1] + pieceNumbers[0]) + showDuration;
        animationShowTotalDuration = animationShowDelay3;

        animationHideDelay1 = (pieceNumbers[2] - 1) * hideDelay + hideDuration;
        animationHideDuration1 = (pieceNumbers[2]) * hideDelay + hideDuration;
        animationHideDelay2 = hideDelay * (pieceNumbers[2] - 1 + pieceNumbers[1]) + hideDuration;
        animationHideDuration2 = (pieceNumbers[2] + pieceNumbers[1]) * hideDelay + hideDuration;
        animationHideDelay3 = hideDelay * (pieceNumbers[2] - 1 + pieceNumbers[1] + pieceNumbers[0]) + hideDuration;
        animationHideTotalDuration = animationHideDelay3;
    }

    private void setShowProcess(float process) {
        long current = (long) (process * animationShowTotalDuration);
        if (animationShowDelay1 < current && current <= animationShowDuration1) {
            processForLine1 = (animationShowDuration1 - current)
                    / (float) (animationShowDuration1 - animationShowDelay1);
        } else if (animationShowDuration1 < current && current <= animationShowDelay2) {
            processForLine1 = 0;
        } else if (animationShowDelay2 < current && current <= animationShowDuration2) {
            processForLine2 = (animationShowDuration2 - current)
                    / (float) (animationShowDuration2 - animationShowDelay2);
        } else if (animationShowDuration2 <= current) {
            processForLine1 = 0;
            processForLine2 = 0;
        }
        invalidate();
    }

    private void setHideProcess(float process) {
        long current = (long) (process * animationHideTotalDuration);
        if (animationHideDelay1 < current && current <= animationHideDuration1) {
            processForLine2 = 1 - (animationHideDuration1 - current)
                    / (float) (animationHideDuration1 - animationHideDelay1);
        } else if (animationHideDuration1 < current && current <= animationHideDelay2) {
            processForLine2 = 1;
        } else if (animationHideDelay2 < current && current <= animationHideDuration2) {
            processForLine1 = 1 - (animationHideDuration2 - current)
                    / (float) (animationHideDuration2 - animationHideDelay2);
        } else if (animationHideDuration2 <= current) {
            processForLine1 = 1;
            processForLine2 = 1;
        }
        invalidate();
    }

    public void setLine1Color(int line1Color) {
        this.line1Color = line1Color;
        linePaint.setColor(line1Color);
    }

    public void setLine2Color(int line2Color) {
        this.line2Color = line2Color;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        linePaint.setStrokeWidth(lineWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(
                piecePositions.get(1).x,
                piecePositions.get(1).y,
                (piecePositions.get(0).x - piecePositions.get(1).x) * processForLine1 + piecePositions.get(1).x,
                (piecePositions.get(0).y - piecePositions.get(1).y) * processForLine1 + piecePositions.get(1).y, linePaint);
        linePaint.setColor(line2Color);
        canvas.drawLine(
                piecePositions.get(2).x,
                piecePositions.get(2).y,
                (piecePositions.get(1).x - piecePositions.get(2).x) * processForLine2 + piecePositions.get(2).x,
                (piecePositions.get(1).y - piecePositions.get(2).y) * processForLine2 + piecePositions.get(2).y, linePaint);
        super.onDraw(canvas);
    }

    public void place(int left, int top, int width, int height) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            layoutParams.leftMargin = left;
            layoutParams.topMargin = top;
            layoutParams.width = width;
            layoutParams.height = height;
            setLayoutParams(layoutParams);
        }
    }
}
