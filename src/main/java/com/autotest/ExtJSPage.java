package com.autotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class ExtJSPage {
	public WebDriver webDriver;
	private String baseUrl="http://10.180.137.61/login/index.html";
	
	public ExtJSPage(WebDriver webDriver){
		webDriver = new FirefoxDriver();
		//如果firefox浏览器打不开，则 加上下面内容 ，填写firefox的正确内容；
		//System.setProperty("webdriver.firefox.bin", "H:\\Program Files\\Mozilla Firefox\\firefox.exe");
		webDriver.get(baseUrl);
		webDriver.manage().window().maximize();
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		this.webDriver=webDriver;
		
	}
	
	protected void init(){
		
	}

}
