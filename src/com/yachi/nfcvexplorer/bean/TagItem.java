package com.yachi.nfcvexplorer.bean;

import java.util.Vector;

/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-07-13
 * 
 * @file 标签数据Bean
 * 
 */
public class TagItem {
	private long id = 00000;
	/** 卡序列号 */
	private String tag_number = "000000000";
	/** TAG名称 */
	private String tag_name;
	/** TAG描述 */
	private String tag_describe = "";
	/** TAG创建时间 */
	private String tag_create_time = "";
	/** TAG开关参数1 */
	private String tag_link_param1 = null;
	/** TAG开关参数2 */
	private String tag_link_param2 = null;
	/** TAG大小 */
	private String tag_size = "";
	/** TAG优先级 */
	private int tag_priority = 0;
	/** TAG可用否 */
	private boolean tag_enable = true;
	/** TAG使用次数 */
	private int tag_use_count;
	private Vector<ActionItem> action_list;
  
	public TagItem() {
		action_list = new Vector<ActionItem>();
	}
	
	/**
	 * @return the tag_use_count
	 */
	public int getTag_use_count() {
		return tag_use_count;
	}

	/**
	 * @param tag_use_count the tag_use_count to set
	 */
	public void setTag_use_count(int tag_use_count) {
		this.tag_use_count = tag_use_count;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
     * 给标签增加事件
     * @param a_item
     */
	public void addAction(ActionItem a_item) {
		if (!action_list.contains(a_item)) {
			action_list.add(a_item);
		}
	}
	/**
     * 给标签增加事件
     * @param a_item
     */
	public void addAllAction(Vector<ActionItem> a_items) {
			action_list.addAll(a_items);
	}
	/**
     * 删除所有事件
     * @param a_item
     */
	public void removeAllAction() {
			action_list.removeAllElements();
	}
    /**
     * 从标签移除事件
     * @param a_item
     */
	public void removeAction(ActionItem a_item) {
		if (action_list.contains(a_item)) {
			action_list.remove(a_item);
		}
	}
    /**
     * 获取该标签所有事件
     * @return
     */
	public Vector<ActionItem> getActions() {
			return action_list;
	}

	/**
	 * @return the tag_number
	 */
	public String getTag_number() {
		return tag_number;
	}

	/**
	 * @param tag_number
	 *            the tag_number to set
	 */
	public void setTag_number(String tag_number) {
		this.tag_number = tag_number;
	}

	/**
	 * @return the tag_name
	 */
	public String getTag_name() {
		return tag_name;
	}

	/**
	 * @param tag_name
	 *            the tag_name to set
	 */
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	/**
	 * @return the tag_describe
	 */
	public String getTag_describe() {
		return tag_describe;
	}

	/**
	 * @param tag_describe
	 *            the tag_describe to set
	 */
	public void setTag_describe(String tag_describe) {
		this.tag_describe = tag_describe;
	}

	/**
	 * @return the tag_size
	 */
	public String getTag_size() {
		return tag_size;
	}

	/**
	 * @param tag_size
	 *            the tag_size to set
	 */
	public void setTag_size(String tag_size) {
		this.tag_size = tag_size;
	}

	/**
	 * @return the tag_priority
	 */
	public int getTag_priority() {
		return tag_priority;
	}

	/**
	 * @param tag_priority
	 *            the tag_priority to set
	 */
	public void setTag_priority(int tag_priority) {
		this.tag_priority = tag_priority;
	}

	/**
	 * @return the tag_create_time
	 */
	public String getTag_create_time() {
		return tag_create_time;
	}

	/**
	 * @param tag_create_time
	 *            the tag_create_time to set
	 */
	public void setTag_create_time(String tag_create_time) {
		this.tag_create_time = tag_create_time;
	}

	/**
	 * @return the tag_enable
	 */
	public boolean isTag_enable() {
		return tag_enable;
	}

	/**
	 * @param tag_enable
	 *            the tag_enable to set
	 */
	public void setTag_enable(boolean tag_enable) {
		this.tag_enable = tag_enable;
	}

	/**
	 * @return the tag_link_param1
	 */
	public String getTag_link_param1() {
		return tag_link_param1;
	}

	/**
	 * @param tag_link_param1 the tag_link_param1 to set
	 */
	public void setTag_link_param1(String tag_link_param1) {
		this.tag_link_param1 = tag_link_param1;
	}

	/**
	 * @return the tag_link_param2
	 */
	public String getTag_link_param2() {
		return tag_link_param2;
	}

	/**
	 * @param tag_link_param2 the tag_link_param2 to set
	 */
	public void setTag_link_param2(String tag_link_param2) {
		this.tag_link_param2 = tag_link_param2;
	}
	
    @Override
    public boolean equals(Object o) {
    	return (tag_create_time.equals(((TagItem) o).getTag_create_time()));
    }
}
