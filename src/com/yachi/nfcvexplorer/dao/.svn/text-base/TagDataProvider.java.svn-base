package com.yachi.nfcvexplorer.dao;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-13
 * 
 * @file 数据库的Provider类
 * 
 */
public class TagDataProvider extends ContentProvider {
	/**TAG管理表*/
	public static final String TABLE_TAG_MANAGE = "tag_manage";
	/**ACTION管理表*/
	public static final String TABLE_ACTION_MANAGE = "action_manage";
	
	/**id*/
	public static final String _ID = "_id";
	/**TAG_id*/
	public static final String TAG_ID = "tag_id";
	/**TAG序列号*/
	public static final String TAG_NUMBER = "tag_number";
	/**TAG名字*/
	public static final String TAG_NAME = "tag_name";
	/**TAG创建时间*/
	public static final String TAG_CREATE_TIME = "tag_create_time";
	/**TAG动作描述*/
	public static final String TAG_DESCIBE = "tag_describe";
	/**TAG大小*/
	public static final String TAG_SIZE = "tag_size";
	/**TAG动作优先级*/
	public static final String TAG_PRIORITY = "tag_priority";
	/**TAG可用*/
	public static final String TAG_ENABLE = "tag_enable";
	/**TAG使用次数*/
	public static final String TAG_USE_COUNT = "tag_use_count";
	/**TAG开关切换参数参考1*/
	public static final String TAG_LINK_PARAM1 = "tag_link_param1";
	/**TAG开关切换参数参考2*/
	public static final String TAG_LINK_PARAM2 = "tag_link_param2";
	
	/**ACTION_id*/
	public static final String ACTION_ID = "action_id";
	/**ACTION创建时间*/
	public static final String ACTION_CREATE_TIME = "action_create_time";
	/**ACTION开关类型的值*/
	public static final String ACTION_SWITCH = "action_switch";
	/**ACTION动作类型*/
	public static final String ACTION_TYPE = "action_type";
	/**ACTION打开的程序包名*/
	public static final String ACTION_PACKAGE_NAME = "action_package_name";
	/**ACTION 电话号码*/
	public static final String ACTION_DIA_NUMBER = "action_dia_number";
	/**ACTION 短信内容*/
	public static final String ACTION_MESSAGE = "action_message";
	/**ACTION 屏幕亮度*/
	public static final String ACTION_SCREEN_LIGHT = "action_screen_light";
	/**ACTION 时间*/
	public static final String ACTION_TIME = "action_time";
	/**ACTION 日期*/
	public static final String ACTION_DATE = "action_date";
	/**ACTION 铃声类型*/
	public static final String ACTION_RING_TYPE = "action_ring_type";
	/**ACTION 铃声名字*/
	public static final String ACTION_RING_NAME = "action_ring_name";
	/**ACTION 声音音量*/
	public static final String ACTION_RING_VALUE = "action_ring_value";
	/**ACTION 描述*/
	public static final String ACTION_DETAIL = "action_detail";
	/**
	 * Authority for Uris
	 * 数据库的一些数据的Provider类
	 */
	public static final String AUTHORITY = TagDataProvider.class.getName();
	/**TAG管理表*/
	public static final Uri CONTENT_URI_TAGMANAGE = Uri.parse("content://" + AUTHORITY + "/"
			+ Schema.TABLE_TAG_MANAGE);  
	/**TAG管理表*/
	public static final Uri CONTENT_URI_ACTIONMANAGE = Uri.parse("content://" + AUTHORITY + "/"
			+ Schema.TABLE_ACTION_MANAGE);  
	
	/**Uri匹配器*/
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static {
		sUriMatcher.addURI(AUTHORITY, Schema.TABLE_TAG_MANAGE, Schema.URI_CODE_TAGMANAGE);
		sUriMatcher.addURI(AUTHORITY, Schema.TABLE_ACTION_MANAGE, Schema.URI_CODE_ACTIONMANAGE);
	}
	/** 内部类*/
	private TagDBHelp dbHelp;
	/** Uri*/
	private Uri currentUri;
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		String tblName = getTblName(uri);
		if (tblName != null) {
			getContext().getContentResolver().notifyChange(currentUri, null);
			return db.delete(tblName, selection, selectionArgs);
		}
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		long rowId = -1;
		String tblName = getTblName(uri);
		if (tblName != null) {
			rowId = db.insert(tblName, null, values);
			if (rowId > 0) {
				Uri insertedDownloadUri = ContentUris.withAppendedId(currentUri, rowId);
				getContext().getContentResolver().notifyChange(insertedDownloadUri, null);
				return insertedDownloadUri;
			}
		}

