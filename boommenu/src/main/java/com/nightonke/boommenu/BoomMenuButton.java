package com.nightonke.boommenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.nightonke.boommenu.Animation.AnimationManager;
import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.Animation.Ease;
import com.nightonke.boommenu.Animation.EaseEnum;
import com.nightonke.boommenu.Animation.HideRgbEvaluator;
import com.nightonke.boommenu.Animation.OrderEnum;
import com.nightonke.boommenu.Animation.ShareLinesView;
import com.nightonke.boommenu.Animation.ShowRgbEvaluator;
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.BoomButtonBuilder;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceAlignmentEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceManager;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.InnerOnBoomButtonClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.Piece.BoomPiece;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Piece.PiecePlaceManager;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 14:33 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

@SuppressWarnings("unused")
public class BoomMenuButton extends FrameLayout implements InnerOnBoomButtonClickListener {

    protected static final String TAG = "BoomMenuButton";

    // Basic
    private Context context;
    private boolean needToLayout = true;
    private boolean cacheOptimization = true;
    private boolean boomInWholeScreen = true;
    private boolean inList = false;
    private boolean inFragment = false;
    private Runnable listLayoutRunnable;

    // Shadow
    private boolean shadowEffect;
    private int shadowOffsetX;
    private int shadowOffsetY;
    private int shadowRadius;
    private int shadowColor;
    private BMBShadow shadow;

    // Button
    private int buttonRadius;
    private ButtonEnum buttonEnum = ButtonEnum.Unknown;
    private boolean backgroundEffect;
    private boolean rippleEffect;
    private int normalColor;
    private int highlightedColor;
    private int unableColor;
    private FrameLayout button;

    // Piece
    private ArrayList<BoomPiece> pieces;
    private ArrayList<Point> piecePositions;
    private int dotRadius = 6;
    private int hamWidth = 40;
    private int hamHeight = 6;
    private int pieceHorizontalMargin = 5;
    private int pieceVerticalMargin = 5;
    private int pieceInclinedMargin = (int) (5 * Math.sqrt(2));
    private int shareLineLength = 35;
    private int shareLine1Color = Color.WHITE;
    private int shareLine2Color = Color.WHITE;
    private int shareLineWidth = 3;
    private ShareLinesView shareLinesView;
    private PiecePlaceEnum piecePlaceEnum = PiecePlaceEnum.Unknown;

    // Animation
    private int animatingViewNumber = 0;
    private OnBoomListener onBoomListener;
    private int dimColor = Color.parseColor("#55000000");
    private long showDuration = 500;
    private long showDelay = 100;
    private long hideDuration = 500;
    private long hideDelay = 100;
    private boolean cancelable = true;
    private boolean autoHide = true;
    private OrderEnum orderEnum = OrderEnum.RANDOM;
    private int frames = 60;
    private BoomEnum boomEnum = BoomEnum.HORIZONTAL_THROW_2;
    private EaseEnum showMoveEaseEnum = EaseEnum.EaseOutBack;
    private EaseEnum showScaleEaseEnum = EaseEnum.EaseOutBack;
    private EaseEnum showRotateEaseEnum = EaseEnum.EaseOutBack;
    private EaseEnum hideMoveEaseEnum = EaseEnum.EaseInBack;
    private EaseEnum hideScaleEaseEnum = EaseEnum.EaseInBack;
    private EaseEnum hideRotateEaseEnum = EaseEnum.EaseInBack;
    private int rotateDegree = 720;
    private BoomStateEnum boomStateEnum = BoomStateEnum.DidHide;

    // Background
    private ViewGroup background;

    // Boom Buttons
    private ArrayList<BoomButton> boomButtons = new ArrayList<>();
    private ArrayList<BoomButtonBuilder> boomButtonBuilders = new ArrayList<>();
    private float simpleCircleButtonRadius;
    private float textInsideCircleButtonRadius;
    private float textOutsideCircleButtonWidth;
    private float textOutsideCircleButtonHeight;
    private float hamButtonWidth;
    private float hamButtonHeight;
    private ButtonPlaceEnum buttonPlaceEnum = ButtonPlaceEnum.Unknown;
    private ButtonPlaceAlignmentEnum buttonPlaceAlignmentEnum
            = ButtonPlaceAlignmentEnum.Center;
    private float buttonHorizontalMargin;
    private float buttonVerticalMargin;
    private float buttonInclinedMargin;
    private float buttonTopMargin;
    private float buttonBottomMargin;
    private float buttonLeftMargin;
    private float buttonRightMargin;
    private ArrayList<Point> startPositions;
    private ArrayList<Point> endPositions;
    private float bottomHamButtonTopMargin = -1f;

    private void ___________________________1_Initialization() {}
    //region Constructor and Initializer

    public BoomMenuButton(Context context) {
        super(context);
        init(context, null);
    }

