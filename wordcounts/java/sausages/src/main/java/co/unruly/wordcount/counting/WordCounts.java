package co.unruly.wordcount.counting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class WordCounts {

    private final Map<String, Integer> wordCounts = new HashMap<>();

    public void record(String word) {
        int currentCount = wordCounts.getOrDefault(word, 0);
        wordCounts.put(word, currentCount + 1);
    }

    public List<WordCount> counts() {
        return wordCounts.entrySet().stream()
                .map(entry -> new WordCount(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt((WordCount a) -> a.count).reversed())
                .collect(toList());
    }
}
