package com.sddk.conflictclassreplacer.core.file;

import com.sddk.conflictclassreplacer.core.exception.PackageNotRecognizedException;
import com.sddk.conflictclassreplacer.core.model.FileModel;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    public static List<FileModel> getFilesUnderFolder(String directoryPath) throws IOException {

        Path startPath = Paths.get(directoryPath.trim());
        ArrayList<FileModel> files = new ArrayList<>();


        Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
                FileModel fileModel;
                fileModel = new FileModel(filePath.toFile());
                files.add(fileModel);
                return FileVisitResult.CONTINUE;
            }
        });
        return files;
    }

    public static void findAndReplace(FileModel fileModel, String searchString, String replacement) throws IOException {
        String fileString = fileModel.getFileString();

        String newFileString = fileString.replaceAll(searchString, replacement);
        FileWriter fileWriter = new FileWriter(fileModel.getFile().getAbsoluteFile());

        fileWriter.write(newFileString);
        fileWriter.close();

    }
}
