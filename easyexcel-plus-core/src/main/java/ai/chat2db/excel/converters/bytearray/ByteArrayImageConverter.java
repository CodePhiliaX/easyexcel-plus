package ai.chat2db.excel.converters.bytearray;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Byte array and image converter
 *
 * @author Jiaju Zhuang
 */
public class ByteArrayImageConverter implements Converter<byte[]> {

    @Override
    public Class<byte[]> supportJavaTypeKey() {
        return byte[].class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(byte[] value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return new WriteCellData<>(value);
    }

}
