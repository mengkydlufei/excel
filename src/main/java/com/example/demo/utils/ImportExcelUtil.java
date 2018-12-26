package com.example.demo.utils;


import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel文件上传Util
 *
 * @author Justin
 */
public class ImportExcelUtil {

    private final static String Excel_2003 = ".xls"; //2003 版本的excel
    private final static String Excel_2007 = ".xlsx"; //2007 版本的excel

    /**
     * @param in
     * @param fileName
     * @param columNum 自定义列数
     * @return
     */
    public List<List<Object>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        List<List<Object>> list = null;

        //创建Excel工作簿
        Workbook work = this.getWorkbook(in, fileName);
        if (work == null) {
            throw new Exception("创建Excel工作簿为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        //遍历Excel中的所有sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            //int totalRow = sheet.getPhysicalNumberOfRows();//如果excel有格式，这种方式取值不准确
            int totalRow = sheet.getPhysicalNumberOfRows();
            for (int j = sheet.getFirstRowNum(); j < totalRow; j++) {
                row = sheet.getRow(j + 1);
                if (row != null && !"".equals(row)) {
                    //获取第一个单元格的数据是否存在
                   /* Cell fristCell=row.getCell(0);
                    if(fristCell != null){*/
                    //遍历所有的列
                    List<Object> li = new ArrayList<Object>();
                    //int totalColum = row.getLastCellNum();
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        String callCal = this.getCellValue(cell) + "";
                        li.add(callCal);
                    }
                    list.add(li);
//                    }
                }

            }
        }
        in.close();
        return list;
    }

    /**
     * 描述：根据文件后缀，自动适应上传文件的版本
     *
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook work = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (Excel_2003.equals(fileType)) {
            work = new HSSFWorkbook(inStr);//2003 版本的excel
        } else if (Excel_2007.equals(fileType)) {
            work = new XSSFWorkbook(inStr);//2007 版本的excel
        } else {
            throw new Exception("解析文件格式有误！");
        }
        return work;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df1 = new DecimalFormat("0");//格式化number，string字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");//日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");//格式化数字
        if (cell != null && !"".equals(cell)) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                        value = df1.format(cell.getNumericCellValue());
                    } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                        value = sdf.format(cell.getDateCellValue());
                    } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        value = sdf.format(date);
                    } else {
                        value = df2.format(cell.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    break;
            }
        }
        return value;
    }

    public String getFormat(String str) {
        if (str.equals("null")) {
            str = "";
            return str;
        } else {
            return str;
        }
    }

    public Integer getFormats(Integer str) {
        if (str == null) {
            str = 0;
            return str;
        } else {
            return str;
        }
    }




    public List<Map<String,Object>> getBankListByExcel(InputStream in, String fileName,Object obj) throws Exception {
        List<Map<String,Object>> list = null;

        //创建Excel工作簿
        Workbook work = this.getWorkbook(in, fileName);
        if (work == null) {
            throw new Exception("创建Excel工作簿为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<Map<String,Object>>();
        //遍历Excel中的所有sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            //遍历当前sheet中的所有行
            //int totalRow = sheet.getPhysicalNumberOfRows();//如果excel有格式，这种方式取值不准确
            int totalRow = sheet.getPhysicalNumberOfRows();
            for (int j = sheet.getFirstRowNum(); j < totalRow; j++) {
                row = sheet.getRow(j + 1);
                if (row != null && !"".equals(row)) {
                    //获取第一个单元格的数据是否存在
                   /* Cell fristCell=row.getCell(0);
                    if(fristCell != null){*/
                    //遍历所有的列
                    //List<Object> li = new ArrayList<Object>();
                    Field[] fields = FieldUtils.getAllFields(obj.getClass());
                    List list1 = new ArrayList();
                    for(int k = 0;k<fields.length;k++){
                        ExcelAnnotation excelAnnotations = fields[k].getAnnotation(ExcelAnnotation.class);
                        if(excelAnnotations != null){
                           list1.add(fields[k].getName());
                        }
                    }
                    Map<String,Object> map = new HashMap<String, Object>();
                    //int totalColum = row.getLastCellNum();
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);
                        String callCal = this.getCellValue(cell) + "";
                        map.put(list1.get(j).toString(),callCal);
                    }
                    list.add(map);
//                    }
                }

            }
        }
        in.close();
        return list;
    }
}

