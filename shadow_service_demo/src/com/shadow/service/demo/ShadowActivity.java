package com.shadow.service.demo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ShadowActivity extends Activity {

	public static final String TAG = "ShadowActivity";
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shadow);
		
		mContext = this;

		((Button)findViewById(R.id.start_service)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(mContext, ShadowIntentService.class);
				//启动Service
				startService(i);
			}
		});
		((Button)findViewById(R.id.stop_service)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(mContext, ShadowIntentService.class);
				//停止Service
				stopService(i);
				
			}
		});
		
		final ServiceConnection connection = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {

				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {

				ShadowIntentService.SimpleBinder sBinder = (ShadowIntentService.SimpleBinder)service;
				Log.v(TAG, "3 + 5 = " + sBinder.add( 3 , 5 ));
			}
		};
		
		((Button)findViewById(R.id.bind_service)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(mContext, ShadowIntentService.class);
				bindService(i, connection, Context.BIND_AUTO_CREATE);
			}
		});
		
		((Button)findViewById(R.id.unbind_service)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				unbindService(connection);
			}
		});
		
		((Button)findViewById(R.id.start_and_bindservice)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(mContext, ShadowIntentService.class);
				startService(i);
				bindService(i, connection, Context.BIND_AUTO_CREATE);
			}
		});
		
		((Button)findViewById(R.id.stop_and_unbindservice)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(mContext, ShadowIntentService.class);
				unbindService(connection);
				stopService(i);
			}
		});
	}

}
