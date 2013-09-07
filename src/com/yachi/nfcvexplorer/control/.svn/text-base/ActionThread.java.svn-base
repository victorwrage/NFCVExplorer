package com.yachi.nfcvexplorer.control;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.yachi.nfcvexplorer.bean.ActionItem;


/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-19
 * 
 * @file 事物执行线程
 * 
 */
public  class ActionThread extends Thread {
	private ActionItem item;
	private Handler handler;
    private ActionExecuteUtils actionUtils;
    /** 执行成功*/
    public static final int ACTION_EXEC_SUCCESS = 1024;
	public ActionThread(Context context, ActionItem item,Handler handler) {
		this.item = item;
		this.handler = handler;
		actionUtils =new ActionExecuteUtils(context,item);
	}

	@Override
	public void run() {
		
		switch (item.getAction_type()) {
		case ActionItem.ACTION_OPEN_APPLICATION:
			actionUtils.OpenApplication();
			break;
		case ActionItem.ACTION_WIFI:
			actionUtils.SwitchWIFI();
			break;
		case ActionItem.ACTION_DIA:
			actionUtils.DialPhone();
			break;
		case ActionItem.ACTION_MESSAGE:
			actionUtils.SentMessage();
			break;
		case ActionItem.ACTION_BLUETOOTH:
			actionUtils.SwitchBluetooth();
			break;
		case ActionItem.ACTION_SCREEN_LOCK:
			actionUtils.SetScreenLock();
			break;
		case ActionItem.ACTION_SCREEN_LIGHT:
			actionUtils.SetScreenLight();
			break;
		case ActionItem.ACTION_RING_TYPE:
			actionUtils.SetRingType();
			break;
		case ActionItem.ACTION_RING_NAME:
			break;
		case ActionItem.ACTION_RING_VALUE:
			actionUtils.SetRingValue();
			break;
		}
		
		Message msg = new Message();
		msg.what = ACTION_EXEC_SUCCESS;
		msg.obj = item;
		handler.sendMessage(msg);
	}

}
