package com.codingkapoor.esslattendancereportgenerator.writer.employeeinfoheader

import java.time.YearMonth

import com.codingkapoor.esslattendancereportgenerator.`package`.EmployeesInfoHeader
import com.codingkapoor.esslattendancereportgenerator.writer.EmployeeInfoHeaderDimensions
import org.apache.poi.xssf.usermodel.XSSFWorkbook

trait EmployeeInfoHeaderWriter extends EmployeeInfoHeaderStyle {
  val month: Int
  val year: Int

  def writeEmployeeInfoHeader(implicit workbook: XSSFWorkbook): Unit = {
    val yearMonth = YearMonth.of(year, month)
    val _month = yearMonth.getMonth.toString

    val sheet = workbook.getSheet(_month)

    val employeeInfoHeaderDimensions = EmployeeInfoHeaderDimensions()

    val firstRowIndex = employeeInfoHeaderDimensions.firstRowIndex
    val firstColumnIndex = employeeInfoHeaderDimensions.firstColumnIndex
    val lastColumnIndex = employeeInfoHeaderDimensions.lastColumnIndex

    val row = sheet.createRow(firstRowIndex)

    val cellStyle = getEmployeeInfoHeaderCellStyle

    for (i <- firstColumnIndex to lastColumnIndex) {
      val col = row.createCell(i)
      col.setCellValue(EmployeesInfoHeader(i))
      col.setCellStyle(cellStyle)
    }
  }
}
