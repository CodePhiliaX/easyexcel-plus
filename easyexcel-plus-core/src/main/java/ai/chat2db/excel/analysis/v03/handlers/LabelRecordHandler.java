package ai.chat2db.excel.analysis.v03.handlers;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.enums.RowTypeEnum;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.context.xls.XlsReadContext;

import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.Record;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class LabelRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {
    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        LabelRecord lrec = (LabelRecord)record;
        String data = lrec.getValue();
        if (data != null && xlsReadContext.currentReadHolder().globalConfiguration().getAutoTrim()) {
            data = data.trim();
        }
        xlsReadContext.xlsReadSheetHolder().getCellMap().put((int)lrec.getColumn(),
            ReadCellData.newInstance(data, lrec.getRow(), (int)lrec.getColumn()));
        xlsReadContext.xlsReadSheetHolder().setTempRowType(RowTypeEnum.DATA);
    }
}
