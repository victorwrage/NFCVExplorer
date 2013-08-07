package com.yachi.nfcvexplorer.gui;

import java.util.ArrayList;

import com.viewpagerindicator.TitlePageIndicator;
import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.gui.frame.FrameHelp;
import com.yachi.nfcvexplorer.gui.frame.FrameHistory;
import com.yachi.nfcvexplorer.gui.frame.FrameHot;
import com.yachi.nfcvexplorer.gui.frame.FrameViewPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FrameMain extends Fragment implements OnPageChangeListener {
	FrameHistory history;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpage_lay, container, false);
		FrameHot hot = new FrameHot();
		FrameHelp buy = new FrameHelp();
		history = new FrameHistory();

		ArrayList<Fragment> frams = new ArrayList<Fragment>();
		frams.add(buy);
		frams.add(hot);
		frams.add(history);
		FrameViewPage adapter = new FrameViewPage(getFragmentManager(), frams);
       
		ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
		TitlePageIndicator indicator = (TitlePageIndicator) view
				.findViewById(R.id.indicator);
		viewPager.setOnPageChangeListener(this);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(1);
		indicator.setViewPager(viewPager, 1);
		return view;
	}

	public FrameHistory getFragment() {
		return history;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		if(arg0==2){
			history.notifyAdapter();
		}
	}
}
