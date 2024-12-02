package cn.idev.excel.fileconvertor.v07;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TableRenderer;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

/**
 * Renders images from an Excel sheet onto a PDF table.
 */
public class XlsxImageTableRenderer extends TableRenderer {
    private List<XSSFPicture> xssfPictures;
    private XSSFSheet sheet;

    public XlsxImageTableRenderer(Table modelElement, List<XSSFPicture> xssfPictures, XSSFSheet sheet) {
        super(modelElement);
        this.xssfPictures = xssfPictures;
        this.sheet = sheet;
    }

    @Override
    public void drawChildren(DrawContext drawContext) {
        super.drawChildren(drawContext);
        renderExcelImages(drawContext);
    }

    private void renderExcelImages(DrawContext drawContext) {
        for (XSSFPicture picture : xssfPictures) {
            XSSFClientAnchor clientAnchor = picture.getClientAnchor();
            Rectangle imageRect = calculateImageRectangle(clientAnchor);
            ImageData imageData = ImageDataFactory.create(picture.getPictureData().getData());
            drawContext.getCanvas().addImage(imageData,
                    imageRect.getWidth(),
                    0,
                    0,
                    imageRect.getHeight(),
                    imageRect.getLeft(),
                    imageRect.getTop());
        }
    }

    private Rectangle calculateImageRectangle(XSSFClientAnchor clientAnchor) {
        CellRenderer cellRenderer1 = rows.get(clientAnchor.getRow1())[clientAnchor.getCol1()];
        CellRenderer cellRenderer2 = rows.get(clientAnchor.getRow2())[clientAnchor.getCol2()];
        Rectangle rect1 = cellRenderer1.getOccupiedAreaBBox();
        Rectangle rect2 = cellRenderer2.getOccupiedAreaBBox();

        float widthRate = (super.getOccupiedAreaBBox().getWidth() + rect2.getWidth()) / getExcelWidth(sheet);
        float heightRate = (super.getOccupiedAreaBBox().getHeight() - rect2.getHeight()) / getExcelHeight(sheet);

        float width = calculateImageWidth(clientAnchor, widthRate);
        float height = calculateImageHeight(clientAnchor, heightRate);

        float x = rect1.getLeft() + clientAnchor.getDx1() * widthRate;
        float y = rect1.getTop() - height - clientAnchor.getDy1() * heightRate;

        return new Rectangle(x, y, width, height);
    }

    private float calculateImageWidth(XSSFClientAnchor clientAnchor, float widthRate) {
        float width = 0f;
        for (int j = clientAnchor.getCol1(); j < clientAnchor.getCol2(); j++) {
            width += sheet.getColumnWidth(j);
        }
        return Math.abs(width - clientAnchor.getDx1() + clientAnchor.getDx2()) * widthRate;
    }

    private float calculateImageHeight(XSSFClientAnchor clientAnchor, float heightRate) {
        float height = 0f;
        for (int j = clientAnchor.getRow1(); j < clientAnchor.getRow2(); j++) {
            height += sheet.getRow(j).getHeight();
        }
        return Math.abs(height - clientAnchor.getDy1() + clientAnchor.getDy2()) * heightRate;
    }

    private float getExcelHeight(XSSFSheet sheet) {
        float totalHeight = 0;
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            totalHeight += sheet.getRow(i).getHeight();
        }
        return totalHeight;
    }

    private float getExcelWidth(XSSFSheet sheet) {
        return (short)sheet.getRow(0).getLastCellNum() * sheet.getColumnWidth(0);
    }

    @Override
    public IRenderer getNextRenderer() {
        return new XlsxImageTableRenderer((Table) modelElement, xssfPictures, sheet);
    }
}
