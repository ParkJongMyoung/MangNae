package com.mangnae.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.mangnae.R;

/**
 * Created by pjm on 2015-12-31.
 */
public class BasicFragment extends SherlockFragment {

    TextView textTitleBar;
    Button button_write;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.basic_fragment, container, false);

        textTitleBar = (TextView) getSherlockActivity().findViewById(R.id.textTitle);
        textTitleBar.setText("기초");
        button_write = (Button)getSherlockActivity().findViewById(R.id.button_write);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button_write.setVisibility(View.GONE);



    }
}
