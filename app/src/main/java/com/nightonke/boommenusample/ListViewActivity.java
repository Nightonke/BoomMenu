package com.nightonke.boommenusample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ((ListView)findViewById(R.id.list_view)).setAdapter(new MyAdapter());
        ((ListView)findViewById(R.id.list_view)).setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((BoomMenuButton)view.findViewById(R.id.boom_ham)).boom();
            }
        });
    }

    static class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1000;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, final ViewGroup parent) {

            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);

                viewHolder = new ViewHolder();
                viewHolder.tv = (TextView) convertView.findViewById(R.id.text_view);
                viewHolder.circleBoomMenuButton = (BoomMenuButton) convertView.findViewById(R.id.boom_circle);
                viewHolder.hamBoomMenuButton = (BoomMenuButton) convertView.findViewById(R.id.boom_ham);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv.setText("No. " + position);

            final Drawable[] circleSubButtonDrawables = new Drawable[3];
            int[] drawablesResource = new int[]{
                    R.drawable.mark,
                    R.drawable.refresh,
                    R.drawable.copy
            };
            for (int i = 0; i < 3; i++)
                circleSubButtonDrawables[i]
                        = ContextCompat.getDrawable(parent.getContext(), drawablesResource[i]);

            final Drawable[] hamSubButtonDrawables = new Drawable[3];
            drawablesResource = new int[]{
                    R.drawable.java,
                    R.drawable.java,
                    R.drawable.java
            };
            for (int i = 0; i < 3; i++)
                hamSubButtonDrawables[i]
                        = ContextCompat.getDrawable(parent.getContext(), drawablesResource[i]);

            final String[] circleSubButtonTexts = new String[]{
                    "No. " + position,
                    "No. " + position,
                    "No. " + position};

            final String[] hamSubButtonTexts = new String[]{
                    "Java 1 at No. " + position,
                    "Java 2 at No. " + position,
                    "Java 3 at No. " + position};

            final int[][] subButtonColors = new int[3][2];
            for (int i = 0; i < 3; i++) {
                subButtonColors[i][1] = GetRandomColor();
                subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
            }

            // init the BMB with delay

            viewHolder.circleBoomMenuButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Now with Builder, you can init BMB more convenient
                    new BoomMenuButton.Builder()
                            .subButtons(circleSubButtonDrawables, subButtonColors, circleSubButtonTexts)
                            .button(ButtonType.CIRCLE)
                            .boom(BoomType.PARABOLA)
                            .place(PlaceType.CIRCLE_3_1)
                            .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                            .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                                @Override
                                public void onClick(int buttonIndex) {
                                    Toast.makeText(
                                            parent.getContext(),
                                            "On click " + circleSubButtonTexts[buttonIndex],
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .init(viewHolder.circleBoomMenuButton);
                }
            }, 1);

            viewHolder.hamBoomMenuButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Now with Builder, you can init BMB more convenient
                    new BoomMenuButton.Builder()
                            .subButtons(hamSubButtonDrawables, subButtonColors, hamSubButtonTexts)
                            .button(ButtonType.HAM)
                            .boom(BoomType.PARABOLA)
                            .place(PlaceType.HAM_3_1)
                            .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                            .onSubButtonClick(new BoomMenuButton.OnSubButtonClickListener() {
                                @Override
                                public void onClick(int buttonIndex) {
                                    Toast.makeText(
                                            parent.getContext(),
                                            "On click " + hamSubButtonTexts[buttonIndex],
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .init(viewHolder.hamBoomMenuButton);
                }
            }, 1);

            return convertView;
        }

        class ViewHolder {
            public TextView tv;
            public BoomMenuButton circleBoomMenuButton;
            public BoomMenuButton hamBoomMenuButton;
        }
    }

    private static String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B"};

    public static int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }
}
