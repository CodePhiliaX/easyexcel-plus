package ai.chat2db.excel.read.metadata.holder.csv;

import ai.chat2db.excel.read.metadata.ReadWorkbook;
import ai.chat2db.excel.read.metadata.holder.ReadWorkbookHolder;
import ai.chat2db.excel.support.ExcelTypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

/**
 * Workbook holder
 *
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class CsvReadWorkbookHolder extends ReadWorkbookHolder {

    private CSVFormat csvFormat;
    private CSVParser csvParser;

    public CsvReadWorkbookHolder(ReadWorkbook readWorkbook) {
        super(readWorkbook);
        setExcelType(ExcelTypeEnum.CSV);
        this.csvFormat = CSVFormat.DEFAULT;
    }
}
