package ai.chat2db.excel.test.temp.dataformat;

import ai.chat2db.excel.metadata.data.ReadCellData;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author 罗成
 **/
@Getter
@Setter
@EqualsAndHashCode
public class DataFormatData {
    private ReadCellData<String> date;
    private ReadCellData<String> num;
}
