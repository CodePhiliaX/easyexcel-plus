package ai.chat2db.excel.write;

import java.util.Collection;

import ai.chat2db.excel.context.WriteContext;
import ai.chat2db.excel.context.WriteContextImpl;
import ai.chat2db.excel.enums.WriteTypeEnum;
import ai.chat2db.excel.exception.ExcelGenerateException;
import ai.chat2db.excel.support.ExcelTypeEnum;
import ai.chat2db.excel.util.FileUtils;
import ai.chat2db.excel.write.executor.ExcelWriteAddExecutor;
import ai.chat2db.excel.write.executor.ExcelWriteFillExecutor;
import ai.chat2db.excel.write.metadata.WriteSheet;
import ai.chat2db.excel.write.metadata.WriteTable;
import ai.chat2db.excel.write.metadata.WriteWorkbook;
import ai.chat2db.excel.write.metadata.fill.FillConfig;

import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author jipengfei
 */
public class ExcelBuilderImpl implements ExcelBuilder {

    private final WriteContext context;
    private ExcelWriteFillExecutor excelWriteFillExecutor;
    private ExcelWriteAddExecutor excelWriteAddExecutor;

    static {
        // Create temporary cache directory at initialization time to avoid POI concurrent write bugs
        FileUtils.createPoiFilesDirectory();
    }

    public ExcelBuilderImpl(WriteWorkbook writeWorkbook) {
        try {
            context = new WriteContextImpl(writeWorkbook);
        } catch (RuntimeException e) {
            finishOnException();
            throw e;
        } catch (Throwable e) {
            finishOnException();
            throw new ExcelGenerateException(e);
        }
    }

    @Override
    public void addContent(Collection<?> data, WriteSheet writeSheet) {
        addContent(data, writeSheet, null);
    }

    @Override
    public void addContent(Collection<?> data, WriteSheet writeSheet, WriteTable writeTable) {
        try {
            context.currentSheet(writeSheet, WriteTypeEnum.ADD);
            context.currentTable(writeTable);
            if (excelWriteAddExecutor == null) {
                excelWriteAddExecutor = new ExcelWriteAddExecutor(context);
            }
            excelWriteAddExecutor.add(data);
        } catch (RuntimeException e) {
            finishOnException();
            throw e;
        } catch (Throwable e) {
            finishOnException();
            throw new ExcelGenerateException(e);
        }
    }

    @Override
    public void fill(Object data, FillConfig fillConfig, WriteSheet writeSheet) {
        try {
            if (context.writeWorkbookHolder().getTempTemplateInputStream() == null) {
                throw new ExcelGenerateException("Calling the 'fill' method must use a template.");
            }
            if (context.writeWorkbookHolder().getExcelType() == ExcelTypeEnum.CSV) {
                throw new ExcelGenerateException("csv does not support filling data.");
            }
            context.currentSheet(writeSheet, WriteTypeEnum.FILL);
            if (excelWriteFillExecutor == null) {
                excelWriteFillExecutor = new ExcelWriteFillExecutor(context);
            }
            excelWriteFillExecutor.fill(data, fillConfig);
        } catch (RuntimeException e) {
            finishOnException();
            throw e;
        } catch (Throwable e) {
            finishOnException();
            throw new ExcelGenerateException(e);
        }
    }

    private void finishOnException() {
        finish(true);
    }

    @Override
    public void finish(boolean onException) {
        if (context != null) {
            context.finish(onException);
        }
    }

    @Override
    public void merge(int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        context.writeSheetHolder().getSheet().addMergedRegion(cra);
    }

    @Override
    public WriteContext writeContext() {
        return context;
    }
}
