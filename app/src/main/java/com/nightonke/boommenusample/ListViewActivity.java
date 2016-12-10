package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = (ListView) findViewById(R.id.list_view);
        assert listView != null;
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BoomMenuButton bmb = (BoomMenuButton)view.findViewById(R.id.bmb1);
                bmb.boom();
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
                viewHolder.text = (TextView) convertView.findViewById(R.id.text);
                viewHolder.bmb1 = (BoomMenuButton) convertView.findViewById(R.id.bmb1);
                viewHolder.bmb2 = (BoomMenuButton) convertView.findViewById(R.id.bmb2);
                viewHolder.bmb3 = (BoomMenuButton) convertView.findViewById(R.id.bmb3);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.text.setText("No. " + position);

            viewHolder.bmb1.clearBuilders();
            for (int i = 0; i < viewHolder.bmb1.getPiecePlaceEnum().pieceNumber(); i++)
                viewHolder.bmb1.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

            viewHolder.bmb2.clearBuilders();
            for (int i = 0; i < viewHolder.bmb2.getPiecePlaceEnum().pieceNumber(); i++)
                viewHolder.bmb2.addBuilder(BuilderManager.getHamButtonBuilder());

            viewHolder.bmb3.clearBuilders();
            for (int i = 0; i < viewHolder.bmb3.getButtonPlaceEnum().buttonNumber(); i++)
                viewHolder.bmb3.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

            return convertView;
        }

        class ViewHolder {
            TextView text;
            BoomMenuButton bmb1;
            BoomMenuButton bmb2;
            BoomMenuButton bmb3;
        }
    }
}
