package ai.chat2db.excel.context.xlsx;

import ai.chat2db.excel.context.AnalysisContextImpl;
import ai.chat2db.excel.read.metadata.ReadWorkbook;
import ai.chat2db.excel.read.metadata.holder.xlsx.XlsxReadSheetHolder;
import ai.chat2db.excel.read.metadata.holder.xlsx.XlsxReadWorkbookHolder;
import ai.chat2db.excel.support.ExcelTypeEnum;

/**
 *
 * A context is the main anchorage point of a ls xls reader.
 *
 * @author Jiaju Zhuang
 */
public class DefaultXlsxReadContext extends AnalysisContextImpl implements XlsxReadContext {

    public DefaultXlsxReadContext(ReadWorkbook readWorkbook, ExcelTypeEnum actualExcelType) {
        super(readWorkbook, actualExcelType);
    }

    @Override
    public XlsxReadWorkbookHolder xlsxReadWorkbookHolder() {
        return (XlsxReadWorkbookHolder)readWorkbookHolder();
    }

    @Override
    public XlsxReadSheetHolder xlsxReadSheetHolder() {
        return (XlsxReadSheetHolder)readSheetHolder();
    }
}
