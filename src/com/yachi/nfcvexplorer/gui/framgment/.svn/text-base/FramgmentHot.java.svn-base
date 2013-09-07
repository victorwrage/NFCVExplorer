package com.yachi.nfcvexplorer.gui.framgment;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.control.ENUM_ACTION_TYPE;
import com.yachi.nfcvexplorer.control.IFragment;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.gui.ActivityActionManager;
import com.yachi.nfcvexplorer.gui.ActivitySwitchSave;
import com.yachi.nfcvexplorer.gui.ActivityTagSave;
import com.yachi.nfcvexplorer.utils.Constants;

/**
 * 热门标签界面（Fragment）
 * 
 * @author xiaoyl
 * @date 2013-07-15
 */
public class FramgmentHot extends Fragment implements IFragment,
		OnClickListener {
	private TagsDbHelper db;
	private String title = "热门标签";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.hottag_frame_layout, container,
				false);
        
	
		LinearLayout home = (LinearLayout) view.findViewById(R.id.hot_home_lay);
		LinearLayout office = (LinearLayout) view.findViewById(R.id.hot_office_lay);
		LinearLayout car = (LinearLayout) view.findViewById(R.id.hot_car_lay);
		LinearLayout bed = (LinearLayout) view.findViewById(R.id.hot_bed_lay);
		LinearLayout wifi = (LinearLayout) view.findViewById(R.id.hot_wifi_lay);
		LinearLayout light = (LinearLayout) view
				.findViewById(R.id.hot_light_lay);
		LinearLayout switch_light = (LinearLayout) view
				.findViewById(R.id.hot_switch_light_lay);
		LinearLayout switch_wifi = (LinearLayout) view
				.findViewById(R.id.hot_switch_wifi_lay);
		

		home.setOnClickListener(this);
		office.setOnClickListener(this);
		car.setOnClickListener(this);
		bed.setOnClickListener(this);
		wifi.setOnClickListener(this);
		light.setOnClickListener(this);
		switch_light.setOnClickListener(this);
		switch_wifi.setOnClickListener(this);
		db = TagsDbHelper.getInstance(getActivity());
		return view;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public void onClick(View arg0) {
		ActionItem item1 = new ActionItem();
		ActionItem item2 = new ActionItem();
		ActionItem item3 = new ActionItem();
		TagItem tag_item1 = new TagItem();
		TagItem tag_item2 = new TagItem();
		switch (arg0.getId()) {
		case R.id.hot_home_lay:// 家里

			item1.setId(new Random().nextLong());
			item1.setAction_type(ActionItem.ACTION_WIFI);
			item1.setAction_switch(true);
			item1.setDetail(ENUM_ACTION_TYPE.FUNCTION_WIFI.getDescribePre() + "开启");

			item2.setId(new Random().nextLong());
			item2.setAction_type(ActionItem.ACTION_RING_TYPE);
			item2.setRing_type("2");
			item2.setDetail(ENUM_ACTION_TYPE.FUNCTION_RINGTYPE.getDescribePre()
					+ "普通模式");
			Constants.actions_in_list = new ArrayList<ActionItem>();
			Constants.actions_in_list.add(item1);
			Constants.actions_in_list.add(item2);
			startActionSaveActivity();
			break;
		case R.id.hot_office_lay:// 办公室
			item1.setId(new Random().nextLong());
			item1.setAction_type(ActionItem.ACTION_WIFI);
			item1.setAction_switch(true);
			item1.setDetail(ENUM_ACTION_TYPE.FUNCTION_WIFI.getDescribePre() + "开启");

			item2.setId(new Random().nextLong());
			item2.setAction_type(ActionItem.ACTION_RING_TYPE);
			item2.setRing_type("0");
			item2.setDetail(ENUM_ACTION_TYPE.FUNCTION_RINGTYPE.getDescribePre()
					+ "静音模式");
			Constants.actions_in_list = new ArrayList<ActionItem>();
			Constants.actions_in_list.add(item1);
			Constants.actions_in_list.add(item2);
			startActionSaveActivity();
			break;
		case R.id.hot_car_lay:// 汽车
			item1.setId(new Random().nextLong());
			item1.setAction_type(ActionItem.ACTION_WIFI);
			item1.setAction_switch(false);
			item1.setDetail(ENUM_ACTION_TYPE.FUNCTION_WIFI.getDescribePre() + "关闭");

			item2.setId(new Random().nextLong());
			item2.setAction_type(ActionItem.ACTION_OPEN_APPLICATION);
			item2.setPackage_name("com.google.android.apps.maps");
			item2.setDetail(ENUM_ACTION_TYPE.FUNCTION_APPLICATION.getDescribePre()
					+ "(地图)");

			item3.setId(new Random().nextLong());
			item3.setAction_type(ActionItem.ACTION_RING_TYPE);
			item3.setRing_type("2");
			item3.setDetail(ENUM_ACTION_TYPE.FUNCTION_RINGTYPE.getDescribePre()
					+ "普通模式");
			Constants.actions_in_list = new ArrayList<ActionItem>();
			Constants.actions_in_list.add(item1);
			Constants.actions_in_list.add(item2);
			Constants.actions_in_list.add(item3);
			startActionSaveActivity();
			break;
		case R.id.hot_bed_lay:// 卧室
			item1.setId(new Random().nextLong());
			item1.setAction_type(ActionItem.ACTION_WIFI);
			item1.setAction_switch(false);
			item1.setDetail(ENUM_ACTION_TYPE.FUNCTION_WIFI.getDescribePre() + "关闭");

			item2.setId(new Random().nextLong());
			item2.setAction_type(ActionItem.ACTION_RING_TYPE);
			item2.setRing_type("0");
			item2.setDetail(ENUM_ACTION_TYPE.FUNCTION_RINGTYPE.getDescribePre()
					+ "静音模式");
			Constants.actions_in_list = new ArrayList<ActionItem>();
			Constants.actions_in_list.add(item1);
			Constants.actions_in_list.add(item2);
			startActionSaveActivity();
			break;
		case R.id.hot_light_lay:// 应用
			Constants.actionList = new ArrayList<ENUM_ACTION_TYPE>();
			Constants.actionList.add(ENUM_ACTION_TYPE.FUNCTION_APPLICATION);
			startActionManagerActivity();
			break;
		case R.id.hot_wifi_lay:// 电话
			Constants.actionList = new ArrayList<ENUM_ACTION_TYPE>();
			Constants.actionList.add(ENUM_ACTION_TYPE.FUNCTION_DIAL);
			startActionManagerActivity();
			break;
		case R.id.hot_switch_wifi_lay:// WIFI
			item1.setId(new Random().nextLong());
			item1.setAction_type(ActionItem.ACTION_WIFI);
			item1.setAction_switch(false);
			item1.setDetail(ENUM_ACTION_TYPE.FUNCTION_WIFI.getDescribePre() + "关闭");
			tag_item1.addAction(item1);
			builtTagItem(tag_item1, 1);

			item2.setId(new Random().nextLong());
			item2.setAction_type(ActionItem.ACTION_WIFI);
			item2.setAction_switch(true);
			item2.setDetail(ENUM_ACTION_TYPE.FUNCTION_WIFI.getDescribePre() + "开启");
			tag_item2.addAction(item2);
			builtTagItem(tag_item2, 2);

			tag_item1.setTag_link_param1(tag_item2.getTag_create_time());
			tag_item2.setTag_link_param1(tag_item1.getTag_create_time());
			Constants.switch_item1 = tag_item1;
			Constants.switch_item2 = tag_item2;

			startSwitchSaveActivity();
			break;
		case R.id.hot_switch_light_lay:// 声音
			item1.setId(new Random().nextLong());
			item1.setAction_type(ActionItem.ACTION_RING_TYPE);
			item1.setRing_type("0");
			item1.setDetail(ENUM_ACTION_TYPE.FUNCTION_RINGTYPE.getDescribePre()
					+ "静音模式");
			tag_item1.addAction(item1);
			builtTagItem(tag_item1, 1);

			item2.setId(new Random().nextLong());
			item2.setAction_type(ActionItem.ACTION_RING_TYPE);
			item2.setRing_type("2");
			item2.setDetail(ENUM_ACTION_TYPE.FUNCTION_RINGTYPE.getDescribePre()
					+ "普通模式");
			tag_item2.addAction(item2);
			builtTagItem(tag_item2, 2);

			tag_item1.setTag_link_param1(tag_item2.getTag_create_time());
			tag_item2.setTag_link_param1(tag_item1.getTag_create_time());
			Constants.switch_item1 = tag_item1;
			Constants.switch_item2 = tag_item2;

			startSwitchSaveActivity();
			break;
		}
	}

	/**
	 * 创建TagItem
	 * 
	 * @param tag_item
	 * @param para
	 */
	private void builtTagItem(TagItem tag_item, int para) {

		tag_item.setId(new Random().nextLong());
		final Calendar c = Calendar.getInstance();
		tag_item.setTag_create_time(c.get(Calendar.YEAR) + "_"
				+ (c.get(Calendar.MONTH) + 1) + "_"
				+ c.get(Calendar.DAY_OF_MONTH) + "_"
				+ c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE)
				+ "_" + c.get(Calendar.SECOND) + "_"
				+ (c.get(Calendar.MILLISECOND) + para));
		StringBuffer str = new StringBuffer();
		for (ActionItem item : tag_item.getActions()) {
			str.append("(");
			str.append(item.getDetail());
			str.append(")");
			item.setCard_id("000000000");
			item.setCreate_time(tag_item.getTag_create_time());
		}
		tag_item.setTag_describe(str.toString());
		tag_item.setTag_number("0000000000");
		tag_item.setTag_name("标签 " + (Constants.tags_in_list.size() + para));
		tag_item.setTag_priority(Constants.tags_in_list.size() + para);
		tag_item.setTag_size(String.valueOf(tag_item.getTag_describe()
				.toCharArray().length));

	}

	/**
	 * 打开Action管理界面
	 */
	private void startActionManagerActivity() {
		Intent intent = new Intent(getActivity(), ActivityActionManager.class);
		intent.putExtra(Constants.INTENT_EXTRA, true);
		startActivity(intent);
	}

	/**
	 * 打开开关保存界面
	 */
	private void startSwitchSaveActivity() {
		Intent intent = new Intent(getActivity(), ActivitySwitchSave.class);
		intent.putExtra(Constants.INTENT_EXTRA, true);
		startActivity(intent);
	}

	/**
	 * 打开Action保存界面
	 */
	private void startActionSaveActivity() {
		Intent intent = new Intent(getActivity(), ActivityTagSave.class);
		intent.putExtra(Constants.INTENT_EXTRA, true);
		startActivity(intent);
	}

}
