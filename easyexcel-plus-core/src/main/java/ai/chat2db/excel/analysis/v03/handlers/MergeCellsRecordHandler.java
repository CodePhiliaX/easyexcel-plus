package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.enums.CellExtraTypeEnum;
import ai.chat2db.excel.metadata.CellExtra;
import org.apache.poi.hssf.record.MergeCellsRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.ss.util.CellRangeAddress;

import ai.chat2db.excel.context.xls.XlsReadContext;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class MergeCellsRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {

    @Override
    public boolean support(XlsReadContext xlsReadContext, Record record) {
        return xlsReadContext.readWorkbookHolder().getExtraReadSet().contains(CellExtraTypeEnum.MERGE);
    }

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        MergeCellsRecord mcr = (MergeCellsRecord)record;
        for (int i = 0; i < mcr.getNumAreas(); i++) {
            CellRangeAddress cellRangeAddress = mcr.getAreaAt(i);
            CellExtra cellExtra = new CellExtra(CellExtraTypeEnum.MERGE, null, cellRangeAddress.getFirstRow(),
                cellRangeAddress.getLastRow(), cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
            xlsReadContext.xlsReadSheetHolder().setCellExtra(cellExtra);
            xlsReadContext.analysisEventProcessor().extra(xlsReadContext);
        }
    }
}
