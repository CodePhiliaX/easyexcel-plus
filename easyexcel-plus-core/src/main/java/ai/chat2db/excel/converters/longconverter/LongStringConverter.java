package ai.chat2db.excel.converters.longconverter;

import java.text.ParseException;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.NumberUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Long and string converter
 *
 * @author Jiaju Zhuang
 */
public class LongStringConverter implements Converter<Long> {

    @Override
    public Class<Long> supportJavaTypeKey() {
        return Long.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Long convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws ParseException {
        return NumberUtils.parseLong(cellData.getStringValue(), contentProperty);
    }

    @Override
    public WriteCellData<?> convertToExcelData(Long value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return NumberUtils.formatToCellDataString(value, contentProperty);
    }
}
