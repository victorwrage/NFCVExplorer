package com.yachi.nfcexplorer.exception;

import com.yachi.nfcvexplorer.utils.NFCUtils;

import android.content.Context;
import android.os.Process;

/**
 * 捕获崩溃异常
 * @author xiaoyl
 * @date 2013-08-03
 */
public class UncaughtException  implements Thread.UncaughtExceptionHandler{

	  private static UncaughtException INSTANCE;
	  private Thread.UncaughtExceptionHandler defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
	  private Context mContext;

	  public static UncaughtException getInstance()
	  {
	    if (INSTANCE == null)
	      INSTANCE = new UncaughtException();
	    return INSTANCE;
	  }

	  public void init(Context paramContext)
	  {
	    this.mContext = paramContext;
	    this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
	    Thread.setDefaultUncaughtExceptionHandler(this);
	  }

	  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
	  {
	    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
	    int i = arrayOfStackTraceElement.length;
	    String str = super.getClass().getName();
	    NFCUtils.toast(mContext, "遇到错误需要关闭了");
	    int j = 0;
	    while (true)
	    {
	      if (j >= i)
	    	//  ExceptionUtil.printErrorLogToFile(str,paramThrowable.getMessage());
	      try
	      {  
	        if (arrayOfStackTraceElement[j].getClassName().equals(str)){
	         // ExceptionUtil.printErrorLogToFile(str, arrayOfStackTraceElement[j].toString());
	          Thread.sleep(2000L);
		      Process.killProcess(Process.myPid());
		      System.exit(10);
	        }
	        ++j;
	      }
	      catch (InterruptedException localInterruptedException)
	      {

	      }
	    }
	  }
	
}
