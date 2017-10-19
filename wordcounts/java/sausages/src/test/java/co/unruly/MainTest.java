package co.unruly;

import co.unruly.wordcount.FileWordCounter;
import co.unruly.wordcount.WordCountPrinter;
import co.unruly.wordcount.counting.WordCounts;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    public void prints_out_the_word_counts_of_the_given_text_file() throws Exception {
        FileLocator fileLocator = mock(FileLocator.class);
        FileWordCounter fileWordCounter = mock(FileWordCounter.class);
        WordCountPrinter printer = mock(WordCountPrinter.class);
        File inputFile = mock(File.class);
        WordCounts wordCounts = mock(WordCounts.class);

        when(fileLocator.findInputFile("file path")).thenReturn(inputFile);
        when(fileWordCounter.countWordsIn(inputFile)).thenReturn(wordCounts);

        Main main = new Main(fileLocator, fileWordCounter, printer);
        main.run("file path");

        verify(printer).print(wordCounts);
    }
}