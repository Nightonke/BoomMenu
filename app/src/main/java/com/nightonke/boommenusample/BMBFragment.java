package com.nightonke.boommenusample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nightonke.boommenu.BoomMenuButton;

public class BMBFragment extends Fragment {

    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_bmb, container, false);

        TextView text = (TextView) fragment.findViewById(R.id.text);
        text.setText(String.valueOf(position));

        BoomMenuButton bmb1 = (BoomMenuButton) fragment.findViewById(R.id.bmb1);
        for (int i = 0; i < bmb1.getPiecePlaceEnum().pieceNumber(); i++)
            bmb1.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

        BoomMenuButton bmb2 = (BoomMenuButton) fragment.findViewById(R.id.bmb2);
        for (int i = 0; i < bmb2.getPiecePlaceEnum().pieceNumber(); i++)
            bmb2.addBuilder(BuilderManager.getSimpleCircleButtonBuilder());

        return fragment;
    }

    public Fragment position(int position) {
        this.position = position;
        return this;
    }

}
