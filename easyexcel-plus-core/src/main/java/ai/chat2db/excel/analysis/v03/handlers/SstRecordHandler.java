package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.cache.XlsCache;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;

import ai.chat2db.excel.context.xls.XlsReadContext;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class SstRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {
    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        xlsReadContext.readWorkbookHolder().setReadCache(new XlsCache((SSTRecord)record));
    }
}
