package com.autotest.Module;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import com.autotest.Control;
import com.autotest.ExtJSPage;
import com.autotest.common.ValidateObj;
import com.autotest.common.objects.AlarmObject;
import com.autotest.component.Button;
import com.autotest.component.Combo;
import com.autotest.component.Label;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.CommonOperations;
import com.autotest.utils.Locator;
import com.autotest.utils.Locator.ByType;
import com.sun.org.apache.bcel.internal.generic.Select;

public class AlarmRule {
	public static Logger logger = Logger.getLogger(AlarmRule.class);
	public static AlarmRule singleAlarm=null;
	private WebDriver driver=null;
	private String tempJS=null;
	private int alarmCount =0;
	private WebElement alarmTable=null;
	AlarmObject aObj= new AlarmObject();
	
	//告警规则对象
	

	/**
	 * 告警规则对象初始化
	 * @param webDriver
	 */
	public AlarmRule(WebDriver webDriver) {
		this.driver=webDriver;
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static AlarmRule getInstance(WebDriver driver){
		if(singleAlarm == null){
			singleAlarm=new AlarmRule(driver);
		}
		return singleAlarm;
	}
	
	/**
	 * 检查告警规则表格的元素正确性
	 * @return
	 */
	public boolean check_AlarmTable(){
		String resultJS=null;
		alarmTable = getAalarmTable();
		if(alarmTable == null){
			logger.error("告警规则表不存在");
			return false;
		}
		logger.debug("校验告警规则各个列");
		tempJS="return document.getElementsByClassName(\"x-column-header-text\").length.toString()";
		int colCount=Integer.parseInt(CommonOperations.executeJS(tempJS, driver));
		if(colCount != aObj.columnNumber){
			logger.error("告警规则列不正确");
			return false;
		}
		for(int i = 0 ; i < colCount; i++){
			tempJS="return document.getElementsByClassName(\"x-column-header-text\")["+i+"].textContent";
			resultJS=CommonOperations.executeJS(tempJS, driver);
			if(resultJS.equals(aObj.colAlarmTable[i])){
				Assert.assertTrue(true);
			}else{
				Reporter.log("the column: "+aObj.colAlarmTable[i]+" is not exist");
				Assert.assertTrue(false);
			}
		}
		return true;
	}
	
	/**
	 * 获取告警规则表alarmtable;
	 */
	public WebElement getAalarmTable(){
		Locator loc = new Locator("//table", 3, ByType.xpath);
		ExtJSPage extObj = new ExtJSPage(driver);
		try {
			alarmTable =extObj.getElement(driver, loc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alarmTable;
	}
	
	/**
	 * 获取 告警规则数量
	 * @return
	 */
	public int getAlarmCount(){
		logger.info("get the alarm count");
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\").length.toString()";
		String tCount=CommonOperations.executeJS(tempJS, driver);
		alarmCount =Integer.parseInt(tCount);
		return alarmCount;
	}
	
	/**
	 * 根据行号获取告警规则名称
	 * @param rowID
	 * @return
	 */
	public String getAlarmName(int rowID){
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[0].textContent";
		return CommonOperations.executeJS(tempJS, driver);
	}
	
	/**
	 * 根据行号获取告警规则元素的id号:elementID
	 * @param rowID
	 * @return
	 */
	public String getAlarmID(int rowID){
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")["
				+rowID+"].id";
		return CommonOperations.executeJS(tempJS, driver);
	}
	
	/**
	 * 根据告警规则名称获取元素的id号：elementID
	 * @param name
	 * @return
	 */
	public String getAlarmID(String name){
		alarmCount = getAlarmCount();
		tempJS =null;
		for(int rowID=0; rowID < alarmCount; rowID++){
			if(getAlarmName(rowID).equals(name)){
				return getAlarmID(rowID);
			}
		}
		return null;
	}
	
	/**
	 * 告警信息的读取
	 * @return
	 */
	public List<AlarmObject> getAlarmMessages(){
		//收集告警信息
		int count = getAlarmCount();
		List<AlarmObject> list = new LinkedList<AlarmObject>();
		for(int i = 0 ; i < count ; i ++){
			list.add(getAlarmMessage(i));
		}
		return list;
	}
	
	/**
	 * 单条告警信息收集
	 * @param rowID
	 * @return
	 */
	public AlarmObject getAlarmMessage(int rowID){
		AlarmObject obj = new AlarmObject();
		//告警规则名称
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[0].textContent";
		obj.alarmName=CommonOperations.executeJS(tempJS, driver);
		//告警设备
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[1].textContent";
		obj.alarmDevices=CommonOperations.executeJS(tempJS, driver);
		//级别
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[2].textContent";
		obj.level=CommonOperations.executeJS(tempJS, driver);
		//创建时间
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[3].textContent";
		obj.creatime=CommonOperations.executeJS(tempJS, driver);
		//告警方式
		obj.alarmMethod=getAlarmMethod(rowID,4);
		//描述
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[5].textContent";
		obj.desc=CommonOperations.executeJS(tempJS, driver);
		return obj;
	}
	
	/**
	 * 获取告警方式信息
	 * @param rowID
	 * @param columnID
	 * @return
	 */
	public String getAlarmMethod(int rowID,int columnID){
		logger.info("collect the alarm method");
		int alarmMessage=0;
		String tempJS=null;
		for(int i = 1 ; i <= 3; i++){
			tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
					+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")["
					+columnID+"].getElementsByTagName(\"img\")["+(i-1)+"].getAttribute(\"class\")";
			String result = CommonOperations.executeJS(tempJS, driver);
			if(result.contains("hidden-icon")){
				alarmMessage=alarmMessage+i;
			}
		}
		if(alarmMessage ==1){
			return "发送短信";
		}else if(alarmMessage == 3){
			return "发送邮件";
		}else if(alarmMessage == 0){
			return "发送短信，发送邮件 ";
		}else{
			return null;
		}
	}
	
	/**
	 * 新建告警规则
	 * @param alarm
	 */
	public void addAlarm_Rule(AlarmObject alarm){
		//点击新建按钮
		Button addAlarmBtn = new Button(UCenterPageConstant.alarmRule,driver);
		addAlarmBtn.click();
		//输入告警规则相关内容
		inputAlarmMessage(alarm, true);
		//单击确定
		CommonOperations.clickButton(UCenterPageConstant.alarmCertain, driver);
	}
	
	/**
	 * 告警规则编辑
	 * @param alarm
	 */
	public void editAlarm_Rule(AlarmObject alarm){
		String rowID=getAlarmID(alarm.alarmName);
		CommonOperations.clickTableEditBtn(rowID, driver);
		inputAlarmMessage(alarm,false);
		//单击确定
		CommonOperations.clickButton(UCenterPageConstant.alarmCertain, driver);		
	}
	
	/**
	 * 告警规则删除
	 * @param alarm
	 */
	public void deleteAlarm_Rule(AlarmObject alarm){
		String rowID=getAlarmID(alarm.alarmName);
		CommonOperations.clickTableDeleteBtn(rowID, driver);
	}
	
	/**
	 * 告警规则信息输入：新建：true，编辑：false
	 * @param alarmMessage
	 * @param flag
	 */
	public void inputAlarmMessage(AlarmObject alarmMessage,boolean flag){
		//校验是否进入告警规则信息输入面板
		logger.debug("check the alarm message alert");
		if(CommonOperations.getWindowHead(driver).equals("告警规则")){
			logger.debug("enter into the alarm panel");
		}else{
			logger.error("the alarm window panel not visiable");
			return;
		}
		//输入规则名称
		if(flag){
			CommonOperations.inputText(UCenterPageConstant.alarmName, alarmMessage.alarmName, driver);
		}else{
			TextBox textEle = new TextBox(UCenterPageConstant.alarmName,driver);
			if(!textEle.getTextEnable()){
				logger.debug("the text can't  input the value");
			}else{
				logger.error("the text attrubute input not correct");
				return;
			}
		}
		//输入描述
		CommonOperations.inputText(UCenterPageConstant.alarmDesc, alarmMessage.desc, driver);
		//输入触发条件	
		inputRuleOption(alarmMessage);
		//选择告警设备
		CommonOperations.clickCombBtn(UCenterPageConstant.alarmDevice, driver);
		CommonOperations.selectComboTreeDevice(alarmMessage.alarmDevices,1, driver);
		CommonOperations.clickCombBtn(UCenterPageConstant.alarmDevice, driver);
		//响应动作
		//级别
		String labelPath="(\"label[text='创建一个']\")[0]";
		Label la = new Label(driver,labelPath);
		String tempID =la.getID();
		String[] strNumber=tempID.split("-");
		int standNumber=Integer.parseInt(strNumber[1]);
		String comboID="combo-"+(standNumber+1);
		CommonOperations.clickComboByID(comboID, driver);
		CommonOperations.selectOptionCombo(alarmMessage.level, driver);
		//告警方式选择
		CommonOperations.selectCheckBox(alarmMessage.alarmMethod, driver);
		

	}
	


	/**
	 * 触发条件
	 * @param alarmMessage
	 */
	private void inputRuleOption(AlarmObject alarmMessage){
		//获取触发条件的elementID号 
		Combo combo =new Combo(driver);
		String elementID=combo.getComboID(UCenterPageConstant.alarmOption);
		//分别计算其它条件的elementID号
		String[] strNumber=elementID.split("-");
		int standNumber=Integer.parseInt(strNumber[1]);
		//告警类型		
		//告警类型选择
		CommonOperations.clickComboByID(elementID, driver);
		CommonOperations.selectOptionCombo(alarmMessage.alarmType, driver);
		//告警类型二级选择
		String elementID1 =strNumber[0]+"-"+(standNumber+1);
		CommonOperations.clickComboByID(elementID1, driver);
		CommonOperations.selectOptionCombo(alarmMessage.alarmType_SecType, driver);
		//告警时间条件设置
		String timeOpt="textfield-"+(standNumber+3);
		String textPath="(\"textfield[id='"+timeOpt+"']\")[0]";
		CommonOperations.inputText(textPath, String.valueOf(alarmMessage.alarmTime_Number), driver);
		//分钟，小时，天
		String elementID2=strNumber[0]+"-"+(standNumber+4);
		CommonOperations.clickComboByID(elementID2, driver);
		CommonOperations.selectOptionCombo(alarmMessage.alarmTime_Type, driver);
		//告警条件设置
		String elementID3=strNumber[0]+"-"+(standNumber+5);
		CommonOperations.clickComboByID(elementID3, driver);
		CommonOperations.selectOptionCombo(alarmMessage.alarmRegex, driver);
		//时间参数设置
		String elementID4="textfield-"+(standNumber+6);
		textPath="(\"textfield[id='"+elementID4+"']\")[0]";
		CommonOperations.inputText(textPath, String.valueOf(alarmMessage.alarmType_Value), driver);				
	}
	
	
	
}
