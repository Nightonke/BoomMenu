package com.nightonke.boommenusample;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

public class EasyUseActivity extends AppCompatActivity {

    private boolean init = false;
    private BoomMenuButton boomMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_use);

        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
    }

    /**
     * Init the boom menu button.
     * Notice that you should call this NOT in your onCreate method.
     * Because the width and height of boom menu button is 0.
     * Call this in:
     *
     * @Override
     * public void onWindowFocusChanged(boolean hasFocus) {
     *     super.onWindowFocusChanged(hasFocus);
     *     init(...);
     * }
     *
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Use a param to record whether the boom button has been initialized
        // Because we don't need to init it again when onResume()
        if (init) return;
        init = true;

        Drawable[] subButtonDrawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.drawable.boom,
                R.drawable.java,
                R.drawable.github
        };
        for (int i = 0; i < 3; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] subButtonTexts = new String[]{"BoomMenuButton", "View source code", "Follow me"};

        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(this, R.color.material_white);
            subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
        }


        /**
         * @param drawables The drawables of images of sub buttons. Can not be null.
         * @param strings The texts of sub buttons, ok to be null.
         * @param colors The colors of sub buttons, including pressed-state and normal-state.
         * @param buttonType The button type.
         * @param boomType The boom type.
         * @param placeType The place type.
         * @param particleEffect Whether use particle effect.
         * @param showMoveEaseType Ease type to move the sub buttons when showing.
         * @param showScaleEaseType Ease type to scale the sub buttons when showing.
         * @param showRotateEaseType Ease type to rotate the sub buttons when showing.
         * @param hideMoveEaseType Ease type to move the sub buttons when dismissing.
         * @param hideScaleEaseType Ease type to scale the sub buttons when dismissing.
         * @param hideRotateEaseType Ease type to rotate the sub buttons when dismissing.
         * @param rotateDegree Rotation degree.
         */
        boomMenuButton.init(
                subButtonDrawables,
                subButtonTexts,
                subButtonColors,
                ButtonType.HAM,
                BoomType.PARABOLA,
                PlaceType.HAM_3_1,
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        boomMenuButton.setTextViewColor(ContextCompat.getColor(this, R.color.black));
    }
}