		return null;
	}

	@Override
	public boolean onCreate() {
		dbHelp =new TagDBHelp(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		String tblName = null;
		String groupBy = null;
		String limit = null;
		switch (sUriMatcher.match(uri)) {
		case Schema.URI_CODE_TAGMANAGE:
			tblName = Schema.TABLE_TAG_MANAGE;
			break;
		case Schema.URI_CODE_ACTIONMANAGE:
			tblName = Schema.TABLE_ACTION_MANAGE;
			break;
		}
		if (tblName != null) {
			return db.query(tblName, projection, selection, selectionArgs, groupBy, null, sortOrder, limit);
		}
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelp.getWritableDatabase();
		String tblName = getTblName(uri);

		if (tblName != null) {
			getContext().getContentResolver().notifyChange(currentUri, null);
			return db.update(tblName, values, selection, selectionArgs);
		}

		return 0;
	}
	
	/**
	 * 根据uri获取表名
	 * @param uri Uri
	 * @return
	 */
	private String getTblName(Uri uri) {
		String tblName = null;
		switch (sUriMatcher.match(uri)) {
		case Schema.URI_CODE_TAGMANAGE:
			tblName = Schema.TABLE_TAG_MANAGE;
			currentUri = CONTENT_URI_TAGMANAGE;
			break;
		case Schema.URI_CODE_ACTIONMANAGE:
			tblName = Schema.TABLE_ACTION_MANAGE;
			currentUri = CONTENT_URI_ACTIONMANAGE;
			break;
		}
		return tblName;
	}
	/**
	 * 创建数据库
	 * @author xiaoyl
	 *@date 2013-07-12
	 */
	public static class TagDBHelp extends SQLiteOpenHelper {
		private static final String NAME = "nfc_mifare.db";
		private static final int VERSION = 1;
		
		/** 创建TAG管理表语句*/
		public static final String CREATE_TABLE_TAG_MANAGE = "create table  if not exists "
				+ TABLE_TAG_MANAGE
				+ " ("
				+ _ID
				+ " integer primary key autoincrement, "
				+ TAG_ID
				+ " bigint not null, "
				+ TAG_NUMBER
				+ " text not null, "
				+ TAG_CREATE_TIME
				+ " text not null, "
				+ TAG_NAME
				+ " text not null, "
				+ TAG_DESCIBE
				+ " text, "
				+ TAG_ENABLE
				+ " int, "
				+ TAG_PRIORITY
				+ " integer, "
				+ TAG_USE_COUNT
				+ " integer, "
				+ TAG_SIZE
				+ " text, "
				+ TAG_LINK_PARAM1
				+ " text, "
				+ TAG_LINK_PARAM2
				+ " text "
				+ ")";
		/** 创建TAG管理表语句*/
		public static final String CREATE_TABLE_ACTION_MANAGE = "create table  if not exists "
				+ TABLE_ACTION_MANAGE
				+ " ("
				+ _ID
				+ " integer primary key autoincrement, "
				+ ACTION_ID
				+ " bigint not null, "
				+ TAG_NUMBER
				+ " text not null, "
				+ ACTION_TYPE
				+ " integer not null, "
				+ ACTION_CREATE_TIME
				+ " text, "
				+ ACTION_PACKAGE_NAME
				+ " text, "
				+ ACTION_DATE
				+ " text, "
				+ ACTION_DIA_NUMBER
				+ " text, "
				+ ACTION_RING_NAME
				+ " text, "
				+ ACTION_RING_VALUE
				+ " text, "
				+ ACTION_RING_TYPE
				+ " text, "
				+ ACTION_MESSAGE
				+ " text, "
				+ ACTION_SCREEN_LIGHT
				+ " text, "
				+ ACTION_SWITCH
				+ " text, "
				+ ACTION_TIME
				+ " text, "
				+ ACTION_DETAIL
				+ " text "
				+ ")";
		/**
		 * 删除TAG表
		 */
		static final String DELETE_TABLE_TAG_MANAGE = "drop table if exists "+TABLE_TAG_MANAGE;
		/**
		 * 删除ACTION表
		 */
		static final String DELETE_TABLE_ACTION_MANAGE = "drop table if exists "+TABLE_ACTION_MANAGE;
		public TagDBHelp(Context context) {
			super(context, NAME, null, VERSION);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_TAG_MANAGE);
			db.execSQL(CREATE_TABLE_ACTION_MANAGE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL(DELETE_TABLE_TAG_MANAGE);
			db.execSQL(DELETE_TABLE_ACTION_MANAGE);
			db.execSQL(CREATE_TABLE_TAG_MANAGE);
			db.execSQL(CREATE_TABLE_ACTION_MANAGE);
		}
	}
	
	/**
	 * Represents Data Schema.
	 */
	public static final class Schema{
		/** TAG管理表 */
		static final String TABLE_TAG_MANAGE = "tag_manage";
		/** ACTION管理表 */
		static final String TABLE_ACTION_MANAGE = "action_manage";
		/** Codes for UriMatcher 对应TAG管理表 */
		static final int URI_CODE_TAGMANAGE = 1;
		/** Codes for UriMatcher 对应ACTION管理表 */
		static final int URI_CODE_ACTIONMANAGE = 2;
	}
}
