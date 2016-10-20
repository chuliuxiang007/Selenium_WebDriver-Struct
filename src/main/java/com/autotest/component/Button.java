package com.autotest.component;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.pageconstant.GlobalConstant;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.Locator;
import com.autotest.utils.Locator.ByType;

public class Button extends Control {

	public static Logger logger = Logger.getLogger(Button.class);
	
	private String path;
	private String id;
	
	

	
	public Button (String path,WebDriver webDriver){
		super(webDriver);
		this.path =path;
	}
	
	public Button (WebDriver webDriver){
		super(webDriver);
	}
	
	@Override
	public String getQuery(){
		return "Ext.ComponentQuery.query"+path;
	}
	
	/**
	 * 获取按钮ID号
	 */
	public void getID() {
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		this.id=executeJS(js);
	}
	

	
	/**
	 * 校验button的是否存在
	 * @return
	 */
	public boolean existButton(){
		getID();
		if(this.id != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 单击按钮
	 */
	public void click(){
		getID();
		Locator locator = new Locator(this.id);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		try {
			extPage.click(locator);
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据表格的row的id属性号，点击编辑按钮 
	 */
	public void clickTable_EditBtn(String rowID ){
		String elementPath="//tr[@id='"+rowID +"']"+"//img[@src='resources/edit.png']";
		Locator loc = new Locator(elementPath, 3, ByType.xpath);
		ExtJSPage extObj = new ExtJSPage(webDriver);
		try {
			WebElement ele =extObj.getElement(webDriver, loc);
			ele.click();
			CommonOperations.waitTime(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据表格的row的id属性号，点击删除按钮 
	 * @param rowID
	 */
	public void clickTable_DeleteBtn(String rowID){
		String elementPath="//tr[@id='"+rowID +"']"+"//img[@src='resources/delete.png']";
		Locator loc = new Locator(elementPath, 3, ByType.xpath);
		ExtJSPage extObj = new ExtJSPage(webDriver);
		try {
			WebElement ele =extObj.getElement(webDriver, loc);
			ele.click();
			CommonOperations.waitTime(1);
			if(CommonOperations.getWindowHead(webDriver) == null){
				logger.error("未弹出 窗口");
				return;
			}
			logger.info("确认删除");
			CommonOperations.clickButton(GlobalConstant.alertCertain, webDriver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 根据按钮id号点击按钮
	 * @param elementID
	 */
	public void click(String elementID){
		Locator locator = new Locator(elementID);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		try {
			extPage.click(locator);
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
