package ai.chat2db.excel.converters;

import java.util.Map;

import ai.chat2db.excel.util.MapUtils;
import ai.chat2db.excel.converters.ConverterKeyBuild.ConverterKey;
import ai.chat2db.excel.converters.bigdecimal.BigDecimalBooleanConverter;
import ai.chat2db.excel.converters.bigdecimal.BigDecimalNumberConverter;
import ai.chat2db.excel.converters.bigdecimal.BigDecimalStringConverter;
import ai.chat2db.excel.converters.biginteger.BigIntegerBooleanConverter;
import ai.chat2db.excel.converters.biginteger.BigIntegerNumberConverter;
import ai.chat2db.excel.converters.biginteger.BigIntegerStringConverter;
import ai.chat2db.excel.converters.booleanconverter.BooleanBooleanConverter;
import ai.chat2db.excel.converters.booleanconverter.BooleanNumberConverter;
import ai.chat2db.excel.converters.booleanconverter.BooleanStringConverter;
import ai.chat2db.excel.converters.bytearray.BoxingByteArrayImageConverter;
import ai.chat2db.excel.converters.bytearray.ByteArrayImageConverter;
import ai.chat2db.excel.converters.byteconverter.ByteBooleanConverter;
import ai.chat2db.excel.converters.byteconverter.ByteNumberConverter;
import ai.chat2db.excel.converters.byteconverter.ByteStringConverter;
import ai.chat2db.excel.converters.date.DateDateConverter;
import ai.chat2db.excel.converters.date.DateNumberConverter;
import ai.chat2db.excel.converters.date.DateStringConverter;
import ai.chat2db.excel.converters.doubleconverter.DoubleBooleanConverter;
import ai.chat2db.excel.converters.doubleconverter.DoubleNumberConverter;
import ai.chat2db.excel.converters.doubleconverter.DoubleStringConverter;
import ai.chat2db.excel.converters.file.FileImageConverter;
import ai.chat2db.excel.converters.floatconverter.FloatBooleanConverter;
import ai.chat2db.excel.converters.floatconverter.FloatNumberConverter;
import ai.chat2db.excel.converters.floatconverter.FloatStringConverter;
import ai.chat2db.excel.converters.inputstream.InputStreamImageConverter;
import ai.chat2db.excel.converters.integer.IntegerBooleanConverter;
import ai.chat2db.excel.converters.integer.IntegerNumberConverter;
import ai.chat2db.excel.converters.integer.IntegerStringConverter;
import ai.chat2db.excel.converters.localdate.LocalDateDateConverter;
import ai.chat2db.excel.converters.localdate.LocalDateNumberConverter;
import ai.chat2db.excel.converters.localdate.LocalDateStringConverter;
import ai.chat2db.excel.converters.localdatetime.LocalDateTimeNumberConverter;
import ai.chat2db.excel.converters.localdatetime.LocalDateTimeDateConverter;
import ai.chat2db.excel.converters.localdatetime.LocalDateTimeStringConverter;
import ai.chat2db.excel.converters.longconverter.LongBooleanConverter;
import ai.chat2db.excel.converters.longconverter.LongNumberConverter;
import ai.chat2db.excel.converters.longconverter.LongStringConverter;
import ai.chat2db.excel.converters.shortconverter.ShortBooleanConverter;
import ai.chat2db.excel.converters.shortconverter.ShortNumberConverter;
import ai.chat2db.excel.converters.shortconverter.ShortStringConverter;
import ai.chat2db.excel.converters.string.StringBooleanConverter;
import ai.chat2db.excel.converters.string.StringErrorConverter;
import ai.chat2db.excel.converters.string.StringNumberConverter;
import ai.chat2db.excel.converters.string.StringStringConverter;
import ai.chat2db.excel.converters.url.UrlImageConverter;

/**
 * Load default handler
 *
 * @author Jiaju Zhuang
 */
public class DefaultConverterLoader {
    private static Map<ConverterKey, Converter<?>> defaultWriteConverter;
    private static Map<ConverterKey, Converter<?>> allConverter;

    static {
        initDefaultWriteConverter();
        initAllConverter();
    }

