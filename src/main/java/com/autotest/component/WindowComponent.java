package com.autotest.component;


import org.openqa.selenium.WebDriver;

import com.autotest.pageconstant.GlobalConstant;
import com.autotest.utils.CommonOperations;

public class WindowComponent {
	
	private String tempJS=null;
	private String result=null;

	public WindowComponent() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取当前弹窗窗口ID号
	 * @return
	 */
	public String getWindowID(WebDriver driver){
		tempJS="return document.getElementsByClassName(\"x-window\")[0].id";
		result =CommonOperations.executeJS(tempJS, driver);
		return result;
	}
	
	/**
	 * 获取当前窗口head的内容,根据窗口的id号
	 * @param id
	 * @return
	 */
	public String getHeadContext(String id){
		String regularContext="return document.getElementById(\""+id+"_header\").textContent";
		return regularContext;		
	}
	
	/**
	 * 获取当前唯一弹窗的标题栏内容：context
	 * @return
	 */
	public String getCurrentHeadContext(WebDriver driver){
		tempJS="return document.getElementsByClassName(\"x-window\")[0]"
				+ ".getElementsByClassName(\"x-header-text x-window-header-text x-window-header-text-default\")"
				+ "[0].textContent";
		result =CommonOperations.executeJS(tempJS, driver);
		return result;
	}
	
	/**
	 * 获取弹窗的各个item的ID号
	 * @param labelName
	 * @return
	 */
	public String getLabelID(String labelName){
		String regexLabel="Ext.ComponentQuery.query(\"textfield[fieldLabel='"+labelName+"']\")[0]";
		return regexLabel;
	}
	
	
	
	
	

	
}
