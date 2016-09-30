package com.autotest.component;

import org.openqa.selenium.WebDriver;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.utils.Locator;

public class Label extends Control {
	
	private String path;
	private String id;

	public Label(WebDriver webDriver,String path) {
		super(webDriver);
		this.path =path;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getQuery(){
		return "Ext.ComponentQuery.query"+path;
	}
	
	/**
	 * 获取label的ID号
	 */
	public void getID() {
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		this.id=getId(js);
		System.out.println("the id is "+id);
	}
	
	/**
	 * 校验label标签是否存在
	 */
	public boolean existLabel(){
		boolean flag=false;
		getID();
		if(this.id==null){
			return false;
		}
		Locator locator = new Locator(this.id);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		try {
			flag=extPage.isElementPresent(locator, 60);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
