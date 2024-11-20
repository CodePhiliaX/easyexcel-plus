package ai.chat2db.excel.write.handler.impl;

import ai.chat2db.excel.write.handler.RowWriteHandler;
import ai.chat2db.excel.write.handler.context.RowWriteHandlerContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Default row handler.
 *
 * @author Jiaju Zhuang
 */
@Slf4j
public class DefaultRowWriteHandler implements RowWriteHandler {

    @Override
    public void afterRowDispose(RowWriteHandlerContext context) {
        context.getWriteSheetHolder().setLastRowIndex(context.getRowIndex());
    }

}
