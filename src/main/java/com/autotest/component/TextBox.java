package com.autotest.component;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.autotest.Control;

public class TextBox extends Control {

	String path=null;
	String keys=null;
	String id=null;
	public TextBox(String path,WebDriver webDriver) {
		super(webDriver);
		this.path = path;
		// TODO Auto-generated constructor stub
	}
	
	public void setValue(String keys){
		this.keys=keys;
	}
	
	public void setID(String component){
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		this.id=getId(js)+component;
		System.out.println("the id is"+id);
		
	}
	
	@Override
	public String getQuery(){
		return "Ext.ComponentQuery.query"+path;
	}
	
	
	
	public void inputTextBox() throws InterruptedException{
		Thread.sleep(2000);
		webDriver.findElement(By.id(this.id)).sendKeys(keys);
	}
	


}
