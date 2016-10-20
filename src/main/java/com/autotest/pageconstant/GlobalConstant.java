package com.autotest.pageconstant;

import org.openqa.selenium.WebDriver;

public class GlobalConstant {
	
	public static WebDriver driver=null;
	
	public static String loginPath=".//src/test/resources/TestData/UserInfo";
	public static String loginFile = "UserInfo.xlsx";
	public static String loginSheet="UserLogin";
	
	public static String dataPath=".//src/test/resources/TestData/";
	
	//弹窗中确定按钮
	public static String alertCertain = "(\"button[text='确定']\")[0]";
	
	public static String newAdd="(\"button[text='新建']\")[0]";

}
