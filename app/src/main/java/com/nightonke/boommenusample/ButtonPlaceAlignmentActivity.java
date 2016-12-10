package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceAlignmentEnum;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import java.util.ArrayList;
import java.util.List;

public class ButtonPlaceAlignmentActivity extends AppCompatActivity {

    private BoomMenuButton bmb;

    private TextView topMarginSeekText;
    private TextView bottomMarginSeekText;
    private TextView leftMarginSeekText;
    private TextView rightMarginSeekText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_place_alignment);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

        ListView listView = (ListView) findViewById(R.id.list_view);
        assert listView != null;
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, getData()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bmb.setButtonPlaceAlignmentEnum(ButtonPlaceAlignmentEnum.getEnum(position));
            }
        });

        initTopMarginSeek();
        initBottomMarginSeek();
        initLeftMarginSeek();
        initRightMarginSeek();
    }

    private void initTopMarginSeek() {
        SeekBar topMarginSeekBar = (SeekBar) findViewById(R.id.top_margin_seek);
        assert topMarginSeekBar != null;
        topMarginSeekBar.setMax(Util.dp2px(50));
        topMarginSeekBar.setProgress((int) bmb.getButtonTopMargin());
        topMarginSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                topMarginSeekText.setText("Top margin = " + seekBar.getProgress() + " pixel(s)");
                bmb.setButtonTopMargin(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        topMarginSeekText = (TextView)findViewById(R.id.top_margin_text);
        topMarginSeekText.setText("Top margin = " + topMarginSeekBar.getProgress() + " pixel(s)");
    }

    private void initBottomMarginSeek() {
        SeekBar bottomMarginSeekBar = (SeekBar) findViewById(R.id.bottom_margin_seek);
        assert bottomMarginSeekBar != null;
        bottomMarginSeekBar.setMax(Util.dp2px(50));
        bottomMarginSeekBar.setProgress((int) bmb.getButtonBottomMargin());
        bottomMarginSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                bottomMarginSeekText.setText("Bottom margin = " + seekBar.getProgress() + " pixel(s)");
                bmb.setButtonBottomMargin(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        bottomMarginSeekText = (TextView)findViewById(R.id.bottom_margin_text);
        bottomMarginSeekText.setText("Bottom margin = " + bottomMarginSeekBar.getProgress() + " pixel(s)");
    }

    private void initLeftMarginSeek() {
        SeekBar leftMarginSeekBar = (SeekBar) findViewById(R.id.left_margin_seek);
        assert leftMarginSeekBar != null;
        leftMarginSeekBar.setMax(Util.dp2px(50));
        leftMarginSeekBar.setProgress((int) bmb.getButtonLeftMargin());
        leftMarginSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                leftMarginSeekText.setText("Left margin = " + seekBar.getProgress() + " pixel(s)");
                bmb.setButtonLeftMargin(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        leftMarginSeekText = (TextView)findViewById(R.id.left_margin_text);
        leftMarginSeekText.setText("Left margin = " + leftMarginSeekBar.getProgress() + " pixel(s)");
    }

    private void initRightMarginSeek() {
        SeekBar rightMarginSeekBar = (SeekBar) findViewById(R.id.right_margin_seek);
        assert rightMarginSeekBar != null;
        rightMarginSeekBar.setMax(Util.dp2px(50));
        rightMarginSeekBar.setProgress((int) bmb.getButtonRightMargin());
        rightMarginSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rightMarginSeekText.setText("Right margin = " + seekBar.getProgress() + " pixel(s)");
                bmb.setButtonRightMargin(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rightMarginSeekText = (TextView)findViewById(R.id.right_margin_text);
        rightMarginSeekText.setText("Right margin = " + rightMarginSeekBar.getProgress() + " pixel(s)");
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int p = 0; p < ButtonPlaceAlignmentEnum.values().length - 1; p++)
            data.add(ButtonPlaceAlignmentEnum.getEnum(p) + "");
        return data;
    }
}
