package cn.idev.excel.fileconvertor.v03;

import cn.idev.excel.fileconvertor.BaseExcelConverter;
import cn.idev.excel.fileconvertor.Excel2PdfUtils;
import cn.idev.excel.fileconvertor.FileConverterContext;
import cn.idev.excel.util.StringUtils;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsConverter extends BaseExcelConverter {
    public XlsConverter(FileConverterContext context) {
        super(context);
    }

    @Override
    public void addPicsToTable(Table table, Sheet sheet) {
        HSSFPatriarch drawingPatriarch = ((HSSFSheet) sheet).getDrawingPatriarch();
        if (drawingPatriarch != null) {
            List<HSSFPicture> pictures = new ArrayList<>();
            for (HSSFShape shape : drawingPatriarch.getChildren()) {
                if (shape instanceof HSSFPicture) {
                    pictures.add((HSSFPicture) shape);
                }
            }
            table.setNextRenderer(new XlsImageTableRenderer(table, pictures, (HSSFSheet) sheet));
        }
    }

    @Override
    public Cell convertCell(org.apache.poi.ss.usermodel.Cell cell, int rowspan, int colspan, float maxWidth, String fontPath) throws IOException {
        String value = Excel2PdfUtils.getValue(cell);
        Cell pdfCell = createPdfCell(rowspan, colspan, cell, value, maxWidth, fontPath);

        return pdfCell;
    }

    private Cell createPdfCell(int rowspan, int colspan, org.apache.poi.ss.usermodel.Cell cell, String value, float maxWidth, String fontPath) throws IOException {
        float cellHeight = cell.getRow().getHeightInPoints() * 1.2f;
        Cell pdfCell = new Cell(rowspan, colspan)
                .setHeight(cellHeight)
                .setPadding(0);
        Text text = new Text(value);
        setPdfCellFont((HSSFCell) cell, text, fontPath);

        Paragraph paragraph = new Paragraph(text).setPadding(0f).setMargin(0f);
        HSSFCellStyle cellStyle = ((HSSFCell) cell).getCellStyle();
        if (cellStyle.getWrapText()) {
            paragraph.setMaxWidth(maxWidth);
        }

        pdfCell.add(paragraph);

        setCellStyles(cell, pdfCell);
        return pdfCell;
    }

    private void setCellStyles(org.apache.poi.ss.usermodel.Cell cell, Cell pdfCell) throws IOException {
        HSSFCellStyle cellStyle = ((HSSFCell) cell).getCellStyle();
        // Layout
        pdfCell.setVerticalAlignment(getVerticalAlignment(cellStyle.getVerticalAlignment()));
        pdfCell.setTextAlignment(getTextAlignment(cellStyle.getAlignment(), cell.getCellType()));

        // Set borders
        transformBorders((HSSFCell) cell, pdfCell);

        // Set background color
        setBackgroundColor(cellStyle, pdfCell, cell);
    }

    private void setBackgroundColor(HSSFCellStyle cellStyle, Cell pdfCell, org.apache.poi.ss.usermodel.Cell cell) {
        short colorIndex = cellStyle.getFillForegroundColor();
        HSSFWorkbook workbook = (HSSFWorkbook) cell.getSheet().getWorkbook();
        HSSFColor color = workbook.getCustomPalette().getColor(colorIndex);
        if (color != null && color.getIndex() != 64) {
            short[] triplet = color.getTriplet();
            int r = Math.min(triplet[0] + 32, 255);
            int g = Math.min(triplet[1] + 90, 255);
            int b = Math.min(triplet[2] + 60, 255);
            pdfCell.setBackgroundColor(new DeviceRgb(r, g, b));
        }
    }

    public static void transformBorders(HSSFCell cell, Cell pdfCell) {
        HSSFCellStyle cellStyle = cell.getCellStyle();
        pdfCell.setBorderBottom(getBorder(cellStyle.getBorderBottom(), cellStyle.getBottomBorderColor(), cell));
        pdfCell.setBorderLeft(getBorder(cellStyle.getBorderLeft(), cellStyle.getLeftBorderColor(), cell));
        pdfCell.setBorderRight(getBorder(cellStyle.getBorderRight(), cellStyle.getRightBorderColor(), cell));
        pdfCell.setBorderTop(getBorder(cellStyle.getBorderTop(), cellStyle.getTopBorderColor(), cell));
    }

    private void setPdfCellFont(HSSFCell cell, Text text, String fontPath) throws IOException {
        HSSFCellStyle cellStyle = cell.getCellStyle();
        HSSFFont font = cellStyle.getFont(cell.getSheet().getWorkbook());
        text.setFont(StringUtils.isEmpty(fontPath) ? PdfFontFactory.createFont() : PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H));
        text.setFontSize(font.getFontHeightInPoints());

        // Set font color
        HSSFColor hssfColor = font.getHSSFColor(cell.getSheet().getWorkbook());
        if (hssfColor != null && hssfColor.getIndex() != 64) {
            short[] triplet = hssfColor.getTriplet();
            text.setFontColor(new DeviceRgb(triplet[0], triplet[1], triplet[2]));
        }

        // Set font styles
        if (font.getBold()) text.setBold();
        if (font.getItalic()) text.setItalic();
        if (font.getUnderline() == 1) text.setUnderline(0.5f, -1f);
    }

    public static Border getBorder(BorderStyle borderStyle, short colorIndex, HSSFCell cell) {
        HSSFPalette customPalette = cell.getSheet().getWorkbook().getCustomPalette();
        HSSFColor color = customPalette.getColor(colorIndex);
        Color defaultColor = (color != null && color.getIndex() != 64)
                ? new DeviceRgb(color.getTriplet()[0], color.getTriplet()[1], color.getTriplet()[2])
                : ColorConstants.BLACK;

        switch (borderStyle) {
            case THIN:
                return new SolidBorder(defaultColor, 0.3f);
            case MEDIUM:
                return new SolidBorder(defaultColor, 0.5f);
            case DASHED:
                return new DashedBorder(defaultColor, 0.3f);
            case DOTTED:
                return new DottedBorder(defaultColor, 0.3f);
            case THICK:
                return new SolidBorder(defaultColor, 1f);
            case DOUBLE:
                return new DoubleBorder(defaultColor, 0.3f);
            case MEDIUM_DASHED:
                return new DashedBorder(defaultColor, 0.5f);
            default:
                return Border.NO_BORDER;
        }
    }
}
