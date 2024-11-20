package ai.chat2db.excel.context.csv;

import ai.chat2db.excel.context.AnalysisContextImpl;
import ai.chat2db.excel.read.metadata.ReadWorkbook;
import ai.chat2db.excel.read.metadata.holder.csv.CsvReadSheetHolder;
import ai.chat2db.excel.read.metadata.holder.csv.CsvReadWorkbookHolder;
import ai.chat2db.excel.support.ExcelTypeEnum;

/**
 * A context is the main anchorage point of a ls xls reader.
 *
 * @author Jiaju Zhuang
 */
public class DefaultCsvReadContext extends AnalysisContextImpl implements CsvReadContext {

    public DefaultCsvReadContext(ReadWorkbook readWorkbook, ExcelTypeEnum actualExcelType) {
        super(readWorkbook, actualExcelType);
    }

    @Override
    public CsvReadWorkbookHolder csvReadWorkbookHolder() {
        return (CsvReadWorkbookHolder)readWorkbookHolder();
    }

    @Override
    public CsvReadSheetHolder csvReadSheetHolder() {
        return (CsvReadSheetHolder)readSheetHolder();
    }
}
