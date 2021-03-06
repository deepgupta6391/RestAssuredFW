package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static String TESTDATA_SHEET_PATH = "/Users/deepshikhagupta/Documents/LearningFromNaveen/RestAssuredFrameWorkFeb2021"
			+ "/src/main/java/com/qa/api/gorest/testdata/testdata.xlsx";

	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getData(String sheetName) {

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);

			try {
				book = WorkbookFactory.create(ip);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;

	}

}
