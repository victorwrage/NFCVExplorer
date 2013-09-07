package com.yachi.nfcvexplorer.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-20
 * 
 * @file 小数据存读Preference操作类
 * 
 */
public class NfcVSharedPreference {
	/** 小数据配置存储类*/
	private static NfcVSharedPreference instance;
	
	private Context context;
	/** KEY 保存最大任务数*/
	private static final String TAG_ACTION_COUNT = "action_count";
	/** KEY 保存最大TAG数*/
	private static final String TAG_TAG_COUNT = "tag_count";
       
       /**
   	 * 构造方法
   	 * @param context
   	 */
   	private NfcVSharedPreference(Context context) {
   		this.context = context;
   	}

   	/**
   	 * 获得GoMarketPreferences静态实例对象
   	 * @param context
   	 * @return
   	 */
   	public static NfcVSharedPreference getInstance(Context context) {
   		if (instance == null) {
   			instance = new NfcVSharedPreference(context);
   		}
   		return instance;
   	}
   	/**
	 * get SharedPreferences
	 * @return
	 */
	private SharedPreferences getSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	/**
	 * get Editor
	 * @return
	 */
	private Editor getEditor() {
		SharedPreferences pref = getSharedPreferences();
		return pref.edit();
	}
   	/**
	 * 取得最大执行任务数量
	 * @return
	 */
	public int getActionCount() {
		SharedPreferences pref = getSharedPreferences();
		return pref.getInt(TAG_ACTION_COUNT, 1);

	}
	/**
	 * 设置最大执行任务数量
	 * @return
	 */
	public boolean setActionCount(int count) {
		Editor editor = getEditor();
		editor.putInt(TAG_ACTION_COUNT, count);
		return editor.commit();
	}
}
