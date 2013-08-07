package com.yachi.nfcvexplorer.gui.adapter;

import java.util.ArrayList;

import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionChildItem;
import com.yachi.nfcvexplorer.bean.ActionGroupItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 事件选择适配器
 * @author xiaoyl
 * @date 2013-07-15
 */
public class ActionSelectExpandableApdater extends BaseExpandableListAdapter {
	private Context context;
	private ArrayList<ActionGroupItem> group_items;
	private ArrayList<ArrayList<ActionChildItem>> child_items;
    
	public ActionSelectExpandableApdater(Context context,
			ArrayList<ActionGroupItem> group_items,
			ArrayList<ArrayList<ActionChildItem>> child_items) {
		this.context = context;
		this.group_items = group_items;
		this.child_items = child_items;
		
	}

	@Override
	public ActionChildItem getChild(int arg0, int arg1) {
		return child_items.get(arg0).get(arg1);
	}

	@Override
	public long getChildId(int arg0, int arg1) {
		return 0;
	}

	@Override
	public View getChildView(int g_position, int c_position, boolean arg2,
			View view, ViewGroup arg4) {
		ViewHolder holder = null;
		ActionChildItem item = child_items.get(g_position).get(c_position);

		if (view == null) {
			holder = new ViewHolder();
			view = initChildView(holder, item);
		} else {
			if (view instanceof RelativeLayout) {
				holder = (ViewHolder) view.getTag();
				holder.title.setText(item.getTitle());
				holder.enable.setChecked(item.isSelected());
			} else {
				holder = new ViewHolder();
				view = initChildView(holder, item);
			}
		}
		return view;
	}

	/**
	 * 初始化ChildView视图
	 * 
	 * @return
	 */
	private View initChildView(ViewHolder holder, ActionChildItem item) {
		View view = View.inflate(context, R.layout.action_select_child, null);
		holder.title = (TextView) view.findViewById(R.id.child_title);
		holder.enable = (CheckBox) view.findViewById(R.id.child_chk);
		holder.title.setText(item.getTitle());
		holder.enable.setChecked(item.isSelected());
		view.setTag(holder);
		return view;
	}

	/**
	 * 初始化GroupView视图
	 * 
	 * @return
	 */
	private View initGroupView(ViewHolder holder, ActionGroupItem item) {
		View view = View.inflate(context, R.layout.action_select_group, null);
		holder.title = (TextView) view.findViewById(R.id.group_title);
		holder.icon = (ImageView) view.findViewById(R.id.group_iv);
		holder.title.setText(item.getTitle());
		holder.icon.setImageResource(item.getIcon());
		view.setTag(holder);
		return view;
	}

	@Override
	public int getChildrenCount(int arg0) {
		return child_items.get(arg0).size();
	}

	@Override
	public ActionGroupItem getGroup(int arg0) {
		return group_items.get(arg0);
	}

	@Override
	public int getGroupCount() {
		return group_items.size();
	}

	@Override
	public long getGroupId(int arg0) {
		return 0;
	}

	@Override
	public View getGroupView(int position, boolean arg1, View view,
			ViewGroup arg3) {
		ViewHolder holder = null;
		ActionGroupItem item = getGroup(position);
		if (view == null) {
			holder = new ViewHolder();
			view = initGroupView(holder, item);
		} else {
			if (view instanceof RelativeLayout) {
				holder = (ViewHolder) view.getTag();
				holder.title.setText(item.getTitle());
				holder.icon.setImageResource(item.getIcon());
			} else {
				holder = new ViewHolder();
				view = initGroupView(holder, item);
			}
		}
		return view;
	}

	@Override
	public boolean hasStableIds() {

		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}

	private static class ViewHolder {
		TextView title;
		ImageView icon;
		CheckBox enable;
	}
}
