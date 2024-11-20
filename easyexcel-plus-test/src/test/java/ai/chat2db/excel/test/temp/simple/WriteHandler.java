package ai.chat2db.excel.test.temp.simple;

import ai.chat2db.excel.write.handler.SheetWriteHandler;
import ai.chat2db.excel.write.metadata.holder.WriteSheetHolder;
import ai.chat2db.excel.write.metadata.holder.WriteWorkbookHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Jiaju Zhuang
 */
@Slf4j
public class WriteHandler implements SheetWriteHandler {

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder,
        WriteSheetHolder writeSheetHolder) {
        log.info("锁住");
        writeSheetHolder.getSheet().protectSheet("edit");
    }
}
