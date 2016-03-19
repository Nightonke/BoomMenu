package com.nightonke.boommenu.Eases;

import android.graphics.PointF;

/**
 * Created by Weiping on 2016/3/3.
 */
public abstract class CubicBezier {

    private PointF start;
    private PointF end;
    private PointF a = new PointF();
    private PointF b = new PointF();
    private PointF c = new PointF();

    /**
     * init the 4 values of the cubic-bezier
     * @param startX x of start
     * @param startY y of start
     * @param endX x of end
     * @param endY y of end
     */
    public void init(float startX, float startY, float endX, float endY) {
        setStart(new PointF(startX, startY));
        setEnd(new PointF(endX, endY));
    }

    public void init(double startX, double startY, double endX, double endY) {
        init((float) startX, (float) startY, (float) endX, (float) endY);
    }
    
    public float getOffset(float offset) {
        return getBezierCoordinateY(getXForTime(offset));
    }

    private float getBezierCoordinateY(float time) {
        c.y = 3 * start.y;
        b.y = 3 * (end.y - start.y) - c.y;
        a.y = 1 - c.y - b.y;
        return time * (c.y + time * (b.y + time * a.y));
    }

    private float getXForTime(float time) {
        float x = time;
        float z;
        for (int i = 1; i < 14; i++) {
            z = getBezierCoordinateX(x) - time;
            if (Math.abs(z) < 1e-3) {
                break;
            }
            x -= z / getXDerivate(x);
        }
        return x;
    }

    private float getXDerivate(float t) {
        return c.x + t * (2 * b.x + 3 * a.x * t);
    }

    private float getBezierCoordinateX(float time) {
        c.x = 3 * start.x;
        b.x = 3 * (end.x - start.x) - c.x;
        a.x = 1 - c.x - b.x;
        return time * (c.x + time * (b.x + time * a.x));
    }

    public PointF getStart() {
        return start;
    }

    public void setStart(PointF start) {
        this.start = start;
    }

    public PointF getEnd() {
        return end;
    }

    public void setEnd(PointF end) {
        this.end = end;
    }
}
