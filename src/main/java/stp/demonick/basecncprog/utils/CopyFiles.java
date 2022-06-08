package stp.demonick.basecncprog.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class CopyFiles {

    public void copyPrtFiles(String fileName, String detailNumber, String operationName, String machineName,
                             String endFolderName) throws IOException {
        String MAIN_PATH = "D:\\work\\BaseCNC";
        Path originalPath = Paths.get(fileName);
        Path newDir = Paths.get(MAIN_PATH, detailNumber, operationName, machineName,
                getPartName(originalPath.getFileName()), endFolderName);
        Files.createDirectories(newDir);
        File[] files = new File(originalPath.getParent().toString()).listFiles();
        if (files != null) {
            for (File file1 : files) {
                Files.copy(file1.toPath(), newDir.resolve(file1.getName()), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }


    private String getPartName(Path path) {
        String fullName = path.getFileName().toString();
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

}
