package ai.chat2db.excel.test.core.repetition;

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
public class RepetitionData {
    @ExcelProperty("字符串")
    private String string;
}
