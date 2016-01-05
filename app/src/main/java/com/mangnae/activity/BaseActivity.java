package com.mangnae.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Window;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.mangnae.R;

public class BaseActivity extends SlidingFragmentActivity {
	
	Button btnToggle;

	private Handler mHandler;
	private boolean mFlag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 액션바 설정
		getSupportActionBar().setDisplayShowCustomEnabled(true);
		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getSupportActionBar().setCustomView(R.layout.action_bar_title);

		getSupportActionBar().show();
		
		// 메뉴 토글 버튼 추가
		btnToggle = (Button) findViewById(R.id.btnToggle);
		btnToggle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				toggle();
			}
		});

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(msg.what == 0) {
					mFlag = false;
				}
			}
		};
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent Data) { 
		super.onActivityResult(requestCode, resultCode, Data); 	
	}
	
	@Override 
	protected void onPostResume() {
	    super.onPostResume();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		if (!mFlag) {
			Toast.makeText(this, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
			mFlag = true;
			mHandler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
		}
	}
}
