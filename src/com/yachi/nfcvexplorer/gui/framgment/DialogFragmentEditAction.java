package com.yachi.nfcvexplorer.gui.framgment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.yachi.library_yachi.gui.DialogFragmentBaseSherlock;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.control.ENUM_ACTION_TYPE;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;
import com.yachi.nfcvexplorer.view.CustomSinnper;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 事件编辑对话框
 * @author xiaoyl
 * @date 2013-07-17
 */
public class DialogFragmentEditAction extends DialogFragmentBaseSherlock implements
		OnClickListener, OnSeekBarChangeListener {
	private IEditActionListtener callBack;
	/** 加载View */
	private static final int ADD_VIEW_SWITCH = 10;
	/** 数据加载完成 */
	private static final int ADD_VIEW_FINISHED = ADD_VIEW_SWITCH + 1;
	private View view;
	private LayoutInflater inflater;
	private LinearLayout container_lay;
	private ProgressBar progressBar;
	private Button trueBtn;

	/** 屏幕亮度 */
	private int temp_screen_light;
	/** 软件信息集合 */
	private List<PackageInfo> softList;
	/** 软件名字集合 */
	private String[] softName;
	/** 需要加载的View */
	private int view_res;
	/** 需要显示的TItle */
	private String title_str;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case ADD_VIEW_SWITCH:

				break;
			case ADD_VIEW_FINISHED:
				addViewList();
				progressBar.setVisibility(View.GONE);
				trueBtn.setEnabled(true);

				break;
			}
		}
	};

	/**
	 * 把所有组件加入布局
	 */
	private void addViewList() {
		for (ENUM_ACTION_TYPE action : Constants.actionList) {
			View v = null;
			title_str = action.getKey();
			switch (action) {
			case FUNCTION_WIFI:

				view_res = R.layout.function_switch_lay;
				v = inflaterView(title_str, R.array.network_switch);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_BLUETOOTH:
				view_res = R.layout.function_switch_lay;
				v = inflaterView(title_str, R.array.network_switch);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_LOCKSCREEN:
				view_res = R.layout.function_switch_lay;
				v = inflaterView(title_str, R.array.screen_switch);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_APPLICATION:
				view_res = R.layout.function_switch_lay;
				v = inflaterView(title_str);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_DIAL:
				view_res = R.layout.function_message_lay;
				v = inflaterTextView(title_str, false);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_MESSAGE:
				view_res = R.layout.function_message_lay;
				v = inflaterTextView(title_str, true);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_RINGTYPE:
				view_res = R.layout.function_switch_lay;
				v = inflaterView(title_str, R.array.ring_type);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_RINGVALUE:
				view_res = R.layout.function_value_lay;
				v = inflaterValueView(title_str, action);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			case FUNCTION_SCREENLIGHT:
				view_res = R.layout.function_value_lay;
				v = inflaterValueView(title_str, action);
				container_lay.addView(v, new ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.WRAP_CONTENT));
				break;
			}
			v.setTag(action);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		try {
			callBack = (IEditActionListtener) activity;
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
		this.inflater = inflater;
		if (Constants.actionList.size() > 5) {
			view = inflater.inflate(R.layout.actionedit_frame_layout_extend,
					container, false);
		} else {
			view = inflater.inflate(R.layout.actionedit_frame_layout_extend,
					container, false);
		}
		trueBtn = (Button) view.findViewById(R.id.edit_true_btn);
		progressBar = (ProgressBar) view.findViewById(R.id.edit_progressbar);

		Button cancelBtn = (Button) view.findViewById(R.id.edit_cancel_btn);
		container_lay = (LinearLayout) view.findViewById(R.id.edit_lay);
		trueBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
		new InitActionThread().start();
		return view;
	}

	/**
	 * 事件编辑界面消息发送接口
	 * 
	 */
	public static abstract interface IEditActionListtener {
		/** 编辑好了A事件 */
		public abstract void OnActionEdited();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.edit_true_btn:
			if (!builtAction()) {
				Constants.actions_in_list = new ArrayList<ActionItem>();
				return;
			}
			callBack.OnActionEdited();

			break;
		case R.id.edit_cancel_btn:
			dismiss();
			break;
		}
	}

	/**
	 * 事件生成
	 */
	private boolean builtAction() {
		for (ENUM_ACTION_TYPE action : Constants.actionList) {
			ActionItem item = new ActionItem();
			item.setId(new Random().nextLong());
			View v = view.findViewWithTag(action);
			switch (action) {
			case FUNCTION_WIFI:
				item.setAction_type(ActionItem.ACTION_WIFI);
				CustomSinnper sp = (CustomSinnper) v
						.findViewById(R.id.function_wifi_spinner);
				boolean b = (Integer)sp.getTag()  == 0 ? true : false;
				item.setAction_switch(b);
				item.setDetail(action.getDescribePre()  + (b ? "开启" : "禁用"));
				break;
			case FUNCTION_BLUETOOTH:
				item.setAction_type(ActionItem.ACTION_BLUETOOTH);
				CustomSinnper sp2 = (CustomSinnper) v
						.findViewById(R.id.function_wifi_spinner);
				boolean b2 = (Integer)sp2.getTag() == 0 ? true : false;
				item.setAction_switch(b2);
				item.setDetail(action.getDescribePre() + (b2 ? "开启" : "禁用"));
				break;
			case FUNCTION_APPLICATION:
				item.setAction_type(ActionItem.ACTION_OPEN_APPLICATION);
				CustomSinnper sp3 = (CustomSinnper) v
						.findViewById(R.id.function_wifi_spinner);
				PackageInfo i = softList.get((Integer)sp3.getTag() );
				String s1 = i.packageName;
				item.setPackage_name(s1);
				item.setDetail(action.getDescribePre()  + "("
						+ softName[(Integer)sp3.getTag() ] + ")");
				break;
			case FUNCTION_DIAL:
				item.setAction_type(ActionItem.ACTION_DIA);
				EditText dial_number = (EditText) v
						.findViewById(R.id.function_message_dial);
				String dial_number_str = dial_number.getText().toString();
				item.setDia_number(dial_number_str);
				item.setDetail(action.getDescribePre()  + "(" + item.getDia_number() + ")");
				if (dial_number_str.isEmpty()) {
					NFCUtils.toast(getActivity(), "请输入电话号码");
					return false;
				}
				break;
			case FUNCTION_MESSAGE:
				item.setAction_type(ActionItem.ACTION_MESSAGE);
				EditText dial_number2 = (EditText) v
						.findViewById(R.id.function_message_dial);
				String dial_number_str2 = dial_number2.getText().toString();
				item.setDia_number(dial_number_str2);
				EditText message = (EditText) v
						.findViewById(R.id.function_message_content);
				String message_str = message.getText().toString();
				item.setMessage_content(message_str);
				item.setDetail(action.getDescribePre()  + "(" + item.getDia_number() + ")");
				if (dial_number_str2.isEmpty()) {
					NFCUtils.toast(getActivity(), "请输入电话号码");
					return false;
				}
				break;
			case FUNCTION_LOCKSCREEN:
				item.setAction_type(ActionItem.ACTION_SCREEN_LOCK);
				CustomSinnper sp4 = (CustomSinnper) v
						.findViewById(R.id.function_wifi_spinner);
				boolean b4 = ((Integer)sp4.getTag()  == 0) ? true : false;
				item.setAction_switch(b4);
				item.setDetail(action.getDescribePre()  + (b4 ? "锁定" : "解锁"));
				break;
			case FUNCTION_RINGTYPE:
				item.setAction_type(ActionItem.ACTION_RING_TYPE);
				CustomSinnper sp5 = (CustomSinnper) v
						.findViewById(R.id.function_wifi_spinner);
				item.setRing_type(String.valueOf( (Integer)sp5.getTag()));
				item.setDetail(action.getDescribePre() + sp5.getText());
				break;
			case FUNCTION_RINGVALUE:
				item.setAction_type(ActionItem.ACTION_RING_VALUE);
				SeekBar seekBar = (SeekBar) v
						.findViewById(R.id.function_value_seekbar);
				item.setRing_value(seekBar.getProgress());
				item.setDetail(action.getDescribePre()  + seekBar.getProgress());
				break;
			case FUNCTION_SCREENLIGHT:
				item.setAction_type(ActionItem.ACTION_SCREEN_LIGHT);
				SeekBar seekBar2 = (SeekBar) v
						.findViewById(R.id.function_value_seekbar);
				item.setScreen_light(seekBar2.getProgress());
				item.setDetail(action.getDescribePre()  + seekBar2.getProgress());
				break;
			}
			Constants.actions_in_list.add(item);
		}
		return true;
	}

	/**
	 * 初始化选择型View
	 * 
	 * @param title
	 * @param array_res
	 * @return
	 */
	private View inflaterView(String title, int array_res) {
		View v = inflater.inflate(view_res, null);
		final CustomSinnper spinner = (CustomSinnper) v
				.findViewById(R.id.function_wifi_spinner);
		spinner.setTag(0);
		spinner.setOnItemSeletedListener(new com.yachi.nfcvexplorer.view.CustomSinnper.OnItemSeletedListener() {

			@Override
			public void onItemSeleted(AdapterView<?> parent, View view,
					int position, long id) {
				spinner.setTag(position);

			}
		});
		String[] list = getActivity().getResources().getStringArray(array_res);
		// ChoiceSpinnerAdapter adapter = new
		// ChoiceSpinnerAdapter(getActivity(), list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.select_dialog_item, list);
		spinner.setAdapter(adapter);
		TextView tv = (TextView) v.findViewById(R.id.function_wifi_tv);
		tv.setText(title);
		return v;
	}

	/**
	 * 初始化化数值View
	 * 
	 * @param title
	 * @param array_res
	 * @return
	 */
	private View inflaterTextView(String title, boolean visible) {
		View v = inflater.inflate(view_res, null);
		EditText dial_tv = (EditText) v
				.findViewById(R.id.function_message_dial);
		dial_tv.setHint("请输入电话号码");
		EditText content_tv = (EditText) v
				.findViewById(R.id.function_message_content);
		content_tv.setHint("请输入短信内容");
		if (!visible) {
			content_tv.setVisibility(View.GONE);
		}
		TextView tv = (TextView) v.findViewById(R.id.function_message_tv);
		tv.setText(title);
		return v;
	}

	/**
	 * 初始化化数值View
	 * 
	 * @param title
	 * @param array_res
	 * @return
	 */
	private View inflaterValueView(String title, ENUM_ACTION_TYPE type) {
		View v = inflater.inflate(view_res, null);
		SeekBar seekBar = (SeekBar) v.findViewById(R.id.function_value_seekbar);
		seekBar.setMax(100);
		switch (type) {
		case FUNCTION_RINGVALUE:
			seekBar.setProgress(NFCUtils.getRingValue(getActivity()));
			break;
		case FUNCTION_SCREENLIGHT:
			temp_screen_light = NFCUtils.getScreenLight(getActivity());
			seekBar.setProgress(temp_screen_light);
			seekBar.setOnSeekBarChangeListener(this);
			break;
			default:
				break;
		}
		TextView tv = (TextView) v.findViewById(R.id.function_value_tv);
		tv.setText(title);
		return v;
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		if (Constants.actionList.contains(ENUM_ACTION_TYPE.FUNCTION_SCREENLIGHT)) {
			NFCUtils.setScreenLight(getActivity(), temp_screen_light, true);
		}
		super.onDismiss(dialog);
	}

	/**
	 * 初始选择性View
	 * 
	 * @param title
	 * @param array_res
	 * @return
	 */
	private View inflaterView(String title) {
		View v = inflater.inflate(view_res, null);

		final CustomSinnper spinner = (CustomSinnper) v
				.findViewById(R.id.function_wifi_spinner);
		spinner.setTag(0);
		spinner.setOnItemSeletedListener(new com.yachi.nfcvexplorer.view.CustomSinnper.OnItemSeletedListener() {

			@Override
			public void onItemSeleted(AdapterView<?> parent, View view,
					int position, long id) {
				spinner.setTag(position);

			}
		});
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.select_dialog_item,
				softName);
		spinner.setAdapter(adapter);
		TextView tv = (TextView) v.findViewById(R.id.function_wifi_tv);
		tv.setText(title);
		return v;
	}

	/**
	 * 初始化事件线程
	 * 
	 * @author xiaoyl
	 */
	private class InitActionThread extends Thread {

		@Override
		public void run() {
			for (ENUM_ACTION_TYPE action : Constants.actionList) {
				switch (action) {
				case FUNCTION_APPLICATION:
					softList = getActivity().getPackageManager()
							.getInstalledPackages(0);
					softName = new String[softList.size()];
					for (int s = 0; s < softList.size(); s++) {
						PackageInfo info = softList.get(s);
						if (getActivity() == null)
							return;
						String str_name = info.applicationInfo.loadLabel(
								getActivity().getPackageManager()).toString();
						softName[s] = str_name;
					}
					break;
				case FUNCTION_WIFI:
					break;
				case FUNCTION_BLUETOOTH:
					break;
					default:
						break;
				}
			}
			handler.sendEmptyMessage(ADD_VIEW_FINISHED);
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (fromUser) {
			NFCUtils.setScreenLight(getActivity(), progress, true);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

}
