package ai.chat2db.excel.test.temp;

import java.math.BigDecimal;
import java.util.Date;

import ai.chat2db.excel.annotation.ExcelIgnore;
import ai.chat2db.excel.annotation.ExcelProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础数据类
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
public class DemoData2 {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelProperty("数字标题2")
    private BigDecimal bigDecimal;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
