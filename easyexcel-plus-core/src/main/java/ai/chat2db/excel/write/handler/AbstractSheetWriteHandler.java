package ai.chat2db.excel.write.handler;

import ai.chat2db.excel.write.metadata.holder.WriteSheetHolder;
import ai.chat2db.excel.write.metadata.holder.WriteWorkbookHolder;

/**
 * Abstract sheet write handler
 *
 * @author Jiaju Zhuang
 * @deprecated Please use it directly {@link SheetWriteHandler}
 **/
@Deprecated
public abstract class AbstractSheetWriteHandler implements SheetWriteHandler {
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }
}
