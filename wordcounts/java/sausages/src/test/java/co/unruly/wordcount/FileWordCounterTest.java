package co.unruly.wordcount;

import co.unruly.wordcount.counting.WordCounts;
import co.unruly.wordcount.files.LineStreamer;
import org.junit.Test;

import java.io.File;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWordCounterTest {

    @Test
    public void counts_the_words_of_all_lines_in_the_file() throws Exception {
        LineStreamer lineStreamer = mock(LineStreamer.class);
        WordCounter wordCounter = mock(WordCounter.class);
        WordCounts wordCounts = mock(WordCounts.class);
        File inputFile = mock(File.class);

        when(lineStreamer.linesOf(inputFile)).thenReturn(Stream.of("foo", "bar"));
        when(wordCounter.countWords(any())).thenReturn(wordCounts);

        FileWordCounter fileWordCounter = new FileWordCounter(lineStreamer, wordCounter);
        WordCounts results = fileWordCounter.countWordsIn(inputFile);

        assertThat(results, is(wordCounts));
    }
}