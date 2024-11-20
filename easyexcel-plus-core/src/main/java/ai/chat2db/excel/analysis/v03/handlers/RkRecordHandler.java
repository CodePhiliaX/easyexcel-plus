package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.context.xls.XlsReadContext;

import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.Record;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class RkRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        RKRecord re = (RKRecord)record;
        xlsReadContext.xlsReadSheetHolder().getCellMap().put((int)re.getColumn(),
            ReadCellData.newEmptyInstance(re.getRow(), (int)re.getColumn()));
    }
}
