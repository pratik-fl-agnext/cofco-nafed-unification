package com.agnext.unification.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.agnext.unification.model.CSVModel;
import com.agnext.unification.model.StateAnalysisDataModel;

@Service
public class ExcelService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(ExcelService.class);

    public ByteArrayInputStream createExcel(Map<Long, StateAnalysisDataModel> stateDataMap, List<CSVModel> csvModelList) {
        logger.debug("##############################CSV Model Size : {}######################################", csvModelList.size());
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("nafednew.xlsx");
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            XSSFWorkbook nafedDataWorkBook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
            XSSFSheet allScansSheet = nafedDataWorkBook.getSheetAt(0);
            Row firstRow = allScansSheet.getRow(0);
            List<Row> sourceRowList = Arrays.asList(firstRow);

            Map<Long, XSSFSheet> sheetMap = new HashMap<>();
            stateDataMap.forEach((k, v) -> {
                if (CollectionUtils.isNotEmpty(v.getCsvList())) {
                    XSSFSheet stateSheet = nafedDataWorkBook.createSheet(v.getState());
                    copyFromSourceToDestinationRow(nafedDataWorkBook, allScansSheet, 0, stateSheet, 0);
                    sheetMap.put(k, stateSheet);
                }
            });


            DataFormat format = nafedDataWorkBook.createDataFormat();
            XSSFCellStyle style = nafedDataWorkBook.createCellStyle();
            style.setDataFormat(format.getFormat("0.00"));

            //Remove Data if Exist in Sheet.
            for (int i = 1; i < 65000; i++) {
                Row r1 = allScansSheet.getRow(i);
                if (null != r1) {
                    allScansSheet.removeRow(r1);
                }
            }

            createTabs(allScansSheet, csvModelList, style);

            if (!validateIsStateDropDownSelected(sheetMap)) {
                stateDataMap.forEach((state, data) -> {
                    XSSFSheet stateSheet = sheetMap.get(state);
                    List<CSVModel> dataList = data.getCsvList();
                    createTabs(stateSheet, dataList, style);
                });
            }else{
                nafedDataWorkBook.removeSheetAt(1);
            }

            nafedDataWorkBook.write(out);
            logger.info("Complete creating file, sending data by Email");
            inputStream.close();
            nafedDataWorkBook.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("failed to import data to CSV file: " + e.getMessage());
        }

    }


    private Boolean validateIsStateDropDownSelected(Map<Long, XSSFSheet> sheetMap) {
        Boolean returnVal = Boolean.FALSE;
        if (!sheetMap.isEmpty() && sheetMap.size() == 1) {
            //if ((applicationContext.getRequestContext().getRoles().contains(Constants.CustomerType.STATE_ADMIN)) && sheetMap.get(applicationContext.getRequestContext().getStateAdmin()) != null) {
                returnVal = Boolean.TRUE;
            /*}else{

            }*/
        }
        return returnVal;
    }

    private void createTabs(XSSFSheet sheet, List<CSVModel> csvModelList, XSSFCellStyle style) {
        int rowCount = 0;
        for (CSVModel v : csvModelList) {
            int columnCount = 0;
            Row row = sheet.createRow(++rowCount);

            Cell cell = row.createCell(columnCount);
            cell.setCellValue(
                    null != String.valueOf(v.getSampleId()) ? String.valueOf(String.valueOf(v.getSampleId())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getDateOfScan())
                    ? String.valueOf(String.valueOf(v.getDateOfScan())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getCommodity())
                    ? String.valueOf(String.valueOf(v.getCommodity())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(
                    null != String.valueOf(v.getVariety()) ? String.valueOf(String.valueOf(v.getVariety())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getLocationname())
                    ? String.valueOf(String.valueOf(v.getLocationname())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getWarehousename())
                    ? String.valueOf(String.valueOf(v.getWarehousename())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            cell.setCellValue(null != String.valueOf(v.getBagCount()) ? String.valueOf(String.valueOf(v.getBagCount())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            cell.setCellValue(null != v.getWeight() ? v.getWeight() : new Double(""));

            cell = row.createCell(++columnCount);
            cell.setCellValue(null != String.valueOf(v.getTruckNumber())
                    ? String.valueOf(String.valueOf(v.getTruckNumber())) : "");

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getMoisturecontent())
                cell.setCellValue(v.getMoisturecontent());
            /*else if (null != v.getMoisture())
                cell.setCellValue(v.getMoisture());*/

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getAdmixture())
                cell.setCellValue(v.getAdmixture());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getForeignmatter())
                cell.setCellValue(v.getForeignmatter());
            /*else if (null != v.getFm())
                cell.setCellValue(v.getFm());*/

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getDamaged())
                cell.setCellValue(v.getDamaged());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getWeevilled())
                cell.setCellValue(v.getWeevilled());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getPodsofothervar())
                cell.setCellValue(v.getPodsofothervar());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getImmature())
                cell.setCellValue(v.getImmature());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getSlightlydamaged())
                cell.setCellValue(v.getSlightlydamaged());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getShelling())
                cell.setCellValue(v.getShelling());

            cell = row.createCell(++columnCount);
            cell.setCellStyle(style);
            if (null != v.getOtherfoodgrains())
                cell.setCellValue(v.getOtherfoodgrains());
        }

    }


    public static void copyFromSourceToDestinationRow(XSSFWorkbook workbook, XSSFSheet sourceWorksheet, int sourceRowNum, XSSFSheet destinationWorksheet, int destinationRowNum) {
        // Get the source / new row
        XSSFRow sourceRow = sourceWorksheet.getRow(sourceRowNum);
        XSSFRow newRow = destinationWorksheet.createRow(destinationRowNum);
        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            XSSFCell oldCell = sourceRow.getCell(i);
            XSSFCell newCell = newRow.createCell(i);
            // If the old cell is null jump to next cell
            if (oldCell == null) {
                continue;
            }

            // Copy style from old cell and apply to new cell
            XSSFCellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellTypeEnum());

            // Set the cell data value
            switch (oldCell.getCellTypeEnum()) {
                case BLANK:// Cell.CELL_TYPE_BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
                default:
                    break;
            }
        }

        // If there are are any merged regions in the source row, copy to new row
        for (int i = 0; i < sourceWorksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = sourceWorksheet.getMergedRegion(i);
            if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(newRow.getRowNum(),
                        (newRow.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
                        cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
                destinationWorksheet.addMergedRegion(newCellRangeAddress);
            }
        }
    }


}
