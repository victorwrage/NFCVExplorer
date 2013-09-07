package com.yachi.nfcvexplorer.gui.framgment;

import com.umeng.fb.FeedbackAgent;
import com.yachi.library_yachi.gui.ListFragmentBasedSherlock;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.gui.ActivityAbout;
import com.yachi.nfcvexplorer.gui.ActivitySetting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 侧滑栏界面
 * @author xiaoyl
 * @date 2013-07-16
 *
 */
public class ListFragmentMenuSliding extends ListFragmentBasedSherlock implements OnItemClickListener {

	private String menus[] = { "设置", "反馈","关于" };
	private int icons[] = { R.drawable.ic_launcher_settings,R.drawable.ic_action_help,
			R.drawable.ic_category_social};

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		MenuAdapter adapter = new MenuAdapter(getActivity());
		adapter.add(new SlidingMenuItem(menus[0],icons[0]));
		adapter.add(new SlidingMenuItem(menus[1],icons[1]));
		adapter.add(new SlidingMenuItem(menus[2],icons[2]));
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.container_lay, null);
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		switch(arg2){
		case 0:
			startPreference();
			break;
		case 1:
			startFeedBack();
			break;
		case 2:
			startHelper();
			break;
		}
		
	}

	/**
	 * 打开设置界面
	 */
	private void startPreference(){
		
		startActivity(new Intent(this.getActivity(),ActivitySetting.class));
	}
	/**
	 * 打开反馈界面
	 */
	private void startFeedBack(){
		FeedbackAgent agent = new FeedbackAgent(getActivity());
	    agent.startFeedbackActivity();

	}
	/**
	 * 打开帮助界面
	 */
	private void startHelper(){
		
		startActivity(new Intent(this.getActivity(),ActivityAbout.class));
	}
	private class MenuAdapter extends ArrayAdapter<SlidingMenuItem>{
   
		public MenuAdapter(Context context) {
			super(context,0);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.behind_row_lay, null);
             
			}
			 ImageView img = (ImageView) convertView.findViewById(R.id.row_icon);
             img.setImageResource(getItem(position).getIcon());
             TextView txv = (TextView) convertView.findViewById(R.id.row_title);
             txv.setText(getItem(position).getName());
			return convertView;
		}
	}
	
	private class SlidingMenuItem{
		private String name;
		private int icon;
		public SlidingMenuItem(String name, int icon_res){
			 this.name = name;
			 this.icon = icon_res;
		}
		public String getName() {
			return name;
		}
		public int getIcon() {
			return icon;
		}

		
	}
}
