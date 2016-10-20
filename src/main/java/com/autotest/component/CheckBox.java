 package com.autotest.component;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.Module.AlarmRule;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.Locator;

public class CheckBox extends Control {
	String tempJS=null;
	String result=null;

	public static Logger logger = Logger.getLogger(CheckBox.class);
	public CheckBox(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取checkBox的数量
	 * @return
	 */
	public int getCheckBoxCount(){
		tempJS="return document.getElementsByClassName(\"x-form-field x-form-checkbox x-form-cb\").length.toString()";
		int checkBoxCount =Integer.parseInt(executeJS(tempJS)) ;
		return checkBoxCount;
	}
	
	/**
	 * 根据checkbox的nubmer编号获取checkbox的id号
	 * @param i
	 * @return
	 */
	public String getCheckBoxID(int i){
		logger.debug("获取checkbox的id号");
		tempJS="return document.getElementsByClassName(\"x-form-field x-form-checkbox x-form-cb\")["+i+"].id";
		result=executeJS(tempJS);
		String[] tempChecks=result.split("-");
		return tempChecks[0]+"-"+tempChecks[1];
	}
	
	/**
	 * 根据checkbox的nubmer编号获取checkbox是否勾选
	 * @param i
	 * @return
	 */
	public boolean getCheckBoxStatus(int i){
		logger.debug("获取复选框勾选属性");
		String checkBoxID=getCheckBoxID(i);
		tempJS="return document.getElementById(\""+checkBoxID+"\").getAttribute(\"class\")";
		result=executeJS(tempJS);
		if(result.contains("x-form-cb-checked")){
			logger.debug("复选框勾选");
			return true;
		}else{
			return false;
		}
	}
	 
	/**
	 * 清空复选框勾选属性
	 * @param count
	 */
	public void clearCheckBox(int count){
		logger.debug("清空复选框勾选属性");
		for(int i = 0 ; i < count; i++){
			if(getCheckBoxStatus(i)){
				String checkID=getCheckBoxID(i);
				Locator loc = new Locator(checkID);
				ExtJSPage extObj = new ExtJSPage(webDriver);
				extObj.findElement(webDriver, loc).click();
				CommonOperations.waitTime(1);
			}
		}
	}
	
	
	/**
	 * 勾选相应的checkBox，根据复选框内容
	 * @param count
	 * @param checkValue
	 */
	public void selectCheckBox(int count,String checkValue){
		logger.info("复选框勾选相应的内容： "+checkValue);
		for(int i = 0 ; i < count; i++){
			tempJS="return document.getElementsByClassName(\"x-form-cb-label x-form-cb-label-after\")["+i+"].textContent";
			result =executeJS(tempJS);
			if(checkValue.equals(result)){
				tempJS="return document.getElementsByClassName(\"x-form-field x-form-checkbox x-form-cb\")["+i+"].id";
				result=executeJS(tempJS);
				Locator loc = new Locator(result);
				ExtJSPage extObj = new ExtJSPage(webDriver);
				extObj.findElement(webDriver, loc).click();
				CommonOperations.waitTime(1);
			}
		}
	}
	
	

}
