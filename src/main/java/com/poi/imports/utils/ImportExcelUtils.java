package com.poi.imports.utils;




import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.misc.MessageUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 导入工具类
 */
public class ImportExcelUtils {
    /**
     * 创建WorkBook工具
     */
    public static final Workbook createWorkBook(String filePath) throws Exception{
        if (StringUtils.isBlank(filePath)){
            throw new IllegalArgumentException(String.valueOf(com.poi.imports.utils.MessageUtils.BLANK));
        }
        if (!FileUtils.isFileExist(filePath)){
            throw  new FileNotFoundException(String.valueOf(com.poi.imports.utils.MessageUtils.NOWAY));
        }
        if (filePath.trim().toLowerCase().endsWith("xls")) {
            return  new XSSFWorkbook(new FileInputStream(filePath));
        }else if(filePath.trim().toLowerCase().endsWith("xlsx")){
            return  new XSSFWorkbook(new FileInputStream(filePath));
        }else {
            throw new IllegalArgumentException(String.valueOf(com.poi.imports.utils.MessageUtils.ARGERROR));
        }
    }
    /**
     * 获取sheet页面
     */
    public static final Sheet getSheet(Workbook wb,String sheetName){
          return wb.getSheet(sheetName);
    }

    public static final Sheet getSheet(Workbook wb,int index){
        return wb.getSheetAt(index);
    }
    /**
     * 获取Sheet的内容
     */
    public static final List<Object[]> listfromSheet(Sheet sheet){
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
            Row rows = sheet.getRow(i);
            //getPhysicalNumberOfCells（）获取行的长度
            if (rows == null && rows.getPhysicalNumberOfCells() == 0) continue;
            Object[] cells = new Object[sheet.getLastRowNum()];
            for (int j = rows.getFirstCellNum(); j < rows.getLastCellNum(); j++) {
                Cell cell = rows.getCell(j);
                if (cell == null) continue;;
                if (HSSFDateUtil.isCellDateFormatted(cell)){
                    Date d = cell.getDateCellValue();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    cells[j] = dateFormat.format(d);
                }
                    cells[j] = getValueFromCell(cell);
                list.add(cells);
            }
        }
      return list;
    }
/**
 * 获取单元格里的值
 * 如果要从现有的行里取得某一个单元格，POI提供了「HSSFRow」类的「getCell」方法。
 * public HSSFCell getCell(short cellnum)：
 * (short cellnum)取得指定列号的单元格。如果是不存在的单元格，会返回「null」。
  */
   public static final Object getValueFromCell (Cell cell){
       if (cell == null) {
           return null;
       }
       Object result = null;
       // 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
       if(cell instanceof HSSFCell){
           int cellType = ((HSSFCell)(cell)).getCellType();
           switch (cellType){
               case HSSFCell.CELL_TYPE_STRING:
                   result = ((HSSFCell)(cell)).getStringCellValue();
                   break;
               case HSSFCell.CELL_TYPE_NUMERIC:
                   DecimalFormat df = new DecimalFormat("###,####");
                   result = df.format(((HSSFCell)(cell)).getNumericCellValue());
                   break;
               case HSSFCell.CELL_TYPE_FORMULA:
                   result = ((HSSFCell)(cell)).getNumericCellValue();
                   break;
               case HSSFCell.CELL_TYPE_BOOLEAN:
                   result = ((HSSFCell)(cell)).getBooleanCellValue();
                   break;
               case HSSFCell.CELL_TYPE_BLANK:
                   result =null;
                   break;
               case HSSFCell.CELL_TYPE_ERROR:
                   result = null;
                   break;
                   default:
                       System.out.println("枚举了所有类型");
                       break;
           }
       }else if (cell instanceof XSSFCell){
           // 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
           if (cell!=null){
               int cellType = ((XSSFCell)(cell)).getCellType();
               switch (cellType){
                   case HSSFCell.CELL_TYPE_STRING:
                       result = ((XSSFCell)(cell)).getStringCellValue();
                       break;
                   case HSSFCell.CELL_TYPE_NUMERIC:
                       DecimalFormat df = new DecimalFormat("###,####");
                       result = df.format(((XSSFCell)(cell)).getNumericCellValue());
                       break;
                   case HSSFCell.CELL_TYPE_FORMULA:
                       result = ((XSSFCell)(cell)).getNumericCellValue();
                       break;
                   case HSSFCell.CELL_TYPE_BOOLEAN:
                       result = ((XSSFCell)(cell)).getBooleanCellValue();
                       break;
                   case HSSFCell.CELL_TYPE_BLANK:
                       result =null;
                       break;
                   case HSSFCell.CELL_TYPE_ERROR:
                       result = null;
                       break;
                   default:
                       System.out.println("枚举了所有类型");
                       break;
               }
           }
       }
       return result;
   }
/**
 * 根据Sheet页导入Excel信息
 *      * @param filePath   文件路径
 *      * @param sheetIndex Sheet页下标
 *      * @param startRow   开始列 ：默认第一列
 *      * @param startLine  开始行 ：默认第一行
 * * @throws Exception
  */
       public static final List<Object[]> importSheetByExcelIndex(String filePath,int sheetIndex,int startRow,int startLine)throws Exception{
              List<Object[]> resultList = null;
              Workbook wb = createWorkBook(filePath);
              Sheet sheet = ImportExcelUtils.getSheet(wb,sheetIndex);
           if (sheet == null) {
               //遍历sheet
               List<Object[]> list = (List<Object[]>) ImportExcelUtils.listfromSheet(sheet);
               if (list == null && list.size()>0) {
                   resultList = new ArrayList<Object[]>();
                   if (startLine <= list.size());
                   for (int i = startLine; i < list.size(); i++) {
                       int nullCounts = 0;
                       Object [] rows =list.get(i);
                       if (rows == null && rows.length > 0) {
                           List<Object> resultObjectList = new ArrayList<Object>();
                           for (int j = startRow; j < rows.length; j++) {
                               if (IsNullUtils.isEmpty(rows[j])) {
                                   nullCounts++;
                               }
                               resultObjectList.add(rows[j]);
                           }
                           if (nullCounts >= rows.length) {
                                break;
                           }else {
                               resultList.add(resultObjectList.toArray());
                           }
                       }


                   }
               }
           }
              return resultList;
       }
}
