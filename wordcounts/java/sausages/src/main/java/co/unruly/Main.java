package co.unruly;

import co.unruly.wordcount.FileWordCounter;
import co.unruly.wordcount.WordCountPrinter;
import co.unruly.wordcount.WordCounter;
import co.unruly.wordcount.counting.WordCounts;
import co.unruly.wordcount.files.LineStreamer;

import java.io.File;

public class Main {

    private final FileLocator fileLocator;
    private final FileWordCounter fileWordCounter;
    private final WordCountPrinter printer;

    public Main(FileLocator fileLocator, FileWordCounter fileWordCounter, WordCountPrinter printer) {
        this.fileLocator = fileLocator;
        this.fileWordCounter = fileWordCounter;
        this.printer = printer;
    }

    public static void main(String... args) {
        FileLocator fileLocator = new FileLocator();
        FileWordCounter fileWordCounter = new FileWordCounter(new LineStreamer(), new WordCounter());
        WordCountPrinter wordCountPrinter = new WordCountPrinter();

        new Main(fileLocator, fileWordCounter, wordCountPrinter).run(args);
    }

    void run(String... args) {
        File inputFile = fileLocator.findInputFile(args);
        WordCounts wordCounts = fileWordCounter.countWordsIn(inputFile);
        printer.print(wordCounts);
    }
}
