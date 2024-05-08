package com.sddk.conflictclassreplacer.core.file;

import com.sddk.conflictclassreplacer.core.exception.PackageNotRecognizedException;
import com.sddk.conflictclassreplacer.core.model.FileModel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ConflictFileUtilTest {

    @Test
    public void getConflictFiles_isCorrect() {
/*
        try {
            List<ConflictFileModel> conflictFileList = ConflictFileUtil.getConflictFiles("/home/neo/test/conflict_test/local", "/home/neo/test/conflict_test/target");


            conflictFileList.forEach(conflictFileModel -> {
                System.out.println(conflictFileModel.getLocalFile());
                System.out.println(conflictFileModel.getTargetFile());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
    }

    @Test
    public void packagePathFromAbsoluteProjectPath_hasCorrectReturn() {
        String result = ConflictFileUtil.packagePathFromAbsoluteProjectPath("/home/neo/Programs/java/Programs/ConflicftClassReplacer/src/main/java/com/sddk/conflictclassreplacer");
        assert result.equals("com.sddk.conflictclassreplacer");
    }

    @Test
    public void getImportPath_isWorking() {
        File localFile = new File("/home/neo/Programs/java/Programs/ConflicftClassReplacer/src/main/java/com/sddk/conflictclassreplacer/core/model/ConflictFileModel.java");
        try {
            FileModel fileModel = new FileModel(localFile);
            String importPath = ConflictFileUtil.getImportPathOfFile(fileModel);
            System.out.println(importPath);
            assert importPath.equals("com.sddk.conflictclassreplacer.core.model");
        } catch (IOException | PackageNotRecognizedException e) {
            throw new RuntimeException(e);
        }
    }

}
