package com.autotest.common.objects;

public class AlarmObject {
	public int columns=6;
	public String alarmName;
	public String alarmDevices;
	public String level;
	public String creatime;
	public String alarmMethod;
	public String desc;
	public String operator;
	public String snList;
	
	//告警类型条件
	public String alarmType;
	public String alarmType_SecType;
	public int alarmTime_Number;
	public String alarmTime_Type;
	public String alarmRegex;
	public int alarmType_Value;
	
	//告警规则表中 列数据
	public int columnNumber=7;
	public String[] colAlarmTable={"名称","设备","级别","创建时间","告警方式","描述","操作"};
	
	
	public void setName(String name){
		this.alarmName =name;
	}
	
	public void setDevices(String devices){
		this.alarmDevices=devices;
	}
	
	public void setLevel(String level){
		this.level=level;
	}
	
	public void setCreateTime(String time){
		this.creatime=time;
	}
	
	public void setMethod(String method){
		this.alarmMethod=method;
	}
	
	public void setDesc(String desc){
		this.desc=desc;
	}
	
	

}
