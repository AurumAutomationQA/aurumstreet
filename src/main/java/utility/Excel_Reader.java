package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_Reader {

public static String filename = System.getProperty("user.dir");
public  String path;
public  FileInputStream fis = null;
public  FileOutputStream fileOut =null;
private static XSSFWorkbook workbook = null;
private static XSSFSheet sheet = null;
private static XSSFRow row   =null;
private static XSSFCell cell = null;




public Excel_Reader(String path) {

	this.path=path;
	try {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheetAt(0);
		fis.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

}


public synchronized static String getProperty(String key) {
	int lastRow=getRowCount("Config");
	int count=0;
	String value=null;
	
	for(int i=1;i<=lastRow;i++) {
		String excelKey = getCellData("Config", 0, i).trim();
		if(excelKey.equals(key.trim())) {
			count++;
			value= getCellData("Config", 1, i);
			break;
		}
	}
	
	if(count==0) {
		return null;			
	}
	else {
		return value;
	}
}

public synchronized static String[] getPropertyAsArray(String key) {

	int lastRow=getRowCount("Config");
	int count=0;
	int rowNum=0;
	
	for(int i=1;i<=lastRow;i++) {
		String excelKey = getCellData("Config", 0, i).trim();
		if(excelKey.equals(key.trim())) {
			count++;
			rowNum= i;
			break;
		}
	}
	
	if(count==0) {
		return null;			
	}
	else {
		String[] data=new String[getCellCount(key)-1];
		for (int i = 1; i <=data.length; i++) {
			data[i-1]=getCellData("Config", i, rowNum);
		}
		return data;
	}
}

public synchronized static String getProperty(String key,int current) {
	int lastRow=getRowCount("Config");
	int count=0;
	String value=null;
	
	for(int i=1;i<=lastRow;i++) {
		String excelKey = getCellData("Config", 0, i).trim();
		if(excelKey.equals(key.trim())) {
			count++;
			value= getCellData("Config", 1+((current-1)%(getCellCount(key)-1)), i);
			break;
		}
	}
	
	if(count==0) {
		return null;			
	}
	else {
		return value;
	}
}


// returns the specified sheet
public XSSFSheet  getSheet(String sheetName){
	int index = workbook.getSheetIndex(sheetName);
	if(index==-1)
		return null;
	else{
		return workbook.getSheetAt(index);
	}

}	
// returns the specified sheet
public XSSFSheet  getSheetAtIndex(int index){
	return workbook.getSheetAt(index);		
}	

// returns the specified sheet
public int getLastCellNum(String sheetName,int rowNumber){
	XSSFSheet sheet= workbook.getSheet(sheetName);
	XSSFRow row = sheet.getRow(rowNumber-1);
	return row.getLastCellNum();
}	

public int getNumberOfSheets(){
	return workbook.getNumberOfSheets();
}	


// returns the row count in a sheet
public synchronized static int getRowCount(String sheetName){
	int index = workbook.getSheetIndex(sheetName);
	if(index==-1)
		return 0;
	else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
	}

}	

public synchronized static int getMaxDataSetCount(String[] keys){
	int max=0;
	for(int i=0;i<keys.length;i++) {
		if(max<getCellCount(keys[i])) {
			max=getCellCount(keys[i]);
		}
	}
	
	return max;
	
}

public synchronized static int getCellCount(String key){

	int lastRow=getRowCount("config");
	
	int count=0;
	int rowNumber=0;
	
	for(int i=1;i<=lastRow;i++) {
		String excelKey = getCellData("Config", 0, i).trim();
		if(excelKey.equals(key.trim())) {
			count++;
			rowNumber=i;
			break;
		}
	}
	
	if(count==0) {
		return 0;			
	}
	else {
		sheet = workbook.getSheet("config");
		row=sheet.getRow(rowNumber-1);
		return row.getLastCellNum();
	}
}	

public synchronized static int getCellCount(String sheetName,String key){

	int lastRow=getRowCount(sheetName);
	
	int count=0;
	int rowNumber=0;
	
	for(int i=1;i<=lastRow;i++) {
		String excelKey = getCellData(sheetName, 0, i).trim();
		if(excelKey.equals(key.trim())) {
			count++;
			rowNumber=i;
			break;
		}
	}
	
	if(count==0) {
		return 0;			
	}
	else {
		sheet = workbook.getSheet(sheetName);
		row=sheet.getRow(rowNumber-1);
		return row.getLastCellNum();
	}
}	

// returns true if data is set successfully else false
public synchronized boolean setCellData(String sheetName,int colNo,int rowNum, String data){
	try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;

		int index = workbook.getSheetIndex(sheetName);
		//int colNum=-1;
		if(index==-1)
			return false;


		sheet = workbook.getSheetAt(index);


		row=sheet.getRow(0);

		//System.out.println(row.getLastCellNum()+"77777777777777777777777777777777777777777777777777777777777777777777777777777777777777777");
		/*for(int i=0;i<row.getLastCellNum();i++){

		//System.out.println(row.getCell(i).getStringCellValue().trim());
		if(row.getCell(i).getStringCellValue().trim().equals(colName))
			colNum=i;
	}
	if(colNum==-1)
		return false;*/

		sheet.autoSizeColumn(colNo); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);

		cell = row.getCell(colNo);	
		if (cell == null)
			cell = row.createCell(colNo);

		// cell style
		//CellStyle cs = workbook.createCellStyle();
		//cs.setWrapText(true);
		//cell.setCellStyle(cs);
		cell.setCellValue(data);

		fileOut = new FileOutputStream(path);

		workbook.write(fileOut);
		fileOut.close();	
		workbook = new XSSFWorkbook(new FileInputStream(path));

	}
	catch(Exception e){
		e.printStackTrace();
		return false;
	}
	return true;
}



// returns the data from a cell
public String getCellData(String sheetName,String colName,int rowNum){
	try{
		if(rowNum <=0)
			return "";

		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";

		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return "";

		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);

		return cell.getStringCellValue();
	}
	catch(Exception e){

		e.printStackTrace();
		return "row "+rowNum+" or column "+colName +" does not exist in xls";
	}
}

// returns the data from a cell
public static String getCellData(String sheetName,int colNum,int rowNum){
	try{
		if(rowNum <=0)
			return "";

		int index = workbook.getSheetIndex(sheetName);

		if(index==-1)
			return "";


		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum);//colNum  //suman
		return cell.getStringCellValue();
	}
	catch(NullPointerException e){
		return "";
	}
	catch(Exception e){

		e.printStackTrace();
		return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
	}
}

public synchronized static String getProperty(String sheetName,String key) {
	int lastRow=getRowCount(sheetName);
	int count=0;
	String value=null;
	
	for(int i=1;i<=lastRow;i++) {
		String excelKey = getCellData(sheetName, 0, i).trim();
		if(excelKey.equals(key.trim())) {
			count++;
			value= getCellData(sheetName, 1, i).trim();
			break;
		}
	}
	
	if(count==0) {
		return null;			
	}
	else {
		return value;
	}
}}
