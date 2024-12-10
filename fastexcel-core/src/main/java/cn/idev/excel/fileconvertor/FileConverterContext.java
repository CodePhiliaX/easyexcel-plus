package cn.idev.excel.fileconvertor;

import cn.idev.excel.read.metadata.ReadWorkbook;
import cn.idev.excel.support.ExcelTypeEnum;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Getter
@Setter
public class FileConverterContext {

    private File inputFile;
    private File outputFile;
    private String fontPath;
    private Workbook workbook;
    private Document document;
    private int[] sheets;
    private ExcelTypeEnum excelTypeEnum;

    public FileConverterContext(File inputFile, File outputFile, String fontPath, int[] sheets) {
        try {
            this.inputFile = inputFile;
            this.outputFile = outputFile;
            this.fontPath = fontPath;
            ReadWorkbook readWorkbook = new ReadWorkbook();
            readWorkbook.setFile(inputFile);
            excelTypeEnum = ExcelTypeEnum.valueOf(readWorkbook);
            if (excelTypeEnum == ExcelTypeEnum.XLSX) {
                this.workbook = new XSSFWorkbook(inputFile);
            } else if (excelTypeEnum == ExcelTypeEnum.XLS) {
                this.workbook = new HSSFWorkbook(new FileInputStream(inputFile));
            } else {
                throw new IllegalArgumentException("Not supported excel type");
            }
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputFile));
            this.document = new Document(pdfDocument, PageSize.A4.rotate());
            if (sheets == null) {
                this.sheets = new int[workbook.getNumberOfSheets()];
                for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                    this.sheets[i] = i;
                }
            } else {
                this.sheets = sheets;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public ExcelConverter getExcelConverter() {
        if (excelTypeEnum == ExcelTypeEnum.XLSX) {
            return new cn.idev.excel.fileconvertor.v07.XlsxConverter(this);
        } else if (excelTypeEnum == ExcelTypeEnum.XLS) {
            return new cn.idev.excel.fileconvertor.v03.XlsConverter(this);
        }
        throw new IllegalArgumentException("Not supported excel type");
    }


}
