package ai.chat2db.excel.read.metadata.property;

import java.util.List;

import ai.chat2db.excel.metadata.ConfigurationHolder;
import ai.chat2db.excel.metadata.property.ExcelHeadProperty;

/**
 * Define the header attribute of excel
 *
 * @author jipengfei
 */
public class ExcelReadHeadProperty extends ExcelHeadProperty {

    public ExcelReadHeadProperty(ConfigurationHolder configurationHolder, Class headClazz, List<List<String>> head) {
        super(configurationHolder, headClazz, head);
    }
}
