package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SubRecord;

import ai.chat2db.excel.context.xls.XlsReadContext;

/**
 * Record handler
 *
 * @author Jiaju Zhuang
 */
public class ObjRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {
    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        ObjRecord or = (ObjRecord)record;
        for (SubRecord subRecord : or.getSubRecords()) {
            if (subRecord instanceof CommonObjectDataSubRecord) {
                CommonObjectDataSubRecord codsr = (CommonObjectDataSubRecord)subRecord;
                if (CommonObjectDataSubRecord.OBJECT_TYPE_COMMENT == codsr.getObjectType()) {
                    xlsReadContext.xlsReadSheetHolder().setTempObjectIndex(codsr.getObjectId());
                }
                break;
            }
        }
    }
}
