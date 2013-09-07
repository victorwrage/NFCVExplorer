package com.yachi.nfcvexplorer.gui.framgment;

import java.util.ArrayList;

import com.yachi.library_yachi.gui.DialogFragmentBaseSherlock;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionChildItem;
import com.yachi.nfcvexplorer.bean.ActionGroupItem;
import com.yachi.nfcvexplorer.control.ENUM_ACTION_TYPE;
import com.yachi.nfcvexplorer.gui.adapter.ActionSelectExpandableApdater;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;


/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-17
 * 
 * @file 事件选择对话框
 * 
 */
public class DialogFragmentSelectAction extends DialogFragmentBaseSherlock implements
		OnClickListener, OnChildClickListener {
	private ArrayList<ActionGroupItem> group_items;
	private ArrayList<ArrayList<ActionChildItem>> child_items;
	private ArrayList<ENUM_ACTION_TYPE> actionList;
	ExpandableListView listView;
	ActionSelectExpandableApdater adapter;
	private ISelectActionListtener callBack;
	private Button nextBtn;

	@Override
	public void onAttach(Activity activity) {
		try {
			callBack = (ISelectActionListtener) activity;
		} catch (ClassCastException e) {
			LogUtil.e(e.toString());
		}
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initItems();
		setStyle(STYLE_NO_TITLE, 0);
	}
	
	/**
	 * 初始化事件条目
	 */
	private void initItems() {
		actionList = new ArrayList<ENUM_ACTION_TYPE>();
		group_items = new ArrayList<ActionGroupItem>();
		child_items = new ArrayList<ArrayList<ActionChildItem>>();
		int size = ActionGroupItem.group_titles.length;
		for (int s = 0; s < size; s++) {
			group_items.add(new ActionGroupItem(
					ActionGroupItem.group_titles[s],
					ActionGroupItem.group_icons[s]));
			ArrayList<ActionChildItem> temp_child = new ArrayList<ActionChildItem>();

			for (int t = 0; t < ActionChildItem.child_titles[s].length; t++) {
				temp_child.add(new ActionChildItem(
						ActionChildItem.child_titles[s][t],
						ActionChildItem.child_type[s][t]));
			}
			child_items.add(temp_child);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.actionselect_frame_layout,
				container, false);
		nextBtn = (Button) view.findViewById(R.id.action_next_btn);
		Button cancelBtn = (Button) view.findViewById(R.id.action_cancel_btn);
		listView = (ExpandableListView) view.findViewById(R.id.action_list);
		adapter = new ActionSelectExpandableApdater(getActivity(), group_items,
				child_items);
		listView.setAdapter(adapter);
		listView.setOnChildClickListener(this);
		nextBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		return view;
	}

	/**
	 * 加入ACTION
	 * 
	 * @param type
	 */
	private void addAction(ENUM_ACTION_TYPE type) {
		if (!actionList.contains(type)) {
			actionList.add(type);
		}
	}

	/**
	 * 移除ACTION
	 * 
	 * @param type
	 */
	private void removeAction(ENUM_ACTION_TYPE type) {
		if (actionList.contains(type)) {
			actionList.remove(type);
		}
	}

	/**
	 * 新建TAG的事件响应接口
	 * 
	 */
	public static abstract interface ISelectActionListtener {
		/** 选中了某一条ACTON */
		public abstract void OnActionItemSelected();

		/** 选择Action界面消失 */
		public abstract void OnSelectDialogDismiss();

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.action_next_btn:
			Constants.actionList = actionList;
			callBack.OnActionItemSelected();
			break;
		case R.id.action_cancel_btn:
			dismiss();
			break;
		}
	}

	@Override
	public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2,
			int arg3, long arg4) {
		CheckBox chk = (CheckBox) arg1.findViewById(R.id.child_chk);
		boolean b = !chk.isChecked();
		ActionChildItem item = child_items.get(arg2).get(arg3);
		item.setSelected(b);
		chk.setChecked(b);
		if (b) {
			addAction(item.getActiontype());
		} else {
			removeAction(item.getActiontype());
		}
		nextBtn.setEnabled((actionList.size()>0)?true:false);
		return true;
	}
}
