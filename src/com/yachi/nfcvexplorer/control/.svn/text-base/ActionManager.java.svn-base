package com.yachi.nfcvexplorer.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.android.support.v8.app.NotificationCompat.Builder;
import com.yachi.nfcvexplorer.MainActivity;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 事物执行管理类
 * 
 * @author xiaoyl
 * @date 2013-07-13
 * 
 */
public class ActionManager {
	private static ActionManager instance;
	private Context context;
	private NfcVSharedPreference pref;
	private AddTaskThread handlerThread;
	private NotificationManager notifyManager;
	private Handler loadTaskHandler;
	private TagsDbHelper db;
	/** 动作任务线程池 */
	private ExecutorService iExecutor;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			ActionItem item = (ActionItem) msg.obj;
			LogUtil.v("startAction--完成-" + item.getDetail());
			switch (msg.what) {
			case ActionThread.ACTION_EXEC_SUCCESS:
				// Utils.toast(context, item.getDetail());
				notificationPrepare(item);
				Intent i = new Intent(Constants.BROADCAST_ACTION_DESTROY);
				context.sendBroadcast(i);
				break;
			}
		};
	};

	private ActionManager(Context context) {
		this.context = context;
		db = TagsDbHelper.getInstance(context);
		pref = NfcVSharedPreference.getInstance(context);
		notifyManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		iExecutor = Executors.newFixedThreadPool(pref.getActionCount());
		handlerThread = new AddTaskThread();
		handlerThread.start();
		loadTaskHandler = new Handler(handlerThread.getLooper(), handlerThread);
	}

	public static ActionManager getInstance(Context context) {
		if (instance == null) {
			instance = new ActionManager(context);
		}
		return instance;
	}

	private void notificationPrepare(ActionItem item) {

		Intent resultIntent = new Intent(context, MainActivity.class);
		resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		//TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

		final PendingIntent resultPendingIntent = PendingIntent.getActivity(
				context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		final Bitmap large = BitmapFactory.decodeResource(
				context.getResources(), R.drawable.icon);
		Builder mBuilder = NfcNotificationManager.newBaseNotify(context,
				R.drawable.icon, item.getDetail(), large, resultPendingIntent,item);
		
		notifyManager.notify(item.getAction_type(), mBuilder.build());
	}

	/**
	 * 开始以堆栈形式执行Action
	 * 
	 * @param item
	 */
	public void addAndStartAction(TagItem item) {
		if (!item.isTag_enable()) {
			LogUtil.v("tag--disabled");
			return;
		}
		item.setTag_use_count(item.getTag_use_count() + 1);
		for (TagItem alter_item : Constants.tags_in_list) {
			if (alter_item.getTag_number().equals(item.getTag_number())
					&& alter_item.getTag_create_time().equals(
							item.getTag_create_time())) {
				alter_item.setTag_use_count(item.getTag_use_count());
			}
		}
		db.updateTagItem(item);
		
		for (ActionItem aitem : item.getActions()) {
			if (!aitem.getAction_enable()) {
				LogUtil.v("action--disabled");
				break;
			}
			loadTaskHandler
					.sendMessage(loadTaskHandler.obtainMessage(0, aitem));
		}
	}

	/**
	 * 堆栈线程
	 * @author xiaoyl
	 */
	private class AddTaskThread extends HandlerThread implements Callback {
		public AddTaskThread() {
			super("AddTaskThread");
		}

		@Override
		public boolean handleMessage(Message msg) {
			ActionItem item = (ActionItem) msg.obj;
			startAction(item);
			return true;
		}
	}

	/**
	 * 开始执行单条Action
	 * @param item
	 */
	private void startAction(ActionItem item) {
		ActionThread action = new ActionThread(context, item, handler);
		if (action != null) {
			LogUtil.v("startAction---" + item.getDetail());
			iExecutor.submit(action);
		}
	}

}
