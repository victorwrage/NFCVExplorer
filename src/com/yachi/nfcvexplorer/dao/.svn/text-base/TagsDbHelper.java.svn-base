package com.yachi.nfcvexplorer.dao;

import java.util.ArrayList;
import java.util.Vector;

import com.yachi.nfcvexplorer.bean.ActionItem;
import com.yachi.nfcvexplorer.bean.TagItem;
import com.yachi.nfcvexplorer.utils.Constants;
import com.yachi.nfcvexplorer.utils.LogUtil;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class TagsDbHelper {
	private Context context;
	private ContentResolver contentResolver;
	private static TagsDbHelper instance;

	/**
	 * 构造方法
	 * 
	 * @param context
	 */
	private TagsDbHelper(Context context) {
		this.context = context;
		try {
			contentResolver = this.context.getContentResolver();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			contentResolver = this.context.getContentResolver();
		}
	}

	/**
	 * get TagsDbHelper静态实例对象
	 * 
	 * @param context
	 * @return
	 */
	public static TagsDbHelper getInstance(Context context) {
		if (instance == null) {
			LogUtil.e("DBHelp getInstance");
			instance = new TagsDbHelper(context);
		}
		return instance;
	}

	/**
	 * 查找是否表中含有此ID
	 * 
	 * 此应用的ID
	 * 
	 * @return
	 */
	public boolean existCardId(long card_id) {
		boolean result = false;
		String where = TagDataProvider.TAG_ID + "= '" + card_id + "'";
		Cursor cursor = contentResolver.query(
				TagDataProvider.CONTENT_URI_TAGMANAGE, new String[] {}, where,
				null, null);
		if (cursor != null) {
			result = cursor.moveToFirst();
			cursor.close();
			cursor = null;
		}
		LogUtil.v("existCardId---" + result + card_id);
		return result;
	}

	/**
	 * 查找是否表中含有此Card ID
	 * 
	 * @return
	 */
	public boolean existCardId(String card_id) {
		boolean result = false;
		String where = TagDataProvider.TAG_NUMBER + "= '" + card_id + "'"
				+ " and " + TagDataProvider.TAG_ENABLE + " = '" + "1" + "'";
		Cursor cursor = contentResolver.query(
				TagDataProvider.CONTENT_URI_TAGMANAGE, new String[] {}, where,
				null, null);
		if (cursor != null) {
			result = cursor.moveToFirst();
			cursor.close();
			cursor = null;
		}
		LogUtil.v("existCardId---" + result + card_id);
		return result;
	}

	/**
	 * 数据库插入Tag
	 * 
	 * @param item
	 * @return
	 */
	public boolean insertTagItem(TagItem item) {
		boolean result = false;
		ContentValues initValues = new ContentValues();
		initValues.put(TagDataProvider.TAG_ID, item.getId());
		initValues.put(TagDataProvider.TAG_CREATE_TIME,
				item.getTag_create_time());
		initValues.put(TagDataProvider.TAG_USE_COUNT, item.getTag_use_count());
		initValues.put(TagDataProvider.TAG_NAME, item.getTag_name());
		initValues.put(TagDataProvider.TAG_NUMBER, item.getTag_number());
		if (item.getTag_describe() != null) {
			initValues.put(TagDataProvider.TAG_DESCIBE, item.getTag_describe());
		}
		initValues.put(TagDataProvider.TAG_PRIORITY, item.getTag_priority());
		initValues.put(TagDataProvider.TAG_LINK_PARAM1,
				item.getTag_link_param1());
		initValues.put(TagDataProvider.TAG_LINK_PARAM2,
				item.getTag_link_param2());
		initValues.put(TagDataProvider.TAG_SIZE, item.getTag_size());
		initValues.put(TagDataProvider.TAG_ENABLE, item.isTag_enable() ? "1"
				: "0");
		Uri uri = contentResolver.insert(TagDataProvider.CONTENT_URI_TAGMANAGE,
				initValues);
		if (uri != null) {
			result = true;
		}
		LogUtil.e("db insertTag " + result + item.getTag_name());
		return result;
	}

	/**
	 * 数据库更新Tag
	 * 
	 * @param item
	 * @return
	 */
	public boolean updateTagItem(TagItem item) {
		boolean result = false;
		ContentValues initValues = new ContentValues();
		initValues.put(TagDataProvider.TAG_ID, item.getId());

		initValues.put(TagDataProvider.TAG_USE_COUNT, item.getTag_use_count());
		initValues.put(TagDataProvider.TAG_NAME, item.getTag_name());

		if (item.getTag_describe() != null) {
			initValues.put(TagDataProvider.TAG_DESCIBE, item.getTag_describe());
		}
		initValues.put(TagDataProvider.TAG_PRIORITY, item.getTag_priority());
		initValues.put(TagDataProvider.TAG_LINK_PARAM1,
				item.getTag_link_param1());
		initValues.put(TagDataProvider.TAG_LINK_PARAM2,
				item.getTag_link_param2());
		initValues.put(TagDataProvider.TAG_SIZE, item.getTag_size());
		initValues.put(TagDataProvider.TAG_ENABLE, item.isTag_enable() ? "1"
				: "0");
		String where = TagDataProvider.TAG_NUMBER + "= '"
				+ item.getTag_number() + "'" + " and "
				+ TagDataProvider.TAG_CREATE_TIME + "= '"
				+ item.getTag_create_time() + "'";
		int c = contentResolver.update(TagDataProvider.CONTENT_URI_TAGMANAGE,
				initValues, where, null);
		if (c != 0) {
			result = true;
		}
		LogUtil.e("db updateTag " + result);
		return result;
	}
	

	/**
	 * 数据库删除Tag
	 * 
	 * @param item
	 * @return
	 */
	public void deleteTagItem(TagItem item) {
		String where = TagDataProvider.TAG_NUMBER + "= '"
				+ item.getTag_number() + "'" + " and "
				+ TagDataProvider.TAG_CREATE_TIME + "= '"
				+ item.getTag_create_time() + "'";
		int i = contentResolver.delete(TagDataProvider.CONTENT_URI_TAGMANAGE,
				where, null);
		LogUtil.e("db deleteTag " + i);
	}

	/**
	 * 数据库删除Action
	 * 
	 * @param item
	 * @return
	 */
	public void deleteActionItem(ActionItem item) {
		String where = TagDataProvider.TAG_NUMBER + "= '" + item.getCard_id()
				+ "'" + " and " + TagDataProvider.ACTION_CREATE_TIME + "= '"
				+ item.getCreate_time() + "'";
		int i = contentResolver.delete(
				TagDataProvider.CONTENT_URI_ACTIONMANAGE, where, null);
		LogUtil.e("db deleteAction " + i);
	}

	/**
	 * 数据库更新ACTION
	 * 
	 * @param item
	 * @return
	 */
	public boolean updateActionItem(ActionItem item) {
		boolean result = false;
		ContentValues initValues = new ContentValues();
		initValues.put(TagDataProvider.ACTION_ID, item.getId());

		initValues.put(TagDataProvider.ACTION_DETAIL, item.getDetail());

		String where = TagDataProvider.TAG_NUMBER + "= '" + item.getCard_id()
				+ "'" + " and " + TagDataProvider.ACTION_CREATE_TIME + "= '"
				+ item.getCreate_time() + "'";
		int c = contentResolver.update(
				TagDataProvider.CONTENT_URI_ACTIONMANAGE, initValues, where,
				null);
		if (c != 0) {
			result = true;
		}
		LogUtil.e("db updateAction " + result);
		return result;
	}

	

	/**
	 * 数据库插入ACTION
	 * 
	 * @param item
	 * @return
	 */
	public boolean insertActionItem(ActionItem item) {
		boolean result = false;
		ContentValues initValues = new ContentValues();
		initValues.put(TagDataProvider.ACTION_ID, item.getId());
		initValues.put(TagDataProvider.TAG_NUMBER, item.getCard_id());
		initValues.put(TagDataProvider.ACTION_DETAIL, item.getDetail());
		initValues.put(TagDataProvider.ACTION_CREATE_TIME,
				item.getCreate_time());
		int type = item.getAction_type();
		initValues.put(TagDataProvider.ACTION_TYPE, type);
		item.setAction_type(type);
		switch (type) {
		case ActionItem.ACTION_OPEN_APPLICATION:
			initValues.put(TagDataProvider.ACTION_PACKAGE_NAME,
					item.getPackage_name());
			break;
		case ActionItem.ACTION_WIFI:
		case ActionItem.ACTION_BLUETOOTH:
		case ActionItem.ACTION_SCREEN_LOCK:
			initValues.put(TagDataProvider.ACTION_SWITCH,
					item.getAction_switch() ? "1" : "0");
			break;
		case ActionItem.ACTION_MESSAGE:
			if (item.getMessage_content() != null) {
				initValues.put(TagDataProvider.ACTION_MESSAGE,
						item.getMessage_content());
			}
		case ActionItem.ACTION_DIA:
			if (item.getDia_number() != null) {
				initValues.put(TagDataProvider.ACTION_DIA_NUMBER,
						item.getDia_number());
			}
			break;
		case ActionItem.ACTION_SCREEN_LIGHT:
			initValues.put(TagDataProvider.ACTION_SCREEN_LIGHT,
					item.getScreen_light());
			break;
		case ActionItem.ACTION_RING_TYPE:
			if (item.getRing_type() != null) {
				initValues.put(TagDataProvider.ACTION_RING_TYPE,
						item.getRing_type());
			}
			break;
		case ActionItem.ACTION_RING_VALUE:
			initValues.put(TagDataProvider.ACTION_RING_VALUE,
					item.getRing_value());
			break;
		case ActionItem.ACTION_RING_NAME:
			initValues.put(TagDataProvider.ACTION_RING_NAME,
					item.getRing_name());
			break;
		}
		Uri uri = contentResolver.insert(
				TagDataProvider.CONTENT_URI_ACTIONMANAGE, initValues);
		if (uri != null) {
			result = true;
		}
		LogUtil.e("db insertAction " + result);
		return result;
	}

	/**
	 * 获取TagItem
	 * 
	 * @return
	 */
	public ArrayList<TagItem> getCards() {
		ArrayList<TagItem> items = new ArrayList<TagItem>();
		TagItem item = null;
		String orderBy = TagDataProvider.TAG_PRIORITY + " desc";
		Cursor cursor = contentResolver.query(
				TagDataProvider.CONTENT_URI_TAGMANAGE, new String[] {}, null,
				null, orderBy);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					item = builtTagItem(cursor);
					items.add(item);
				} while (cursor.moveToNext());
			}
			cursor.close();
			cursor = null;
		}
		Constants.tag_count = items.size();
		if(Constants.tags_in_list!=null){
			for(int s=Constants.tags_in_list.size()-1;s>=0;s--){
				Constants.tags_in_list.remove(s);
			}
			Constants.tags_in_list.addAll(items);
		}else{
		    Constants.tags_in_list = items;
		}
		LogUtil.v("db getTags " + items.size());
		return items;
	}

	/**
	 * 根据卡ID获取TagItem
	 * 
	 * @param card_id
	 *            此应用的ID
	 * @return
	 */
	public TagItem getCard(String card_id) {
		TagItem item = null;
		String where = TagDataProvider.TAG_NUMBER + "= '" + card_id + "'"
				+ " and " + TagDataProvider.TAG_ENABLE + " = '" + "1" + "'";
		String orderBy = TagDataProvider.TAG_PRIORITY + " desc";
		Cursor cursor = contentResolver.query(
				TagDataProvider.CONTENT_URI_TAGMANAGE, new String[] {}, where,
				null, orderBy);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				item = builtTagItem(cursor);
				cursor.close();
				cursor = null;
			}
		}

		LogUtil.v("getCard---isSwitch" + item.getTag_link_param1() );
		if (item.getTag_link_param1() != null) {// 如果是开关,设置下次启动另一个标签
			
			TagItem item_link = getCardByParam1(item.getTag_create_time());
			
			if (item_link == null) {// 说明开关需要取消
				item.setTag_link_param1(null);
			} else {
				int prority = item.getTag_priority();
				item.setTag_priority(item_link.getTag_priority());
				item_link.setTag_priority(prority);	
				updateTagItem(item_link);
			}
			updateTagItem(item);
		}
		return item;
	}

	/**
	 * 根据TAG创建时间获取TagItem
	 * 
	 * @param card_id
	 *            此应用的ID
	 * @return
	 */
	public TagItem getCardByParam1(String create_time) {
		TagItem item = null;
		String where = TagDataProvider.TAG_LINK_PARAM1 + "= '" + create_time
				+ "'" + " and " + TagDataProvider.TAG_ENABLE + " = '" + "1"
				+ "'";
		Cursor cursor = contentResolver.query(
				TagDataProvider.CONTENT_URI_TAGMANAGE, new String[] {}, where,
				null, null);
		
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				item = builtTagItem(cursor);
				cursor.close();
				cursor = null;
			}
		}
		return item;
	}

	/**
	 * 组装TagItem
	 * 
	 * @param cursor
	 * @return
	 */
	private TagItem builtTagItem(Cursor cursor) {
		TagItem item = new TagItem();
		item.setId(cursor.getInt(cursor.getColumnIndex(TagDataProvider.TAG_ID)));
		item.setTag_create_time(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_CREATE_TIME)));
		item.setTag_name(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_NAME)));
		item.setTag_number(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_NUMBER)));
		item.setTag_link_param1(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_LINK_PARAM1)));
		item.setTag_link_param2(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_LINK_PARAM2)));
		item.setTag_describe(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_DESCIBE)));
		item.setTag_priority(cursor.getInt(cursor
				.getColumnIndex(TagDataProvider.TAG_PRIORITY)));
		item.setTag_size(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_SIZE)));
		item.setTag_enable(cursor.getInt(cursor
				.getColumnIndex(TagDataProvider.TAG_ENABLE)) == 0 ? false
				: true);
		item.setTag_use_count(cursor.getInt(cursor
				.getColumnIndex(TagDataProvider.TAG_USE_COUNT)));
		LogUtil.v("builtTagItem--action_tag_enable-" + item.isTag_enable()
				+ getActions(item).size());
		item.addAllAction(getActions(item));
		return item;
	}

	/**
	 * 得到所有同一卡ID的ACTION
	 * 
	 * @param ID
	 * @return
	 */
	synchronized public Vector<ActionItem> getActions(TagItem tag_item) {
		Vector<ActionItem> items = new Vector<ActionItem>();
		ActionItem item = null;
		String where = TagDataProvider.TAG_NUMBER + " = '"
				+ tag_item.getTag_number() + "'" + " and "
				+ TagDataProvider.ACTION_CREATE_TIME + "= '"
				+ tag_item.getTag_create_time() + "'";
		
		Cursor cursor = contentResolver.query(
				TagDataProvider.CONTENT_URI_ACTIONMANAGE, new String[] {},
				where, null, null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					item = builtActionItem(cursor);
					items.add(item);
				} while (cursor.moveToNext());
			}
			cursor.close();
			cursor = null;
		}
		LogUtil.v("action--number" + items.size());
		return items;
	}

	/**
	 * 组装ActionItem
	 * 
	 * @param cursor
	 * @return
	 */
	private ActionItem builtActionItem(Cursor cursor) {
		ActionItem item = new ActionItem();
		item.setId(cursor.getInt(cursor
				.getColumnIndex(TagDataProvider.ACTION_ID)));
		item.setCard_id(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.TAG_NUMBER)));
		item.setDetail(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.ACTION_DETAIL)));
		item.setCreate_time(cursor.getString(cursor
				.getColumnIndex(TagDataProvider.ACTION_CREATE_TIME)));
		int type = cursor.getInt(cursor
				.getColumnIndex(TagDataProvider.ACTION_TYPE));
		item.setAction_type(type);
		switch (type) {
		case ActionItem.ACTION_OPEN_APPLICATION:
			item.setPackage_name(cursor.getString(cursor
					.getColumnIndex(TagDataProvider.ACTION_PACKAGE_NAME)));
			break;
		case ActionItem.ACTION_WIFI:
		case ActionItem.ACTION_BLUETOOTH:
		case ActionItem.ACTION_SCREEN_LOCK:
			item.setAction_switch(cursor.getInt(cursor
					.getColumnIndex(TagDataProvider.ACTION_SWITCH)) == 0 ? false
					: true);
			break;
		case ActionItem.ACTION_MESSAGE:
			item.setMessage_content(cursor.getString(cursor
					.getColumnIndex(TagDataProvider.ACTION_MESSAGE)));
		case ActionItem.ACTION_DIA:
			item.setDia_number(cursor.getString(cursor
					.getColumnIndex(TagDataProvider.ACTION_DIA_NUMBER)));
			break;
		case ActionItem.ACTION_SCREEN_LIGHT:
			item.setScreen_light(cursor.getInt(cursor
					.getColumnIndex(TagDataProvider.ACTION_SCREEN_LIGHT)));
			break;
		case ActionItem.ACTION_RING_TYPE:
			item.setRing_type(cursor.getString(cursor
					.getColumnIndex(TagDataProvider.ACTION_RING_TYPE)));
			break;
		case ActionItem.ACTION_RING_VALUE:
			item.setRing_value(cursor.getInt(cursor
					.getColumnIndex(TagDataProvider.ACTION_RING_VALUE)));
			break;
		case ActionItem.ACTION_RING_NAME:
			item.setRing_name(cursor.getString(cursor
					.getColumnIndex(TagDataProvider.ACTION_RING_NAME)));
			break;
		}
		return item;
	}

	/**
	 * 清除数据库信息
	 * 
	 * @throws Exception
	 */
	public void reinitializationDataBaseTable() throws Exception {
		contentResolver.delete(TagDataProvider.CONTENT_URI_TAGMANAGE, null,
				null);
		contentResolver.delete(TagDataProvider.CONTENT_URI_ACTIONMANAGE, null,
				null);
	}
}
