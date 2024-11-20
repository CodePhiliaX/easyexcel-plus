package ai.chat2db.excel.analysis.v03.handlers;

import java.math.BigDecimal;
import java.util.Map;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.constant.BuiltinFormats;
import ai.chat2db.excel.constant.EasyExcelConstants;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.enums.RowTypeEnum;
import ai.chat2db.excel.metadata.Cell;
import ai.chat2db.excel.metadata.data.DataFormatData;
import ai.chat2db.excel.metadata.data.FormulaData;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.context.xls.XlsReadContext;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.ss.usermodel.CellType;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
@Slf4j
public class FormulaRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {
    private static final String ERROR = "#VALUE!";

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        FormulaRecord frec = (FormulaRecord)record;
        Map<Integer, Cell> cellMap = xlsReadContext.xlsReadSheetHolder().getCellMap();
        ReadCellData<?> tempCellData = new ReadCellData<>();
        tempCellData.setRowIndex(frec.getRow());
        tempCellData.setColumnIndex((int)frec.getColumn());
        CellType cellType = CellType.forInt(frec.getCachedResultType());
        String formulaValue = null;
        try {
            formulaValue = HSSFFormulaParser.toFormulaString(xlsReadContext.xlsReadWorkbookHolder().getHssfWorkbook(),
                frec.getParsedExpression());
        } catch (Exception e) {
            log.debug("Get formula value error.", e);
        }
        FormulaData formulaData = new FormulaData();
        formulaData.setFormulaValue(formulaValue);
        tempCellData.setFormulaData(formulaData);
        xlsReadContext.xlsReadSheetHolder().setTempRowType(RowTypeEnum.DATA);
        switch (cellType) {
            case STRING:
                // Formula result is a string
                // This is stored in the next record
                tempCellData.setType(CellDataTypeEnum.STRING);
                xlsReadContext.xlsReadSheetHolder().setTempCellData(tempCellData);
                break;
            case NUMERIC:
                tempCellData.setType(CellDataTypeEnum.NUMBER);
                tempCellData.setOriginalNumberValue(BigDecimal.valueOf(frec.getValue()));
                tempCellData.setNumberValue(
                    tempCellData.getOriginalNumberValue().round(EasyExcelConstants.EXCEL_MATH_CONTEXT));
                int dataFormat =
                    xlsReadContext.xlsReadWorkbookHolder().getFormatTrackingHSSFListener().getFormatIndex(frec);
                DataFormatData dataFormatData = new DataFormatData();
                dataFormatData.setIndex((short)dataFormat);
                dataFormatData.setFormat(BuiltinFormats.getBuiltinFormat(dataFormatData.getIndex(),
                    xlsReadContext.xlsReadWorkbookHolder().getFormatTrackingHSSFListener().getFormatString(frec),
                    xlsReadContext.readSheetHolder().getGlobalConfiguration().getLocale()));
                tempCellData.setDataFormatData(dataFormatData);
                cellMap.put((int)frec.getColumn(), tempCellData);
                break;
            case ERROR:
                tempCellData.setType(CellDataTypeEnum.ERROR);
                tempCellData.setStringValue(ERROR);
                cellMap.put((int)frec.getColumn(), tempCellData);
                break;
            case BOOLEAN:
                tempCellData.setType(CellDataTypeEnum.BOOLEAN);
                tempCellData.setBooleanValue(frec.getCachedBooleanValue());
                cellMap.put((int)frec.getColumn(), tempCellData);
                break;
            default:
                tempCellData.setType(CellDataTypeEnum.EMPTY);
                cellMap.put((int)frec.getColumn(), tempCellData);
                break;
        }
    }
}
