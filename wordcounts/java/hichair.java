import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.nio.file.Paths.get;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;

public class WordCount {
    public static void main(String... args) throws Exception {
        Map<String, Integer> wordCounts = new HashMap<>();

        Files.lines(get("/Users/tom/Downloads/moby_dick_no_punctuation.txt"))
            .map(String::toLowerCase)
            .map(line -> line.split(" "))
            .flatMap(Stream::of)
            .filter(word -> !"".equals(word.trim()))
            .forEach(word -> wordCounts.put(word, 1 + wordCounts.getOrDefault(word, 0)));

        wordCounts.entrySet()
            .stream()
            .sorted(reverseOrder(comparing(Map.Entry::getValue)))
            .forEach(entry -> out.println(entry));
    }
}
