package com.autotest.component;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.Locator;

public class Combo extends Control {
	public static Logger logger = Logger.getLogger(Combo.class);
	String tempJS=null;
	String result=null;


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
	 * 点击combo的下拉框按钮
	 *面板内只存在一个下拉框
	 */
	public void clickCombo(){
		String regexJS="return document.getElementsByClassName(\"x-form-arrow-trigger\")";
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
	 * 根据设备名称选择点击设备
	 * @param device
	 */
	public void selectDeviceTree(String[] devices,int option){
		int treeLength=getDeviceTreeLength();
		if(option == 1){
			clearDeviceTree();
		}else if(option ==2){
			clearDeviceTree(treeLength);
		}		
		for(int i = 0 ; i < treeLength; i++){
			tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-tree-node-leaf\")["
					+ i+"].textContent";
			result=executeJS(tempJS);
			for(int j = 0 ; j < devices.length; j++){
				if(devices[j].equals(result)){
					tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-tree-node-leaf\")["
							+ i+"].id";
					result=executeJS(tempJS);
					Locator locator = new Locator(result);
					ExtJSPage extPage=new ExtJSPage(webDriver);
					try {
						extPage.click(locator);
						CommonOperations.waitTime(2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				logger.debug("the device not select: "+devices[j]);
			}
		}
	}
	
	/**
	 * 获取设备树上设备的数量
	 * @return
	 */
	public int getDeviceTreeLength(){
		String regexTree="return document.getElementsByClassName(\"x-grid-row x-grid-tree-node-leaf\").length.toString()";
		int count=Integer.parseInt(executeJS(regexTree));
		return count;
	}
	
	
	
	/**
	 * 清空下拉设备树上勾选的内容
	 * 存在“全部设备”
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
	
	/**
	 *清空下拉设备树上勾选的内容
	 * 不存在“全部设备“
	 * @param count
	 */
	public void clearDeviceTree(int count){
		for(int i = 0 ; i < count; i++){
			tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-tree-node-leaf\")["
					+ i+"].getElementsByClassName(\"x-tree-checkbox-checked\").length.toString()";
			result=executeJS(tempJS);
			int number = Integer.parseInt(result);
			if(number != 0){
				tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-tree-node-leaf\")["
						+ i+"].id";
				result=executeJS(tempJS);
				Locator locator = new Locator(result);
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
	}
	


	
}
