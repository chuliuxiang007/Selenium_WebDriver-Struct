package com.autotest.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;
import com.autotest.component.Combo;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.UCenterPageConstant;

public class CommonOperations {
	
	/**
	 * 执行js脚本
	 * @param js
	 * @param driver
	 * @return 返回执行记过
	 */
	public static String executeJS(String js,WebDriver driver){		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String resultJS=(String)executor.executeScript(js);
		int count = 0;
		while(resultJS == null){
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
			resultJS=(String)executor.executeScript(js);
			
		}
		return resultJS;
	}
	
	/**
	 * 文本框输入内容
	 * @param textBox
	 * @param textValue
	 * @param driver
	 */
	public static void inputText(String path,String textValue,WebDriver driver){
		TextBox textBox = new TextBox(path,driver);
		textBox.getID();
		textBox.setValue(textValue);
		textBox.inputTextBox();
	}
	
	/**
	 * 下拉框按钮操作：右边按钮点击
	 * @param path
	 * @param driver
	 */
	public static void clickCombBtn(String path,WebDriver driver){
		Combo combo = new Combo(driver);
		String elementID =combo.getComboID(path);
		combo.clickCombo(elementID);
		
	}
	
	/**
	 * 勾选combo下拉树列表框设备
	 * @param sn
	 */
	public static void selectComboTreeDevice(String sn,WebDriver driver){
		Combo combo = new Combo(driver);
		combo.selectDeviceTree(sn);
	}
	
	public static void clickButton(String path,WebDriver driver){
		Button btn = new Button(path, driver);
		btn.click();
	}


}
