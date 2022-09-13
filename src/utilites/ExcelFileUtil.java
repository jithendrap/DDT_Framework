package utilites;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//constructor for reading excel file
    public ExcelFileUtil(String excelpath) throws Throwable
    {
    	FileInputStream fi=new FileInputStream(excelpath);
    	wb=new XSSFWorkbook(fi);
    }
    //counting no of rows
    public int rowCount(String sheetName)
    {
    	return wb.getSheet(sheetName).getLastRowNum();
    	
    }
    public int cellcount(String sheetName)
    {
    	return wb.getSheet(sheetName).getRow(0).getLastCellNum();
    }
    //read cell data
    public String getCellData(String sheetName,int row,int column)
    {
    	
      	String data="";
    if (wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {
		int celldata=(int)wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		data=String.valueOf(celldata);
	}	
    else
    {
    	data =wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
    }
    return data;
    }
    //method for writing
    public void setCellData(String sheetName,int row,int column,String status,String writeExcel) throws Throwable {
    //get shhet from wb
    	XSSFSheet ws=wb.getSheet(sheetName);
    	//get row ffrom sheet
    	XSSFRow rownum=ws.getRow(row);
    	//create cell from row
    	XSSFCell cell=rownum.createCell(column);
    	//write status
    	cell.setCellValue(status);
    	if (status.equalsIgnoreCase("pass")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
    	else if (status.equalsIgnoreCase("fail")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
    	else if (status.equalsIgnoreCase("Blocked")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
					font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}
    	FileOutputStream fo=new FileOutputStream(writeExcel);
    	wb.write(fo);
    }
	public static void main(String[] args) throws Throwable {
		ExcelFileUtil xl=new ExcelFileUtil("F:/Book.xlsx");
		int rc=xl.rowCount("Login");
		int cc=xl.cellcount("Login");
		System.out.println(rc+"   "+cc);
		for (int i = 1; i <=rc; i++) {
			String user=xl.getCellData("Login", i,0);
			String pass=xl.getCellData("Login",i,1);
			System.out.println(user+"   "+pass);
			xl.setCellData("Login",i,2,"Pass", "F://Results.xlsx");
			//xl.setCellData("Login",i,2,"Blocked", "F://Results.xlsx");
			
		}

	}

}
