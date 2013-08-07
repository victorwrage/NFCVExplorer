package com.yachi.nfcvexplorer.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.yachi.nfcvexplorer.MainActivity;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 保存开关的界面
 * 
 * @author xiaoyl
 * @date 2013-07-20
 */
public class FrameSwitchSave extends SherlockActivity {
	private TagsDbHelper db_helper;
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
		setContentView(R.layout.scantag_frame_layout);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("保存开关");
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
	
	/**
	 *构建mPendingIntent
	 */
	public void setUpIntentFilter() {
		IntentFilter localIntentFilter = new IntentFilter(
				"android.nfc.action.TAG_DISCOVERED");
		if (adapter_nfc != null) {
			mPendingIntent = PendingIntent.getActivity(this, 0,
					new Intent(this, super.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
			
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
	public void onResume() {
		super.onResume();
		if (getIntent().getBooleanExtra(Constants.INTENT_EXTRA, false)) {
			isShutCut = true;
		}
		try {
			if (adapter_nfc != null)
				adapter_nfc.enableForegroundDispatch(this, mPendingIntent,
						null, null);
		} catch (Exception localException) {
			LogUtil.e("NFCV", "Error enabling foreground dispatch "
					+ localException.toString());
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		NFCUtils.getCardID(intent);
		if(NFCUtils.writeTag(intent, "nfcv://tags.do/"+Constants.current_card.getCard_uid())){
			processCard();
			NFCUtils.toast(FrameSwitchSave.this.getApplicationContext(), "写入成功");
		}else{
			NFCUtils.toast(FrameSwitchSave.this.getApplicationContext(), "写入失败，但仍然可以工作，需要选择本应用在弹出的窗口");
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		if(adapter_nfc==null)return;
		adapter_nfc.disableForegroundDispatch(FrameSwitchSave.this);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Constants.switch_item1 = null;
			Constants.switch_item2 = null;
			finish();
			return true;
		case 0:
			if (!isScaned) {
				NFCUtils.toast(FrameSwitchSave.this, "未扫描到标签");
				return true;
			}
			saveTag();
			Constants.actions_in_list = new ArrayList<ActionItem>();
			startMainActivity();
			return true;
		}
		return false;
	}


	@Override
	protected void onDestroy() {
		Constants.switch_item1 = null;
		Constants.switch_item2 = null;
		super.onDestroy();
	}
    

	/**
	 * 保存或更新TAG
	 */
	private void saveTag() {
		
		
		if (isShutCut) {
			db_helper.insertTagItem(Constants.switch_item1);
			db_helper.insertTagItem(Constants.switch_item2);
			for(ActionItem a_item:Constants.switch_item1.getActions()){
				db_helper.insertActionItem(a_item);
			}
			for(ActionItem a_item:Constants.switch_item2.getActions()){
				db_helper.insertActionItem(a_item);
			}
			LogUtil.v("saveSwitch successfull---shutcut"
					+ Constants.switch_item1.getTag_link_param1());
		} else {
			Constants.switch_item1.setTag_link_param1(Constants.switch_item2
					.getTag_create_time());
			Constants.switch_item2.setTag_link_param1(Constants.switch_item1
					.getTag_create_time());
			
			TagItem duplicate_item = db_helper.getCardByParam1(Constants.switch_item1.getTag_create_time());
			
			if(duplicate_item!=null){
				duplicate_item.setTag_link_param1(null);
				db_helper.updateTagItem(duplicate_item);
			}
			TagItem duplicate_item2 = db_helper.getCardByParam1(Constants.switch_item2.getTag_create_time());
			if(duplicate_item2!=null){
				duplicate_item2.setTag_link_param1(null);
				db_helper.updateTagItem(duplicate_item2);
			}
			
			db_helper.updateTagItem(Constants.switch_item1);
			db_helper.updateTagItem(Constants.switch_item2);
			
		}

		Constants.switch_item1 = null;
		Constants.switch_item2 = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "保存")
				.setIcon(R.drawable.ic_cab_done)
				.setTitleCondensed("保存")
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 回到主界面
	 */
	private void startMainActivity() {
		finish();
		startActivity(new Intent(this, MainActivity.class));
	}

    /**
     * 处理卡信息
     */
	protected void processCard() {
		if (Constants.current_card != null) {
			tip_tv.setText("扫描成功");
			Constants.switch_item1.setTag_number(Constants.current_card
					.getCard_uid());
			for(ActionItem a_item:Constants.switch_item1.getActions()){
				a_item.setCard_id(Constants.switch_item1.getTag_number());
			}
			Constants.switch_item2.setTag_number(Constants.current_card
					.getCard_uid());
			for(ActionItem a_item:Constants.switch_item2.getActions()){
				a_item.setCard_id(Constants.switch_item2.getTag_number());
			}
			isScaned = true;
			saveTag();
			Constants.actions_in_list = new ArrayList<ActionItem>();
			startMainActivity();
		} else {
			if (tip_tv == null)
				return;
			isScaned = false;
			tip_tv.setText("扫描失败，请重试!");
		}
		
	}
}
