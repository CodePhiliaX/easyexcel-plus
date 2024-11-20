package ai.chat2db.excel.context.xls;

import ai.chat2db.excel.context.AnalysisContextImpl;
import ai.chat2db.excel.read.metadata.ReadWorkbook;
import ai.chat2db.excel.read.metadata.holder.xls.XlsReadSheetHolder;
import ai.chat2db.excel.read.metadata.holder.xls.XlsReadWorkbookHolder;
import ai.chat2db.excel.support.ExcelTypeEnum;

/**
 *
 * A context is the main anchorage point of a ls xls reader.
 *
 * @author Jiaju Zhuang
 */
public class DefaultXlsReadContext extends AnalysisContextImpl implements XlsReadContext {

    public DefaultXlsReadContext(ReadWorkbook readWorkbook, ExcelTypeEnum actualExcelType) {
        super(readWorkbook, actualExcelType);
    }

    @Override
    public XlsReadWorkbookHolder xlsReadWorkbookHolder() {
        return (XlsReadWorkbookHolder)readWorkbookHolder();
    }

    @Override
    public XlsReadSheetHolder xlsReadSheetHolder() {
        return (XlsReadSheetHolder)readSheetHolder();
    }
}
