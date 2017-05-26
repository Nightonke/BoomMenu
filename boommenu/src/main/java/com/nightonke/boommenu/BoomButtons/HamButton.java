package com.nightonke.boommenu.BoomButtons;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.R;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 13:13 on 16/11/27
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

@SuppressWarnings("unused")
public class HamButton extends BoomButton {

    private HamButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        this.buttonEnum = ButtonEnum.Ham;
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
    public ButtonEnum type() {
        return ButtonEnum.Ham;
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
    public void toHighlighted() {
        if (lastStateIsNormal && ableToHighlight) {
            toHighlightedImage();
            toHighlightedText();
            toHighlightedSubText();
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

    public static class Builder extends BoomButtonWithTextBuilder<Builder> {

        public Builder() {
            imageRect = new Rect(0, 0, Util.dp2px(60), Util.dp2px(60));
            textRect = new Rect(Util.dp2px(70), Util.dp2px(10), Util.dp2px(280), Util.dp2px(40));
            textGravity = Gravity.START|Gravity.CENTER_VERTICAL;
            textSize = 15;
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
         * Set the sub-text when boom-button is at normal-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subNormalText sub-text
         * @return the builder
         */
        public Builder subNormalText(String subNormalText) {
            if (this.subNormalText == null || !this.subNormalText.equals(subNormalText)) {
                this.subNormalText = subNormalText;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalText = subNormalText;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the sub-text resource when boom-button is at normal-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subNormalTextRes sub-text resource
         * @return the builder
         */
        public Builder subNormalTextRes(int subNormalTextRes) {
            if (this.subNormalTextRes != subNormalTextRes) {
                this.subNormalTextRes = subNormalTextRes;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalTextRes = subNormalTextRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the sub-text when boom-button is at highlighted-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subHighlightedText sub-text
         * @return the builder
         */
        public Builder subHighlightedText(String subHighlightedText) {
            if (this.subHighlightedText == null || !this.subHighlightedText.equals(subHighlightedText)) {
                this.subHighlightedText = subHighlightedText;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedText = subHighlightedText;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the sub-text resource when boom-button is at highlighted-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subHighlightedTextRes sub-text resource
         * @return the builder
         */
        public Builder subHighlightedTextRes(int subHighlightedTextRes) {
            if (this.subHighlightedTextRes != subHighlightedTextRes) {
                this.subHighlightedTextRes = subHighlightedTextRes;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedTextRes = subHighlightedTextRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the sub-text when boom-button is at unable-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subUnableText sub-text
         * @return the builder
         */
        public Builder subUnableText(String subUnableText) {
            if (this.subUnableText == null || !this.subUnableText.equals(subUnableText)) {
                this.subUnableText = subUnableText;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableText = subUnableText;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the sub-text resource when boom-button is at unable-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subUnableTextRes sub-text resource
         * @return the builder
         */
        public Builder subUnableTextRes(int subUnableTextRes) {
            if (this.subUnableTextRes != subUnableTextRes) {
                this.subUnableTextRes = subUnableTextRes;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableTextRes = subUnableTextRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at normal-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subNormalTextColor color of sub-text
         * @return the builder
         */
        public Builder subNormalTextColor(int subNormalTextColor) {
            if (this.subNormalTextColor != subNormalTextColor) {
                this.subNormalTextColor = subNormalTextColor;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalTextColor = subNormalTextColor;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at normal-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subNormalTextColorRes color resource of sub-text
         * @return the builder
         */
        public Builder subNormalTextColorRes(int subNormalTextColorRes) {
            if (this.subNormalTextColorRes != subNormalTextColorRes) {
                this.subNormalTextColorRes = subNormalTextColorRes;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalTextColorRes = subNormalTextColorRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at highlighted-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subHighlightedTextColor color of sub-text
         * @return the builder
         */
        public Builder subHighlightedTextColor(int subHighlightedTextColor) {
            if (this.subHighlightedTextColor != subHighlightedTextColor) {
                this.subHighlightedTextColor = subHighlightedTextColor;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedTextColor = subHighlightedTextColor;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at highlighted-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subHighlightedTextColorRes color resource of sub-text
         * @return the builder
         */
        public Builder subHighlightedTextColorRes(int subHighlightedTextColorRes) {
            if (this.subHighlightedTextColorRes != subHighlightedTextColorRes) {
                this.subHighlightedTextColorRes = subHighlightedTextColorRes;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedTextColorRes = subHighlightedTextColorRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at unable-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subUnableTextColor color of sub-text
         * @return the builder
         */
        public Builder subUnableTextColor(int subUnableTextColor) {
            if (this.subUnableTextColor != subUnableTextColor) {
                this.subUnableTextColor = subUnableTextColor;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableTextColor = subUnableTextColor;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the color of sub-text when boom-button is at unable-state.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subUnableTextColorRes color resource of sub-text
         * @return the builder
         */
        public Builder subUnableTextColorRes(int subUnableTextColorRes) {
            if (this.subUnableTextColorRes != subUnableTextColorRes) {
                this.subUnableTextColorRes = subUnableTextColorRes;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableTextColorRes = subUnableTextColorRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        /**
         * Set the rect of sub-text.
         * By this method, you can set the position and size of the sub-text-view in boom-button.
         * For example, builder.textRect(new Rect(0, 50, 100, 100)) will make the
         * sub-text-view's size to be 100 * 50 and margin-top to be 50 pixel.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subTextRect the sub-text rect, in pixel.
         * @return the builder
         */
        public Builder subTextRect(Rect subTextRect) {
            if (this.subTextRect != subTextRect) {
                this.subTextRect = subTextRect;
                BoomButton button = button();
                if (button != null) {
                    button.subTextRect = subTextRect;
                    button.updateSubTextRect();
                }
            }
            return this;
        }

        /**
         * Set the padding of sub-text.
         * By this method, you can control the padding in the sub-text-view.
         * For instance, builder.textPadding(new Rect(10, 10, 10, 10)) will make the
         * sub-text-view content 10-pixel padding to itself.
         * <br><br>
         * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
         * then synchronize this change to boom-button.
         *
         * @param subTextPadding the sub-text padding
         * @return the builder
         */
        public Builder subTextPadding(Rect subTextPadding) {
            if (this.subTextPadding != subTextPadding) {
                this.subTextPadding = subTextPadding;
                BoomButton button = button();
                if (button != null) {
                    button.subTextPadding = subTextPadding;
                    button.updateSubTextPadding();
                }
            }
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
        @Override
        public HamButton build(Context context) {
            HamButton button = new HamButton(this, context);
            weakReferenceButton(button);
            return button;
        }
    }
}
