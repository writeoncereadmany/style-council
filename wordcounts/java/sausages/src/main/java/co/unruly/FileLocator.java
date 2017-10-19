package co.unruly;

import java.io.File;

class FileLocator {
    File findInputFile(String... args) {
        if (args.length == 0) {
            throw new RuntimeException();
        }

        File file = new File(args[0]);
        if (!isValidForInput(file)) {
            throw new RuntimeException();
        }

        return file;
    }

    private boolean isValidForInput(File file) {
        return file.exists() && file.isFile();
    }
}
