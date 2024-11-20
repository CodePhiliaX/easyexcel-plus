package ai.chat2db.excel.write.merge;

import ai.chat2db.excel.write.handler.CellWriteHandler;
import ai.chat2db.excel.write.handler.context.CellWriteHandlerContext;
import ai.chat2db.excel.metadata.Head;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Merge strategy
 *
 * @author Jiaju Zhuang
 */
public abstract class AbstractMergeStrategy implements CellWriteHandler {

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        if (context.getHead()) {
            return;
        }
        merge(context.getWriteSheetHolder().getSheet(), context.getCell(), context.getHeadData(),
            context.getRelativeRowIndex());
    }

    /**
     * merge
     *
     * @param sheet
     * @param cell
     * @param head
     * @param relativeRowIndex
     */
    protected abstract void merge(Sheet sheet, Cell cell, Head head, Integer relativeRowIndex);
}
