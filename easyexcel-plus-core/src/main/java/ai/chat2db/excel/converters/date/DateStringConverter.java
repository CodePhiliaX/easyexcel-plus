package ai.chat2db.excel.converters.date;

import java.text.ParseException;
import java.util.Date;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.util.DateUtils;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Date and string converter
 *
 * @author Jiaju Zhuang
 */
public class DateStringConverter implements Converter<Date> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Date convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws ParseException {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return DateUtils.parseDate(cellData.getStringValue(), null);
        } else {
            return DateUtils.parseDate(cellData.getStringValue(),
                contentProperty.getDateTimeFormatProperty().getFormat());
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(Date value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return new WriteCellData<>(DateUtils.format(value, null));
        } else {
            return new WriteCellData<>(DateUtils.format(value, contentProperty.getDateTimeFormatProperty().getFormat()));
        }
    }
}
