package com.xwheel.xmonitor.commons.utils;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author: lei hang
 * @date: 2015年09月14日
 * @description:
 */

public class ExcelCreateUtils {
    //获取一列
    public static XSSFRow getCellRow(XSSFSheet sheet, int rowNum, XSSFCellStyle style) {
        XSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        if (style != null) {
            //bug
            row.setRowStyle(style);
            //循环设置每个格子的背景色
            for (int i = 0; i < 50; i++) {
                XSSFCell cell = row.getCell(i);
                if (cell == null) {
                    break;
                } else {
                    cell.setCellStyle(style);
                }
            }
        }

        return row;
    }

    //设置单元格值
    public static void setCellValue(XSSFRow row, int colNum, String cellValue, XSSFCellStyle style) {
        //创建一格
        XSSFCell cell = row.getCell(colNum);
        if (cell == null) {
            cell = row.createCell(colNum);
        }
        if (style != null) {
            cell.setCellStyle(style);
        }
        cell.setCellValue(cellValue);//设置表格值
    }



}
