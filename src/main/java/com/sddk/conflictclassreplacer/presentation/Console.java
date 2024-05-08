package com.sddk.conflictclassreplacer.presentation;

import com.sddk.conflictclassreplacer.core.exception.PackageNotRecognizedException;
import com.sddk.conflictclassreplacer.core.file.ConflictFileUtil;
import com.sddk.conflictclassreplacer.core.file.FileFinder;
import com.sddk.conflictclassreplacer.core.model.ConflictFileModel;
import com.sddk.conflictclassreplacer.core.model.FileModel;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static com.sddk.conflictclassreplacer.presentation.ConsoleHelper.*;

public class Console {

    private final String localProjectPath;
    private final String targetProjectPath;
    private List<FileModel> allLocalFiles = null;
    private List<FileModel> allTargetFiles = null;
    private List<ConflictFileModel> allConflictFiles = null;


    public Console(String[] args) {

        localProjectPath = args[0];
        targetProjectPath = args[1];

        askForPackage();
        getAllFiles();
        getConflictFiles();
        replaceImports();
        deleteLocalFiles();
    }



    public void askForPackage() {

        tabBlank();
        writeInfo("Local Package: ");
        writeInfoLn(ConflictFileUtil.packagePathFromAbsoluteProjectPath(packagePathWithBrackets(localProjectPath)));
        tabBlank();
        writeInfo("Target Package: ");
        writeInfoLn(ConflictFileUtil.packagePathFromAbsoluteProjectPath(packagePathWithBrackets(targetProjectPath)));
        blankLine();


    }

    public void getAllFiles() {

        try {
            allLocalFiles = FileFinder.getFilesUnderFolder(localProjectPath);
            allTargetFiles = FileFinder.getFilesUnderFolder(targetProjectPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getConflictFiles() {
        try {
            allConflictFiles = ConflictFileUtil.getConflictFiles(allLocalFiles, allTargetFiles, conflictFileModel -> {
                try {
                    tabBlank();
                    tabBlank();
                    writeInfoLn(packagePathWithBrackets(conflictFileModel.getLocalFile().getImportPath()) + " --> " + conflictFileModel.getLocalFile().getFile().getAbsolutePath());
                    tabBlank();
                    tabBlank();
                    writeInfoLn(packagePathWithBrackets(conflictFileModel.getTargetFile().getImportPath()) + " --> " + conflictFileModel.getTargetFile().getFile().getAbsolutePath());
                    tabBlank();
                    tabBlank();
                    writeInfo("Are Files Equal: " + conflictFileModel.areFilesEqual() + "       ");
                    writeInfo("Are Files Equal With Blanks: " + conflictFileModel.areFilesEqualWithBlanks());
                    blankLine();
                    blankLine();
                } catch (PackageNotRecognizedException | IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void replaceImports() {

        String answer = "";

        do {
            writeInfoLn("Do you want to replace imports? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            answer = scanner.next();
        } while (!answer.equals("y") && !answer.equals("n") && !answer.equals("Y") && !answer.equals("N"));
        if (answer.equals("y") || answer.equals("Y")) {
            try {
                ConflictFileUtil.changeImports(allLocalFiles, allConflictFiles, conflictFileModel -> {
                    try {
                        blankLine();
                        writeInfo(packagePathWithBrackets(conflictFileModel.getLocalFile().getImportPath()));
                        writeInfo(" --> ");
                        writeInfoLn(packagePathWithBrackets(conflictFileModel.getTargetFile().getImportPath()));
                    } catch (PackageNotRecognizedException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }, fileModel -> {
                    tabBlank();
                    writeInfo(fileModel.getFile().getAbsolutePath());
                    blankLine();
                    return null;
                });
            } catch (PackageNotRecognizedException | IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.exit(0);
        }
    }

    public void deleteLocalFiles() {

        String answer = "";
        do {
            writeInfoLn("Do you want to replace imports? (Y/N)");
            Scanner scanner = new Scanner(System.in);
            answer = scanner.next();
        } while (!answer.equals("y") && !answer.equals("n") && !answer.equals("Y") && !answer.equals("N"));

        if (answer.equals("y") || answer.equals("Y")) {
            blankLine();
            writeInfoLn("DELETING FILES");
            ConflictFileUtil.deleteLocalFiles(allConflictFiles, fileModel -> {

                tabBlank();
                try {
                    writeInfoLn(fileModel.getImportPath());
                } catch (PackageNotRecognizedException e) {
                    throw new RuntimeException(e);
                }

                return null;
            });

        } else {
           System.exit(0);
        }
    }

}
