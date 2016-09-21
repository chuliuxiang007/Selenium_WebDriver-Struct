package com.autotest;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public  abstract class Control {
	
	protected WebDriver webDriver;
	
	protected Control parent;
	
	public Control(WebDriver webDriver) {
	      this.webDriver = webDriver;
	}
	
	public String getQuery(){
		return StringUtils.EMPTY;
	}
	
	public String getId(){
		JavascriptExecutor executor = (JavascriptExecutor) webDriver;
		String idNumber=(String)executor.executeScript("return " + this.getQuery() + ".id");
		return idNumber ;
	}
	
	public String getId(String js){
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		String idNumber=(String)executor.executeScript(js);
		return idNumber;
	}
}
