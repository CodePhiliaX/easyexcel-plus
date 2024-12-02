package cn.idev.excel.fileconvertor;

import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.util.List;

public abstract class BaseExcelConverter implements ExcelConverter {

    private final FileConverterContext context;

    public BaseExcelConverter(FileConverterContext context) {
        this.context = context;
    }

    @Override
    public void convertToPdf() {
        try {
            for (int sheetIndex : context.getSheets()) {
                processSheet(sheetIndex);
            }
            context.getDocument().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void processSheet(int sheetIndex) throws IOException {
        Sheet sheet = context.getWorkbook().getSheetAt(sheetIndex);
        if (sheet == null || sheet.getRow(0) == null) {
            return;
        }
        float[] columnWidths = getColumnWidths(sheet);
        Table table = new Table(columnWidths);

        addRowsToTable(table, sheet, columnWidths, context.getFountPath());
//        addPicsToTable(table, sheet);

        context.getDocument().add(table);
    }

    protected abstract void addPicsToTable(Table table, Sheet sheet);

    private void addRowsToTable(Table table, Sheet sheet, float[] columnWidths, String fontPath) throws IOException {
        int lastRowNum = sheet.getLastRowNum() + 1;
        int lastCellNum = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < lastRowNum; i++) {
            Row row = sheet.getRow(i);
            addRowToTable(table, row, lastCellNum, columnWidths, fontPath);
        }
    }

    private void addRowToTable(Table table, Row row, int lastCellNum, float[] columnWidths, String fontPath) throws IOException {
        if (row == null) {
            addEmptyCells(table, lastCellNum); // 0 for empty row
            return;
        }

        for (int j = 0; j < lastCellNum; j++) {
            Cell cell = row.getCell(j);
            if (cell != null && !isCellProcessed(cell)) {
//                addCellToTable(table, cell, columnWidths, fontPath);
                CellRangeAddress cellRange = getCellRangeAddress(cell);
                int rowspan = (cellRange != null) ? (cellRange.getLastRow() - cellRange.getFirstRow() + 1) : 1;
                int colspan = (cellRange != null) ? (cellRange.getLastColumn() - cellRange.getFirstColumn() + 1) : 1;
                if ((cellRange != null)) {
                    j = cellRange.getLastColumn();
                }
                float maxWidth = (cellRange != null) ? calculateMaxWidth(columnWidths, cellRange) : columnWidths[j];

                com.itextpdf.layout.element.Cell pdfCell = convertCell(cell, rowspan, colspan, maxWidth, fontPath);
                table.addCell(pdfCell);
            } else if (cell == null) {
                addEmptyCell(table);
            }
        }
    }

    private float calculateMaxWidth(float[] columnWidths, CellRangeAddress cellRange) {
        float maxWidth = 0;
        for (int k = cellRange.getFirstColumn(); k < cellRange.getLastColumn(); k++) {
            maxWidth += columnWidths[k];
        }
        return maxWidth;
    }

    private void addEmptyCell(Table table) {
        com.itextpdf.layout.element.Cell pdfCell = new com.itextpdf.layout.element.Cell();
        pdfCell.setBorder(com.itextpdf.layout.borders.Border.NO_BORDER);
        table.addCell(pdfCell);
    }

    private void addEmptyCells(Table table, int numberOfCells) {
        for (int j = 0; j < numberOfCells; j++) {
            addEmptyCell(table);
        }
    }

    protected abstract com.itextpdf.layout.element.Cell convertCell(Cell cell, int rowspan, int colspan, float maxWidth, String fontPath) throws IOException;

    public static com.itextpdf.layout.property.VerticalAlignment getVerticalAlignment(VerticalAlignment verticalAlignment) {
        switch (verticalAlignment) {
            case TOP:
                return com.itextpdf.layout.property.VerticalAlignment.TOP;
            case BOTTOM:
                return com.itextpdf.layout.property.VerticalAlignment.BOTTOM;
            case JUSTIFY:
            case CENTER:
                return com.itextpdf.layout.property.VerticalAlignment.MIDDLE;
        }
        return com.itextpdf.layout.property.VerticalAlignment.MIDDLE;
    }

    public static TextAlignment getTextAlignment(org.apache.poi.ss.usermodel.HorizontalAlignment alignment, CellType cellType) {
        switch (alignment) {
            case LEFT:
                return TextAlignment.LEFT;
            case RIGHT:
                return TextAlignment.RIGHT;
            case CENTER:
                return TextAlignment.CENTER;
            case JUSTIFY:
                return TextAlignment.JUSTIFIED;
            case GENERAL:
                if (cellType == CellType.NUMERIC) {
                    return TextAlignment.RIGHT;
                } else if (cellType == CellType.BOOLEAN) {
                    return TextAlignment.CENTER;
                }
        }
        return TextAlignment.LEFT;
    }

    private float[] getColumnWidths(Sheet sheet) {
        short lastCellNum = sheet.getRow(0).getLastCellNum();
        float[] widths = new float[lastCellNum];
        for (int i = 0; i < lastCellNum; i++) {
            widths[i] = sheet.getColumnWidthInPixels(i);
        }
        return widths;
    }

    private boolean isCellProcessed(Cell cell) {
        List<CellRangeAddress> mergedRegions = cell.getSheet().getMergedRegions();
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();

        for (CellRangeAddress cellAddresses : mergedRegions) {
            if (cellAddresses.getFirstRow() <= rowIndex && cellAddresses.getLastRow() >= rowIndex
                    && cellAddresses.getFirstColumn() <= columnIndex && cellAddresses.getLastColumn() >= columnIndex) {
                return !(cellAddresses.getFirstRow() == rowIndex && cellAddresses.getFirstColumn() == columnIndex);
            }
        }
        return false;
    }

    private CellRangeAddress getCellRangeAddress(Cell cell) {
        List<CellRangeAddress> mergedRegions = cell.getSheet().getMergedRegions();
        int rowIndex = cell.getRowIndex();
        int columnIndex = cell.getColumnIndex();

        for (CellRangeAddress cellAddresses : mergedRegions) {
            if (cellAddresses.getFirstRow() == rowIndex && cellAddresses.getFirstColumn() == columnIndex) {
                return cellAddresses;
            }
        }
        return null;
    }
}