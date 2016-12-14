package com.nightonke.boommenu.BoomButtons;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.nightonke.boommenu.R;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 13:13 on 16/11/27
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class HamButton extends BoomButton {

    protected HamButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        init(builder);
    }

    private void init(Builder builder) {
        LayoutInflater.from(context).inflate(R.layout.bmb_ham_button, this, true);
        initAttrs(builder);
        initShadow(builder.shadowCornerRadius);
        initHamButton();
        initText(button);
        initSubText(button);
        initImage();
        centerPoint = new PointF(
                buttonWidth / 2.0f + shadowRadius + shadowOffsetX,
                buttonHeight / 2.0f + shadowRadius + shadowOffsetY);
    }

    private void initAttrs(Builder builder) {
        super.initAttrs(builder);
    }

    @Override
    public ArrayList<View> goneViews() {
        ArrayList<View> goneViews = new ArrayList<>();
        goneViews.add(image);
        goneViews.add(text);
        if (subText != null) goneViews.add(subText);
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
        return buttonWidth + shadowRadius * 2 + shadowOffsetX * 2;
    }

    @Override
    public int trueHeight() {
        return buttonHeight + shadowRadius * 2 + shadowOffsetY * 2;
    }

    @Override
    public int contentWidth() {
        return buttonWidth;
    }

    @Override
    public int contentHeight() {
        return buttonHeight;
    }

    @Override
    public void toPress() {
        if (lastStateIsNormal && ableToHighlight) {
            toPressImage();
            toPressText();
            toPressSubText();
            lastStateIsNormal = false;
        }
    }

    @Override
    public void toNormal() {
        if (!lastStateIsNormal) {
            toNormalImage();
            toNormalText();
            toNormalSubText();
            lastStateIsNormal = true;
        }
    }

    @Override
    public void setRotateAnchorPoints() {

    }

    @Override
    public void setSelfScaleAnchorPoints() {

    }

    /**
     * Builder of ham boom button.
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
     * normalTextRes,
     * highlightedTextRes,
     * unableTextRes,
     * normalText,
     * highlightedText,
     * unableText,
     * normalTextColor,
     * highlightedTextColor,
     * unableTextColor,
     * textRect,
     * textPadding,
     * typeface,
     * maxLines,
     * textGravity,
     * ellipsize,
     * textSize,
     * subNormalTextRes,
     * subHighlightedTextRes,
     * subUnableTextRes,
     * subNormalText,
     * subHighlightedText,
     * subUnableText,
     * subNormalTextColor,
     * subHighlightedTextColor
     * subUnableTextColor,
     * subTextRect,
     * subTextPadding,
     * subTypeface,
     * subMaxLines,
     * subTextGravity,
     * subEllipsize,
     * subTextSize,
     * rippleEffect,
     * normalColor,
     * highlightedColor,
     * rippleColor,
     * unableColor,
     * unable,
     * buttonWidth,
     * buttonHeight.
     */
    public static class Builder extends BoomButtonBuilder {

        public Builder() {
            imageRect = new Rect(0, 0, Util.dp2px(60), Util.dp2px(60));
            textRect = new Rect(Util.dp2px(70), Util.dp2px(10), Util.dp2px(280), Util.dp2px(40));
            textGravity = Gravity.START|Gravity.CENTER_VERTICAL;
            textSize = 15;
        }

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
         * Whether the ham-button contains a sub text-view.
         *
         * @param containsSubText contains a sub text-view or not
         * @return the builder
         */
        public Builder containsSubText(boolean containsSubText) {
            this.containsSubText = containsSubText;
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
         * Set the corner-radius of the shadow.
         *
         * @param shadowCornerRadius corner-radius of the shadow
         * @return the builder
         */
        public Builder shadowCornerRadius(int shadowCornerRadius) {
            this.shadowCornerRadius = shadowCornerRadius;
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
         * Set the text resource when boom-button is at normal-state.
         *
         * @param normalTextRes text resource
         * @return the builder
         */
        public Builder normalTextRes(int normalTextRes) {
            this.normalTextRes = normalTextRes;
            return this;
        }

        /**
         * Set the text resource when boom-button is at highlighted-state.
         *
         * @param highlightedTextRes text resource
         * @return the builder
         */
        public Builder highlightedTextRes(int highlightedTextRes) {
            this.highlightedTextRes = highlightedTextRes;
            return this;
        }

        /**
         * Set the text resource when boom-button is at unable-state.
         *
         * @param unableTextRes text resource
         * @return the builder
         */
        public Builder unableTextRes(int unableTextRes) {
            this.unableTextRes = unableTextRes;
            return this;
        }

        /**
         * Set the text when boom-button is at normal-state.
         *
         * @param normalText text
         * @return the builder
         */
        public Builder normalText(String normalText) {
            this.normalText = normalText;
            return this;
        }

        /**
         * Set the text when boom-button is at highlighted-state.
         *
         * @param highlightedText text
         * @return the builder
         */
        public Builder highlightedText(String highlightedText) {
            this.highlightedText = highlightedText;
            return this;
        }

        /**
         * Set the text when boom-button is at unable-state.
         *
         * @param unableText text
         * @return the builder
         */
        public Builder unableText(String unableText) {
            this.unableText = unableText;
            return this;
        }

        /**
         * Set the color of text when boom-button is at normal-state.
         *
         * @param normalTextColor color of text
         * @return the builder
         */
        public Builder normalTextColor(int normalTextColor) {
            this.normalTextColor = normalTextColor;
            return this;
        }

        /**
         * Set the color of text when boom-button is at highlighted-state.
         *
         * @param highlightedTextColor color of text
         * @return the builder
         */
        public Builder highlightedTextColor(int highlightedTextColor) {
            this.highlightedTextColor = highlightedTextColor;
            return this;
        }

        /**
         * Set the color of text when boom-button is at unable-state.
         *
         * @param unableTextColor color the text
         * @return the builder
         */
        public Builder unableTextColor(int unableTextColor) {
            this.unableTextColor = unableTextColor;
            return this;
        }

        /**
         * Set the rect of text.
         * By this method, you can set the position and size of the text-view in boom-button.
         * For example, builder.textRect(new Rect(0, 50, 100, 100)) will make the
         * text-view's size to be 100 * 50 and margin-top to be 50 pixel.
         *
         * @param textRect the text rect, in pixel.
         * @return the builder
         */
        public Builder textRect(Rect textRect) {
            this.textRect = textRect;
            return this;
        }

        /**
         * Set the padding of text.
         * By this method, you can control the padding in the text-view.
         * For instance, builder.textPadding(new Rect(10, 10, 10, 10)) will make the
         * text-view content 10-pixel padding to itself.
         *
         * @param textPadding the image padding
         * @return the builder
         */
        public Builder textPadding(Rect textPadding) {
            this.textPadding = textPadding;
            return this;
        }

        /**
         * Set the typeface of the text.
         *
         * @param typeface typeface
         * @return the builder
         */
        public Builder typeface(Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        /**
         * Set the maximum of the lines of text-view.
         *
         * @param maxLines maximum lines
         * @return the builder
         */
        public Builder maxLines(int maxLines) {
            this.maxLines = maxLines;
            return this;
        }

        /**
         * Set the gravity of text-view.
         *
         * @param gravity gravity, for example, Gravity.CENTER
         * @return the builder
         */
        public Builder textGravity(int gravity) {
            this.textGravity = gravity;
            return this;
        }

        /**
         * Set the ellipsize of the text-view.
         *
         * @param ellipsize ellipsize
         * @return the builder
         */
        public Builder ellipsize(TextUtils.TruncateAt ellipsize) {
            this.ellipsize = ellipsize;
            return this;
        }

        /**
         * Set the text size of the text-view.
         *
         * @param textSize size of text, in sp
         * @return the builder
         */
        public Builder textSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        /**
         * Set the sub-text resource when boom-button is at normal-state.
         *
         * @param subNormalTextRes sub-text resource
         * @return the builder
         */
        public Builder subNormalTextRes(int subNormalTextRes) {
            this.subNormalTextRes = subNormalTextRes;
            return this;
        }

        /**
         * Set the sub-text resource when boom-button is at highlighted-state.
         *
         * @param subHighlightedTextRes sub-text resource
         * @return the builder
         */
        public Builder subHighlightedTextRes(int subHighlightedTextRes) {
            this.subHighlightedTextRes = subHighlightedTextRes;
            return this;
        }

        /**
         * Set the sub-text resource when boom-button is at unable-state.
         *
         * @param subUnableTextRes sub-text resource
         * @return the builder
         */
        public Builder subUnableTextRes(int subUnableTextRes) {
            this.subUnableTextRes = subUnableTextRes;
            return this;
        }

        /**
         * Set the sub-text when boom-button is at normal-state.
         *
         * @param subNormalText sub-text
         * @return the builder
         */
        public Builder subNormalText(String subNormalText) {
            this.subNormalText = subNormalText;
            return this;
        }

        /**
         * Set the sub-text when boom-button is at highlighted-state.
         *
         * @param subHighlightedText sub-text
         * @return the builder
         */
        public Builder subHighlightedText(String subHighlightedText) {
            this.subHighlightedText = subHighlightedText;
            return this;
        }

        /**
         * Set the sub-text when boom-button is at unable-state.
         *
         * @param subUnableText sub-text
         * @return the builder
         */
        public Builder subUnableText(String subUnableText) {
            this.subUnableText = subUnableText;
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at normal-state.
         *
         * @param subNormalTextColor color of sub-text
         * @return the builder
         */
        public Builder subNormalTextColor(int subNormalTextColor) {
            this.subNormalTextColor = subNormalTextColor;
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at highlighted-state.
         *
         * @param subHighlightedTextColor color of sub-text
         * @return the builder
         */
        public Builder subHighlightedTextColor(int subHighlightedTextColor) {
            this.subHighlightedTextColor = subHighlightedTextColor;
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at unable-state.
         *
         * @param subUnableTextColor color the sub-text
         * @return the builder
         */
        public Builder subUnableTextColor(int subUnableTextColor) {
            this.subUnableTextColor = subUnableTextColor;
            return this;
        }

        /**
         * Set the rect of sub-text.
         * By this method, you can set the position and size of the sub-text-view in boom-button.
         * For example, builder.textRect(new Rect(0, 50, 100, 100)) will make the
         * sub-text-view's size to be 100 * 50 and margin-top to be 50 pixel.
         *
         * @param subTextRect the sub-text rect, in pixel.
         * @return the builder
         */
        public Builder subTextRect(Rect subTextRect) {
            this.subTextRect = subTextRect;
            return this;
        }

        /**
         * Set the padding of sub-text.
         * By this method, you can control the padding in the sub-text-view.
         * For instance, builder.textPadding(new Rect(10, 10, 10, 10)) will make the
         * sub-text-view content 10-pixel padding to itself.
         *
         * @param subTextPadding the sub-text padding
         * @return the builder
         */
        public Builder subTextPadding(Rect subTextPadding) {
            this.subTextPadding = subTextPadding;
            return this;
        }

        /**
         * Set the typeface of the sub-text.
         *
         * @param subTypeface typeface
         * @return the builder
         */
        public Builder subTypeface(Typeface subTypeface) {
            this.subTypeface = subTypeface;
            return this;
        }

        /**
         * Set the maximum of the lines of sub-text-view.
         *
         * @param subMaxLines maximum lines
         * @return the builder
         */
        public Builder subMaxLines(int subMaxLines) {
            this.subMaxLines = subMaxLines;
            return this;
        }

        /**
         * Set the gravity of sub-text-view.
         *
         * @param subTextGravity gravity, for example, Gravity.CENTER
         * @return the builder
         */
        public Builder subTextGravity(int subTextGravity) {
            this.subTextGravity = subTextGravity;
            return this;
        }

        /**
         * Set the ellipsize of the sub-text-view.
         *
         * @param subEllipsize ellipsize
         * @return the builder
         */
        public Builder subEllipsize(TextUtils.TruncateAt subEllipsize) {
            this.subEllipsize = subEllipsize;
            return this;
        }

        /**
         * Set the text size of the sub-text-view.
         *
         * @param subTextSize size of sub-text, in sp
         * @return the builder
         */
        public Builder subTextSize(int subTextSize) {
            this.subTextSize = subTextSize;
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
        public HamButton.Builder pieceColor(int pieceColor) {
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
         * Set the width of boom-button, in pixel.
         *
         * @param buttonWidth width of button
         * @return the builder
         */
        public Builder buttonWidth(int buttonWidth) {
            this.buttonWidth = buttonWidth;
            return this;
        }

        /**
         * Set the height of boom-button, in pixel.
         *
         * @param buttonHeight height of button
         * @return the builder
         */
        public Builder buttonHeight(int buttonHeight) {
            this.buttonHeight = buttonHeight;
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

        //endregion

        //region getters

        /**
         * Get the width of boom-button.
         *
         * @return width of button
         */
        public int getButtonWidth() {
            return buttonWidth;
        }

        /**
         * Get the height of boom-button
         *
         * @return height of button
         */
        public int getButtonHeight() {
            return buttonHeight;
        }

        //endregion

        /**
         * Build ham button, don't use this method.
         *
         * @param context the context
         * @return the ham button
         */
        public HamButton build(Context context) {
            return new HamButton(this, context);
        }
    }
}
