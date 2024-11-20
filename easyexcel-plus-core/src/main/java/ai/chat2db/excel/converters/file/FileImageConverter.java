package ai.chat2db.excel.converters.file;

import java.io.File;
import java.io.IOException;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.util.FileUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * File and image converter
 *
 * @author Jiaju Zhuang
 */
public class FileImageConverter implements Converter<File> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return File.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(File value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws IOException {
        return new WriteCellData<>(FileUtils.readFileToByteArray(value));
    }
}
