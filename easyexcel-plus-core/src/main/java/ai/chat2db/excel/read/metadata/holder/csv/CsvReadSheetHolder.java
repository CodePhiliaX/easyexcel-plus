package ai.chat2db.excel.read.metadata.holder.csv;

import ai.chat2db.excel.read.metadata.ReadSheet;
import ai.chat2db.excel.read.metadata.holder.ReadSheetHolder;
import ai.chat2db.excel.read.metadata.holder.ReadWorkbookHolder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * sheet holder
 *
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class CsvReadSheetHolder extends ReadSheetHolder {

    public CsvReadSheetHolder(ReadSheet readSheet, ReadWorkbookHolder readWorkbookHolder) {
        super(readSheet, readWorkbookHolder);
    }
}
