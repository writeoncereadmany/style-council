package co.unruly.wordcount.counting;

import java.util.Objects;

public class WordCount {
    private final String word;
    final int count;

    public WordCount(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String printFormat() {
        return String.format("%s: %d", word, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordCount wordCount = (WordCount) o;
        return count == wordCount.count &&
                Objects.equals(word, wordCount.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, count);
    }

    @Override
    public String toString() {
        return String.format("{'%s': %d}", word, count);
    }
}
