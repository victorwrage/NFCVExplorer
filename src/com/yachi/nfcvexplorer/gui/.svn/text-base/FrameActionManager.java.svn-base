package com.yachi.nfcvexplorer.gui;

import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.gui.FrameDialogEditAction.IEditActionListtener;
import com.yachi.nfcvexplorer.gui.FrameDialogSelectAction.ISelectActionListtener;

import com.yachi.nfcvexplorer.gui.adapter.ActionAdapter;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 事件管理的界面
 * 
 * @author xiaoyl
 * @date 2013-07-15
 */
public class FrameActionManager extends BaseActivity implements
		OnClickListener, IEditActionListtener,ISelectActionListtener, OnItemClickListener {
	private ListView listView;
	private ActionAdapter adapter;
	private ActionItem currentSelectItem;
	private int currentSelectPosition;
	private EditText name_et;
	private ActionMode amode;
	private TagsDbHelper db;
	/** 事件编辑对话框 */
	FrameDialogEditAction fragment5 ;
	/** 事件选择对话框 */
	private FrameDialogSelectAction fragment4;
	/** 来自热门标签界面 */
	private boolean isShutCut = false;

	@Override
	public void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.actionmanager_frame_layout);
		db = TagsDbHelper.getInstance(this);
		name_et = (EditText) findViewById(R.id.tag_name_etv);
		listView = (ListView) findViewById(R.id.tag_list);
		adapter = new ActionAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		int count = Constants.tag_count + 1;
		name_et.setText("标记 " + count);
		name_et.setSelection(3 + String.valueOf(count).length());

		LinearLayout newAction = (LinearLayout) findViewById(R.id.add_action_lay);
		newAction.setOnClickListener(this);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("新标签");
		invalidateOptionsMenu();
	}

	@Override
	public void onResume() {
		super.onResume();
		Intent intent = getIntent();
		if (intent.getBooleanExtra(Constants.INTENT_EXTRA, false)) {
			isShutCut = true;
			showEdit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case 0:
			if (Constants.actions_in_list.size() == 0) {
				NFCUtils.toast(FrameActionManager.this, "没有加入事件");
				return true;
			}
			Constants.tag_name = name_et.getText().toString();
			startTagSave();
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "下一步")
				.setIcon(R.drawable.ic_cab_done)
				.setTitleCondensed("下一步")
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 新建TAG的事件响应接口
	 * 
	 */
	public static abstract interface IActionManageListtener {
		/** 新建ACTION */
		public abstract void OnAddAction();

		/** 保存ACTION */
		public abstract void OnSaveAction();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.add_action_lay:
			showSelect();
			break;
		}
	}

	/**
	 * 显示编辑页面
	 */
	private void showEdit() {
		fragment5 = new FrameDialogEditAction();
		fragment5.show(this.getSupportFragmentManager(), "");
	}

	/** 事件选择界面 */
	private void showSelect() {
		fragment4 = new FrameDialogSelectAction();
		fragment4.show(getSupportFragmentManager(), "");
	}

	/** 标签保存界面 */
	private void startTagSave() {
		Intent intent = new Intent(this, FrameTagSave.class);
		if (isShutCut) {
			intent.putExtra(Constants.INTENT_EXTRA, true);
			finish();
		}
		startActivity(intent);
	}
    /**
     * 打开事件编辑模式
     * @author xiaoyl
     *
     */
	private final class ActionModeAction implements ActionMode.Callback {
		private ActionModeAction() {
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		/*	menu.add(0, 0, 0, "向上").setIcon(R.drawable.ic_action_arrow_top);
			menu.add(0, 1, 1, "向下").setIcon(R.drawable.ic_action_arrow_bottom);*/
			menu.add(0, 2, 2, "删除").setIcon(R.drawable.ic_action_trash);
			return true;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch (item.getItemId()) {
			case 0:
				if (currentSelectPosition == 0)
					break;
				Constants.actions_in_list.set(currentSelectPosition,
						Constants.actions_in_list
								.get(currentSelectPosition - 1));
				Constants.actions_in_list.set(currentSelectPosition - 1,
						currentSelectItem);
				break;
			case 1:
				if (currentSelectPosition == Constants.actions_in_list.size() - 1)
					break;
				Constants.actions_in_list.set(currentSelectPosition,
						Constants.actions_in_list
								.get(currentSelectPosition + 1));
				Constants.actions_in_list.set(currentSelectPosition + 1,
						currentSelectItem);
				break;
			case 2:
				if (currentSelectPosition >= Constants.actions_in_list.size())
					break;
				Constants.actions_in_list.remove(currentSelectPosition);
				amode.finish();
				break;
			}
			adapter.notifyDataSetChanged();
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {

		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		currentSelectPosition = arg2;
		currentSelectItem = Constants.actions_in_list.get(arg2);
		amode = startActionMode(new ActionModeAction());
	}

	@Override
	public void OnActionEdited() {
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
		if (fragment5 != null) {
			fragment5.dismiss();
		}
		if (fragment4 != null) {
			fragment4.dismiss();
		}
	}

	@Override
	public void OnActionItemSelected() {
		showEdit();
	}

	@Override
	public void OnSelectDialogDismiss() {
		
	}

}
