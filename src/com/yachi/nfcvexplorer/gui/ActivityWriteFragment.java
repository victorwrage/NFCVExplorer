package com.yachi.nfcvexplorer.gui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.yachi.nfcvexplorer.R;

/**
 * 写TAG的基类
 * @author xiaoyl
 * @date 2013-0801
 */
public abstract class ActivityWriteFragment extends BaseActivity {
	
    /**
     * 创建内容
     * @return
     */
	protected abstract Fragment createFragment();
	/**
     * 内容类型
     * @return
     */
	protected abstract String getFragmentType();
   
	/**
	 * 查找Fragment
	 * @return
	 */
	protected Fragment getFragment() {
	    return getSupportFragmentManager().findFragmentByTag("fragment");
	 }
	
	public void onCreate(Bundle paramBundle)
	  {
	    super.onCreate(paramBundle);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    setContentView(R.layout.activity_fore_lay);
	    if (getFragment() != null)
	      return;
	    
	    
	    getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.bg_tab));
    	getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("写入"+getFragmentType());
		
	    FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
	    localFragmentTransaction.add(R.id.fragment_container, createFragment(), "fragment");
	    localFragmentTransaction.commit();
	  }

	  public boolean onOptionsItemSelected(MenuItem item)
	  {
		  switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			}
			return false;
	  }
}
