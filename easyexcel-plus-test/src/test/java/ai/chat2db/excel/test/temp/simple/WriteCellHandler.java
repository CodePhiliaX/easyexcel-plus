package ai.chat2db.excel.test.temp.simple;

import ai.chat2db.excel.metadata.Head;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.write.handler.CellWriteHandler;
import ai.chat2db.excel.write.metadata.holder.WriteSheetHolder;
import ai.chat2db.excel.write.metadata.holder.WriteTableHolder;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author Jiaju Zhuang
 */
@Slf4j
public class WriteCellHandler implements CellWriteHandler {

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                       WriteCellData<?> cellData, Cell cell, Head head, Integer integer, Boolean isHead) {

        if (!isHead) {
            CreationHelper createHelper = writeSheetHolder.getSheet().getWorkbook().getCreationHelper();
            CellStyle cellStyle = writeSheetHolder.getSheet().getWorkbook().createCellStyle();
            if (cellStyle != null) {
                DataFormat dataFormat = createHelper.createDataFormat();
                cellStyle.setWrapText(true);
                cellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
                cellStyle.setBottomBorderColor(IndexedColors.RED.getIndex());
                cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
                cell.setCellStyle(cellStyle);
            }
        }
    }
}
