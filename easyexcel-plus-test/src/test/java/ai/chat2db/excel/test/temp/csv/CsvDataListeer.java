package ai.chat2db.excel.test.temp.csv;

import ai.chat2db.excel.event.AnalysisEventListener;
import ai.chat2db.excel.context.AnalysisContext;
import com.alibaba.fastjson2.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvDataListeer extends AnalysisEventListener<CsvData> {
    @Override
    public void invoke(CsvData data, AnalysisContext context) {
        log.info("data:{}", JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
