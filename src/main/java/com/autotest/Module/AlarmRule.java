package com.autotest.Module;

import org.openqa.selenium.WebDriver;

import com.autotest.Control;
import com.autotest.component.Button;
import com.autotest.component.Combo;
import com.autotest.component.TextBox;
import com.autotest.pageconstant.UCenterPageConstant;
import com.autotest.utils.CommonOperations;

public class AlarmRule {
	
	public static AlarmRule singleAlarm=null;
	private WebDriver driver=null;
	private String tempJS=null;
	private int alarmCount =0;
	

	
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
		tempJS="return document.getElementsByClassName(\"x-grid-row x-grid-data-row\").length";
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
	 * 新建告警规则
	 * @param alarm
	 */
	public void addAlarmRule(String[] alarm){
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
	public void inputAlarmMessage(String[] alarmMessage,boolean flag){
		//输入规则名称
		if(flag){
			CommonOperations.inputText(UCenterPageConstant.alarmName, alarmMessage[0], driver);
		}
		//输入描述
		CommonOperations.inputText(UCenterPageConstant.alarmDesc, alarmMessage[0], driver);
		//输入触发条件		
		//选择告警设备
		CommonOperations.clickCombBtn(UCenterPageConstant.alarmDevice, driver);
		CommonOperations.selectComboTreeDevice("2307440130001328", driver);

	}
	
	private void selectAlarmType(String type){
		String tempJS = null;
		String js = "document.getElementsByClassName(\"x-boundlist x-boundlist-"
				+ "floating x-layer x-boundlist-default x-border-box\")[0].id";
		String elementID =CommonOperations.executeJS(js, driver);
		String js1="document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\").length";
		int typeCount=Integer.parseInt(CommonOperations.executeJS(js1, driver));
		for(int i = 0; i < typeCount; i++){
			tempJS = "document.getElementById(\""+elementID+"\").getElementsByTagName(\"li\")["
					+ i+"].textContent";
			if(type.equals(CommonOperations.executeJS(tempJS, driver))){
				tempJS=
			}
		}
	}

	
	private void inputRuleOption(String[] alarmMessage){
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

		//告警条件设置
		String alarmOption =strNumber[0]+"-"+(standNumber+1);
		//时间参数设置
		String timeOpt="textfield-"+(standNumber+3);
		String timeType=strNumber[0]+"-"+(standNumber+4);
		String timeMeasure=strNumber[0]+"-"+(standNumber+5);
		String measureValue="textfield-"+(standNumber+6);
		
	}
	
}
