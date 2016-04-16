package com.nightonke.boommenusample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;

public class ShareActivity extends AppCompatActivity {

    private boolean init = false;
    private BoomMenuButton boomMenuButton;
    
    private SeekBar buttonNumberSeek;
    private TextView buttonNumberText;
    
    private RadioGroup placeTypeGroup;
    private RadioButton[] placeTypeButtons;
    private int[] SharePlaceTypes = new int[]{1, 2, 4, 2, 4, 6, 4, 3, 2};

    private SeekBar shareLineWidthSeek;
    private TextView shareLineWidthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initViews();
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

        initBoom();
    }

    private void initBoom() {
        int number = buttonNumberSeek.getProgress() + 3;

        Drawable[] drawables = new Drawable[number];
        int[] drawablesResource = new int[]{
                R.drawable.mark,
                R.drawable.refresh,
                R.drawable.copy,
                R.drawable.heart,
                R.drawable.info,
                R.drawable.like,
                R.drawable.record,
                R.drawable.search,
                R.drawable.settings
        };
        for (int i = 0; i < number; i++)
            drawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] STRINGS = new String[]{
                "Mark",
                "Refresh",
                "Copy",
                "Heart",
                "Info",
                "Like",
                "Record",
                "Search",
                "Settings"
        };
        String[] strings = new String[number];
        for (int i = 0; i < number; i++)
            strings[i] = STRINGS[i];

        int[][] colors = new int[number][2];
        for (int i = 0; i < number; i++) {
            colors[i][1] = getRandomColor();
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .subButtons(drawables, colors, strings)
                .button(ButtonType.CIRCLE)
                .boom(BoomType.HORIZONTAL_THROW_2)
                .place(getPlaceType())
                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .shareStyle(3f, getRandomColor(), getRandomColor())
                .init(boomMenuButton);
    }
    
    private void initViews() {
        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);

        buttonNumberSeek = (SeekBar)findViewById(R.id.button_number_seek);
        buttonNumberSeek.setMax(6);
        buttonNumberSeek.setProgress(0);
        buttonNumberSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                buttonNumberText.setText((progress + 3) + " Button(s)");
                setPlaceRadioButton(progress + 3);
                initBoom();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        buttonNumberText = (TextView)findViewById(R.id.button_number_text);
        buttonNumberText.setText("3 Button(s)");

        placeTypeGroup = (RadioGroup)findViewById(R.id.group_place_type);
        placeTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                initBoom();
            }
        });
        setPlaceRadioButton(3);

        shareLineWidthSeek = (SeekBar)findViewById(R.id.share_line_width_seek);
        shareLineWidthSeek.setMax(100);
        shareLineWidthSeek.setProgress(50);
        shareLineWidthSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shareLineWidthText.setText("Width: " + Util.getInstance().round(progress * 6f / 100, 1));
                boomMenuButton.setShareLineWidth(shareLineWidthSeek.getProgress() * 6f / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        shareLineWidthText = (TextView)findViewById(R.id.share_line_width_text);
        shareLineWidthText.setText("Width: 3.0");
    }

    private void setPlaceRadioButton(int index) {
        placeTypeGroup.removeAllViews();
        int length = SharePlaceTypes[index - 1];
        placeTypeButtons = new RadioButton[length];
        for (int i = 0; i < length; i++) {
            placeTypeButtons[i] = new RadioButton(this);
            placeTypeButtons[i].setText("SHARE_" + index + "_" + (i + 1));
            placeTypeButtons[i].setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            placeTypeGroup.addView(placeTypeButtons[i]);
        }
        placeTypeButtons[0].setChecked(true);
    }

    private PlaceType getPlaceType() {
        if (buttonNumberSeek.getProgress() == 0) {
            if (placeTypeButtons[0].isChecked()) {
                return PlaceType.SHARE_3_1;
            } else if (placeTypeButtons[1].isChecked()) {
                return PlaceType.SHARE_3_2;
            } else if (placeTypeButtons[2].isChecked()) {
                return PlaceType.SHARE_3_3;
            } else if (placeTypeButtons[3].isChecked()) {
                return PlaceType.SHARE_3_4;
            }
        } else if (buttonNumberSeek.getProgress() == 1) {
            if (placeTypeButtons[0].isChecked()) {
                return PlaceType.SHARE_4_1;
            } else if (placeTypeButtons[1].isChecked()) {
                return PlaceType.SHARE_4_2;
            }
        } else if (buttonNumberSeek.getProgress() == 2) {
            if (placeTypeButtons[0].isChecked()) {
                return PlaceType.SHARE_5_1;
            } else if (placeTypeButtons[1].isChecked()) {
                return PlaceType.SHARE_5_2;
            } else if (placeTypeButtons[2].isChecked()) {
                return PlaceType.SHARE_5_3;
            } else if (placeTypeButtons[3].isChecked()) {
                return PlaceType.SHARE_5_4;
            }
        } else if (buttonNumberSeek.getProgress() == 3) {
            if (placeTypeButtons[0].isChecked()) {
                return PlaceType.SHARE_6_1;
            } else if (placeTypeButtons[1].isChecked()) {
                return PlaceType.SHARE_6_2;
            } else if (placeTypeButtons[2].isChecked()) {
                return PlaceType.SHARE_6_3;
            } else if (placeTypeButtons[3].isChecked()) {
                return PlaceType.SHARE_6_4;
            } else if (placeTypeButtons[4].isChecked()) {
                return PlaceType.SHARE_6_5;
            } else if (placeTypeButtons[5].isChecked()) {
                return PlaceType.SHARE_6_6;
            }
        } else if (buttonNumberSeek.getProgress() == 4) {
            if (placeTypeButtons[0].isChecked()) {
                return PlaceType.SHARE_7_1;
            } else if (placeTypeButtons[1].isChecked()) {
                return PlaceType.SHARE_7_2;
            } else if (placeTypeButtons[2].isChecked()) {
                return PlaceType.SHARE_7_3;
            } else if (placeTypeButtons[3].isChecked()) {
                return PlaceType.SHARE_7_4;
            }
        } else if (buttonNumberSeek.getProgress() == 5) {
            if (placeTypeButtons[0].isChecked()) {
                return PlaceType.SHARE_8_1;
            } else if (placeTypeButtons[1].isChecked()) {
                return PlaceType.SHARE_8_2;
            } else if (placeTypeButtons[2].isChecked()) {
                return PlaceType.SHARE_8_3;
            }
        } else if (buttonNumberSeek.getProgress() == 6) {
            if (placeTypeButtons[0].isChecked()) {
                return PlaceType.SHARE_9_1;
            } else if (placeTypeButtons[1].isChecked()) {
                return PlaceType.SHARE_9_2;
            }
        }
        return PlaceType.SHARE_3_1;
    }
    
    private String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B"};

    public int getRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }
}
