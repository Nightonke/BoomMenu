package com.nightonke.boommenusample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;

public class ShareActivity extends AppCompatActivity {

    private BoomMenuButton bmb1;
    private BoomMenuButton bmb2;
    private BoomMenuButton bmb3;

    private TextView showDelaySeekText;
    private TextView showDurationSeekText;
    private TextView hideDelaySeekText;
    private TextView hideDurationSeekText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        initBmb(bmb1 = (BoomMenuButton)findViewById(R.id.bmb1));
        initBmbWithWhitePieceColor(bmb2 = (BoomMenuButton)findViewById(R.id.bmb2));
        initBmb(bmb3 = (BoomMenuButton)findViewById(R.id.bmb3));

        bmb1.setShareLineLength(45);
        bmb1.setShareLineWidth(5);
        bmb1.setDotRadius(12);

        bmb3.setShareLine1Color(Color.BLACK);
        bmb3.setShareLine2Color(Color.BLACK);

        initShowDelaySeek();
        initShowDurationSeek();
        initHideDelaySeek();
        initHideDurationSeek();
    }

    private BoomMenuButton initBmb(BoomMenuButton bmb) {
        assert bmb != null;
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++)
            bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilder());
        return bmb;
    }

    private BoomMenuButton initBmbWithWhitePieceColor(BoomMenuButton bmb) {
        assert bmb != null;
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++)
            bmb.addBuilder(BuilderManager.getTextInsideCircleButtonBuilderWithDifferentPieceColor());
        return bmb;
    }

    private void initShowDelaySeek() {
        SeekBar showDelaySeekBar = (SeekBar) findViewById(R.id.show_delay_seek);
        assert showDelaySeekBar != null;
        showDelaySeekBar.setMax(1000);
        showDelaySeekBar.setProgress((int) bmb1.getShowDelay());
        showDelaySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showDelaySeekText.setText("Show delay = " + seekBar.getProgress() + " ms");
                bmb1.setShowDelay(progress);
                bmb2.setShowDelay(progress);
                bmb3.setShowDelay(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        showDelaySeekText = (TextView)findViewById(R.id.show_delay_text);
        showDelaySeekText.setText("Show delay = " + showDelaySeekBar.getProgress() + " ms");
    }

    private void initShowDurationSeek() {
        SeekBar showDurationSeekBar = (SeekBar) findViewById(R.id.show_duration_seek);
        assert showDurationSeekBar != null;
        showDurationSeekBar.setMax(1000);
        showDurationSeekBar.setProgress((int) bmb1.getShowDuration());
        showDurationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showDurationSeekText.setText("Show duration = " + seekBar.getProgress() + " ms");
                bmb1.setShowDuration(progress);
                bmb2.setShowDuration(progress);
                bmb3.setShowDuration(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        showDurationSeekText = (TextView)findViewById(R.id.show_duration_text);
        showDurationSeekText.setText("Show duration = " + showDurationSeekBar.getProgress() + " ms");
    }

    private void initHideDelaySeek() {
        SeekBar hideDelaySeekBar = (SeekBar) findViewById(R.id.hide_delay_seek);
        assert hideDelaySeekBar != null;
        hideDelaySeekBar.setMax(1000);
        hideDelaySeekBar.setProgress((int) bmb1.getHideDelay());
        hideDelaySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hideDelaySeekText.setText("Hide delay = " + seekBar.getProgress() + " ms");
                bmb1.setHideDelay(progress);
                bmb2.setHideDelay(progress);
                bmb3.setHideDelay(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        hideDelaySeekText = (TextView)findViewById(R.id.hide_delay_text);
        hideDelaySeekText.setText("Hide delay = " + hideDelaySeekBar.getProgress() + " ms");
    }

    private void initHideDurationSeek() {
        SeekBar hideDurationSeekBar = (SeekBar) findViewById(R.id.hide_duration_seek);
        assert hideDurationSeekBar != null;
        hideDurationSeekBar.setMax(1000);
        hideDurationSeekBar.setProgress((int) bmb1.getHideDuration());
        hideDurationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hideDurationSeekText.setText("Hide duration = " + seekBar.getProgress() + " ms");
                bmb1.setHideDuration(progress);
                bmb2.setHideDuration(progress);
                bmb3.setHideDuration(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        hideDurationSeekText = (TextView)findViewById(R.id.hide_duration_text);
        hideDurationSeekText.setText("Hide duration = " + hideDurationSeekBar.getProgress() + " ms");
    }
}
