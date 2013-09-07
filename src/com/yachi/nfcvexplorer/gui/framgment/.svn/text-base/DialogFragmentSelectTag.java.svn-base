package com.yachi.nfcvexplorer.gui.framgment;

import com.yachi.library_yachi.gui.DialogFragmentBaseSherlock;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.gui.adapter.TagAdapterForSelect;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
/**
 * 标签开关选择对话框
 * @author xiaoyl
 * @date 2013-07-22
 */
public class DialogFragmentSelectTag extends DialogFragmentBaseSherlock implements OnItemClickListener   {
	ISelectTagListtener callBack;
	ListView listView;
	TagAdapterForSelect adapter;
	int position;

    public DialogFragmentSelectTag(int position){
    	this.position = position;
    }
    
    @Override
	public void onAttach(Activity activity) {
		try {
			callBack = (ISelectTagListtener) activity;
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
		View view = inflater.inflate(R.layout.switchselect_frame_layout,
				container, false);
		
		listView = (ListView) view.findViewById(R.id.action_list);
		switch(position){
		case 0:
			adapter = new TagAdapterForSelect(getActivity(),Constants.switch_item1);
			break;
		case 1:
			adapter = new TagAdapterForSelect(getActivity(),Constants.switch_item2);
			break;
		}
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch(position){
		case 0:
			Constants.switch_item1 = adapter.getItem(arg2);
			break;
		case 1:
			Constants.switch_item2 = adapter.getItem(arg2);
			break;
		}	
		callBack.OnTagItemSelected();
		DialogFragmentSelectTag.this.dismiss();
	}

	/**
	 * 选择开关的事件响应接口
	 * 
	 */
	public static abstract interface ISelectTagListtener {
		/** 选中了某一条Tag */
		public abstract void OnTagItemSelected();

	}
}
