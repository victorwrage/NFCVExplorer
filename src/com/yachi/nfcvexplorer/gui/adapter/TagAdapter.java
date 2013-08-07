package com.yachi.nfcvexplorer.gui.adapter;

import java.util.HashMap;

import com.yachi.nfcvexplorer.R;

import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * TAG适配器
 * 
 * @author xiaoyl
 * @date 2013-07-17
 */
public class TagAdapter extends BaseAdapter {
	private Context context;
	private TagsDbHelper db;
	private HashMap<Integer, Boolean> checkList;

	@SuppressLint("UseSparseArrays")
	public TagAdapter(Context context) {
		this.context = context;
		db = TagsDbHelper.getInstance(context);
		checkList = new HashMap<Integer, Boolean>();
		for (int i = 0; i < Constants.tags_in_list.size(); i++) {
			checkList.put(i, Constants.tags_in_list.get(i).isTag_enable());
		}
	}

	@Override
	public int getCount() {
		return Constants.tags_in_list.size();
	}

	@Override
	public TagItem getItem(int arg0) {
		return Constants.tags_in_list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup container) {
		ViewHolder holder = null;
		final TagItem item = Constants.tags_in_list.get(position);

		if (view == null) {
			holder = new ViewHolder();
			view = initView(holder, item, position);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.action_detail.setText(item.getTag_name() + "使用"
				+ item.getTag_use_count() + "次" + item.getTag_describe());
		holder.action_enable.setChecked(checkList.get(position));

		holder.action_enable.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (checkList.get(position)) {
					checkList.put(position, false);		
					item.setTag_enable(false);
					db.updateTagItem(item);
				} else {
					checkList.put(position, true);
					item.setTag_enable(true);
					db.updateTagItem(item);
				}
				notifyDataSetChanged();
			}
		});

		return view;
	}

	/**
	 * 初始化View
	 * 
	 * @return
	 */
	private View initView(ViewHolder holder, TagItem item, int position) {
		View view = View.inflate(context, R.layout.action_item_lay, null);
		holder.action_detail = (TextView) view
				.findViewById(R.id.action_detail_tv);
		holder.action_enable = (Switch) view.findViewById(R.id.action_switch);
		holder.action_detail.setText(item.getTag_name() + "使用"
				+ item.getTag_use_count() + "次" + item.getTag_describe());
		holder.action_enable.setChecked(checkList.get(position));
		holder.action_enable.setVisibility(View.VISIBLE);
		view.setTag(holder);
		return view;
	}

	private static class ViewHolder {
		/** ACTION 描述 */
		TextView action_detail;
		/** ACTION 是否可用 */
		Switch action_enable;

	}

}
