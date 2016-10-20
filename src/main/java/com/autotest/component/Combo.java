package com.autotest.component;

import org.openqa.selenium.WebDriver;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.Locator;

public class Combo extends Control {
	


	/**
	 * 下拉框按钮选择列表
	 * @param webDriver
	 */
	public Combo(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 根据textfield的发fieldLabel路径获取ID号
	 * @param path
	 * @return
	 */
	public String  getComboID(String path){
		String elementPath=getExtQueryID(path);
		String id =executeJS(elementPath);
		return id;
	}
	
	/**
	 * 点击combo的下拉框按钮
	 * @param id 根据按钮combo的id号
	 */
	public void clickCombo(String id){
		String comoElement="\""+id+"-triggerWrap\"";
		String regexJS="return document.getElementById("+comoElement+
				").getElementsByTagName('td')[1].getElementsByTagName('div')[0].id";
		String cBtn=getId(regexJS);
		Locator locator = new Locator(cBtn);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		try {
			extPage.click(locator);
			CommonOperations.waitTime(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 根据sn号选择点击设备
	 * @param sn
	 */
	public void selectDeviceTree(String sn){
		String regexTree="return document.getElementsByClassName(\"x-tree-view x-fit-"
				+ "item x-tree-view-default x-unselectable\")[0].id";
		String treeID = executeJS(regexTree);
		clearDeviceTree();
		String sCombo=treeID+"-record-"+sn;
		Locator locator = new Locator(sCombo);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		try {
			extPage.click(locator);
			CommonOperations.waitTime(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 清空下拉设备树上勾选的内容
	 */
	public void clearDeviceTree(){
		String regexTree="return document.getElementsByClassName(\"x-tree-view x-fit-"
				+ "item x-tree-view-default x-unselectable\")[0].id";
		String treeID = executeJS(regexTree);
		String sCombo=treeID+"-record-0";
		Locator locator = new Locator(sCombo);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		try {
			extPage.click(locator);
			CommonOperations.waitTime(2);
			extPage.click(locator);
			CommonOperations.waitTime(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	
}
