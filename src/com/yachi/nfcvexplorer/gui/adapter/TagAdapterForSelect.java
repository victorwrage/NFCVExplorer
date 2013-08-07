package com.yachi.nfcvexplorer.gui.adapter;

import java.util.ArrayList;

import com.yachi.nfcvexplorer.bean.TagItem;

import com.yachi.nfcvexplorer.utils.Constants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

/**
 * TAG选择器
 * 
 * @author xiaoyl
 * @date 2013-07-20
 */
public class TagAdapterForSelect extends BaseAdapter {
	private Context context;
	private ArrayList<TagItem> items;

	@SuppressLint("UseSparseArrays")
	public TagAdapterForSelect(Context context, TagItem sample) {
		this.context = context;
		items= new ArrayList<TagItem>();
		if (sample == null) {
			for (int  i =Constants.tags_in_list.size()-1;i>=0;i--) {
				 items.add(Constants.tags_in_list.get(i));
			}
		} else {
			for (int  i =Constants.tags_in_list.size()-1;i>=0;i--) {
				if (Constants.tags_in_list.get(i).getTag_number().equals(sample.getTag_number())) {
					if(Constants.tags_in_list.get(i).getTag_create_time().equals(sample.getTag_create_time()))continue;
					items.add(Constants.tags_in_list.get(i));
				}
			}
		}
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public TagItem getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup container) {
		ViewHolder holder = null;
		TagItem item = items.get(position);

		if (view == null) {
			holder = new ViewHolder();
			view = initView(holder, item, position);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.tag_name.setText(item.getTag_name() +"  "+ item.getTag_describe());
		return view;
	}

	/**
	 * 初始化View
	 * 
	 * @return
	 */
	private View initView(ViewHolder holder, TagItem item, int position) {
		TextView view = new TextView(context);
		view.setMinHeight(100);
		holder.tag_name = view;
		holder.tag_name.setText(item.getTag_name() +"  "+ item.getTag_describe());
		view.setTag(holder);
		return view;
	}

	private static class ViewHolder {
		/** ACTION 描述 */
		TextView tag_name;
	}
}
