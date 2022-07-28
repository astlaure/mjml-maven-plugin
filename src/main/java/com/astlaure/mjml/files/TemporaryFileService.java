package com.astlaure.mjml.files;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

class TemporaryFileService {
    Path createTemporaryDirectory() {
        try {
            Path path = Files.createTempDirectory(null);
            path.toFile().deleteOnExit();
            return path;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Path createTemporaryFile(Path directory, String filename, String extension) {
        // Read script file
        try (InputStream content = getClass().getClassLoader().getResourceAsStream(filename)) {
            // Create temp file
            Path tempFile = Files.createTempFile(directory, null, extension);

            // Write to temp file
            assert content != null;
            Files.write(tempFile, content.readAllBytes());

            // Mark for deletion
            tempFile.toFile().deleteOnExit();

            return tempFile;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
