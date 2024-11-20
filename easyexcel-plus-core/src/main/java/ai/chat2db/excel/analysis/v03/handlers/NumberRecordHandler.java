package ai.chat2db.excel.analysis.v03.handlers;

import java.math.BigDecimal;

import ai.chat2db.excel.analysis.v03.IgnorableXlsRecordHandler;
import ai.chat2db.excel.constant.BuiltinFormats;
import ai.chat2db.excel.enums.RowTypeEnum;
import ai.chat2db.excel.metadata.data.DataFormatData;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.context.xls.XlsReadContext;

import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class NumberRecordHandler extends AbstractXlsRecordHandler implements IgnorableXlsRecordHandler {

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        NumberRecord nr = (NumberRecord)record;
        ReadCellData<?> cellData = ReadCellData.newInstanceOriginal(BigDecimal.valueOf(nr.getValue()), nr.getRow(),
            (int)nr.getColumn());
        short dataFormat = (short)xlsReadContext.xlsReadWorkbookHolder().getFormatTrackingHSSFListener().getFormatIndex(
            nr);
        DataFormatData dataFormatData = new DataFormatData();
        dataFormatData.setIndex(dataFormat);
        dataFormatData.setFormat(BuiltinFormats.getBuiltinFormat(dataFormat,
            xlsReadContext.xlsReadWorkbookHolder().getFormatTrackingHSSFListener().getFormatString(nr),
            xlsReadContext.readSheetHolder().getGlobalConfiguration().getLocale()));
        cellData.setDataFormatData(dataFormatData);
        xlsReadContext.xlsReadSheetHolder().getCellMap().put((int)nr.getColumn(), cellData);
        xlsReadContext.xlsReadSheetHolder().setTempRowType(RowTypeEnum.DATA);
    }
}
