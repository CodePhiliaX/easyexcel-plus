package ai.chat2db.excel.converters.floatconverter;

import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Float and boolean converter
 *
 * @author Jiaju Zhuang
 */
public class FloatBooleanConverter implements Converter<Float> {
    private static final Float ONE = (float)1.0;
    private static final Float ZERO = (float)0.0;

    @Override
    public Class<?> supportJavaTypeKey() {
        return Float.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.BOOLEAN;
    }

    @Override
    public Float convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (cellData.getBooleanValue()) {
            return ONE;
        }
        return ZERO;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Float value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (ONE.equals(value)) {
            return new WriteCellData<>(Boolean.TRUE);
        }
        return new WriteCellData<>(Boolean.FALSE);
    }

}
