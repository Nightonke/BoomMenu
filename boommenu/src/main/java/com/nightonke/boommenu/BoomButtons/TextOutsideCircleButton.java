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
public class TextOutsideCircleButton extends BoomButton {

    private TextOutsideCircleButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        this.buttonEnum = ButtonEnum.TextOutsideCircle;
        init(builder);
    }

    private void init(Builder builder) {
        LayoutInflater.from(context).inflate(R.layout.bmb_text_outside_circle_button, this, true);
        initAttrs(builder);
        initTextOutsideCircleButtonLayout();
        if (isRound) initShadow(buttonRadius + shadowRadius);
        else initShadow(shadowCornerRadius);
        initCircleButton();
        initText(layout);
        initImage();
        centerPoint = new PointF(trueRadius, trueRadius);
    }

    private void initAttrs(Builder builder) {
        super.initAttrs(builder);
    }

    @Override
    public ButtonEnum type() {
        return ButtonEnum.TextOutsideCircle;
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
        return trueRadius * 2;
    }

    @Override
    public int trueHeight() {
        return trueRadius * 2;
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
        text.setPivotX(trueRadius - textRect.left);
        text.setPivotY(trueRadius - textRect.top);
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
         * Set the top-margin between text-view and the circle button.
         * Don't need to mind the shadow, BMB will mind this in code.
         *
         * @param textTopMargin top-margin between text-view and the circle button, bigger or
         *                      equal to zero.
         * @return the builder
         */
        public Builder textTopMargin(int textTopMargin) {
            if (textTopMargin < 0) textTopMargin = 0;
            this.textTopMargin = textTopMargin;
            return this;
        }

        /**
         * The width of text-view.
         *
         * @param textWidth width of text-view
         * @return the builder
         */
        public Builder textWidth(int textWidth) {
            this.textWidth = textWidth;
            return this;
        }

        /**
         * The height of text-view.
         *
         * @param textHeight height of text-view
         * @return the builder
         */
        public Builder textHeight(int textHeight) {
            this.textHeight = textHeight;
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
         * Get the height of content.
         * This method is used to calculate the position of boom-button on the screen,
         * don't use it outside.
         *
         * @return the width of content in text-outside-circle-button
         */
        public int getButtonContentWidth() {
            int width = buttonRadius * 2;
            if (textRect != null) width = Math.max(width, textWidth);
            return width;
        }

        /**
         * Get the height of content.
         * This method is used to calculate the position of boom-button on the screen,
         * don't use it outside.
         *
         * @return the height of content in text-outside-circle-button
         */
        public int getButtonContentHeight() {
            int height = buttonRadius * 2;
            if (textRect != null) height = Math.max(height, textRect.bottom - shadowOffsetY - shadowRadius);
            return height;
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
         * Build text-inside circle button, only used in BMB package.
         *
         * @param context the context
         * @return the simple circle button
         */
        @Override
        public TextOutsideCircleButton build(Context context) {
            TextOutsideCircleButton button = new TextOutsideCircleButton(this, context);
            weakReferenceButton(button);
            return button;
        }
    }
}
