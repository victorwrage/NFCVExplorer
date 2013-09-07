package com.yachi.nfcvexplorer.gui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceScreen;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.yachi.library_yachi.gui.PreferenceActivityBasedSherlock;
import com.yachi.nfcvexplorer.NFCApplication;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.dao.TagsDbHelper;


/** 
 * @ClassName:	ActivitySetting 
 * @Description:TODO(设置界面) 
 * @author:	xiaoyl
 * @date:	2013-8-1 下午3:46:06 
 *  
 */
public class ActivitySetting extends PreferenceActivityBasedSherlock implements OnPreferenceClickListener {
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
			e.printStackTrace();
		}
		return true;
	}
}
