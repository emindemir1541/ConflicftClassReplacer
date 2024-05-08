package com.sddk.conflictclassreplacer.presentation;

public class ConsoleHelper {

    public static void writeInfo(String message) {
        System.out.print(message);
    }
    public static void writeInfoLn(String message) {
        System.out.println(message);
    }

    public static void tabBlank() {
        writeInfo("    ");
    }

    public static void blankLine() {
        writeInfo("\n");
    }

    public static String packagePathWithBrackets(String packageName) {
        return "[" + packageName + "]";
    }
}
