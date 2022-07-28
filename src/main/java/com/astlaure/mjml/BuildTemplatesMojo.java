package com.astlaure.mjml;

import com.astlaure.mjml.files.FileService;
import com.astlaure.mjml.plugins.PluginConstants;
import com.astlaure.mjml.plugins.PluginContext;
import com.astlaure.mjml.commands.CommandService;
import com.astlaure.mjml.npm.NpmService;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Mojo(name = "build-templates", defaultPhase = LifecyclePhase.COMPILE)
public class BuildTemplatesMojo extends AbstractMojo {
    private final FileService fileService = new FileService();
    private final NpmService npmService = new NpmService();
    private final CommandService commandService = new CommandService();

    @Parameter(property = "mjml.source")
    private String source;

    @Parameter(property = "mjml.destination")
    private String destination;

    @Parameter(property = "mjml.extension")
    private String extension;

    @Parameter(property = "mjml.override", defaultValue = "false")
    private boolean override;

    @Parameter(property = "mjml.temporary", defaultValue = "false")
    private boolean temporary;

    @Parameter( defaultValue = "${plugin}", readonly = true )
    private PluginDescriptor plugin;

    PluginContext context = PluginContext.getInstance();

    @Override
    public void execute() {
        context.setPluginVersion(plugin.getVersion());
        context.setOverride(override);
        context.setTemporary(temporary);

        Path directory = fileService.createDirectory();
        Path script = fileService.createFile(directory, "index.js", ".js");

        npmService.create(directory);

        Map<String, String> values = new HashMap<>();
        values.put("MJML_SOURCE", source == null ? "" : source);
        values.put("MJML_DEST", destination == null ? "" : destination);
        values.put("MJML_EXT", extension == null ? "" : extension);

        commandService.executeCommand(
                values,
                PluginConstants.executable,
                PluginConstants.argument,
                "node " + script
        );

        npmService.cleanup(directory);
    }
}
