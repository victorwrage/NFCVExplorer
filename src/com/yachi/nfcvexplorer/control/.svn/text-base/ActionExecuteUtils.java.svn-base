package com.yachi.nfcvexplorer.control;

import java.util.List;

import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.receive.ActionReceiver;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;

import android.net.Uri;
import android.net.wifi.WifiManager;

import android.telephony.SmsManager;

public class ActionExecuteUtils {
	private Context context;
	private ActionItem item;

	public ActionExecuteUtils(Context context, ActionItem item) {
		this.context = context;
		this.item = item;
	}

	/**
	 * 打开应用程序
	 * 
	 * @param context
	 * @param item
	 */
	public void OpenApplication() {
		try {
			PackageManager packageManager = context.getPackageManager();
			Intent intent = packageManager.getLaunchIntentForPackage(item
					.getPackage_name());
			context.startActivity(intent);
		} catch (Exception e) {
			LogUtil.v("OpenApplication--fail");
		}
		LogUtil.v("OpenApplication");
	}

	/**
	 * 打开关闭WIFI
	 * 
	 * @param context
	 * @param item
	 */
	public void SwitchWIFI() {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		wifi.setWifiEnabled(item.getAction_switch());
		LogUtil.v("SwitchWIFI");
	}

	/**
	 * 打开关闭蓝牙
	 * 
	 * @param context
	 * @param item
	 */
	public void SwitchBluetooth() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (item.getAction_switch()) {
			mBluetoothAdapter.enable();
		} else {
			mBluetoothAdapter.disable();
		}
		LogUtil.v("SwitchBluetooth");
	}

	/**
	 * 发送短信
	 */
	public void SentMessage() {
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				new Intent(), 0);
		String sms = item.getMessage_content();
		if (sms.length() > 70) {
			List<String> texts = smsManager.divideMessage(sms);
			for (String str : texts) {
				smsManager.sendTextMessage(item.getDia_number(), null, str,
						pendingIntent, null);
			}
		} else {
			smsManager.sendTextMessage(item.getDia_number(), null, sms,
					pendingIntent, null);
		}
		LogUtil.v("SentMessage");
	}

	/**
	 * 打电话
	 */
	public void DialPhone() {
		Intent phoneIntent = new Intent("android.intent.action.CALL",
				Uri.parse("tel:" + item.getDia_number()));
		phoneIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(phoneIntent);
		LogUtil.v("DialPhone" + item.getDia_number());
	}

	/**
	 * 设置铃声类型
	 */
	public void SetRingType() {
		AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setRingerMode(Integer.parseInt(item.getRing_type()));
		LogUtil.v("SetRingType");
	}

	/**
	 * 设置铃声音量
	 */
	public void SetRingValue() {
		AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setStreamVolume(AudioManager.STREAM_RING,
				item.getRing_value(), AudioManager.FLAG_SHOW_UI);
		LogUtil.v("SetRingValue");
	}

	/**
	 * 设置屏幕亮度
	 */
	public void SetScreenLight() {
		LogUtil.v("SetScreenLight" + context);
		Intent it = new Intent(Intent.ACTION_VIEW);
		it.setClass(context, ActionReceiver.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		it.putExtra(Constants.INTENT_EXTRA, item.getScreen_light());
		it.putExtra(Constants.INTENT_EXTRA_DETAIL, item.getDetail());
		context.startActivity(it);

	}

	/**
	 * 设置屏幕锁定
	 */
	public void SetScreenLock() {
		Intent i = new Intent(Constants.BROADCAST_ACTION_MESSAGE);
		i.putExtra(Constants.INTENT_EXTRA, item.getAction_switch());
		context.sendBroadcast(i);
		LogUtil.v("SetScreenLock");
	}
}
