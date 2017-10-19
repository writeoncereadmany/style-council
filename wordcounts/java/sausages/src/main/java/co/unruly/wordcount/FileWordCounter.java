package co.unruly.wordcount;

import co.unruly.wordcount.counting.WordCounts;
import co.unruly.wordcount.files.LineStreamer;

import java.io.File;

public class FileWordCounter {
    private final LineStreamer lineStreamer;
    private final WordCounter wordCounter;

    public FileWordCounter(LineStreamer lineStreamer, WordCounter wordCounter) {
        this.lineStreamer = lineStreamer;
        this.wordCounter = wordCounter;
    }

    public WordCounts countWordsIn(File inputFile) {
        return wordCounter.countWords(lineStreamer.linesOf(inputFile));
    }
}
