package com.shadow.service.demo;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class ShadowIntentService extends IntentService{

	public class SimpleBinder extends Binder {
		
		public ShadowIntentService getService(){
			
			return ShadowIntentService.this;
		}
		
		public int add(int a , int b){
			return a + b;
		}
	}
	
	public static final String TAG = "ShadowIntentService";
	
	public SimpleBinder sBinder;
	public ShadowIntentService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public ShadowIntentService() {
		super(TAG);
	}
	 /**
	  * IntentService从缺省的工作线程中调用本方法，并用启动服务的intent作为参数。 
	  * 本方法返回后，IntentService将适时终止这个服务。
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		
		int l = 10;
		for(int i = 0; i < l; i++){
		      Log.v(TAG, "num-" + i);
		      long endTime = System.currentTimeMillis() + 1*1000;
		      while (System.currentTimeMillis() < endTime) {
		          synchronized (this) {
		              try {
		                  wait(endTime - System.currentTimeMillis());
		              } catch (Exception e) {
		              }
		          }
		      }
		  }
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		sBinder = new SimpleBinder();
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		Toast.makeText(this, R.string.toast_service_start, Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		
		Toast.makeText(this, R.string.toast_service_destroy, Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return sBinder;
	}
}
