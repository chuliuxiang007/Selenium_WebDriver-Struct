package com.autotest.component;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.utils.Locator;

public class TextBox extends Control {

	String path=null;
	String keys=null;
	String id=null;
	String component="-inputEl";
	public TextBox(String path,WebDriver webDriver) {
		super(webDriver);
		this.path = path;
		// TODO Auto-generated constructor stub
	}
	
	public void setValue(String keys){
		this.keys=keys;
	}
	
	public void getID(String component){
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		String cID=getId(js);
		this.id=getId(js)+component;
		System.out.println("the id is"+id);
		
	}
	
	public void getID(){
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		String cID=getId(js);
		this.id=getId(js)+this.component;
		System.out.println("the id is"+id);
		
	}
	
	/**
	 * textfield的标签的路径获取id号
	 * @return
	 */
	public String getParentID(){
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		String cID=getId(js);
		return cID;
	}
	
	@Override
	public String getQuery(){
		return "Ext.ComponentQuery.query"+path;
	}
	
	
	/**
	 * 文本框输入
	 */
	public void inputTextBox(){
		Locator locator = new Locator(this.id);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		extPage.type(locator, keys);
	}
	


}
