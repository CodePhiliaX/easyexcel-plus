package ai.chat2db.excel.test.core.fill;

import ai.chat2db.excel.annotation.ExcelProperty;
import ai.chat2db.excel.annotation.format.NumberFormat;
import ai.chat2db.excel.converters.doubleconverter.DoubleStringConverter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class FillData {
    private String name;
    @NumberFormat("#")
    @ExcelProperty(converter = DoubleStringConverter.class)
    private Double number;
    private String empty;
}
