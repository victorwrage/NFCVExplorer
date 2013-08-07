package com.yachi.nfcvexplorer.gui.frame;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.control.WRITE_TYPE;
import com.yachi.nfcvexplorer.gui.WriteTagActivity;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;
import com.yachi.nfcvexplorer.utils.NFCUtils;

/**
 * 写入联系人界面
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class FragmentContactTag extends SherlockFragment implements OnClickListener,LoaderManager.LoaderCallbacks<Cursor>  {
	EditText editText1,editText2;
    private  Uri contactUri;
    static final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {Contacts._ID, Contacts.DISPLAY_NAME, Contacts.LOOKUP_KEY };  
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		return paramLayoutInflater.inflate(R.layout.write_contact, null);
	}

	public void onViewCreated(View paramView, Bundle paramBundle) {
		super.onViewCreated(paramView, paramBundle);
		editText1 = (EditText) getView().findViewById(
				R.id.write_contact_name);
		editText2 = (EditText) getView().findViewById(
				R.id.write_contact_number);
		ImageView find = (ImageView) getView().findViewById(
				R.id.write_contact_find);
		LinearLayout writeTagLay = (LinearLayout) getView().findViewById(
				R.id.contact_write_lay);
		writeTagLay.setOnClickListener(this);
		find.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.contact_write_lay:
			if(editText2.getText().toString().equals(null)|| editText2.getText().toString().equals("")){
				NFCUtils.toast(getActivity(), "电话号码为必填");
				return;
			}
			Constants.write_type = WRITE_TYPE.WRITE_CONTACT;
			Constants.write_text_array = new ArrayList<String>();
			Constants.write_text_array.add(editText1.getText().toString());
			Constants.write_text_array.add(editText2.getText().toString());
			getActivity().finish();
			FragmentContactTag.this.startActivity(new Intent(FragmentContactTag.this.getActivity(),WriteTagActivity.class));
			break;
		case R.id.write_contact_find:
			toContact();
			break;
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if ((requestCode != 1) || (resultCode != -1))
		      return;
	    
		    LogUtil.v("onActivityResult"+data.getData());
		    
		    LoaderManager loadManager = getLoaderManager();
		    loadManager.initLoader(0, null, this);
	  
		    contactUri = data.getData();   
		 
	}
	
	private void toContact()
	  {
	    Intent localIntent = new Intent("android.intent.action.PICK");
	    localIntent.setType("vnd.android.cursor.dir/contact");
	    startActivityForResult(localIntent, 1);
	  }

	

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		
		 /*  Constants.write_type = WRITE_TYPE.WRITE_CONTACT;
		   Constants.write_text_array = new ArrayList<String>();
		   Constants.write_text_array.add(arg1.getString(Contacts.DISPLAY_NAME));
			Constants.write_text_array.add(editText2.getText().toString());
*/
			startActivity(new Intent(FragmentContactTag.this.getActivity(),WriteTagActivity.class));
			getActivity().finish();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {

	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		 CursorLoader cl = new CursorLoader(getActivity(), contactUri,
				 CONTACTS_SUMMARY_PROJECTION, null, null, null);
         cl.setUpdateThrottle(2000); // update at most every 2 seconds.
         return cl;
	}
}
