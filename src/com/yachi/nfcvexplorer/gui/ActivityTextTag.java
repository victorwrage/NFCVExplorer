package com.yachi.nfcvexplorer.gui;

import android.support.v4.app.Fragment;

import com.yachi.nfcvexplorer.gui.framgment.FragmentTextTag;

/**
 * 写入文本界面容器
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class ActivityTextTag extends ActivityWriteFragment {
	
	protected Fragment createFragment()
	  {
	    return new FragmentTextTag();
	   }

	@Override
	protected String getFragmentType() {
		return "文本";
	}

}
