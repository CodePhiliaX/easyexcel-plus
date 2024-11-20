package ai.chat2db.excel.cache.selector;

import ai.chat2db.excel.cache.ReadCache;
import org.apache.poi.openxml4j.opc.PackagePart;

/**
 * Select the cache
 *
 * @author Jiaju Zhuang
 **/
public interface ReadCacheSelector {

    /**
     * Select a cache
     *
     * @param sharedStringsTablePackagePart
     * @return
     */
    ReadCache readCache(PackagePart sharedStringsTablePackagePart);
}
