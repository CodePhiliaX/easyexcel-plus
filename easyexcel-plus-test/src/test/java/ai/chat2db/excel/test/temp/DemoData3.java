package ai.chat2db.excel.test.temp;

import java.time.LocalDateTime;

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
public class DemoData3 {
    @ExcelProperty("日期时间标题")
    private LocalDateTime localDateTime;
}
