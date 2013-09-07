package com.yachi.nfcvexplorer.gui.framgment;

import com.yachi.nfcvexplorer.R;
import com.yachi.nfcvexplorer.control.IFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 使用帮助\简介 界面(Fragment)
 * @author xiaoyl
 * @date 2013-07-20
 */
public class FramgmentHelp extends Fragment implements IFragment{
        private   String title = "使用帮助";
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
        		Bundle savedInstanceState) {
           View view =  inflater.inflate(R.layout.buytag_frame_layout, null);
           TextView tv = (TextView) view.findViewById(R.id.help_tv);
           StringBuilder builder = new StringBuilder();

               for (String dialog : DIALOGUE) {
                     builder.append(dialog).append("\n\n");
               }
           
           tv.setText(builder.toString());
        	return view;
        }
		@Override
		public String getTitle() {
			return title;
		}
		/** 描述*/
		public static final String[] DIALOGUE = new String[] {
            "这是一个关于安卓NFC的应用," ,

            "设想自己在某种环境下的要求", 
            "比如," ,
            "同时打开WIFI，以及地图," ,
            "又或者," +
            "1秒钟内拨打某个号码," ,
            "在这里," ,
            "您只需要一次轻轻点击屏幕上的图标," ,
            "把所联想的事件保存起来," ,
            "这样," ,
            "下次你便可以在触碰一下标签后同时触发" ,
            "很方便吧," ,
            "试试感觉如何."
        
    };
}
