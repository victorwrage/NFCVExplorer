
package com.yachi.nfcvexplorer.gui.framgment;

import com.yachi.library_yachi.gui.DialogFragmentBaseSherlock;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.gui.ActivityActionManager;
import com.yachi.nfcvexplorer.gui.ActivitySwitchManager;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/** 
 * @ClassName:	DialogFragmentAddTag 
 * @Description:TODO(新建界面) 
 * @author:	xiaoyl
 * @date:	2013-07-20 下午6:38:07 
 *  
 */
public class DialogFragmentAddTag extends DialogFragmentBaseSherlock implements
		OnClickListener {
	private IAddTagListtener callBack;

	@Override
	public void onAttach(Activity activity) {

		try {
			callBack = (IAddTagListtener) activity;
		} catch (ClassCastException e) {
			LogUtil.e(e.toString());
		}

		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.addtag_frame_layout, container,
				false);
		LinearLayout newTag = (LinearLayout) view
				.findViewById(R.id.add_tag_lay);
		LinearLayout newSwitchTag = (LinearLayout) view
				.findViewById(R.id.add_switch_lay);
		LinearLayout newWriteTag = (LinearLayout) view
				.findViewById(R.id.add_write_lay);
		LinearLayout cancelTag = (LinearLayout) view
				.findViewById(R.id.cancel_tag_lay);
		newTag.setOnClickListener(this);
		cancelTag.setOnClickListener(this);
		newSwitchTag.setOnClickListener(this);
		newWriteTag.setOnClickListener(this);
		return view;
	}

	/**
	 * 新建TAG的事件响应接口
	 * 
	 */
	public static abstract interface IAddTagListtener {
		/** 新建TAG */
		public abstract void OnWirteTagSelected();

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.add_tag_lay:
			dismiss();
			startActionManager();
			break;
		case R.id.add_switch_lay:
			dismiss();
			startSwitchManager();
			break;
		case R.id.add_write_lay:
			dismiss();
			startWriteTag();
			break;
		case R.id.cancel_tag_lay:
			dismiss();
			break;
		}
	}

	/**
	 * 打开事件管理界面
	 */
	private void startActionManager() {
		Intent intent = new Intent(getActivity(), ActivityActionManager.class);
		startActivity(intent);
	}

	/**
	 * 打开开关管理界面
	 */
	private void startSwitchManager() {
		Intent intent = new Intent(getActivity(), ActivitySwitchManager.class);
		startActivity(intent);
	}

	/**
	 * 打开写入标签
	 */
	private void startWriteTag() {

		callBack.OnWirteTagSelected();

		/*
		 * try { PackageManager packageManager =
		 * getActivity().getPackageManager(); Intent intent =
		 * packageManager.getLaunchIntentForPackage("com.victor.ndefproject");
		 * getActivity().startActivity(intent); } catch (Exception e) {
		 * launchMarket();
		 * LogUtil.v("OpenApplication--fail----download--from--market");
		 * 
		 * }
		 */
	}

	/**
	 * 打开市场
	 */
	private void launchMarket() {
		Intent intent = new Intent("android.intent.action.VIEW",
				Uri.parse("market://details?id=com.victor.ndefproject"));
		intent.setFlags(268435456);
		try {
			getActivity().startActivity(intent);
			return;
		} catch (ActivityNotFoundException localActivityNotFoundException) {
			NFCUtils.toast(getActivity(), "Failed to launch Play Store.");
		}
	}
}
