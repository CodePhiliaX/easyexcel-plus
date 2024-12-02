package cn.idev.excel.fileconvertor.v07;

import cn.idev.excel.fileconvertor.BaseExcelConverter;
import cn.idev.excel.fileconvertor.Excel2PdfUtils;
import cn.idev.excel.fileconvertor.FileConverterContext;
import cn.idev.excel.util.StringUtils;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.borders.*;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsxConverter extends BaseExcelConverter {
    private static final String DEFAULT_FONT_PATH = System.getProperty("user.dir") + "/doc/font/SimHei.TTF";

    public XlsxConverter(FileConverterContext context) {
        super(context);
    }

    @Override
    public void addPicsToTable(Table table, Sheet sheet) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        if (drawing != null) {
            List<XSSFPicture> pictures = new ArrayList<>();
            for (XSSFShape shape : drawing.getShapes()) {
                if (shape instanceof XSSFPicture) {
                    pictures.add((XSSFPicture) shape);
                }
            }
            table.setNextRenderer(new XlsxImageTableRenderer(table, pictures, (XSSFSheet) sheet));
        }
    }

    @Override
    public com.itextpdf.layout.element.Cell convertCell(Cell cell, int rowspan, int colspan, float maxWidth, String fontPath) throws IOException {
        String value = Excel2PdfUtils.getValue(cell);
        com.itextpdf.layout.element.Cell pdfCell = createPdfCell(rowspan, colspan, cell, value, maxWidth, fontPath);
        return pdfCell;
    }

    public static void transformBorder(XSSFCell cell, com.itextpdf.layout.element.Cell pdfCell) {
        XSSFCellStyle cellStyle = cell.getCellStyle();
        BorderStyle borderBottom = cellStyle.getBorderBottom();
        pdfCell.setBorderBottom(getBorder(borderBottom, cellStyle.getBottomBorderXSSFColor()));

        BorderStyle borderLeft = cellStyle.getBorderLeft();
        pdfCell.setBorderLeft(getBorder(borderLeft, cellStyle.getLeftBorderXSSFColor()));

        BorderStyle borderRight = cellStyle.getBorderRight();
        pdfCell.setBorderRight(getBorder(borderRight, cellStyle.getRightBorderXSSFColor()));

        BorderStyle borderTop = cellStyle.getBorderTop();
        pdfCell.setBorderTop(getBorder(borderTop, cellStyle.getTopBorderXSSFColor()));
    }

    private com.itextpdf.layout.element.Cell createPdfCell(int rowspan, int colspan, Cell cell, String value, float maxWidth, String fontPath) throws IOException {
        com.itextpdf.layout.element.Cell pdfCell = new com.itextpdf.layout.element.Cell(rowspan, colspan)
                .setHeight(cell.getRow().getHeightInPoints() * 1.2f)
                .setPadding(0);
        Text text = new Text(value);
        setPdfCellFont((XSSFCell) cell, text, fontPath);
        Paragraph paragraph = new Paragraph(text).setPadding(0f).setMargin(0f);
        XSSFCellStyle cellStyle = ((XSSFCell) cell).getCellStyle();
        if (cellStyle.getWrapText()) {
            paragraph.setMaxWidth(maxWidth);
        }
        pdfCell.add(paragraph);
        setCellStyles((XSSFCell)cell, pdfCell);
        return pdfCell;
    }

    private void setCellStyles( XSSFCell cell,com.itextpdf.layout.element.Cell pdfCell) throws IOException {
        XSSFCellStyle cellStyle = cell.getCellStyle();
        pdfCell.setVerticalAlignment(getVerticalAlignment(cellStyle.getVerticalAlignment()))
                .setTextAlignment(getTextAlignment(cellStyle.getAlignment(), cell.getCellType()));

        // Set borders and background color
        setBorders(pdfCell, cellStyle);
        setBackgroundColor(pdfCell, cellStyle);
    }

    private void setBorders(com.itextpdf.layout.element.Cell pdfCell, XSSFCellStyle cellStyle) {
        pdfCell.setBorderBottom(getBorder(cellStyle.getBorderBottom(), cellStyle.getBottomBorderXSSFColor()));
        pdfCell.setBorderLeft(getBorder(cellStyle.getBorderLeft(), cellStyle.getLeftBorderXSSFColor()));
        pdfCell.setBorderRight(getBorder(cellStyle.getBorderRight(), cellStyle.getRightBorderXSSFColor()));
        pdfCell.setBorderTop(getBorder(cellStyle.getBorderTop(), cellStyle.getTopBorderXSSFColor()));
    }

    private void setBackgroundColor(com.itextpdf.layout.element.Cell pdfCell, XSSFCellStyle cellStyle) {
        XSSFColor fillColor = cellStyle.getFillForegroundXSSFColor();
        if (fillColor != null) {
            byte[] rgb = fillColor.getRGB();
            if (rgb != null) {
                pdfCell.setBackgroundColor(new DeviceRgb(Byte.toUnsignedInt(rgb[0]), Byte.toUnsignedInt(rgb[1]), Byte.toUnsignedInt(rgb[2])));
            }
        }
    }

    private void setPdfCellFont(XSSFCell cell, Text text,String fontPath) throws IOException {
        XSSFCellStyle cellStyle = cell.getCellStyle();
        short fontHeight = cellStyle.getFont().getFontHeightInPoints();
        cellStyle.getFont().getFontName();
        text.setFont(StringUtils.isEmpty(fontPath) ? PdfFontFactory.createFont() : PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H));
        text.setFontSize(fontHeight);
        setFontColor(cellStyle.getFont(), text);
        setFontStyles(cell.getCellStyle().getFont(), text);
    }

    private void setFontColor(XSSFFont font, Text text) {
        XSSFColor xssfColor = font.getXSSFColor();
        if (xssfColor != null && xssfColor.getIndex() != 64) {
            byte[] rgb = xssfColor.getRGB();
            if (rgb != null) {
                text.setFontColor(new DeviceRgb(Byte.toUnsignedInt(rgb[0]), Byte.toUnsignedInt(rgb[1]), Byte.toUnsignedInt(rgb[2])));
            }
        }
    }

    private void setFontStyles(XSSFFont font, Text text) {
        if (font.getBold()) text.setBold();
        if (font.getItalic()) text.setItalic();
        if (font.getUnderline() == 1) text.setUnderline(0.5f, -1f);
    }

    public static com.itextpdf.layout.borders.Border getBorder(BorderStyle borderStyle, XSSFColor xSSFColor) {
        DeviceRgb defaultColor = new DeviceRgb(0, 0, 0); // Default to black
        if (xSSFColor != null) {
            byte[] rgb = xSSFColor.getRGB();
            if (rgb != null) {
                defaultColor = new DeviceRgb(Byte.toUnsignedInt(rgb[0]), Byte.toUnsignedInt(rgb[1]), Byte.toUnsignedInt(rgb[2]));
            }
        }

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