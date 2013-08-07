package com.yachi.nfcexplorer.exception;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yachi.nfcvexplorer.utils.LogUtil;

public class ExceptionUtil {

	  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	  public static void printErrorLog(Exception paramException, String paramString)
	  {
	    int i;
	    int j;
	    do
	      try
	      {
	        StackTraceElement[] arrayOfStackTraceElement = paramException.getStackTrace();
	        i = arrayOfStackTraceElement.length;
	        StringBuffer localStringBuffer = new StringBuffer();
	        j = 0;

	        String str = arrayOfStackTraceElement[j].toString();
	     //   printErrorLogToFile(paramString, str);
	        localStringBuffer.append(str);
	        ++j;
	      }
	      catch (Exception localException)
	      {
	        LogUtil.e(localException.toString());
	        return;
	      }
	    while (j < i);
	  }

	  public static void printErrorLogToFile(String paramString1, String paramString2)
	  {
	    File localFile1 = Environment.getExternalStorageDirectory();
	    File  localFile2 = new File(localFile1, "/nfcvexplorer/nfc/error/");;
	    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
	    {
	
	      if (!localFile1.exists()) 
	    	 return; 
	      
	      if (!localFile2.exists()){
		        localFile2.mkdirs();

	      try
	      {
	        File localFile3 = new File(localFile2, "error.txt");
	        if (!localFile3.exists())
	          localFile3.createNewFile();
	        PrintWriter localPrintWriter = new PrintWriter(new FileOutputStream(localFile3, true));
	        localPrintWriter.println("Class name: " + paramString1);
	        localPrintWriter.println("Date: " + sdf.format(new Date()));
	        localPrintWriter.println("Error content: " + paramString2);
	        localPrintWriter.close();
	      }
	      catch (Exception localException)
	      {
	    	  LogUtil.e(localException.toString());
	      }
	    
	    }
	  }
	  }
	
}
