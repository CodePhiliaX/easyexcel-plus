package ai.chat2db.excel.metadata;

import ai.chat2db.excel.enums.HolderEnum;

/**
 *
 * Get the corresponding holder
 *
 * @author Jiaju Zhuang
 **/
public interface Holder {

    /**
     * What holder is the return
     *
     * @return Holder enum.
     */
    HolderEnum holderType();

}
