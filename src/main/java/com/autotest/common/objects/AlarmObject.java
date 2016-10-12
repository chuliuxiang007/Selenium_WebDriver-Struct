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
