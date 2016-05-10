package com.nightonke.boommenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nightonke.boommenu.Eases.EaseType;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.ClickEffectType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.OrderType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Types.StateType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Weiping on 2016/3/19.
 */

@SuppressWarnings("JavaDoc")
public class BoomMenuButton extends FrameLayout
        implements
        CircleButton.OnCircleButtonClickListener,
        HamButton.OnHamButtonClickListener {

    // This param is used to optimizize the memory used.
    // When this param is set to true,
    // all the sub buttons will be created when needed
    // and will not be stored.
    public static final boolean MEMORY_OPTIMIZATION = true;

    public static final int MIN_CIRCLE_BUTTON_NUMBER = 1;
    public static final int MAX_CIRCLE_BUTTON_NUMBER = 9;
    public static final int MIN_HAM_BUTTON_NUMBER = 1;
    public static final int MAX_HAM_BUTTON_NUMBER = 4;

    private ViewGroup animationLayout = null;

    private ShadowLayout shadowLayout;
    private FrameLayout frameLayout;
    private View ripple;

    private int[][] startLocations = new int[MAX_CIRCLE_BUTTON_NUMBER][2];
    private int[][] endLocations = new int[MAX_CIRCLE_BUTTON_NUMBER][2];

    private boolean animationPlaying = false;
    private StateType state = StateType.CLOSED;

    // Params about buttons
    private int buttonNum = 0;
    private CircleButton[] circleButtons = new CircleButton[MAX_CIRCLE_BUTTON_NUMBER];
    private HamButton[] hamButtons = new HamButton[MAX_HAM_BUTTON_NUMBER];
    private Dot[] dots = new Dot[MAX_CIRCLE_BUTTON_NUMBER];
    private Bar[] bars = new Bar[MAX_HAM_BUTTON_NUMBER];
    private ShareLines shareLines = null;

    // Store the drawables of buttons
    private Drawable[] drawables = null;
    // Store the colors of buttons
    private int[][] colors = null;
    // Store the strings of buttons
    private String[] strings = null;

    // Is in action bar
    private boolean isInActionBar = false;
    // Is in list item
    private boolean isInList = false;
    // Boom button color
    private int boomButtonColor = 0;
    // Boom button pressed color
    private int boomButtonPressedColor = 0;

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
    // Default dot width
    private int dotWidth = 10;
    // Default dot width
    private int dotHeight = 10;
    // Default circle button width
    private int buttonWidth = (int)Util.getInstance().dp2px(88);
    // Default bar width
    private int barWidth = 50;
    // Default bar height
    private int barHeight = 8;
    // Default ham button width
    private int hamButtonWidth = 0;
    // Default ham button height
    private int hamButtonHeight = (int) Util.getInstance().dp2px(66 + 4);
    // Boom button radius
    private int boomButtonRadius = (int)Util.getInstance().dp2px(56);
    // Movement ease
    private EaseType showMoveEaseType = EaseType.EaseOutBack;
    private EaseType hideMoveEaseType = EaseType.EaseOutCirc;
    // Scale ease
    private EaseType showScaleEaseType = EaseType.EaseOutBack;
    private EaseType hideScaleEaseType = EaseType.EaseOutCirc;
    // Whether rotate
    private int rotateDegree = 720;
    // Rotate ease
    private EaseType showRotateEaseType = EaseType.EaseOutBack;
    private EaseType hideRotateEaseType = EaseType.Linear;
    // Auto dismiss
    private boolean autoDismiss = true;
    // Cancelable
    private boolean cancelable = true;
    // Dim value
    private DimType dimType = DimType.DIM_6;
    // Click effect
    private ClickEffectType clickEffectType = ClickEffectType.RIPPLE;
    // Sub buttons offsets of shadow
    private float subButtonsXOffsetOfShadow = 0;
    private float subButtonsYOffsetOfShadow = 0;

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

        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.BoomMenuButton, 0, 0);
        if (attr != null) {
            try {
                isInActionBar = attr.getBoolean(R.styleable.BoomMenuButton_boom_inActionBar, false);
                isInList = attr.getBoolean(R.styleable.BoomMenuButton_boom_inList, false);
                boomButtonColor = attr.getColor(R.styleable.BoomMenuButton_boom_button_color,
                        ContextCompat.getColor(mContext, R.color.default_boom_button_color));
                boomButtonPressedColor = attr.getColor(R.styleable.BoomMenuButton_boom_button_pressed_color,
                        ContextCompat.getColor(mContext, R.color.default_boom_button_color_pressed));
            } finally {
                attr.recycle();
            }
        }

        if (isInActionBar || isInList) {
            LayoutInflater.from(context).inflate(R.layout.boom_menu_button_in_action_bar, this, true);
            frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shoot();
                }
            });
        } else {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                LayoutInflater.from(context).inflate(R.layout.boom_menu_button, this, true);
            } else {
                LayoutInflater.from(context).inflate(R.layout.boom_menu_button_below_lollipop, this, true);
            }
            shadowLayout = (ShadowLayout)findViewById(R.id.shadow_layout);
            frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
            ripple = findViewById(R.id.ripple);

            setRipple(clickEffectType);
            setBoomButtonBackgroundColor(boomButtonPressedColor, boomButtonColor);
        }

        hamButtonWidth = (int) (Util.getInstance().getScreenWidth(getContext()) * 8 / 9
                + Util.getInstance().dp2px(4));

        setWillNotDraw(false);
    }

    /**
     * Init the boom menu button.
     * Notice that you should call this NOT in your onCreate method.
     * Because the width and height of boom menu button is 0.
     * Call this in:
     *
     * (This method needs to be overrided in activity)
     * public void onWindowFocusChanged(boolean hasFocus) {
     *     super.onWindowFocusChanged(hasFocus);
     *     init(...);
     * }
     *
     * @param drawables The drawables of images of sub buttons. Can not be null.
     * @param strings The texts of sub buttons, ok to be null.
     * @param colors The colors of sub buttons, including pressed-state and normal-state.
     * @param buttonType The button type.
     * @param boomType The boom type.
     * @param placeType The place type.
     * @param showMoveEaseType Ease type to move the sub buttons when showing.
     * @param showScaleEaseType Ease type to scale the sub buttons when showing.
     * @param showRotateEaseType Ease type to rotate the sub buttons when showing.
     * @param hideMoveEaseType Ease type to move the sub buttons when dismissing.
     * @param hideScaleEaseType Ease type to scale the sub buttons when dismissing.
     * @param hideRotateEaseType Ease type to rotate the sub buttons when dismissing.
     * @param rotateDegree Rotation degree.
     */
    public void init(
            Drawable[] drawables,
            String[] strings,
            int[][] colors,
            ButtonType buttonType,
            BoomType boomType,
            PlaceType placeType,
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
            buttonNum = drawables.length;

            if (isInList || MEMORY_OPTIMIZATION) {
                // store the drawables, THEN we will build the buttons when create them
                this.drawables = drawables;
                this.colors = colors;
                this.strings = strings;
            } else {
                for (int i = 0; i < buttonNum; i++) {
                    circleButtons[i] = new CircleButton(mContext);
                    circleButtons[i].setOnCircleButtonClickListener(this, i);
                    circleButtons[i].setDrawable(drawables[i]);
                    if (strings != null) circleButtons[i].setText(strings[i]);
                    circleButtons[i].setColor(colors[i][0], colors[i][1]);
                }
            }

            // create dots
            for (int i = 0; i < buttonNum; i++) {
                dots[i] = new Dot(mContext);
                dots[i].setColor(colors[i][1]);
            }

            // place dots according to the number of them and the place type
            placeDots();
        } else if (buttonType.equals(ButtonType.HAM)) {
            // hamburger button
            hamButtonWidth = Util.getInstance().getScreenWidth(getContext()) * 8 / 9;
            // create buttons
            buttonNum = drawables.length;

            if (isInList || MEMORY_OPTIMIZATION) {
                // store the drawables, THEN we will build the buttons when create them
                this.drawables = drawables;
                this.colors = colors;
                this.strings = strings;
            } else {
                for (int i = 0; i < buttonNum; i++) {
                    hamButtons[i] = new HamButton(mContext);
                    hamButtons[i].setOnHamButtonClickListener(this, i);
                    hamButtons[i].setDrawable(drawables[i]);
                    if (strings != null) hamButtons[i].setText(strings[i]);
                    hamButtons[i].setColor(colors[i][0], colors[i][1]);
                }
            }

            // create bars
            for (int i = 0; i < buttonNum; i++) {
                bars[i] = new Bar(mContext);
                bars[i].setColor(colors[i][1]);
            }

            // place bars according to the number of them and the place type
            placeBars();
        }
    }

    /**
     * Judge whether the input params to init boom menu button is incorrect.
     *
     * @param drawables The drawables of the sub buttons.
     * @param strings The texts of the sub buttons.
     * @param colors The colors(including the pressed-state and normal-state) of the sub buttons.
     * @param buttonType The button type of the sub buttons.
     */
    private void errorJudge(
            Drawable[] drawables,
            String[] strings,
            int[][] colors,
            ButtonType buttonType) {
        if (drawables == null) {
            throw new RuntimeException("The button's drawables are null!");
        }
        if (colors == null) {
            throw new RuntimeException("The button's colors are null!");
        }
        if (buttonType.equals(ButtonType.CIRCLE)) {
            if (       !(
                    MIN_CIRCLE_BUTTON_NUMBER <= drawables.length
                            && drawables.length <= MAX_CIRCLE_BUTTON_NUMBER)
                    || (strings != null
                    && !(
                    MIN_CIRCLE_BUTTON_NUMBER <= strings.length
                            && strings.length <= MAX_CIRCLE_BUTTON_NUMBER))
                    || !(
                    MIN_CIRCLE_BUTTON_NUMBER <= colors.length
                            && colors.length <= MAX_CIRCLE_BUTTON_NUMBER)) {
                throw new RuntimeException("The circle type button's length must be in [" +
                        MIN_CIRCLE_BUTTON_NUMBER + ", " +
                        MAX_CIRCLE_BUTTON_NUMBER + "]!");
            }
        } else if (buttonType.equals(ButtonType.HAM)) {
            if ((      !(
                    MIN_HAM_BUTTON_NUMBER <= drawables.length
                            && drawables.length <= MAX_HAM_BUTTON_NUMBER))
                    || (strings != null
                    && !(
                    MIN_HAM_BUTTON_NUMBER <= strings.length
                            && strings.length <= MAX_HAM_BUTTON_NUMBER))
                    || !(
                    MIN_HAM_BUTTON_NUMBER <= colors.length
                            && colors.length <= MAX_HAM_BUTTON_NUMBER)) {
                throw new RuntimeException("The ham type button's length must be in [" +
                        MIN_HAM_BUTTON_NUMBER + ", " +
                        MAX_HAM_BUTTON_NUMBER + "]!");
            }
        }
    }

    /**
     * Place all dots to the boom menu botton.
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private void placeDots() {
        frameLayout.removeAllViews();
        FrameLayout.LayoutParams[] ps = PlaceParamsFactory.getDotParams(
                placeType,
                frameLayout.getWidth(),
                frameLayout.getHeight(),
                dotWidth,
                dotHeight
        );

        if (placeType.SHARE_3_1.v <= placeType.v && placeType.v <= PlaceType.SHARE_9_2.v) {
            shareLines = new ShareLines(mContext);
            float[][] locations = new float[3][2];
            locations[0][0] = ps[0].leftMargin + dotWidth / 2;
            locations[0][1] = ps[0].topMargin + dotHeight / 2;
            locations[1][0] = ps[1].leftMargin + dotWidth / 2;
            locations[1][1] = ps[1].topMargin + dotHeight / 2;
            locations[2][0] = ps[2].leftMargin + dotWidth / 2;
            locations[2][1] = ps[2].topMargin + dotHeight / 2;
            shareLines.setLocations(locations);
            shareLines.setOffset(1);

            FrameLayout.LayoutParams p
                    = new FrameLayout.LayoutParams(frameLayout.getWidth(), frameLayout.getHeight());
            frameLayout.addView(shareLines, p);
        }

        for (int i = 0; i < buttonNum; i++) {
            frameLayout.addView(dots[i], ps[i]);
        }
    }

    /**
     * Place all bars to the boom menu botton.
     */
    private void placeBars() {
        frameLayout.removeAllViews();
        FrameLayout.LayoutParams[] ps = PlaceParamsFactory.getBarParams(
                placeType,
                frameLayout.getWidth(),
                frameLayout.getHeight(),
                barWidth,
                barHeight
        );
        for (int i = 0; i < ps.length; i++) frameLayout.addView(bars[i], ps[i]);
    }

    /**
     * When the boom menu button is clicked.
     */
    private void shoot() {
        // create the buttons
        if (isInList || MEMORY_OPTIMIZATION) {
            if (buttonType.equals(ButtonType.CIRCLE)) {
                for (int i = 0; i < buttonNum; i++) {
                    circleButtons[i] = new CircleButton(mContext);
                    circleButtons[i].setOnCircleButtonClickListener(this, i);
                    circleButtons[i].setDrawable(drawables[i]);
                    if (strings != null) circleButtons[i].setText(strings[i]);
                    circleButtons[i].setColor(colors[i][0], colors[i][1]);
                    circleButtons[i].setShadowDx(subButtonsXOffsetOfShadow);
                    circleButtons[i].setShadowDy(subButtonsYOffsetOfShadow);
                }
            } else if (buttonType.equals(ButtonType.HAM)) {
                for (int i = 0; i < buttonNum; i++) {
                    hamButtons[i] = new HamButton(mContext);
                    hamButtons[i].setOnHamButtonClickListener(this, i);
                    hamButtons[i].setDrawable(this.drawables[i]);
                    if (this.strings != null) hamButtons[i].setText(this.strings[i]);
                    hamButtons[i].setColor(this.colors[i][0], this.colors[i][1]);
                    hamButtons[i].setShadowDx(subButtonsXOffsetOfShadow);
                    hamButtons[i].setShadowDy(subButtonsYOffsetOfShadow);
                }
            }
            setRipple(clickEffectType);
        }

        // listener
        if (onClickListener != null) onClickListener.onClick();
        // wait for the before animations finished
        if (animationPlaying) return;
        animationPlaying = true;
        // dim the animation layout
        dimAnimationLayout();
        // start all animations
        startShowAnimations();
    }

    /**
     * Dim the background layout.
     */
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
                DimType.DIM_0.value,
                dimType.value)
                .setDuration(duration + delay * (buttonNum - 1));
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (animatorListener != null) animatorListener.toShow();
                state = StateType.OPENING;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (animatorListener != null) animatorListener.showed();
                state = StateType.OPEN;
            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animatorListener != null) animatorListener.showing(animation.getAnimatedFraction());
            }
        });
        objectAnimator.start();

        // share lines animation
        if (placeType.SHARE_3_1.v <= placeType.v && placeType.v <= PlaceType.SHARE_9_2.v) {
            ObjectAnimator shareLinesAnimator = ObjectAnimator.ofFloat(shareLines, "offset",
                    1f,
                    0f).setDuration(duration + delay * (buttonNum - 1));
            shareLinesAnimator.setStartDelay(0);
            shareLinesAnimator.start();
        }
    }

    /**
     * Start all animations about showing the boom menu button.
     */
    private void startShowAnimations() {
        if (animationLayout != null) animationLayout.removeAllViews();
        if (buttonType.equals(ButtonType.CIRCLE)) {
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
        } else if (buttonType.equals(ButtonType.HAM)) {
            getEndLocations();
            if (showOrderType.equals(OrderType.DEFAULT)) {
                for (int i = 0; i < buttonNum; i++) {
                    bars[i].getLocationOnScreen(startLocations[i]);
                    startLocations[i][0] -= (hamButtonWidth - bars[i].getWidth()) / 2;
                    startLocations[i][1] -= (hamButtonHeight - bars[i].getHeight()) / 2;
                    setShowAnimation(bars[i], hamButtons[i], startLocations[i], endLocations[i], i);
                }
            } else if (showOrderType.equals(OrderType.REVERSE)) {
                for (int i = 0; i < buttonNum; i++) {
                    bars[i].getLocationOnScreen(startLocations[i]);
                    startLocations[i][0] -= (hamButtonWidth - bars[i].getWidth()) / 2;
                    startLocations[i][1] -= (hamButtonHeight - bars[i].getHeight()) / 2;
                    setShowAnimation(bars[i], hamButtons[i], startLocations[i], endLocations[i], buttonNum - i - 1);
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

                        bars[count].getLocationOnScreen(startLocations[count]);
                        startLocations[count][0] -= (hamButtonWidth - bars[count].getWidth()) / 2;
                        startLocations[count][1] -= (hamButtonHeight - bars[count].getHeight()) / 2;
                        setShowAnimation(bars[count], hamButtons[count], startLocations[count], endLocations[count], i);

                        count++;
                        if (count == buttonNum) break;
                    }
                }
            }
        }

    }

    /**
     * Get end location of all sub buttons.
     */
    private void getEndLocations() {
        int width = Util.getInstance().getScreenWidth(mContext);
        int height = Util.getInstance().getScreenHeight(mContext);
        if (buttonType.equals(ButtonType.CIRCLE)) {
            endLocations = EndLocationsFactory.getEndLocations(
                    placeType, width, height, buttonWidth, buttonWidth);
        } else if (buttonType.equals(ButtonType.HAM)) {
            endLocations = EndLocationsFactory.getEndLocations(
                    placeType, width, height, hamButtonWidth, hamButtonHeight);
        }
    }

    /**
     * Create the background layout as a "canvas" of all animations and sub buttons.
     * Notice that we don't need to call this every time, we can set the visibility
     * of the background layout to hide it.
     *
     * @return The background layout.
     */
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

    /**
     * Put the sub button to the background layout.
     *
     * @param view The sub button.
     * @param location Location in background layout.
     * @return The sub button after set.
     */
    private View setViewLocationInAnimationLayout(final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = null;
        if (buttonType.equals(ButtonType.CIRCLE)) {
            lp = new LinearLayout.LayoutParams(
                    buttonWidth,
                    buttonWidth);
        } else if (buttonType.equals(ButtonType.HAM)) {
            lp = new LinearLayout.LayoutParams(
                    hamButtonWidth,
                    hamButtonHeight);
        }
        lp.leftMargin = x;
        lp.topMargin = y;
        animationLayout.addView(view, lp);
        return view;
    }

    /**
     * Set show animation of each sub button.
     *
     * @param dot The dot corresponding to the sub button.
     * @param button The sub button.
     * @param startLocation Start location of the animation.
     * @param endLocation End location of the animation.
     * @param index Index of the sub button in the array.
     */
    public void setShowAnimation(
            final View dot,
            final View button,
            int[] startLocation,
            int[] endLocation,
            final int index) {
        button.bringToFront();

        final View view = setViewLocationInAnimationLayout(button, startLocation);

        float[] sl = new float[2];
        float[] el = new float[2];
        sl[0] = startLocation[0] * 1.0f;
        sl[1] = startLocation[1] * 1.0f;
        el[0] = endLocation[0] * 1.0f;
        el[1] = endLocation[1] * 1.0f;

        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        getShowXY(sl, el, xs, ys);

        if (view != null) {
            ObjectAnimator xAnimator = ObjectAnimator.ofFloat(view, "x", xs).setDuration(duration);
            xAnimator.setStartDelay(delay * index);
            xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
            xAnimator.start();

            ObjectAnimator yAnimator = ObjectAnimator.ofFloat(view, "y", ys).setDuration(duration);
            yAnimator.setStartDelay(delay * index);
            yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
            yAnimator.start();
        }

        // scale animation
        float scaleW = 0;
        float scaleH = 0;
        if (buttonType.equals(ButtonType.CIRCLE)) {
            scaleW = dotWidth * 1.0f / buttonWidth;
            scaleH = dotWidth * 1.0f / buttonWidth;
        } else if (buttonType.equals(ButtonType.HAM)) {
            scaleW = barWidth * 1.0f / hamButtonWidth;
            scaleH = barHeight * 1.0f / hamButtonHeight;
        }

        if (view != null) {
            view.setScaleX(scaleW);
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX",
                    scaleW,
                    1f).setDuration(duration);
            scaleXAnimator.setStartDelay(delay * index);
            scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showScaleEaseType));
            scaleXAnimator.start();

            view.setScaleY(scaleH);
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY",
                    scaleH,
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
        }

        // alpha animation
        View view1 = null;
        View view2 = null;
        if (button != null && button instanceof CircleButton) {
            view1 = ((CircleButton) button).getImageView();
            view2 = ((CircleButton) button).getTextView();
        } else if (button != null && button instanceof HamButton) {
            view1 = ((HamButton) button).getImageView();
            view2 = ((HamButton) button).getTextView();
        }

        if (view1 != null) {
            ObjectAnimator alphaAnimator1 = ObjectAnimator.ofFloat(view1, "alpha",
                    0f,
                    1f).setDuration(duration);
            alphaAnimator1.setStartDelay(delay * index);
            alphaAnimator1.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
            alphaAnimator1.start();
        }

        if (view2 != null) {
            ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(view2, "alpha",
                    0f,
                    1f).setDuration(duration);
            alphaAnimator2.setStartDelay(delay * index);
            alphaAnimator2.setInterpolator(InterpolatorFactory.getInterpolator(showMoveEaseType));
            alphaAnimator2.start();
        }

        // rotation animation
        if (view != null && view instanceof CircleButton) {
            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(
                    ((CircleButton) view).getFrameLayout(), "rotation",
                    0,
                    rotateDegree).setDuration(duration);
            rotateAnimator.setStartDelay(delay * index);
            rotateAnimator.setInterpolator(InterpolatorFactory.getInterpolator(showRotateEaseType));
            rotateAnimator.start();
        }
    }

    /**
     * Get the function of the road of the animation of showing.
     * Then calculate each points to be ready for the animation.
     *
     * @param startPoint Start point of the animation.
     * @param endPoint End point of the animation.
     * @param xs The values on the x axis.
     * @param ys The values on the y axis.
     */
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
            float y3 = Math.min(startPoint[1], endPoint[1]) / 2;
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
            float y3 = (Util.getInstance().getScreenHeight(mContext)
                    - Math.max(startPoint[1], endPoint[1])) / 2
                    + Math.max(startPoint[1], endPoint[1]);
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

    /**
     * Get the function of the road of the animation of dismissing.
     * Then calculate each points to be ready for the animation.
     *
     * @param startPoint Start point of the animation.
     * @param endPoint End point of the animation.
     * @param xs The values on the x axis.
     * @param ys The values on the y axis.
     */
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
            float y3 = Math.min(startPoint[1], endPoint[1]) / 2;
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
            float y3 = (Util.getInstance().getScreenHeight(mContext)
                    - Math.max(startPoint[1], endPoint[1])) / 2
                    + Math.max(startPoint[1], endPoint[1]);
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

    /**
     * Start all animations about dismissing.
     */
    private void startHideAnimations() {
        animationPlaying = true;
        lightAnimationLayout();
        if (buttonType.equals(ButtonType.CIRCLE)) {
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
        } else if (buttonType.equals(ButtonType.HAM)) {
            if (hideOrderType.equals(OrderType.DEFAULT)) {
                for (int i = 0; i < buttonNum; i++) {
                    setHideAnimation(bars[i], hamButtons[i], endLocations[i], startLocations[i], i);
                }
            } else if (hideOrderType.equals(OrderType.REVERSE)) {
                for (int i = 0; i < buttonNum; i++) {
                    setHideAnimation(bars[i], hamButtons[i], endLocations[i], startLocations[i], buttonNum - i - 1);
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
                                bars[count],
                                hamButtons[count],
                                endLocations[count],
                                startLocations[count],
                                i);

                        count++;
                        if (count == buttonNum) break;
                    }
                }
            }
        }
    }

    /**
     * Set hide animation of each sub button.
     *
     * @param dot The dot corresponding to the sub button.
     * @param button The sub button.
     * @param startLocation Start location of the animation.
     * @param endLocation End location of the animation.
     * @param index Index of the sub button in the array.
     */
    public void setHideAnimation(
            final View dot,
            final View button,
            int[] startLocation,
            int[] endLocation,
            final int index) {

        // position animation
        float[] sl = new float[2];
        float[] el = new float[2];
        sl[0] = startLocation[0] * 1.0f;
        sl[1] = startLocation[1] * 1.0f;
        el[0] = endLocation[0] * 1.0f;
        el[1] = endLocation[1] * 1.0f;

        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        getHideXY(sl, el, xs, ys);

        if (button != null) {
            ObjectAnimator xAnimator = ObjectAnimator.ofFloat(button, "x", xs).setDuration(duration);
            xAnimator.setStartDelay(index * delay);
            xAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
            xAnimator.start();

            ObjectAnimator yAnimator = ObjectAnimator.ofFloat(button, "y", ys).setDuration(duration);
            yAnimator.setStartDelay(index * delay);
            yAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
            yAnimator.start();
        }

        // scale animation
        float scaleW = 0;
        float scaleH = 0;
        if (buttonType.equals(ButtonType.CIRCLE)) {
            scaleW = dotWidth * 1.0f / buttonWidth;
            scaleH = dotWidth * 1.0f / buttonWidth;
        } else if (buttonType.equals(ButtonType.HAM)) {
            scaleW = barWidth * 1.0f / hamButtonWidth;
            scaleH = barHeight * 1.0f / hamButtonHeight;
        }

        if (button != null) {
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(button, "scaleX",
                    1f,
                    scaleW).setDuration(duration);
            scaleXAnimator.setStartDelay(index * delay);
            scaleXAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideScaleEaseType));
            scaleXAnimator.start();

            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(button, "scaleY",
                    1f,
                    scaleH).setDuration(duration);
            scaleYAnimator.setStartDelay(index * delay);
            scaleYAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideScaleEaseType));
            scaleYAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dot.setVisibility(VISIBLE);
                    if (isInList || MEMORY_OPTIMIZATION) {
                        if (buttonType.equals(ButtonType.CIRCLE)) circleButtons[index] = null;
                        else if (buttonType.equals(ButtonType.HAM)) hamButtons[index] = null;
                    }
                }
            });
            scaleYAnimator.start();
        }

        // alpha animation
        View view1 = null;
        View view2 = null;
        if (button != null && button instanceof CircleButton) {
            view1 = ((CircleButton) button).getImageView();
            view2 = ((CircleButton) button).getTextView();
        } else if (button != null && button instanceof HamButton) {
            view1 = ((HamButton) button).getImageView();
            view2 = ((HamButton) button).getTextView();
        }

        if (view1 != null) {
            ObjectAnimator alphaAnimator1 = ObjectAnimator.ofFloat(view1, "alpha",
                    1f,
                    0f).setDuration(duration);
            alphaAnimator1.setStartDelay(delay * index);
            alphaAnimator1.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
            alphaAnimator1.start();
        }

        if (view2 != null) {
            ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(view2, "alpha",
                    1f,
                    0f).setDuration(duration);
            alphaAnimator2.setStartDelay(delay * index);
            alphaAnimator2.setInterpolator(InterpolatorFactory.getInterpolator(hideMoveEaseType));
            alphaAnimator2.start();
        }

        // rotation animation
        if (button != null && button instanceof CircleButton) {
            ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(
                    ((CircleButton) button).getFrameLayout(), "rotation",
                    0,
                    -rotateDegree).setDuration(duration);
            rotateAnimator.setStartDelay(index * delay);
            rotateAnimator.setInterpolator(InterpolatorFactory.getInterpolator(hideRotateEaseType));
            rotateAnimator.start();
        }

    }

    /**
     * Light the background, used when the boom menu button is to dismiss.
     */
    public void lightAnimationLayout() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animationLayout, "backgroundColor",
                dimType.value,
                DimType.DIM_0.value)
                .setDuration(duration + delay * (buttonNum - 1));
        objectAnimator.setEvaluator(new ArgbEvaluator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (animatorListener != null) animatorListener.toHide();
                state = StateType.CLOSING;
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationLayout.removeAllViews();
                animationLayout.setVisibility(GONE);
                animationPlaying = false;
                if (animatorListener != null) animatorListener.hided();
                state = StateType.CLOSED;
            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animatorListener != null) animatorListener.hiding(animation.getAnimatedFraction());
            }
        });
        objectAnimator.start();

        // share lines animation
        if (placeType.SHARE_3_1.v <= placeType.v && placeType.v <= PlaceType.SHARE_9_2.v) {
            ObjectAnimator shareLinesAnimator = ObjectAnimator.ofFloat(shareLines, "offset",
                    0f,
                    1f).setDuration(duration + delay * (buttonNum - 1));
            shareLinesAnimator.setStartDelay(0);
            shareLinesAnimator.start();
        }
    }

    /**
     * Set auto dismiss. If the boom menu button is auto dismiss, user can click one
     * of the sub buttons to dismiss the boom menu botton.
     *
     * @param autoDismiss
     */
    public void setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
    }

    /**
     * Set cancelable. If the boom menu button is cancelable, user can click
     * the background to dismiss it.
     *
     * @param cancelable
     */
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    /**
     * Set frames for animaitons.
     * @param frames
     */
    public void setFrames(int frames) {
        this.frames = frames;
    }

    /**
     * Set animation duration.
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Set start delay.
     *
     * @param delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Set rotate degrees.
     *
     * @param rotateDegree
     */
    public void setRotateDegree(int rotateDegree) {
        this.rotateDegree = rotateDegree;
    }

    /**
     * Set show order type.
     *
     * @param showOrderType
     */
    public void setShowOrderType(OrderType showOrderType) {
        this.showOrderType = showOrderType;
    }

    /**
     * Set hide order type.
     *
     * @param hideOrderType
     */
    public void setHideOrderType(OrderType hideOrderType) {
        this.hideOrderType = hideOrderType;
    }

    /**
     * Set OnClickListener.
     *
     * @param onClickListener
     */
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * Set AnimatorListener.
     *
     * @param animatorListener
     */
    public void setAnimatorListener(AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    /**
     * @return The imagebuttons of sub buttons.
     */
    public ImageButton[] getImageButtons() {
        ImageButton[] imageButtons = new ImageButton[buttonNum];
        for (int i = 0; i < buttonNum; i++) {
            if (circleButtons[i] != null) imageButtons[i] = circleButtons[i].getImageButton();
        }
        return imageButtons;
    }

    /**
     * @return The imageviews of ham buttons.
     */
    public ImageView[] getImageViews() {
        ImageView[] imageViews = new ImageView[buttonNum];
        if (buttonType.equals(ButtonType.CIRCLE)) {
            for (int i = 0; i < buttonNum; i++)
                if (circleButtons[i] != null) imageViews[i] = circleButtons[i].getImageView();
        } else if (buttonType.equals(ButtonType.HAM)) {
            for (int i = 0; i < buttonNum; i++)
                if (hamButtons[i] != null) imageViews[i] = hamButtons[i].getImageView();
        }
        return imageViews;
    }

    /**
     * @return The textviews of sub buttons.
     */
    public TextView[] getTextViews() {
        TextView[] textViews = new TextView[buttonNum];
        if (buttonType.equals(ButtonType.CIRCLE)) {
            for (int i = 0; i < buttonNum; i++)
                if (circleButtons != null) textViews[i] = circleButtons[i].getTextView();
        } else if (buttonType.equals(ButtonType.HAM)) {
            for (int i = 0; i < buttonNum; i++)
                if (hamButtons[i] != null) textViews[i] = hamButtons[i].getTextView();
        }
        return textViews;
    }

    /**
     * Set OnSubButtonClickListener.
     *
     * @param onSubButtonClickListener
     */
    public void setOnSubButtonClickListener(OnSubButtonClickListener onSubButtonClickListener) {
        this.onSubButtonClickListener = onSubButtonClickListener;
    }

    /**
     * Set the color of pressed-state or normal-state of boom menu button.
     *
     * @param pressedColor
     * @param normalColor
     */
    public void setBoomButtonBackgroundColor(int pressedColor, int normalColor) {
        Util.getInstance().setCircleButtonStateListDrawable(
                frameLayout, boomButtonRadius, pressedColor, normalColor);
    }

    /**
     * Set the offset of the shadow layouts of sub buttons.
     * If xOffset is 0 and yOffset is 0, then the shadow layout is at the center.
     *
     * @param xOffset In pixels.
     * @param yOffset In pixels.
     */
    public void setSubButtonShadowOffset(float xOffset, float yOffset) {
        for (int i = 0; i < buttonNum; i++) {
            if (buttonType.equals(ButtonType.CIRCLE)) {
                if (circleButtons[i] != null) {
                    circleButtons[i].setShadowDx(xOffset);
                    circleButtons[i].setShadowDy(yOffset);
                } else {
                    subButtonsXOffsetOfShadow = xOffset;
                    subButtonsYOffsetOfShadow = xOffset;
                }
            } else if (buttonType.equals(ButtonType.HAM)) {
                if (hamButtons[i] != null) {
                    hamButtons[i].setShadowDx(xOffset);
                    hamButtons[i].setShadowDy(yOffset);
                } else {
                    subButtonsXOffsetOfShadow = xOffset;
                    subButtonsYOffsetOfShadow = xOffset;
                }
            }
        }
    }

    /**
     * Set the offset of the shadow layouts of boom menu button.
     * If xOffset is 0 and yOffset is 0, then the shadow layout is at the center.
     *
     * @param xOffset In pixels.
     * @param yOffset In pixels.
     */
    public void setBoomButtonShadowOffset(float xOffset, float yOffset) {
        if (shadowLayout != null) {
            shadowLayout.setmDx(xOffset);
            shadowLayout.setmDy(yOffset);
        }
    }

    /**
     * Set the dim type.
     * Dim_0 for no dim.
     * Max is Dim_9.
     *
     * @param dimType
     */
    public void setDimType(DimType dimType) {
        this.dimType = dimType;
    }

    /**
     * Set the click effect.
     *
     * @param clickEffectType
     */
    public void setClickEffectType(ClickEffectType clickEffectType) {
        setRipple(clickEffectType);
        if (buttonType.equals(ButtonType.CIRCLE)) {
            if (circleButtons != null) {
                for (int i = 0; i < buttonNum; i++) {
                    if (circleButtons[i] != null) circleButtons[i].setRipple(clickEffectType);
                }
            }
        } else if (buttonType.equals(ButtonType.HAM)) {
            if (hamButtons != null) {
                for (int i = 0; i < buttonNum; i++) {
                    if (hamButtons[i] != null) hamButtons[i].setRipple(clickEffectType);
                }
            }
        }
    }

    /**
     * Set the click effect of the boom button.
     *
     * @param clickEffectType
     */
    private void setRipple(ClickEffectType clickEffectType) {
        this.clickEffectType = clickEffectType;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && clickEffectType.equals(ClickEffectType.RIPPLE)
                && ripple != null) {
            ripple.setVisibility(VISIBLE);
            ripple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shoot();
                }
            });
        } else {
            if (ripple != null) ripple.setVisibility(GONE);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shoot();
                }
            });
        }
    }

    /**
     * Set the color of all textviews in the sub buttons.
     *
     * @param color
     */
    public void setTextViewColor(int color) {
        if (buttonType.equals(ButtonType.CIRCLE)) {
            if (circleButtons != null) {
                for (int i = 0; i < buttonNum; i++) {
                    if (circleButtons[i] != null) circleButtons[i].getTextView().setTextColor(color);
                }
            }
        } else if (buttonType.equals(ButtonType.HAM)) {
            if (hamButtons != null) {
                for (int i = 0; i < buttonNum; i++) {
                    if (hamButtons[i] != null) hamButtons[i].getTextView().setTextColor(color);
                }
            }
        }
    }

    /**
     * Set the color of all textviews in the sub buttons corresonding to the array.
     *
     * @param colors
     */
    public void setTextViewColor(int[] colors) {
        int length = Math.min(buttonNum, colors.length);
        if (buttonType.equals(ButtonType.CIRCLE)) {
            if (circleButtons != null) {
                for (int i = 0; i < length; i++) {
                    if (circleButtons[i] != null) circleButtons[i].getTextView().setTextColor(colors[i]);
                }
            }
        } else if (buttonType.equals(ButtonType.HAM)) {
            if (hamButtons != null) {
                for (int i = 0; i < length; i++) {
                    if (hamButtons[i] != null) hamButtons[i].getTextView().setTextColor(colors[i]);
                }
            }
        }
    }

    /**
     * Set the boom type of boom button.
     *
     * @param boomType
     */
    public void setBoomType(BoomType boomType) {
        this.boomType = boomType;
    }

    /**
     *
     * @return
     */
    public boolean isClosed() {
        return state.equals(StateType.CLOSED);
    }

    /**
     *
     * @return
     */
    public boolean isClosing() {
        return state.equals(StateType.CLOSING);
    }

    /**
     *
     * @return
     */
    public boolean isOpen() {
        return state.equals(StateType.OPEN);
    }

    /**
     *
     * @return
     */
    public boolean isOpening() {
        return state.equals(StateType.OPENING);
    }

    public void setShowMoveEaseType(EaseType showMoveEaseType) {
        this.showMoveEaseType = showMoveEaseType;
    }

    public void setShowScaleEaseType(EaseType showScaleEaseType) {
        this.showScaleEaseType = showScaleEaseType;
    }

    public void setShowRotateEaseType(EaseType showRotateEaseType) {
        this.showRotateEaseType = showRotateEaseType;
    }

    public void setHideMoveEaseType(EaseType hideMoveEaseType) {
        this.hideMoveEaseType = hideMoveEaseType;
    }

    public void setHideScaleEaseType(EaseType hideScaleEaseType) {
        this.hideScaleEaseType = hideScaleEaseType;
    }

    public void setHideRotateEaseType(EaseType hideRotateEaseType) {
        this.hideRotateEaseType = hideRotateEaseType;
    }

    /**
     * Get the click event from CircleButton or HamButton
     *
     * @param index
     */
    @Override
    public void onClick(int index) {
        if (!state.equals(StateType.OPEN)) return;
        if (onSubButtonClickListener != null) onSubButtonClickListener.onClick(index);
        if (autoDismiss && !animationPlaying) startHideAnimations();
    }

    /**
     * This interface tells when the boom menu button is clicked.
     */
    public interface OnClickListener {
        void onClick();
    }

    /**
     * Animation listener.
     */
    public interface AnimatorListener {
        void toShow();
        void showing(float fraction);
        void showed();
        void toHide();
        void hiding(float fraction);
        void hided();
    }

    /**
     * This interface return which button is clicked.
     */
    public interface OnSubButtonClickListener {
        void onClick(int buttonIndex);
    }

    /**
     * If the boom menu button is open, dismiss it.
     *
     * @return True if dismiss, false if can not dismiss.
     */
    public boolean dismiss() {
        if (state == StateType.OPEN) {
            startHideAnimations();
            return true;
        }
        return false;
    }

    /**
     * If the boom menu button is closed, open it.
     *
     * @return True if open, false if can not open.
     */
    public boolean boom() {
        if (state == StateType.CLOSED) {
            shoot();
            return true;
        }
        return false;
    }

    /**
     * Set the width of the share icon lines
     * @param width
     */
    public void setShareLineWidth(float width) {
        if (shareLines != null) shareLines.setLineWidth(width);
    }

    /**
     * Set the color of the share icon line 1
     * @param color
     */
    public void setShareLine1Color(int color) {
        if (shareLines != null) shareLines.setLine1Color(color);
    }

    /**
     * Set the color of the share icon line 2
     * @param color
     */
    public void setShareLine2Color(int color) {
        if (shareLines != null) shareLines.setLine2Color(color);
    }

    /**
     * Builder
     * Fuck this... That's so long...
     */
    public static class Builder {

        // required parameters
        private ArrayList<Drawable> drawables = null;
        private ArrayList<int[]> colors = null;
        private ArrayList<String> strings = null;

        private int frames = 80;

        private int duration = 800;

        private int delay = 100;

        private OrderType showOrderType = OrderType.DEFAULT;

        private OrderType hideOrderType = OrderType.DEFAULT;

        private ButtonType buttonType = ButtonType.CIRCLE;

        private BoomType boomType = BoomType.HORIZONTAL_THROW;

        private PlaceType placeType = null;

        private EaseType showMoveEaseType = EaseType.EaseOutBack;
        private EaseType hideMoveEaseType = EaseType.EaseOutCirc;

        private EaseType showScaleEaseType = EaseType.EaseOutBack;
        private EaseType hideScaleEaseType = EaseType.EaseOutCirc;

        private int rotateDegree = 720;
        private EaseType showRotateEaseType = EaseType.EaseOutBack;
        private EaseType hideRotateEaseType = EaseType.Linear;

        private boolean autoDismiss = true;

        private boolean cancelable = true;

        private DimType dimType = DimType.DIM_6;

        private ClickEffectType clickEffectType = ClickEffectType.RIPPLE;

        private float boomButtonXOffsetOfShadow = 0;
        private float boomButtonYOffsetOfShadow = 0;

        private float subButtonsXOffsetOfShadow = 0;
        private float subButtonsYOffsetOfShadow = 0;
        private int subButtonTextColor = Color.WHITE;

        private OnClickListener onClickListener = null;
        private AnimatorListener animatorListener = null;
        private OnSubButtonClickListener onSubButtonClickListener = null;

        private float shareLineWidth = 3f;
        private int shareLine1Color = Color.WHITE;
        private int shareLine2Color = Color.WHITE;

        public Builder subButtons(ArrayList<Drawable> drawables, ArrayList<int[]> colors, ArrayList<String> strings) {
            this.drawables = drawables;
            this.colors = colors;
            this.strings = strings;
            return this;
        }

        public Builder subButtons(Drawable[] drawables, int[][] colors, String[] strings) {
            this.drawables = new ArrayList<>(Arrays.asList(drawables));
            this.colors = new ArrayList<>(Arrays.asList(colors));
            this.strings = new ArrayList<>(Arrays.asList(strings));
            return this;
        }

        public Builder frames(int frames) {
            this.frames = frames;
            return this;
        }

        public Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public Builder delay(int delay) {
            this.delay = delay;
            return this;
        }

        public Builder showOrder(OrderType showOrderType) {
            this.showOrderType = showOrderType;
            return this;
        }
        
        public Builder hideOrder(OrderType hideOrderType) {
            this.hideOrderType = hideOrderType;
            return this;
        }
        
        public Builder button(ButtonType buttonType) {
            this.buttonType = buttonType;
            return this;
        }

        public Builder boom(BoomType boomType) {
            this.boomType = boomType;
            return this;
        }
        
        public Builder place(PlaceType placeType) {
            this.placeType = placeType;
            return this;
        }
        
        public Builder showMoveEase(EaseType showMoveEaseType) {
            this.showMoveEaseType = showMoveEaseType;
            return this;
        }
        
        public Builder hideMoveEase(EaseType hideMoveEaseType) {
            this.hideMoveEaseType = hideMoveEaseType;
            return this;
        }
        
        public Builder showScaleEase(EaseType showScaleEaseType) {
            this.showScaleEaseType = showScaleEaseType;
            return this;
        }
        
        public Builder hideScaleType(EaseType hideScaleEaseType) {
            this.hideScaleEaseType = hideScaleEaseType;
            return this;
        }

        public Builder rotateDegree(int rotateDegree) {
            this.rotateDegree = rotateDegree;
            return this;
        }

        public Builder showRotateEase(EaseType showRotateEaseType) {
            this.showRotateEaseType = showRotateEaseType;
            return this;
        }

        public Builder hideRotateType(EaseType hideRotateEaseType) {
            this.hideRotateEaseType = hideRotateEaseType;
            return this;
        }

        public Builder autoDismiss(boolean autoDismiss) {
            this.autoDismiss = autoDismiss;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder dim(DimType dimType) {
            this.dimType = dimType;
            return this;
        }

        public Builder clickEffect(ClickEffectType clickEffectType) {
            this.clickEffectType = clickEffectType;
            return this;
        }

        public Builder boomButtonShadow(float boomButtonXOffsetOfShadow, float boomButtonYOffsetOfShadow) {
            this.boomButtonXOffsetOfShadow = boomButtonXOffsetOfShadow;
            this.boomButtonYOffsetOfShadow = boomButtonYOffsetOfShadow;
            return this;
        }

        public Builder subButtonsShadow(float subButtonsXOffsetOfShadow, float subButtonsYOffsetOfShadow) {
            this.subButtonsXOffsetOfShadow = subButtonsXOffsetOfShadow;
            this.subButtonsYOffsetOfShadow = subButtonsYOffsetOfShadow;
            return this;
        }

        public Builder subButtonTextColor(int subButtonTextColor) {
            this.subButtonTextColor = subButtonTextColor;
            return this;
        }

        public Builder onBoomButtonBlick(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder animator(AnimatorListener animatorListener) {
            this.animatorListener = animatorListener;
            return this;
        }

        public Builder onSubButtonClick(OnSubButtonClickListener onSubButtonClickListener) {
            this.onSubButtonClickListener = onSubButtonClickListener;
            return this;
        }

        public Builder shareStyle(float shareLineWidth, int shareLine1Color, int shareLine2Color) {
            this.shareLineWidth = shareLineWidth;
            this.shareLine1Color = shareLine1Color;
            this.shareLine2Color = shareLine2Color;
            return this;
        }

        /**
         * Add a sub button with 3 params.
         * @param drawable
         * @param twoColors
         * @param string
         * @return
         */
        public Builder addSubButton(Drawable drawable, int[] twoColors, String string) {
            if (drawables == null) drawables = new ArrayList<>();
            drawables.add(drawable);
            if (colors == null) colors = new ArrayList<>();
            colors.add(twoColors);
            if (strings == null) strings = new ArrayList<>();
            strings.add(string);
            return this;
        }

        /**
         * Add a sub button with 2 params.
         * @param drawable
         * @param twoColors
         * @return
         */
        public Builder addSubButton(Drawable drawable, int[] twoColors) {
            if (drawables == null) drawables = new ArrayList<>();
            drawables.add(drawable);
            if (colors == null) colors = new ArrayList<>();
            colors.add(twoColors);
            return this;
        }

        /**
         * Add a sub button with 4 params.
         * @param context
         * @param drawable
         * @param twoColors
         * @param string
         * @return
         */
        public Builder addSubButton(Context context, int drawable, int[] twoColors, String string) {
            if (drawables == null) drawables = new ArrayList<>();
            drawables.add(ContextCompat.getDrawable(context, drawable));
            if (colors == null) colors = new ArrayList<>();
            colors.add(twoColors);
            if (strings == null) strings = new ArrayList<>();
            strings.add(string);
            return this;
        }

        /**
         * Add a sub button with 3 params.
         * @param context
         * @param drawable
         * @param twoColors
         * @return
         */
        public Builder addSubButton(Context context, int drawable, int[] twoColors) {
            if (drawables == null) drawables = new ArrayList<>();
            drawables.add(ContextCompat.getDrawable(context, drawable));
            if (colors == null) colors = new ArrayList<>();
            colors.add(twoColors);
            return this;
        }

        public BoomMenuButton init(BoomMenuButton boomMenuButton) {
            if (boomMenuButton == null) throw new RuntimeException("BMB is null!");
            Drawable[] drawablesInArray = new Drawable[drawables.size()];
            for (int i = 0; i < drawables.size(); i++) drawablesInArray[i] = drawables.get(i);
            String[] stringsInArray = new String[strings.size()];
            for (int i = 0; i < strings.size(); i++) stringsInArray[i] = strings.get(i);
            int[][] colorsInArray = new int[colors.size()][2];
            for (int i = 0; i < colors.size(); i++) colorsInArray[i] = colors.get(i);
            boomMenuButton.init(
                    drawablesInArray,
                    stringsInArray,
                    colorsInArray,
                    buttonType,
                    boomType,
                    placeType,
                    showMoveEaseType,
                    showScaleEaseType,
                    showRotateEaseType,
                    hideMoveEaseType,
                    hideScaleEaseType,
                    hideRotateEaseType,
                    rotateDegree);
            boomMenuButton.setFrames(frames);
            boomMenuButton.setDuration(duration);
            boomMenuButton.setDelay(delay);
            boomMenuButton.setShowOrderType(showOrderType);
            boomMenuButton.setHideOrderType(hideOrderType);
            boomMenuButton.setAutoDismiss(autoDismiss);
            boomMenuButton.setCancelable(cancelable);
            boomMenuButton.setDimType(dimType);
            boomMenuButton.setClickEffectType(clickEffectType);
            boomMenuButton.setBoomButtonShadowOffset(boomButtonXOffsetOfShadow, boomButtonYOffsetOfShadow);
            boomMenuButton.setSubButtonShadowOffset(subButtonsXOffsetOfShadow, subButtonsYOffsetOfShadow);
            boomMenuButton.setTextViewColor(subButtonTextColor);
            boomMenuButton.setOnClickListener(onClickListener);
            boomMenuButton.setAnimatorListener(animatorListener);
            boomMenuButton.setOnSubButtonClickListener(onSubButtonClickListener);
            boomMenuButton.setShareLineWidth(shareLineWidth);
            boomMenuButton.setShareLine1Color(shareLine1Color);
            boomMenuButton.setShareLine2Color(shareLine2Color);
            return boomMenuButton;
        }
    }
}
