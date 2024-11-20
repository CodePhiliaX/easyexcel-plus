package ai.chat2db.excel.context.csv;

import ai.chat2db.excel.context.AnalysisContext;
import ai.chat2db.excel.read.metadata.holder.csv.CsvReadSheetHolder;
import ai.chat2db.excel.read.metadata.holder.csv.CsvReadWorkbookHolder;

/**
 * A context is the main anchorage point of a ls xls reader.
 *
 * @author Jiaju Zhuang
 **/
public interface CsvReadContext extends AnalysisContext {
    /**
     * All information about the workbook you are currently working on.
     *
     * @return Current workbook holder
     */
    CsvReadWorkbookHolder csvReadWorkbookHolder();

    /**
     * All information about the sheet you are currently working on.
     *
     * @return Current sheet holder
     */
    CsvReadSheetHolder csvReadSheetHolder();
}
