package com.yachi.nfcvexplorer.bean;

import com.yachi.nfcvexplorer.control.ENUM_ACTION_TYPE;

/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-25
 * 
 * @file 列表中的事件Bean（CHILD）
 * 
 */
public class ActionChildItem {
	public static final String child_titles[][] = { { "WIFI开启或关闭", "蓝牙开启或关闭" },
			{ "铃声类型", "铃声音量" }, { "亮度" }, { "放置一个呼叫", "发送文本消息" },
			{ "打开应用程序" } };
	public static final ENUM_ACTION_TYPE child_type[][] = { { ENUM_ACTION_TYPE.FUNCTION_WIFI,ENUM_ACTION_TYPE.FUNCTION_BLUETOOTH },
		{ ENUM_ACTION_TYPE.FUNCTION_RINGTYPE, ENUM_ACTION_TYPE.FUNCTION_RINGVALUE },
		{ENUM_ACTION_TYPE.FUNCTION_SCREENLIGHT}, 
		{ ENUM_ACTION_TYPE.FUNCTION_DIAL, ENUM_ACTION_TYPE.FUNCTION_MESSAGE },
		{ ENUM_ACTION_TYPE.FUNCTION_APPLICATION} };
	private String title;
	private boolean selected;
	private ENUM_ACTION_TYPE actiontype;

	public ActionChildItem(String title,ENUM_ACTION_TYPE type) {
		this.title = title;
		this.actiontype = type;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the actiontype
	 */
	public ENUM_ACTION_TYPE getActiontype() {
		return actiontype;
	}

	/**
	 * @param actiontype
	 *            the actiontype to set
	 */
	public void setActiontype(ENUM_ACTION_TYPE actiontype) {
		this.actiontype = actiontype;
	}

}
