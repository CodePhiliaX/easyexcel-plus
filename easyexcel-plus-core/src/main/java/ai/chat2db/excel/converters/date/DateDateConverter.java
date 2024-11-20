package ai.chat2db.excel.converters.date;

import java.util.Date;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.util.DateUtils;
import ai.chat2db.excel.util.WorkBookUtil;
import ai.chat2db.excel.metadata.GlobalConfiguration;
import ai.chat2db.excel.metadata.data.WriteCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

/**
 * Date and date converter
 *
 * @author Jiaju Zhuang
 */
public class DateDateConverter implements Converter<Date> {
    @Override
    public Class<Date> supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Date value, ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<?> cellData = new WriteCellData<>(value);
        String format = null;
        if (contentProperty != null && contentProperty.getDateTimeFormatProperty() != null) {
            format = contentProperty.getDateTimeFormatProperty().getFormat();
        }
        WorkBookUtil.fillDataFormat(cellData, format, DateUtils.defaultDateFormat);
        return cellData;
    }
}
