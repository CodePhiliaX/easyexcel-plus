package ai.chat2db.excel.test.core.excludeorinclude;

import ai.chat2db.excel.annotation.ExcelProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class ExcludeOrIncludeData {
    @ExcelProperty(order = 1)
    private String column1;
    @ExcelProperty(order = 2)
    private String column2;
    @ExcelProperty(order = 3)
    private String column3;
    @ExcelProperty(order = 4)
    private String column4;
}
