package ai.chat2db.excel.test.temp.read;

import java.io.File;

import ai.chat2db.excel.EasyExcel;
import ai.chat2db.excel.cache.Ehcache;
import ai.chat2db.excel.test.util.TestFileUtil;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 临时测试
 *
 * @author Jiaju Zhuang
 **/

public class HeadReadTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeadReadTest.class);

    @Test
    public void test() throws Exception {
        File file = TestFileUtil.readUserHomeFile("test/t2.xlsx");
        EasyExcel.read(file, HeadReadData.class, new HeadListener()).ignoreEmptyRow(false).sheet(0).doRead();

    }

    @Test
    public void testCache() throws Exception {
        File file = new File("D:\\test\\headt1.xls");
        EasyExcel.read(file, HeadReadData.class, new HDListener()).readCache(new Ehcache(20)).sheet(0).doRead();

        LOGGER.info("------------------");
        EasyExcel.read(file, HeadReadData.class, new HDListener()).readCache(new Ehcache(20)).sheet(0).doRead();
        LOGGER.info("------------------");
        EasyExcel.read(file, HeadReadData.class, new HDListener()).readCache(new Ehcache(20)).sheet(0).doRead();
    }

}
