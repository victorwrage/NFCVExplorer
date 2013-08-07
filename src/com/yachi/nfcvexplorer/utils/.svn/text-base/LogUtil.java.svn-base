package com.yachi.nfcvexplorer.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class LogUtil {
	public static boolean DEBUG = true;
	private static final String TAG = "NFCVexplorer";

	public static void e(String paramString) {
		if (!DEBUG)
			return;
		Log.e("NFCVexplorer", paramString);
		writeFile(paramString);
	}

	public static void e(String paramString1, String paramString2) {
		if (!DEBUG)
			return;
		Log.e(paramString1, paramString2);
		writeFile(paramString2);
	}

	public static void e(String paramString1, String paramString2,
			Throwable paramThrowable) {
		if (!DEBUG)
			return;
		Log.e(paramString1, paramString2, paramThrowable);
		writeFile(paramString2);
	}

	public static void i(String paramString) {
		if (!DEBUG)
			return;
		Log.i("NFCVexplorer", paramString);
	}

	public static void i(String paramString1, String paramString2) {
		if (!DEBUG)
			return;
		Log.i(paramString1, paramString2);
	}

	public static void i(String paramString1, String paramString2,
			Throwable paramThrowable) {
		if (!DEBUG)
			return;
		Log.i(paramString1, paramString2, paramThrowable);
	}

	public static boolean isDEBUG() {
		return DEBUG;
	}

	public static void setDEBUG(boolean paramBoolean) {
		DEBUG = paramBoolean;
	}

	public static void v(String paramString) {
		if (!DEBUG)
			return;
		Log.v("NFCVexplorer", paramString);
		writeFile(paramString);
	}

	public static void v(String paramString1, String paramString2) {
		if (!DEBUG)
			return;
		Log.v(paramString1, paramString2);
		writeFile(paramString2);
	}

	/**
	 * 写入文件
	 * 
	 * @param paramString
	 */
	private static void writeFile(String paramString) {
		/*fileWriter localfileWriter = new fileWriter();
		String[] arrayOfString = new String[1];
		arrayOfString[0] = paramString;
		localfileWriter.execute(arrayOfString);*/
	}
    /**
     * 
     * @author xiaoyl
     * @date 后台写LOG文件
     */
	private static class fileWriter extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			File localFile1 = Environment.getExternalStorageDirectory();
			if (localFile1.canWrite());
			try {
				
				File localFile2 = new File(localFile1.getPath() + "/NFCVexplorer/");
				localFile2.mkdirs();
				
				File dir = new File(localFile2.getAbsoluteFile()+"/debug.txt"); 	
		        if (!dir.exists()) { 
		              try { 
		                  dir.createNewFile(); 	 
		            } catch (Exception e) { 
		            	
		            } 
		        } 

				FileWriter localFileWriter = new FileWriter(dir, true);
				BufferedWriter localBufferedWriter = new BufferedWriter(
						localFileWriter);
				localBufferedWriter.write(new Date().toLocaleString() + ": "
						+ params[0] + "\n");
				localBufferedWriter.close();
				localFileWriter.close();
				return null;
			} catch (Exception localException) {
				localException.printStackTrace();
			}
			return null;
		}
	}
}
