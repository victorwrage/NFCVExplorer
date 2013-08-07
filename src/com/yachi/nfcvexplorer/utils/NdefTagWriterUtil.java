package com.yachi.nfcvexplorer.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.bitbucket.dollar.Dollar;

import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.text.TextUtils;

/**
 * 写标签的应用类
 * 
 * @author xiaoyl
 * @date 2013-08-01
 */
public class NdefTagWriterUtil {
	private static final String NFCTECH_NDEF_FORMATTABLE;
	private static final String NFC_TECH_MIFARE_CLASSIC;
	private static final String NFC_TECH_MIFARE_ULTRALIGHT = MifareUltralight.class
			.getName();
	private static final String NFC_TECH_NDEF;
	private static final String TAG = "NdefTagWriterUtil";
	private static final String[] URI_PREFIX_MAP;
	static {
		NFC_TECH_MIFARE_CLASSIC = MifareClassic.class.getName();
		NFC_TECH_NDEF = Ndef.class.getName();
		NFCTECH_NDEF_FORMATTABLE = NdefFormatable.class.getName();
		String[] arrayOfString = new String[35];
		arrayOfString[0] = "";
		arrayOfString[1] = "http://www.";
		arrayOfString[2] = "https://www.";
		arrayOfString[3] = "http://";
		arrayOfString[4] = "https://";
		arrayOfString[5] = "tel:";
		arrayOfString[6] = "mailto:";
		arrayOfString[7] = "ftp://anonymous:anonymous@";
		arrayOfString[8] = "ftp://ftp.";
		arrayOfString[9] = "ftps://";
		arrayOfString[10] = "sftp://";
		arrayOfString[11] = "smb://";
		arrayOfString[12] = "nfs://";
		arrayOfString[13] = "ftp://";
		arrayOfString[14] = "dav://";
		arrayOfString[15] = "news:";
		arrayOfString[16] = "telnet://";
		arrayOfString[17] = "imap:";
		arrayOfString[18] = "rtsp://";
		arrayOfString[19] = "urn:";
		arrayOfString[20] = "pop:";
		arrayOfString[21] = "sip:";
		arrayOfString[22] = "sips:";
		arrayOfString[23] = "tftp:";
		arrayOfString[24] = "btspp://";
		arrayOfString[25] = "btl2cap://";
		arrayOfString[26] = "btgoep://";
		arrayOfString[27] = "tcpobex://";
		arrayOfString[28] = "irdaobex://";
		arrayOfString[29] = "file://";
		arrayOfString[30] = "urn:epc:id:";
		arrayOfString[31] = "urn:epc:tag:";
		arrayOfString[32] = "urn:epc:pat:";
		arrayOfString[33] = "urn:epc:raw:";
		arrayOfString[34] = "urn:epc:";
		URI_PREFIX_MAP = arrayOfString;
	}
    
	/**
	 * 创建邮件信息
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public static NdefMessage ndefMessageForEmail(String paramString1,
			String paramString2, String paramString3) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("mailto:");
		stringBuilder.append(Uri.encode(paramString1));
		ArrayList localArrayList = new ArrayList();
		if (!TextUtils.isEmpty(paramString2)) {
			Object[] arrayOfObject2 = new Object[1];
			arrayOfObject2[0] = Uri.encode(paramString2);
			localArrayList.add(String.format("subject=%s", arrayOfObject2));
		}
		if (!TextUtils.isEmpty(paramString3)) {
			Object[] arrayOfObject1 = new Object[1];
			arrayOfObject1[0] = Uri.encode(paramString3);
			localArrayList.add(String.format("body=%s", arrayOfObject1));
		}
		if (localArrayList.size() > 0) {
			stringBuilder.append("?");
			stringBuilder.append(Dollar.$(localArrayList).join("&"));
		}
		return ndefMessageForUri(stringBuilder.toString());
	}
	
    /**
     * 创建电话/短信的消息
     * @param paramString1
     * @param paramString2
     * @param paramString3
     * @return
     */
	public static NdefMessage ndefMessageForPhone(String paramString1,
			String paramString2, String paramString3) {
		Object[] arrayOfObject2 = null;
		if (paramString2.equals("sms")) {
			arrayOfObject2 = new Object[2];
			arrayOfObject2[0] = paramString1;
			arrayOfObject2[1] = paramString3;
			NdefMessage localNdefMessage = ndefMessageForUri(String.format(
					"sms:%s?body=%s", arrayOfObject2));
			return localNdefMessage;
		}
		Object[] arrayOfObject1 = null;

		arrayOfObject1 = new Object[1];
		arrayOfObject1[0] = paramString1;
		NdefMessage localNdefMessage = ndefMessageForUri(String.format("tel:%s",
				arrayOfObject1));
		return localNdefMessage;
	}
    
