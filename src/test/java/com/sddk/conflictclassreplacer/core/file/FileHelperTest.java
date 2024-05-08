package com.sddk.conflictclassreplacer.core.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileHelperTest {

    @Test
    public void readFileAsOneString() {
        try {
            var file = FileHelper.readFileAsOneString("/home/neo/Programs/java/Programs/ConflicftClassReplacer/src/main/java/com/sddk/conflictclassreplacer/core/model/ConflictFileModel.java");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
