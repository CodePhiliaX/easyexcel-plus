package ai.chat2db.excel.test.core.style;

import ai.chat2db.excel.annotation.ExcelProperty;
import ai.chat2db.excel.annotation.write.style.HeadFontStyle;
import ai.chat2db.excel.annotation.write.style.HeadStyle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
@HeadStyle
@HeadFontStyle
public class StyleData {
    @ExcelProperty("字符串")
    private String string;
    @ExcelProperty("字符串1")
    private String string1;
}
