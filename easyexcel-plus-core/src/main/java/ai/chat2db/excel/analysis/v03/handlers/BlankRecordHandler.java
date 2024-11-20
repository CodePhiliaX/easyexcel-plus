package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.context.xls.XlsReadContext;

import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.Record;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class BlankRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        BlankRecord br = (BlankRecord)record;
        xlsReadContext.xlsReadSheetHolder().getCellMap().put((int)br.getColumn(),
            ReadCellData.newEmptyInstance(br.getRow(), (int)br.getColumn()));
    }
}
