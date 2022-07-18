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
/*
    MAY BE REFACTOR!!!!!!
*/
    private final String mainPath = "D:\\work\\BaseCNC";

    public String copyPrtFiles(String prtFileName, String username, String detailNumber, String operationName, String machineName) throws IOException {
        Path originalPrtPath = Paths.get(prtFileName);
        Path newPrtDir = Paths.get(mainPath, detailNumber, username, operationName, machineName,
                getPartName(originalPrtPath.getFileName()), "Part_Man");
        Files.createDirectories(newPrtDir);
        copyFiles(originalPrtPath.getParent(), newPrtDir);
        return newPrtDir.toString();
    }

    public String copyCNCFiles(String prtFileName, String username, String cncDir, String detailNumber, String operationName, String machineName) throws IOException {
        Path originalPrtPath = Paths.get(prtFileName);
        Path originalCNCDir = Paths.get(cncDir);
        Path newCNCDir = Paths.get(mainPath, detailNumber, username, operationName, machineName,
                getPartName(originalPrtPath.getFileName()), "CNC");
        Files.createDirectories(newCNCDir);
        copyFiles(originalCNCDir, newCNCDir);
        return newCNCDir.toString();
    }


    private void copyFiles(Path oldPath, Path newPath) {
        File[] files = new File(oldPath.toString()).listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.isFile()) {
                    try {
                        Files.copy(file1.toPath(), newPath.resolve(file1.getName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String getPartName(Path path) {
        String fullName = path.getFileName().toString();
        return fullName.substring(0, fullName.lastIndexOf("."));
    }
}
