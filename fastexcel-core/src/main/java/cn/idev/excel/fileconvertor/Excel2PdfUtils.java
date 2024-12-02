package cn.idev.excel.fileconvertor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;

import java.text.SimpleDateFormat;

public class Excel2PdfUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"; // 日期格式
    private static final String TRUE_STRING = "TRUE";
    private static final String FALSE_STRING = "FALSE";
    private static final String EMPTY_STRING = "";

    // 使用单例模式确保 SimpleDateFormat 只被创建一次
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = ThreadLocal.withInitial(() ->
            new SimpleDateFormat(DATE_FORMAT, LocaleUtil.getUserLocale())
    );

    public static String getValue(Cell cell) {
        if (cell == null) {
            return EMPTY_STRING;
        }

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case BOOLEAN:
                return cell.getBooleanCellValue() ? TRUE_STRING : FALSE_STRING;
            case NUMERIC:
                return getNumericCellValue(cell);
            case STRING:
                return cell.getStringCellValue();
            default:
                return EMPTY_STRING;
        }
    }

    private static String getNumericCellValue(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            return DATE_FORMATTER.get().format(cell.getDateCellValue());
        }
        return String.valueOf(cell.getNumericCellValue());
    }
}
