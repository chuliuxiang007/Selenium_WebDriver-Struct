package com.autotest.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.utils.Locator;

public class Button extends Control {

	private String path;
	private String id;
	
	

	
	public Button (String path,WebDriver webDriver){
		super(webDriver);
		this.path =path;
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
		System.out.println("the id is "+id);
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
