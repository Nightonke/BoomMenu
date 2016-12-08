package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nightonke.boommenu.BoomMenuButton;

public class EaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ease);

        initBmb(R.id.bmb1);
        initBmb(R.id.bmb2);
        initBmb(R.id.bmb3);
        initBmb(R.id.bmb4);
        initBmb(R.id.bmb5);
        initBmb(R.id.bmb6);
        initBmb(R.id.bmb7);
        initBmb(R.id.bmb8);
        initBmb(R.id.bmb9);
    }

    private BoomMenuButton initBmb(int res) {
        BoomMenuButton bmb = (BoomMenuButton) findViewById(res);
        assert bmb != null;
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());
        return bmb;
    }
}
