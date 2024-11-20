package ai.chat2db.excel.test.core.cache;

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
public class CacheData {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Long age;
}
