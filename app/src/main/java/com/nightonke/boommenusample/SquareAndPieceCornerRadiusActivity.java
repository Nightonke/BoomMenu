package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nightonke.boommenu.BoomMenuButton;

public class SquareAndPieceCornerRadiusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_and_piece_corner_radius);

        BoomMenuButton bmb1 = (BoomMenuButton) findViewById(R.id.bmb1);
        for (int i = 0; i < bmb1.getPiecePlaceEnum().pieceNumber(); i++)
            bmb1.addBuilder(BuilderManager.getSquareSimpleCircleButtonBuilder());

        BoomMenuButton bmb2 = (BoomMenuButton) findViewById(R.id.bmb2);
        for (int i = 0; i < bmb2.getPiecePlaceEnum().pieceNumber(); i++)
            bmb2.addBuilder(BuilderManager.getSquareTextInsideCircleButtonBuilder());

        BoomMenuButton bmb3 = (BoomMenuButton) findViewById(R.id.bmb3);
        for (int i = 0; i < bmb3.getPiecePlaceEnum().pieceNumber(); i++)
            bmb3.addBuilder(BuilderManager.getTextOutsideCircleButtonBuilderWithDifferentPieceColor());

        BoomMenuButton bmb4 = (BoomMenuButton) findViewById(R.id.bmb4);
        for (int i = 0; i < bmb4.getPiecePlaceEnum().pieceNumber(); i++)
            bmb4.addBuilder(BuilderManager.getPieceCornerRadiusHamButtonBuilder());
    }
}
