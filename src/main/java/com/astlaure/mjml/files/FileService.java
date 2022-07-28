package com.astlaure.mjml.files;

import com.astlaure.mjml.plugins.PluginContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class FileService {
    private final PluginContext context = PluginContext.getInstance();
    private final PersistentFileService persistentFileService = new PersistentFileService();
    private final TemporaryFileService temporaryFileService = new TemporaryFileService();

    public Path createDirectory() {
        if (context.isTemporary()) {
            return temporaryFileService.createTemporaryDirectory();
        }
        return persistentFileService.createDirectory();
    }

    public Path createFile(Path directory, String filename, String extension) {
        if (context.isTemporary()) {
            return temporaryFileService.createTemporaryFile(directory, filename, extension);
        }
        return persistentFileService.createFile(filename);
    }

    public void deleteFile(Path directory, String filename) {
        try {
            Path filePath = Path.of(directory.toString(), filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFolder(Path directory, String folder) {
        try {
            Path tempFolder = Path.of(directory.toString(), folder);

            Files.walk(tempFolder)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
