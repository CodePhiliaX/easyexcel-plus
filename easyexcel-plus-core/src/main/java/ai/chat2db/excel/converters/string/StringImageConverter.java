package ai.chat2db.excel.converters.string;

import java.io.File;
import java.io.IOException;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.util.FileUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * String and image converter
 *
 * @author Jiaju Zhuang
 */
public class StringImageConverter implements Converter<String> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(String value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws IOException {
        return new WriteCellData<>(FileUtils.readFileToByteArray(new File(value)));
    }

}
