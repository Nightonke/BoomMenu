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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dd.ShadowLayout;
import com.nightonke.boommenu.Eases.EaseType;

/**
 * Created by Weiping on 2016/3/19.
 */
public class BoomMenuButton extends FrameLayout {

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
    // Button type
    private ButtonType buttonType = ButtonType.CIRCLE;
    // Boom type
    private BoomType boomType = BoomType.PARABOLA;
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
    private EaseType hideRotateEaseType = EaseType.EaseOutCirc;

    private OnClickListener onClickListener = null;
    private AnimatorListener animatorListener = null;

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

    private void placeDots() {
        Log.d("BBB", placeType + "");
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
                    params.leftMargin = width / 4 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[0], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width / 2 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[1], params);
                    params = new FrameLayout.LayoutParams(dotWidth, dotWidth);
                    params.leftMargin = width * 3 / 4 - dotWidth / 2;
                    params.topMargin = width / 2 - dotWidth / 2;
                    frameLayout.addView(dots[2], params);
                }
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
                    startHideAnimations();
                }
            });
        }
        animationLayout.setVisibility(VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animationLayout, "backgroundColor",
                ContextCompat.getColor(mContext, R.color.lightness),
                ContextCompat.getColor(mContext, R.color.darkness)).setDuration(duration);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.start();
    }

    private void startShowAnimations() {
        if (animationLayout != null) animationLayout.removeAllViews();
        getEndLocations();
        for (int i = 0; i < buttonNum; i++) {
            dots[i].getLocationOnScreen(startLocations[i]);
            startLocations[i][0] -= (buttonWidth - dots[i].getWidth()) / 2;
            startLocations[i][1] -= (buttonWidth - dots[i].getHeight()) / 2;
            setShowAnimation(dots[i], circleButtons[i], startLocations[i], endLocations[i]);
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
                        endLocations[0][0] = width / 4 - buttonWidth / 2;
                        endLocations[0][1] = height / 2 - buttonWidth / 2;
                        endLocations[1][0] = width / 2 - buttonWidth / 2;
                        endLocations[1][1] = height / 2 - buttonWidth / 2;
                        endLocations[2][0] = width * 3 / 4 - buttonWidth / 2;
                        endLocations[2][1] = height / 2 - buttonWidth / 2;
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

    public void setShowAnimation(final View dot, final View button, int[] startLocation, int[] endLocation) {
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
        ml[0] = (sl[0] + el[0]) / 2;  // + (new Random().nextInt(200) - 100);
        ml[1] = sl[1] / 2;

        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        getXY(sl, el, ml, xs, ys);

        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(view, "x", xs).setDuration(duration);
        xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
        xAnimator.start();

        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(view, "y", ys).setDuration(duration);
        yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
        yAnimator.start();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX",
                dotWidth / Util.getInstance().dp2px(buttonWidth),
                1f).setDuration(duration);
        scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showScaleEaseType));
        scaleXAnimator.start();

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY",
                dotWidth / Util.getInstance().dp2px(buttonWidth),
                1f).setDuration(duration);
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
    }

    private void getXY(float[] startPoint, float[] endPoint, float[] midPoint, float[] xs, float[] ys) {
        float x1 = startPoint[0];
        float y1 = startPoint[1];
        float x2 = endPoint[0];
        float y2 = endPoint[1];
        float x3 = midPoint[0];
        float y3 = midPoint[1];
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

    private void startHideAnimations() {
        for (int i = 0; i < buttonNum; i++) {
            setHideAnimation(dots[i], circleButtons[i], endLocations[i], startLocations[i]);
        }
    }

    public void setHideAnimation(final View dot, final View button, int[] startLocation, int[] endLocation) {
        animationPlaying = true;

        float[] sl = new float[2];
        float[] ml = new float[2];
        float[] el = new float[2];
        sl[0] = startLocation[0] * 1.0f;
        sl[1] = startLocation[1] * 1.0f;
        el[0] = endLocation[0] * 1.0f;
        el[1] = endLocation[1] * 1.0f;
        ml[0] = (sl[0] + el[0]) / 2;  // + (new Random().nextInt(200) - 100);
        ml[1] = sl[1] / 2;

        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        getXY(sl, el, ml, xs, ys);

        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(button, "x", xs).setDuration(duration);
        xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
        xAnimator.start();

        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(button, "y", ys).setDuration(duration);
        yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
        yAnimator.start();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(button, "scaleX",
                1f,
                dotWidth / Util.getInstance().dp2px(buttonWidth)).setDuration(duration);
        scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideScaleEaseType));
        scaleXAnimator.start();

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(button, "scaleY",
                1f,
                dotWidth / Util.getInstance().dp2px(buttonWidth)).setDuration(duration);
        scaleYAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideScaleEaseType));
        scaleYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationLayout.removeAllViews();
                dot.setVisibility(VISIBLE);
                animationPlaying = false;
            }
        });
        scaleYAnimator.start();

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animationLayout, "backgroundColor",
                ContextCompat.getColor(mContext, R.color.darkness),
                ContextCompat.getColor(mContext, R.color.lightness)).setDuration(duration);
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationLayout.setVisibility(GONE);
            }
        });
        objectAnimator.start();
    }



    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setAnimatorListener(AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    public interface OnClickListener {
        void onClick();
    }

    public interface AnimatorListener {
        void toShow();
        void showing();
        void showed();
        void toHide();
        void hiding();
        void hided();
    }

}
