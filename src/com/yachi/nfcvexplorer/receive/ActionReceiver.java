package com.yachi.nfcvexplorer.receive;

import com.yachi.nfcvexplorer.R;

import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * 事件响应界面
 * @author xiaoyl
 * @date 2013-07-31
 */
public class ActionReceiver extends Activity {
	
	private TextView tv_tip;
	private String tip_content;
	
	/** 关闭提示界面*/
	private static final int SHUT_TOAST = 1024;
	
	/**
	 * 执行完成后
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SHUT_TOAST:
				ActionReceiver.this.finish();
			}
		}
	};
	
	/**
	 * 改变屏幕亮度
	 */
	private void changeScreenLight(int value){
		NFCUtils.setScreenLight(ActionReceiver.this,
						value, false);
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.v("ActionReceive----onCreate");
		setContentView(R.layout.action_toast_lay);	

		tv_tip = (TextView) findViewById(R.id.toast_tv);
		
		if(tv_tip==null)return;
		tv_tip.setText(getIntent().getStringExtra(Constants.INTENT_EXTRA_DETAIL));
		
		int v = getIntent().getIntExtra(Constants.INTENT_EXTRA, -1);
		if(v!=-1){
			changeScreenLight(v);
		}
		new Thread(new MyThread()).start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (tip_content == null || Constants.current_card==null)
			return;	
		tv_tip.setText(tip_content);
	}
	
	
	/**
	 * 对自身的显示时间控制线程
	 * @author xiaoyl
	 */
	public class MyThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(2000);
					Message message = new Message();
					message.what = SHUT_TOAST;
					handler.sendMessage(message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
