package com.nightonke.boommenu.BoomButtons;

import android.content.Context;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;

import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.R;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 01:33 on 16/11/18
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */
@SuppressWarnings("unused")
public class SimpleCircleButton extends BoomButton {

    private SimpleCircleButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        this.buttonEnum = ButtonEnum.SimpleCircle;
        init(builder);
    }

    private void init(Builder builder) {
        LayoutInflater.from(context).inflate(R.layout.bmb_simple_circle_button, this, true);
        initAttrs(builder);
        if (isRound) initShadow(buttonRadius + shadowRadius);
        else initShadow(shadowCornerRadius);
        initCircleButton();
        initImage();
        centerPoint = new PointF(
                buttonRadius + shadowRadius + shadowOffsetX,
                buttonRadius + shadowRadius + shadowOffsetY);
    }

    private void initAttrs(Builder builder) {
        super.initAttrs(builder);
    }

    @Override
    public ButtonEnum type() {
        return ButtonEnum.SimpleCircle;
    }

    @Override
    public ArrayList<View> goneViews() {
        ArrayList<View> goneViews = new ArrayList<>();
        goneViews.add(image);
        return goneViews;
    }

    @Override
    public ArrayList<View> rotateViews() {
        ArrayList<View> rotateViews = new ArrayList<>();
        if (rotateImage) rotateViews.add(image);
        return rotateViews;
    }

    @Override
    public int trueWidth() {
        return buttonRadius * 2 + shadowRadius * 2 + shadowOffsetX * 2;
    }

    @Override
    public int trueHeight() {
        return buttonRadius * 2 + shadowRadius * 2 + shadowOffsetY * 2;
    }

    @Override
    public int contentWidth() {
        return buttonRadius * 2;
    }

    @Override
    public int contentHeight() {
        return buttonRadius * 2;
    }

    @Override
    public void toHighlighted() {
        if (lastStateIsNormal && ableToHighlight) {
            toHighlightedImage();
            lastStateIsNormal = false;
        }
    }

    @Override
    public void toNormal() {
        if (!lastStateIsNormal) {
            toNormalImage();
            lastStateIsNormal = true;
        }
    }

    @Override
    public void setRotateAnchorPoints() {
        image.setPivotX(buttonRadius - imageRect.left);
        image.setPivotY(buttonRadius - imageRect.top);
    }

    @Override
    public void setSelfScaleAnchorPoints() {

    }

    public static class Builder extends BoomButtonBuilder<Builder> {

        /**
         * The radius of boom-button, in pixel.
         *
         * @param buttonRadius the button radius
         * @return the builder
         */
        public Builder buttonRadius(int buttonRadius) {
            this.buttonRadius = buttonRadius;
            return this;
        }

        /**
         * Set the corner-radius of button.
         *
         * @param buttonCornerRadius corner-radius of button
         * @return the builder
         */
        public Builder buttonCornerRadius(int buttonCornerRadius) {
            this.buttonCornerRadius = buttonCornerRadius;
            return this;
        }

        /**
         * Whether the button is a circle shape.
         *
         * @param isRound is or not
         * @return the builder
         */
        public Builder isRound(boolean isRound) {
            this.isRound = isRound;
            return this;
        }

        /**
         * Gets button radius, used in BMB package.
         *
         * @return the button radius
         */
        public int getButtonRadius() {
            return buttonRadius;
        }

        /**
         * Build simple circle button, only used in BMB package.
         *
         * @param context the context
         * @return the simple circle button
         */
        @Override
        public SimpleCircleButton build(Context context) {
            SimpleCircleButton button = new SimpleCircleButton(this, context);
            weakReferenceButton(button);
            return button;
        }
    }
}
