package me.teakivy.teakstweaks.utils.command;

public class Arg {
    private final String[] options;
    private final Type type;

    public Arg(Type type, String option1, String... options) {
        this.type = type;
        this.options = new String[options.length + 1];
        this.options[0] = option1;
        System.arraycopy(options, 0, this.options, 1, options.length);
    }

    public Arg(Type type, String option1) {
        this.type = type;
        this.options = new String[1];
        this.options[0] = option1;
    }

    public String[] getOptions() {
        return options;
    }

    public Type getType() {
        return type;
    }

    public boolean isRequired() {
        return type == Type.REQUIRED;
    }

    public String toString() {
        StringBuilder options = new StringBuilder(type == Type.REQUIRED ? "<" : "[");
        for (String option : this.options) {
            options.append(option).append(" | ");
        }
        options = new StringBuilder(options.substring(0, options.length() - 3));
        options.append(type == Type.REQUIRED ? ">" : "]");
        return options.toString();
    }

    public static Arg optional(String option1, String... options) {
        return new Arg(Type.OPTIONAL, option1, options);
    }

    public static Arg required(String option1, String... options) {
        return new Arg(Type.REQUIRED, option1, options);
    }

    public enum Type {
        REQUIRED,
        OPTIONAL
    }
}
