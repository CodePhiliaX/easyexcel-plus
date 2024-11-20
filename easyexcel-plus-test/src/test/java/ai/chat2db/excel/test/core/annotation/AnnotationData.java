package ai.chat2db.excel.test.core.annotation;

import java.util.Date;

import ai.chat2db.excel.annotation.ExcelIgnore;
import ai.chat2db.excel.annotation.ExcelProperty;
import ai.chat2db.excel.annotation.format.DateTimeFormat;
import ai.chat2db.excel.annotation.format.NumberFormat;
import ai.chat2db.excel.annotation.write.style.ColumnWidth;
import ai.chat2db.excel.annotation.write.style.ContentRowHeight;
import ai.chat2db.excel.annotation.write.style.HeadRowHeight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
@ColumnWidth(50)
@HeadRowHeight(50)
@ContentRowHeight(100)
public class AnnotationData {
    @ExcelProperty("日期")
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date date;

    @ExcelProperty(value = "数字")
    @NumberFormat("#.##%")
    private Double number;

    @ExcelIgnore
    private String ignore;
    private static final String staticFinal = "test";
    private transient String transientString;
}
