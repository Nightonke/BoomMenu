package com.nightonke.boommenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nightonke.boommenu.Eases.EaseType;

import java.util.Random;

/**
 * Created by Weiping on 2016/3/19.
 */
public class BoomMenuButton extends FrameLayout implements CircleButton.OnCircleButtonClickListener {

    private final int MIN_CIRCLE_BUTTON_NUMBER = 1;
    private final int MAX_CIRCLE_BUTTON_NUMBER = 9;
    private final int MIN_HAM_BUTTON_NUMBER = 1;
    private final int MAX_HAM_BUTTON_NUMBER = 4;

    private ShadowLayout shadowLayout;
    private FrameLayout frameLayout;

    private int[][] startLocations = new int[MAX_CIRCLE_BUTTON_NUMBER][2];
    private int[][] endLocations = new int[MAX_CIRCLE_BUTTON_NUMBER][2];

    private boolean animationPlaying = false;

    private int buttonNum = 0;
    private CircleButton[] circleButtons = new CircleButton[MAX_CIRCLE_BUTTON_NUMBER];
    private Dot[] dots = new Dot[MAX_CIRCLE_BUTTON_NUMBER];
    private int[] colors = new int[MAX_CIRCLE_BUTTON_NUMBER];

    // Frames of animations
    private int frames = 80;
    // Duration of animations
    private int duration = 800;
    // Delay
    private int delay = 100;
    // Show order type
    private OrderType showOrderType = OrderType.DEFAULT;
    // Hide order type
    private OrderType hideOrderType = OrderType.DEFAULT;
    // Button type
    private ButtonType buttonType = ButtonType.CIRCLE;
    // Boom type
    private BoomType boomType = BoomType.HORIZONTAL_THROW;
    // Place type
    private PlaceType placeType = null;
    // Particle effect
    private ParticleEffect particleEffect;
    // Default dot width
    private int dotWidth = 10;
    // Default circle button width
    private int buttonWidth = (int)Util.getInstance().dp2px(88);
    // Movement ease
    private EaseType showMoveEaseType = EaseType.EaseOutBack;
    private EaseType hideMoveEaseType = EaseType.EaseOutCirc;
    // Scale ease
    private EaseType showScaleEaseType = EaseType.EaseOutBack;
    private EaseType hideScaleEaseType = EaseType.Linear;
    // Whether rotate
    private int rotateDegree = 720;
    // Rotate ease
    private EaseType showRotateEaseType = EaseType.EaseOutBack;
    private EaseType hideRotateEaseType = EaseType.Linear;
    // Auto dismiss
    private boolean autoDismiss = true;
    // Cancelable
    private boolean cancelable = true;

    private OnClickListener onClickListener = null;
    private AnimatorListener animatorListener = null;
    private OnSubButtonClickListener onSubButtonClickListener = null;

    private Context mContext;

    public BoomMenuButton(Context context) {
        this(context, null);
    }

