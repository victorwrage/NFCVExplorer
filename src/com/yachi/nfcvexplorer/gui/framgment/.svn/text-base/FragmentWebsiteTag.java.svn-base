package com.yachi.nfcvexplorer.gui.framgment;

import java.util.ArrayList;

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
 * 写入网址界面
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class FragmentWebsiteTag extends BaseWriteTagFragment implements OnClickListener {
	EditText editText1;
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		return paramLayoutInflater.inflate(R.layout.write_website, null);
	}

	public void onViewCreated(View paramView, Bundle paramBundle) {
		super.onViewCreated(paramView, paramBundle);
		editText1 = (EditText) getView().findViewById(
				R.id.write_website_address);

		LinearLayout writeTagLay = (LinearLayout) getView().findViewById(
				R.id.website_write_lay);
		writeTagLay.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.website_write_lay:
			Constants.write_type = ENUM_WRITE_TYPE.WRITE_WEBSITE;
			Constants.write_text_array = new ArrayList<String>();
			StringBuffer str = new StringBuffer();
			if(!editText1.getText().toString().startsWith("http://")){
				str.append("http://");
			}
			
			str.append(editText1.getText().toString());
			
			if(!urlFormat(str.toString())){
				NFCUtils.toast(getActivity(), "错误的网址");
				return;
			}
			
			Constants.write_text_array.add(str.toString());
			getActivity().finish();
			FragmentWebsiteTag.this.startActivity(new Intent(FragmentWebsiteTag.this.getActivity(),ActivityWriteTag.class));
			break;
		}
		
	}
	
	/**
	 * 匹配网址
	 * @param paramString
	 * @return
	 */
	private static boolean urlFormat(String paramString)
	  {
	    return NFCUtils.match("http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", paramString);
	  }

	@Override
	public void sentDataToActivity() {
		
	}
}