    public BoomMenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BoomMenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.bmb, this, true);
        initAttrs(context, attrs);
        initShadow();
        initButton();
        initListLayoutRunnable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.BoomMenuButton, 0, 0);
        if (typedArray == null) return;
        try {
            // Basic
            cacheOptimization = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_cacheOptimization, R.bool.default_bmb_cacheOptimization);
            boomInWholeScreen = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_boomInWholeScreen, R.bool.default_bmb_boomInWholeScreen);
            inList = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_inList, R.bool.default_bmb_inList);
            inFragment = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_inFragment, R.bool.default_bmb_inFragment);

            // Shadow
            shadowEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_shadowEffect, R.bool.default_bmb_shadow_effect);
            shadowRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_shadowRadius, R.dimen.default_bmb_shadow_radius);
            shadowOffsetX = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetX, R.dimen.default_bmb_shadow_offset_x);
            shadowOffsetY = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetY, R.dimen.default_bmb_shadow_offset_y);
            shadowColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shadowColor, R.color.default_bmb_shadow_color);

            // Button
            buttonRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_buttonRadius, R.dimen.default_bmb_button_radius);
            buttonEnum = ButtonEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonEnum, R.integer.default_bmb_button_enum));
            backgroundEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_backgroundEffect, R.bool.default_bmb_background_effect);
            rippleEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_rippleEffect, R.bool.default_bmb_ripple_effect);
            normalColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_normalColor, R.color.default_bmb_normal_color);
            highlightedColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_highlightedColor, R.color.default_bmb_highlighted_color);
            if (highlightedColor == Color.TRANSPARENT) highlightedColor = Util.getDarkerColor(normalColor);
            unableColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_unableColor, R.color.default_bmb_unable_color);
            if (unableColor == Color.TRANSPARENT) unableColor = Util.getLighterColor(normalColor);

            // Piece
            dotRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_dotRadius, R.dimen.default_bmb_dotRadius);
            hamWidth = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_hamWidth, R.dimen.default_bmb_hamWidth);
            hamHeight = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_hamHeight, R.dimen.default_bmb_hamHeight);
            pieceHorizontalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceHorizontalMargin, R.dimen.default_bmb_pieceHorizontalMargin);
            pieceVerticalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceVerticalMargin, R.dimen.default_bmb_pieceVerticalMargin);
            pieceVerticalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceInclinedMargin, R.dimen.default_bmb_pieceInclinedMargin);
            shareLineLength = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_sharedLineLength, R.dimen.default_bmb_sharedLineLength);
            shareLine1Color = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shareLine1Color, R.color.default_bmb_shareLine1Color);
            shareLine2Color = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shareLine2Color, R.color.default_bmb_shareLine2Color);
            shareLineWidth = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_shareLineWidth, R.dimen.default_bmb_shareLineWidth);
            piecePlaceEnum = PiecePlaceEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_piecePlaceEnum, R.integer.default_bmb_pieceEnum));

            // Animation
            dimColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_dimColor, R.color.default_bmb_dimColor);
            showDuration = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showDuration, R.integer.default_bmb_showDuration);
            showDelay = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showDelay, R.integer.default_bmb_showDelay);
            hideDuration = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideDuration, R.integer.default_bmb_hideDuration);
            hideDelay = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideDelay, R.integer.default_bmb_hideDelay);
            cancelable = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_cancelable, R.bool.default_bmb_cancelable);
            autoHide = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_autoHide, R.bool.default_bmb_autoHide);
            orderEnum = OrderEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_orderEnum, R.integer.default_bmb_orderEnum));
            frames = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_frames, R.integer.default_bmb_frames);
            boomEnum = BoomEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_boomEnum, R.integer.default_bmb_boomEnum));
            showMoveEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showMoveEaseEnum, R.integer.default_bmb_showMoveEaseEnum));
            showScaleEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showScaleEaseEnum, R.integer.default_bmb_showScaleEaseEnum));
            showRotateEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showRotateEaseEnum, R.integer.default_bmb_showRotateEaseEnum));
            hideMoveEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideMoveEaseEnum, R.integer.default_bmb_hideMoveEaseEnum));
            hideScaleEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideScaleEaseEnum, R.integer.default_bmb_hideScaleEaseEnum));
            hideRotateEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideRotateEaseEnum, R.integer.default_bmb_hideRotateEaseEnum));
            rotateDegree = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_rotateDegree, R.integer.default_bmb_rotateDegree);

            // Boom buttons
            buttonPlaceEnum = ButtonPlaceEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonPlaceEnum, R.integer.default_bmb_buttonPlaceEnum));
            buttonPlaceAlignmentEnum = ButtonPlaceAlignmentEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonPlaceAlignmentEnum, R.integer.default_bmb_buttonPlaceAlignmentEnum));
            buttonHorizontalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonHorizontalMargin, R.dimen.default_bmb_buttonHorizontalMargin);
            buttonVerticalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonVerticalMargin, R.dimen.default_bmb_buttonVerticalMargin);
            buttonInclinedMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonInclinedMargin, R.dimen.default_bmb_buttonInclinedMargin);
            buttonTopMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonTopMargin, R.dimen.default_bmb_buttonTopMargin);
            buttonBottomMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonBottomMargin, R.dimen.default_bmb_buttonBottomMargin);
            buttonLeftMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonLeftMargin, R.dimen.default_bmb_buttonLeftMargin);
            buttonRightMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonRightMargin, R.dimen.default_bmb_buttonRightMargin);
            bottomHamButtonTopMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_bottomHamButtonTopMargin, R.dimen.default_bmb_bottomHamButtonTopMargin);
        } finally {
            typedArray.recycle();
        }
    }

    private void initShadow() {
        if (shadow == null) shadow = (BMBShadow) findViewById(R.id.shadow);
        boolean hasShadow = shadowEffect && backgroundEffect && !inList;
        shadow.setShadowEffect(hasShadow);
        if (hasShadow) {
            shadow.setShadowOffsetX(shadowOffsetX);
            shadow.setShadowOffsetY(shadowOffsetY);
            shadow.setShadowColor(shadowColor);
            shadow.setShadowRadius(shadowRadius);
            shadow.setShadowCornerRadius(shadowRadius + buttonRadius);
        } else {
            shadow.clearShadow();
        }
    }

    private void initButton() {
        if (button == null) button = (FrameLayout) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boom();
            }
        });

        setButtonSize();
        setButtonBackground();
    }

    private void setButtonSize() {
        LayoutParams params = (LayoutParams) button.getLayoutParams();
        params.width = buttonRadius * 2;
        params.height = buttonRadius * 2;
        button.setLayoutParams(params);
    }

    private void setButtonBackground() {
        if (backgroundEffect && !inList) {
            if (rippleEffect && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable rippleDrawable = new RippleDrawable(
                        ColorStateList.valueOf(highlightedColor),
                        Util.getOvalDrawable(button, normalColor),
                        null);
                Util.setDrawable(button, rippleDrawable);
            } else {
                StateListDrawable stateListDrawable = Util.getOvalStateListBitmapDrawable(
                        button,
                        buttonRadius,
                        normalColor,
                        highlightedColor,
                        unableColor);
                Util.setDrawable(button, stateListDrawable);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Util.setDrawable(button, Util.getSystemDrawable(context, android.R.attr.selectableItemBackgroundBorderless));
            } else {
                Util.setDrawable(button, Util.getSystemDrawable(context, android.R.attr.selectableItemBackground));
            }
        }
    }

    private void initListLayoutRunnable() {
        if (listLayoutRunnable == null) {
            listLayoutRunnable = new Runnable() {
                @Override
                public void run() {
                    doLayoutJobs();
                }
            };
        }
    }

    //endregion

    private void ___________________________2_Place_Pieces() {}
    //region Place Pieces

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        if (needToLayout) {
            if (inList) {
                initListLayoutRunnable();
                post(listLayoutRunnable);
            } else {
                doLayoutJobs();
            }
        }
        needToLayout = false;
    }

    private void doLayoutJobs() {
        removePieces();
        createPieces();
        placeShareLinesView();
        placePieces();
        placePiecesAtPositions();
        // We have to calculate the start positions again when BMB is used in list-view.
        // So we don't need to calculate them here.
        if (!inList && !inFragment) calculateStartPositions();
        setShareLinesViewData();
    }

    private void removePieces() {
        button.removeAllViews();
        if (pieces != null) pieces.clear();
    }

    private void createPieces() {
        ExceptionManager.judge(buttonEnum, piecePlaceEnum);
        ExceptionManager.judge(piecePlaceEnum, buttonPlaceEnum, boomButtonBuilders);
        calculatePiecePositions();
        int pieceNumber = pieceNumber();
        pieces = new ArrayList<>(pieceNumber);
        for (int i = 0; i < pieceNumber; i++) {
            BoomPiece piece = PiecePlaceManager.createPiece(
                    context,
                    piecePlaceEnum,
                    boomButtonBuilders.get(i).pieceColor());
            pieces.add(piece);
        }
    }

    private void placePieces() {
        ArrayList<Integer> indexes;
        if (piecePlaceEnum == PiecePlaceEnum.Share)
            indexes = AnimationManager.getOrderIndex(OrderEnum.DEFAULT, pieces.size());
        else
            indexes = AnimationManager.getOrderIndex(orderEnum, pieces.size());
        // Reverse to keep the former pieces are above than the latter(z-axis)
        // So the early-animating pieces are above than the later ones
        for (int i = indexes.size() - 1; i >= 0; i--) button.addView(pieces.get(indexes.get(i)));
    }

    private void placePiecesAtPositions() {
        int pieceNumber = pieceNumber();
        int w, h;
        switch (piecePlaceEnum) {
            case DOT_1:
            case DOT_2_1:
            case DOT_2_2:
            case DOT_3_1:
            case DOT_3_2:
            case DOT_3_3:
            case DOT_3_4:
            case DOT_4_1:
            case DOT_4_2:
            case DOT_5_1:
            case DOT_5_2:
            case DOT_5_3:
            case DOT_5_4:
            case DOT_6_1:
            case DOT_6_2:
            case DOT_6_3:
            case DOT_6_4:
            case DOT_6_5:
            case DOT_6_6:
            case DOT_7_1:
            case DOT_7_2:
            case DOT_7_3:
            case DOT_7_4:
            case DOT_7_5:
            case DOT_7_6:
            case DOT_8_1:
            case DOT_8_2:
            case DOT_8_3:
            case DOT_8_4:
            case DOT_8_5:
            case DOT_8_6:
            case DOT_8_7:
            case DOT_9_1:
            case DOT_9_2:
            case DOT_9_3:
            case Share:
                w = 2 * dotRadius;
                h = 2 * dotRadius;
                break;
            case HAM_1:
            case HAM_2:
            case HAM_3:
            case HAM_4:
            case HAM_5:
            case HAM_6:
                w = hamWidth;
                h = hamHeight;
                break;
            default:
                throw new RuntimeException("Unknown piece-place-enum!");
        }
        for (int i = 0; i < pieceNumber; i++) pieces.get(i).place(piecePositions.get(i).x, piecePositions.get(i).y, w, h);
    }

    private void calculatePiecePositions() {
        ExceptionManager.judge(buttonEnum);
        ExceptionManager.judge(piecePlaceEnum);
        switch (buttonEnum) {
            case SimpleCircle:
            case TextInsideCircle:
            case TextOutsideCircle:
                if (piecePlaceEnum == PiecePlaceEnum.Share) {
                    piecePositions = PiecePlaceManager.getShareDotPositions(
                            new Point(button.getWidth(), button.getHeight()),
                            dotRadius,
                            boomButtonBuilders.size(),
                            shareLineLength);
                } else {
                    piecePositions = PiecePlaceManager.getDotPositions(
                            piecePlaceEnum,
                            new Point(button.getWidth(), button.getHeight()),
                            dotRadius,
                            pieceHorizontalMargin,
                            pieceVerticalMargin,
                            pieceInclinedMargin);
                }
                break;
            case Ham:
                piecePositions = PiecePlaceManager.getHamPositions(
                        piecePlaceEnum,
                        new Point(button.getWidth(), button.getHeight()),
                        hamWidth,
                        hamHeight,
                        pieceVerticalMargin);
                break;
            case Unknown:
                throw new RuntimeException("The button-enum is unknown!");
        }
    }

    //endregion

    private void ___________________________3_Animation() {}
    //region Animation

    /**
     * Boom the BMB!
     */
    public void boom() {
        if (isAnimating() || boomStateEnum != BoomStateEnum.DidHide) return;
        boomStateEnum = BoomStateEnum.WillShow;
        if (onBoomListener != null) onBoomListener.onBoomWillShow();
        // We have to calculate the start positions again when BMB is used in list-view
        // or in fragment.
        if (inList || inFragment) calculateStartPositions();
        createButtons();
        dimBackground(false);
        ExceptionManager.judge(boomEnum);
        startShowAnimations(false);
    }

    /**
     * Re-boom the BMB!
     */
    public void reboom() {
        if (isAnimating() || boomStateEnum != BoomStateEnum.DidShow) return;
        boomStateEnum = BoomStateEnum.WillHide;
        if (onBoomListener != null) onBoomListener.onBoomWillHide();
        lightBackground(false);
        startHideAnimations(false);
    }

    /**
     * Boom the BMB with duration 0!
     */
    public void boomImmediately() {
        if (isAnimating() || boomStateEnum != BoomStateEnum.DidHide) return;
        boomStateEnum = BoomStateEnum.WillShow;
        if (onBoomListener != null) onBoomListener.onBoomWillShow();
        // We have to calculate the start positions again when BMB is used in list-view
        // or in fragment.
        if (inList || inFragment) calculateStartPositions();
        createButtons();
        dimBackground(true);
        ExceptionManager.judge(boomEnum);
        startShowAnimations(true);
    }

    /**
     * Re-boom the BMB with duration 0!
     */
    public void reboomImmediately() {
        if (isAnimating() || boomStateEnum != BoomStateEnum.DidShow) return;
        boomStateEnum = BoomStateEnum.WillHide;
        if (onBoomListener != null) onBoomListener.onBoomWillHide();
        lightBackground(true);
        startHideAnimations(true);
    }

    private void dimBackground(boolean immediately) {
        createBackground();
        Util.setVisibility(VISIBLE, background);
        AnimationManager.animate(
                background,
                "backgroundColor",
                0,
                immediately ? 1 : showDuration + showDelay * (pieces.size() - 1),
                new ArgbEvaluator(),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        boomStateEnum = BoomStateEnum.DidShow;
                        if (onBoomListener != null) onBoomListener.onBoomDidShow();
                    }
                },
                Color.TRANSPARENT,
                dimColor);
        if (piecePlaceEnum == PiecePlaceEnum.Share) {
            AnimationManager.animate(
                    shareLinesView,
                    "showProcess",
                    0,
                    immediately ? 1 : showDuration + showDelay * (pieces.size() - 1),
                    Ease.getInstance(EaseEnum.Linear),
                    0f, 1f);
        }
    }

    private void lightBackground(boolean immediately) {
        createBackground();
        AnimationManager.animate(
                background,
                "backgroundColor",
                0,
                immediately ? 1 : hideDuration + hideDelay * (pieces.size() - 1),
                new ArgbEvaluator(),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        boomStateEnum = BoomStateEnum.DidHide;
                        if (onBoomListener != null) onBoomListener.onBoomDidHide();
                        clearViewsAndValues();
                    }
                },
                dimColor,
                Color.TRANSPARENT);
        if (piecePlaceEnum == PiecePlaceEnum.Share) {
            AnimationManager.animate(
                    shareLinesView,
                    "hideProcess",
                    0,
                    immediately ? 1 : hideDuration + hideDelay * (pieces.size() - 1),
                    Ease.getInstance(EaseEnum.Linear),
                    0f, 1f);
        }
    }

    private void startShowAnimations(boolean immediately) {
        if (background != null) background.removeAllViews();
        calculateEndPositions();
        ArrayList<Integer> indexes;
        if (piecePlaceEnum == PiecePlaceEnum.Share)
            indexes = AnimationManager.getOrderIndex(OrderEnum.DEFAULT, pieces.size());
        else
            indexes = AnimationManager.getOrderIndex(orderEnum, pieces.size());
        // Reverse to keep the former boom-buttons are above than the latter(z-axis)
        // So the early-animating boom-buttons are above than the later ones
        for (int i = indexes.size() - 1; i >= 0; i--) {
            int index = indexes.get(i);
            BoomButton boomButton = boomButtons.get(index);
            Point startPosition = new Point(
                    (int) (startPositions.get(index).x - boomButton.centerPoint.x),
                    (int) (startPositions.get(index).y - boomButton.centerPoint.y));
            putBoomButtonInBackground(boomButton, startPosition);
            startEachShowAnimation(
                    pieces.get(index),
                    boomButton,
                    startPosition,
                    new Point(endPositions.get(index)),
                    i,
                    immediately);
        }
    }

    private void startHideAnimations(boolean immediately) {
        ArrayList<Integer> indexes;
        if (piecePlaceEnum == PiecePlaceEnum.Share)
            indexes = AnimationManager.getOrderIndex(OrderEnum.REVERSE, pieces.size());
        else
            indexes = AnimationManager.getOrderIndex(orderEnum, pieces.size());
        for (Integer index : indexes) boomButtons.get(index).bringToFront();
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            BoomButton boomButton = boomButtons.get(index);
            Point startPosition = new Point(
                    (int) (startPositions.get(index).x - boomButton.centerPoint.x),
                    (int) (startPositions.get(index).y - boomButton.centerPoint.y));
            startEachHideAnimation(
                    pieces.get(index),
                    boomButton,
                    new Point(endPositions.get(index)),
                    startPosition,
                    i,
                    immediately);
        }
    }

    private void startEachShowAnimation(final BoomPiece piece,
                                        final BoomButton boomButton,
                                        Point startPosition,
                                        Point endPosition,
                                        int delayFactor,
                                        boolean immediately) {
        animatingViewNumber++;
        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        float scaleX = piece.getWidth() * 1.0f / boomButton.contentWidth();
        float scaleY = piece.getHeight() * 1.0f / boomButton.contentHeight();
        long delay = immediately ? 1 : showDelay * delayFactor;
        long duration = immediately ? 1 : showDuration;
        boomButton.setSelfScaleAnchorPoints();
        boomButton.setScaleX(scaleX);
        boomButton.setScaleY(scaleY);
        boomButton.setClickable(false);
        AnimationManager.calculateShowXY(
                boomEnum,
                new Point(
                        background.getLayoutParams().width,
                        background.getLayoutParams().height),
                frames, startPosition, endPosition, xs, ys);
        if (boomButton.isNeededColorAnimation()) AnimationManager.animate(boomButton, "buttonColor", delay, duration, ShowRgbEvaluator.getInstance(), boomButton.pieceColor(), boomButton.buttonColor());
        AnimationManager.animate(boomButton, "x", delay, duration, Ease.getInstance(showMoveEaseEnum), xs);
        AnimationManager.animate(boomButton, "y", delay, duration, Ease.getInstance(showMoveEaseEnum), ys);
        AnimationManager.rotate(boomButton, delay, duration, Ease.getInstance(showRotateEaseEnum), 0, rotateDegree);
        AnimationManager.animate("alpha", delay, duration, new float[]{0, 1}, Ease.getInstance(EaseEnum.Linear), boomButton.goneViews());
        AnimationManager.animate(boomButton, "scaleX", delay, duration, Ease.getInstance(showScaleEaseEnum), scaleX, 1);
        AnimationManager.animate(boomButton, "scaleY", delay, duration, Ease.getInstance(showScaleEaseEnum),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        Util.setVisibility(INVISIBLE, piece);
                        Util.setVisibility(VISIBLE, boomButton);
                        boomButton.willShow();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        boomButton.setClickable(true);
                        boomButton.didShow();
                        animatingViewNumber--;
                    }
                }, scaleY, 1);
    }

    private void startEachHideAnimation(final BoomPiece piece,
                                        final BoomButton boomButton,
                                        Point startPosition,
                                        Point endPosition,
                                        final int delayFactor,
                                        boolean immediately) {
        animatingViewNumber++;
        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        float scaleX = piece.getWidth() * 1.0f / boomButton.contentWidth();
        float scaleY = piece.getHeight() * 1.0f / boomButton.contentHeight();
        long delay = immediately ? 1 : hideDelay * delayFactor;
        long duration = immediately ? 1 : hideDuration;
        boomButton.setClickable(false);
        AnimationManager.calculateHideXY(
                boomEnum,
                new Point(
                        background.getLayoutParams().width,
                        background.getLayoutParams().height),
                frames, startPosition, endPosition, xs, ys);
        if (boomButton.isNeededColorAnimation()) AnimationManager.animate(boomButton, "buttonColor", delay, duration, HideRgbEvaluator.getInstance(), boomButton.buttonColor(), boomButton.pieceColor());
        AnimationManager.animate(boomButton, "x", delay, duration, Ease.getInstance(hideMoveEaseEnum), xs);
        AnimationManager.animate(boomButton, "y", delay, duration, Ease.getInstance(hideMoveEaseEnum), ys);
        AnimationManager.rotate(boomButton, delay, duration, Ease.getInstance(hideRotateEaseEnum), 0, rotateDegree);
        AnimationManager.animate("alpha", delay, duration, new float[]{1, 0}, Ease.getInstance(EaseEnum.Linear), boomButton.goneViews());
        AnimationManager.animate(boomButton, "scaleX", delay, duration, Ease.getInstance(hideScaleEaseEnum), 1, scaleX);
        AnimationManager.animate(boomButton, "scaleY", delay, duration, Ease.getInstance(hideScaleEaseEnum),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        boomButton.willHide();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Util.setVisibility(VISIBLE, piece);
                        Util.setVisibility(INVISIBLE, boomButton);
                        boomButton.didHide();
                        boomButton.cleanListener();
                        animatingViewNumber--;
                    }
                }, 1, scaleY);
    }

    //endregion

    private void ___________________________4_Support_Methods() {}
    //region Support Methods

    private void createBackground() {
        if (background == null) {
            ViewGroup rootView = getParentView();
            background = new FrameLayout(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    rootView.getWidth(),
                    rootView.getHeight());
            background.setLayoutParams(params);
            background.setBackgroundColor(Color.TRANSPARENT);
            background.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackgroundClicked();
                }
            });
            background.setMotionEventSplittingEnabled(false);
            rootView.addView(background);
        }
    }

    private ViewGroup getParentView() {
        if (boomInWholeScreen) {
            Activity activity = Util.scanForActivity(context);
            if (activity == null) {
                return (ViewGroup) getParent();
            } else {
                return (ViewGroup) activity.getWindow().getDecorView();
            }
        } else {
            return (ViewGroup) getParent();
        }
    }

    private void clearBackground() {
        Util.setVisibility(GONE, background);
        if (!cacheOptimization || inList || inFragment) {
            background.removeAllViews();
            ((ViewGroup)background.getParent()).removeView(background);
            background = null;
        }
    }

    private void createButtons() {
        boomButtons = new ArrayList<>(pieces.size());
        int buttonNumber = pieces.size();
        ExceptionManager.judge(buttonEnum, piecePlaceEnum);
        ExceptionManager.judge(piecePlaceEnum, buttonPlaceEnum, boomButtonBuilders);
        switch (buttonEnum) {
            case SimpleCircle:
                for (int i = 0; i < buttonNumber; i++) {
                    SimpleCircleButton.Builder builder =
                            (SimpleCircleButton.Builder) boomButtonBuilders.get(i);
                    builder.innerListener(this).index(i);
                    boomButtons.add(builder.build(context));
                    simpleCircleButtonRadius = builder.getButtonRadius();
                }
                break;
            case TextInsideCircle:
                for (int i = 0; i < buttonNumber; i++) {
                    TextInsideCircleButton.Builder builder =
                            (TextInsideCircleButton.Builder) boomButtonBuilders.get(i);
                    builder.innerListener(this).index(i);
                    boomButtons.add(builder.build(context));
                    textInsideCircleButtonRadius = builder.getButtonRadius();
                }
                break;
            case TextOutsideCircle:
                for (int i = 0; i < buttonNumber; i++) {
                    TextOutsideCircleButton.Builder builder =
                            (TextOutsideCircleButton.Builder) boomButtonBuilders.get(i);
                    builder.innerListener(this).index(i);
                    boomButtons.add(builder.build(context));
                    textOutsideCircleButtonWidth = builder.getButtonContentWidth();
                    textOutsideCircleButtonHeight = builder.getButtonContentHeight();
                }
                break;
            case Ham:
                for (int i = 0; i < buttonNumber; i++) {
                    HamButton.Builder builder =
                            (HamButton.Builder) boomButtonBuilders.get(i);
                    builder.innerListener(this).index(i);
                    boomButtons.add(builder.build(context));
                    hamButtonWidth = builder.getButtonWidth();
                    hamButtonHeight = builder.getButtonHeight();
                }
                break;
        }
    }

    private void onBackgroundClicked() {
        if (isAnimating()) return;
        if (onBoomListener != null) onBoomListener.onBackgroundClick();
        if (cancelable) reboom();
    }

    private boolean isAnimating() {
        return animatingViewNumber != 0;
    }

    private void calculateStartPositions() {
        int pieceNumber = pieceNumber();
        startPositions = new ArrayList<>(pieceNumber);
        ViewGroup rootView = getParentView();
        int[] rootViewLocation = new int[2];
        rootView.getLocationOnScreen(rootViewLocation);
        for (int i = 0; i < pieces.size(); i++) {
            Point pieceCenterInRootView = new Point();
            int[] buttonLocation = new int[2];
            button.getLocationOnScreen(buttonLocation);
            pieceCenterInRootView.x = buttonLocation[0] + piecePositions.get(i).x
                    - rootViewLocation[0] + pieces.get(i).getLayoutParams().width / 2;
            pieceCenterInRootView.y = buttonLocation[1] + piecePositions.get(i).y
                    - rootViewLocation[1] + pieces.get(i).getLayoutParams().height / 2;
            startPositions.add(pieceCenterInRootView);
        }
    }

    private void calculateEndPositions() {
        ExceptionManager.judge(buttonEnum);
        switch (buttonEnum) {
            case SimpleCircle:
                endPositions = ButtonPlaceManager.getCircleButtonPositions(
                        buttonPlaceEnum,
                        buttonPlaceAlignmentEnum,
                        new Point(
                                background.getLayoutParams().width,
                                background.getLayoutParams().height),
                        simpleCircleButtonRadius,
                        boomButtonBuilders.size(),
                        buttonHorizontalMargin,
                        buttonVerticalMargin,
                        buttonInclinedMargin,
                        buttonTopMargin,
                        buttonBottomMargin,
                        buttonLeftMargin,
                        buttonRightMargin);
                break;
            case TextInsideCircle:
                endPositions = ButtonPlaceManager.getCircleButtonPositions(
                        buttonPlaceEnum,
                        buttonPlaceAlignmentEnum,
                        new Point(
                                background.getLayoutParams().width,
                                background.getLayoutParams().height),
                        textInsideCircleButtonRadius,
                        boomButtonBuilders.size(),
                        buttonHorizontalMargin,
                        buttonVerticalMargin,
                        buttonInclinedMargin,
                        buttonTopMargin,
                        buttonBottomMargin,
                        buttonLeftMargin,
                        buttonRightMargin);
                break;
            case TextOutsideCircle:
                endPositions = ButtonPlaceManager.getCircleButtonPositions(
                        buttonPlaceEnum,
                        buttonPlaceAlignmentEnum,
                        new Point(
                                background.getLayoutParams().width,
                                background.getLayoutParams().height),
                        textOutsideCircleButtonWidth,
                        textOutsideCircleButtonHeight,
                        boomButtonBuilders.size(),
                        buttonHorizontalMargin,
                        buttonVerticalMargin,
                        buttonInclinedMargin,
                        buttonTopMargin,
                        buttonBottomMargin,
                        buttonLeftMargin,
                        buttonRightMargin);
                break;
            case Ham:
                endPositions = ButtonPlaceManager.getHamButtonPositions(
                        buttonPlaceEnum,
                        buttonPlaceAlignmentEnum,
                        new Point(
                                background.getLayoutParams().width,
                                background.getLayoutParams().height),
                        hamButtonWidth,
                        hamButtonHeight,
                        boomButtonBuilders.size(),
                        buttonHorizontalMargin,
                        buttonVerticalMargin,
                        buttonTopMargin,
                        buttonBottomMargin,
                        buttonLeftMargin,
                        buttonRightMargin,
                        bottomHamButtonTopMargin);
                break;
        }
        for (int i = 0; i < boomButtons.size(); i++) {
            endPositions.get(i).x -= boomButtons.get(i).centerPoint.x;
            endPositions.get(i).y -= boomButtons.get(i).centerPoint.y;
        }
    }

    private BoomButton putBoomButtonInBackground(BoomButton boomButton, Point position) {
        createBackground();
        boomButton.place(
                position.x,
                position.y,
                boomButton.trueWidth(),
                boomButton.trueHeight());
        boomButton.setVisibility(INVISIBLE);
        background.addView(boomButton);
        return boomButton;
    }

    private void clearViewsAndValues() {
        clearBackground();
        if (!cacheOptimization || inList || inFragment) {
            endPositions.clear();
            endPositions = null;
            boomButtons.clear();
            boomButtons = new ArrayList<>();
        }
    }

    private void toLayout() {
        if (needToLayout) return;
        needToLayout = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (!isInLayout()) requestLayout();
        } else {
            requestLayout();
        }
    }

    private int pieceNumber() {
        if (piecePlaceEnum == PiecePlaceEnum.Share) return boomButtonBuilders.size();
        else return piecePlaceEnum.pieceNumber();
    }

    //endregion

    @Override
    public void onButtonClick(int index, BoomButton boomButton) {
        if (isAnimating()) return;
        if (onBoomListener != null) onBoomListener.onClicked(index, boomButton);
        if (autoHide) reboom();
    }

    private void placeShareLinesView() {
        if (piecePlaceEnum == PiecePlaceEnum.Share) {
            shareLinesView = new ShareLinesView(context);
            shareLinesView.setLine1Color(shareLine1Color);
            shareLinesView.setLine2Color(shareLine2Color);
            shareLinesView.setLineWidth(shareLineWidth);
            button.addView(shareLinesView);
            shareLinesView.place(0, 0, button.getWidth(), button.getHeight());
        }
    }

    private void setShareLinesViewData() {
        if (piecePlaceEnum == PiecePlaceEnum.Share) {
            shareLinesView.setData(
                    piecePositions,
                    dotRadius,
                    showDuration,
                    showDelay,
                    hideDuration,
                    hideDelay);
        }
    }

    private void ___________________________5_Builders_and_Buttons() {}

    //region Builders

    /**
     * Add a builder to bmb, notice that @needToLayout will be called.
     *
     * @param builder builder
     */
    public void addBuilder(BoomButtonBuilder builder) {
        boomButtonBuilders.add(builder);
        toLayout();
    }

    /**
     * Set a builder at index, notice that @needToLayout will be called.
     *
     * @param index index
     * @param builder builder
     */
    public void setBuilder(int index, BoomButtonBuilder builder) {
        boomButtonBuilders.set(index, builder);
        toLayout();
    }

    public void setBuilders(ArrayList<BoomButtonBuilder> builders) {
        boomButtonBuilders = builders;
        toLayout();
    }

    /**
     * Remove a builder, notice that @needToLayout will be called.
     *
     * @param builder builder
     */
    public void removeBuilder(BoomButtonBuilder builder) {
        boomButtonBuilders.remove(builder);
        toLayout();
    }

    /**
     * Remove a builder at index, notice that @needToLayout will be called.
     *
     * @param index index
     */
    public void removeBuilder(int index) {
        boomButtonBuilders.remove(index);
        toLayout();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        button.setEnabled(enabled);
        setButtonBackground();
    }

    /**
     * Set enable attribute of the boom-button at index.
     *
     * @param index index of the boom-button
     * @param enable whether the boom-button should be enable
     */
    public void setEnable(int index, boolean enable) {
        if (index < 0) return;
        if (boomButtonBuilders != null && index < boomButtonBuilders.size()) {
            boomButtonBuilders.get(index).setUnable(!enable);
        }
        if (boomButtons != null && index < boomButtons.size()) {
            boomButtons.get(index).setEnabled(enable);
        }
    }

    /**
     * Remove all builders, notice that @needToLayout will NOT be called.
     */
    public void clearBuilders() {
        boomButtonBuilders.clear();
    }

    /**
     * Get the array of builders.
     *
     * @return array of builders
     */
    public ArrayList<BoomButtonBuilder> getBuilders() {
        return boomButtonBuilders;
    }

    //endregion

    private void ___________________________6_Getters_and_Setters() {}

    //region Getter and Setter


    public boolean isCacheOptimization() {
        return cacheOptimization;
    }

    /**
     * Whether use cache optimization to avoid multi-creating boom-buttons.
     *
     * @param cacheOptimization cache optimization
     */
    public void setCacheOptimization(boolean cacheOptimization) {
        this.cacheOptimization = cacheOptimization;
    }

    public boolean isBoomInWholeScreen() {
        return boomInWholeScreen;
    }

    /**
     * Whether the BMB should boom in the whole screen.
     * If this value is false, then BMB will boom its boom-buttons to its parent-view.
     *
     * @param boomInWholeScreen boom in the whole screen
     */
    public void setBoomInWholeScreen(boolean boomInWholeScreen) {
        this.boomInWholeScreen = boomInWholeScreen;
    }

    public boolean isInList() {
        return inList;
    }

    /**
     * When BMB is used in list-view, it must be setInList(true).
     *
     * @param inList use BMB in list-view
     */
    public void setInList(boolean inList) {
        this.inList = inList;
    }

    public boolean isInFragment() {
        return inFragment;
    }

    /**
     * When BMB is used in fragment, it must be setInFragment(true).
     *
     * @param inFragment use BMB in fragment
     */
    public void setInFragment(boolean inFragment) {
        this.inFragment = inFragment;
    }

    public boolean isShadowEffect() {
        return shadowEffect;
    }

    /**
     * Whether BMB should have a shadow-effect.
     * Notice that when you set @backgroundEffect to false, this value will set to false too.
     *
     * @param shadowEffect shadow-effect
     */
    public void setShadowEffect(boolean shadowEffect) {
        this.shadowEffect = shadowEffect;
        initShadow();
    }

    public int getShadowOffsetX() {
        return shadowOffsetX;
    }

    /**
     * Set the BMB's shadow offset in the x-axis.
     *
     * @param shadowOffsetX x-axis shadow offset
     */
    public void setShadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        initShadow();
    }

    public int getShadowOffsetY() {
        return shadowOffsetY;
    }

    /**
     * Set the BMB's shadow offset in the y-axis.
     *
     * @param shadowOffsetY y-axis shadow offset
     */
    public void setShadowOffsetY(int shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        initShadow();
    }

    public int getShadowRadius() {
        return shadowRadius;
    }

    /**
     * Set the shadow-radius of BMB, please notice that the "radius" here means the extra
     * radius of BMB.
     * For example, if the radius of BMB is 30dp and the shadow-radius is 5dp, then the
     * radius of shadow-circle behind the BMB if 35dp.
     *
     * @param shadowRadius extra shadow radius
     */
    public void setShadowRadius(int shadowRadius) {
        this.shadowRadius = shadowRadius;
        initShadow();
    }

    public int getShadowColor() {
        return shadowColor;
    }

    /**
     * Set the color of shadow.
     *
     * @param shadowColor color of shadow
     */
    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        initShadow();
    }

    public int getButtonRadius() {
        return buttonRadius;
    }

    /**
     * Set the radius of BMB, if you use this method to set the size of BMB,
     * then you should set the width and height of BMB in .xml file to "wrap-content".
     *
     * @param buttonRadius radius of BMB
     */
    public void setButtonRadius(int buttonRadius) {
        this.buttonRadius = buttonRadius;
        initButton();
        toLayout();
    }

    public ButtonEnum getButtonEnum() {
        return buttonEnum;
    }

    /**
     * Set the button-enum for bmb, notice that @requestLayout() and @clearBuilders() will
     * be called.
     *
     * @param buttonEnum button-enum
     */
    public void setButtonEnum(ButtonEnum buttonEnum) {
        this.buttonEnum = buttonEnum;
        toLayout();
        clearBuilders();
    }

    public boolean isBackgroundEffect() {
        return backgroundEffect;
    }

    /**
     * Whether the BMB should have a background effect. Use this when you don't want the
     * circle background of BMB.
     * It can be useful when you want to use BMB in actionbar of in list-view.
     * Please notice that, when BMB does not have a background effect, it does not
     * have shadow effect, either.
     *
     * @param backgroundEffect background effect
     */
    public void setBackgroundEffect(boolean backgroundEffect) {
        this.backgroundEffect = backgroundEffect;
        setButtonBackground();
        toLayout();
    }

    public boolean isRippleEffect() {
        return rippleEffect;
    }

    /**
     * Whether the BMB should have a ripple-effect.
     * The ripple effect is disable below LOLLIPOP.
     *
     * @param rippleEffect ripple effect
     */
    public void setRippleEffect(boolean rippleEffect) {
        this.rippleEffect = rippleEffect;
        setButtonBackground();
        toLayout();
    }

    public int getNormalColor() {
        return normalColor;
    }

    /**
     * Set the color of BMB at normal-state.
     *
     * @param normalColor the color of BMB at normal-state
     */
    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        setButtonBackground();
        toLayout();
    }

    public int getHighlightedColor() {
        return highlightedColor;
    }

    /**
     * Set the color of BMB at highlighted-state.
     *
     * @param highlightedColor the color of BMB at highlighted-state
     */
    public void setHighlightedColor(int highlightedColor) {
        this.highlightedColor = highlightedColor;
        setButtonBackground();
        toLayout();
    }

    public int getUnableColor() {
        return unableColor;
    }

    /**
     * Set the color of BMB at unable-state.
     *
     * @param unableColor the color of BMB at unable-state
     */
    public void setUnableColor(int unableColor) {
        this.unableColor = unableColor;
        setButtonBackground();
        toLayout();
    }

    public int getDotRadius() {
        return dotRadius;
    }

    /**
     * Set the radius of dots in BMB.
     *
     * @param dotRadius radius of dot
     */
    public void setDotRadius(int dotRadius) {
        this.dotRadius = dotRadius;
        toLayout();
    }

    public int getHamWidth() {
        return hamWidth;
    }

    /**
     * Set the width of hams in BMB.
     *
     * @param hamWidth width of ham
     */
    public void setHamWidth(int hamWidth) {
        this.hamWidth = hamWidth;
        toLayout();
    }

    public int getHamHeight() {
        return hamHeight;
    }

    /**
     * Set the height of hams in BMB.
     *
     * @param hamHeight height of ham
     */
    public void setHamHeight(int hamHeight) {
        this.hamHeight = hamHeight;
        toLayout();
    }

    public int getPieceHorizontalMargin() {
        return pieceHorizontalMargin;
    }

    /**
     * Set the horizontal margin between pieces(dots, blocks or hams) in BMB.
     *
     * @param pieceHorizontalMargin horizontal margin between pieces
     */
    public void setPieceHorizontalMargin(int pieceHorizontalMargin) {
        this.pieceHorizontalMargin = pieceHorizontalMargin;
        toLayout();
    }

    public int getPieceVerticalMargin() {
        return pieceVerticalMargin;
    }

    /**
     * Set the vertical margin between pieces(dots, blocks or hams) in BMB.
     *
     * @param pieceVerticalMargin vertical margin between pieces
     */
    public void setPieceVerticalMargin(int pieceVerticalMargin) {
        this.pieceVerticalMargin = pieceVerticalMargin;
        toLayout();
    }

    public int getPieceInclinedMargin() {
        return pieceInclinedMargin;
    }

    /**
     * Set the inclined margin between pieces(dots, blocks or hams) in BMB.
     *
     * @param pieceInclinedMargin inclined margin between pieces
     */
    public void setPieceInclinedMargin(int pieceInclinedMargin) {
        this.pieceInclinedMargin = pieceInclinedMargin;
        toLayout();
    }

    public int getShareLineLength() {
        return shareLineLength;
    }

    /**
     * Set the length of share-lines in BMB, only works when piece-place-enum is Share.
     *
     * @param shareLineLength length of share-lines, in pixel
     */
    public void setShareLineLength(int shareLineLength) {
        this.shareLineLength = shareLineLength;
    }

    public int getShareLine1Color() {
        return shareLine1Color;
    }

    /**
     * Set the color of share-line 1(the above), only works when piece-place-enum is Share.
     *
     * @param shareLine1Color color of share-line 1
     */
    public void setShareLine1Color(int shareLine1Color) {
        this.shareLine1Color = shareLine1Color;
    }

    public int getShareLine2Color() {
        return shareLine2Color;
    }

    /**
     * Set the color of share-line 2(the below), only works when piece-place-enum is Share.
     *
     * @param shareLine2Color color of share-line 2
     */
    public void setShareLine2Color(int shareLine2Color) {
        this.shareLine2Color = shareLine2Color;
    }

    public int getShareLineWidth() {
        return shareLineWidth;
    }

    /**
     * Set the width of share-lines in BMB, only works when piece-place-enum is Share.
     *
     * @param shareLineWidth width of share-lines, in pixel
     */
    public void setShareLineWidth(int shareLineWidth) {
        this.shareLineWidth = shareLineWidth;
    }

    public PiecePlaceEnum getPiecePlaceEnum() {
        return piecePlaceEnum;
    }

    public ButtonPlaceEnum getButtonPlaceEnum() {
        return buttonPlaceEnum;
    }

    /**
     * Set the piece-place-enum, notice that @requestLayout() will be called.
     *
     * @param piecePlaceEnum piece-place-enum
     */
    public void setPiecePlaceEnum(PiecePlaceEnum piecePlaceEnum) {
        this.piecePlaceEnum = piecePlaceEnum;
        toLayout();
    }

    public OnBoomListener getOnBoomListener() {
        return onBoomListener;
    }

    /**
     * Set the @OnBoomListener of BMB.
     *
     * @param onBoomListener OnBoomListener
     */
    public void setOnBoomListener(OnBoomListener onBoomListener) {
        this.onBoomListener = onBoomListener;
    }

    /**
     * Whether the BMB has finished booming.
     *
     * @return whether the BMB has finished booming
     */
    public boolean isBoomed() {
        return boomStateEnum == BoomStateEnum.DidShow;
    }

    /**
     * Whether the BMB has finished ReBooming.
     *
     * @return whether the BMB has finished ReBooming
     */
    public boolean isReBoomed() {
        return boomStateEnum == BoomStateEnum.DidHide;
    }

    public int getDimColor() {
        return dimColor;
    }

    /**
     * Set the dim-color of the background when the BMB booms.
     *
     * @param dimColor dim-color of background
     */
    public void setDimColor(int dimColor) {
        this.dimColor = dimColor;
    }

    public long getShowDuration() {
        return showDuration;
    }

    /**
     * Set the duration of every boom-button when booming.
     * Notice that this is not the total duration of the boom animation.
     * For example, if there are 5 boom-buttons, delay of boom animation is 100ms
     * and duration of every boom animation is 1000ms. Then the total duration of
     * all boom animations is 1000ms + 4 * 100ms = 1400ms.
     *
     * @param showDuration duration of every boom-button when booming
     */
    public void setShowDuration(long showDuration) {
        this.showDuration = showDuration;
        setShareLinesViewData();
    }

    public long getShowDelay() {
        return showDelay;
    }

    /**
     * Delay of every boom-button.
     *
     * @param showDelay delay of every boom-button
     */
    public void setShowDelay(long showDelay) {
        this.showDelay = showDelay;
        setShareLinesViewData();
    }

    public long getHideDuration() {
        return hideDuration;
    }
    
    /**
     * Set the duration of every boom-button when re-booming.
     * Notice that this is not the total duration of the re-boom animation.
     * For example, if there are 5 boom-buttons, delay of re-boom animation is 100ms
     * and duration of every re-boom animation is 1000ms. Then the total duration of
     * all re-boom animations is 1000ms + 4 * 100ms = 1400ms.
     *
     * @param hideDuration duration of every boom-button when booming
     */
    public void setHideDuration(long hideDuration) {
        this.hideDuration = hideDuration;
        setShareLinesViewData();
    }

    public long getHideDelay() {
        return hideDelay;
    }

    /**
     * Delay of every boom-button.
     *
     * @param hideDelay delay of every boom-button
     */
    public void setHideDelay(long hideDelay) {
        this.hideDelay = hideDelay;
        setShareLinesViewData();
    }

    public boolean isCancelable() {
        return cancelable;
    }

    /**
     * Whether the BMB is cancelable. If the BMB is cancelable, then when after the boom
     * animation of booming, you can re-boom BMB by clicking the background.
     *
     * @param cancelable whether the BMB is cancelable
     */
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isAutoHide() {
        return autoHide;
    }

    /**
     * Whether the BMB is auto-hide. If the BMB is auto-hide, then when after the boom
     * animation of booming, you can click one of the boom-buttons to re-boom BMB.
     *
     * @param autoHide whether the BMB is auto-hide
     */
    public void setAutoHide(boolean autoHide) {
        this.autoHide = autoHide;
    }

    public OrderEnum getOrderEnum() {
        return orderEnum;
    }

    /**
     * Set the order-enum for BMB.
     *
     * @param orderEnum order-enum
     */
    public void setOrderEnum(OrderEnum orderEnum) {
        this.orderEnum = orderEnum;
    }

    public int getFrames() {
        return frames;
    }

    /**
     * Set the animation-frames for every boom-button animation. The higher, the animations
     * more continue.
     *
     * @param frames frames for every boom-button animation
     */
    public void setFrames(int frames) {
        this.frames = frames;
    }

    public BoomEnum getBoomEnum() {
        return boomEnum;
    }

    /**
     * Set the boom-enum for BMB.
     *
     * @param boomEnum boom-enum
     */
    public void setBoomEnum(BoomEnum boomEnum) {
        this.boomEnum = boomEnum;
    }

    public EaseEnum getShowMoveEaseEnum() {
        return showMoveEaseEnum;
    }

    /**
     * Set the ease type of movement when every boom-button is booming.
     *
     * @param showMoveEaseEnum ease type of movement when booming
     */
    public void setShowMoveEaseEnum(EaseEnum showMoveEaseEnum) {
        this.showMoveEaseEnum = showMoveEaseEnum;
    }

    public EaseEnum getShowScaleEaseEnum() {
        return showScaleEaseEnum;
    }

    /**
     * Set the ease type of scale-animation when every boom-button is booming.
     *
     * @param showScaleEaseEnum ease type of scale-animation when booming
     */
    public void setShowScaleEaseEnum(EaseEnum showScaleEaseEnum) {
        this.showScaleEaseEnum = showScaleEaseEnum;
    }

    public EaseEnum getShowRotateEaseEnum() {
        return showRotateEaseEnum;
    }

    /**
     * Set the ease type of rotate-animation when every boom-button is booming.
     *
     * @param showRotateEaseEnum ease type of rotate-animation when booming
     */
    public void setShowRotateEaseEnum(EaseEnum showRotateEaseEnum) {
        this.showRotateEaseEnum = showRotateEaseEnum;
    }

    public EaseEnum getHideMoveEaseEnum() {
        return hideMoveEaseEnum;
    }

    /**
     * Set the ease type of movement when every boom-button is re-booming.
     *
     * @param hideMoveEaseEnum ease type of movement when re-booming
     */
    public void setHideMoveEaseEnum(EaseEnum hideMoveEaseEnum) {
        this.hideMoveEaseEnum = hideMoveEaseEnum;
    }

    public EaseEnum getHideScaleEaseEnum() {
        return hideScaleEaseEnum;
    }

    /**
     * Set the ease type of scale-animation when every boom-button is re-booming.
     *
     * @param hideScaleEaseEnum ease type of scale-animation when re-booming
     */
    public void setHideScaleEaseEnum(EaseEnum hideScaleEaseEnum) {
        this.hideScaleEaseEnum = hideScaleEaseEnum;
    }

    public EaseEnum getHideRotateEaseEnum() {
        return hideRotateEaseEnum;
    }

    /**
     * Set the ease type of rotate-animation when every boom-button is re-booming.
     *
     * @param hideRotateEaseEnum ease type of rotate-animation when re-booming
     */
    public void setHideRotateEaseEnum(EaseEnum hideRotateEaseEnum) {
        this.hideRotateEaseEnum = hideRotateEaseEnum;
    }

    public int getRotateDegree() {
        return rotateDegree;
    }

    /**
     * Set the rotate degree of rotate-animation of every boom-button.
     *
     * @param rotateDegree rotate degree
     */
    public void setRotateDegree(int rotateDegree) {
        this.rotateDegree = rotateDegree;
    }

    /**
     * Set the button-place-enum.
     *
     * @param buttonPlaceEnum button-place-enum
     */
    public void setButtonPlaceEnum(ButtonPlaceEnum buttonPlaceEnum) {
        this.buttonPlaceEnum = buttonPlaceEnum;
    }

    public ButtonPlaceAlignmentEnum getButtonPlaceAlignmentEnum() {
        return buttonPlaceAlignmentEnum;
    }

    /**
     * Set the button-place-alignment-enum.
     *
     * @param buttonPlaceAlignmentEnum button-place-alignment-enum
     */
    public void setButtonPlaceAlignmentEnum(ButtonPlaceAlignmentEnum buttonPlaceAlignmentEnum) {
        this.buttonPlaceAlignmentEnum = buttonPlaceAlignmentEnum;
    }

    public float getButtonHorizontalMargin() {
        return buttonHorizontalMargin;
    }

    /**
     * Set the horizontal-margin between buttons.
     *
     * @param buttonHorizontalMargin horizontal-margin
     */
    public void setButtonHorizontalMargin(float buttonHorizontalMargin) {
        this.buttonHorizontalMargin = buttonHorizontalMargin;
    }

    public float getButtonVerticalMargin() {
        return buttonVerticalMargin;
    }

    /**
     * Set the vertical-margin between buttons.
     *
     * @param buttonVerticalMargin vertical-margin
     */
    public void setButtonVerticalMargin(float buttonVerticalMargin) {
        this.buttonVerticalMargin = buttonVerticalMargin;
    }

    public float getButtonInclinedMargin() {
        return buttonInclinedMargin;
    }

    /**
     * Set the inclined-margin between buttons.
     *
     * @param buttonInclinedMargin Inclined-margin
     */
    public void setButtonInclinedMargin(float buttonInclinedMargin) {
        this.buttonInclinedMargin = buttonInclinedMargin;
    }

    public float getButtonTopMargin() {
        return buttonTopMargin;
    }

    /**
     * Set the top-alignment margin between screen and buttons.
     *
     * @param buttonTopMargin Top-alignment margin
     */
    public void setButtonTopMargin(float buttonTopMargin) {
        this.buttonTopMargin = buttonTopMargin;
    }

    public float getButtonBottomMargin() {
        return buttonBottomMargin;
    }

    /**
     * Set the bottom-alignment margin between screen and buttons.
     *
     * @param buttonBottomMargin Bottom-alignment margin
     */
    public void setButtonBottomMargin(float buttonBottomMargin) {
        this.buttonBottomMargin = buttonBottomMargin;
    }

    public float getButtonLeftMargin() {
        return buttonLeftMargin;
    }

    /**
     * Set the left-alignment margin between screen and buttons.
     *
     * @param buttonLeftMargin Left-alignment margin
     */
    public void setButtonLeftMargin(float buttonLeftMargin) {
        this.buttonLeftMargin = buttonLeftMargin;
    }

    public float getButtonRightMargin() {
        return buttonRightMargin;
    }

    /**
     * Set the right-alignment margin between screen and buttons.
     *
     * @param buttonRightMargin Right-alignment margin
     */
    public void setButtonRightMargin(float buttonRightMargin) {
        this.buttonRightMargin = buttonRightMargin;
    }

    public float getBottomHamButtonTopMargin() {
        return bottomHamButtonTopMargin;
    }

    /**
     * Set the top-margin of bottom ham-boom-button. This method is used when the bottom
     * ham-boom-button has different meaning compared with others. For example, the bottom
     * ham-boom-button maybe used as a "cancel" selection.
     *
     * @param bottomHamButtonTopMargin top-margin of bottom ham-boom-button
     */
    public void setBottomHamButtonTopMargin(float bottomHamButtonTopMargin) {
        this.bottomHamButtonTopMargin = bottomHamButtonTopMargin;
    }

    //endregion
}
