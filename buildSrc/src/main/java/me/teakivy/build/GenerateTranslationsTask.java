package me.teakivy.build;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.*;

@CacheableTask
public abstract class GenerateTranslationsTask extends DefaultTask {

    @InputDirectory
    @PathSensitive(PathSensitivity.RELATIVE)
    public abstract DirectoryProperty getTranslationsDirectory();

    @OutputDirectory
    public abstract DirectoryProperty getOutputDirectory();

    @Input
    public abstract Property<String> getBaseLocale();

    public GenerateTranslationsTask() {
        getBaseLocale().convention("en_US");
    }

    @TaskAction
    public void generate() throws Exception {
        TranslationGenerator generator = new TranslationGenerator(
                getTranslationsDirectory().get().getAsFile().toPath(),
                getOutputDirectory().get().getAsFile().toPath(),
                getBaseLocale().get()
        );

        generator.generate();
    }
}