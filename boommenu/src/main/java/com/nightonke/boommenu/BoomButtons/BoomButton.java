package com.nightonke.boommenu.BoomButtons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.BMBShadow;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.R;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 01:06 on 16/11/18
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public abstract class BoomButton extends FrameLayout {

    // Basic
    protected Context context;
    protected int index = -1;
    protected InnerOnBoomButtonClickListener listener;
    protected OnBMClickListener onBMClickListener;
    protected boolean lastStateIsNormal = true;
    protected boolean ableToHighlight = true;
    protected FrameLayout button;
    protected int buttonRadius;  // for simple/text-inside/text-outside circle button
    protected int buttonWidth;  // for ham button
    protected int buttonHeight;  // for ham button
    protected int buttonCornerRadius;  // for ham button
    protected boolean isRound;
    protected boolean rotateImage;
    protected boolean rotateText;
    protected boolean containsSubText;  // for ham button
    protected ButtonEnum buttonEnum = ButtonEnum.Unknown;
    private boolean touchable = false;

    // piece
    protected Integer pieceColor = null;
    protected int pieceColorRes = 0;

    // Shadow
    protected boolean shadowEffect = true;
    protected int shadowOffsetX = 0;
    protected int shadowOffsetY = 0;
    protected int shadowRadius = 0;
    protected int shadowCornerRadius = 0;
    protected int shadowColor;
    protected BMBShadow shadow;

    // Images
    protected int normalImageRes = 0;
    protected Drawable normalImageDrawable;
    protected int highlightedImageRes = 0;
    protected Drawable highlightedImageDrawable;
    protected int unableImageRes = 0;
    protected Drawable unableImageDrawable;
    protected Rect imageRect = null;
    protected Rect imagePadding = null;

    // Text
    protected int normalTextRes = 0;
    protected String normalText;
    protected int highlightedTextRes = 0;
    protected String highlightedText;
    protected int unableTextRes = 0;
    protected String unableText;
    protected int normalTextColor;
    protected int normalTextColorRes = 0;
    protected int highlightedTextColor;
    protected int highlightedTextColorRes = 0;
    protected int unableTextColor;
    protected int unableTextColorRes = 0;
    protected Rect textRect = null;
    protected Rect textPadding = null;
    protected Typeface typeface;
    protected int maxLines;
    protected int textGravity;
    protected TextUtils.TruncateAt ellipsize;
    protected int textSize;

    // Sub text
    protected int subNormalTextRes = 0;
    protected String subNormalText;
    protected int subHighlightedTextRes = 0;
    protected String subHighlightedText;
    protected int subUnableTextRes = 0;
    protected String subUnableText;
    protected int subNormalTextColor;
    protected int subNormalTextColorRes = 0;
    protected int subHighlightedTextColor;
    protected int subHighlightedTextColorRes = 0;
    protected int subUnableTextColor;
    protected int subUnableTextColorRes = 0;
    protected Rect subTextRect = null;
    protected Rect subTextPadding = null;
    protected Typeface subTypeface;
    protected int subMaxLines;
    protected int subTextGravity;
    protected TextUtils.TruncateAt subEllipsize;
    protected int subTextSize;

    // Text for text-outside-circle-button
    protected int textTopMargin;
    protected int textWidth;
    protected int textHeight;
    protected int trueRadius;

    // Button Colors
    protected boolean rippleEffect = true;
    protected int normalColor;
    protected int normalColorRes = 0;
    protected int highlightedColor;
    protected int highlightedColorRes = 0;
    protected int unableColor;
    protected int unableColorRes = 0;
    protected boolean unable = false;
    protected boolean rippleEffectWorks = true;
    protected RippleDrawable rippleDrawable;
    protected StateListDrawable nonRippleBitmapDrawable;
    protected GradientDrawable nonRippleGradientDrawable;

    // Views
    protected ViewGroup layout;
    protected ImageView image;
    protected TextView text;
    protected TextView subText;

    protected BoomButton(Context context) {
        super(context);
    }

    protected void initAttrs(BoomButtonBuilder builder) {
        index = builder.index;
        listener = builder.listener;
        onBMClickListener = builder.onBMClickListener;
        rotateImage = builder.rotateImage;
        rotateText = builder.rotateText;
        containsSubText = builder.containsSubText;

        pieceColor = builder.pieceColor;
        pieceColorRes = builder.pieceColorRes;

        shadowEffect = builder.shadowEffect;
        if (shadowEffect) {
            shadowOffsetX = builder.shadowOffsetX;
            shadowOffsetY = builder.shadowOffsetY;
            shadowRadius = builder.shadowRadius;
            shadowCornerRadius = builder.shadowCornerRadius;
            shadowColor = builder.shadowColor;
        }

        normalImageRes = builder.normalImageRes;
        highlightedImageRes = builder.highlightedImageRes;
        unableImageRes = builder.unableImageRes;
        normalImageDrawable = builder.normalImageDrawable;
        highlightedImageDrawable = builder.highlightedImageDrawable;
        unableImageDrawable = builder.unableImageDrawable;
        imageRect = builder.imageRect;
        imagePadding = builder.imagePadding;

        normalText = builder.normalText;
        normalTextRes = builder.normalTextRes;
        highlightedText = builder.highlightedText;
        highlightedTextRes = builder.highlightedTextRes;
        unableText = builder.unableText;
        unableTextRes = builder.unableTextRes;
        normalTextColor = builder.normalTextColor;
        normalTextColorRes = builder.normalTextColorRes;
        highlightedTextColor = builder.highlightedTextColor;
        highlightedTextColorRes = builder.highlightedTextColorRes;
        unableTextColor = builder.unableTextColor;
        unableTextColorRes = builder.unableTextColorRes;
        textRect = builder.textRect;
        textPadding = builder.textPadding;
        typeface = builder.typeface;
        maxLines = builder.maxLines;
        textGravity = builder.textGravity;
        ellipsize = builder.ellipsize;
        textSize = builder.textSize;

        subNormalText = builder.subNormalText;
        subNormalTextRes = builder.subNormalTextRes;
        subHighlightedText = builder.subHighlightedText;
        subHighlightedTextRes = builder.subHighlightedTextRes;
        subUnableText = builder.subUnableText;
        subUnableTextRes = builder.subUnableTextRes;
        subNormalTextColor = builder.subNormalTextColor;
        subNormalTextColorRes = builder.subNormalTextColorRes;
        subHighlightedTextColor = builder.subHighlightedTextColor;
        subHighlightedTextColorRes = builder.subHighlightedTextColorRes;
        subUnableTextColor = builder.subUnableTextColor;
        subUnableTextColorRes = builder.subUnableTextColorRes;
        subTextRect = builder.subTextRect;
        subTextPadding = builder.subTextPadding;
        subTypeface = builder.subTypeface;
        subMaxLines = builder.subMaxLines;
        subTextGravity = builder.subTextGravity;
        subEllipsize = builder.subEllipsize;
        subTextSize = builder.subTextSize;

        rippleEffect = builder.rippleEffect;
        normalColor = builder.normalColor;
        normalColorRes = builder.normalColorRes;
        highlightedColor = builder.highlightedColor;
        highlightedColorRes = builder.highlightedColorRes;
        unableColor = builder.unableColor;
        unableColorRes = builder.unableColorRes;
        unable = builder.unable;
        buttonRadius = builder.buttonRadius;
        buttonWidth = builder.buttonWidth;
        buttonHeight = builder.buttonHeight;
        isRound = builder.isRound;
        if (buttonEnum == ButtonEnum.SimpleCircle
                || buttonEnum == ButtonEnum.TextInsideCircle
                || buttonEnum == ButtonEnum.TextOutsideCircle) {
            if (isRound) buttonCornerRadius = builder.buttonRadius;
            else buttonCornerRadius = builder.buttonCornerRadius;
        } else buttonCornerRadius = builder.buttonCornerRadius;
        buttonCornerRadius = builder.buttonCornerRadius;
        rippleEffectWorks = rippleEffect && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

        // for text-outside-circle-button
        textTopMargin = builder.textTopMargin;
        textWidth = builder.textWidth;
        textHeight = builder.textHeight;
        if (builder instanceof TextOutsideCircleButton.Builder) {
            int buttonAndShadowWidth = buttonRadius * 2 + shadowOffsetX * 2 + shadowRadius * 2;
            if (textWidth > buttonAndShadowWidth) {
                textRect = new Rect(
                        0,
                        shadowOffsetY + shadowRadius + buttonRadius * 2 + textTopMargin,
                        textWidth,
                        shadowOffsetY + shadowRadius + buttonRadius * 2 + textTopMargin + textHeight);
            } else {
                textRect = new Rect(
                        (buttonAndShadowWidth - textWidth) / 2,
                        shadowOffsetY + shadowRadius + buttonRadius * 2 + textTopMargin,
                        (buttonAndShadowWidth - textWidth) / 2 + textWidth,
                        shadowOffsetY + shadowRadius + buttonRadius * 2 + textTopMargin + textHeight);
            }
            trueRadius = (int) (Util.distance(
                    new Point(
                            shadowOffsetX + shadowRadius + buttonRadius,
                            shadowOffsetY + shadowRadius + buttonRadius),
                    new Point(
                            textRect.right,
                            textRect.bottom)) + 1);
            if (textWidth > buttonAndShadowWidth)
                textRect.offset(
                        trueRadius - textWidth / 2,
                        trueRadius - (shadowOffsetY + shadowRadius + buttonRadius));
            else
                textRect.offset(
                        trueRadius - (shadowOffsetX + shadowRadius + buttonRadius),
                        trueRadius - (shadowOffsetY + shadowRadius + buttonRadius));
        }

    }

    protected void initTextOutsideCircleButtonLayout() {
        layout = (ViewGroup) findViewById(R.id.layout);
        FrameLayout.LayoutParams params = new LayoutParams(trueRadius * 2, trueRadius * 2);
        layout.setLayoutParams(params);
    }

    protected void initShadow(int shadowCornerRadius) {
        if (shadowEffect) {
            shadow = (BMBShadow) findViewById(R.id.shadow);
            shadow.setShadowOffsetX(shadowOffsetX);
            shadow.setShadowOffsetY(shadowOffsetY);
            shadow.setShadowColor(shadowColor);
            shadow.setShadowRadius(shadowRadius);
            shadow.setShadowCornerRadius(shadowCornerRadius);
        }
    }

    protected void initImage() {
        image = new ImageView(context);
        updateImageRect();
        updateImagePadding();
        button.addView(image);
        lastStateIsNormal = false;
        toNormal();
    }

    protected void initText(ViewGroup parent) {
        text = new TextView(context);
        updateTextRect();
        updateTextPadding();
        if (typeface != null) text.setTypeface(typeface);
        text.setMaxLines(maxLines);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        text.setGravity(textGravity);
        text.setEllipsize(ellipsize);
        if (ellipsize == TextUtils.TruncateAt.MARQUEE) {
            text.setSingleLine(true);
            text.setMarqueeRepeatLimit(-1);
            text.setHorizontallyScrolling(true);
            text.setFocusable(true);
            text.setFocusableInTouchMode(true);
            text.setFreezesText(true);
        }
        parent.addView(text);
    }

    protected void initSubText(ViewGroup parent) {
        if (!containsSubText) return;
        subText = new TextView(context);
        updateSubTextRect();
        updateSubTextPadding();
        if (subTypeface != null) subText.setTypeface(subTypeface);
        subText.setMaxLines(maxLines);
        subText.setTextSize(TypedValue.COMPLEX_UNIT_SP, subTextSize);
        subText.setGravity(subTextGravity);
        subText.setEllipsize(subEllipsize);
        if (subEllipsize == TextUtils.TruncateAt.MARQUEE) {
            subText.setSingleLine(true);
            subText.setMarqueeRepeatLimit(-1);
            subText.setHorizontallyScrolling(true);
            subText.setFocusable(true);
            subText.setFocusableInTouchMode(true);
            subText.setFreezesText(true);
        }
        parent.addView(subText);
    }

    @SuppressLint("NewApi")
    protected void initCircleButtonDrawable() {
        if (rippleEffectWorks) {
            GradientDrawable gradientDrawable = isRound ?
                    Util.getOvalDrawable(button, unable ? unableColor() : normalColor()) :
                    Util.getRectangleDrawable(button, buttonCornerRadius, unable ? unableColor() : normalColor());
            RippleDrawable rippleDrawable = new RippleDrawable(
                    ColorStateList.valueOf(highlightedColor()),
                    gradientDrawable,
                    null);
            Util.setDrawable(button, rippleDrawable);
            this.rippleDrawable = rippleDrawable;
        } else {
            if (isRound)
                nonRippleBitmapDrawable = Util.getOvalStateListBitmapDrawable(
                        button,
                        buttonRadius,
                        normalColor(),
                        highlightedColor(),
                        unableColor());
            else
                nonRippleBitmapDrawable = Util.getRectangleStateListBitmapDrawable(
                        button,
                        buttonWidth,
                        buttonHeight,
                        buttonCornerRadius,
                        normalColor(),
                        highlightedColor(),
                        unableColor());
            if (isNeededColorAnimation()) {
                // Then we need to create 2 drawables to perform the color-transaction effect.
                // Because gradient-drawable is able to change the color,
                // but not able to perform a click-effect.
                nonRippleGradientDrawable = Util.getOvalDrawable(button, unable ? unableColor() : normalColor());
            }
            Util.setDrawable(button, nonRippleBitmapDrawable);
        }
    }

    @SuppressLint("NewApi")
    protected void initCircleButton() {
        button = (FrameLayout) findViewById(R.id.button);
        LayoutParams params = (LayoutParams) button.getLayoutParams();
        params.width = buttonRadius * 2;
        params.height = buttonRadius * 2;
        button.setLayoutParams(params);
        button.setEnabled(!unable);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!touchable) return;
                if (listener != null) listener.onButtonClick(index, BoomButton.this);
                if (onBMClickListener != null) onBMClickListener.onBoomButtonClick(index);
            }
        });

        initCircleButtonDrawable();

        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!touchable) return false;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toHighlighted();
                            ableToHighlight = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toHighlighted();
                        } else {
                            ableToHighlight = false;
                            toNormal();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ableToHighlight = false;
                        toNormal();
                        break;
                }
                return false;
            }
        });
    }

    @SuppressLint("NewApi")
    protected void initHamButtonDrawable() {
        if (rippleEffectWorks) {
            RippleDrawable rippleDrawable = new RippleDrawable(
                    ColorStateList.valueOf(highlightedColor()),
                    Util.getRectangleDrawable(button, buttonCornerRadius, unable ? unableColor() : normalColor()),
                    null);
            Util.setDrawable(button, rippleDrawable);
            this.rippleDrawable = rippleDrawable;
        } else {
            nonRippleBitmapDrawable = Util.getRectangleStateListBitmapDrawable(
                    button,
                    buttonWidth,
                    buttonHeight,
                    buttonCornerRadius,
                    normalColor(),
                    highlightedColor(),
                    unableColor());
            if (isNeededColorAnimation()) {
                // Then we need to create 2 drawables to perform the color-transaction effect.
                // Because gradient-drawable is able to change the color,
                // but not able to perform a click-effect.
                nonRippleGradientDrawable = Util.getRectangleDrawable(button, buttonCornerRadius, unable ? unableColor() : normalColor());
            }
            Util.setDrawable(button, nonRippleBitmapDrawable);
        }
    }

    @SuppressLint("NewApi")
    protected void initHamButton() {
        button = (FrameLayout) findViewById(R.id.button);
        LayoutParams params = (LayoutParams) button.getLayoutParams();
        params.width = buttonWidth;
        params.height = buttonHeight;
        button.setLayoutParams(params);
        button.setEnabled(!unable);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!touchable) return;
                if (listener != null) listener.onButtonClick(index, BoomButton.this);
                if (onBMClickListener != null) onBMClickListener.onBoomButtonClick(index);
            }
        });

        initHamButtonDrawable();

        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!touchable) return false;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toHighlighted();
                            ableToHighlight = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toHighlighted();
                        } else {
                            ableToHighlight = false;
                            toNormal();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        ableToHighlight = false;
                        toNormal();
                        break;
                }
                return false;
            }
        });
    }

    public FrameLayout.LayoutParams place(int left, int top, int width, int height) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
        layoutParams.leftMargin = left;
        layoutParams.topMargin = top;
        setLayoutParams(layoutParams);
        return layoutParams;
    }

    /**
     * When the parameters about image are changed by builder, the corresponding builder should
     * call this method to update the image on the ImageView.
     */
    void updateImage() {
        if (lastStateIsNormal) toNormalImage();
        else toHighlightedImage();
    }

    void updateText() {
        if (lastStateIsNormal) toNormalText();
        else toHighlightedText();
    }

    void updateSubText() {
        if (lastStateIsNormal) toNormalSubText();
        else toHighlightedSubText();
    }

    void updateImageRect() {
        LayoutParams params = new LayoutParams(
                imageRect.right - imageRect.left,
                imageRect.bottom - imageRect.top);
        params.leftMargin = imageRect.left;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            params.setMarginStart(imageRect.left);
        params.topMargin = imageRect.top;
        if (image != null) image.setLayoutParams(params);
    }

    void updateImagePadding() {
        if (imagePadding != null && image != null) {
            image.setPadding(
                    imagePadding.left,
                    imagePadding.top,
                    imagePadding.right,
                    imagePadding.bottom);
        }
    }

    void updateTextRect() {
        FrameLayout.LayoutParams params = new LayoutParams(
                textRect.right - textRect.left,
                textRect.bottom - textRect.top);
        params.leftMargin = textRect.left;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            params.setMarginStart(textRect.left);
        params.topMargin = textRect.top;
        if (text != null) text.setLayoutParams(params);
    }

    void updateTextPadding() {
        if (textPadding != null && text != null) {
            text.setPadding(
                    textPadding.left,
                    textPadding.top,
                    textPadding.right,
                    textPadding.bottom);
        }
    }

    void updateSubTextRect() {
        FrameLayout.LayoutParams params = new LayoutParams(
                subTextRect.right - subTextRect.left,
                subTextRect.bottom - subTextRect.top);
        params.leftMargin = subTextRect.left;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            params.setMarginStart(subTextRect.left);
        params.topMargin = subTextRect.top;
        if (subText != null) subText.setLayoutParams(params);
    }

    void updateSubTextPadding() {
        if (subTextPadding != null && subText != null) {
            subText.setPadding(
                    subTextPadding.left,
                    subTextPadding.top,
                    subTextPadding.right,
                    subTextPadding.bottom);
        }
    }

    void updateButtonDrawable() {
        if (buttonEnum == ButtonEnum.SimpleCircle
                || buttonEnum == ButtonEnum.TextInsideCircle
                || buttonEnum == ButtonEnum.TextOutsideCircle) {
            initCircleButtonDrawable();
        } else initHamButtonDrawable();
    }

    void updateUnable() {
        if (rippleEffectWorks) updateButtonDrawable();
        button.setEnabled(!unable);
        if (lastStateIsNormal) {
            toNormalImage();
            toNormalText();
            toNormalSubText();
        } else {
            toHighlightedImage();
            toHighlightedText();
            toHighlightedSubText();
        }
    }

    protected void toNormalImage() {
        if (unable) Util.setDrawable(image, unableImageRes, unableImageDrawable);
        else Util.setDrawable(image, normalImageRes, normalImageDrawable);
    }

    protected void toHighlightedImage() {
        if (unable) Util.setDrawable(image, unableImageRes, unableImageDrawable);
        else Util.setDrawable(image, highlightedImageRes, highlightedImageDrawable);
    }

    protected void toNormalText() {
        if (unable) {
            Util.setText(text, unableTextRes, unableText);
            Util.setTextColor(text, unableTextColorRes, unableTextColor);
        } else {
            Util.setText(text, normalTextRes, normalText);
            Util.setTextColor(text, normalTextColorRes, normalTextColor);
        }
    }

    protected void toHighlightedText() {
        if (unable) {
            Util.setText(text, unableTextRes, unableText);
            Util.setTextColor(text, unableTextColorRes, unableTextColor);
        } else {
            Util.setText(text, highlightedTextRes, highlightedText);
            Util.setTextColor(text, highlightedTextColorRes, highlightedTextColor);
        }
    }

    protected void toNormalSubText() {
        if (unable) {
            Util.setText(subText, subUnableTextRes, subUnableText);
            Util.setTextColor(subText, subUnableTextColorRes, subUnableTextColor);
        } else {
            Util.setText(subText, subNormalTextRes, subNormalText);
            Util.setTextColor(subText, subNormalTextColorRes, subNormalTextColor);
        }
    }

    protected void toHighlightedSubText() {
        if (unable) {
            Util.setText(subText, subUnableTextRes, subUnableText);
            Util.setTextColor(subText, subUnableTextColorRes, subUnableTextColor);
        } else {
            Util.setText(subText, subHighlightedTextRes, subHighlightedText);
            Util.setTextColor(subText, subHighlightedTextColorRes, subHighlightedTextColor);
        }
    }

    public int pieceColor() {
        if (pieceColor == null && pieceColorRes == 0)
            if (unable) return unableColor();
            else return normalColor();
        else if (pieceColor == null) return Util.getColor(context, pieceColorRes);
        else return Util.getColor(context, pieceColorRes, pieceColor);
    }

    public int buttonColor() {
        if (unable) return unableColor();
        else return normalColor();
    }

    public boolean isNeededColorAnimation() {
        Integer pieceColor = pieceColor();
        if (unable) return pieceColor.compareTo(unableColor()) != 0;
        else return pieceColor.compareTo(normalColor()) != 0;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        unable = !enabled;
    }

    public void willShow() {
        touchable = false;
        if (!rippleEffectWorks && isNeededColorAnimation())
            Util.setDrawable(button, nonRippleGradientDrawable);
        else updateButtonDrawable();
    }

    public void didShow() {
        touchable = true;
        if (!rippleEffectWorks && isNeededColorAnimation())
            Util.setDrawable(button, nonRippleBitmapDrawable);
        if (text != null) text.setSelected(true);
        if (subText != null) subText.setSelected(true);
    }

    public void willHide() {
        touchable = false;
        if (!rippleEffectWorks && isNeededColorAnimation())
            Util.setDrawable(button, nonRippleGradientDrawable);
    }

    public void didHide() {
        if (text != null) text.setSelected(false);
        if (subText != null) subText.setSelected(false);
    }

    public void hideAllGoneViews() {
        for (View view : goneViews()) view.setAlpha(0);
    }

    public boolean prepareColorTransformAnimation() {
        if (rippleEffectWorks) {
            if (rippleDrawable == null) throw new RuntimeException("Background drawable is null!");
        } else if (nonRippleGradientDrawable == null) {
            throw new RuntimeException("Background drawable is null!");
        }
        return rippleEffectWorks;
    }

    protected void setNonRippleButtonColor(int color) {
        nonRippleGradientDrawable.setColor(color);
    }

    protected void setRippleButtonColor(int color) {
        ((GradientDrawable)rippleDrawable.getDrawable(0)).setColor(color);
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        button.setClickable(clickable);
    }

    protected int normalColor() {
        return Util.getColor(context, normalColorRes, normalColor);
    }

    protected int highlightedColor() {
        return Util.getColor(context, highlightedColorRes, highlightedColor);
    }

    protected int unableColor() {
        return Util.getColor(context, unableColorRes, unableColor);
    }

    /**
     * Get the layout view of a boom button.
     *
     * @return layout view
     */
    public ViewGroup getLayout() { return layout; }

    /**
     * Get the button layout view of a boom button.
     *
     * @return button layout view
     */
    public FrameLayout getButton() { return button; }

    /**
     * Get the shadow view of a boom button.
     *
     * @return shadow view
     */
    public BMBShadow getShadow() { return shadow; }

    /**
     * Get the image view of a boom button.
     *
     * @return image view
     */
    public ImageView getImageView() {
        return image;
    }

    /**
     * Get the text view of a boom button.
     *
     * @return text view
     */
    public TextView getTextView() {
        return text;
    }

    /**
     * Get the sub text view of a boom button.
     *
     * @return sub text view
     */
    public TextView getSubTextView() {
        return subText;
    }

    public abstract ButtonEnum type();
    public abstract ArrayList<View> goneViews();
    public abstract ArrayList<View> rotateViews();
    public abstract int trueWidth();
    public abstract int trueHeight();
    public abstract int contentWidth();
    public abstract int contentHeight();
    protected abstract void toHighlighted();
    protected abstract void toNormal();
    public abstract void setRotateAnchorPoints();
    public abstract void setSelfScaleAnchorPoints();
    public PointF centerPoint;
}
