package com.astlaure.mjml.plugins;

import java.nio.file.Path;

public class PluginContext {
    private static PluginContext instance;

    private String pluginVersion;

    private Path directory;

    private boolean override;

    private boolean temporary;

    private PluginContext() {}

    public static PluginContext getInstance() {
        if (instance == null) {
            instance = new PluginContext();
        }
        return instance;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
        this.directory = Path.of(PluginConstants.tempFolder, "mjml-maven-plugin-" + pluginVersion);
    }

    public Path getDirectory() {
        return directory;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }
}
