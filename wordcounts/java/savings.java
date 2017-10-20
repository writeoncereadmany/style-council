import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;

public class Words2 {

    public static void main(String [] args) throws FileNotFoundException {
        printWords();
    }

    public static void printWords() throws FileNotFoundException {

        Stream<String> lowerCaseWords =
                new BufferedReader(new FileReader("moby_dick_no_punctuation.txt"))
                .lines()
                .flatMap(line -> Stream.of(line.split(" ")))
                .filter(word -> !"".equals(word))
                .map(word -> word.toLowerCase());

        class WordAndItsCount implements Comparable<WordAndItsCount> {
            Integer count;
            String word;

            public WordAndItsCount(Integer count, String word) {
                this.count = count;
                this.word = word;
            }

            @Override
            public int compareTo(WordAndItsCount o) {
                return o.count.compareTo(this.count);
            }
        }

        Map<String, List<String>> groupedWords = lowerCaseWords
                .collect(groupingBy(identity()));

        Stream<WordAndItsCount> result = groupedWords
                .entrySet().stream()
                .map(x -> new WordAndItsCount(x.getValue().size(), x.getKey()))
                .sorted();

        result.limit(5)
              .forEach(word -> System.out.println(word.count + ": " + word.word));

        }



}
