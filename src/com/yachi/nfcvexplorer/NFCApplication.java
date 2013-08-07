package com.yachi.nfcvexplorer;

import java.util.LinkedList;
import java.util.List;

import com.yachi.nfcexplorer.exception.ExceptionUtil;
import com.yachi.nfcexplorer.exception.UncaughtException;

import android.app.Activity;
import android.app.Application;

/**
 * Application
 * 
 * @author xiaoyl
 * @date 2013-07-25
 */
public class NFCApplication extends Application {
	private static NFCApplication instance;
	private List<Activity> activityList = new LinkedList<Activity>();
	private String myState;
	public static boolean isExit = false;

	public NFCApplication() {

	}

	@Override
	public void onCreate() {
		super.onCreate();
		/*try 
	    {
	      new UncaughtException().init(getApplicationContext());
	      return;
	    }
	    catch (Exception localException)
	    {
	      ExceptionUtil.printErrorLog(localException, NFCApplication.class.getName());
	    }*/
	}
	
	public static NFCApplication getInstance() {
		if (null == instance) {
			instance = new NFCApplication();
		}
		return instance;
	}

	public String getState() {
		return myState;
	}

	public void setState(String s) {
		myState = s;
	}

	/**
	 * 
	 * @param activity
	 */
	public void addActivitys(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 退出应用程序
	 */
	public void exitApplication() {
		isExit = true;
		for (Activity a : activityList) {
			a.finish();
		}
		System.exit(0);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
