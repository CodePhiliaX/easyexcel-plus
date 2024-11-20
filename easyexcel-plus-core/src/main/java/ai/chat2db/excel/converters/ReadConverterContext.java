package ai.chat2db.excel.converters;

import ai.chat2db.excel.context.AnalysisContext;
import ai.chat2db.excel.metadata.data.ReadCellData;
import ai.chat2db.excel.metadata.property.ExcelContentProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * read converter context
 *
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ReadConverterContext<T> {
    /**
     * Excel cell data.NotNull.
     */
    private ReadCellData<T> readCellData;
    /**
     * Content property.Nullable.
     */
    private ExcelContentProperty contentProperty;
    /**
     * context.NotNull.
     */
    private AnalysisContext analysisContext;
}
