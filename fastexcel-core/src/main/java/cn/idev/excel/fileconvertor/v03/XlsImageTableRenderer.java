package cn.idev.excel.fileconvertor.v03;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.renderer.TableRenderer;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.List;

/**
 * Renders images from an Excel sheet onto a PDF table.
 */
public class XlsImageTableRenderer extends TableRenderer {
    private List<HSSFPicture> hSSFPictures;
    private HSSFSheet sheet;

    public XlsImageTableRenderer(Table modelElement, List<HSSFPicture> hSSFPictures, HSSFSheet sheet) {
        super(modelElement);
        this.hSSFPictures = hSSFPictures;
        this.sheet = sheet;
    }

    @Override
    public void drawChildren(DrawContext drawContext) {
        super.drawChildren(drawContext);
        drawExcelImages(drawContext);
    }

    private void drawExcelImages(DrawContext drawContext) {
        for (HSSFPicture picture : hSSFPictures) {
            HSSFClientAnchor clientAnchor = picture.getClientAnchor();
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

    private Rectangle calculateImageRectangle(HSSFClientAnchor clientAnchor) {
        CellRenderer cellRenderer1 = rows.get(clientAnchor.getRow1())[clientAnchor.getCol1()];
        Rectangle rect1 = cellRenderer1.getOccupiedAreaBBox();

        CellRenderer cellRenderer2 = rows.get(clientAnchor.getRow2())[clientAnchor.getCol2()];
        Rectangle rect2 = cellRenderer2.getOccupiedAreaBBox();

        float widthRate = calculateWidthRate(rect2);
        float heightRate = calculateHeightRate(rect2);

        float width = calculateImageWidth(clientAnchor, widthRate);
        float height = calculateImageHeight(clientAnchor, heightRate);

        float x = rect1.getLeft() + clientAnchor.getDx1() * widthRate;
        float y = rect1.getTop() - height - clientAnchor.getDy1() * heightRate;

        return new Rectangle(x, y, width, height);
    }

    private float calculateWidthRate(Rectangle rect2) {
        return (super.getOccupiedAreaBBox().getWidth() + rect2.getWidth()) / getExcelWidth(sheet);
    }

    private float calculateHeightRate(Rectangle rect2) {
        return (super.getOccupiedAreaBBox().getHeight() - rect2.getHeight()) / getExcelHeight(sheet);
    }

    private float calculateImageWidth(HSSFClientAnchor clientAnchor, float widthRate) {
        float width = 0f;
        for (int j = clientAnchor.getCol1(); j < clientAnchor.getCol2(); j++) {
            width += sheet.getColumnWidth(j);
        }
        return Math.abs(width - clientAnchor.getDx1() + clientAnchor.getDx2()) * widthRate;
    }

    private float calculateImageHeight(HSSFClientAnchor clientAnchor, float heightRate) {
        float height = 0f;
        for (int j = clientAnchor.getRow1(); j < clientAnchor.getRow2(); j++) {
            height += sheet.getRow(j).getHeight();
        }
        return Math.abs(height - clientAnchor.getDy1() + clientAnchor.getDy2()) * heightRate;
    }

    private float getExcelHeight(HSSFSheet sheet) {
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        float result = 0;
        for (int i = 0; i < physicalNumberOfRows; i++) {
            result += sheet.getRow(i).getHeight();
        }
        return result;
    }

    private float getExcelWidth(HSSFSheet sheet) {
        short lastCellNum = sheet.getRow(0).getLastCellNum();
        float result = 0;
        for (int i = 0; i < lastCellNum; i++) {
            result += sheet.getColumnWidth(i);
        }
        return result;
    }

    @Override
    public IRenderer getNextRenderer() {
        return new XlsImageTableRenderer((Table) modelElement, hSSFPictures, sheet);
    }
}
