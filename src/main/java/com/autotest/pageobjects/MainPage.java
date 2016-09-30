package com.autotest.pageobjects;

import org.openqa.selenium.WebDriver;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;
import com.autotest.component.Label;
import com.autotest.pageconstant.MainPageConstant;
import com.autotest.utils.Locator;

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
		Label mainTitle=new Label(webDriver,MainPageConstant.mainLabel);
		return mainTitle.existLabel();
	}
	
	/**
	 * 获取主页面顶部的Tab页按钮
	 */
	public void getButton(){
		firstPageBtn = new Button(MainPageConstant.firstPagePath, webDriver);
		equipMonitorBtn=new Button(MainPageConstant.equipMonitorPath, webDriver);
		reportTableBtn=new Button(MainPageConstant.reportTablePath, webDriver);
		userCenterBtn= new Button(MainPageConstant.userCenterPath, webDriver);
	}
	
	/**
	 * 点击首页
	 */
	public void clickFirst(){
		firstPageBtn.click();
	}
	
	/**
	 * 点击设备监控
	 */
	public void clickEquipMonitor(){
		equipMonitorBtn.click();
	}
	
	/**
	 * 点击报表
	 */
	public void clickReportTable(){
		reportTableBtn.click();
	}
	
	/**
	 * 点击用户中心
	 */
	public void clickUserCenter(){
		userCenterBtn.click();
	}
	
	
	
	
	

}
