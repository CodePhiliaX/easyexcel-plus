package ai.chat2db.excel.write.style.column;

import java.util.List;

import ai.chat2db.excel.write.handler.CellWriteHandler;
import ai.chat2db.excel.write.handler.context.CellWriteHandlerContext;
import ai.chat2db.excel.metadata.Head;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.write.metadata.holder.WriteSheetHolder;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Column width style strategy
 *
 * @author Jiaju Zhuang
 */
public abstract class AbstractColumnWidthStyleStrategy implements CellWriteHandler {

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        setColumnWidth(context);
    }

    /**
     * Sets the column width when head create
     *
     * @param context
     */
    protected void setColumnWidth(CellWriteHandlerContext context) {
        setColumnWidth(context.getWriteSheetHolder(), context.getCellDataList(), context.getCell(),
            context.getHeadData(), context.getRelativeRowIndex(), context.getHead());
    }

    /**
     * Sets the column width when head create
     *
     * @param writeSheetHolder
     * @param cellDataList
     * @param cell
     * @param head
     * @param relativeRowIndex
     * @param isHead
     */
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell,
        Head head, Integer relativeRowIndex, Boolean isHead) {
        throw new UnsupportedOperationException("Custom styles must override the setColumnWidth method.");
    }

}