    public BoomMenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.boom_menu_button, this, true);
        shadowLayout = (ShadowLayout)findViewById(R.id.shadow_layout);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
            }
        });
    }

    public void init(
            Drawable[] drawables,
            String[] strings,
            int[] colors,
            ButtonType buttonType,
            BoomType boomType,
            PlaceType placeType,
            ParticleEffect particleEffect,
            EaseType showMoveEaseType,
            EaseType showScaleEaseType,
            EaseType showRotateEaseType,
            EaseType hideMoveEaseType,
            EaseType hideScaleEaseType,
            EaseType hideRotateEaseType,
            Integer rotateDegree) {

        // judge
        errorJudge(drawables, strings, colors, buttonType);

        this.buttonType = buttonType;
        this.boomType = boomType;
        if (placeType == null) throw new RuntimeException("Place type is null!");
        else this.placeType = placeType;
        if (particleEffect != null) this.particleEffect = particleEffect;
        if (showMoveEaseType != null) this.showMoveEaseType = showMoveEaseType;
        if (showScaleEaseType != null) this.showScaleEaseType = showScaleEaseType;
        if (showRotateEaseType != null) this.showRotateEaseType = showRotateEaseType;
        if (hideMoveEaseType != null) this.hideMoveEaseType = hideMoveEaseType;
        if (hideScaleEaseType != null) this.hideScaleEaseType = hideScaleEaseType;
        if (hideRotateEaseType != null) this.hideRotateEaseType = hideRotateEaseType;
        if (rotateDegree != null) this.rotateDegree = rotateDegree;

        if (buttonType.equals(ButtonType.CIRCLE)) {
            // circle buttons
            // create buttons
            buttonNum = (drawables == null ? strings.length : drawables.length);
            for (int i = 0; i < buttonNum; i++) {
                circleButtons[i] = new CircleButton(mContext);
                circleButtons[i].setOnCircleButtonClickListener(this, i);
                if (drawables != null) circleButtons[i].setDrawable(drawables[i]);
                if (strings != null) circleButtons[i].setText(strings[i]);
                if (colors != null) circleButtons[i].setColor(colors[i]);
            }

            // create dots
            for (int i = 0; i < buttonNum; i++) {
                dots[i] = new Dot(mContext);
                if (colors != null) dots[i].setColor(colors[i]);
            }

            // place dots according to the number of them and the place type
            placeDots();
        } else if (buttonType.equals(ButtonType.HAM)) {
            // hamburger button
        }
    }

    private void errorJudge(
            Drawable[] drawables,
            String[] strings,
            int[] colors,
            ButtonType buttonType) {
        if (drawables == null && strings == null) {
            throw new RuntimeException("The button's drawables and strings are both null!");
        }
        if (buttonType.equals(ButtonType.CIRCLE)) {
            if ((drawables != null
                    && !(
                    MIN_CIRCLE_BUTTON_NUMBER <= drawables.length
                            && drawables.length <= MAX_CIRCLE_BUTTON_NUMBER))
                    || (strings != null
                    && !(
                    MIN_CIRCLE_BUTTON_NUMBER <= strings.length
                            && strings.length <= MAX_CIRCLE_BUTTON_NUMBER))
                    || (colors != null
                    && !(
                    MIN_CIRCLE_BUTTON_NUMBER <= colors.length
                            && colors.length <= MAX_CIRCLE_BUTTON_NUMBER))) {
                throw new RuntimeException("The circle type button's length must be in [" +
                        MIN_CIRCLE_BUTTON_NUMBER + ", " +
                        MAX_CIRCLE_BUTTON_NUMBER + "]!");
            }
        } else if (buttonType.equals(ButtonType.HAM)) {
            if ((drawables != null
                    && !(
                    MIN_HAM_BUTTON_NUMBER <= drawables.length
                            && drawables.length <= MAX_HAM_BUTTON_NUMBER))
                    || (strings != null
                    && !(
                    MIN_HAM_BUTTON_NUMBER <= strings.length
                            && strings.length <= MAX_HAM_BUTTON_NUMBER))
                    || (colors != null
                    && !(
                    MIN_HAM_BUTTON_NUMBER <= colors.length
                            && colors.length <= MAX_HAM_BUTTON_NUMBER))) {
                throw new RuntimeException("The ham type button's length must be in [" +
                        MIN_HAM_BUTTON_NUMBER + ", " +
                        MAX_HAM_BUTTON_NUMBER + "]!");
            }
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private void placeDots() {
        frameLayout.removeAllViews();
        int width = frameLayout.getWidth();
        FrameLayout.LayoutParams params = null;
        switch (buttonNum) {
            case 1:
                params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                params.leftMargin = width / 2 - dotWidth / 2;
                params.topMargin = width / 2 - dotWidth / 2;
                frameLayout.addView(dots[0], params);
                break;
            case 2:
                if (placeType.equals(PlaceType.CIRCLE_2_1)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_2_2)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                }
                break;
            case 3:
                if (placeType.equals(PlaceType.CIRCLE_3_1)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_3_2)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_3_3)) {
                    int c = width / 6;
                    int a = c / 2;
                    int b = (int)Math.sqrt(c * c - a * a);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - c - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - b - dotWidth / 2;
                    params.topMargin = width / 2 + a - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + b - dotWidth / 2;
                    params.topMargin = width / 2 + a - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_3_4)) {
                    int c = width / 6;
                    int a = c / 2;
                    int b = (int)Math.sqrt(c * c - a * a);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + c - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - b - dotWidth / 2;
                    params.topMargin = width / 2 - a - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + b - dotWidth / 2;
                    params.topMargin = width / 2 - a - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                }
                break;
            case 4:
                if (placeType.equals(PlaceType.CIRCLE_4_1)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 3 / 8 - dotWidth / 2;
                    params.topMargin = width * 3 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 8 - dotWidth / 2;
                    params.topMargin = width * 3 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 3 / 8 - dotWidth / 2;
                    params.topMargin = width * 5 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 8 - dotWidth / 2;
                    params.topMargin = width * 5 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_4_2)) {
                    double s2 = Math.sqrt(2);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = (int) (width / 2 - width / 4 / s2 - dotWidth / 2);
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = (int) (width / 2 - width / 4 / s2 - dotWidth / 2);
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = (int) (width / 2 + width / 4 / s2 - dotWidth / 2);
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = (int) (width / 2 + width / 4 / s2 - dotWidth / 2);
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                }
                break;
            case 5:
                if (placeType.equals(PlaceType.CIRCLE_5_1)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = (int) (width * 5.5 / 12 - dotWidth / 2);
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = (int) (width * 5.5 / 12 - dotWidth / 2);
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = (int) (width * 5.5 / 12 - dotWidth / 2);
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = (int) (width * 7.5 / 12 - dotWidth / 2);
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = (int) (width * 7.5 / 12 - dotWidth / 2);
                    frameLayout.addView(dots[4], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_5_2)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_5_3)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 3 / 8 - dotWidth / 2;
                    params.topMargin = width * 3 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 8 - dotWidth / 2;
                    params.topMargin = width * 3 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 3 / 8 - dotWidth / 2;
                    params.topMargin = width * 5 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 8 - dotWidth / 2;
                    params.topMargin = width * 5 / 8 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_5_4)) {
                    double s2 = Math.sqrt(2);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = (int) (width / 2 - width / 4 / s2 - dotWidth / 2);
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = (int) (width / 2 - width / 4 / s2 - dotWidth / 2);
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = (int) (width / 2 + width / 4 / s2 - dotWidth / 2);
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = (int) (width / 2 + width / 4 / s2 - dotWidth / 2);
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                }
                break;
            case 6:
                if (placeType.equals(PlaceType.CIRCLE_6_1)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_6_2)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_6_3)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_6_4)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_6_5)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 * 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 * 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_6_6)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 * 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 * 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                }
                break;
            case 7:
                if (placeType.equals(PlaceType.CIRCLE_7_1)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_7_2)) {
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_7_3)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 7 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 5 / 12 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_7_4)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                }
                break;
            case 8:
                if (placeType.equals(PlaceType.CIRCLE_8_1)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 3 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 2 / 3 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[7], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_8_2)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 5 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width * 7 / 12 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 3 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width * 2 / 3 - dotWidth / 2;
                    frameLayout.addView(dots[7], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_8_3)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[7], params);
                }
                break;
            case 9:
                if (placeType.equals(PlaceType.CIRCLE_9_1)) {
                    int dis1 = width / 12;
                    int dis2 = (int) (dis1 * Math.sqrt(3));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis2 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[7], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis2 - dotWidth / 2;
                    frameLayout.addView(dots[8], params);
                }
                if (placeType.equals(PlaceType.CIRCLE_9_2)) {
                    int dis1 = (int) (width / 12 * Math.sqrt(6));
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis1 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis1 / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dis1 / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[3], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[4], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[5], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dis1 / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis1 / 2 - dotWidth / 2;
                    frameLayout.addView(dots[6], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 + dis1 / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis1 / 2 - dotWidth / 2;
                    frameLayout.addView(dots[7], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 + dis1 - dotWidth / 2;
                    frameLayout.addView(dots[8], params);
                }
                break;

        }
    }

    private void shoot() {
        // listener
        if (onClickListener != null) onClickListener.onClick();
        // wait for the before animations finished
        if (animationPlaying) return;
        // dim the animation layout
        dimAnimationLayout();
        // start all animations
        startShowAnimations();
    }

    private void dimAnimationLayout() {
        if (animationLayout == null) {
            animationLayout = createAnimationLayout();
            animationLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (animationPlaying) return;
                    if (cancelable) startHideAnimations();
                }
            });
        }
        animationLayout.setVisibility(VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animationLayout, "backgroundColor",
                ContextCompat.getColor(mContext, R.color.lightness),
                ContextCompat.getColor(mContext, R.color.darkness))
                .setDuration(duration + delay * (buttonNum - 1));
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (animatorListener != null) animatorListener.toShow();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (animatorListener != null) animatorListener.showed();
            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animatorListener != null) animatorListener.showing(animation.getAnimatedFraction());
            }
        });
        objectAnimator.start();
    }

    private void startShowAnimations() {
        if (animationLayout != null) animationLayout.removeAllViews();
        getEndLocations();
        if (showOrderType.equals(OrderType.DEFAULT)) {
            for (int i = 0; i < buttonNum; i++) {
                dots[i].getLocationOnScreen(startLocations[i]);
                startLocations[i][0] -= (buttonWidth - dots[i].getWidth()) / 2;
                startLocations[i][1] -= (buttonWidth - dots[i].getHeight()) / 2;
                setShowAnimation(dots[i], circleButtons[i], startLocations[i], endLocations[i], i);
            }
        } else if (showOrderType.equals(OrderType.REVERSE)) {
            for (int i = 0; i < buttonNum; i++) {
                dots[i].getLocationOnScreen(startLocations[i]);
                startLocations[i][0] -= (buttonWidth - dots[i].getWidth()) / 2;
                startLocations[i][1] -= (buttonWidth - dots[i].getHeight()) / 2;
                setShowAnimation(dots[i], circleButtons[i], startLocations[i], endLocations[i], buttonNum - i - 1);
            }
        } else if (showOrderType.equals(OrderType.RANDOM)) {
            Random random = new Random();
            boolean[] used = new boolean[buttonNum];
            for (int i = 0; i < buttonNum; i++) used[i] = false;
            int count = 0;
            while (true) {
                int i = random.nextInt(buttonNum);
                if (!used[i]) {
                    used[i] = true;
                    
                    dots[count].getLocationOnScreen(startLocations[count]);
                    startLocations[count][0] -= (buttonWidth - dots[count].getWidth()) / 2;
                    startLocations[count][1] -= (buttonWidth - dots[count].getHeight()) / 2;
                    setShowAnimation(dots[count], circleButtons[count], startLocations[count], endLocations[count], i);

                    count++;
                    if (count == buttonNum) break;
                }
            }
        }
    }

    private void getEndLocations() {
        int width = Util.getInstance().getScreenWidth(mContext);
        int height = Util.getInstance().getScreenHeight(mContext);
        if (buttonType.equals(ButtonType.CIRCLE)) {
            switch (buttonNum) {
                case 1:
                    endLocations[0][0] = width / 2 - buttonWidth / 2;
                    endLocations[0][1] = height / 2 - buttonWidth / 2;
                    break;
                case 2:
                    if (placeType.equals(PlaceType.CIRCLE_2_1)) {
                        endLocations[0][0] = width / 3 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width * 2 / 3 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_2_2)) {
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 3 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = height * 2 / 3 - buttonWidth / 2;
                    }
                    break;
                case 3:
                    if (placeType.equals(PlaceType.CIRCLE_3_1)) {
                        int dis = buttonWidth * 9 / 8;
                        endLocations[0][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_3_2)) {
                        int dis = buttonWidth * 9 / 8;
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 + dis - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_3_3)) {
                        int b = width / 6;
                        int c = (int)(2 * b / Math.sqrt(3));
                        int a = c / 2;
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - c - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - b - buttonWidth / 2;
                        endLocations[1][1] = height / 2 + a - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + b - buttonWidth / 2;
                        endLocations[2][1] = height / 2 + a - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_3_4)) {
                        int b = width / 6;
                        int c = (int)(2 * b / Math.sqrt(3));
                        int a = c / 2;
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 + c - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - b - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - a - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + b - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - a - buttonWidth / 2;
                    }
                    break;
                case 4:
                    if (placeType.equals(PlaceType.CIRCLE_4_1)) {
                        endLocations[0][0] = width / 3 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - width / 6 - buttonWidth / 2;
                        endLocations[1][0] = width * 2 / 3 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - width / 6 - buttonWidth / 2;
                        endLocations[2][0] = width / 3 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 + width / 6 - buttonWidth / 2;
                        endLocations[3][0] = width * 2 / 3 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 + width / 6 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_4_2)) {
                        double s2 = Math.sqrt(2);
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = (int) (height / 2 - width / 3 / s2 - buttonWidth / 2);
                        endLocations[1][0] = (int) (width / 2 + width / 3 / s2 - buttonWidth / 2);
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - buttonWidth / 2;
                        endLocations[2][1] = (int) (height / 2 + width / 3 / s2 - buttonWidth / 2);
                        endLocations[3][0] = (int) (width / 2 - width / 3 / s2 - buttonWidth / 2);
                        endLocations[3][1] = height / 2 - buttonWidth / 2;
                    }
                    break;
                case 5:
                    if (placeType.equals(PlaceType.CIRCLE_5_1)) {
                        double s3 = Math.sqrt(3);
                        int h = height / 2;
                        endLocations[0][0] = width / 4 - buttonWidth / 2;
                        endLocations[0][1] = h - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = h - buttonWidth / 2;
                        endLocations[2][0] = width * 3 / 4 - buttonWidth / 2;
                        endLocations[2][1] = h - buttonWidth / 2;
                        endLocations[3][0] = width * 3 / 8 - buttonWidth / 2;
                        endLocations[3][1] = (int) (h + s3 / 8 * width - buttonWidth / 2);
                        endLocations[4][0] = width * 5 / 8 - buttonWidth / 2;
                        endLocations[4][1] = (int) (h + s3 / 8 * width - buttonWidth / 2);
                    }
                    if (placeType.equals(PlaceType.CIRCLE_5_2)) {
                        double s3 = Math.sqrt(3);
                        int h = height / 2;
                        endLocations[0][0] = width * 3 / 8 - buttonWidth / 2;
                        endLocations[0][1] = h - buttonWidth / 2;
                        endLocations[1][0] = width * 5 / 8 - buttonWidth / 2;
                        endLocations[1][1] = h - buttonWidth / 2;
                        endLocations[2][0] = width / 4 - buttonWidth / 2;
                        endLocations[2][1] = (int) (h + s3 / 8 * width - buttonWidth / 2);
                        endLocations[3][0] = width / 2 - buttonWidth / 2;
                        endLocations[3][1] = (int) (h + s3 / 8 * width - buttonWidth / 2);
                        endLocations[4][0] = width * 3 / 4 - buttonWidth / 2;
                        endLocations[4][1] = (int) (h + s3 / 8 * width - buttonWidth / 2);
                    }
                    if (placeType.equals(PlaceType.CIRCLE_5_3)) {
                        int dis = (int) ((buttonWidth * 9 / 8) / Math.sqrt(2));
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - dis - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[3][1] = height / 2 + dis - buttonWidth / 2;
                        endLocations[4][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_5_4)) {
                        int dis = buttonWidth * 9 / 8;
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - dis - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis - buttonWidth / 2;
                    }
                    break;
                case 6:
                    if (placeType.equals(PlaceType.CIRCLE_6_1)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = buttonWidth * 9 / 16;
                        endLocations[0][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_6_2)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = buttonWidth * 9 / 16;
                        endLocations[0][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis1 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_6_3)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_6_4)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 - dis1 / 2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_6_5)) {
                        int dis1 = buttonWidth * 9 / 16;
                        int dis2 = (int) (dis1 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - dis1 * 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis1 * 2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_6_6)) {
                        int dis1 = buttonWidth * 9 / 16;
                        int dis2 = (int) (dis1 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - dis1 * 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis1 * 2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 - dis2 - buttonWidth / 2;
                    }
                    break;
                case 7:
                    if (placeType.equals(PlaceType.CIRCLE_7_1)) {
                        int dis = buttonWidth * 9 / 8;
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis - buttonWidth / 2;
                        endLocations[5][0] = width / 2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis - buttonWidth / 2;
                        endLocations[6][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[6][1] = height / 2 + dis - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_7_2)) {
                        int dis = buttonWidth * 9 / 8;
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 + dis - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - dis - buttonWidth / 2;
                        endLocations[4][1] = height / 2 - dis - buttonWidth / 2;
                        endLocations[5][0] = width / 2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 - dis - buttonWidth / 2;
                        endLocations[6][0] = width / 2 + dis - buttonWidth / 2;
                        endLocations[6][1] = height / 2 - dis - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_7_3)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[6][0] = width / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[6][1] = height / 2 + dis2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_7_4)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[6][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[6][1] = height / 2 - dis1 / 2 - buttonWidth / 2;
                    }
                    break;
                case 8:
                    if (placeType.equals(PlaceType.CIRCLE_8_1)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - dis2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[6][0] = width / 2 - buttonWidth / 2;
                        endLocations[6][1] = height / 2 + dis2 - buttonWidth / 2;
                        endLocations[7][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[7][1] = height / 2 + dis2 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_8_2)) {
                        int dis1 = buttonWidth * 9 / 8;
                        int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
                        endLocations[0][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - dis2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[6][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[6][1] = height / 2 - buttonWidth / 2;
                        endLocations[7][0] = width / 2 + dis2 - buttonWidth / 2;
                        endLocations[7][1] = height / 2 + dis1 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_8_3)) {
                        int dis1 = buttonWidth * 9 / 8;
                        endLocations[0][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[6][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[6][1] = height / 2 - buttonWidth / 2;
                        endLocations[7][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[7][1] = height / 2 + dis1 - buttonWidth / 2;
                    }
                    break;
                case 9:
                    if (placeType.equals(PlaceType.CIRCLE_9_1)) {
                        int dis1 = buttonWidth * 9 / 8;
                        endLocations[0][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 + dis1 - buttonWidth / 2;
                        endLocations[6][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[6][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[7][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[7][1] = height / 2 - buttonWidth / 2;
                        endLocations[8][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[8][1] = height / 2 + dis1 - buttonWidth / 2;
                    }
                    if (placeType.equals(PlaceType.CIRCLE_9_2)) {
                        int dis1 = (int) (buttonWidth * 8 / 8 * Math.sqrt(2));
                        endLocations[0][0] = width / 2 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - dis1 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[2][0] = width / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[3][0] = width / 2 - dis1 - buttonWidth / 2;
                        endLocations[3][1] = height / 2 - buttonWidth / 2;
                        endLocations[4][0] = width / 2 - buttonWidth / 2;
                        endLocations[4][1] = height / 2 - buttonWidth / 2;
                        endLocations[5][0] = width / 2 + dis1 - buttonWidth / 2;
                        endLocations[5][1] = height / 2 - buttonWidth / 2;
                        endLocations[6][0] = width / 2 - dis1 / 2 - buttonWidth / 2;
                        endLocations[6][1] = height / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[7][0] = width / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[7][1] = height / 2 + dis1 / 2 - buttonWidth / 2;
                        endLocations[8][0] = width / 2 - buttonWidth / 2;
                        endLocations[8][1] = height / 2 + dis1 - buttonWidth / 2;
                    }
            }
        }
    }

    private ViewGroup animationLayout = null;
    private ViewGroup createAnimationLayout() {
        ViewGroup rootView = (ViewGroup) ((Activity)mContext).getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(layoutParams);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View setViewLocationInAnimationLayout(final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                buttonWidth,
                buttonWidth);
        lp.leftMargin = x;
        lp.topMargin = y;
        animationLayout.addView(view, lp);
        return view;
    }

    public void setShowAnimation(
            final View dot,
            final View button,
            int[] startLocation,
            int[] endLocation,
            int index) {
        animationPlaying = true;
        button.bringToFront();

        final View view = setViewLocationInAnimationLayout(button, startLocation);

        float[] sl = new float[2];
        float[] ml = new float[2];
        float[] el = new float[2];
        sl[0] = startLocation[0] * 1.0f;
        sl[1] = startLocation[1] * 1.0f;
        el[0] = endLocation[0] * 1.0f;
        el[1] = endLocation[1] * 1.0f;

        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        getShowXY(sl, el, xs, ys);

        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(view, "x", xs).setDuration(duration);
        xAnimator.setStartDelay(delay * index);
        xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
        xAnimator.start();

        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(view, "y", ys).setDuration(duration);
        yAnimator.setStartDelay(delay * index);
        yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
        yAnimator.start();

        view.setScaleX(dotWidth / buttonWidth);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX",
                dotWidth / buttonWidth,
                1f).setDuration(duration);
        scaleXAnimator.setStartDelay(delay * index);
        scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showScaleEaseType));
        scaleXAnimator.start();

        view.setScaleY(dotWidth / buttonWidth);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY",
                dotWidth / buttonWidth,
                1f).setDuration(duration);
        scaleYAnimator.setStartDelay(delay * index);
        scaleYAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showScaleEaseType));
        scaleYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                dot.setVisibility(INVISIBLE);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationPlaying = false;
            }
        });
        scaleYAnimator.start();

        if (view instanceof CircleButton) {
            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(
                    ((CircleButton) view).getFrameLayout(), "rotation",
                    0,
                    rotateDegree).setDuration(duration);
            rotateAnimator.setStartDelay(delay * index);
            rotateAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showRotateEaseType));
            rotateAnimator.start();
        }

    }

    private void getShowXY(float[] startPoint, float[] endPoint, float[] xs, float[] ys) {
        if (boomType.equals(BoomType.LINE)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x2 = endPoint[0];
            float y2 = endPoint[1];
            float k = (y2 - y1) / (x2 - x1);
            float b = y1 - x1 * k;

            float per = 1f / frames;
            float xx = endPoint[0] - startPoint[0];
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = startPoint[0] + offset * xx;
                ys[i] = k * xs[i] + b;
            }
        } else if (boomType.equals(BoomType.PARABOLA)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x2 = endPoint[0];
            float y2 = endPoint[1];
            float x3 = (startPoint[0] + endPoint[0]) / 2;
            float y3 = startPoint[1] / 2;
            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = x2 - x1;
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = x1 + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        } else if (boomType.equals(BoomType.HORIZONTAL_THROW)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x3 = endPoint[0];
            float y3 = endPoint[1];
            float x2 = x3 * 2 - x1;
            float y2 = y1;

            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = x3 - x1;
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = x1 + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        } else if (boomType.equals(BoomType.PARABOLA_2)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x2 = endPoint[0];
            float y2 = endPoint[1];
            float x3 = (startPoint[0] + endPoint[0]) / 2;
            float y3 = (Util.getInstance().getScreenHeight(mContext) + startPoint[1]) / 2;
            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = x2 - x1;
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = x1 + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        } else if (boomType.equals(BoomType.HORIZONTAL_THROW_2)) {
            float x1 = endPoint[0];
            float y1 = endPoint[1];
            float x3 = startPoint[0];
            float y3 = startPoint[1];
            float x2 = x3 * 2 - x1;
            float y2 = y1;

            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = endPoint[0] - startPoint[0];
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = startPoint[0] + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        }

    }

    private void getHideXY(float[] startPoint, float[] endPoint, float[] xs, float[] ys) {
        if (boomType.equals(BoomType.LINE)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x2 = endPoint[0];
            float y2 = endPoint[1];
            float k = (y2 - y1) / (x2 - x1);
            float b = y1 - x1 * k;

            float per = 1f / frames;
            float xx = endPoint[0] - startPoint[0];
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = startPoint[0] + offset * xx;
                ys[i] = k * xs[i] + b;
            }
        } else if (boomType.equals(BoomType.PARABOLA)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x2 = endPoint[0];
            float y2 = endPoint[1];
            float x3 = (startPoint[0] + endPoint[0]) / 2;
            float y3 = startPoint[1] / 2;
            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = endPoint[0] - startPoint[0];
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = startPoint[0] + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        } else if (boomType.equals(BoomType.HORIZONTAL_THROW)) {
            float x1 = endPoint[0];
            float y1 = endPoint[1];
            float x3 = startPoint[0];
            float y3 = startPoint[1];
            float x2 = x3 * 2 - x1;
            float y2 = y1;

            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = endPoint[0] - startPoint[0];
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = startPoint[0] + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        } else if (boomType.equals(BoomType.PARABOLA_2)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x2 = endPoint[0];
            float y2 = endPoint[1];
            float x3 = (startPoint[0] + endPoint[0]) / 2;
            float y3 = (Util.getInstance().getScreenHeight(mContext) + endPoint[1]) / 2;
            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = x2 - x1;
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = x1 + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        } else if (boomType.equals(BoomType.HORIZONTAL_THROW_2)) {
            float x1 = startPoint[0];
            float y1 = startPoint[1];
            float x3 = endPoint[0];
            float y3 = endPoint[1];
            float x2 = x3 * 2 - x1;
            float y2 = y1;

            float a, b, c;

            a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                    / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
            b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
            c = y1 - (x1 * x1) * a - x1 * b;

            float per = 1f / frames;
            float xx = endPoint[0] - startPoint[0];
            for (int i = 0; i <= frames; i++) {
                float offset = i * per;
                xs[i] = startPoint[0] + offset * xx;
                ys[i] = a * xs[i] * xs[i] + b * xs[i] + c;
            }
        }

    }

    private void startHideAnimations() {
        animationPlaying = true;
        lightAnimationLayout();
        if (hideOrderType.equals(OrderType.DEFAULT)) {
            for (int i = 0; i < buttonNum; i++) {
                setHideAnimation(dots[i], circleButtons[i], endLocations[i], startLocations[i], i);
            }
        } else if (hideOrderType.equals(OrderType.REVERSE)) {
            for (int i = 0; i < buttonNum; i++) {
                setHideAnimation(dots[i], circleButtons[i], endLocations[i], startLocations[i], buttonNum - i - 1);
            }
        } else if (hideOrderType.equals(OrderType.RANDOM)) {
            Random random = new Random();
            boolean[] used = new boolean[buttonNum];
            for (int i = 0; i < buttonNum; i++) used[i] = false;
            int count = 0;
            while (true) {
                int i = random.nextInt(buttonNum);
                if (!used[i]) {
                    used[i] = true;

                    setHideAnimation(
                            dots[count],
                            circleButtons[count],
                            endLocations[count],
                            startLocations[count],
                            i);

                    count++;
                    if (count == buttonNum) break;
                }
            }
        }
    }

    public void setHideAnimation(
            final View dot,
            final View button,
            int[] startLocation,
            int[] endLocation,
            int index) {

        float[] sl = new float[2];
        float[] ml = new float[2];
        float[] el = new float[2];
        sl[0] = startLocation[0] * 1.0f;
        sl[1] = startLocation[1] * 1.0f;
        el[0] = endLocation[0] * 1.0f;
        el[1] = endLocation[1] * 1.0f;

        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        getHideXY(sl, el, xs, ys);

        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(button, "x", xs).setDuration(duration);
        xAnimator.setStartDelay(index * delay);
        xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
        xAnimator.start();

        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(button, "y", ys).setDuration(duration);
        yAnimator.setStartDelay(index * delay);
        yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
        yAnimator.start();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(button, "scaleX",
                1f,
                dotWidth / buttonWidth).setDuration(duration);
        scaleXAnimator.setStartDelay(index * delay);
        scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideScaleEaseType));
        scaleXAnimator.start();

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(button, "scaleY",
                1f,
                dotWidth / buttonWidth).setDuration(duration);
        scaleYAnimator.setStartDelay(index * delay);
        scaleYAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideScaleEaseType));
        scaleYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                dot.setVisibility(VISIBLE);
            }
        });
        scaleYAnimator.start();

        if (button instanceof CircleButton) {
            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(
                    ((CircleButton) button).getFrameLayout(), "rotation",
                    0,
                    -rotateDegree).setDuration(duration);
            rotateAnimator.setStartDelay(index * delay);
            rotateAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideRotateEaseType));
            rotateAnimator.start();
        }

    }

    public void lightAnimationLayout() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animationLayout, "backgroundColor",
                ContextCompat.getColor(mContext, R.color.darkness),
                ContextCompat.getColor(mContext, R.color.lightness))
                .setDuration(duration + delay * (buttonNum - 1));
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (animatorListener != null) animatorListener.toHide();
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationLayout.removeAllViews();
                animationLayout.setVisibility(GONE);
                animationPlaying = false;
                if (animatorListener != null) animatorListener.hided();
            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animatorListener != null) animatorListener.hiding(animation.getAnimatedFraction());
            }
        });
        objectAnimator.start();
    }

    public void setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void setRotateDegree(int rotateDegree) {
        this.rotateDegree = rotateDegree;
    }

    public void setShowOrderType(OrderType showOrderType) {
        this.showOrderType = showOrderType;
    }

    public void setHideOrderType(OrderType hideOrderType) {
        this.hideOrderType = hideOrderType;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setAnimatorListener(AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    public ImageButton[] getImageButtons() {
        ImageButton[] imageButtons = new ImageButton[buttonNum];
        for (int i = 0; i < buttonNum; i++) imageButtons[i] = circleButtons[i].getImageButton();
        return imageButtons;
    }

    public TextView[] getTextViews() {
        TextView[] textViews = new TextView[buttonNum];
        for (int i = 0; i < buttonNum; i++) textViews[i] = circleButtons[i].getTextView();
        return textViews;
    }

    public void setOnSubButtonClickListener(OnSubButtonClickListener onSubButtonClickListener) {
        this.onSubButtonClickListener = onSubButtonClickListener;
    }

    @Override
    public void onClick(int index) {
        if (onSubButtonClickListener != null) onSubButtonClickListener.onClick(index);
        if (autoDismiss && !animationPlaying) startHideAnimations();
    }

    public interface OnClickListener {
        void onClick();
    }

    public interface AnimatorListener {
        void toShow();
        void showing(float fraction);
        void showed();
        void toHide();
        void hiding(float fraction);
        void hided();
    }

    public interface OnSubButtonClickListener {
        void onClick(int buttonIndex);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("BBB", "123");
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (cancelable && !animationPlaying) startHideAnimations();
                return true;
        }
        return false;
    }

}
