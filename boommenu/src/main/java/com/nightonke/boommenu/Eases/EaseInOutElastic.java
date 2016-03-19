package com.nightonke.boommenu.Eases;

/**
 * Created by Weiping on 2016/3/3.
 */

public class EaseInOutElastic extends CubicBezier {

    public EaseInOutElastic() {

    }

    public float getOffset(float t) {
        float b = 0;
        float c = 1;
        float d = 1;
        if (t==0) return b;  if ((t/=d/2)==2) return b+c;
        float p=d*(.3f*1.5f);
        float a=c;
        float s=p/4;
        if (t < 1) return -.5f*(a*(float)Math.pow(2,10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )) + b;
        return a*(float)Math.pow(2,-10*(t-=1)) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p )*.5f + c + b;
    }

}
