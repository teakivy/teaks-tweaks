package me.teakivy.build;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

// This generator was created with the assistance of AI
public final class TranslationGenerator {

    private static final String PACKAGE_NAME =
            "me.teakivy.teakstweaks.generated";

    private static final String CLASS_NAME =
            "Translations";

    private static final String TRANSLATION_KEY_CLASS =
            "me.teakivy.teakstweaks.utils.translation.TranslationKey";

    private final Path translationsDirectory;
    private final Path outputDirectory;
    private final String baseLocale;

    private final Yaml yaml;

    public TranslationGenerator(
            Path translationsDirectory,
            Path outputDirectory,
            String baseLocale
    ) {
        this.translationsDirectory = translationsDirectory;
        this.outputDirectory = outputDirectory;
        this.baseLocale = baseLocale;

        LoaderOptions options = new LoaderOptions();
        options.setAllowDuplicateKeys(false);

        this.yaml = new Yaml(new SafeConstructor(options));
    }

    public void generate() throws IOException {
        Path localeDirectory = translationsDirectory.resolve(baseLocale);

        if (!Files.isDirectory(localeDirectory)) {
            throw new IllegalStateException(
                    "Base translation locale does not exist: "
                            + localeDirectory
            );
        }

        GeneratedNode root = new GeneratedNode(null, "<root>");

        List<Path> files;

        try (Stream<Path> stream = Files.walk(localeDirectory)) {
            files = stream
                    .filter(Files::isRegularFile)
                    .filter(this::isYamlFile)
                    .sorted()
                    .toList();
        }

        if (files.isEmpty()) {
            throw new IllegalStateException(
                    "No translation YAML files found in "
                            + localeDirectory
            );
        }

        for (Path file : files) {
            processFile(root, localeDirectory, file);
        }

        String javaSource = generateJava(root);

        Path packageDirectory = outputDirectory;

        for (String part : PACKAGE_NAME.split("\\.")) {
            packageDirectory = packageDirectory.resolve(part);
        }

        Files.createDirectories(packageDirectory);

        Path outputFile =
                packageDirectory.resolve(CLASS_NAME + ".java");

        Files.writeString(
                outputFile,
                javaSource,
                StandardCharsets.UTF_8
        );

        System.out.println(
                "Generated translations: " + outputFile
        );
    }

    private void processFile(
            GeneratedNode root,
            Path localeDirectory,
            Path file
    ) throws IOException {

        Path relative = localeDirectory.relativize(file);

        GeneratedNode current = root;

        /*
         * Directory hierarchy:
         *
         * packs/afk_display.yml
         *
         * becomes:
         *
         * Translations.Packs.AfkDisplay
         */
        for (int i = 0; i < relative.getNameCount() - 1; i++) {
            String directoryName = relative
                    .getName(i)
                    .toString();

            validateSegment(
                    directoryName,
                    "directory",
                    file
            );

            current = current.getOrCreateChild(
                    toClassName(directoryName),
                    directoryName,
                    file
            );
        }

        String fileName = stripExtension(
                relative
                        .getFileName()
                        .toString()
        );

        validateSegment(
                fileName,
                "file",
                file
        );

        current = current.getOrCreateChild(
                toClassName(fileName),
                fileName,
                file
        );

        Object parsed;

        try (InputStream input = Files.newInputStream(file)) {
            parsed = yaml.load(input);
        }

        if (parsed == null) {
            return;
        }

        if (!(parsed instanceof Map<?, ?> map)) {
            throw new IllegalStateException(
                    "Translation file must contain a YAML map: "
                            + file
            );
        }

        String keyPrefix = createFileKey(
                localeDirectory,
                file
        );

        processMap(
                current,
                map,
                keyPrefix,
                file
        );
    }

    private void processMap(
            GeneratedNode node,
            Map<?, ?> map,
            String keyPrefix,
            Path file
    ) {

        for (Map.Entry<?, ?> entry : map.entrySet()) {

            if (!(entry.getKey() instanceof String key)) {
                throw new IllegalStateException(
                        "Translation keys must be strings in "
                                + file
                );
            }

            validateSegment(
                    key,
                    "translation key",
                    file
            );

            Object value = entry.getValue();

            String fullKey =
                    keyPrefix + "." + key;

            if (value instanceof Map<?, ?> childMap) {

                GeneratedNode child =
                        node.getOrCreateChild(
                                toClassName(key),
                                key,
                                file
                        );

                processMap(
                        child,
                        childMap,
                        fullKey,
                        file
                );

                continue;
            }

            if (!(value instanceof String)) {
                throw new IllegalStateException(
                        "Translation value must be a String: "
                                + fullKey
                                + " in "
                                + file
                                + " (found "
                                + (
                                value == null
                                        ? "null"
                                        : value.getClass()
                                        .getSimpleName()
                        )
                                + ")"
                );
            }

            node.addConstant(
                    toConstantName(key),
                    fullKey,
                    key,
                    file
            );
        }
    }

    private String generateJava(GeneratedNode root) {

        StringBuilder builder = new StringBuilder();

        builder.append("""
                // AUTO-GENERATED FILE.
                // DO NOT EDIT MANUALLY.
                //
                // Generated from the base translation files.

                package %s;

                import %s;

                public final class %s {

                    private %s() {}

                """.formatted(
                PACKAGE_NAME,
                TRANSLATION_KEY_CLASS,
                CLASS_NAME,
                CLASS_NAME
        ));

        for (GeneratedNode child : root.children.values()) {
            writeNode(
                    builder,
                    child,
                    1
            );
        }

        builder.append("}\n");

        return builder.toString();
    }

