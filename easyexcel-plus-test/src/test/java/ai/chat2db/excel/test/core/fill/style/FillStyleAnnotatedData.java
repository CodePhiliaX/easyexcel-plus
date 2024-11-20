package ai.chat2db.excel.test.core.fill.style;

import java.util.Date;

import ai.chat2db.excel.enums.BooleanEnum;
import ai.chat2db.excel.enums.poi.FillPatternTypeEnum;
import ai.chat2db.excel.annotation.write.style.ContentFontStyle;
import ai.chat2db.excel.annotation.write.style.ContentStyle;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class FillStyleAnnotatedData {
    @ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 13)
    @ContentFontStyle(bold = BooleanEnum.TRUE, color = 19)
    private String name;
    @ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 10)
    @ContentFontStyle(bold = BooleanEnum.TRUE, color = 16)
    private Double number;
    @ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 17)
    @ContentFontStyle(bold = BooleanEnum.TRUE, color = 58)
    private Date date;
    @ContentStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 12)
    @ContentFontStyle(bold = BooleanEnum.TRUE, color = 18)
    private String empty;
}
