package com.mangnae.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.mangnae.R;
import com.mangnae.util.MangNaeUtil;

public class DiaryFragment extends SherlockFragment {

	TextView textTitleBar;
	View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.diary_fragment, container, false);


		Log.d("MangNae", "Dimension : " + MangNaeUtil.getDisplay(getActivity()));
		textTitleBar = (TextView) getSherlockActivity().findViewById(R.id.textTitle);
		textTitleBar.setText("DIARY");
		
		return v;
	}

}