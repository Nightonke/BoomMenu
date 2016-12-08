package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nightonke.boommenu.BoomMenuButton;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener {

    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

        findViewById(R.id.boom).setOnClickListener(this);
        findViewById(R.id.reboom).setOnClickListener(this);
        findViewById(R.id.boom_immediately).setOnClickListener(this);
        findViewById(R.id.reboom_immediately).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boom:
                bmb.boom();
                break;
            case R.id.reboom:
                bmb.reboom();
                break;
            case R.id.boom_immediately:
                bmb.boomImmediately();
                break;
            case R.id.reboom_immediately:
                bmb.reboomImmediately();
                break;
        }
    }
}
