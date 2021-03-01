package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	public static Object[][] getReadExcelData(String filename, String sheetName) throws IOException {
		String filePath = "./resources/testdata/" + filename;
		File file = new File(filePath);
		FileInputStream inputFile = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(inputFile);
		Sheet sheet = wb.getSheet(sheetName);

		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getLastCellNum();
		Object[][] data = new String[totalRows][totalCols];
		for(int rowIndex=1; rowIndex <= totalRows; rowIndex++) {
			for(int colIndex=0; colIndex < totalCols; colIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
				if (cell == null)
					data[rowIndex-1][colIndex] = "";
				else if(cell.getCellType() == CellType.NUMERIC)
					data[rowIndex-1][colIndex] = String.valueOf(cell.getNumericCellValue());
				else if (cell.getCellType() == CellType.BOOLEAN)
					data[rowIndex-1][colIndex] = String.valueOf(cell.getBooleanCellValue());
				else if (cell.getCellType() == CellType.STRING)
					data[rowIndex-1][colIndex] = String.valueOf(cell.getStringCellValue());
				else if	(cell.getCellType() == CellType.BLANK)
					data[rowIndex-1][colIndex] = String.valueOf(cell.getStringCellValue());	
				//data[rowIndex-1][colIndex] = sheet.getRow(rowIndex).getCell(0).getStringCellValue();
			System.out.println(data[rowIndex-1][colIndex] + " ");
			}
			System.out.println();
		}
		return data;
	}
}
