package com.yachi.nfcvexplorer.receive;

import java.util.ArrayList;

import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.control.ActionManager;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;

import com.yachi.nfcvexplorer.nfc.M1Card;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.app.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 事件提示界面
 * @author xiaoyl
 * @date 2013-07-23
 */
public class ActionLauncher extends Activity {
	private ActionManager actionManager;
	private TagsDbHelper db_helper;


	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.v("ActionLauncher---onCreate");
		db_helper = TagsDbHelper.getInstance(this.getApplicationContext());
		actionManager = ActionManager.getInstance(this.getApplicationContext());

		new TagPayloadRequest().execute();
	}

	 /**
     * 判断事件存在
     */
	private void doJubgement(){
		if (!db_helper.existCardId(Constants.current_card.getCard_uid())) {// 如果数据库存在此ID启动动作
			return;
		}
		TagItem tag_res = db_helper.getCard(Constants.current_card.getCard_uid());
			
		actionManager.addAndStartAction(tag_res);
		ActionLauncher.this.finish();
	}
	
  	/**
	 * 初始化TAG
	 */
	protected void initTags() {
		if (Constants.actions_in_list == null) {
			Constants.actions_in_list = new ArrayList<ActionItem>();
		}
		db_helper.getCards();
	}
	
	/**
	 * 异步处理卡信息
	 * @author xiaoyl
	 *
	 */
	private class TagPayloadRequest extends AsyncTask<Void, Void, Void>
	{
	    public TagPayloadRequest()
	    {

	    }
        
		@Override
		protected Void doInBackground(Void... params) {
			initTags();	
			String payload = NFCUtils.resolveIntentAsAction(getIntent());
			if(payload==null){
				LogUtil.v("ActionLauncher--analyze--id-- fail");
				NFCUtils.getCardID(getIntent());
			}else{
				LogUtil.v("ActionLauncher--analyze--id--");
				Constants.current_card = new M1Card(payload.substring(payload.indexOf("do/")+3,payload.indexOf("com")));
				LogUtil.v("ActionLauncher--analyze--id--"+Constants.current_card.getCard_uid());
			}
			doJubgement();
			return null;

		}
	  }
}
