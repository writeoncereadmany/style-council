package co.unruly;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FileLocatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final FileLocator fileLocator = new FileLocator();

    @Test
    public void raises_error_when_no_file_path() throws Exception {
        expectedException.expect(RuntimeException.class);

        fileLocator.findInputFile();
    }

    @Test
    public void raises_error_when_file_does_not_exist() throws Exception {
        expectedException.expect(RuntimeException.class);

        fileLocator.findInputFile("nope.txt");
    }

    @Test
    public void raises_error_when_file_path_is_not_a_file() throws Exception {
        expectedException.expect(RuntimeException.class);

        fileLocator.findInputFile("src/test/resources");
    }

    @Test
    public void returns_file_when_file_path_exists() throws Exception {
        File inputFile = fileLocator.findInputFile("src/test/resources/moby_dick_no_punctuation.txt");

        assertTrue(inputFile.exists());
        assertTrue(inputFile.canRead());
        assertThat(inputFile.getName(), is("moby_dick_no_punctuation.txt"));
    }

    @Test
    public void only_uses_the_first_argument() throws Exception {
        File inputFile = fileLocator.findInputFile("src/test/resources/moby_dick_no_punctuation.txt", "pom.xml");

        assertThat(inputFile.getName(), is("moby_dick_no_punctuation.txt"));
    }
}