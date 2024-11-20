package ai.chat2db.excel.converters.booleanconverter;

import java.math.BigDecimal;

import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Boolean and number converter
 *
 * @author Jiaju Zhuang
 */
public class BooleanNumberConverter implements Converter<Boolean> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Boolean.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public Boolean convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (BigDecimal.ONE.compareTo(cellData.getNumberValue()) == 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Boolean value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (value) {
            return new WriteCellData<>(BigDecimal.ONE);
        }
        return new WriteCellData<>(BigDecimal.ZERO);
    }

}
