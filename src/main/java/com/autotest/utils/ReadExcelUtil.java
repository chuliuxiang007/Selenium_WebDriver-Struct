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
		//根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径
		//声明一个File文件对象
		File file=new File(filePath+"\\"+fileName);
		//创建FileInputStream对象读取excle文件
		FileInputStream inputStream = new FileInputStream(file);
		//声明Workbook对象
		Workbook workbook = null;
		//获取文件的扩展名，判断是.xlsx文件还是.xls文件
		String fileExtensiongName = fileName.substring(fileName.indexOf("."));
		//如果是.xlsx,使用XSSFWorkbook对象进行实例化
		//如果是.xls,使用SSFWorkbook对象进行实例化
		if(fileExtensiongName.equals(".xlsx")){
			workbook=new XSSFWorkbook(inputStream);
		}else if(fileExtensiongName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}else {
			logger.fatal("the file is not excel");
		}
		//通过sheetName参数，生产sheet对象
		Sheet sheet =  workbook.getSheet(sheetName);
		//获取文件中sheet1中的数据的行数，getLastRowNum方法获取数据的最后一行行号
		//getFirstRowNum方法获取数据的第一行行号，相减之后算出数据的行数
		//excel文件的行号和列号都是从0开始的
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		//创建名为records的list对象存储从excel数据文件读取的数据
		List<Object[]> records = new ArrayList<Object[]>();
		//2个for循环遍历Excel数据文件的所有数据（除了第一行，第一行是数据列名称）
		for (int r = 0; r < rowCount+1; r++) {
			Row row =sheet.getRow(r);
			//声明一个数组，用来存储excel数据文件每行中的3个数据，数组的大小用动态声明
			//测试数据个数和数组大小相一致
			String fields[] = new String[row.getLastCellNum()];
			for(int j =0;j<row.getLastCellNum();j++){
				fields[j]=row.getCell(j).getStringCellValue();
			}
			//将fields数据对象存储到recors的list中
			records.add(fields);
		}
		//返回Objcet[][]
		//将存储测试数据的list转换为一个Objcet的二维数组
		Object[][] results = new Object[records.size()][];
		//设置二维数组每行的值，每行是一个object对象
		for(int i =0 ; i < records.size();i++){
			results[i]=records.get(i);
		}
		return results;
	}
}
