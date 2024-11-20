package ai.chat2db.excel.write.handler.context;

import ai.chat2db.excel.context.WriteContext;
import ai.chat2db.excel.write.metadata.holder.WriteSheetHolder;
import ai.chat2db.excel.write.metadata.holder.WriteWorkbookHolder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * sheet context
 *
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class SheetWriteHandlerContext {
    /**
     * write context
     */
    private WriteContext writeContext;
    /**
     * workbook
     */
    private WriteWorkbookHolder writeWorkbookHolder;
    /**
     * sheet
     */
    private WriteSheetHolder writeSheetHolder;
}
