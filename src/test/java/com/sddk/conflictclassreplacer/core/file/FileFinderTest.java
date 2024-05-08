package com.sddk.conflictclassreplacer.core.file;


import com.sddk.conflictclassreplacer.core.model.ConflictFileModel;
import com.sddk.conflictclassreplacer.core.model.FileModel;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileFinderTest {

    @Test
    public void getFilesUnderFolder_areFilesCorrect() {

        try {
            for (FileModel file : FileFinder.getFilesUnderFolder("/home/neo/Programs/java/Programs/ConflicftClassReplacer/src/main/java/com/sddk/conflictclassreplacer")) {
                System.out.println(file.getFile().getPath());
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void findAndReplace() {

        File localFile = new File("/home/neo/test/conflict_test/local/ConflictFileModel.java");
        File targetFile = new File("/home/neo/Programs/java/Programs/ConflicftClassReplacer/src/main/java/com/sddk/conflictclassreplacer/core/model/ConflictFileModel.java");
/*
        ConflictFileModel conflictFileModel = new ConflictFileModel(targetFile,localFile);

        try {

            FileFinder.findAndReplace(localFile,"com.sddk.conflictclassreplacer.core.file.FileHelper;","org.core.file.FileHelper;");
        } catch (Exception e) {
            System.out.println(e);
        }
*/
    }

}
