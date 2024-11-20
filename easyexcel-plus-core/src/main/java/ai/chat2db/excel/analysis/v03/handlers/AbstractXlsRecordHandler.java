package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.XlsRecordHandler;
import org.apache.poi.hssf.record.Record;

import ai.chat2db.excel.context.xls.XlsReadContext;

/**
 * Abstract xls record handler
 *
 * @author Jiaju Zhuang
 **/
public abstract class AbstractXlsRecordHandler implements XlsRecordHandler {

    @Override
    public boolean support(XlsReadContext xlsReadContext, Record record) {
        return true;
    }
}
