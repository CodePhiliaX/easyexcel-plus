package ai.chat2db.excel.event;

import java.util.Map;

import ai.chat2db.excel.context.AnalysisContext;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.read.listener.ReadListener;
import ai.chat2db.excel.util.ConverterUtils;

/**
 * Receives the return of each piece of data parsed
 *
 * @author jipengfei
 */
public abstract class AnalysisEventListener<T> implements ReadListener<T> {

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        invokeHeadMap(ConverterUtils.convertToStringMap(headMap, context), context);
    }

    /**
     * Returns the header as a map.Override the current method to receive header data.
     *
     * @param headMap
     * @param context
     */
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {}

}