    private static void initAllConverter() {
        allConverter = MapUtils.newHashMapWithExpectedSize(40);
        putAllConverter(new BigDecimalBooleanConverter());
        putAllConverter(new BigDecimalNumberConverter());
        putAllConverter(new BigDecimalStringConverter());

        putAllConverter(new BigIntegerBooleanConverter());
        putAllConverter(new BigIntegerNumberConverter());
        putAllConverter(new BigIntegerStringConverter());

        putAllConverter(new BooleanBooleanConverter());
        putAllConverter(new BooleanNumberConverter());
        putAllConverter(new BooleanStringConverter());

        putAllConverter(new ByteBooleanConverter());
        putAllConverter(new ByteNumberConverter());
        putAllConverter(new ByteStringConverter());

        putAllConverter(new DateNumberConverter());
        putAllConverter(new DateStringConverter());

        putAllConverter(new LocalDateNumberConverter());
        putAllConverter(new LocalDateStringConverter());

        putAllConverter(new LocalDateTimeNumberConverter());
        putAllConverter(new LocalDateTimeStringConverter());

        putAllConverter(new DoubleBooleanConverter());
        putAllConverter(new DoubleNumberConverter());
        putAllConverter(new DoubleStringConverter());

        putAllConverter(new FloatBooleanConverter());
        putAllConverter(new FloatNumberConverter());
        putAllConverter(new FloatStringConverter());

        putAllConverter(new IntegerBooleanConverter());
        putAllConverter(new IntegerNumberConverter());
        putAllConverter(new IntegerStringConverter());

        putAllConverter(new LongBooleanConverter());
        putAllConverter(new LongNumberConverter());
        putAllConverter(new LongStringConverter());

        putAllConverter(new ShortBooleanConverter());
        putAllConverter(new ShortNumberConverter());
        putAllConverter(new ShortStringConverter());

        putAllConverter(new StringBooleanConverter());
        putAllConverter(new StringNumberConverter());
        putAllConverter(new StringStringConverter());
        putAllConverter(new StringErrorConverter());
    }

    private static void initDefaultWriteConverter() {
        defaultWriteConverter = MapUtils.newHashMapWithExpectedSize(40);
        putWriteConverter(new BigDecimalNumberConverter());
        putWriteConverter(new BigIntegerNumberConverter());
        putWriteConverter(new BooleanBooleanConverter());
        putWriteConverter(new ByteNumberConverter());
        putWriteConverter(new DateDateConverter());
        putWriteConverter(new LocalDateTimeDateConverter());
        putWriteConverter(new LocalDateDateConverter());
        putWriteConverter(new DoubleNumberConverter());
        putWriteConverter(new FloatNumberConverter());
        putWriteConverter(new IntegerNumberConverter());
        putWriteConverter(new LongNumberConverter());
        putWriteConverter(new ShortNumberConverter());
        putWriteConverter(new StringStringConverter());
        putWriteConverter(new FileImageConverter());
        putWriteConverter(new InputStreamImageConverter());
        putWriteConverter(new ByteArrayImageConverter());
        putWriteConverter(new BoxingByteArrayImageConverter());
        putWriteConverter(new UrlImageConverter());

        // In some cases, it must be converted to string
        putWriteStringConverter(new BigDecimalStringConverter());
        putWriteStringConverter(new BigIntegerStringConverter());
        putWriteStringConverter(new BooleanStringConverter());
        putWriteStringConverter(new ByteStringConverter());
        putWriteStringConverter(new DateStringConverter());
        putWriteStringConverter(new LocalDateStringConverter());
        putWriteStringConverter(new LocalDateTimeStringConverter());
        putWriteStringConverter(new DoubleStringConverter());
        putWriteStringConverter(new FloatStringConverter());
        putWriteStringConverter(new IntegerStringConverter());
        putWriteStringConverter(new LongStringConverter());
        putWriteStringConverter(new ShortStringConverter());
        putWriteStringConverter(new StringStringConverter());
    }

    /**
     * Load default write converter
     *
     * @return
     */
    public static Map<ConverterKey, Converter<?>> loadDefaultWriteConverter() {
        return defaultWriteConverter;
    }

    private static void putWriteConverter(Converter<?> converter) {
        defaultWriteConverter.put(ConverterKeyBuild.buildKey(converter.supportJavaTypeKey()), converter);
    }

    private static void putWriteStringConverter(Converter<?> converter) {
        defaultWriteConverter.put(
            ConverterKeyBuild.buildKey(converter.supportJavaTypeKey(), converter.supportExcelTypeKey()), converter);
    }

    /**
     * Load default read converter
     *
     * @return
     */
    public static Map<ConverterKey, Converter<?>> loadDefaultReadConverter() {
        return loadAllConverter();
    }

    /**
     * Load all converter
     *
     * @return
     */
    public static Map<ConverterKey, Converter<?>> loadAllConverter() {
        return allConverter;
    }

    private static void putAllConverter(Converter<?> converter) {
        allConverter.put(ConverterKeyBuild.buildKey(converter.supportJavaTypeKey(), converter.supportExcelTypeKey()),
            converter);
    }
}
