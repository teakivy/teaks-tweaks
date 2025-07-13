package me.teakivy.teakstweaks.utils.oldcommand;

public class ArgumentEvent {
    private final String[] args;

    public ArgumentEvent(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public String getArg(int index) {
        return args[index];
    }

    public int getArgsLength() {
        return args.length;
    }

    public boolean isArgsSize(int length) {
        return args.length == length;
    }

    public boolean isArg(int index, String arg) {
        return args[index].equals(arg);
    }
    public boolean isArg(int index) {
        return args.length - 1 == index;
    }

    public boolean hasArgs() {
        return args.length > 0;
    }

    public boolean hasArgs(int length) {
        return args.length >= length;
    }
}
