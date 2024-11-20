package ai.chat2db.excel.metadata;

import java.util.Map;

import ai.chat2db.excel.converters.Converter;
import ai.chat2db.excel.converters.ConverterKeyBuild;

/**
 * Get the corresponding holder
 *
 * @author Jiaju Zhuang
 **/
public interface ConfigurationHolder extends Holder {

    /**
     * Record whether it's new or from cache
     *
     * @return Record whether it's new or from cache
     */
    boolean isNew();

    /**
     * Some global variables
     *
     * @return Global configuration
     */
    GlobalConfiguration globalConfiguration();

    /**
     * What converter does the currently operated cell need to execute
     *
     * @return Converter
     */
    Map<ConverterKeyBuild.ConverterKey, Converter<?>> converterMap();
}
