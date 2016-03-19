package com.nightonke.boommenusample;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.BoomType;
import com.nightonke.boommenu.ButtonType;
import com.nightonke.boommenu.ParticleEffect;
import com.nightonke.boommenu.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private BoomMenuButton boomMenuButton;

    private Context mContext;

    private RadioGroup buttonTypeGroup;
    private SeekBar buttonNumberSeek;
    private TextView buttonNumberText;
    private RadioGroup boomTypeGroup;
    private RadioButton[] boomTypeButtons;
    private RadioGroup placeTypeGroup;
    private RadioButton[] placeTypeButtons;
    private int[] CirclePlaceTypes = new int[]{1, 2, 4, 2, 4, 6, 4, 3, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);

        initViews();

        initBoom();
    }

    private void initBoom() {
        int number = buttonNumberSeek.getProgress() + 1;

        Drawable[] drawables = new Drawable[number];
        for (int i = 0; i < number; i++) drawables[i] = ContextCompat.getDrawable(mContext, R.drawable.mail);

        String[] strings = new String[number];
        for (int i = 0; i < number; i++) strings[i] = "Boom!";

        int[] colors = new int[number];
        for (int i = 0; i < number; i++) colors[i] = GetRandomColor();

        ButtonType buttonType = ButtonType.CIRCLE;
        switch (buttonTypeGroup.getCheckedRadioButtonId()) {
            case R.id.circle_button:
                buttonType = ButtonType.CIRCLE;
                break;
            case R.id.hamburger_button:
                buttonType = ButtonType.HAM;
                break;
        }


        boomMenuButton.init(
                drawables,
                strings,
                colors,
                buttonType,
                getBoomType(),
                getPlaceType(),
                ParticleEffect.NONE,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    private void initViews() {
        buttonTypeGroup = (RadioGroup)findViewById(R.id.group_button_type);
        buttonTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.circle_button:
                        buttonNumberSeek.setMax(8);
                        break;
                    case R.id.hamburger_button:
                        buttonNumberSeek.setMax(3);
                        break;
                }
                buttonNumberSeek.setProgress(0);
                buttonNumberText.setText("1 Button(s)");
                initBoom();
            }
        });
        buttonNumberSeek = (SeekBar)findViewById(R.id.button_number_seek);
        buttonNumberSeek.setMax(8);
        buttonNumberSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                buttonNumberText.setText((progress + 1) + " Button(s)");
                setPlaceRadioButton(progress + 1);
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
        buttonNumberSeek.setProgress(0);
        buttonNumberText.setText("1 Button(s)");
        boomTypeGroup = (RadioGroup)findViewById(R.id.group_boom_type);
        boomTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                initBoom();
            }
        });
        String[] boomTypes = getResources().getStringArray(R.array.boom_type);
        boomTypeButtons = new RadioButton[boomTypes.length];
        for (int i = 0; i < boomTypes.length; i++) {
            boomTypeButtons[i] = new RadioButton(this);
            boomTypeButtons[i].setText(boomTypes[i]);
            boomTypeButtons[i].setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            boomTypeGroup.addView(boomTypeButtons[i]);
        }
        boomTypeButtons[1].setChecked(true);
        placeTypeGroup = (RadioGroup)findViewById(R.id.group_place_type);
        placeTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                initBoom();
            }
        });
        setPlaceRadioButton(1);
    }

    private BoomType getBoomType() {
        if (boomTypeButtons[0].isChecked()) {
            return BoomType.LINE;
        } else if (boomTypeButtons[1].isChecked()) {
            return BoomType.PARABOLA;
        }
        return BoomType.PARABOLA;
    }

    private void setPlaceRadioButton(int index) {
        placeTypeGroup.removeAllViews();
        int length = 1;
        if (buttonTypeGroup.getCheckedRadioButtonId() == R.id.circle_button) {
            length = CirclePlaceTypes[index - 1];
        }
        placeTypeButtons = new RadioButton[length];
        for (int i = 0; i < length; i++) {
            placeTypeButtons[i] = new RadioButton(this);
            placeTypeButtons[i].setText("CIRCLE_" + index + "_" + (i + 1));
            placeTypeButtons[i].setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            placeTypeGroup.addView(placeTypeButtons[i]);
        }
        placeTypeButtons[0].setChecked(true);
    }

    private PlaceType getPlaceType() {
        if (buttonTypeGroup.getCheckedRadioButtonId() == R.id.circle_button) {
            if (buttonNumberSeek.getProgress() == 0) {
                return PlaceType.CIRCLE_1_1;
            } else if (buttonNumberSeek.getProgress() == 1) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_2_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_2_2;
                }
            } else if (buttonNumberSeek.getProgress() == 2) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_3_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_3_2;
                } else if (placeTypeButtons[2].isChecked()) {
                    return PlaceType.CIRCLE_3_3;
                } else if (placeTypeButtons[3].isChecked()) {
                    return PlaceType.CIRCLE_3_4;
                }
            } else if (buttonNumberSeek.getProgress() == 3) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_4_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_4_2;
                }
            } else if (buttonNumberSeek.getProgress() == 4) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_5_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_5_2;
                } else if (placeTypeButtons[2].isChecked()) {
                    return PlaceType.CIRCLE_5_3;
                } else if (placeTypeButtons[3].isChecked()) {
                    return PlaceType.CIRCLE_5_4;
                }
            } else if (buttonNumberSeek.getProgress() == 5) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_6_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_6_2;
                } else if (placeTypeButtons[2].isChecked()) {
                    return PlaceType.CIRCLE_6_3;
                } else if (placeTypeButtons[3].isChecked()) {
                    return PlaceType.CIRCLE_6_4;
                } else if (placeTypeButtons[4].isChecked()) {
                    return PlaceType.CIRCLE_6_5;
                } else if (placeTypeButtons[5].isChecked()) {
                    return PlaceType.CIRCLE_6_6;
                }
            } else if (buttonNumberSeek.getProgress() == 6) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_7_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_7_2;
                } else if (placeTypeButtons[2].isChecked()) {
                    return PlaceType.CIRCLE_7_3;
                } else if (placeTypeButtons[3].isChecked()) {
                    return PlaceType.CIRCLE_7_4;
                }
            } else if (buttonNumberSeek.getProgress() == 7) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_8_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_8_2;
                } else if (placeTypeButtons[2].isChecked()) {
                    return PlaceType.CIRCLE_8_3;
                }
            } else if (buttonNumberSeek.getProgress() == 8) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.CIRCLE_9_1;
                } else if (placeTypeButtons[1].isChecked()) {
                    return PlaceType.CIRCLE_9_2;
                }
            }
        }
        return PlaceType.CIRCLE_1_1;
    }

    private String[] Colors = {"#F44336",
            "#E91E63",
            "#9C27B0",
            "#673AB7",
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

    public int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }
}
