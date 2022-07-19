package modules;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Puts results from ModMatching's findMod() method into a .xlsx file.
 * @author shaimahussaini
 *
 */
public class WriteToExcel {
	
	/**
	 * ArrayList with matched mods.
	 */
	ArrayList<MatchedMods> findModsArray;
	
	/**
	 * File path to save file to.
	 */
	String filePath;
	
	/**
	 * Constructor
	 * @param findModsArr Array with matched mods.
	 * @param path File path to save file to.
	 */
	public WriteToExcel(ArrayList<MatchedMods> findModsArr, String path) {
		this.findModsArray = findModsArr;
		this.filePath = path;
	}
	
	/**
	 * Creates excel file.
	 */
	public void createXLSXFile() {
//		System.out.println("Called createXLSXFile()");
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(); 
			XSSFSheet sheet = workbook.createSheet("MatchedMods");
			
			String[] columnHeadings = {"Name", "Symbol", "Experimental Mass", 
					"Experimental Intensity", "Database Mono Mass", "Database Avg Mass", "AvP"};
			//modName   modSym   expMass   expIntens   dbMono   dbAvg
			
			Row headerRow = sheet.createRow(0);
			
			for(int i=0; i<columnHeadings.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columnHeadings[i]);
			}
			
			sheet.createFreezePane(0, 1);
			
			int rownum = 1;
			for(MatchedMods i : findModsArray) {
				Row row = sheet.createRow(rownum++);
				row.createCell(0).setCellValue(i.getName());
				row.createCell(1).setCellValue(i.getSymbol());
				row.createCell(2).setCellValue(i.getExpMass());
				row.createCell(3).setCellValue(i.getExpIntens());
				row.createCell(4).setCellValue(i.getDBMono());
				row.createCell(5).setCellValue(i.getDBAvg());
				row.createCell(6).setCellValue(DataProcess.calcAvP(i.getExpIntens(), findModsArray));
				row.createCell(7).setCellValue(i.getMessage());
			}
			
			for(int i=0;i<columnHeadings.length;i++) {
				sheet.autoSizeColumn(i);
			}
			
			FileOutputStream fileOut = new FileOutputStream(filePath);
			
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
}
