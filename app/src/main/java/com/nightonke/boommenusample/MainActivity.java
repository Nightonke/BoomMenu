package com.nightonke.boommenusample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.BoomType;
import com.nightonke.boommenu.ButtonType;
import com.nightonke.boommenu.Util;

public class MainActivity extends AppCompatActivity {

    private BoomMenuButton boomMenuButton;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
        boomMenuButton.init(
                new Drawable[]{
                        ContextCompat.getDrawable(mContext, R.drawable.mail),
                        ContextCompat.getDrawable(mContext, R.drawable.mail)},
                null,
                null,
                ButtonType.CIRCLE,
                BoomType.PARABOLA);

    }
}
