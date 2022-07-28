# MJML Maven Plugin

## Requirements

JDK 11 minimum

NodeJS installed

## Current Version

1.0.0

## Options

source - change the mjml template source folder

destination - change the builded templates location

extension - change the final file extension of the templates

override - replace the javascript script (useful in development)

temporary - delete the copied and downloaded files at the end of a run

## Configuration

### Configuration in the Execution

means you need to run the lifecycle (here compile) to have the config. If you run the goal directly it will be empty

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.astlaure</groupId>
            <artifactId>mjml-maven-plugin</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <executions>
                <execution>
                    <id>generate</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>build-templates</goal>
                    </goals>
                    <configuration>
                        <source>src/main/resources/emails</source>
                        <destination>target/classes/static</destination>
                        <extension>hbs</extension>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### Configuration on the root

This one works for every run scenarios

```xml
<build>
    <plugins>
        <plugin>
            <groupId>com.astlaure</groupId>
            <artifactId>mjml-maven-plugin</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <configuration>
                <source>src/main/resources/emails</source>
                <destination>target/classes/static</destination>
                <extension>hbs</extension>
            </configuration>
            <executions>
                <execution>
                    <id>generate</id>
                    <phase>compile</phase>
                    <goals>
                        <goal>build-templates</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```
