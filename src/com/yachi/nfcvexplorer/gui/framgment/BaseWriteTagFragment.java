package com.yachi.nfcvexplorer.gui.framgment;

import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.yachi.library_yachi.gui.FragmentBasedSherlock;


/**
 * 写入普通标签界面的Fragment的基类
 * @author xiaoyl
 * @date 2013-08-08
 *
 */
public abstract class BaseWriteTagFragment extends FragmentBasedSherlock implements OnClickListener{

	@Override
	public void onClick(View v) {
		
	}
	
	/**
	 * 数据保存
	 */
	public abstract void sentDataToActivity();

}
