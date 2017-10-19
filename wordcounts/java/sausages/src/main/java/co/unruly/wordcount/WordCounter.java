package co.unruly.wordcount;

import co.unruly.wordcount.counting.WordCounts;

import java.util.Arrays;
import java.util.stream.Stream;

public class WordCounter {
    WordCounts countWords(Stream<String> lines) {
        WordCounts wordCounts = new WordCounts();

        normalisedWordsOf(lines)
            .forEach(wordCounts::record);

        return wordCounts;
    }

    private Stream<String> normalisedWordsOf(Stream<String> lines) {
        return lines
                .map(String::toLowerCase)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream);
    }
}
