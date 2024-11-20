package ai.chat2db.excel.test.temp.issue1663;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ai.chat2db.excel.EasyExcel;
import ai.chat2db.excel.ExcelWriter;
import ai.chat2db.excel.enums.WriteDirectionEnum;
import ai.chat2db.excel.test.demo.fill.FillData;
import ai.chat2db.excel.test.util.TestFileUtil;
import ai.chat2db.excel.write.metadata.WriteSheet;
import ai.chat2db.excel.write.metadata.fill.FillConfig;
import ai.chat2db.excel.write.metadata.fill.FillWrapper;

import org.junit.jupiter.api.Test;

public class FillTest {
    @Test
    public void TestFillNullPoint() {
        String templateFileName =
            TestFileUtil.getPath() + "temp/issue1663" + File.separator + "template.xlsx";

        String fileName = TestFileUtil.getPath() + "temp/issue1663" + File.separator + "issue1663.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.VERTICAL).build();
        excelWriter.fill(new FillWrapper("data1", data()), fillConfig, writeSheet);

        Map<String, Object> map = new HashMap<String, Object>();
        // Variable {date} does not exist in the template.xlsx, which should be ignored instead of reporting an error.
        map.put("date", "2019年10月9日13:28:28");
        excelWriter.fill(map, writeSheet);
        excelWriter.finish();
    }

    private List<ai.chat2db.excel.test.demo.fill.FillData> data() {
        List<ai.chat2db.excel.test.demo.fill.FillData> list
            = new ArrayList<ai.chat2db.excel.test.demo.fill.FillData>();
        for (int i = 0; i < 10; i++) {
            ai.chat2db.excel.test.demo.fill.FillData fillData = new FillData();
            list.add(fillData);
            fillData.setName("张三");
            fillData.setNumber(5.2);
        }
        return list;
    }
}
