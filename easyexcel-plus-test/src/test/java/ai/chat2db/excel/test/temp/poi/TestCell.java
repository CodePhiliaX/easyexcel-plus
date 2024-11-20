package ai.chat2db.excel.test.temp.poi;

import java.util.List;

import ai.chat2db.excel.metadata.data.CellData;

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
public class TestCell {
    private CellData<?> c1;
    private CellData<List<String>> c2;
}
