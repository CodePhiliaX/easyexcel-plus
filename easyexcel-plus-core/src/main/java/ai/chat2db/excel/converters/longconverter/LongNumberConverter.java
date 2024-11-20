package ai.chat2db.excel.converters.longconverter;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.converters.WriteConverterContext;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.NumberUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Long and number converter
 *
 * @author Jiaju Zhuang
 */
public class LongNumberConverter implements Converter<Long> {

    @Override
    public Class<Long> supportJavaTypeKey() {
        return Long.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Long convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return cellData.getNumberValue().longValue();
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Long> context) {
        return NumberUtils.formatToCellData(context.getValue(), context.getContentProperty());
    }

}
