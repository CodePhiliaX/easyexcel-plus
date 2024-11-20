package ai.chat2db.excel.converters.localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.util.DateUtils;
import ai.chat2db.excel.util.WorkBookUtil;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * LocalDate and date converter
 *
 * @author Jiaju Zhuang
 */
public class LocalDateDateConverter implements Converter<LocalDate> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(LocalDate value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws Exception {
        LocalDateTime localDateTime = value == null ? null : value.atTime(0, 0);
        WriteCellData<?> cellData = new WriteCellData<>(localDateTime);
        String format = null;
        if (contentProperty != null && contentProperty.getDateTimeFormatProperty() != null) {
            format = contentProperty.getDateTimeFormatProperty().getFormat();
        }
        WorkBookUtil.fillDataFormat(cellData, format, DateUtils.defaultLocalDateFormat);
        return cellData;
    }
}
