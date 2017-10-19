package co.unruly.wordcount;

import co.unruly.wordcount.counting.WordCount;
import co.unruly.wordcount.counting.WordCounts;

public class WordCountPrinter {
    public void print(WordCounts wordCounts) {
        wordCounts.counts().stream()
                .map(WordCount::printFormat)
                .forEach(System.out::println);
    }
}
