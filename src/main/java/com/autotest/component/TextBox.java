package com.autotest.component;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.Locator;

public class TextBox extends Control {

	public static Logger logger = Logger.getLogger(TextBox.class );
	
	String path=null;
	String id=null;
	String tempJS=null;
	String component="-inputEl";
	public TextBox(String path,WebDriver webDriver) {
		super(webDriver);
		this.path = path;
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 检查 文科框是否可以输入
	 * @return
	 */
	public boolean getTextEnable(){
		getTextID();
		tempJS="return document.getElementById(\""+this.id+"\").getAttribute(\"aria-invalid\")";
		String result =executeJS(tempJS);
		if(result.equals("false")){
			return false;
		}
		return true;
		
	}
	
	/**
	 * 根据textfield的路径获取文本框的属性id号
	 * @return
	 */
	public String getTextID(){
		this.path=this.getQuery();
		String js="return " + this.path + ".id";
		String tempID=executeJS(js);
		this.id=tempID+this.component;
		return this.id;
	}
	

	
	
	
	
	@Override
	/**
	 * Ext组件查询
	 * @return
	 */
	public String getQuery(){
		return "Ext.ComponentQuery.query"+path;
	}
	
	
	/**
	 * 文本框输入,默认根据文本框的ID
	 */
	public void inputTextBox(String value){
		String textID =getTextID();
		String textValue=value;
		Locator locator = new Locator(textID);
		ExtJSPage extPage=new ExtJSPage(webDriver);
		extPage.type(locator,textValue);
	}
	


}
