package com.nightonke.boommenu.BoomButtons;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import com.nightonke.boommenu.R;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 01:33 on 16/11/18
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */
public class SimpleCircleButton extends BoomButton {

    private SimpleCircleButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        init(builder);
    }

    private void init(Builder builder) {
        LayoutInflater.from(context).inflate(R.layout.bmb_simple_circle_button, this, true);
        initAttrs(builder);
        initShadow(buttonRadius + shadowRadius);
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
    public void toPress() {
        if (lastStateIsNormal && ableToHighlight) {
            toPressImage();
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

    /**
     * Builder of simple-circle boom button.
     * You can use this builder to set:
     * rotateImage,
     * shadowEffect,
     * shadowOffsetX,
     * shadowOffsetY,
     * shadowRadius,
     * shadowColor,
     * normalImageRes,
     * highlightedImageRes,
     * unableImageRes,
     * normalImageDrawable,
     * highlightedImageDrawable,
     * unableImageRes,
     * imageRect,
     * imagePadding,
     * rippleEffect,
     * normalColor,
     * highlightedColor,
     * rippleColor,
     * unableColor,
     * unable,
     * buttonRadius.
     */
    public static class Builder extends BoomButtonBuilder {

        //region setters

        /**
         * Set the index of the boom-button, don't use this method.
         *
         * @param index the index
         * @return the builder
         */
        public Builder index(int index) {
            this.index = index;
            return this;
        }

        /**
         * Set the listener of the boom-button, don't use this method.
         *
         * @param listener the listener
         * @return the builder
         */
        public Builder innerListener(InnerOnBoomButtonClickListener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * Set listener for when the boom-button is clicked.
         *
         * @param onBMClickListener OnBMClickListener
         * @return the builder
         */
        public Builder listener(OnBMClickListener onBMClickListener) {
            this.onBMClickListener = onBMClickListener;
            return this;
        }

        /**
         * Whether the image-view should rotate.
         *
         * @param rotateImage rotate or not
         * @return the builder
         */
        public Builder rotateImage(boolean rotateImage) {
            this.rotateImage = rotateImage;
            return this;
        }

        /**
         * Whether the boom-button should have a shadow effect.
         *
         * @param shadowEffect have shadow effect or not
         * @return the builder
         */
        public Builder shadowEffect(boolean shadowEffect) {
            this.shadowEffect = shadowEffect;
            return this;
        }

        /**
         * Set the horizontal shadow-offset of the boom-button.
         *
         * @param shadowOffsetX the shadow offset x
         * @return the builder
         */
        public Builder shadowOffsetX(int shadowOffsetX) {
            this.shadowOffsetX = shadowOffsetX;
            return this;
        }

        /**
         * Set the vertical shadow-offset of the boom-button.
         *
         * @param shadowOffsetY the shadow offset y
         * @return the builder
         */
        public Builder shadowOffsetY(int shadowOffsetY) {
            this.shadowOffsetY = shadowOffsetY;
            return this;
        }

        /**
         * Set the radius of shadow of the boom-button.
         *
         * @param shadowRadius the shadow radius
         * @return the builder
         */
        public Builder shadowRadius(int shadowRadius) {
            this.shadowRadius = shadowRadius;
            return this;
        }

        /**
         * Set the color of the shadow of boom-button.
         *
         * @param shadowColor the shadow color
         * @return the builder
         */
        public Builder shadowColor(int shadowColor) {
            this.shadowColor = shadowColor;
            return this;
        }

        /**
         * Set the image resource when boom-button is at normal-state.
         *
         * @param normalImageRes the normal image res
         * @return the builder
         */
        public Builder normalImageRes(int normalImageRes) {
            this.normalImageRes = normalImageRes;
            return this;
        }

        /**
         * Set the image resource when boom-button is at highlighted-state.
         *
         * @param highlightedImageRes the highlighted image res
         * @return the builder
         */
        public Builder highlightedImageRes(int highlightedImageRes) {
            this.highlightedImageRes = highlightedImageRes;
            return this;
        }

        /**
         * Set the image resource when boom-button is at unable-state.
         *
         * @param unableImageRes the unable image res
         * @return the builder
         */
        public Builder unableImageRes(int unableImageRes) {
            this.unableImageRes = unableImageRes;
            return this;
        }

        /**
         * Set the image drawable when boom-button is at normal-state.
         *
         * @param normalImageDrawable the normal image drawable
         * @return the builder
         */
        public Builder normalImageDrawable(Drawable normalImageDrawable) {
            this.normalImageDrawable = normalImageDrawable;
            return this;
        }

        /**
         * Set the image drawable when boom-button is at highlighted-state.
         *
         * @param highlightedImageDrawable the highlighted image drawable
         * @return the builder
         */
        public Builder highlightedImageDrawable(Drawable highlightedImageDrawable) {
            this.highlightedImageDrawable = highlightedImageDrawable;
            return this;
        }

        /**
         * Set the image drawable when boom-button is at unable-state.
         *
         * @param unableImageDrawable the unable image drawable
         * @return the builder
         */
        public Builder unableImageDrawable(Drawable unableImageDrawable) {
            this.unableImageDrawable = unableImageDrawable;
            return this;
        }

        /**
         * Set the rect of image.
         * By this method, you can set the position and size of the image-view in boom-button.
         * For example, builder.imageRect(new Rect(0, 50, 100, 100)) will make the
         * image-view's size to be 100 * 50 and margin-top to be 50 pixel.
         *
         * @param imageRect the image rect, in pixel.
         * @return the builder
         */
        public Builder imageRect(Rect imageRect) {
            this.imageRect = imageRect;
            return this;
        }

        /**
         * Set the padding of image.
         * By this method, you can control the padding in the image-view.
         * For instance, builder.imagePadding(new Rect(10, 10, 10, 10)) will make the
         * image-view content 10-pixel padding to itself.
         *
         * @param imagePadding the image padding
         * @return the builder
         */
        public Builder imagePadding(Rect imagePadding) {
            this.imagePadding = imagePadding;
            return this;
        }

        /**
         * Whether the boom-button should have a ripple effect.
         *
         * @param rippleEffect the ripple effect
         * @return the builder
         */
        public Builder rippleEffect(boolean rippleEffect) {
            this.rippleEffect = rippleEffect;
            return this;
        }

        /**
         * The color of boom-button when it is at normal-state.
         *
         * @param normalColor the normal color
         * @return the builder
         */
        public Builder normalColor(int normalColor) {
            this.normalColor = normalColor;
            return this;
        }

        /**
         * The color of boom-button when it is at highlighted-state.
         *
         * @param highlightedColor the highlighted color
         * @return the builder
         */
        public Builder highlightedColor(int highlightedColor) {
            this.highlightedColor = highlightedColor;
            return this;
        }

        /**
         * The color of boom-button when it is at unable-state.
         *
         * @param unableColor the unable color
         * @return the builder
         */
        public Builder unableColor(int unableColor) {
            this.unableColor = unableColor;
            return this;
        }

        /**
         * The color of boom-button when it is just a piece.
         *
         * @param pieceColor color of piece
         * @return the builder
         */
        public Builder pieceColor(int pieceColor) {
            this.pieceColor = pieceColor;
            return this;
        }

        /**
         * Whether the boom-button is unable, default value is false.
         *
         * @param unable the unable
         * @return the builder
         */
        public Builder unable(boolean unable) {
            this.unable = unable;
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

        //endregion

        //region getters

        /**
         * Gets button radius.
         *
         * @return the button radius
         */
        public int getButtonRadius() {
            return buttonRadius;
        }

        //endregion

        /**
         * Build simple circle button, don't use this method.
         *
         * @param context the context
         * @return the simple circle button
         */
        public SimpleCircleButton build(Context context) {
            return new SimpleCircleButton(this, context);
        }
    }
}
