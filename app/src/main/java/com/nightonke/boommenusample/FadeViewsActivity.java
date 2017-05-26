package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nightonke.boommenu.BoomMenuButton;

public class FadeViewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_views);

        BoomMenuButton bmb1 = (BoomMenuButton) findViewById(R.id.bmb1);
        for (int i = 0; i < bmb1.getPiecePlaceEnum().pieceNumber(); i++)
            bmb1.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

        BoomMenuButton bmb2 = (BoomMenuButton) findViewById(R.id.bmb2);
        for (int i = 0; i < bmb2.getPiecePlaceEnum().pieceNumber(); i++)
            bmb2.addBuilder(BuilderManager.getHamButtonBuilderWithDifferentPieceColor());
    }
}
