package com.astlaure.mjml.files;

import com.astlaure.mjml.plugins.PluginContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

class PersistentFileService {
    private final PluginContext context = PluginContext.getInstance();

    Path createDirectory() {
        try {
            if (Files.notExists(context.getDirectory())) {
                Files.createDirectory(context.getDirectory());
            }
            return context.getDirectory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Path createFile(String filename) {
        Path filePath = Path.of(context.getDirectory().toString(), filename);
        // Read script file
        try (InputStream content = getClass().getClassLoader().getResourceAsStream(filename)) {
            if (context.isOverride()) {
                Files.deleteIfExists(filePath);
            }

            if (Files.exists(filePath)) {
                return filePath;
            }

            // Create temp file
            Files.createFile(filePath);

            // Write to temp file
            assert content != null;
            Files.write(filePath, content.readAllBytes());

            return filePath;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
