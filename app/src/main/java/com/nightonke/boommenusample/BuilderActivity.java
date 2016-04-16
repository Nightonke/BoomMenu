package com.nightonke.boommenusample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Eases.EaseType;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.ClickEffectType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.OrderType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

public class BuilderActivity extends AppCompatActivity {

    private boolean init = false;
    private BoomMenuButton boomMenuButton;

    private TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);

        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
        code = (TextView)findViewById(R.id.code);

        setText();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

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

        // this is an example to show how to use the builder
        new BoomMenuButton.Builder()
                // set all sub buttons with subButtons method
                //.subButtons(subButtonDrawables, subButtonColors, subButtonTexts)
                // or add each sub button with addSubButton method
                .addSubButton(this, R.drawable.boom, subButtonColors[0], "BoomMenuButton")
                .addSubButton(this, R.drawable.java, subButtonColors[1], "View source code")
                .addSubButton(this, R.drawable.github, subButtonColors[2], "Follow me")
                .frames(80)
                .duration(800)
                .delay(100)
                .showOrder(OrderType.RANDOM)
                .hideOrder(OrderType.RANDOM)
                .button(ButtonType.HAM)
                .boom(BoomType.PARABOLA_2)
                .place(PlaceType.HAM_3_1)
                .showMoveEase(EaseType.EaseOutBack)
                .hideMoveEase(EaseType.EaseOutCirc)
                .showScaleEase(EaseType.EaseOutBack)
                .hideScaleType(EaseType.EaseOutCirc)
                .rotateDegree(720)
                .showRotateEase(EaseType.EaseOutBack)
                .hideRotateType(EaseType.Linear)
                .autoDismiss(true)
                .cancelable(true)
                .dim(DimType.DIM_6)
                .clickEffect(ClickEffectType.RIPPLE)
                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonTextColor(Color.BLACK)
                .onBoomButtonBlick(null)
                .animator(null)
                .onSubButtonClick(null)
                // this only work when the place type is SHARE_X_X
                .shareStyle(0, 0, 0)
                .init(boomMenuButton);
    }

    private void setText() {
        code.setText(
                "// this is an example to show how to use the builder\n" +
                        "        new BoomMenuButton.Builder()\n" +
                        "                // set all sub buttons with subButtons method\n" +
                        "                //.subButtons(subButtonDrawables, subButtonColors, subButtonTexts)\n" +
                        "                // or add each sub button with addSubButton method\n" +
                        "                .addSubButton(this, R.drawable.boom, subButtonColors[0], \"BoomMenuButton\")\n" +
                        "                .addSubButton(this, R.drawable.java, subButtonColors[1], \"View source code\")\n" +
                        "                .addSubButton(this, R.drawable.github, subButtonColors[2], \"Follow me\")\n" +
                        "                .frames(80)\n" +
                        "                .duration(800)\n" +
                        "                .delay(100)\n" +
                        "                .showOrder(OrderType.RANDOM)\n" +
                        "                .hideOrder(OrderType.RANDOM)\n" +
                        "                .button(ButtonType.HAM)\n" +
                        "                .boom(BoomType.PARABOLA_2)\n" +
                        "                .place(PlaceType.HAM_3_1)\n" +
                        "                .showMoveEase(EaseType.EaseOutBack)\n" +
                        "                .hideMoveEase(EaseType.EaseOutCirc)\n" +
                        "                .showScaleEase(EaseType.EaseOutBack)\n" +
                        "                .hideScaleType(EaseType.EaseOutCirc)\n" +
                        "                .rotateDegree(720)\n" +
                        "                .showRotateEase(EaseType.EaseOutBack)\n" +
                        "                .hideRotateType(EaseType.Linear)\n" +
                        "                .autoDismiss(true)\n" +
                        "                .cancelable(true)\n" +
                        "                .dim(DimType.DIM_6)\n" +
                        "                .clickEffect(ClickEffectType.RIPPLE)\n" +
                        "                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))\n" +
                        "                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))\n" +
                        "                .subButtonTextColor(Color.BLACK)\n" +
                        "                .onBoomButtonBlick(null)\n" +
                        "                .animator(null)\n" +
                        "                .onSubButtonClick(null)\n" +
                        "                // this only work when the place type is SHARE_X_X\n" +
                        "                .shareStyle(0, 0, 0)\n" +
                        "                .init(boomMenuButton);"
        );
    }
}
