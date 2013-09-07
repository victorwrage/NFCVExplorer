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

/**
 * 写入文本界面
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class FragmentTextTag extends BaseWriteTagFragment implements OnClickListener {
	EditText editText1;
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		return paramLayoutInflater.inflate(R.layout.write_text, null);
	}

	public void onViewCreated(View paramView, Bundle paramBundle) {
		super.onViewCreated(paramView, paramBundle);
		editText1 = (EditText) getView().findViewById(
				R.id.write_text);

		LinearLayout writeTagLay = (LinearLayout) getView().findViewById(
				R.id.text_write_lay);
		writeTagLay.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.text_write_lay:
			Constants.write_type = ENUM_WRITE_TYPE.WRITE_TEXT;
			Constants.write_text_array = new ArrayList<String>();
			Constants.write_text_array.add(editText1.getText().toString());
			getActivity().finish();
			FragmentTextTag.this.startActivity(new Intent(FragmentTextTag.this.getActivity(),ActivityWriteTag.class));
			break;
		}
		
	}

	@Override
	public void sentDataToActivity() {
		
	}
}
