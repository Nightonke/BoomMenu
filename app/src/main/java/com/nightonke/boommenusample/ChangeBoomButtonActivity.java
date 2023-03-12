package com.nightonke.boommenusample;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListenerAdapter;

public class ChangeBoomButtonActivity extends AppCompatActivity {

    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_boom_button);

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("Change Text", "..."));
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("Change Image", "...")
                .normalImageRes(R.drawable.elephant));
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("Change Color", "...")
                .normalColorRes(R.color.colorPrimary));
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("Change Piece Color", "..."));
        bmb.addBuilder(BuilderManager.getHamButtonBuilder("Change Unable", "...")
                .unableColor(Color.BLUE)
                .unableImageRes(R.drawable.butterfly)
                .unableText("Unable!"));

        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                super.onClicked(index, boomButton);
                changeBoomButton(index);
            }
        });
    }

    private void changeBoomButton(int index) {
        // From version 2.0.9, BMB supports a new feature to change contents in boom-button
        // by changing contents in the corresponding builder.
        // Please notice that not every method supports this feature. Only the method whose comment
        // contains the "Synchronicity" tag supports.
        // For more details, check:
        // https://github.com/Nightonke/BoomMenu/wiki/Change-Boom-Buttons-Dynamically
        HamButton.Builder builder = (HamButton.Builder) bmb.getBuilder(index);
        if (index == 0) {
            builder.normalText("Changed!");
            builder.highlightedText("Highlighted, changed!");
            builder.subNormalText("Sub-text, changed!");
            builder.normalTextColor(Color.YELLOW);
            builder.highlightedTextColorRes(R.color.colorPrimary);
            builder.subNormalTextColor(Color.BLACK);
        } else if (index == 1) {
            builder.normalImageRes(R.drawable.bat);
            builder.highlightedImageRes(R.drawable.bear);
        } else if (index == 2) {
            builder.normalColorRes(R.color.colorAccent);
        } else if (index == 3) {
            builder.pieceColor(Color.WHITE);
        } else if (index == 4) {
            builder.unable(true);
        }
    }
}
