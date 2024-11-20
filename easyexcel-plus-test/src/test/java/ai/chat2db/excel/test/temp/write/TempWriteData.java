package ai.chat2db.excel.test.temp.write;

import ai.chat2db.excel.annotation.ExcelProperty;
import ai.chat2db.excel.annotation.write.style.ContentStyle;
import ai.chat2db.excel.annotation.write.style.HeadStyle;
import ai.chat2db.excel.enums.BooleanEnum;

import lombok.Data;

@Data
//@Accessors(chain = true)
public class TempWriteData {
    private String name1;

    @ExcelProperty("     换行\r\n \\ \r\n的名字")
    @HeadStyle(wrapped = BooleanEnum.TRUE)
    @ContentStyle(wrapped = BooleanEnum.TRUE)
    private String name;
}
