package co.unruly;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

public class MainIntegrationTest {

    @Rule
    public final SystemOutRule systemOut = new SystemOutRule().enableLog().muteForSuccessfulTests();

    @Test
    public void correctly_counts_words_of_moby_dick() throws Exception {
        Main.main("src/test/resources/moby_dick_no_punctuation.txt");

        assertThat(systemOut.getLog(), startsWith(
                "the: 14440\n" +
                "of: 6653\n" +
                "and: 6398\n" +
                "a: 4673\n" +
                "to: 4620\n"));
    }
}