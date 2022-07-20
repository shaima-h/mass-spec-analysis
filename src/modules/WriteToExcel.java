package modules;

import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {
  ArrayList<MatchedMods> findModsArray;
  
  String filePath;
  
  public WriteToExcel(ArrayList<MatchedMods> findModsArr, String path) {
    this.findModsArray = findModsArr;
    this.filePath = path;
  }
  
  public void createXLSXFile() {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet("MatchedMods");
      String[] columnHeadings = { "Name", "Symbol", "Experimental Mass", 
          "Experimental Intensity", "Database Mono Mass", "Database Avg Mass", "AvP" };
      XSSFRow xSSFRow = sheet.createRow(0);
      for (int i = 0; i < columnHeadings.length; i++) {
        Cell cell = xSSFRow.createCell(i);
        cell.setCellValue(columnHeadings[i]);
      } 
      sheet.createFreezePane(0, 1);
      int rownum = 1;
      for (MatchedMods matchedMods : this.findModsArray) {
        XSSFRow xSSFRow1 = sheet.createRow(rownum++);
        xSSFRow1.createCell(0).setCellValue(matchedMods.getName());
        xSSFRow1.createCell(1).setCellValue(matchedMods.getSymbol());
        xSSFRow1.createCell(2).setCellValue(matchedMods.getExpMass());
        xSSFRow1.createCell(3).setCellValue(matchedMods.getExpIntens());
        xSSFRow1.createCell(4).setCellValue(matchedMods.getDBMono());
        xSSFRow1.createCell(5).setCellValue(matchedMods.getDBAvg());
        xSSFRow1.createCell(6).setCellValue(DataProcess.calcAvP(matchedMods.getExpIntens(), this.findModsArray));
        xSSFRow1.createCell(7).setCellValue(matchedMods.getMessage());
      } 
      for (int j = 0; j < columnHeadings.length; j++)
        sheet.autoSizeColumn(j); 
      FileOutputStream fileOut = new FileOutputStream(this.filePath);
      workbook.write(fileOut);
      fileOut.close();
      workbook.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
