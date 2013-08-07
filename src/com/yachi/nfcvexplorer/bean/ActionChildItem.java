package com.yachi.nfcvexplorer.bean;

import com.yachi.nfcvexplorer.control.ACTION_TYPE;

public class ActionChildItem {
	public static final String child_titles[][] = { { "WIFI开启或关闭", "蓝牙开启或关闭" },
			{ "铃声类型", "铃声音量" }, { "亮度" }, { "放置一个呼叫", "发送文本消息" },
			{ "打开应用程序" } };
	public static final ACTION_TYPE child_type[][] = { { ACTION_TYPE.FUNCTION_WIFI,ACTION_TYPE.FUNCTION_BLUETOOTH },
		{ ACTION_TYPE.FUNCTION_RINGTYPE, ACTION_TYPE.FUNCTION_RINGVALUE },
		{ACTION_TYPE.FUNCTION_SCREENLIGHT}, 
		{ ACTION_TYPE.FUNCTION_DIAL, ACTION_TYPE.FUNCTION_MESSAGE },
		{ ACTION_TYPE.FUNCTION_APPLICATION} };
	private String title;
	private boolean selected;
	private ACTION_TYPE actiontype;

	public ActionChildItem(String title,ACTION_TYPE type) {
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
	public ACTION_TYPE getActiontype() {
		return actiontype;
	}

	/**
	 * @param actiontype
	 *            the actiontype to set
	 */
	public void setActiontype(ACTION_TYPE actiontype) {
		this.actiontype = actiontype;
	}

}