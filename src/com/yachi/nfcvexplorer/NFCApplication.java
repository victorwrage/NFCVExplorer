package com.yachi.nfcvexplorer;

import com.yachi.library_yachi.VApplication;




/** 
 * @ClassName:	NFCApplication 
 * @Description:TODO(Application) 
 * @author:	xiaoyl
 * @date:	2013-7-10 下午4:01:27 
 *  
 */
public class NFCApplication extends VApplication {
	private static NFCApplication instance;
	public static boolean isExit = false;
	public NFCApplication() {

	}

	public static NFCApplication getInstance() {
		if (null == instance) {
			instance = new NFCApplication();
		}
		return instance;
	}
}
