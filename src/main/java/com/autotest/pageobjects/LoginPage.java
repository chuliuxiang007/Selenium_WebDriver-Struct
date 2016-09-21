package com.autotest.pageobjects;

import org.openqa.selenium.WebDriver;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;
import com.autotest.component.TextBox;

public class LoginPage extends ExtJSPage {
	
	
	public LoginPage(WebDriver webDriver){
		super(webDriver);
	}
	
	private TextBox txtUserName;
	private TextBox txtPassWord;
	private Button btnLogin;
	private String componentText="-inputEl";
	
	@Override 
	protected void init(){
		String userName="(\"textfield[name='username']\")[0]";
		String password="(\"textfield[name='password']\")[0]";
		String btnPath="(\"button[text='登录']\")[0].el";
		txtUserName=new TextBox(userName,webDriver);
		txtPassWord = new TextBox(password,webDriver);
		btnLogin = new Button(btnPath,webDriver);
	}
	
	public void login(String userInfo,String userPassWord) throws InterruptedException{
		init();
		txtUserName.setValue(userInfo);
		txtPassWord.setValue(userPassWord);
		txtUserName.setID(componentText);
		txtPassWord.setID(componentText);
		btnLogin.setID();
		txtUserName.inputTextBox();
		txtPassWord.inputTextBox();
		btnLogin.click();
	}

}
