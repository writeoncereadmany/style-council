import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.nio.file.Paths.get;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordCount {
    public static void main(String... args) throws Exception {
        Files.lines(get("/Users/tom/Downloads/moby_dick_no_punctuation.txt"))
            .flatMap(line -> Stream.of(line.split(" ")))
            .filter(word -> !"".equals(word.trim()))
            .collect(groupingBy(String::toLowerCase, counting()))
            .entrySet()
            .stream()
            .sorted(reverseOrder(comparing(Map.Entry::getValue)))
            .forEach(entry -> out.println(entry));
    }
}
