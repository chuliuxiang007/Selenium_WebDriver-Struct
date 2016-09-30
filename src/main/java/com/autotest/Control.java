package com.autotest;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public  abstract class Control {
	
	protected WebDriver webDriver;
	
	protected Control parent;
	

	
	public Control(WebDriver webDriver) {
	      this.webDriver = webDriver;
	}
	
	
	public String getQuery(){
		return StringUtils.EMPTY;
	}
	
	/**
	 * Ext组件查询js脚本组装
	 * @param path
	 * @return
	 */
	public String getExtQueryID(String path){
		return "return Ext.ComponentQuery.query"+path+".id";
	}
	
	public String getId(){
		JavascriptExecutor executor = (JavascriptExecutor) webDriver;
		String idNumber=(String)executor.executeScript("return " + this.getQuery() + ".id");
		return idNumber ;
	}
	
	public String getId(String js){
		String idNumber=null;
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		idNumber=(String)executor.executeScript(js);
		int count=0;
		while(idNumber == null){
			try {
				 Thread.sleep(3000);
				count=count+1;
				System.out.println("the count is "+count);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(count == 20){
				return null;
			}
			idNumber=(String)executor.executeScript(js);
			
		}
		return idNumber;
	}
	
	/**
	 * 执行js脚本
	 * @param js
	 * @return
	 */
	public String executeJS(String js){
		String idNumber=null;
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		idNumber=(String)executor.executeScript(js);
		int count=0;
		while(idNumber == null){
			try {
				 Thread.sleep(3000);
				count=count+1;
				System.out.println("the count is "+count);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(count == 20){
				return null;
			}
			idNumber=(String)executor.executeScript(js);
			
		}
		return idNumber;
	}
}
