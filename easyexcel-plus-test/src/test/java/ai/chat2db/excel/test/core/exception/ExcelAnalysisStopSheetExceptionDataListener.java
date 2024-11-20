package ai.chat2db.excel.test.core.exception;

import java.util.List;
import java.util.Map;

import ai.chat2db.excel.event.AnalysisEventListener;
import ai.chat2db.excel.util.ListUtils;
import ai.chat2db.excel.util.MapUtils;
import ai.chat2db.excel.context.AnalysisContext;
import ai.chat2db.excel.exception.ExcelAnalysisStopSheetException;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;

/**
 * @author Jiaju Zhuang
 */
@Getter
@Slf4j
public class ExcelAnalysisStopSheetExceptionDataListener extends AnalysisEventListener<ExceptionData> {

    private Map<Integer, List<String>> dataMap = MapUtils.newHashMap();


    @Override
    public void invoke(ExceptionData data, AnalysisContext context) {
        List<String> sheetDataList = dataMap.computeIfAbsent(context.readSheetHolder().getSheetNo(),
            key -> ListUtils.newArrayList());
        sheetDataList.add(data.getName());
        if (sheetDataList.size() >= 5) {
            throw new ExcelAnalysisStopSheetException();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<String> sheetDataList = dataMap.get(context.readSheetHolder().getSheetNo());
        Assertions.assertNotNull(sheetDataList);
        Assertions.assertEquals(5, sheetDataList.size());
    }
}
