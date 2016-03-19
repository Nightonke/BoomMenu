package com.nightonke.boommenu.Eases;

/**
 * Created by Weiping on 2016/3/3.
 */

public enum EaseType {
    
    EaseInSine(EaseInSine.class),
    EaseOutSine(EaseOutSine.class),
    EaseInOutSine(EaseInOutSine.class),

    EaseInQuad(EaseInQuad.class),
    EaseOutQuad(EaseOutQuad.class),
    EaseInOutQuad(EaseInOutQuad.class),

    EaseInCubic(EaseInCubic.class),
    EaseOutCubic(EaseOutCubic.class),
    EaseInOutCubic(EaseInOutCubic.class),

    EaseInQuart(EaseInQuart.class),
    EaseOutQuart(EaseOutQuart.class),
    EaseInOutQuart(EaseInOutQuart.class),

    EaseInQuint(EaseInQuint.class),
    EaseOutQuint(EaseOutQuint.class),
    EaseInOutQuint(EaseInOutQuint.class),

    EaseInExpo(EaseInExpo.class),
    EaseOutExpo(EaseOutExpo.class),
    EaseInOutExpo(EaseInOutExpo.class),

    EaseInCirc(EaseInCirc.class),
    EaseOutCirc(EaseOutCirc.class),
    EaseInOutCirc(EaseInOutCirc.class),

    EaseInBack(EaseInBack.class),
    EaseOutBack(EaseOutBack.class),
    EaseInOutBack(EaseInOutBack.class),

    EaseInElastic(EaseInElastic.class),
    EaseOutElastic(EaseOutElastic.class),
    EaseInOutElastic(EaseInOutElastic.class),

    EaseInBounce(EaseInBounce.class),
    EaseOutBounce(EaseOutBounce.class),
    EaseInOutBounce(EaseInOutBounce.class),

    Linear(Linear.class);

    private Class easingType;

    /**
     * ease animation helps to make the movement more real
     * @param easingType
     */
    EaseType(Class easingType) {
        this.easingType = easingType;
    }

    public float getOffset(float offset) {
        try {
            return ((CubicBezier) easingType.getConstructor().newInstance()).getOffset(offset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error("CubicBezier init error.");
        }
    }

}
