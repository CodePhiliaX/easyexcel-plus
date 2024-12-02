package cn.idev.excel;

import cn.idev.excel.fileconvertor.ExcelConverter;
import cn.idev.excel.fileconvertor.FileConverterContext;

import java.io.File;

/**
 * This is actually {@link FastExcelFactory}, and short names look better.
 *
 * @author jipengfei
 */
public class FastExcel extends FastExcelFactory {

    /**
     * Convert excel to pdf
     *
     * @param excelFile excel file
     * @param pdfFile   pdf file
     * @param fontPath  font path for pdf can be null
     * @param sheets    sheet index to convert, if null convert all sheets
     */
    public static void convertToPdf(File excelFile, File pdfFile, String fontPath, int[] sheets) {
        FileConverterContext context = new FileConverterContext(excelFile, pdfFile, fontPath, sheets);
        ExcelConverter excelConverter = context.getExcelConverter();
        excelConverter.convertToPdf();
    }

}
