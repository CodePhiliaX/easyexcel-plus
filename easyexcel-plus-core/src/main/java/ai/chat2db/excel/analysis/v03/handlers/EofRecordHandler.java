package ai.chat2db.excel.analysis.v03.handlers;

import java.util.LinkedHashMap;

import ai.chat2db.excel.enums.RowTypeEnum;
import ai.chat2db.excel.metadata.Cell;
import ai.chat2db.excel.read.metadata.holder.ReadRowHolder;
import ai.chat2db.excel.read.metadata.holder.xls.XlsReadSheetHolder;
import ai.chat2db.excel.util.BooleanUtils;
import org.apache.poi.hssf.record.Record;

import ai.chat2db.excel.context.xls.XlsReadContext;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class EofRecordHandler extends AbstractXlsRecordHandler {

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        if (xlsReadContext.readSheetHolder() == null) {
            return;
        }

        //Represents the current sheet does not need to be read or the user manually stopped reading the sheet.
        if (BooleanUtils.isTrue(xlsReadContext.xlsReadWorkbookHolder().getIgnoreRecord())) {
            // When the user manually stops reading the sheet, the method to end the sheet needs to be called.
            if (BooleanUtils.isTrue(xlsReadContext.xlsReadWorkbookHolder().getCurrentSheetStopped())) {
                xlsReadContext.analysisEventProcessor().endSheet(xlsReadContext);
            }
            return;
        }

        // Sometimes tables lack the end record of the last column
        if (!xlsReadContext.xlsReadSheetHolder().getCellMap().isEmpty()) {
            XlsReadSheetHolder xlsReadSheetHolder = xlsReadContext.xlsReadSheetHolder();
            // Forge a termination data
            xlsReadContext.readRowHolder(new ReadRowHolder(xlsReadContext.xlsReadSheetHolder().getRowIndex() + 1,
                xlsReadSheetHolder.getTempRowType(),
                xlsReadContext.readSheetHolder().getGlobalConfiguration(), xlsReadSheetHolder.getCellMap()));
            xlsReadContext.analysisEventProcessor().endRow(xlsReadContext);
            xlsReadSheetHolder.setCellMap(new LinkedHashMap<Integer, Cell>());
            xlsReadSheetHolder.setTempRowType(RowTypeEnum.EMPTY);
        }

        xlsReadContext.analysisEventProcessor().endSheet(xlsReadContext);
    }
}
