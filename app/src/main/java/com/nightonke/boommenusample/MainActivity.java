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

        listenClickEventOf(R.id.simple_circle_button_example);
        listenClickEventOf(R.id.text_inside_circle_button_example);
        listenClickEventOf(R.id.text_outside_circle_button_example);
        listenClickEventOf(R.id.ham_button_example);
        listenClickEventOf(R.id.square_and_piece_corner_radius_example);
        listenClickEventOf(R.id.boom_example);
        listenClickEventOf(R.id.button_place_alignment_example);
        listenClickEventOf(R.id.order_example);
        listenClickEventOf(R.id.actionbar_example);
        listenClickEventOf(R.id.tool_bar_example);
        listenClickEventOf(R.id.draggable_example);
        listenClickEventOf(R.id.ease_example);
        listenClickEventOf(R.id.listener_example);
        listenClickEventOf(R.id.control_example);
        listenClickEventOf(R.id.share_example);
        listenClickEventOf(R.id.list_example);
        listenClickEventOf(R.id.recycler_view_example);
        listenClickEventOf(R.id.fragment_example);
        listenClickEventOf(R.id.change_boom_button_example);
        listenClickEventOf(R.id.three_d_animation_example);
        listenClickEventOf(R.id.custom_position_example);
        listenClickEventOf(R.id.fade_views_example);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple_circle_button_example:
                startActivity(SimpleCircleButtonActivity.class);
                break;
            case R.id.text_inside_circle_button_example:
                startActivity(TextInsideCircleButtonActivity.class);
                break;
            case R.id.text_outside_circle_button_example:
                startActivity(TextOutsideCircleButtonActivity.class);
                break;
            case R.id.ham_button_example:
                startActivity(HamButtonActivity.class);
                break;
            case R.id.square_and_piece_corner_radius_example:
                startActivity(SquareAndPieceCornerRadiusActivity.class);
                break;
            case R.id.boom_example:
                startActivity(BoomExampleActivity.class);
                break;
            case R.id.button_place_alignment_example:
                startActivity(ButtonPlaceAlignmentActivity.class);
                break;
            case R.id.order_example:
                startActivity(OrderExampleActivity.class);
                break;
            case R.id.tool_bar_example:
                startActivity(ToolBarActivity.class);
                break;
            case R.id.actionbar_example:
                startActivity(ActionBarActivity.class);
                break;
            case R.id.draggable_example:
                startActivity(DraggableActivity.class);
                break;
            case R.id.ease_example:
                startActivity(EaseActivity.class);
                break;
            case R.id.listener_example:
                startActivity(ListenerActivity.class);
                break;
            case R.id.control_example:
                startActivity(ControlActivity.class);
                break;
            case R.id.share_example:
                startActivity(ShareActivity.class);
                break;
            case R.id.list_example:
                startActivity(ListViewActivity.class);
                break;
            case R.id.recycler_view_example:
                startActivity(RecyclerViewActivity.class);
                break;
            case R.id.fragment_example:
                startActivity(FragmentActivity.class);
                break;
            case R.id.change_boom_button_example:
                startActivity(ChangeBoomButtonActivity.class);
                break;
            case R.id.three_d_animation_example:
                startActivity(ThreeDAnimationActivity.class);
                break;
            case R.id.custom_position_example:
                startActivity(CustomPositionActivity.class);
                break;
            case R.id.fade_views_example:
                startActivity(FadeViewsActivity.class);
                break;
        }
    }
    
    private void listenClickEventOf(int id) {
        findViewById(id).setOnClickListener(this);
    }
    
    private void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