    private void writeNode(
            StringBuilder builder,
            GeneratedNode node,
            int depth
    ) {

        indent(builder, depth)
                .append("public static final class ")
                .append(node.className)
                .append(" {\n\n");

        indent(builder, depth + 1)
                .append("private ")
                .append(node.className)
                .append("() {}\n");

        if (!node.constants.isEmpty()
                || !node.children.isEmpty()) {
            builder.append('\n');
        }

        for (GeneratedConstant constant
                : node.constants.values()) {

            indent(builder, depth + 1)
                    .append("public static final TranslationKey ")
                    .append(constant.javaName())
                    .append(" = new TranslationKey(\"")
                    .append(escapeJava(constant.key()))
                    .append("\");\n");
        }

        if (!node.constants.isEmpty()
                && !node.children.isEmpty()) {
            builder.append('\n');
        }

        Iterator<GeneratedNode> iterator =
                node.children.values().iterator();

        while (iterator.hasNext()) {
            GeneratedNode child = iterator.next();

            writeNode(
                    builder,
                    child,
                    depth + 1
            );

            if (iterator.hasNext()) {
                builder.append('\n');
            }
        }

        indent(builder, depth)
                .append("}\n");
    }

    private String createFileKey(
            Path localeDirectory,
            Path file
    ) {

        Path relative =
                localeDirectory.relativize(file);

        List<String> parts =
                new ArrayList<>();

        for (int i = 0;
             i < relative.getNameCount();
             i++) {

            String part =
                    relative.getName(i).toString();

            if (i == relative.getNameCount() - 1) {
                part = stripExtension(part);
            }

            parts.add(part);
        }

        return String.join(".", parts);
    }

    private boolean isYamlFile(Path path) {
        String name = path
                .getFileName()
                .toString()
                .toLowerCase(Locale.ROOT);

        return name.endsWith(".yml")
                || name.endsWith(".yaml");
    }

    private String stripExtension(String fileName) {

        int dot = fileName.lastIndexOf('.');

        if (dot == -1) {
            return fileName;
        }

        return fileName.substring(0, dot);
    }

    private void validateSegment(
            String segment,
            String type,
            Path file
    ) {

        if (!segment.matches("[A-Za-z0-9_-]+")) {
            throw new IllegalStateException(
                    "Invalid "
                            + type
                            + " name '"
                            + segment
                            + "' in "
                            + file
                            + ". Only letters, numbers, '-' and '_' are allowed."
            );
        }
    }

    private String toClassName(String value) {

        List<String> words =
                splitWords(value);

        StringBuilder result =
                new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) continue;

            result.append(
                    Character.toUpperCase(
                            word.charAt(0)
                    )
            );

            if (word.length() > 1) {
                result.append(
                        word.substring(1)
                                .toLowerCase(Locale.ROOT)
                );
            }
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot convert to Java class name: "
                            + value
            );
        }

        if (Character.isDigit(result.charAt(0))) {
            result.insert(0, '_');
        }

        return result.toString();
    }

    private String toConstantName(String value) {

        List<String> words =
                splitWords(value);

        String result = String.join(
                "_",
                words.stream()
                        .map(word ->
                                word.toUpperCase(Locale.ROOT)
                        )
                        .toList()
        );

        if (result.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot convert to Java constant: "
                            + value
            );
        }

        if (Character.isDigit(result.charAt(0))) {
            result = "_" + result;
        }

        return result;
    }

    private List<String> splitWords(String value) {
        String normalized = value.replaceAll(
                "([a-z0-9])([A-Z])",
                "$1_$2"
        );

        return Arrays.stream(
                        normalized.split("[^A-Za-z0-9]+")
                )
                .filter(part -> !part.isBlank())
                .toList();
    }

    private String escapeJava(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    private StringBuilder indent(
            StringBuilder builder,
            int depth
    ) {
        return builder.append(
                "    ".repeat(depth)
        );
    }

    private static final class GeneratedNode {

        private final String className;
        private final String sourceName;

        private final Map<String, GeneratedNode> children =
                new LinkedHashMap<>();

        private final Map<String, GeneratedConstant> constants =
                new LinkedHashMap<>();

        private GeneratedNode(
                String className,
                String sourceName
        ) {
            this.className = className;
            this.sourceName = sourceName;
        }

        private GeneratedNode getOrCreateChild(
                String className,
                String sourceName,
                Path file
        ) {

            GeneratedNode existing =
                    children.get(className);

            if (existing != null) {

                if (!existing.sourceName.equals(sourceName)) {
                    throw new IllegalStateException(
                            "Java name collision: '"
                                    + existing.sourceName
                                    + "' and '"
                                    + sourceName
                                    + "' both become '"
                                    + className
                                    + "' in "
                                    + file
                    );
                }

                return existing;
            }

            GeneratedNode child =
                    new GeneratedNode(
                            className,
                            sourceName
                    );

            children.put(
                    className,
                    child
            );

            return child;
        }

        private void addConstant(
                String javaName,
                String key,
                String sourceName,
                Path file
        ) {

            GeneratedConstant existing =
                    constants.get(javaName);

            if (existing != null) {
                throw new IllegalStateException(
                        "Java constant collision: '"
                                + existing.sourceName()
                                + "' and '"
                                + sourceName
                                + "' both become '"
                                + javaName
                                + "' in "
                                + file
                );
            }

            constants.put(
                    javaName,
                    new GeneratedConstant(
                            javaName,
                            key,
                            sourceName
                    )
            );
        }
    }

    private record GeneratedConstant(
            String javaName,
            String key,
            String sourceName
    ) {}
}