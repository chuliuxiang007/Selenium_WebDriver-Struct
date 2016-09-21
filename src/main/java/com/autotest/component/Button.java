package com.autotest.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.autotest.Control;

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
	
	public void setID() {
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		this.id=getId(js);
		System.out.println("the id is "+id);
	}
	
	public void click(){
		webDriver.findElement(By.id(this.id)).click();
	}
}
