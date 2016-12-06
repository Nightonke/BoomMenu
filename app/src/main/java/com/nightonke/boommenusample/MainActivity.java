package com.nightonke.boommenusample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.simple_circle_button_example).setOnClickListener(this);
        findViewById(R.id.text_inside_circle_button_example).setOnClickListener(this);
        findViewById(R.id.text_outside_circle_button_example).setOnClickListener(this);
        findViewById(R.id.ham_button_example).setOnClickListener(this);
        findViewById(R.id.boom_example).setOnClickListener(this);
        findViewById(R.id.button_place_alignment_example).setOnClickListener(this);
        findViewById(R.id.order_example).setOnClickListener(this);
        findViewById(R.id.actionbar_example).setOnClickListener(this);
        findViewById(R.id.ease_example).setOnClickListener(this);
        findViewById(R.id.listener_example).setOnClickListener(this);
        findViewById(R.id.control_example).setOnClickListener(this);
        findViewById(R.id.share_example).setOnClickListener(this);
        findViewById(R.id.list_example).setOnClickListener(this);
        findViewById(R.id.recycler_view_example).setOnClickListener(this);
        findViewById(R.id.fragment_example).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple_circle_button_example:
                startActivity(new Intent(this, SimpleCircleButtonActivity.class));
                break;
            case R.id.text_inside_circle_button_example:
                startActivity(new Intent(this, TextInsideCircleButtonActivity.class));
                break;
            case R.id.text_outside_circle_button_example:
                startActivity(new Intent(this, TextOutsideCircleButtonActivity.class));
                break;
            case R.id.ham_button_example:
                startActivity(new Intent(this, HamButtonActivity.class));
                break;
            case R.id.boom_example:
                startActivity(new Intent(this, BoomExampleActivity.class));
                break;
            case R.id.button_place_alignment_example:
                startActivity(new Intent(this, ButtonPlaceAlignmentActivity.class));
                break;
            case R.id.order_example:
                startActivity(new Intent(this, OrderExampleActivity.class));
                break;
            case R.id.actionbar_example:
                startActivity(new Intent(this, ActionBarActivity.class));
                break;
            case R.id.ease_example:
                startActivity(new Intent(this, EaseActivity.class));
                break;
            case R.id.listener_example:
                startActivity(new Intent(this, ListenerActivity.class));
                break;
            case R.id.control_example:
                startActivity(new Intent(this, ControlActivity.class));
                break;
            case R.id.share_example:
                startActivity(new Intent(this, ShareActivity.class));
                break;
            case R.id.list_example:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.recycler_view_example:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.fragment_example:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
        }
    }
}
