package ai.chat2db.excel.converters.floatconverter;

import ai.chat2db.excel.converters.WriteConverterContext;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.NumberUtils;
import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Float and number converter
 *
 * @author Jiaju Zhuang
 */
public class FloatNumberConverter implements Converter<Float> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Float.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Float convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return cellData.getNumberValue().floatValue();
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Float> context) {
        return NumberUtils.formatToCellData(context.getValue(), context.getContentProperty());
    }
}
