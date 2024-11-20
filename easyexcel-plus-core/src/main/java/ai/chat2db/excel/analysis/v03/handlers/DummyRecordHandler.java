package ai.chat2db.excel.analysis.v03.handlers;

import java.util.LinkedHashMap;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.enums.RowTypeEnum;
import ai.chat2db.excel.metadata.Cell;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.read.metadata.holder.ReadRowHolder;
import ai.chat2db.excel.read.metadata.holder.xls.XlsReadSheetHolder;
import ai.chat2db.excel.context.xls.XlsReadContext;

import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.record.Record;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class DummyRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {
    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        XlsReadSheetHolder xlsReadSheetHolder = xlsReadContext.xlsReadSheetHolder();
        if (record instanceof LastCellOfRowDummyRecord) {
            // End of this row
            LastCellOfRowDummyRecord lcrdr = (LastCellOfRowDummyRecord)record;
            xlsReadSheetHolder.setRowIndex(lcrdr.getRow());
            xlsReadContext.readRowHolder(new ReadRowHolder(lcrdr.getRow(), xlsReadSheetHolder.getTempRowType(),
                xlsReadContext.readSheetHolder().getGlobalConfiguration(), xlsReadSheetHolder.getCellMap()));
            xlsReadContext.analysisEventProcessor().endRow(xlsReadContext);
            xlsReadSheetHolder.setCellMap(new LinkedHashMap<Integer, Cell>());
            xlsReadSheetHolder.setTempRowType(RowTypeEnum.EMPTY);
        } else if (record instanceof MissingCellDummyRecord) {
            MissingCellDummyRecord mcdr = (MissingCellDummyRecord)record;
            // https://github.com/CodePhiliaX/easyexcel-plus/issues/2236
            // Some abnormal XLS, in the case of data already exist, or there will be a "MissingCellDummyRecord"
            // records, so if the existing data, empty data is ignored
            xlsReadSheetHolder.getCellMap().putIfAbsent(mcdr.getColumn(),
                ReadCellData.newEmptyInstance(mcdr.getRow(), mcdr.getColumn()));
        }
    }
}
