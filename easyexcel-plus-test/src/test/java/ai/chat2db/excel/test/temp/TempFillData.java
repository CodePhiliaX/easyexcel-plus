package ai.chat2db.excel.test.temp;

import ai.chat2db.excel.annotation.write.style.ContentRowHeight;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(30)
public class TempFillData {
    private String name;
    private double number;
}
