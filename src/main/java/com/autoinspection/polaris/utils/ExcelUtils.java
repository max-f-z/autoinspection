package com.autoinspection.polaris.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	@SuppressWarnings({ "rawtypes", "resource" })
	public static void main(String[] args) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream("C:\\Users\\jimao\\Desktop\\test.xlsx");  
	    XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);  
	  
	    XSSFSheet sheet = wb.getSheetAt(0);  
	    XSSFRow row;  
	    XSSFCell cell;  
	  
	    Iterator rows = sheet.rowIterator(); 
	    
	    while (rows.hasNext())  
	    {  
	      row = (XSSFRow) rows.next();  
	      if (row.getRowNum() > 0) {
	    	  System.out.println(row.getCell(1).getDateCellValue());
	      }
	      Iterator cells = row.cellIterator();  
	  
	      while (cells.hasNext())  
	      {  
	        cell = (XSSFCell) cells.next();  
	  
	        if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)  
	        {  
	          System.out.print(cell.getStringCellValue() + " ");  
	        }  
	        else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)  
	        {  
	          System.out.print(cell.getNumericCellValue() + " ");  
	        }  
	        else 
	        {  
	          
	        }  
	      }
	      System.out.println();  
	    }  
	  
	}
}
