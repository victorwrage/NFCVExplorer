package com.yachi.nfcvexplorer.gui.framgment;

import java.util.ArrayList;

import com.yachi.nfcvexplorer.control.IFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 主界面FragmentPagerAdapter适配器
 * @author xiaoyl
 * @date 2013-07-16
 */
public class FramgmentViewPage extends FragmentPagerAdapter {
	ArrayList<Fragment> fragments;
	public FramgmentViewPage(FragmentManager fm,ArrayList<Fragment> fragments) {
		
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {

		return fragments.get(arg0);
	}
    
	@Override
	public CharSequence getPageTitle(int position) {
		IFragment  d =  (IFragment)fragments.get(position);
		return d.getTitle();
	}
	@Override
	public int getCount() {

		return fragments.size();
	}
}
