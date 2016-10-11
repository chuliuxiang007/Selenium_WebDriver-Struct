package com.autotest.utils;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;
import com.autotest.component.Combo;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.Locator.ByType;

public class CommonOperations {
	
	/**
	 * 执行js脚本
	 * @param js
	 * @param driver
	 * @return 返回执行结果
	 */
	public static String executeJS(String js,WebDriver driver){		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String resultJS=null;
		int count = 0;
		while(resultJS == null){
			try {
				Thread.sleep(3000);
				resultJS = (String)executor.executeScript(js);
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
	 * 执行js脚本
	 * @param js
	 * @param driver
	 * @return 无返回结果
	 */
	public static void exeJS(String js,WebDriver driver){		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String resultJS=null;
		int count = 0;
		try {
			Thread.sleep(3000);
			resultJS = (String)executor.executeScript(js);
			System.out.println("the count is "+count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
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
	 * @param 根据combo的path
	 * @param driver
	 */
	public static void clickCombBtn(String path,WebDriver driver){
		Combo combo = new Combo(driver);
		String elementID =combo.getComboID(path);
		combo.clickCombo(elementID);
		
	}
	/**
	 * 下拉框按钮操作：右边按钮点击
	 * @param elementID 根据Combo的id号
	 * @param driver
	 */
	public static void clickComboByID(String elementID,WebDriver driver){
		Combo combo = new Combo(driver);
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
	
	/**
	 * 按钮：单击
	 * @param path 根据按钮的元素路径
	 * @param driver
	 */
	public static void clickButton(String path,WebDriver driver){
		Button btn = new Button(path, driver);
		btn.click();
	}
	
	
	public static void selectCombo(String elementName,String value,WebDriver driver){
		Locator locator = new Locator(elementName,20,ByType.className);
		ExtJSPage extPage=new ExtJSPage(driver);
		try {
			List<WebElement> elements=extPage.getElements(driver, locator);
			for(WebElement e :elements){
				if(e.getText().equals(value)){
					e.click();
				}
			}
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}


}
