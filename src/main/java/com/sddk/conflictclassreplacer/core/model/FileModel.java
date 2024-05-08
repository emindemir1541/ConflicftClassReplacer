package com.sddk.conflictclassreplacer.core.model;

import com.sddk.conflictclassreplacer.core.exception.PackageNotRecognizedException;
import com.sddk.conflictclassreplacer.core.file.ConflictFileUtil;
import com.sddk.conflictclassreplacer.core.file.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class FileModel {
    private File file;
    private String fileString;

    public FileModel(File file) throws IOException{
        this.file = file;
        this.fileString = FileHelper.readFileAsOneString(file.getAbsolutePath());
    }

    public String getImportPath() throws PackageNotRecognizedException {
        return ConflictFileUtil.getImportPathOfFile(this);
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

    public void getFileAsStringLines(Function<String,Void> line) {

    }
}
