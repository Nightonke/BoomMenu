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
    protected boolean rotateImage;
    protected boolean rotateText;
    protected boolean containsSubText;  // for ham button

    // piece
    protected Integer pieceColor = null;

    // Shadow
    protected boolean shadowEffect = true;
    protected int shadowOffsetX = 0;
    protected int shadowOffsetY = 0;
    protected int shadowRadius = 0;
    protected int shadowCornerRadius = 0;
    protected int shadowColor;
    protected BMBShadow shadow;

    // Images
    protected int normalImageRes = -1;
    protected int highlightedImageRes = -1;
    protected int unableImageRes = -1;
    protected Drawable normalImageDrawable;
    protected Drawable highlightedImageDrawable;
    protected Drawable unableImageDrawable;
    protected Rect imageRect = null;
    protected Rect imagePadding = null;

    // Text
    protected int normalTextRes = -1;
    protected int highlightedTextRes = -1;
    protected int unableTextRes = -1;
    protected String normalText;
    protected String highlightedText;
    protected String unableText;
    protected int normalTextColor;
    protected int highlightedTextColor;
    protected int unableTextColor;
    protected Rect textRect = null;
    protected Rect textPadding = null;
    protected Typeface typeface;
    protected int maxLines;
    protected int textGravity;
    protected TextUtils.TruncateAt ellipsize;
    protected int textSize;

    // Sub text
    protected int subNormalTextRes = -1;
    protected int subHighlightedTextRes = -1;
    protected int subUnableTextRes = -1;
    protected String subNormalText;
    protected String subHighlightedText;
    protected String subUnableText;
    protected int subNormalTextColor;
    protected int subHighlightedTextColor;
    protected int subUnableTextColor;
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
    protected int highlightedColor;
    protected int unableColor;
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

        normalTextRes = builder.normalTextRes;
        highlightedTextRes = builder.highlightedTextRes;
        unableTextRes = builder.unableTextRes;
        normalText = builder.normalText;
        highlightedText = builder.highlightedText;
        unableText = builder.unableText;
        normalTextColor = builder.normalTextColor;
        highlightedTextColor = builder.highlightedTextColor;
        unableTextColor = builder.unableTextColor;
        textRect = builder.textRect;
        textPadding = builder.textPadding;
        typeface = builder.typeface;
        maxLines = builder.maxLines;
        textGravity = builder.textGravity;
        ellipsize = builder.ellipsize;
        textSize = builder.textSize;

        subNormalTextRes = builder.subNormalTextRes;
        subHighlightedTextRes = builder.subHighlightedTextRes;
        subUnableTextRes = builder.subUnableTextRes;
        subNormalText = builder.subNormalText;
        subHighlightedText = builder.subHighlightedText;
        subUnableText = builder.subUnableText;
        subNormalTextColor = builder.subNormalTextColor;
        subHighlightedTextColor = builder.subHighlightedTextColor;
        subUnableTextColor = builder.subUnableTextColor;
        subTextRect = builder.subTextRect;
        subTextPadding = builder.subTextPadding;
        subTypeface = builder.subTypeface;
        subMaxLines = builder.subMaxLines;
        subTextGravity = builder.subTextGravity;
        subEllipsize = builder.subEllipsize;
        subTextSize = builder.subTextSize;

        rippleEffect = builder.rippleEffect;
        normalColor = builder.normalColor;
        highlightedColor = builder.highlightedColor;
        unableColor = builder.unableColor;
        unable = builder.unable;
        buttonRadius = builder.buttonRadius;
        buttonWidth = builder.buttonWidth;
        buttonHeight = builder.buttonHeight;
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
        LayoutParams params = new LayoutParams(
                imageRect.right - imageRect.left,
                imageRect.bottom - imageRect.top);
        params.leftMargin = imageRect.left;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            params.setMarginStart(imageRect.left);
        params.topMargin = imageRect.top;
        if (imagePadding != null) {
            image.setPadding(
                    imagePadding.left,
                    imagePadding.top,
                    imagePadding.right,
                    imagePadding.bottom);
        }
        button.addView(image, params);
        lastStateIsNormal = false;
        toNormal();
    }

    protected void initText(ViewGroup parent) {
        text = new TextView(context);
        FrameLayout.LayoutParams params = new LayoutParams(
                textRect.right - textRect.left,
                textRect.bottom - textRect.top);
        params.leftMargin = textRect.left;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            params.setMarginStart(textRect.left);
        params.topMargin = textRect.top;
        if (textPadding != null) {
            text.setPadding(
                    textPadding.left,
                    textPadding.top,
                    textPadding.right,
                    textPadding.bottom);
        }
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
            post(new Runnable() {
                @Override
                public void run() {
                    text.setSelected(true);
                }
            });
        }
        parent.addView(text, params);
    }

    protected void initSubText(ViewGroup parent) {
        if (!containsSubText) return;
        subText = new TextView(context);
        FrameLayout.LayoutParams params = new LayoutParams(
                subTextRect.right - subTextRect.left,
                subTextRect.bottom - subTextRect.top);
        params.leftMargin = subTextRect.left;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            params.setMarginStart(subTextRect.left);
        params.topMargin = subTextRect.top;
        if (subTextPadding != null) {
            subText.setPadding(
                    subTextPadding.left,
                    subTextPadding.top,
                    subTextPadding.right,
                    subTextPadding.bottom);
        }
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
            post(new Runnable() {
                @Override
                public void run() {
                    subText.setSelected(true);
                }
            });
        }
        parent.addView(subText, params);
    }

    @SuppressLint("NewApi")
    protected void initCircleButtonDrawable() {
        if (rippleEffectWorks) {
            RippleDrawable rippleDrawable = new RippleDrawable(
                    ColorStateList.valueOf(highlightedColor),
                    Util.getOvalDrawable(button, unable ? unableColor : normalColor),
                    null);
            Util.setDrawable(button, rippleDrawable);
            this.rippleDrawable = rippleDrawable;
        } else {
            nonRippleBitmapDrawable = Util.getOvalStateListBitmapDrawable(
                    button,
                    buttonRadius,
                    normalColor,
                    highlightedColor,
                    unableColor);
            if (isNeededColorAnimation()) {
                // Then we need to create 2 drawables to perform the color-transaction effect.
                // Because gradient-drawable is able to change the color,
                // but not able to perform a click-effect.
                nonRippleGradientDrawable = Util.getOvalDrawable(button, unable ? unableColor : normalColor);
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
                if (listener != null) listener.onButtonClick(index, BoomButton.this);
                if (onBMClickListener != null) onBMClickListener.onBoomButtonClick(index);
            }
        });

        initCircleButtonDrawable();

        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toPress();
                            ableToHighlight = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toPress();
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
                    ColorStateList.valueOf(highlightedColor),
                    Util.getRectangleDrawable(button, buttonCornerRadius, unable ? unableColor : normalColor),
                    null);
            Util.setDrawable(button, rippleDrawable);
            this.rippleDrawable = rippleDrawable;
        } else {
            nonRippleBitmapDrawable = Util.getRectangleStateListBitmapDrawable(
                    button,
                    buttonWidth,
                    buttonHeight,
                    buttonCornerRadius,
                    normalColor,
                    highlightedColor,
                    unableColor);
            if (isNeededColorAnimation()) {
                // Then we need to create 2 drawables to perform the color-transaction effect.
                // Because gradient-drawable is able to change the color,
                // but not able to perform a click-effect.
                nonRippleGradientDrawable = Util.getRectangleDrawable(button, buttonCornerRadius, unable ? unableColor : normalColor);
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
                if (listener != null) listener.onButtonClick(index, BoomButton.this);
                if (onBMClickListener != null) onBMClickListener.onBoomButtonClick(index);
            }
        });

        initHamButtonDrawable();

        button.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toPress();
                            ableToHighlight = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Util.pointInView(new PointF(event.getX(), event.getY()), button)) {
                            toPress();
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

    protected void toPressImage() {
        if (unable && unableImageRes != -1) image.setImageResource(unableImageRes);
        else if (unable && unableImageDrawable != null) image.setImageDrawable(unableImageDrawable);
        else if (highlightedImageRes != -1) image.setImageResource(highlightedImageRes);
        else if (highlightedImageDrawable != null) image.setImageDrawable(highlightedImageDrawable);
    }

    protected void toNormalImage() {
        if (unable && unableImageRes != -1) image.setImageResource(unableImageRes);
        else if (unable && unableImageDrawable != null) image.setImageDrawable(unableImageDrawable);
        else if (normalImageRes != -1) image.setImageResource(normalImageRes);
        else if (normalImageDrawable != null) image.setImageDrawable(normalImageDrawable);
    }

    protected void toPressText() {
        if (unable && unableTextRes != -1) setText(unableTextRes);
        else if (unable && unableText != null) setText(unableText);
        else if (highlightedTextRes != -1) setText(highlightedTextRes);
        else if (highlightedText != null) setText(highlightedText);

        if (unable) text.setTextColor(unableTextColor);
        else text.setTextColor(highlightedTextColor);
    }

    protected void toNormalText() {
        if (unable && unableTextRes != -1) setText(unableTextRes);
        else if (unable && unableText != null) setText(unableText);
        else if (normalTextRes != -1) setText(normalTextRes);
        else if (normalText != null) setText(normalText);

        if (unable) text.setTextColor(unableTextColor);
        else text.setTextColor(normalTextColor);
    }

    protected void toPressSubText() {
        if (unable && subUnableTextRes != -1) setSubText(subUnableTextRes);
        else if (unable && subUnableText != null) setSubText(subUnableText);
        else if (subHighlightedTextRes != -1) setSubText(subHighlightedTextRes);
        else if (subHighlightedText != null) setSubText(subHighlightedText);

        if (subText != null) {
            if (unable) subText.setTextColor(subUnableTextColor);
            else subText.setTextColor(subHighlightedTextColor);
        }
    }

    protected void toNormalSubText() {
        if (unable && subUnableTextRes != -1) setSubText(subUnableTextRes);
        else if (unable && subUnableText != null) setSubText(subUnableText);
        else if (subNormalTextRes != -1) setSubText(subNormalTextRes);
        else if (subNormalText != null) setSubText(subNormalText);

        if (subText != null) {
            if (unable) subText.setTextColor(subUnableTextColor);
            else subText.setTextColor(subNormalTextColor);
        }
    }

    private void setText(int stringRes) {
        setText((String) getContext().getResources().getText(stringRes));
    }

    private void setText(String string) {
        if (string != null && !string.equals(text.getText())) text.setText(string);
    }

    private void setSubText(int stringRes) {
        setSubText((String) getContext().getResources().getText(stringRes));
    }

    private void setSubText(String string) {
        if (string != null && subText != null && !string.equals(subText.getText())) subText.setText(string);
    }

    public int pieceColor() {
        if (pieceColor == null) return unable ? unableColor : normalColor;
        else return pieceColor;
    }

    public int buttonColor() {
        if (unable) return unableColor;
        else return normalColor;
    }

    public boolean isNeededColorAnimation() {
        if (pieceColor == null) return false;
        if (unable) return pieceColor.compareTo(unableColor) != 0;
        else return pieceColor.compareTo(normalColor) != 0;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        unable = !enabled;
    }

    public void cleanListener() {
        listener = null;
    }

    public void willShow() {
        if (!rippleEffectWorks && isNeededColorAnimation())
            Util.setDrawable(button, nonRippleGradientDrawable);
    }

    public void didShow() {
        if (!rippleEffectWorks && isNeededColorAnimation())
            Util.setDrawable(button, nonRippleBitmapDrawable);
    }

    public void willHide() {
        if (!rippleEffectWorks && isNeededColorAnimation())
            Util.setDrawable(button, nonRippleGradientDrawable);
    }

    public void didHide() {

    }

    protected void setButtonColor(int color) {
        if (rippleEffectWorks) {
            if (rippleDrawable != null) ((GradientDrawable)rippleDrawable.getDrawable(0)).setColor(color);
        } else {
            if (nonRippleGradientDrawable != null) nonRippleGradientDrawable.setColor(color);
        }
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
        button.setClickable(clickable);
    }

    public abstract ArrayList<View> goneViews();
    public abstract ArrayList<View> rotateViews();
    public abstract int trueWidth();
    public abstract int trueHeight();
    public abstract int contentWidth();
    public abstract int contentHeight();
    protected abstract void toPress();
    protected abstract void toNormal();
    public abstract void setRotateAnchorPoints();
    public abstract void setSelfScaleAnchorPoints();
    public PointF centerPoint;
}
