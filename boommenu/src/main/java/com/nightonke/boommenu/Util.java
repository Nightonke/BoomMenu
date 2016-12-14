package com.nightonke.boommenu;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.StateSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Weiping Huang at 01:12 on 2016/3/19
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 *
 */

public class Util {

    static Activity scanForActivity(Context context) {
        if (context == null) {
            Log.w(BoomMenuButton.TAG, "scanForActivity: context is null!");
            return null;
        } else if (context instanceof Activity)
            return (Activity)context;
        else if (context instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)context).getBaseContext());
        Log.w(BoomMenuButton.TAG, "scanForActivity: context is null!");
        return null;
    }

    static void setVisibility(int visibility, View... views) {
        for (View view : views) if (view != null) view.setVisibility(visibility);
    }

    public static int dp2px(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public static int getColor(View view, int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return view.getResources().getColor(id, theme);
        } else {
            //noinspection deprecation
            return view.getResources().getColor(id);
        }
    }

    public static int getColor(TypedArray typedArray, int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return typedArray.getResources().getColor(id, theme);
        } else {
            //noinspection deprecation
            return typedArray.getResources().getColor(id);
        }
    }

    public static int getColor(View view, int id) {
        return getColor(view, id, null);
    }

    public static int getColor(TypedArray typedArray, int id) {
        return getColor(typedArray, id, null);
    }

    public static Drawable getSystemDrawable(Context context, int id) {
        int[] attrs = new int[] { id };
        TypedArray ta = context.obtainStyledAttributes(attrs);
        Drawable drawable = ta.getDrawable(0);
        ta.recycle();
        return drawable;
    }

    public static Drawable getDrawable(View view, int id, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return view.getResources().getDrawable(id, theme);
        } else {
            //noinspection deprecation
            return view.getResources().getDrawable(id);
        }
    }

    public static Drawable getDrawable(View view, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return view.getResources().getDrawable(id, null);
        } else {
            //noinspection deprecation
            return view.getResources().getDrawable(id);
        }
    }

    public static GradientDrawable getOvalDrawable(View view, int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) getDrawable(
                view,
                R.drawable.shape_oval_normal);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    public static BitmapDrawable getOvalBitmapDrawable(View view, int radius, int color) {
        Bitmap bitmap = Bitmap.createBitmap(
                2 * radius,
                2 * radius,
                Bitmap.Config.ARGB_8888);
        Canvas canvasPressed = new Canvas(bitmap);
        Paint paintPressed = new Paint();
        paintPressed.setAntiAlias(true);
        paintPressed.setColor(color);
        canvasPressed.drawCircle(
                radius,
                radius,
                radius, paintPressed);
        return new BitmapDrawable(view.getResources(), bitmap);
    }

    public static GradientDrawable getRectangleDrawable(View view, int cornerRadius, int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) getDrawable(
                view,
                R.drawable.shape_rectangle_normal);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    public static BitmapDrawable getRectangleBitmapDrawable(View view, int width, int height, int cornerRadius, int color) {
        Bitmap bitmap = Bitmap.createBitmap(
                width,
                height,
                Bitmap.Config.ARGB_8888);
        Canvas canvasPressed = new Canvas(bitmap);
        Paint paintPressed = new Paint();
        paintPressed.setAntiAlias(true);
        paintPressed.setColor(color);
        canvasPressed.drawRoundRect(new RectF(0, 0, width, height), cornerRadius, cornerRadius, paintPressed);
        return new BitmapDrawable(view.getResources(), bitmap);
    }

    public static float distance(Point a, Point b) {
        return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    // Bitmap drawable in state-list drawable is able to perform a click-effect.
    public static StateListDrawable getOvalStateListBitmapDrawable(View view,
                                                             int radius,
                                                             int normalColor,
                                                             int highlightedColor,
                                                             int unableColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(
                new int[]{android.R.attr.state_pressed},
                getOvalBitmapDrawable(view, radius, highlightedColor));
        stateListDrawable.addState(
                new int[]{-android.R.attr.state_enabled},
                getOvalBitmapDrawable(view, radius, unableColor));
        stateListDrawable.addState(
                StateSet.WILD_CARD,
                getOvalBitmapDrawable(view, radius, normalColor));
        return stateListDrawable;
    }

    // Gradient drawable in state-list drawable is not able to perform a click-effect.
    public static StateListDrawable getOvalStateListGradientDrawable(View view,
                                                                     int normalColor,
                                                                     int highlightedColor,
                                                                     int unableColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(
                new int[]{android.R.attr.state_pressed},
                getOvalDrawable(view, highlightedColor));
        stateListDrawable.addState(
                new int[]{-android.R.attr.state_enabled},
                getOvalDrawable(view, unableColor));
        stateListDrawable.addState(
                StateSet.WILD_CARD,
                getOvalDrawable(view, normalColor));
        return stateListDrawable;
    }

    // Bitmap drawable in state-list drawable is able to perform a click-effect.
    public static StateListDrawable getRectangleStateListBitmapDrawable(View view,
                                                                        int width,
                                                                        int height,
                                                                        int cornerRadius,
                                                                        int normalColor,
                                                                        int highlightedColor,
                                                                        int unableColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(
                new int[]{android.R.attr.state_pressed},
                getRectangleBitmapDrawable(view, width, height, cornerRadius, highlightedColor));
        stateListDrawable.addState(
                new int[]{-android.R.attr.state_enabled},
                getRectangleBitmapDrawable(view, width, height, cornerRadius, unableColor));
        stateListDrawable.addState(
                StateSet.WILD_CARD,
                getRectangleBitmapDrawable(view, width, height, cornerRadius, normalColor));
        return stateListDrawable;
    }

    // Gradient drawable in state-list drawable is not able to perform a click-effect.
    public static StateListDrawable getRectangleStateListGradientDrawable(View view,
                                                                          int cornerRadius,
                                                                          int normalColor,
                                                                          int highlightedColor,
                                                                          int unableColor) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(
                new int[]{android.R.attr.state_pressed},
                getRectangleDrawable(view, cornerRadius, highlightedColor));
        stateListDrawable.addState(
                new int[]{-android.R.attr.state_enabled},
                getRectangleDrawable(view, cornerRadius, unableColor));
        stateListDrawable.addState(
                StateSet.WILD_CARD,
                getRectangleDrawable(view, cornerRadius, normalColor));
        return stateListDrawable;
    }

    public static int getInt(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getInt(id, typedArray.getResources().getInteger(defaultId));
    }

    public static boolean getBoolean(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getBoolean(id, typedArray.getResources().getBoolean(defaultId));
    }

    public static int getDimenSize(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getDimensionPixelSize(id, typedArray.getResources().getDimensionPixelSize(defaultId));
    }

    public static int getDimenOffset(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getDimensionPixelOffset(id, typedArray.getResources().getDimensionPixelOffset(defaultId));
    }

    public static int getColor(
            TypedArray typedArray,
            int id,
            int defaultId) {
        return typedArray.getColor(id, Util.getColor(typedArray, defaultId));
    }

    public static void setDrawable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            //noinspection deprecation
            view.setBackgroundDrawable(drawable);
        }
    }

    public static int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.9f;
        return Color.HSVToColor(hsv);
    }

    public static int getLighterColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 1.1f;
        return Color.HSVToColor(hsv);
    }

    private static int[] colors = new int[] {
            Color.parseColor("#F44336"),
            Color.parseColor("#E91E63"),
            Color.parseColor("#9C27B0"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#03A9F4"),
            Color.parseColor("#00BCD4"),
            Color.parseColor("#009688"),
            Color.parseColor("#4CAF50"),
            Color.parseColor("#009688"),
            Color.parseColor("#CDDC39"),
            Color.parseColor("#FFEB3B"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548"),
            Color.parseColor("#9E9E9E"),
            Color.parseColor("#607D8B"),
    };

    private static ArrayList<Integer> usedColor = new ArrayList<>();

    public static int getColor() {
        Random random = new Random();
        while (true) {
            int colorIndex = random.nextInt(colors.length);
            if (!usedColor.contains(colorIndex)) {
                usedColor.add(colorIndex);
                while (usedColor.size() > 6) usedColor.remove(0);
                return colors[colorIndex];
            }
        }
    }

    public static boolean pointInView(PointF point, View view) {
        return view.getLeft() <= point.x && point.x <= view.getRight() &&
               view.getTop() <= point.y && point.y <= view.getBottom();
    }

    private static Util ourInstance = new Util();

    public static Util getInstance() {
        return ourInstance;
    }

    private Util() {
    }
}
