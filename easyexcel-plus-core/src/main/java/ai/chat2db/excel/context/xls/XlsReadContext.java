package ai.chat2db.excel.context.xls;

import ai.chat2db.excel.context.AnalysisContext;
import ai.chat2db.excel.read.metadata.holder.xls.XlsReadSheetHolder;
import ai.chat2db.excel.read.metadata.holder.xls.XlsReadWorkbookHolder;

/**
 * A context is the main anchorage point of a ls xls reader.
 *
 * @author Jiaju Zhuang
 **/
public interface XlsReadContext extends AnalysisContext {
    /**
     * All information about the workbook you are currently working on.
     *
     * @return Current workbook holder
     */
    XlsReadWorkbookHolder xlsReadWorkbookHolder();

    /**
     * All information about the sheet you are currently working on.
     *
     * @return Current sheet holder
     */
    XlsReadSheetHolder xlsReadSheetHolder();
}
