package com.nightonke.boommenu.BoomButtons;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;

/**
 * Created by Weiping Huang at 01:12 on 2017/4/7
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

@SuppressWarnings({"unchecked", "unused"})
public abstract class BoomButtonWithTextBuilder<T> extends BoomButtonBuilder<T> {

    /**
     * Set the text when boom-button is at normal-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param normalText text
     * @return the builder
     */
    public T normalText(String normalText) {
        if (this.normalText == null || !this.normalText.equals(normalText)) {
            this.normalText = normalText;
            BoomButton button = button();
            if (button != null) {
                button.normalText = normalText;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the text resource when boom-button is at normal-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param normalTextRes text resource
     * @return the builder
     */
    public T normalTextRes(int normalTextRes) {
        if (this.normalTextRes != normalTextRes) {
            this.normalTextRes = normalTextRes;
            BoomButton button = button();
            if (button != null) {
                button.normalTextRes = normalTextRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the text when boom-button is at highlighted-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param highlightedText text
     * @return the builder
     */
    public T highlightedText(String highlightedText) {
        if (this.highlightedText == null || !this.highlightedText.equals(highlightedText)) {
            this.highlightedText = highlightedText;
            BoomButton button = button();
            if (button != null) {
                button.highlightedText = highlightedText;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the text resource when boom-button is at highlighted-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param highlightedTextRes text resource
     * @return the builder
     */
    public T highlightedTextRes(int highlightedTextRes) {
        if (this.highlightedTextRes != highlightedTextRes) {
            this.highlightedTextRes = highlightedTextRes;
            BoomButton button = button();
            if (button != null) {
                button.highlightedTextRes = highlightedTextRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the text when boom-button is at unable-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param unableText text
     * @return the builder
     */
    public T unableText(String unableText) {
        if (this.unableText == null || !this.unableText.equals(unableText)) {
            this.unableText = unableText;
            BoomButton button = button();
            if (button != null) {
                button.unableText = unableText;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the text resource when boom-button is at unable-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param unableTextRes text resource
     * @return the builder
     */
    public T unableTextRes(int unableTextRes) {
        if (this.unableTextRes != unableTextRes) {
            this.unableTextRes = unableTextRes;
            BoomButton button = button();
            if (button != null) {
                button.unableTextRes = unableTextRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the color of text when boom-button is at normal-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param normalTextColor color of text
     * @return the builder
     */
    public T normalTextColor(int normalTextColor) {
        if (this.normalTextColor != normalTextColor) {
            this.normalTextColor = normalTextColor;
            BoomButton button = button();
            if (button != null) {
                button.normalTextColor = normalTextColor;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the color of text when boom-button is at normal-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param normalTextColorRes color resource of text
     * @return the builder
     */
    public T normalTextColorRes(int normalTextColorRes) {
        if (this.normalTextColorRes != normalTextColorRes) {
            this.normalTextColorRes = normalTextColorRes;
            BoomButton button = button();
            if (button != null) {
                button.normalTextColorRes = normalTextColorRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the color of text when boom-button is at highlighted-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param highlightedTextColor color of text
     * @return the builder
     */
    public T highlightedTextColor(int highlightedTextColor) {
        if (this.highlightedTextColor != highlightedTextColor) {
            this.highlightedTextColor = highlightedTextColor;
            BoomButton button = button();
            if (button != null) {
                button.highlightedTextColor = highlightedTextColor;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the color of text when boom-button is at highlighted-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param highlightedTextColorRes color resource of text
     * @return the builder
     */
    public T highlightedTextColorRes(int highlightedTextColorRes) {
        if (this.highlightedTextColorRes != highlightedTextColorRes) {
            this.highlightedTextColorRes = highlightedTextColorRes;
            BoomButton button = button();
            if (button != null) {
                button.highlightedTextColorRes = highlightedTextColorRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the color of text when boom-button is at unable-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param unableTextColor color of text
     * @return the builder
     */
    public T unableTextColor(int unableTextColor) {
        if (this.unableTextColor != unableTextColor) {
            this.unableTextColor = unableTextColor;
            BoomButton button = button();
            if (button != null) {
                button.unableTextColor = unableTextColor;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the color of text when boom-button is at unable-state.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param unableTextColorRes color resource of text
     * @return the builder
     */
    public T unableTextColorRes(int unableTextColorRes) {
        if (this.unableTextColorRes != unableTextColorRes) {
            this.unableTextColorRes = unableTextColorRes;
            BoomButton button = button();
            if (button != null) {
                button.unableTextColorRes = unableTextColorRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    /**
     * Set the rect of text.
     * By this method, you can set the position and size of the text-view in boom-button.
     * For example, builder.textRect(new Rect(0, 50, 100, 100)) will make the
     * text-view's size to be 100 * 50 and margin-top to be 50 pixel.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param textRect the text rect, in pixel.
     * @return the builder
     */
    public T textRect(Rect textRect) {
        if (this.textRect != textRect) {
            this.textRect = textRect;
            BoomButton button = button();
            if (button != null) {
                button.textRect = textRect;
                button.updateTextRect();
            }
        }
        return (T) this;
    }

    /**
     * Set the padding of text.
     * By this method, you can control the padding in the text-view.
     * For instance, builder.textPadding(new Rect(10, 10, 10, 10)) will make the
     * text-view content 10-pixel padding to itself.
     * <br><br>
     * <STRONG>Synchronicity:</STRONG> If the boom-button existed,
     * then synchronize this change to boom-button.
     *
     * @param textPadding the text padding
     * @return the builder
     */
    public T textPadding(Rect textPadding) {
        if (this.textPadding != textPadding) {
            this.textPadding = textPadding;
            BoomButton button = button();
            if (button != null) {
                button.textPadding = textPadding;
                button.updateTextPadding();
            }
        }
        return (T) this;
    }

    /**
     * Set the typeface of the text.
     *
     * @param typeface typeface
     * @return the builder
     */
    public T typeface(Typeface typeface) {
        this.typeface = typeface;
        return (T) this;
    }

    /**
     * Set the maximum of the lines of text-view.
     *
     * @param maxLines maximum lines
     * @return the builder
     */
    public T maxLines(int maxLines) {
        this.maxLines = maxLines;
        return (T) this;
    }

    /**
     * Set the gravity of text-view.
     *
     * @param gravity gravity, for example, Gravity.CENTER
     * @return the builder
     */
    public T textGravity(int gravity) {
        this.textGravity = gravity;
        return (T) this;
    }

    /**
     * Set the ellipsize of the text-view.
     *
     * @param ellipsize ellipsize
     * @return the builder
     */
    public T ellipsize(TextUtils.TruncateAt ellipsize) {
        this.ellipsize = ellipsize;
        return (T) this;
    }

    /**
     * Set the text size of the text-view.
     *
     * @param textSize size of text, in sp
     * @return the builder
     */
    public T textSize(int textSize) {
        this.textSize = textSize;
        return (T) this;
    }
}
