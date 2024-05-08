package com.sddk.conflictclassreplacer.core.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {


    public static String readFileAsOneString(String path) throws IOException {
        String fileData = new String(Files.readAllBytes(Paths.get(path)));
        return fileData;
    }

    public static String clearBlanksInFile(String file) {
        return file.replaceAll("(?m)^\\\\s*$[\\n\\r]{1,}", "");
    }

    public static boolean isClass(File file) {
        return file.getName().endsWith(".class");
    }

}
