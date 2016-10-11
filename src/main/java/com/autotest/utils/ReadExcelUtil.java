package com.autotest.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;



public class ReadExcelUtil {
	
	public static Logger logger = Logger.getLogger(ReadExcelUtil.class);
	public static String path;
	public Workbook wb=null;

	public ReadExcelUtil(String path){
		try{
			InputStream inp = new FileInputStream(path);
		}catch(FileNotFoundException e){
			logger.fatal("the excel is not found");
		}catch(){
			
		}
		catch(IOException e){
			e.printStackTrace();
			logger.fatal("read the excel file failure");
		}
	}
	
	public void readExcel(){
		Workbook web = null;
	}
}
