package com.yachi.nfcvexplorer.gui.framgment;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.control.ENUM_WRITE_TYPE;
import com.yachi.nfcvexplorer.gui.ActivityWriteTag;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.NFCUtils;

/**
 * 写入邮件界面
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class FragmentEmailTag extends BaseWriteTagFragment implements OnClickListener {
	EditText editText1,editText2,editText3;
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		return paramLayoutInflater.inflate(R.layout.write_email, null);
	}

	public void onViewCreated(View paramView, Bundle paramBundle) {
		super.onViewCreated(paramView, paramBundle);
		editText1 = (EditText) getView().findViewById(
				R.id.write_email_address);
		editText2 = (EditText) getView().findViewById(
				R.id.write_email_subject);
		editText3 = (EditText) getView().findViewById(
				R.id.write_email_content);
		LinearLayout writeTagLay = (LinearLayout) getView().findViewById(
				R.id.email_write_lay);
		writeTagLay.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.email_write_lay:
			if(!emailFormat(editText1.getText().toString())){
				NFCUtils.toast(getActivity(), "错误的Email");
			}
			Constants.write_type = ENUM_WRITE_TYPE.WRITE_EMAIL;
			Constants.write_text_array = new ArrayList<String>();
			Constants.write_text_array.add(editText1.getText().toString());
			Constants.write_text_array.add(editText2.getText().toString());
			Constants.write_text_array.add(editText3.getText().toString());
			getActivity().finish();
			FragmentEmailTag.this.startActivity(new Intent(FragmentEmailTag.this.getActivity(),ActivityWriteTag.class));
			break;
		}
	}
	
	/**
	 * 检测email格式
	 * @param paramString
	 * @return
	 */
	public static boolean emailFormat(String paramString)
	  {
	    return NFCUtils.match("^[a-zA-Z]*[\\w\\.-]*[a-zA-Z0-9][\\w\\.-]*@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$", paramString);
	  }

	@Override
	public void sentDataToActivity() {

		
	}
}
