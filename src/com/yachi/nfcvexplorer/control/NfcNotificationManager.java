package com.yachi.nfcvexplorer.control;


import com.android.support.v8.app.NotificationCompat;
import com.yachi.nfcvexplorer.bean.ActionItem;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;


public class NfcNotificationManager {
	/**
	 * 自身的实例
	 */
	private static NfcNotificationManager instance;
	private Resources res;
	private Context context;
	/**系统通知管理类*/
	private NotificationManager notificationManager;
	
	private NfcNotificationManager(Context context) {
		this.context = context;
		res = context.getResources();
		notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	public static NfcNotificationManager getInstance(Context context) {
		if (instance == null) {
			instance = new NfcNotificationManager(context);
		}
		return instance;
	}
	
    /**
     * 提醒
     * @param ctx
     * @param smallIcon
     * @param ticker
     * @param large
     * @param pi
     * @param item
     * @return
     */
	public static NotificationCompat.Builder newBaseNotify(Context ctx,int smallIcon,String ticker,Bitmap large,PendingIntent pi,
			ActionItem item){
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ctx)
		.setContentTitle("已经设置的活动")
		.setWhen(System.currentTimeMillis())
		.setContentText(item.getDetail())
		.setContentInfo("来自NFCExplorer")
        .setAutoCancel(true)
		.setSmallIcon(smallIcon)
		.setContentIntent(pi)
		.setLargeIcon(large)
		.setTicker(ticker);
		return mBuilder;
	}
	//set context view 
	public static Notification apiNie(){
		return null;
	}
    
	/**
	 * 取消
	 */
	public static void cancel(){
		
	}
}
