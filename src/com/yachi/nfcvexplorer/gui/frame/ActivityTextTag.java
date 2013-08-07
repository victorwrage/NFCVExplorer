package com.yachi.nfcvexplorer.gui.frame;

import android.support.v4.app.Fragment;

import com.yachi.nfcvexplorer.gui.WriteFragmentActivity;

/**
 * 写入文本界面容器
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class ActivityTextTag extends WriteFragmentActivity {
	
	protected Fragment createFragment()
	  {
	    return new FragmentTextTag();
	   }

}
