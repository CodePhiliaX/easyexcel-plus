package ai.chat2db.excel.converters.localdate;

import java.math.BigDecimal;
import java.time.LocalDate;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.DateUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

import org.apache.poi.ss.usermodel.DateUtil;

/**
 * LocalDate and number converter
 *
 * @author Jiaju Zhuang
 */
public class LocalDateNumberConverter implements Converter<LocalDate> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public LocalDate convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return DateUtils.getLocalDate(cellData.getNumberValue().doubleValue(),
                globalConfiguration.getUse1904windowing());
        } else {
            return DateUtils.getLocalDate(cellData.getNumberValue().doubleValue(),
                contentProperty.getDateTimeFormatProperty().getUse1904windowing());
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(LocalDate value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return new WriteCellData<>(
                BigDecimal.valueOf(DateUtil.getExcelDate(value, globalConfiguration.getUse1904windowing())));
        } else {
            return new WriteCellData<>(BigDecimal.valueOf(
                DateUtil.getExcelDate(value, contentProperty.getDateTimeFormatProperty().getUse1904windowing())));
        }
    }
}
