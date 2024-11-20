package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import org.apache.poi.hssf.record.IndexRecord;
import org.apache.poi.hssf.record.Record;

import ai.chat2db.excel.context.xls.XlsReadContext;

/**
 * Record handler
 *
 * @author Jiaju Zhuang
 */
public class IndexRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {
    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        if (xlsReadContext.readSheetHolder() == null) {
            return;
        }
        xlsReadContext.readSheetHolder().setApproximateTotalRowNumber(((IndexRecord)record).getLastRowAdd1());
    }
}
