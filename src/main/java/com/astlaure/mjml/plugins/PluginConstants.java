package com.astlaure.mjml.plugins;

public class PluginConstants {
    public static final boolean isWindows = System.getProperty("os.name")
            .toLowerCase().startsWith("windows");

    public static String executable = isWindows ? "cmd.exe" : "sh";
    public static String argument = isWindows ? "/c" : "-c";

    public static String tempFolder = System.getProperty("java.io.tmpdir");
}
