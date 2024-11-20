package ai.chat2db.excel.test.core.celldata;

import java.util.Date;

import ai.chat2db.excel.annotation.format.DateTimeFormat;
import ai.chat2db.excel.metadata.data.WriteCellData;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class CellDataWriteData {
    @DateTimeFormat("yyyy年MM月dd日")
    private WriteCellData<Date> date;
    private WriteCellData<Integer> integer1;
    private Integer integer2;
    private WriteCellData<?> formulaValue;
}
