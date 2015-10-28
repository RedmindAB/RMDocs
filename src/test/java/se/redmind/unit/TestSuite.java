package se.redmind.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by victormattsson on 2015-10-26.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArgumentParserTest.class,
        ConditionsTest.class,
        ConfigPropertiesTest.class,
        FileFinderTest.class,
        FormatterInitTest.class,
        JsonWriterTest.class,
        ProjectSerializerTest.class,
        RMFileReaderTest.class,
        RMFileWriterTest.class,
        StringCustomizerTest.class,
        StructureFormatterTest.class,
        XLSWriterTest.class
})
public class TestSuite {

}
