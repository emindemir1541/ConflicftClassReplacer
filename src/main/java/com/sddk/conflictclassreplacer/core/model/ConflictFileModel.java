package com.sddk.conflictclassreplacer.core.model;

import com.sddk.conflictclassreplacer.core.file.FileHelper;

import java.io.IOException;

public class ConflictFileModel {
    private FileModel targetFile;
    private FileModel localFile;

    public ConflictFileModel(FileModel targetFile, FileModel localFile) {
        this.targetFile = targetFile;
        this.localFile = localFile;
    }

    public FileModel getTargetFile() {
        return targetFile;
    }


    public FileModel getLocalFile() {
        return localFile;
    }


    public boolean areFilesEqual() throws IOException {


        return FileHelper.clearBlanksInFile(localFile.getFileString()).equals(FileHelper.clearBlanksInFile(targetFile.getFileString()));
    }

    public boolean areFilesEqualWithBlanks() throws IOException {

        return localFile.getFileString().equals(targetFile.getFileString());
    }
}
