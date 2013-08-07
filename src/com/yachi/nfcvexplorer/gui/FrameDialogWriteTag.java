package com.yachi.nfcvexplorer.gui;

import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.gui.frame.ActivityContactTag;
import com.yachi.nfcvexplorer.gui.frame.ActivityDailTag;
import com.yachi.nfcvexplorer.gui.frame.ActivityEmailTag;
import com.yachi.nfcvexplorer.gui.frame.ActivityTextTag;
import com.yachi.nfcvexplorer.gui.frame.ActivityWebsiteTag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * 新建写入界面
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class FrameDialogWriteTag extends DialogFragment implements
OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NO_TITLE, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.writetag_lay, container,
				false);
		LinearLayout webTag = (LinearLayout) view
				.findViewById(R.id.write_website_lay);
		LinearLayout contactTag = (LinearLayout) view
				.findViewById(R.id.write_contact_lay);
		LinearLayout dialTag = (LinearLayout) view
				.findViewById(R.id.write_dialnumber_lay);
		LinearLayout writeTag = (LinearLayout) view
				.findViewById(R.id.write_text_lay);
		LinearLayout emailTag = (LinearLayout) view
				.findViewById(R.id.write_email_lay);
		LinearLayout cancelTag = (LinearLayout) view
				.findViewById(R.id.cancel_write_lay);
		
		webTag.setOnClickListener(this);
		contactTag.setOnClickListener(this);
		dialTag.setOnClickListener(this);
		writeTag.setOnClickListener(this);
		emailTag.setOnClickListener(this);
		cancelTag.setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.write_website_lay:
			startTagWriter(0);
			break;
		case R.id.write_contact_lay:
			startTagWriter(1);
			break;
		case R.id.write_dialnumber_lay:
			startTagWriter(2);
			break;
		case R.id.write_text_lay:
			startTagWriter(3);
			break;
		case R.id.write_email_lay:
			startTagWriter(4);
			break;
		case R.id.cancel_write_lay:

			break;
		}
		dismiss();
	}
	
	/**
	 * 打开写入标签界面
	 */
	private void startTagWriter(int fragment) {
		Intent intent = new Intent();
		switch(fragment){
		case 0:
			intent = new Intent(getActivity(),ActivityWebsiteTag.class);
			break;
		case 1:
			intent = new Intent(getActivity(), ActivityContactTag.class);
			break;
		case 2:
			intent = new Intent(getActivity(), ActivityDailTag.class);
			break;
		case 3:
			intent = new Intent(getActivity(), ActivityTextTag.class);
			break;
		case 4:
			intent = new Intent(getActivity(), ActivityEmailTag.class);
			break;
		}
		
		startActivity(intent);
	}
}
