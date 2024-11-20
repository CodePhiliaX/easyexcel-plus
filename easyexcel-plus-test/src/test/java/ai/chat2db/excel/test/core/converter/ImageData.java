package ai.chat2db.excel.test.core.converter;

import java.io.File;
import java.io.InputStream;

import ai.chat2db.excel.annotation.ExcelProperty;
import ai.chat2db.excel.annotation.write.style.ColumnWidth;
import ai.chat2db.excel.annotation.write.style.ContentRowHeight;
import ai.chat2db.excel.converters.string.StringImageConverter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(500)
@ColumnWidth(500 / 8)
public class ImageData {
    private File file;
    private InputStream inputStream;
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;
    private byte[] byteArray;
}
