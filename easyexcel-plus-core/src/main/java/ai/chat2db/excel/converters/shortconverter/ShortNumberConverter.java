package ai.chat2db.excel.converters.shortconverter;

import ai.chat2db.excel.converters.WriteConverterContext;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.NumberUtils;
import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Short and number converter
 *
 * @author Jiaju Zhuang
 */
public class ShortNumberConverter implements Converter<Short> {

    @Override
    public Class<Short> supportJavaTypeKey() {
        return Short.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Short convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return cellData.getNumberValue().shortValue();
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Short> context) {
        return NumberUtils.formatToCellData(context.getValue(), context.getContentProperty());
    }
}
