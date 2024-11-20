package ai.chat2db.excel.test.temp.simple;

import java.io.FileInputStream;
import java.io.IOException;

import ai.chat2db.excel.EasyExcel;
import ai.chat2db.excel.ExcelReader;
import ai.chat2db.excel.read.metadata.ReadSheet;
import ai.chat2db.excel.test.temp.LockData;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试poi
 *
 * @author Jiaju Zhuang
 **/

public class RepeatTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepeatTest.class);

    @Test
    public void hh() throws IOException {
        ExcelReader reader =
            EasyExcel.read(new FileInputStream("D:\\test\\hg2.xls"), LockData.class, new RepeatListener())
                .headRowNumber(0).build();
        ReadSheet r1 = EasyExcel.readSheet(0).build();
        ReadSheet r2 = EasyExcel.readSheet(2).build();
        reader.read(r1);
        reader.read(r2);
        reader.finish();
    }

    @Test
    public void hh2() throws IOException {
        ExcelReader reader =
            EasyExcel.read(new FileInputStream("D:\\test\\sheet.xls"), LockData.class, new RepeatListener())
                .headRowNumber(0).build();
        ReadSheet r2 = EasyExcel.readSheet(1).build();
        reader.read(r2);
        reader.finish();
    }

    @Test
    public void hh1() throws IOException {
        ExcelReader reader =
            EasyExcel.read(new FileInputStream("D:\\test\\hg2.xls"), LockData.class, new RepeatListener())
                .headRowNumber(0).build();
        ReadSheet r2 = EasyExcel.readSheet(0).build();
        reader.read(r2);
        reader.finish();
    }
}
