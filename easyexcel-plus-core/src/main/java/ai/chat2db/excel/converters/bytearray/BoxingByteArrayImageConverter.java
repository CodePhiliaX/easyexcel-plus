package ai.chat2db.excel.converters.bytearray;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Boxing Byte array and image converter
 *
 * @author Jiaju Zhuang
 */
public class BoxingByteArrayImageConverter implements Converter<Byte[]> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Byte[].class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Byte[] value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        byte[] byteValue = new byte[value.length];
        for (int i = 0; i < value.length; i++) {
            byteValue[i] = value[i];
        }
        return new WriteCellData<>(byteValue);
    }

}
