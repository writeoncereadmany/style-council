package co.unruly.wordcount;

import co.unruly.wordcount.counting.WordCount;
import co.unruly.wordcount.counting.WordCounts;
import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class WordCounterTest {

    private final WordCounter wordCounter = new WordCounter();

    @Test
    public void empty_file_contains_no_words() {
        WordCounts wordCounts = wordCounter.countWords(Stream.of());
        assertThat(wordCounts.counts(), is(empty()));
    }

    @Test
    public void one_word_counted_once() {
        WordCounts result = wordCounter.countWords(Stream.of("word"));
        assertThat(result.counts(), contains(new WordCount("word", 1)));
    }

    @Test
    public void case_is_ignored() throws Exception {
        WordCounts result = wordCounter.countWords(Stream.of("word Word worD WORD"));
        assertThat(result.counts(), contains(new WordCount("word", 4)));
    }

    @Test
    public void orders_by_count_descending() throws Exception {
        WordCounts result = wordCounter.countWords(Stream.of("Word word", "another word"));
        assertThat(result.counts(), contains(new WordCount("word", 3), new WordCount("another", 1)));
    }

    @Test
    public void whitespace_is_not_recorded_as_a_word() throws Exception {
        WordCounts result = wordCounter.countWords(Stream.of("Word     word  \t  \n", "", "\n", "\r\n", " word"));
        assertThat(result.counts(), contains(new WordCount("word", 3)));
    }
}