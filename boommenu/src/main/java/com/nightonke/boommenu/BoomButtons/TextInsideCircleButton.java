package com.nightonke.boommenu.BoomButtons;

import android.content.Context;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;

import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.R;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 22:44 on 16/11/23
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

@SuppressWarnings("unused")
public class TextInsideCircleButton extends BoomButton {

    private TextInsideCircleButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        this.buttonEnum = ButtonEnum.TextInsideCircle;
        init(builder);
    }

    private void init(Builder builder) {
        LayoutInflater.from(context).inflate(R.layout.bmb_text_inside_circle_button, this, true);
        initAttrs(builder);
        if (isRound) initShadow(buttonRadius + shadowRadius);
        else initShadow(shadowCornerRadius);
        initCircleButton();
        initText(button);
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
        return ButtonEnum.TextInsideCircle;
    }

    @Override
    public ArrayList<View> goneViews() {
        ArrayList<View> goneViews = new ArrayList<>();
        goneViews.add(image);
        goneViews.add(text);
        return goneViews;
    }

    @Override
    public ArrayList<View> rotateViews() {
        ArrayList<View> rotateViews = new ArrayList<>();
        if (rotateImage) rotateViews.add(image);
        if (rotateText) rotateViews.add(text);
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
            toHighlightedText();
            lastStateIsNormal = false;
        }
    }

    @Override
    public void toNormal() {
        if (!lastStateIsNormal) {
            toNormalImage();
            toNormalText();
            lastStateIsNormal = true;
        }
    }

    @Override
    public void setRotateAnchorPoints() {
        image.setPivotX(buttonRadius - imageRect.left);
        image.setPivotY(buttonRadius - imageRect.top);
        text.setPivotX(buttonRadius - textRect.left);
        text.setPivotY(buttonRadius - textRect.top);
    }

    @Override
    public void setSelfScaleAnchorPoints() {

    }

    public static class Builder extends BoomButtonWithTextBuilder<Builder> {

        /**
         * Whether the text-view should rotate.
         *
         * @param rotateText rotate or not
         * @return the builder
         */
        public Builder rotateText(boolean rotateText) {
            this.rotateText = rotateText;
            return this;
        }

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
         * Gets button radius.
         *
         * @return the button radius
         */
        public int getButtonRadius() {
            return buttonRadius;
        }

        /**
         * Build text-inside circle button, only use in BMB package.
         *
         * @param context the context
         * @return the simple circle button
         */
        @Override
        public TextInsideCircleButton build(Context context) {
            TextInsideCircleButton button = new TextInsideCircleButton(this, context);
            weakReferenceButton(button);
            return button;
        }
    }
}
