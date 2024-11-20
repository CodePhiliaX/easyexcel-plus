package ai.chat2db.excel.converters.localdate;

import java.text.ParseException;
import java.time.LocalDate;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.DateUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * LocalDate and string converter
 *
 * @author Jiaju Zhuang
 */
public class LocalDateStringConverter implements Converter<LocalDate> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDate convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws ParseException {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return DateUtils.parseLocalDate(cellData.getStringValue(), null, globalConfiguration.getLocale());
        } else {
            return DateUtils.parseLocalDate(cellData.getStringValue(),
                contentProperty.getDateTimeFormatProperty().getFormat(), globalConfiguration.getLocale());
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(LocalDate value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return new WriteCellData<>(DateUtils.format(value, null, globalConfiguration.getLocale()));
        } else {
            return new WriteCellData<>(
                DateUtils.format(value, contentProperty.getDateTimeFormatProperty().getFormat(),
                    globalConfiguration.getLocale()));
        }
    }
}
