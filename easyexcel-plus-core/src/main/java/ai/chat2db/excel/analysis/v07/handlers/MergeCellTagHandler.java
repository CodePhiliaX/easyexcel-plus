package ai.chat2db.excel.analysis.v07.handlers;

import ai.chat2db.excel.enums.CellExtraTypeEnum;
import ai.chat2db.excel.metadata.CellExtra;
import ai.chat2db.excel.util.StringUtils;
import org.xml.sax.Attributes;

import ai.chat2db.excel.constant.ExcelXmlConstants;
import ai.chat2db.excel.context.xlsx.XlsxReadContext;

/**
 * Cell Handler
 *
 * @author Jiaju Zhuang
 */
public class MergeCellTagHandler extends AbstractXlsxTagHandler {

    @Override
    public boolean support(XlsxReadContext xlsxReadContext) {
        return xlsxReadContext.readWorkbookHolder().getExtraReadSet().contains(CellExtraTypeEnum.MERGE);
    }

    @Override
    public void startElement(XlsxReadContext xlsxReadContext, String name, Attributes attributes) {
        String ref = attributes.getValue(ExcelXmlConstants.ATTRIBUTE_REF);
        if (StringUtils.isEmpty(ref)) {
            return;
        }
        CellExtra cellExtra = new CellExtra(CellExtraTypeEnum.MERGE, null, ref);
        xlsxReadContext.readSheetHolder().setCellExtra(cellExtra);
        xlsxReadContext.analysisEventProcessor().extra(xlsxReadContext);
    }

}
