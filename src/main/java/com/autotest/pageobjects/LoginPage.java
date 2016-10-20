package com.autotest.pageobjects;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.GlobalConstant;
import com.autotest.pageconstant.LoginPageConstant;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.ReadExcelUtil;

public class LoginPage extends ExtJSPage {
	
	public static int browserID=0;
	public static Logger logger = Logger.getLogger(LoginPage.class);
	public LoginPage(WebDriver webDriver){
		
		super(webDriver,browserID);
	}
	
	private TextBox txtUserName;
	private TextBox txtPassWord;
	private Button btnLogin;
	
	/**
	 * 用户登录模块初始化
	 */
	protected void init(){
		txtUserName=new TextBox(LoginPageConstant.userName,webDriver);
		txtPassWord = new TextBox(LoginPageConstant.password,webDriver);
		btnLogin = new Button(LoginPageConstant.btnPath,webDriver);
	}
	
	/**
	 * 用户登录 
	 * @param userInfo
	 * @param userPassWord
	 */
	public void login(String userInfo,String userPassWord){
		init();
		logger.info("用户："+userInfo+"登录");
		txtUserName.inputTextBox(userInfo);
		txtPassWord.inputTextBox(userPassWord);
		btnLogin.click();
	}
	
	/**
	 * 默认路径下用户数据的用户登录
	 */
	public void loginDefault(){
		Object[][] readData=null;
		try {
		readData = ReadExcelUtil.readExcel(GlobalConstant.loginPath, 
				GlobalConstant.loginFile, GlobalConstant.loginSheet);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		String userName = readData[0][0].toString();
		String passWord = readData[0][1].toString();
		login(userName,passWord);
		CommonOperations.waitTime(10);
	}
}
