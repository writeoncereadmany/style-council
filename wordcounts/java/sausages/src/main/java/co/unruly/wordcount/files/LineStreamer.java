package co.unruly.wordcount.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class LineStreamer {
    public Stream<String> linesOf(File inputFile) {
        try {
            return Files.lines(inputFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
