package com.nightonke.boommenusample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;


/**
 * Created by Weiping Huang at 02:25 on 16/12/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText("No. " + position);

        holder.bmb1.clearBuilders();
        for (int i = 0; i < holder.bmb1.getPiecePlaceEnum().pieceNumber(); i++)
            holder.bmb1.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

        holder.bmb2.clearBuilders();
        for (int i = 0; i < holder.bmb2.getPiecePlaceEnum().pieceNumber(); i++)
            holder.bmb2.addBuilder(BuilderManager.getHamButtonBuilder());

        holder.bmb3.clearBuilders();
        for (int i = 0; i < holder.bmb3.getButtonPlaceEnum().buttonNumber(); i++)
            holder.bmb3.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        BoomMenuButton bmb1;
        BoomMenuButton bmb2;
        BoomMenuButton bmb3;

        ViewHolder(View view) {
            super(view);
            this.text = (TextView) view.findViewById(R.id.text);
            this.bmb1 = (BoomMenuButton) view.findViewById(R.id.bmb1);
            this.bmb2 = (BoomMenuButton) view.findViewById(R.id.bmb2);
            this.bmb3 = (BoomMenuButton) view.findViewById(R.id.bmb3);
        }
    }
}
