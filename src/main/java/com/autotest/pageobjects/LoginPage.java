package com.autotest.pageobjects;

import org.openqa.selenium.WebDriver;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.LoginPageConstant;

public class LoginPage extends ExtJSPage {
	
	public static int browserID=0;
	public LoginPage(WebDriver webDriver){
		
		super(webDriver,browserID);
	}
	
	private TextBox txtUserName;
	private TextBox txtPassWord;
	private Button btnLogin;
	private String componentText="-inputEl";
	
	protected void init(){
		txtUserName=new TextBox(LoginPageConstant.userName,webDriver);
		txtPassWord = new TextBox(LoginPageConstant.password,webDriver);
		btnLogin = new Button(LoginPageConstant.btnPath,webDriver);
	}
	
	public void login(String userInfo,String userPassWord) throws InterruptedException{
		init();
		txtUserName.setValue(userInfo);
		txtPassWord.setValue(userPassWord);
		txtUserName.getID(componentText);
		txtPassWord.getID(componentText);
		txtUserName.inputTextBox();
		txtPassWord.inputTextBox();
		btnLogin.click();
	}

}
