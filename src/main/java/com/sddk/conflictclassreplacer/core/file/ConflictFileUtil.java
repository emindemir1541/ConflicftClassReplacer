package com.sddk.conflictclassreplacer.core.file;

import com.sddk.conflictclassreplacer.core.exception.PackageNotRecognizedException;
import com.sddk.conflictclassreplacer.core.model.ConflictFileModel;
import com.sddk.conflictclassreplacer.core.model.FileModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ConflictFileUtil {

    public static List<ConflictFileModel> getConflictFiles(List<FileModel> localFiles, List<FileModel> targetFiles, Function<ConflictFileModel, Void> conflictFileModelFunc) throws IOException {

        ArrayList<ConflictFileModel> conflictFileList = new ArrayList<>();

        for (FileModel localFile : localFiles) {
            for (FileModel targetFile : targetFiles) {

                if (localFile.getFile().getName().equals(targetFile.getFile().getName())) {
                    ConflictFileModel conflictFileModel = new ConflictFileModel(targetFile, localFile);
                    conflictFileList.add(conflictFileModel);
                    conflictFileModelFunc.apply(conflictFileModel);
                }
            }
        }
        return conflictFileList;
    }


    public static void changeImports(List<FileModel> allLocalFiles, List<ConflictFileModel> conflictFileModels,Function<ConflictFileModel,Void> conflictFileModelFunc,Function<FileModel,Void> fileModelFunc) throws PackageNotRecognizedException, IOException {

        for (ConflictFileModel conflictFileModel : conflictFileModels) {
            conflictFileModelFunc.apply(conflictFileModel);

            for (FileModel fileModel : allLocalFiles) {
                if (fileModel.getFileString().contains(conflictFileModel.getLocalFile().getImportPath())) {
                    FileFinder.findAndReplace(fileModel, conflictFileModel.getLocalFile().getImportPath(), conflictFileModel.getTargetFile().getImportPath());
                    fileModelFunc.apply(fileModel);
                }
            }

        }

    }


    public static String packagePathFromAbsoluteProjectPath(String absoluteProjectPath) {
        String[] pathArray = absoluteProjectPath.split("/");

        int length = pathArray.length;


        String[] packagePathArray = new String[3];
        System.arraycopy(pathArray, length - 3, packagePathArray, 0, 3);


        return packagePathArray[0] + "." + packagePathArray[1] + "." + packagePathArray[2];
    }

    public static String getImportPathOfFile(FileModel fileModel) throws PackageNotRecognizedException {


        String[] lines = fileModel.getFileString().split("\\r?\\n");
        String importPath = null;

        for (String line : lines) {

            if (line.contains("package")) {
                importPath = line.replaceAll("package", "").replaceAll(" ", "").replaceAll(";", "");
                break;
            }
        }

        if (importPath == null) {
            throw new PackageNotRecognizedException("package could not found in file");
        }

        return importPath;

    }

    public static void deleteLocalFiles(List<ConflictFileModel> conflictFileModels, Function<FileModel,Void> fileModelFunc) {

        for (ConflictFileModel conflictFileModel : conflictFileModels) {
            conflictFileModel.getLocalFile().getFile().delete();
            fileModelFunc.apply(conflictFileModel.getLocalFile());
        }

    }

}
