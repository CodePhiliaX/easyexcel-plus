package ai.chat2db.excel.test.core.head;

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
public class NoHeadData {
    @ExcelProperty("字符串")
    private String string;
}
