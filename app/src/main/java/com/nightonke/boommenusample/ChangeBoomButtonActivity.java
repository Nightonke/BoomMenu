package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.BoomButton;
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
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++)
            bmb.addBuilder(BuilderManager.getHamButtonBuilder());

        bmb.setOnBoomListener(new OnBoomListenerAdapter() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                super.onClicked(index, boomButton);
                if (index == bmb.getPiecePlaceEnum().pieceNumber() - 1) {
                    changeBoomButton();
                }
            }
        });
    }

    private void changeBoomButton() {
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            BoomButton boomButton = bmb.getBoomButton(i);
            if (boomButton == null) continue;
            ImageView image = boomButton.getImageView();
            if (image != null) image.setImageResource(BuilderManager.getImageResource());
            TextView text = boomButton.getTextView();
            if (text != null) text.setText("I'm changed!");
            TextView subText = boomButton.getSubTextView();
            if (subText != null) subText.setText("I'm changed, too!");
        }
    }
}