	/**
	 * 创建文本信息
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static NdefMessage ndefMessageForText(String paramString1,
			String paramString2) {
		NdefRecord[] arrayOfNdefRecord = new NdefRecord[1];
		arrayOfNdefRecord[0] = createText(paramString1, paramString2);
		return new NdefMessage(arrayOfNdefRecord);
	}

	public static NdefMessage ndefMessageForUri(String paramString) {
		NdefRecord[] arrayOfNdefRecord = new NdefRecord[1];
		arrayOfNdefRecord[0] = createUri(paramString);
		return new NdefMessage(arrayOfNdefRecord);
	}
    
	/**
	 * 创建联系人信息
	 * @param paramString
	 * @return
	 */
	public static NdefMessage ndefMessageForVcard(String paramString1,String paramString2) {
		NdefRecord[] arrayOfNdefRecord = new NdefRecord[1];
		arrayOfNdefRecord[0] = createVcard(getContactContents(paramString1,paramString2));
		return new NdefMessage(arrayOfNdefRecord);
	}

	private static NdefRecord createMime(String paramString1,
			String paramString2) {
		return new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
				paramString1.getBytes(), new byte[0], paramString2.getBytes());
	}

	private static NdefRecord createText(String paramString1,
			String paramString2) {
		int i = 0;
		try {
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream localDataOutputStream = new DataOutputStream(
					localByteArrayOutputStream);
			byte[] arrayOfByte1 = paramString2.getBytes(Charset
					.forName("US-ASCII"));
			byte[] arrayOfByte2 = paramString1.getBytes(Charset
					.forName("UTF-8"));

			localDataOutputStream
					.writeByte((byte) (char) (i + arrayOfByte1.length));
			localDataOutputStream.write(arrayOfByte1);
			localDataOutputStream.write(arrayOfByte2);
			NdefRecord localNdefRecord = new NdefRecord(
					NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT,
					new byte[0], localByteArrayOutputStream.toByteArray());
			return localNdefRecord;

		} catch (IOException localIOException) {
			throw new RuntimeException(localIOException);
		}
	}

	public static NdefRecord createUri(String paramString) {
		byte i = 0;
		for (int j = 1;; ++j) {
			if (j < URI_PREFIX_MAP.length) {
				if (!paramString.startsWith(URI_PREFIX_MAP[j]))
					continue;
				i = (byte) j;
				paramString = paramString.substring(URI_PREFIX_MAP[j].length());
			}
			byte[] arrayOfByte1 = paramString
					.getBytes(Charset.forName("UTF-8"));
			byte[] arrayOfByte2 = new byte[1 + arrayOfByte1.length];
			arrayOfByte2[0] = i;
			System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 1,
					arrayOfByte1.length);
			return new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
					NdefRecord.RTD_URI, new byte[0], arrayOfByte2);
		}
	}
    
	/**
	 * 得到联系人的Uri文本
	 * @param name
	 * @param phone
	 * @return
	 */
	private static String getContactContents(String name,String phone)
	  {
		StringBuilder localStringBuilder = new StringBuilder("BEGIN:VCARD\nVERSION:2.1\nN:;");
	      localStringBuilder.append(name);
	      localStringBuilder.append("");
	      localStringBuilder.append(";;;\nFN:");
	      localStringBuilder.append("");
	      localStringBuilder.append("\n");

	      if (!"".equals(phone))
	      {
	        localStringBuilder.append("TEL;CELL:");
	        localStringBuilder.append(phone);
	        localStringBuilder.append("\n");
	      }
	      localStringBuilder.append("END:VCARD");
	   
	    return localStringBuilder.toString();
	  }

	
	public static NdefRecord createVcard(String paramString) {
		return createMime("text/x-vCard", paramString);
	}
	
}
