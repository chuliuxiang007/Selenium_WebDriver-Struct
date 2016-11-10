package com.autotest.utils;

import java.io.IOException;
import java.sql.Driver;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.autotest.ExtJSPage;
import com.autotest.component.Button;
import com.autotest.component.CheckBox;
import com.autotest.component.Combo;
import com.autotest.component.TextBox;
import com.autotest.component.WindowComponent;
import com.autotest.utils.Locator.ByType;

public class CommonOperations {
	
	public static Logger logger = Logger.getLogger(CommonOperations.class);
	
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
	 * 等待时间：秒
	 * @param sec
	 */
	public static void waitTime(int sec){
		try {
			Thread.sleep(1000*sec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据行号的id点击表格中的编辑按钮
	 * @param columnID
	 * @param driver
	 */
	public static void clickTableEditBtn(String rowID,WebDriver driver){
		Button eBtn = new Button(driver);
		eBtn.clickTable_EditBtn(rowID);
		
	}
	
	/**
	 * 根据行号的id点击表格中的删除按钮
	 * @param rowID
	 * @param driver
	 */
	public static void clickTableDeleteBtn(String rowID,WebDriver driver){
		Button eBtn = new Button(driver);
		eBtn.clickTable_DeleteBtn(rowID);
	}
	
	
	
	
	/**
	 * 获取window弹窗标题内容
	 * @param driver
	 * @return
	 */
	public static String getWindowHead(WebDriver driver){
		WindowComponent winHandle = new WindowComponent();
		String headText=winHandle.getCurrentHeadContext(driver);
		return headText;
	}
	
	
	
	/**
	 * 文本框输入内容
	 * @param textBox
	 * @param textValue
	 * @param driver
	 */
	public static void inputText(String path,String textValue,WebDriver driver){
		TextBox textBox = new TextBox(path,driver);
		textBox.inputTextBox(textValue);
	}
	
	/**
	 * 根据文本框左边标签定位文本框，输入 内容
	 * @param label
	 * @param textValue
	 * @param driver
	 */
	public static void inputLabelText(String label,String textValue,WebDriver driver){
		String labelPath="(\"textfield[fieldLabel='"+label+"']\")[0]";
		TextBox textBox = new TextBox(labelPath,driver);
		textBox.inputTextBox(textValue);
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
	 * 面板只存在一个下拉框按钮
	 * @param driver
	 */
	public static void clickCombBtn(WebDriver driver){
		Combo combo = new Combo(driver);
		combo.clickCombo();
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
	 * 定位显示的 下拉列选框位置：visible
	 * @param driver
	 * @return
	 */
	public static String getVisiableCombo(WebDriver driver){
		String tempJS = null;
		String tempResult=null;
		boolean flag = false;
		tempJS = "return document.getElementsByClassName(\"x-boundlist x-boundlist-"
				+ "floating x-layer x-boundlist-default x-border-box\").length.toString()";	
		int comboCount =Integer.parseInt(executeJS(tempJS, driver));
		if(comboCount == 0){
			logger.error("not found the combo");
			return null;
		}
		logger.info("get the combo count: "+comboCount);
		for(int i = 0 ; i < comboCount; i++){
			tempJS = "return document.getElementsByClassName(\"x-boundlist x-boundlist-"
					+ "floating x-layer x-boundlist-default x-border-box\")["+i+"].getAttribute(\"style\")";
			tempResult = executeJS(tempJS, driver);
			if(!tempResult.contains("display")){
				tempJS = "return document.getElementsByClassName(\"x-boundlist x-boundlist-"
						+ "floating x-layer x-boundlist-default x-border-box\")["+i+"].id";
				tempResult=executeJS(tempJS, driver);
				flag = true;
				return tempResult;
			}			
		}
		if(!flag){
			logger.error("not find the visible combo");
		}
		return null;
	}
	
	/**
	 * Combo的下拉框选择
	 * @param type
	 * @param driver
	 */
	public static void selectOptionCombo(String type ,WebDriver driver){
		logger.info("start select combo options");
		String elementID=getVisiableCombo(driver);
		if(elementID == null){
			logger.error("the combo not visible");
			return;
		}
		String tempJS="return document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\").length.toString()";
		String typeCount=CommonOperations.executeJS(tempJS, driver);
		for(int i = 0; i < Integer.parseInt(typeCount); i++){
			tempJS = "return document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\")["
					+ i+"].textContent";
			if(type.equals(CommonOperations.executeJS(tempJS, driver))){
				tempJS = "document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\")["
						+ i+"].click()";
				CommonOperations.exeJS(tempJS, driver);
			}
		}		
	}
	
	
	/**
	 * 勾选combo下拉树列表框设备
	 * @param sn
	 */
	public static void selectComboTreeDevice(String devices,int number,WebDriver driver){
		String[] devicesItem=null;
		Combo combo = new Combo(driver);
		if(devices == null){
			return;
		}else{
			devicesItem=devices.split(",");
		}
		combo.selectDeviceTree(devicesItem,number);
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
	
	/**
	 * 单选框勾选
	 * @param checkValue
	 * @param driver
	 */
	public static void selectCheckBox(String checkValue,WebDriver driver){
		CheckBox checkboxObj = new CheckBox(driver);
		int checkBoxCount =checkboxObj.getCheckBoxCount();
		if(checkBoxCount == 0){
			logger.error("not found the checkbox");
			return;
		}
		checkboxObj.clearCheckBox(checkBoxCount);
		checkboxObj.selectCheckBox(checkBoxCount,checkValue);
	}
	
	
	
	
//	public static void selectCombo(String elementName,String value,WebDriver driver){
//		Locator locator = new Locator(elementName,20,ByType.className);
//		ExtJSPage extPage=new ExtJSPage(driver);
//		try {
//			List<WebElement> elements=extPage.getElements(driver, locator);
//			for(WebElement e :elements){
//				if(e.getText().equals(value)){
//					e.click();
//				}
//			}
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
//	}


}
