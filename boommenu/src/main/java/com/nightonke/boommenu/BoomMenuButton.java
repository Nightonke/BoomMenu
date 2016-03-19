package com.nightonke.boommenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nightonke.boommenu.Eases.EaseType;

/**
 * Created by Weiping on 2016/3/19.
 */
public class BoomMenuButton extends FrameLayout {

    private final int MIN_CIRCLE_BUTTON_NUMBER = 1;
    private final int MAX_CIRCLE_BUTTON_NUMBER = 9;
    private final int MIN_HAM_BUTTON_NUMBER = 1;
    private final int MAX_HAM_BUTTON_NUMBER = 4;

    private FrameLayout frameLayout;

    private int buttonNum = 0;
    private CircleButton[] circleButtons = new CircleButton[MAX_CIRCLE_BUTTON_NUMBER];
    private Dot[] dots = new Dot[MAX_CIRCLE_BUTTON_NUMBER];

    private int[][] startLocations = new int[MAX_CIRCLE_BUTTON_NUMBER][2];
    private int[][] endLocations = new int[MAX_CIRCLE_BUTTON_NUMBER][2];

    private int frames = 40;
    private int duration = 800;

    private boolean animationPlaying = false;

    private ButtonType buttonType;
    private BoomType boomType;

    private int dotWidth = 10;
    private int buttonWidth = 88;
    
    private EaseType showEaseType = EaseType.Linear;
    private EaseType hideEaseType = EaseType.Linear;

    private LinearLayout linearLayout;

    private OnClickListener onClickListener = null;

    private Context mContext;

    public BoomMenuButton(Context context) {
        this(context, null);
    }

    public BoomMenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.boom_menu_button, this, true);
        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
            }
        });
        linearLayout = (LinearLayout)findViewById(R.id.linear_layout);

    }

    public void init(
            Drawable[] drawables,
            String[] strings,
            int[] colors,
            ButtonType buttonType,
            BoomType boomType) {
        this.buttonType = buttonType;
        this.boomType = boomType;
        
        errorJudge(drawables, strings, colors, buttonType);

        // create buttons
        buttonNum = (drawables == null ? strings.length : drawables.length);
        if (buttonType.equals(ButtonType.CIRCLE)) {
            for (int i = 0; i < buttonNum; i++) {
                circleButtons[i] = new CircleButton(mContext);
                if (drawables != null) circleButtons[i].setDrawable(drawables[i]);
                if (strings != null) circleButtons[i].setText(strings[i]);
            }
        }

        // create dots
        linearLayout.removeAllViews();
        for (int i = 0; i < buttonNum; i++) {
            dots[i] = new Dot(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    10,
                    10
            );
            params.setMargins(10, 10, 10, 10);
            linearLayout.addView(dots[i], params);
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
        animationLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.darkness));
    }

    private void startShowAnimations() {
        if (animationLayout != null) animationLayout.removeAllViews();
        getEndLocations();
        for (int i = 0; i < buttonNum; i++) {
            dots[i].getLocationOnScreen(startLocations[i]);
            startLocations[i][0] -= ((int)Util.getInstance().dp2px(buttonWidth) - dots[i].getWidth()) / 2;
            startLocations[i][1] -= ((int)Util.getInstance().dp2px(buttonWidth) - dots[i].getHeight()) / 2;
            setShowAnimation(dots[i], circleButtons[i], startLocations[i], endLocations[i]);
        }
    }

    private void getEndLocations() {
        int width = Util.getInstance().getScreenWidth(mContext);
        int height = Util.getInstance().getScreenHeight(mContext);
        if (buttonNum == 2) {
            endLocations[0][0] = width * 1 / 3 - (int)Util.getInstance().dp2px(buttonWidth) / 2;
            endLocations[0][1] = height / 2 - (int)Util.getInstance().dp2px(buttonWidth) / 2;
            endLocations[1][0] = width * 2 / 3 - (int)Util.getInstance().dp2px(buttonWidth) / 2;
            endLocations[1][1] = height / 2 - (int)Util.getInstance().dp2px(buttonWidth) / 2;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick();
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
                (int)Util.getInstance().dp2px(buttonWidth),
                (int)Util.getInstance().dp2px(buttonWidth));
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
        xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showEaseType));
        xAnimator.start();

        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(view, "y", ys).setDuration(duration);
        yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showEaseType));
        yAnimator.start();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX",
                dotWidth / Util.getInstance().dp2px(buttonWidth),
                1f).setDuration(duration);
        scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showEaseType));
        scaleXAnimator.start();

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY",
                dotWidth / Util.getInstance().dp2px(buttonWidth),
                1f).setDuration(duration);
        scaleYAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showEaseType));
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
        xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideEaseType));
        xAnimator.start();

        ObjectAnimator yAnimator = ObjectAnimator.ofFloat(button, "y", ys).setDuration(duration);
        yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideEaseType));
        yAnimator.start();

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(button, "scaleX",
                1f,
                dotWidth / Util.getInstance().dp2px(buttonWidth)).setDuration(duration);
        scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideEaseType));
        scaleXAnimator.start();

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(button, "scaleY",
                1f,
                dotWidth / Util.getInstance().dp2px(buttonWidth)).setDuration(duration);
        scaleYAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideEaseType));
        scaleYAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.lightness));
                animationLayout.removeAllViews();
                animationLayout.setVisibility(GONE);
                dot.setVisibility(VISIBLE);
                animationPlaying = false;
            }
        });
        scaleYAnimator.start();
    }

}
