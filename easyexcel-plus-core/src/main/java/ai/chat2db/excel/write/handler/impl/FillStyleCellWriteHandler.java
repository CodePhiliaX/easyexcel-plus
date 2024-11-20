package ai.chat2db.excel.write.handler.impl;

import ai.chat2db.excel.write.handler.CellWriteHandler;
import ai.chat2db.excel.constant.OrderConstant;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.util.BooleanUtils;
import ai.chat2db.excel.write.handler.context.CellWriteHandlerContext;
import ai.chat2db.excel.write.metadata.holder.WriteWorkbookHolder;
import ai.chat2db.excel.write.metadata.style.WriteCellStyle;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * fill cell style.
 *
 * @author Jiaju Zhuang
 */
@Slf4j
public class FillStyleCellWriteHandler implements CellWriteHandler {

    @Override
    public int order() {
        return OrderConstant.FILL_STYLE;
    }

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        if (BooleanUtils.isTrue(context.getIgnoreFillStyle())) {
            return;
        }

        WriteCellData<?> cellData = context.getFirstCellData();
        if (cellData == null) {
            return;
        }
        WriteCellStyle writeCellStyle = cellData.getWriteCellStyle();
        CellStyle originCellStyle = cellData.getOriginCellStyle();
        if (writeCellStyle == null && originCellStyle == null) {
            return;
        }
        WriteWorkbookHolder writeWorkbookHolder = context.getWriteWorkbookHolder();
        context.getCell().setCellStyle(writeWorkbookHolder.createCellStyle(writeCellStyle, originCellStyle));
    }

}
