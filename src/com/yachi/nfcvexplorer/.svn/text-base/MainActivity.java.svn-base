package com.yachi.nfcvexplorer;

import java.util.ArrayList;

import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuItem;
import com.yachi.nfcvexplorer.gui.BaseActivity;
import com.yachi.nfcvexplorer.gui.FrameDialogAddTag;
import com.yachi.nfcvexplorer.gui.FrameDialogAddTag.IAddTagListtener;
import com.yachi.nfcvexplorer.gui.FrameDialogWriteTag;

import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.control.ACTION_TYPE;
import com.yachi.nfcvexplorer.gui.FrameMain;
import com.yachi.nfcvexplorer.gui.frame.FrameHistory;
import com.yachi.nfcvexplorer.gui.frame.FrameHistory.IManageTagListtener;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.actionbarsherlock.view.Menu;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends BaseActivity implements IManageTagListtener,IAddTagListtener {
	private static final String PAGE_1 = "page_1";
	/** 主界面的Fragment */
	private FrameMain fragment1;
	private FragmentTransaction ft;
	private ActionMode amode;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fore_lay);
		ft = getSupportFragmentManager().beginTransaction();
		if (savedInstanceState == null) {
			fragment1 = new FrameMain();
			ft.add(R.id.fragment_container, fragment1, PAGE_1);
			ft.commit();
		} else {
			fragment1 = (FrameMain) getSupportFragmentManager()
					.findFragmentByTag(PAGE_1);
		}
	}
	
	@Override
	public void onResume() {
		LogUtil.v("MainActivity---OnResume");
		super.onResume();
		initTags();
		Constants.tag_name = null;
		Constants.actionList = new ArrayList<ACTION_TYPE>();
		Constants.actions_in_list = new ArrayList<ActionItem>();
		FrameHistory f = (FrameHistory) fragment1.getFragment();
		f.notifyAdapter();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 0:
			showCreateType();
			return true;
		case 1:
			toggle();
			return true;
		}
		return false;
	}

	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "新的TAG")
				.setIcon(R.drawable.ic_action_add)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		menu.add(0, 1, 1, "更多")
				.setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_light)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 显示创建类型对话框
	 */
	private void showCreateType() {
		LogUtil.v("step--FrameDialogAddTag  ");
		FrameDialogAddTag fragment2 = new FrameDialogAddTag();
		fragment2.show(getSupportFragmentManager(), "");
	}
	/**
	 * 显示写入类型对话框
	 */
	private void showWriteType() {

		FrameDialogWriteTag fragment2 = new FrameDialogWriteTag();
		fragment2.show(getSupportFragmentManager(), "");
	}

	private final class TagModeAction implements ActionMode.Callback {
		private TagModeAction() {
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			menu.add(0, 0, 0, "删除").setIcon(R.drawable.ic_action_trash)
					.setTitleCondensed("删除");
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
				FrameHistory f = (FrameHistory) fragment1.getFragment();
				f.deleteTag();
				amode.finish();
				break;
			}
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {

		}
	}

	@Override
	public void OnTagSelected() {
		amode = startActionMode(new TagModeAction());
	}


	@Override
	public void OnWirteTagSelected() {
		showWriteType();
	}
	
}
