package ai.chat2db.excel.test.demo.write;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import ai.chat2db.excel.annotation.write.style.ContentRowHeight;
import ai.chat2db.excel.converters.string.StringImageConverter;
import ai.chat2db.excel.annotation.ExcelProperty;
import ai.chat2db.excel.annotation.write.style.ColumnWidth;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 图片导出类
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(100)
@ColumnWidth(100 / 8)
public class ImageDataWithAnnotation {
    private File file;
    private InputStream inputStream;
    /**
     * 如果string类型 必须指定转换器，string默认转换成string
     */
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;
    private byte[] byteArray;
    /**
     * 根据url导出
     *
     * @since 2.1.1
     */
    private URL url;
}
