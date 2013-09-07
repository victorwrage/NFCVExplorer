package com.yachi.nfcvexplorer.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yachi.nfcvexplorer.NFCApplication;
import com.yachi.nfcvexplorer.mifare.M1Card;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class NFCUtils {
	private static String message_last = "";
	private static long elapse_time = 0;

	

	/**
	 * 获取屏幕亮度
	 */
	public static int getScreenLight(Context context) {
		int nowBrightnessValue = 0;
		ContentResolver resolver = context.getContentResolver();
		try {
			nowBrightnessValue = android.provider.Settings.System.getInt(
					resolver, Settings.System.SCREEN_BRIGHTNESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		nowBrightnessValue = (int) Math.round((nowBrightnessValue / (2.55)));

		return nowBrightnessValue;
	}

	/**
	 * 设置屏幕亮度
	 * 
	 * @param context
	 * @param progress
	 */
	public static void setScreenLight(Activity context, int progress,
			boolean istemp) {

		Window window = context.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		progress = (progress == 0) ? 1 : progress;
		float p = (int) (progress) / 100.00f;
		p += p;
		p *= 0.47;
		lp.screenBrightness = p;
		window.setAttributes(lp);
		if (istemp)
			return;
		try {
			Settings.System.putInt(context.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE,
					Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
			Settings.System.putInt(context.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS, (int) (progress * 2.55));
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		LogUtil.v("setScreenLight--" + progress);
	}

	/**
	 * 获取铃声音量
	 */
	public static int getRingValue(Context context) {
		AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		return audioManager.getStreamVolume(AudioManager.STREAM_RING);

	}

	/**
	 * 获取卡ID
	 * 
	 * @param intent
	 */
	public static void getCardID(Intent intent) {

		byte[] myNFCID = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
		if (myNFCID == null)
			return;
		myNFCID = reverseArray(myNFCID);
		Constants.current_card = new M1Card(Converter.byteToHexStr(myNFCID));

	}

	/**
	 * 获取卡ID字符串
	 * 
	 * @param paramTag
	 * @return
	 */
	public static String getTagIdAsString(Tag paramTag) {
		String str = "";
		byte[] arrayOfByte = paramTag.getId();
		StringBuilder localStringBuilder;
		int j;
		if (arrayOfByte != null) {
			localStringBuilder = new StringBuilder(2 * arrayOfByte.length);
			int i = arrayOfByte.length;
		}
		/*
		 * for (String str = localStringBuilder.toString();; str = "") {
		 * 
		 * byte b = arrayOfByte[j]; Object[] arrayOfObject = new Object[1];
		 * arrayOfObject[0] = Byte.valueOf(b);
		 * localStringBuilder.append(String.format("%02X", arrayOfObject)); ++j;
		 * 
		 * }
		 */
		return str;
	}

	/**
	 * 解析TAG
	 * 
	 * @param intent
	 * @return
	 */
	public static String resolveIntentAsAction(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
				|| NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			Parcelable[] rawMsgs = intent
					.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

			NdefMessage[] msgs;
			Log.v("Util--resolveIntentAsAction", intent.getAction());
			if (rawMsgs != null) {
				msgs = new NdefMessage[rawMsgs.length];
				Log.v("Util--resolveIntentAsAction----msgs--length---",
						msgs.length + "");
				for (int i = 0; i < rawMsgs.length; i++) {
					msgs[i] = (NdefMessage) rawMsgs[i];
				}
				if (msgs.length == 0)
					return null;
				NdefRecord[] records = msgs[0].getRecords();

				StringBuffer str = new StringBuffer();
				for (NdefRecord re : records) {

					byte[] arrayOfByte = re.getPayload();
					LogUtil.v(new String(arrayOfByte));
					if (re.getPayload().length == 0)
						return null;

					str.append(new String(re.getPayload(), Charset
							.forName("UTF-8")));
					if ((0x80 & arrayOfByte[0]) != 0) {

						int i = 0x3F & arrayOfByte[0];
						String str2 = null;
						try {
							str2 = new String(arrayOfByte, 1, i, "US-ASCII");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						try {
							String str3 = new String(arrayOfByte, i + 1, -1
									+ (arrayOfByte.length - i), "UTF-8");
							Log.v("Util--resolveIntentAsAction", records.length
									+ "" + str2 + "----" + str3);
						} catch (Exception localException) {
							throw new IllegalArgumentException();
						}

						Log.v("Util--resolveIntentAsAction", records.length
								+ "" + new String(arrayOfByte.toString()));
					}
				}
				return str.toString();
			} else {
				// Unknown tag type
				byte[] empty = new byte[] {};
				NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN,
						empty, empty, empty);
				NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
				msgs = new NdefMessage[] { msg };
				LogUtil.e("Util--Unknown tag " + msg.toString());
			}

		} else {
			LogUtil.e("Util--Unknown tag " + intent.getAction());
		}
		return null;
	}

	/**
	 * 写标签
	 * 
	 * @param intent
	 * @param action
	 */
	public static boolean writeTag(Intent intent, String action) {

		NdefMessage localNdefMessage;
		try {
			Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");

		/*	byte[] arrayOfByte1 = Locale.US.getLanguage().getBytes(
					Charset.forName("US-ASCII"));
			byte[] arrayOfByte2 = action.getBytes(Charset.forName("UTF-8"));
			int i = (char) (0 + arrayOfByte1.length);
			byte[][] arrayOfByte = new byte[3][];
			byte[] arrayOfByte3 = new byte[1];
			arrayOfByte3[0] = (byte) i;
			arrayOfByte[0] = arrayOfByte3;
			arrayOfByte[1] = arrayOfByte1;
			arrayOfByte[2] = arrayOfByte2;

			byte[] arrayOfByte4 = concat(arrayOfByte);*/
			
			byte[] arrayOfByte = action.getBytes(Charset.forName("UTF-8"));
			NdefRecord uriRecord = new NdefRecord(NdefRecord.TNF_ABSOLUTE_URI,
					NdefRecord.RTD_URI, new byte[0], arrayOfByte);
			NdefRecord aar = NdefRecord
					.createApplicationRecord("com.yachi.nfcvexplorer");

			NdefRecord[] arrayOfNdefRecord = new NdefRecord[2];
			arrayOfNdefRecord[0] = uriRecord;
			arrayOfNdefRecord[1] = aar;

			localNdefMessage = new NdefMessage(createNdefRecord(arrayOfByte,"com.yachi.nfcvexplorer"));
			Ndef localNdef = Ndef.get(tag);
			if (localNdef != null) {
				localNdef.connect();
				localNdef.writeNdefMessage(localNdefMessage);
			}
			return true;
		} catch (IOException localIOException) {
			LogUtil.e("" + localIOException.toString());
			return false;

		} catch (FormatException localFormatException) {
			LogUtil.e("" + localFormatException.toString());
			return false;
		}

	}
	
	/**
	 * 写标签
	 * 
	 * @param intent
	 * @param action
	 */
	public static boolean writeTagByNdefMessage(Intent intent, NdefMessage message) {

		try {
			Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
			Ndef localNdef = Ndef.get(tag);
			if (localNdef != null) {
				localNdef.connect();
				localNdef.writeNdefMessage(message);
			}
			return true;
		} catch (IOException localIOException) {
			LogUtil.e("" + localIOException.toString());
			return false;

		} catch (FormatException localFormatException) {
			LogUtil.e("" + localFormatException.toString());
			return false;
		}
	}
	
	@TargetApi(14)
	  public static NdefRecord[] createNdefRecord( byte[] paramArrayOfByte, String paramString )
	  {
	    byte[] arrayOfByte1 = "nfcv".getBytes();
	    NdefRecord localNdefRecord = makeMarketLink();
	 
	    NdefRecord[] arrayOfNdefRecord2;
	    if ( (Build.VERSION.SDK_INT >= 14))//加入AAR
	    {
	      byte[] arrayOfByte2 = "x/nfcv".getBytes();
	      arrayOfNdefRecord2 = new NdefRecord[2];
	      arrayOfNdefRecord2[0] = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, arrayOfByte2, new byte[0], paramArrayOfByte);
	      arrayOfNdefRecord2[1] = NdefRecord.createApplicationRecord(paramString);
	      return arrayOfNdefRecord2;
	    }
	    NdefRecord[] arrayOfNdefRecord1;

	    arrayOfNdefRecord1 = new NdefRecord[2];
	    arrayOfNdefRecord1[0] = localNdefRecord;
	    arrayOfNdefRecord1[1] = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, arrayOfByte1, new byte[0], paramArrayOfByte);
	    return arrayOfNdefRecord1;
	  }
	
	/**
	 * 创建响应记录
	 * @return
	 */
	private static NdefRecord makeMarketLink( )
	  {
	    byte[] arrayOfByte1 = "tags.do/nfcv".getBytes(Charset.forName("US-ASCII"));
	    byte[] arrayOfByte2 = new byte[1 + arrayOfByte1.length];
	    arrayOfByte2[0] = 3;
	    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 1, arrayOfByte1.length);
	    return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_URI, new byte[0], arrayOfByte2);
	  }

	/**
	 * 二维数组变一维
	 * @param paramArrayOfByte
	 * @return
	 */
	public static byte[] concat(byte[][] paramArrayOfByte) {
		int i = 0;
		int j = paramArrayOfByte.length;
		for (int k = 0; k < j; ++k)
			i += paramArrayOfByte[k].length;
		byte[] arrayOfByte1 = new byte[i];
		int l = 0;
		int i1 = paramArrayOfByte.length;
		for (int i2 = 0; i2 < i1; ++i2) {
			byte[] arrayOfByte2 = paramArrayOfByte[i2];
			System.arraycopy(arrayOfByte2, 0, arrayOfByte1, l,
					arrayOfByte2.length);
			l += arrayOfByte2.length;
		}
		return arrayOfByte1;
	}

	/**
	 * 反转数组
	 * 
	 * @param originalArray
	 * @return
	 */
	private static byte[] reverseArray(byte[] originalArray) {
		for (int i = 0; i < originalArray.length / 2; i++) {
			byte temp = originalArray[i];
			originalArray[i] = originalArray[originalArray.length - 1 - i];
			originalArray[originalArray.length - 1 - i] = temp;
		}
		return originalArray;
	}

	/**
	 * 提示
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static void toast(Context context, String message) {
		if (message_last.equals(message)
				&& (System.currentTimeMillis() - elapse_time) < 2000) {
			return;
		}
		message_last = message;
		elapse_time = System.currentTimeMillis();
		elapse_time = System.currentTimeMillis();
		Toast toast = new Toast(context);
		toast.makeText(context, message, Toast.LENGTH_SHORT).show();

	}
	
	/**
	 * 匹配器s
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static boolean match(String paramString1, String paramString2)
	  {
	    boolean  i = false;
	    Matcher localMatcher = Pattern.compile(paramString1).matcher(paramString2);
	    if ((localMatcher.find()) && (localMatcher.groupCount() >= 0))
	    {
	      String str = localMatcher.group(0);
	      if (paramString2.length() == str.length())
	        i = true;
	    }
	    return i;
	  }
}
