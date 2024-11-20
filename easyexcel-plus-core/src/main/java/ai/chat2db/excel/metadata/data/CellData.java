package ai.chat2db.excel.metadata.data;

import java.math.BigDecimal;

import ai.chat2db.excel.enums.CellDataTypeEnum;
import ai.chat2db.excel.metadata.AbstractCell;
import ai.chat2db.excel.util.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Excel internal cell data.
 *
 * <p>
 *
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class CellData<T> extends AbstractCell {
    /**
     * cell type
     */
    private CellDataTypeEnum type;
    /**
     * {@link CellDataTypeEnum#NUMBER}
     */
    private BigDecimal numberValue;
    /**
     * {@link CellDataTypeEnum#STRING} and{@link CellDataTypeEnum#ERROR}
     */
    private String stringValue;
    /**
     * {@link CellDataTypeEnum#BOOLEAN}
     */
    private Boolean booleanValue;

    /**
     * The resulting converted data.
     */
    private T data;

    /**
     * formula
     */
    private FormulaData formulaData;

    /**
     * Ensure that the object does not appear null
     */
    public void checkEmpty() {
        if (type == null) {
            type = CellDataTypeEnum.EMPTY;
        }
        switch (type) {
            case STRING:
            case DIRECT_STRING:
            case ERROR:
                if (StringUtils.isEmpty(stringValue)) {
                    type = CellDataTypeEnum.EMPTY;
                }
                return;
            case NUMBER:
                if (numberValue == null) {
                    type = CellDataTypeEnum.EMPTY;
                }
                return;
            case BOOLEAN:
                if (booleanValue == null) {
                    type = CellDataTypeEnum.EMPTY;
                }
                return;
            default:
        }
    }

}
