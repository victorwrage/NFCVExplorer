package com.yachi.nfcvexplorer.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.yachi.nfcvexplorer.MainActivity;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.receive.LockScreenReceive;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.KeyguardManager.KeyguardLock;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 保存TAG的界面
 * 
 * @author xiaoyl
 * @date 2013-07-12
 */
public class FrameTagSave extends BaseActivity {
	private TagsDbHelper db_helper;
	private TagItem tag_item;
	private TextView tip_tv;
	private boolean isScaned = false;
	/** 来自热门标签界面 */
	private boolean isShutCut = false;
	/** NFC适配器 */
	private NfcAdapter adapter_nfc;
	private boolean isNfcAvailable = true;

	private IntentFilter[] mFilters;
	private PendingIntent mPendingIntent;
	private String[][] mTechLists;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.e("FrameTagSave----onCreate");
		setContentView(R.layout.scantag_frame_layout);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("写入标签");
		invalidateOptionsMenu();
		db_helper = TagsDbHelper.getInstance(this);
		tip_tv = (TextView) findViewById(R.id.scan_tag_tip);
		adapter_nfc = NfcAdapter.getDefaultAdapter(this);
		if (adapter_nfc == null) {
			isNfcAvailable = false;
		}
		if (isNfcAvailable) {
			setUpIntentFilter();
		}	
	}

	@Override
	protected void onNewIntent(Intent intent) {
		LogUtil.e("FrameTagSave----onNewIntent");
		NFCUtils.getCardID(intent);
		if (NFCUtils.writeTag(intent,
				"nfcv://tags.do/" + Constants.current_card.getCard_uid())) {
			processCard();
			NFCUtils.toast(getApplicationContext(), "扫描成功");
		}else{
			NFCUtils.toast(FrameTagSave.this.getApplicationContext(), "写入失败，但仍然可以工作，需要选择本应用在弹出的窗口");
		}
	}

	/**
	 * 构建mPendingIntent
	 */
	public void setUpIntentFilter() {
		IntentFilter localIntentFilter = new IntentFilter(
				"android.nfc.action.TAG_DISCOVERED");
		if (adapter_nfc != null) {
			mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(
					this, super.getClass())
					.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		}
		try {
			localIntentFilter.addDataType("*/*");
			IntentFilter[] arrayOfIntentFilter = new IntentFilter[1];
			arrayOfIntentFilter[0] = localIntentFilter;
			mFilters = arrayOfIntentFilter;
			String[][] arrayOfStringt = new String[1][];
			String[] arrayOfString = new String[1];
			arrayOfString[0] = NfcF.class.getName();
			arrayOfStringt[0] = arrayOfString;
			mTechLists = arrayOfStringt;
		} catch (IntentFilter.MalformedMimeTypeException localMalformedMimeTypeException) {
			throw new RuntimeException("fail", localMalformedMimeTypeException);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return false;
	}

	@Override
	public void onResume() {
		LogUtil.e("FrameTagSave----onResume");
		super.onResume();
		if (getIntent().getBooleanExtra(Constants.INTENT_EXTRA, false)) {
			isShutCut = true;
		}
		try {
			if (adapter_nfc != null) {
				adapter_nfc.enableForegroundDispatch(FrameTagSave.this,
						mPendingIntent, null, null);
			}
		} catch (Exception localException) {
			LogUtil.e("NFCV", "Error enabling foreground dispatch "
					+ localException.toString());
		}
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (adapter_nfc != null) {
			adapter_nfc.disableForegroundDispatch(FrameTagSave.this);
		}
	}
	/**
	 * 保存TAG
	 */
	private void saveTag() {
		if (!db_helper.insertTagItem(tag_item)) {
			return;
		}
		for (ActionItem a_item : Constants.actions_in_list) {
			db_helper.insertActionItem(a_item);
		}
		LogUtil.v("saveTag successfull" + Constants.actions_in_list.size());
	}

	/**
	 * 增加标签到数据库
	 */
	private void processCard() {
		
		tag_item = new TagItem();
		if (Constants.tag_name == null) {
			Constants.tag_name = "标签" + (Constants.tag_count + 1);
		}
		tag_item.setId(new Random().nextLong());
		final Calendar c = Calendar.getInstance();
		tag_item.setTag_create_time(c.get(Calendar.YEAR) + "_"
				+ (c.get(Calendar.MONTH) + 1) + "_"
				+ c.get(Calendar.DAY_OF_MONTH) + "_"
				+ c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE)
				+ "_" + c.get(Calendar.SECOND) + "_"
				+ c.get(Calendar.MILLISECOND));
		StringBuffer str = new StringBuffer();
		for (ActionItem item : Constants.actions_in_list) {
			str.append("(");
			str.append(item.getDetail());
			LogUtil.v("onNewIntent--" + item.getDetail());
			str.append(")");
			item.setCard_id(Constants.current_card.getCard_uid());
			item.setCreate_time(tag_item.getTag_create_time());
		}
		tag_item.setTag_describe(str.toString());
		tag_item.setTag_number(Constants.current_card.getCard_uid());
		tag_item.setTag_name(Constants.tag_name);
		tag_item.setTag_priority(Constants.tags_in_list.size() + 1);
		tag_item.setTag_size(String.valueOf(tag_item.getTag_describe()
				.toCharArray().length));
		isScaned = true;
		saveTag();
		Constants.current_card = null;
		Constants.actions_in_list = new ArrayList<ActionItem>();
		startMainActivity();
	}

	/**
	 * 回到主界面
	 */
	private void startMainActivity() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
}
