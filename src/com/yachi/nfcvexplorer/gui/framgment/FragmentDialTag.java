package com.yachi.nfcvexplorer.gui.framgment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.control.ENUM_WRITE_TYPE;
import com.yachi.nfcvexplorer.gui.ActivityWriteTag;
import com.yachi.nfcvexplorer.gui.adapter.ChoiceSpinnerAdapter;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.NFCUtils;
import com.yachi.nfcvexplorer.view.CustomSinnper;

/**
 * 写入电话号码界面
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class FragmentDialTag extends BaseWriteTagFragment implements OnClickListener {
	EditText editText1,editText2;
	CustomSinnper spinner;
	String[] type= {"call","sms"};
	String type_str = type[0];
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		return paramLayoutInflater.inflate(R.layout.write_dail, null);
	}

	public void onViewCreated(View paramView, Bundle paramBundle) {
		super.onViewCreated(paramView, paramBundle);
		 editText1 = (EditText) getView().findViewById(
				R.id.write_dial_number);
		 editText2 = (EditText) getView().findViewById(
					R.id.write_dial_content);
		 spinner= (CustomSinnper) getView().findViewById(
					R.id.phone_spinner);
		 
		 spinner.setOnItemSeletedListener(new com.yachi.nfcvexplorer.view.CustomSinnper.OnItemSeletedListener() {
				@Override
				public void onItemSeleted(AdapterView<?> parent, View view,
						int position, long id) {
					type_str = type[position];
					if(position==0){
						editText2.setVisibility(View.GONE);
					}else{
						editText2.setVisibility(View.VISIBLE);
					}
				}
			});
		 String[] list = getActivity().getResources().getStringArray(R.array.write_phone_type);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.select_dialog_item,
					list);
			spinner.setAdapter(adapter);
		LinearLayout writeTagLay = (LinearLayout) getView().findViewById(
				R.id.dial_write_lay);
		writeTagLay.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.dial_write_lay:
			if(editText1.getText().toString().equals(null)|| editText1.getText().toString().equals("")){
				NFCUtils.toast(getActivity(), "电话号码为必填");
				return;
			}
			Constants.write_type = ENUM_WRITE_TYPE.WRITE_DIAL_NUMBER;
			Constants.write_text_array = new ArrayList<String>();
			Constants.write_text_array.add(editText1.getText().toString());
			Constants.write_text_array.add(type_str);
			if(type_str.equals("sms")){
			   Constants.write_text_array.add(editText2.getText().toString());
			}else{
			   Constants.write_text_array.add("");
			}
			getActivity().finish();
			FragmentDialTag.this.startActivity(new Intent(FragmentDialTag.this.getActivity(),ActivityWriteTag.class));
			break;
		}
		
	}

	@Override
	public void sentDataToActivity() {
		
	}
}
