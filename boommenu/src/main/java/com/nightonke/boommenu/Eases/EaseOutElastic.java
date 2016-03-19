package com.nightonke.boommenu.Eases;

/**
 * Created by Weiping on 2016/3/3.
 */

public class EaseOutElastic extends CubicBezier {

    public EaseOutElastic() {

    }

    public float getOffset(float t) {
        float b = 0;
        float c = 1;
        float d = 1;
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        float p=d*.3f;
        float a=c;
        float s=p/4;
        return (a*(float)Math.pow(2,-10*t) * (float)Math.sin( (t*d-s)*(2*(float)Math.PI)/p ) + c + b);
    }

}
