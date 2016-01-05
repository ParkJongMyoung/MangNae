package com.mangnae.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.mangnae.R;
import com.mangnae.activity.MainActivity;

public class SNSFragment extends SherlockFragment implements View.OnClickListener {

	TextView textTitleBar;
	Button button_write;
	View v;

	private ListView listview_sns;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		v = inflater.inflate(R.layout.sns_fragment, container, false);

		textTitleBar = (TextView) getSherlockActivity().findViewById(R.id.textTitle);
		textTitleBar.setText("SNS");
		button_write = (Button)getSherlockActivity().findViewById(R.id.button_write);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);


		listview_sns = (ListView)getActivity().findViewById(R.id.listview_sns);
		button_write.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		if(view == button_write){
			Fragment newContent = null;
			newContent = new SNSWriteFragment();
			if (newContent != null)
				switchFragment(newContent);
		}

	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchContent(fragment);
		}
	}
}