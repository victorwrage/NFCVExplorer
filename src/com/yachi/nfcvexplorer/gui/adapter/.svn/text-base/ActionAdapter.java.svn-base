package com.yachi.nfcvexplorer.gui.adapter;

import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.utils.Constants;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
/**
 * TAG的事件适配器
 * @author xiaoyl
 * @date 2013-07-15
 */
public class ActionAdapter extends BaseAdapter  {
	private Context context;

	public ActionAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		return Constants.actions_in_list.size();
	}

	@Override
	public ActionItem getItem(int arg0) {
			return Constants.actions_in_list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup container) {
		ViewHolder holder = null;
		ActionItem item = Constants.actions_in_list.get(position);
		if (position < Constants.actions_in_list.size()) {
			if (view == null) {
				holder = new ViewHolder();
				view = initView(holder, item,position);
			} else {
				if (view instanceof RelativeLayout) {
					holder = (ViewHolder) view.getTag();
					holder.action_detail.setText(item.getDetail());
					holder.action_enable.setChecked(item.getAction_enable());
				} else {
					holder = new ViewHolder();
					view = initView(holder, item,position);
				}
			}
		
		}
		return view;
	}
	/**
	 * 初始化View
	 * @return
	 */
	private View initView(ViewHolder holder, ActionItem item,int position) {
		View view = View.inflate(context,R.layout.action_item_lay, null);
		holder.action_detail = (TextView) view
				.findViewById(R.id.action_detail_tv);
		holder.action_enable = (Switch) view
				.findViewById(R.id.action_switch);
		holder.action_detail.setText(item.getDetail());
		holder.action_enable.setChecked(item.getAction_enable());
	
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
