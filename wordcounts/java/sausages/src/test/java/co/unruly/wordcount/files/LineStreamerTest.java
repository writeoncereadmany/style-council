package co.unruly.wordcount.files;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LineStreamerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void streams_all_the_lines_in_the_file() throws Exception {
        File file = new File("src/test/resources/moby_dick_no_punctuation.txt");

        Stream<String> lines = new LineStreamer().linesOf(file);

        assertThat(lines.count(), is(21955L));
    }

    @Test
    public void streams_the_content_of_the_file() throws Exception {
        File file = new File("src/test/resources/moby_dick_no_punctuation.txt");

        Stream<String> lines = new LineStreamer().linesOf(file);

        assertThat(lines.collect(toList()), hasItems(
                "MOBY DICK OR THE WHALE",
                "By Herman Melville",
                "So powerfully did the whole grim aspect of Ahab affect me and the livid",
                "I fear not thy epidemic man said Ahab from the bulwarks to Captain"
        ));
    }

    @Test
    public void throws_error_when_file_cannot_be_opened() throws Exception {
        expectedException.expect(RuntimeException.class);

        new LineStreamer().linesOf(new File("nope.txt"));
    }
}