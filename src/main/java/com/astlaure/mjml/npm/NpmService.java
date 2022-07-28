package com.astlaure.mjml.npm;

import com.astlaure.mjml.files.FileService;
import com.astlaure.mjml.plugins.PluginConstants;
import com.astlaure.mjml.plugins.PluginContext;
import com.astlaure.mjml.commands.CommandService;

import java.nio.file.Files;
import java.nio.file.Path;

public class NpmService {
    private final CommandService commandService = new CommandService();
    private final FileService fileService = new FileService();

    private final PluginContext context = PluginContext.getInstance();

    public void create(Path directory) {
        if (!context.isTemporary() && Files.exists(Path.of(directory.toString(), "package.json"))) {
            return;
        }

        commandService.executeCommand(
                PluginConstants.executable,
                PluginConstants.argument,
                "cd " + directory + " && npm init -y"
        );
        commandService.executeCommand(
                PluginConstants.executable,
                PluginConstants.argument,
                "cd " + directory + " && npm install mjml"
        );
    }

    public void cleanup(Path directory) {
        if (!context.isTemporary()) {
            return;
        }

        fileService.deleteFile(directory, "package.json");
        fileService.deleteFile(directory, "package-lock.json");
        fileService.deleteFolder(directory, "node_modules");
    }
}
