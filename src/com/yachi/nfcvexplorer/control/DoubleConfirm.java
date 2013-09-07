package com.yachi.nfcvexplorer.control;

import android.content.Context;
import android.content.res.Resources;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-19
 * 
 * @file 二次返回按键确认
 * 
 */
public class DoubleConfirm {
	private DoubleConfirmEvent iEvent;
	/** 二次按键确认间隔 */
	private int iTimeSpace = 3 * 1000;
	/** 是否进行了第一次按键确认 */
	private boolean isFirstConfirm = false;
	private Toast iToast;

	/**
	 * 设置二次确认等待间隔
	 * 
	 * @param aTime
	 */
	public void setTimeSpace(int aTime) {
		iTimeSpace = aTime;
	}

	/**
	 * 设置二次确认要执行的事件
	 * 
	 * @param aEvent
	 */
	public void setEvent(DoubleConfirmEvent aEvent) {
		iEvent = aEvent;
	}

	/**
	 * 检测二次按下返回按键
	 * 
	 * @param aKeyEvent
	 */
	public void onKeyPressed(KeyEvent aKeyEvent, Context aContext) {
		if (iEvent == null)
			return;
		if (aKeyEvent.getAction() == KeyEvent.ACTION_DOWN) {
			if (aKeyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
				if (isFirstConfirm) {// 判断是否第二次按下
					isFirstConfirm = false;
					iEvent.doSecondConfirmEvent();
					iToast.cancel();
				} else {
					isFirstConfirm = true;
					iToast = Toast.makeText(aContext, aContext.getResources()
							.getString(iEvent.getFirstConfirmTipsId()),
							Toast.LENGTH_SHORT);
					iToast.show();
					new Thread(resetConfirm).start();
				}
			}
		}
	}

	/**
	 * 超过间隔后复位第一次按键操作
	 * */
	private Runnable resetConfirm = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(iTimeSpace);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isFirstConfirm = false;
		}
	};

	/**
	 * 二次确认要执行的事件
	 * */
	static public interface DoubleConfirmEvent {
		/**
		 * 获取第一次按键时要弹出的提示说明的StringValue id
		 * 
		 * @return
		 */
		public int getFirstConfirmTipsId();

		/**
		 * 二次确认后要执行的操作
		 */
		public void doSecondConfirmEvent();
	}
}