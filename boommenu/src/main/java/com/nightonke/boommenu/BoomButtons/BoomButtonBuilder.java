package com.nightonke.boommenu.BoomButtons;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;

import com.nightonke.boommenu.Util;

/**
 * Created by Weiping Huang at 02:20 on 16/11/18
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public abstract class BoomButtonBuilder {

    // Basic
    int index = -1;
    InnerOnBoomButtonClickListener listener;
    OnBMClickListener onBMClickListener;
    boolean rotateImage = true;
    boolean rotateText = true;
    boolean containsSubText = true;

    // piece
    Integer pieceColor = null;

    // Shadow
    boolean shadowEffect = true;
    int shadowOffsetX = Util.dp2px(0);
    int shadowOffsetY = Util.dp2px(3);
    int shadowRadius = Util.dp2px(8);
    int shadowColor = Color.parseColor("#88757575");
    int shadowCornerRadius = Util.dp2px(5);

    // Images
    int normalImageRes = -1;
    int highlightedImageRes = -1;
    int unableImageRes = -1;
    Drawable normalImageDrawable = null;
    Drawable highlightedImageDrawable = null;
    Drawable unableImageDrawable = null;
    Rect imageRect = new Rect(Util.dp2px(10), Util.dp2px(10), Util.dp2px(70), Util.dp2px(70));
    Rect imagePadding = new Rect(0, 0, 0, 0);

    // Text
    int normalTextRes = -1;
    int highlightedTextRes = -1;
    int unableTextRes = -1;
    String normalText;
    String highlightedText;
    String unableText;
    int normalTextColor = Color.WHITE;
    int highlightedTextColor = Color.WHITE;
    int unableTextColor = Color.WHITE;
    Rect textRect = new Rect(Util.dp2px(15), Util.dp2px(52), Util.dp2px(65), Util.dp2px(72));
    Rect textPadding = new Rect(0, 0, 0, 0);
    Typeface typeface = null;
    int maxLines = 1;
    int textGravity = Gravity.CENTER;
    TextUtils.TruncateAt ellipsize = TextUtils.TruncateAt.MARQUEE;
    int textSize = 10;

    // Sub text
    int subNormalTextRes = -1;
    int subHighlightedTextRes = -1;
    int subUnableTextRes = -1;
    String subNormalText;
    String subHighlightedText;
    String subUnableText;
    int subNormalTextColor = Color.WHITE;
    int subHighlightedTextColor = Color.WHITE;
    int subUnableTextColor = Color.WHITE;
    Rect subTextRect = new Rect(Util.dp2px(70), Util.dp2px(35), Util.dp2px(280), Util.dp2px(55));
    Rect subTextPadding = new Rect(0, 0, 0, 0);
    Typeface subTypeface = null;
    int subMaxLines = 1;
    int subTextGravity = Gravity.START|Gravity.CENTER_VERTICAL;
    TextUtils.TruncateAt subEllipsize = TextUtils.TruncateAt.MARQUEE;
    int subTextSize = 10;

    // Text for text-outside-circle-button
    int textTopMargin = Util.dp2px(5);
    int textWidth = Util.dp2px(80);
    int textHeight = Util.dp2px(20);

    // Button
    boolean rippleEffect = true;
    int normalColor = Util.getColor();
    int highlightedColor = Util.getColor();
    int unableColor = Util.getColor();
    boolean unable = false;
    int buttonRadius = Util.dp2px(40);
    int buttonWidth = Util.dp2px(300);
    int buttonHeight = Util.dp2px(60);
    int buttonCornerRadius = Util.dp2px(5);

    public int pieceColor() {
        if (pieceColor == null) return unable ? unableColor : normalColor;
        else return pieceColor;
    }

    public void setUnable(boolean unable) {
        this.unable = unable;
    }
}
