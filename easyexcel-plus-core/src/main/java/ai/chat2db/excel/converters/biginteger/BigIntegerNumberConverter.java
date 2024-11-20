package ai.chat2db.excel.converters.biginteger;

import java.math.BigInteger;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.NumberUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * BigInteger and number converter
 *
 * @author Jiaju Zhuang
 */
public class BigIntegerNumberConverter implements Converter<BigInteger> {

    @Override
    public Class<BigInteger> supportJavaTypeKey() {
        return BigInteger.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public BigInteger convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return cellData.getNumberValue().toBigInteger();
    }

    @Override
    public WriteCellData<?> convertToExcelData(BigInteger value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return NumberUtils.formatToCellData(value, contentProperty);
    }
}
