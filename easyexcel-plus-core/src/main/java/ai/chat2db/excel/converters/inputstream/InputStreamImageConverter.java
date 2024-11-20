package ai.chat2db.excel.converters.inputstream;

import java.io.IOException;
import java.io.InputStream;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.util.IoUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * File and image converter
 *
 * @author Jiaju Zhuang
 */
public class InputStreamImageConverter implements Converter<InputStream> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return InputStream.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(InputStream value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws IOException {
        return new WriteCellData<>(IoUtils.toByteArray(value));
    }

}
