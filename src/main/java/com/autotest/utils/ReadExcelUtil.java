package com.autotest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcelUtil {
	
	public static Logger logger = Logger.getLogger(ReadExcelUtil.class);


	
	public static Object[][] readExcel(String filePath,String fileName,String sheetName) throws IOException{
		Workbook web = null;
		//���ݲ�������������ļ�·�����ļ����ƣ���ϳ�Excel�����ļ��ľ���·��
		//����һ��File�ļ�����
		File file=new File(filePath+"\\"+fileName);
		//����FileInputStream�����ȡexcle�ļ�
		FileInputStream inputStream = new FileInputStream(file);
		//����Workbook����
		Workbook workbook = null;
		//��ȡ�ļ�����չ�����ж���.xlsx�ļ�����.xls�ļ�
		String fileExtensiongName = fileName.substring(fileName.indexOf("."));
		//�����.xlsx,ʹ��XSSFWorkbook�������ʵ����
		//�����.xls,ʹ��SSFWorkbook�������ʵ����
		if(fileExtensiongName.equals(".xlsx")){
			workbook=new XSSFWorkbook(inputStream);
		}else if(fileExtensiongName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}else {
			logger.fatal("the file is not excel");
		}
		//ͨ��sheetName����������sheet����
		Sheet sheet =  workbook.getSheet(sheetName);
		//��ȡ�ļ���sheet1�е����ݵ�������getLastRowNum������ȡ���ݵ����һ���к�
		//getFirstRowNum������ȡ���ݵĵ�һ���кţ����֮��������ݵ�����
		//excel�ļ����кź��кŶ��Ǵ�0��ʼ��
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		//������Ϊrecords��list����洢��excel�����ļ���ȡ������
		List<Object[]> records = new ArrayList<Object[]>();
		//2��forѭ������Excel�����ļ����������ݣ����˵�һ�У���һ�������������ƣ�
		for (int r = 0; r < rowCount+1; r++) {
			Row row =sheet.getRow(r);
			//����һ�����飬�����洢excel�����ļ�ÿ���е�3�����ݣ�����Ĵ�С�ö�̬����
			//�������ݸ����������С��һ��
			String fields[] = new String[row.getLastCellNum()];
			for(int j =0;j<row.getLastCellNum();j++){
				fields[j]=row.getCell(j).getStringCellValue();
			}
			//��fields���ݶ���洢��recors��list��
			records.add(fields);
		}
		//����Objcet[][]
		//���洢�������ݵ�listת��Ϊһ��Objcet�Ķ�ά����
		Object[][] results = new Object[records.size()][];
		//���ö�ά����ÿ�е�ֵ��ÿ����һ��object����
		for(int i =0 ; i < records.size();i++){
			results[i]=records.get(i);
		}
		return results;
	}
}
