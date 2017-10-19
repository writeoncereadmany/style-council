package co.unruly.wordcount;

import co.unruly.wordcount.counting.WordCounts;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;

public class WordCountPrinterTest {

    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog();

    @Test
    public void prints_word_counts_to_standard_out() throws Exception {
        WordCounts wordCounts = new WordCounts();
        wordCounts.record("hello");
        wordCounts.record("world");
        wordCounts.record("goodbye");
        wordCounts.record("world");

        new WordCountPrinter().print(wordCounts);

        assertThat(systemOut.getLog(), is(
                "world: 2\n" +
                "goodbye: 1\n" +
                "hello: 1\n"
        ));
    }

    @Test
    public void prints_nothing_if_nothing_was_counted() throws Exception {
        new WordCountPrinter().print(new WordCounts());

        assertThat(systemOut.getLog(), isEmptyString());
    }
}