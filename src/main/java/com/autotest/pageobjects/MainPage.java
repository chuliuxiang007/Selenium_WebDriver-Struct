package com.autotest.pageobjects;

import org.openqa.selenium.WebDriver;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;

public class MainPage extends ExtJSPage {
	
	public static Button firstPageBtn;
	public static Button equipMonitorBtn;
	public static Button reportTableBtn;
	public static Button userCenterBtn;

	public MainPage(WebDriver webDriver) {
		super(webDriver);
		getButton();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 验证是否成功跳转到主页面：ture:成功 ， false：失败
	 * @return
	 */
	public boolean isMainPage(){
		return true;
	}
	
	/**
	 * 获取主页面顶部的Tab页按钮
	 */
	public void getButton(){
		String firstPagePath="(\"button[text='首页']\")[0].id";
		String equipMonitorPath="(\"button[text='设备监控']\")[0].id";
		String reportTablePath="(\"button[text='报表']\")[0].id";
		String userCenterPath="(\"button[text='用户中心']\")[0].id";
		firstPageBtn = new Button(firstPagePath, webDriver);
		equipMonitorBtn=new Button(equipMonitorPath, webDriver);
		reportTableBtn=new Button(reportTablePath, webDriver);
		userCenterBtn= new Button(userCenterPath, webDriver);
	}

}
