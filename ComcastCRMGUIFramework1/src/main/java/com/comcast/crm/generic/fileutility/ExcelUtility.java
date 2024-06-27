package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName, int rownum, int celnum) throws Exception {

		FileInputStream wfis = new FileInputStream("./testdata/CRMtestdataorgandcontacts.xlsx");
		Workbook wb = WorkbookFactory.create(wfis);
		String data = wb.getSheet(sheetName).getRow(rownum).getCell(celnum).toString();
		wb.close();

		return data;

	}

	public int getRowCount(String sheetName) throws Exception {
		FileInputStream fis = new FileInputStream("./testdata/CRMtestdataorgandcontacts.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;

	}

	public void setDataIntoExcel(String sheetName, int rowNum, int celnum, String data) throws Exception {
		FileInputStream fis = new FileInputStream("./testdata/CRMtestdataorgandcontacts.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celnum);

		FileOutputStream fos = new FileOutputStream("./testdata/CRMtestdataorgandcontacts.xlsx");
		wb.write(fos);
		    wb.close();

	}

}
