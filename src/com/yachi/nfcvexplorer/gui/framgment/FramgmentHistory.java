package com.yachi.nfcvexplorer.gui.framgment;

import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.control.IFragment;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.gui.adapter.TagAdapter;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 标签管理界面(Fragment)
 * @author xiaoyl
 * @date 2013-07-20
 */
public class FramgmentHistory extends Fragment implements IFragment,
		OnItemClickListener, OnItemLongClickListener {
	private String title = "我的标签";
	TagAdapter adapter;
	TextView tip_tv;
	private int current_pos;
    private IManageTagListtener callBack;
    private TagsDbHelper db;
    ListView listView;
	@Override
	public void onAttach(Activity activity) {
		try {
			callBack = (IManageTagListtener) activity;
		} catch (ClassCastException e) {
			LogUtil.e(e.toString());
		}
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.mytag_frame_layout, null);
		tip_tv = (TextView) view.findViewById(R.id.my_tags_tv);
	    listView = (ListView) view.findViewById(R.id.my_tags_list);
		adapter = new TagAdapter(getActivity());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
		if (adapter.getCount() > 0) {
			tip_tv.setText("您已经有"+adapter.getCount() + "个标签");
		} else {
			tip_tv.setText("您暂时没有标签");
		}
		db = TagsDbHelper.getInstance(getActivity());
		return view;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * 刷新界面
	 */
	public void notifyAdapter() {
		if (adapter == null)
			return;
		adapter = new TagAdapter(getActivity());
		listView.setAdapter(adapter);
		if (adapter.getCount() > 0) {
			tip_tv.setText("您已经有"+adapter.getCount() + "个标签");
		} else {
			tip_tv.setText("您暂时没有标签");
		}
	}
	/**
	 * 删除TAG
	 */
	public void deleteTag() {
		if (Constants.tags_in_list.size() == 0)
			return;
		TagItem t_item = Constants.tags_in_list.get(current_pos);
		LogUtil.v("deleteTag--actioncount"+t_item.getActions());
		for(ActionItem a_item:t_item.getActions()){
			db.deleteActionItem(a_item);
		}
		db.deleteTagItem(t_item);
		Constants.tags_in_list.remove(current_pos);
		notifyAdapter();
	}

	/**
	 * 管理TAG的事件响应接口
	 * 
	 */
	public static abstract interface IManageTagListtener {
		/** 点击TAG */
		public abstract void OnTagSelected();

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		notifyAdapter();
		current_pos = arg2;
		callBack.OnTagSelected();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		return true;
	}

}
