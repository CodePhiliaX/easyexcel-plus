package ai.chat2db.excel.test.temp.read;

import ai.chat2db.excel.annotation.ExcelProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 临时测试
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
public class HeadReadData {
    @ExcelProperty({"主标题", "数据1"})
    private String h1;
    @ExcelProperty({"主标题", "数据2"})
    private String h2;
}
