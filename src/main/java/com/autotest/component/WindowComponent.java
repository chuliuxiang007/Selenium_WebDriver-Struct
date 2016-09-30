package com.autotest.component;

import org.openqa.selenium.WebDriver;

import com.autotest.Control;

public class WindowComponent extends Control {

	public WindowComponent(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取当前弹窗窗口
	 * @return
	 */
	public String getWindowID(){
		return "document.getElementsByClassName(\"x-window\")[0]";
	}
	
	/**
	 * 获取当前窗口head的内容
	 * @param id
	 * @return
	 */
	public String getHeadContext(String id){
		String regularContext="document.getElementById(\""+id+"_header\").textContent";
		return regularContext;		
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
