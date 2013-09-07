package com.yachi.nfcvexplorer.gui;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.yachi.nfcvexplorer.R;

import com.yachi.nfcvexplorer.dao.TagsDbHelper;


import com.yachi.nfcvexplorer.gui.framgment.DialogFragmentSelectTag;

import com.yachi.nfcvexplorer.gui.framgment.DialogFragmentSelectTag.ISelectTagListtener;
import com.yachi.nfcvexplorer.utils.Constants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.LinearLayout;

import android.widget.TextView;


/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-20
 * 
 * @file 开关管理的界面
 * 
 */
public class ActivitySwitchManager extends BaseActivity implements
		OnClickListener ,ISelectTagListtener{
    private TextView switch_tv1 ,switch_tv2;
	private TagsDbHelper db;
	private int current_pos;
	/** 标签选择对话框*/
	private DialogFragmentSelectTag fragment4;

	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.switchmanager_frame_layout);
		db = TagsDbHelper.getInstance(this);

		switch_tv1 = (TextView) findViewById(R.id.switch_tag_tv1);
		switch_tv2 = (TextView) findViewById(R.id.switch_tag_tv2);
		LinearLayout switch_lay1 = (LinearLayout) findViewById(R.id.add_switch_lay1);
		LinearLayout switch_lay2 = (LinearLayout) findViewById(R.id.add_switch_lay2);
		switch_lay1.setOnClickListener(this);
		switch_lay2.setOnClickListener(this);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("新开关");
		invalidateOptionsMenu();
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
			saveSwitch();
			finish();
			return true;
		}
		return false;
	}
	
    /**
     * 保存开关 
     */
	private void saveSwitch() {
		Intent intent = new Intent(this,ActivitySwitchSave.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "保存开关")
				.setIcon(R.drawable.ic_cab_done)
				.setTitleCondensed("保存开关")
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.add_switch_lay1:
			current_pos = 0;
			showSelect(0);
			break;
		case R.id.add_switch_lay2:
			current_pos =1;
			showSelect(1);
			break;
		}
	}
 
    /**事件选择界面*/
	private void showSelect(int postion) {
		fragment4 = new DialogFragmentSelectTag(postion);
		fragment4.show(getSupportFragmentManager(), "");
	}

	@Override
	public void OnTagItemSelected() {
		switch(current_pos){
		case 0:
			switch_tv1.setText(Constants.switch_item1.getTag_name());
			break;
		case 1:
			switch_tv2.setText(Constants.switch_item2.getTag_name());
			break;
		}	
	}
}
