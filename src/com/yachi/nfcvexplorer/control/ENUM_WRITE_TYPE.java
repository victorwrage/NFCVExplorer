/**
 * 
 */
package com.yachi.nfcvexplorer.control;


/**
 * @firm 长沙江泓信息技术有限公司
 * 
 * @author xiaoyl
 * @date 2013-08-01
 * 
 * @file 写入标签功能枚举类
 * 
 */
public enum ENUM_WRITE_TYPE {
	  /**写入网址*/
	  WRITE_WEBSITE("写入网址") ,
	  /**写入联系人*/
	  WRITE_CONTACT("写入联系人"),
      /**写入电话*/
	  WRITE_DIAL_NUMBER("写入电话"),
      /**写入文本*/
	  WRITE_TEXT("写入文本"),
      /**写入邮件*/
	  WRITE_EMAIL("写入邮件");
	  /**描述*/
	  private String mKey;

      ENUM_WRITE_TYPE(String key ){
    	  mKey = key;
      }
      /**
       * 获取描述
       * @return
       */
      public String getKey() {
		return mKey;
	  }

}