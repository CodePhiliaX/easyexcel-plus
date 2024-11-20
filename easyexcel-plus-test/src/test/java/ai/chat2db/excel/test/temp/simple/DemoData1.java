package ai.chat2db.excel.test.temp.simple;

import java.util.Date;

import ai.chat2db.excel.annotation.ExcelIgnore;
import ai.chat2db.excel.annotation.ExcelProperty;
import ai.chat2db.excel.annotation.write.style.HeadStyle;
import ai.chat2db.excel.enums.poi.FillPatternTypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class DemoData1 {
    @ExcelProperty("字符串标题")
    @HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 42)
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
