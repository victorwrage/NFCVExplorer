package com.yachi.nfcvexplorer.gui;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceScreen;

import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.yachi.nfcvexplorer.MainActivity;
import com.yachi.nfcvexplorer.NFCApplication;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;
import com.yachi.nfcvexplorer.utils.Constants;
/**
 * 设置界面
 * @author xiaoyl
 * @date 2013-07-18
 */
public class FrameSetting extends SherlockPreferenceActivity implements OnPreferenceClickListener {
	private TagsDbHelper db;
     @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	addPreferencesFromResource(R.xml.preferences);
    	NFCApplication.getInstance().addActivitys(this);
    	PreferenceScreen clear = (PreferenceScreen) findPreference("pclear");
    	clear.setOnPreferenceClickListener(this);
    	db = TagsDbHelper.getInstance(getApplicationContext());
    	getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.bg_tab));
    	getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("设置");
    	invalidateOptionsMenu();
    }
     
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return false;
	}

	@Override
	public boolean onPreferenceClick(Preference arg0) {
		try {
			db.reinitializationDataBaseTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
