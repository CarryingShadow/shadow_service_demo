package com.shadow.service.demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShadowActivity extends Activity {

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
				startService(i);
			}
		});
	}

}
