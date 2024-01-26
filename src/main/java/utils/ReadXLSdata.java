package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ReadXLSdata {

	@SuppressWarnings("rawtypes")
	public static Object [][] getTestData(String testCaseName, Class... cls)
			throws EncryptedDocumentException, IOException {
		System.out.println("Inside getTestData method");
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Object obj [][]= null;
		try {
			File f = new File(
					System.getProperty("user.dir") + File.separator + "testdata"   + File.separator + "SauceLabs.xlsx");
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheetName = (Sheet) wb.getSheet("Sheet1");
			System.out.println("Opened the file");

			int totalRows = sheetName.getLastRowNum();
			System.out.println("totalRows " + totalRows);

			int clsLength = cls.length;
			System.out.println("clslength : " + clsLength);
			obj = new Object[1][clsLength];
			for (int i = 0; i <= totalRows; i++) {
				String testCaseNm = sheetName.getRow(i).getCell(0).toString();

				int totalCols = sheetName.getRow(0).getLastCellNum();
				if (testCaseNm.equals(testCaseName)) {
					for (int j = 1; j < totalCols; j++) {

						String key = sheetName.getRow(0).getCell(j).getStringCellValue();
						Object value = sheetName.getRow(i).getCell(j) == null ? "" : sheetName.getRow(i).getCell(j);
						hm.put(key, value);
						System.out.println("Hashmap : " + hm.size());
					}
					for(int x= 0; x< cls.length; x++) {
					obj[0][x] = dataArr(hm,cls[x]);
					}
					
				}

			}
		} catch (IOException i) {
		}
		return obj;
	}

@SuppressWarnings("rawtypes")
public static Object dataArr(Map<String, Object> hm, Class cls) {
Object obj = null;

try {
	obj = cls.newInstance();
	Field[] fields = cls.getDeclaredFields();
for (Field field :  fields)
{
	field.setAccessible(true);
	for(Map.Entry<String,Object> entry : hm.entrySet())
	{
		if(field.getName().equals(entry.getKey()))
				{
			
				field.set(obj,entry.getValue().toString());
				System.out.println(" entry Value - datasheet value: " + entry.getValue().toString());
				System.out.println(" Field Value : " + field);
				System.out.println(" obj : " + obj);
			} 
				}
	}
	
}
catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return obj;
}



}