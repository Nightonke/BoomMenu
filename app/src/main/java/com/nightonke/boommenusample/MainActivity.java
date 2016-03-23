package com.nightonke.boommenusample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.ClickEffectType;
import com.nightonke.boommenu.Types.DimType;
import com.nightonke.boommenu.Types.OrderType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements
        BoomMenuButton.OnSubButtonClickListener,
        BoomMenuButton.AnimatorListener,
        View.OnClickListener {

    private BoomMenuButton boomMenuButton;
    private BoomMenuButton boomMenuButtonInActionBar;
    private BoomMenuButton boomInfo;

    private Context mContext;
    private View mCustomView;

    private RadioGroup buttonTypeGroup;
    
    private SeekBar buttonNumberSeek;
    private TextView buttonNumberText;
    
    private RadioGroup boomTypeGroup;
    private RadioButton[] boomTypeButtons;
    
    private RadioGroup placeTypeGroup;
    private RadioButton[] placeTypeButtons;
    private int[] CirclePlaceTypes = new int[]{1, 2, 4, 2, 4, 6, 4, 3, 2};
    private int[] HamPlaceTypes = new int[]{1, 1, 1, 1};
    
    private SeekBar animationDurationSeek;
    private TextView animationDurationText;

    private SeekBar animationStartDelaySeek;
    private TextView animationStartDelayText;

    private SeekBar animationRotationDegreeSeek;
    private TextView animationRotationDegreeText;
    
    private CheckBox autoDismiss;

    private RadioGroup showOrderTypeGroup;
    private RadioGroup hideOrderTypeGroup;

    private RadioGroup clickEffect;

    private ProgressBar animationListener;

    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText(R.string.app_name);

        boomMenuButtonInActionBar = (BoomMenuButton) mCustomView.findViewById(R.id.boom);
        boomMenuButtonInActionBar.setOnSubButtonClickListener(this);
        boomMenuButtonInActionBar.setAnimatorListener(this);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0,0);

        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
        boomMenuButton.setOnSubButtonClickListener(this);
        boomMenuButton.setAnimatorListener(this);
        boomMenuButton.setDimType(DimType.DIM_0);

        boomInfo = (BoomMenuButton)mCustomView.findViewById(R.id.info);
        boomInfo.setOnSubButtonClickListener(new BoomMenuButton.OnSubButtonClickListener() {
            @Override
            public void onClick(int buttonIndex) {
                if (buttonIndex == 0) {
                    Toast.makeText(mContext, "Boom!", Toast.LENGTH_SHORT).show();
                } else if (buttonIndex == 1) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                            "https://github.com/Nightonke/BoomMenu")));
                } else if (buttonIndex == 2) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                            "https://github.com/Nightonke")));
                }
            }
        });
        boomInfo.setAnimatorListener(new BoomMenuButton.AnimatorListener() {
            @Override
            public void toShow() {

            }

            @Override
            public void showing(float fraction) {

            }

            @Override
            public void showed() {

            }

            @Override
            public void toHide() {

            }

            @Override
            public void hiding(float fraction) {

            }

            @Override
            public void hided() {

            }
        });

        initViews();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!isInit) {
            initBoom();
            initInfoBoom();
        }
        isInit = true;
    }

    private void initInfoBoom() {

        Drawable[] drawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.drawable.boom,
                R.drawable.java,
                R.drawable.github
        };
        for (int i = 0; i < 3; i++)
            drawables[i] = ContextCompat.getDrawable(mContext, drawablesResource[i]);

        int[][] colors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            colors[i][1] = ContextCompat.getColor(mContext, R.color.material_white);
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

        boomInfo.init(
                drawables,
                new String[]{"BoomMenuButton", "View source code", "Follow me"},
                colors,
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

        boomInfo.setSubButtonShadowOffset(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2));
        boomInfo.setTextViewColor(ContextCompat.getColor(mContext, R.color.black));
        boomInfo.setBoomType(BoomType.PARABOLA_2);
    }

    private void initBoom() {
        int number = buttonNumberSeek.getProgress() + 1;

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
            drawables[i] = ContextCompat.getDrawable(mContext, drawablesResource[i]);

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
            colors[i][1] = GetRandomColor();
            colors[i][0] = Util.getInstance().getPressedColor(colors[i][1]);
        }

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
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        boomMenuButtonInActionBar.init(
                drawables,
                strings,
                colors,
                buttonType,
                getBoomType(),
                getPlaceType(),
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        boomMenuButton.setSubButtonShadowOffset(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2));
        boomMenuButtonInActionBar.setSubButtonShadowOffset(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2));
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
        buttonNumberSeek.setProgress(0);
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

        animationDurationSeek = (SeekBar)findViewById(R.id.animation_duration_seek);
        animationDurationSeek.setMax(9);
        animationDurationSeek.setProgress(0);
        animationDurationSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                animationDurationText.setText(((progress + 1) * 500) + " ms");
                boomMenuButton.setDuration((progress + 1) * 500);
                boomMenuButtonInActionBar.setDuration((progress + 1) * 500);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        animationDurationText = (TextView)findViewById(R.id.animation_duration_text);
        animationDurationText.setText(((animationDurationSeek.getProgress() + 1) * 500) + " ms");
        boomMenuButton.setDuration((animationDurationSeek.getProgress() + 1) * 500);
        boomMenuButtonInActionBar.setDuration((animationDurationSeek.getProgress() + 1) * 500);

        animationStartDelaySeek = (SeekBar)findViewById(R.id.animation_start_delay_seek);
        animationStartDelaySeek.setMax(10);
        animationStartDelaySeek.setProgress(0);
        animationStartDelaySeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                animationStartDelayText.setText(((progress + 1) * 100) + " ms");
                boomMenuButton.setDelay((progress + 1) * 100);
                boomMenuButtonInActionBar.setDelay((progress + 1) * 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        animationStartDelayText = (TextView)findViewById(R.id.animation_start_delay_text);
        animationStartDelayText.setText(((animationStartDelaySeek.getProgress() + 1) * 100) + " ms");
        boomMenuButton.setDelay((animationStartDelaySeek.getProgress() + 1) * 100);
        boomMenuButtonInActionBar.setDelay((animationStartDelaySeek.getProgress() + 1) * 100);

        animationRotationDegreeSeek = (SeekBar)findViewById(R.id.animation_rotation_degree_seek);
        animationRotationDegreeSeek.setMax(9);
        animationRotationDegreeSeek.setProgress(0);
        animationRotationDegreeSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                animationRotationDegreeText.setText((progress * 360) + " degrees");
                boomMenuButton.setRotateDegree(progress * 360);
                boomMenuButtonInActionBar.setRotateDegree(progress * 360);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        animationRotationDegreeText = (TextView)findViewById(R.id.animation_rotation_degree_text);
        animationRotationDegreeText.setText((animationRotationDegreeSeek.getProgress() * 360) + " degrees");
        boomMenuButton.setRotateDegree(animationRotationDegreeSeek.getProgress() * 360);
        boomMenuButtonInActionBar.setRotateDegree(animationRotationDegreeSeek.getProgress() * 360);

        autoDismiss = (CheckBox)findViewById(R.id.auto_dismiss);
        autoDismiss.setChecked(true);
        autoDismiss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boomMenuButton.setAutoDismiss(isChecked);
                boomMenuButtonInActionBar.setAutoDismiss(isChecked);
            }
        });

        showOrderTypeGroup = (RadioGroup) findViewById(R.id.group_show_order_type);
        showOrderTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.show_order_type_default:
                        boomMenuButton.setShowOrderType(OrderType.DEFAULT);
                        boomMenuButtonInActionBar.setShowOrderType(OrderType.DEFAULT);
                        break;
                    case R.id.show_order_type_reverse:
                        boomMenuButton.setShowOrderType(OrderType.REVERSE);
                        boomMenuButtonInActionBar.setShowOrderType(OrderType.REVERSE);
                        break;
                    case R.id.show_order_type_random:
                        boomMenuButton.setShowOrderType(OrderType.RANDOM);
                        boomMenuButtonInActionBar.setShowOrderType(OrderType.RANDOM);
                        break;
                }
            }
        });
        boomMenuButton.setShowOrderType(OrderType.DEFAULT);
        boomMenuButtonInActionBar.setShowOrderType(OrderType.DEFAULT);

        hideOrderTypeGroup = (RadioGroup) findViewById(R.id.group_hide_order_type);
        hideOrderTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.hide_order_type_default:
                        boomMenuButton.setHideOrderType(OrderType.DEFAULT);
                        boomMenuButtonInActionBar.setHideOrderType(OrderType.DEFAULT);
                        break;
                    case R.id.hide_order_type_reverse:
                        boomMenuButton.setHideOrderType(OrderType.REVERSE);
                        boomMenuButtonInActionBar.setHideOrderType(OrderType.REVERSE);
                        break;
                    case R.id.hide_order_type_random:
                        boomMenuButton.setHideOrderType(OrderType.RANDOM);
                        boomMenuButtonInActionBar.setHideOrderType(OrderType.RANDOM);
                        break;
                }
            }
        });
        boomMenuButton.setHideOrderType(OrderType.DEFAULT);
        boomMenuButtonInActionBar.setHideOrderType(OrderType.DEFAULT);

        animationListener = (ProgressBar)findViewById(R.id.animation_listener);

        clickEffect = (RadioGroup)findViewById(R.id.group_click_effect);
        clickEffect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.click_effect_ripple:
                        boomMenuButton.setClickEffectType(ClickEffectType.RIPPLE);
                        boomMenuButtonInActionBar.setClickEffectType(ClickEffectType.RIPPLE);
                        boomInfo.setClickEffectType(ClickEffectType.RIPPLE);
                        break;
                    case R.id.click_effect_normal:
                        boomMenuButton.setClickEffectType(ClickEffectType.NORMAL);
                        boomMenuButtonInActionBar.setClickEffectType(ClickEffectType.NORMAL);
                        boomInfo.setClickEffectType(ClickEffectType.NORMAL);
                        break;
                }
            }
        });

        findViewById(R.id.easy_example).setOnClickListener(this);
    }

    private BoomType getBoomType() {
        if (boomTypeButtons[0].isChecked()) {
            return BoomType.LINE;
        } else if (boomTypeButtons[1].isChecked()) {
            return BoomType.PARABOLA;
        } else if (boomTypeButtons[2].isChecked()) {
            return BoomType.HORIZONTAL_THROW;
        } else if (boomTypeButtons[3].isChecked()) {
            return BoomType.PARABOLA_2;
        } else if (boomTypeButtons[4].isChecked()) {
            return BoomType.HORIZONTAL_THROW_2;
        }
        return BoomType.PARABOLA;
    }

    private void setPlaceRadioButton(int index) {
        placeTypeGroup.removeAllViews();
        int length = 1;
        if (buttonTypeGroup.getCheckedRadioButtonId() == R.id.circle_button) {
            length = CirclePlaceTypes[index - 1];
            placeTypeButtons = new RadioButton[length];
            for (int i = 0; i < length; i++) {
                placeTypeButtons[i] = new RadioButton(this);
                placeTypeButtons[i].setText("CIRCLE_" + index + "_" + (i + 1));
                placeTypeButtons[i].setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                placeTypeGroup.addView(placeTypeButtons[i]);
            }
        } else if (buttonTypeGroup.getCheckedRadioButtonId() == R.id.hamburger_button) {
            length = HamPlaceTypes[index - 1];
            placeTypeButtons = new RadioButton[length];
            for (int i = 0; i < length; i++) {
                placeTypeButtons[i] = new RadioButton(this);
                placeTypeButtons[i].setText("HAM_" + index + "_" + (i + 1));
                placeTypeButtons[i].setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                placeTypeGroup.addView(placeTypeButtons[i]);
            }
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
        } else if (buttonTypeGroup.getCheckedRadioButtonId() == R.id.hamburger_button) {
            if (buttonNumberSeek.getProgress() == 0) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.HAM_1_1;
                }
            } else if (buttonNumberSeek.getProgress() == 1) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.HAM_2_1;
                }
            } else if (buttonNumberSeek.getProgress() == 2) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.HAM_3_1;
                }
            } else if (buttonNumberSeek.getProgress() == 3) {
                if (placeTypeButtons[0].isChecked()) {
                    return PlaceType.HAM_4_1;
                }
            }
        }
        return PlaceType.CIRCLE_1_1;
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

    public int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }

    @Override
    public void onClick(int buttonIndex) {
        Toast.makeText(this, "On click " +
                boomMenuButton.getTextViews()[buttonIndex].getText().toString() +
                " button", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toShow() {
        animationListener.setProgress(0);
    }

    @Override
    public void showing(float fraction) {
        animationListener.setProgress((int) (fraction * 100));
    }

    @Override
    public void showed() {
        animationListener.setProgress(100);
    }

    @Override
    public void toHide() {
        animationListener.setProgress(100);
    }

    @Override
    public void hiding(float fraction) {
        animationListener.setProgress((int) ((1 - fraction) * 100));
    }

    @Override
    public void hided() {
        animationListener.setProgress(0);
    }

    @Override
    public void onBackPressed() {
        if (boomMenuButton.isClosed()
                && boomMenuButtonInActionBar.isClosed()
                && boomInfo.isClosed()) {
            super.onBackPressed();
        } else {
            boomMenuButton.dismiss();
            boomMenuButtonInActionBar.dismiss();
            boomInfo.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.easy_example:
                startActivity(new Intent(this, EasyUseActivity.class));
                break;
        }
    }
}
