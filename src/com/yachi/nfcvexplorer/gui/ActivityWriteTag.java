package com.yachi.nfcvexplorer.gui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.view.MenuItem;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;
import com.yachi.nfcvexplorer.utils.NdefTagWriterUtil;

/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-08-09
 * 
 * @file 写入基本标签的界面
 * 
 */
public class ActivityWriteTag extends BaseActivity {
	private TagsDbHelper db_helper;
	private TextView tip_tv;
	/** NFC适配器 */
	private NfcAdapter adapter_nfc;
	private boolean isNfcAvailable = true;

	private IntentFilter[] mFilters;
	private PendingIntent mPendingIntent;
	private String[][] mTechLists;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.e("WriteTagActivity----onCreate");
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
		LogUtil.e("WriteTagActivity----onResume");
		super.onResume();

		try {
			if (adapter_nfc != null) {
				adapter_nfc.enableForegroundDispatch(ActivityWriteTag.this,
						mPendingIntent, null, null);
			}
		} catch (Exception localException) {
			LogUtil.e("NFCV", "Error enabling foreground dispatch "
					+ localException.toString());
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		LogUtil.e("WriteTagActivity----onNewIntent");
		NdefMessage message = null;
		try {
			message = new NdefMessage(new byte[0]);
		} catch (FormatException e) {
			e.printStackTrace();
		}
		switch (Constants.write_type) {
		case WRITE_WEBSITE:
			message = NdefTagWriterUtil
					.ndefMessageForUri(Constants.write_text_array.get(0));
			break;
		case WRITE_DIAL_NUMBER:
			message = NdefTagWriterUtil.ndefMessageForPhone(
					Constants.write_text_array.get(0),
					Constants.write_text_array.get(1),
					Constants.write_text_array.get(2));
			break;
		case WRITE_CONTACT:
			message = NdefTagWriterUtil
					.ndefMessageForVcard(Constants.write_text_array.get(0),Constants.write_text_array.get(1));
			break;
		case WRITE_TEXT:
			message = NdefTagWriterUtil.ndefMessageForText(
					Constants.write_text_array.get(0), "");
			break;
		case WRITE_EMAIL:
			message = NdefTagWriterUtil.ndefMessageForEmail(
					Constants.write_text_array.get(0),
					Constants.write_text_array.get(1),
					Constants.write_text_array.get(2));
			break;
		}

		if (NFCUtils.writeTagByNdefMessage(intent, message)) {
			NFCUtils.toast(ActivityWriteTag.this.getApplicationContext(), "写入成功");
			LogUtil.v("WriteTagActivity---success");
			finish();
		}else{
			NFCUtils.toast(ActivityWriteTag.this.getApplicationContext(), "写入失败");
		}
	}

	@Override
	protected void onPause() {
		LogUtil.e("WriteTagActivity----onStop");
		super.onPause();
		if (adapter_nfc != null) {
			adapter_nfc.disableForegroundDispatch(ActivityWriteTag.this);
		}
	}
}
