package com.astlaure.mjml.commands;

import java.io.IOException;
import java.util.Map;

public class CommandService {
    public void executeCommand(String... command) {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
//        builder.inheritIO();

        try {
            builder.start().waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeCommand(Map<String, String> variables, String... command) {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(command);
//        builder.inheritIO();

        variables.forEach((key, value) -> builder.environment().put(key, value));

        try {
            builder.start().waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
