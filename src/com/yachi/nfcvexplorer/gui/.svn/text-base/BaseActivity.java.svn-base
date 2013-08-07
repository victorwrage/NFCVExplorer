package com.yachi.nfcvexplorer.gui;

import java.util.ArrayList;

import android.app.PendingIntent;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.graphics.Canvas;

import android.net.Uri;
import android.nfc.NfcAdapter;

import android.nfc.tech.MifareClassic;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.KeyEvent;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import com.yachi.nfcvexplorer.MainActivity;
import com.yachi.nfcvexplorer.NFCApplication;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.control.DoubleConfirm;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.gui.frame.ListFragmentMenuSliding;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;

/**
 * 界面的基类
 * 
 * @author xiaoyl
 * @date 2013-07-10
 */
public class BaseActivity extends SlidingFragmentActivity {
	private DoubleConfirm double_c;
	ListFragmentMenuSliding mFrag;
	SlidingMenu slidingMenu;

	private TagsDbHelper db_helper;

	/** 双击事件 */
	private DoubleConfirm.DoubleConfirmEvent doubleConfirmEvent = new DoubleConfirm.DoubleConfirmEvent() {
		public void doSecondConfirmEvent() {
			NFCApplication.getInstance().exitApplication();
		}

		public int getFirstConfirmTipsId() {
			return R.string.msg_exit;
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.activity_behind_lay);
		getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		NFCApplication.getInstance().addActivitys(this);
		db_helper = TagsDbHelper.getInstance(getApplicationContext());
		initTags();
		this.double_c = new DoubleConfirm();
		this.double_c.setEvent(this.doubleConfirmEvent);

		if (savedInstanceState == null) {
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			mFrag = new ListFragmentMenuSliding();
			ft.replace(R.id.container_behind, mFrag);
			ft.commit();
		} else {
			mFrag = (ListFragmentMenuSliding) getSupportFragmentManager()
					.findFragmentById(R.id.container_behind);
		}

		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
		int width = 2 * localDisplayMetrics.widthPixels / 5;
		LogUtil.v("width_screen" + width);
		this.slidingMenu = getSlidingMenu();
		this.slidingMenu.setMode(SlidingMenu.RIGHT);
		this.slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		this.slidingMenu.setShadowDrawable(R.drawable.shadow);
		this.slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		this.slidingMenu.setFadeDegree(0.35F);
		this.slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		this.slidingMenu.setBehindCanvasTransformer(new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);
			}
		});
		this.slidingMenu.setBehindWidth(width);
		invalidateOptionsMenu();
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.bg_tab));
		
		if (Constants.firstEnter) {
			Constants.firstEnter = false;
		}
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

	public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
		if (!(this instanceof MainActivity))
			return super.onKeyDown(paramInt, paramKeyEvent);
		this.double_c.onKeyPressed(paramKeyEvent, this);
		return false;
	}
}
