package com.autotest.Module;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.autotest.Control;
import com.autotest.common.objects.AlarmObject;
import com.autotest.component.Button;
import com.autotest.component.Combo;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.CommonOperations;
import com.sun.org.apache.bcel.internal.generic.Select;

public class AlarmRule {
	
	public static AlarmRule singleAlarm=null;
	private WebDriver driver=null;
	private String tempJS=null;
	private int alarmCount =0;
	
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
	 * 获取 告警规则数量
	 * @return
	 */
	public int getAalrmCount(){
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
	public String getAalarmID(String name){
		alarmCount = getAalrmCount();
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
		int count = getAalrmCount();
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
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[4].textContent";
		obj.alarmMethod=CommonOperations.executeJS(tempJS, driver);
		//描述
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\")"	
				+ "["+rowID+"].getElementsByClassName(\"x-grid-cell-inner\")[5].textContent";
		obj.desc=CommonOperations.executeJS(tempJS, driver);
		return obj;
	}
	
	/**
	 * 新建告警规则
	 * @param alarm
	 */
	public void addAlarmRule(AlarmObject alarm){
		//点击新建按钮
		Button addAlarmBtn = new Button(UCenterPageConstant.alarmRule,driver);
		addAlarmBtn.click();
		//输入告警规则相关内容
		inputAlarmMessage(alarm, true);
		//单击确定
		CommonOperations.clickButton(UCenterPageConstant.alarmCertain, driver);
	}
	
	/**
	 * 告警规则信息输入：新建：true，编辑：false
	 * @param alarmMessage
	 * @param flag
	 */
	public void inputAlarmMessage(AlarmObject alarmMessage,boolean flag){
		//输入规则名称
		if(flag){
			CommonOperations.inputText(UCenterPageConstant.alarmName, alarmMessage.alarmName, driver);
		}
		//输入描述
		CommonOperations.inputText(UCenterPageConstant.alarmDesc, alarmMessage.desc, driver);
		//输入触发条件	
		inputRuleOption(alarmMessage);
		//选择告警设备
		CommonOperations.clickCombBtn(UCenterPageConstant.alarmDevice, driver);
		CommonOperations.selectComboTreeDevice("2307440130001328", driver);

	}
	
	/**
	 * 告警类型选择
	 * @param type
	 */
	private void selectAlarmType(String type){
		String tempJS = null;
		String js = "return document.getElementsByClassName(\"x-boundlist x-boundlist-"
				+ "floating x-layer x-boundlist-default x-border-box\")[0].id";
		String elementID =CommonOperations.executeJS(js, driver);
		tempJS="return document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\").length";
		//String typeCount=CommonOperations.executeJS(tempJS, driver);
		
		for(int i = 0; i < Integer.parseInt("2"); i++){
			tempJS = "return document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\")["
					+ i+"].textContent";
			if(type.equals(CommonOperations.executeJS(tempJS, driver))){
				String tempPath="//div[@id='"+elementID+"']//li["+i+"]";
				WebElement elementPart = driver.findElement(By.xpath(tempPath));
				if(elementPart == null){
					System.out.println("the element is not exist");
				}
				tempJS = "document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\")["
						+ i+"].click()";
				CommonOperations.exeJS(tempJS, driver);
			}
		}
	}

	/**
	 * 告警内容信息填写，还未完成，需要继续完成
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
		String alarmType=elementID;
		CommonOperations.clickComboByID(alarmType, driver);
		//选择资源
		selectAlarmType("状态");
		//告警条件设置
		String alarmOption =strNumber[0]+"-"+(standNumber+1);
		//时间参数设置
		String timeOpt="textfield-"+(standNumber+3);
		String timeType=strNumber[0]+"-"+(standNumber+4);
		String timeMeasure=strNumber[0]+"-"+(standNumber+5);
		String measureValue="textfield-"+(standNumber+6);		
	}
	
	
	
}
