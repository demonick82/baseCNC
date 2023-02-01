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

    private final StartPath startPath;

    public CopyFiles(StartPath startPath) {
        this.startPath = startPath;
    }

    public String createNewDir(String prtFileName, String username, String detailNumber, String operationName, String machineName, String folderName) {
        Path originalPrtPath = Paths.get(prtFileName);
        Path newPrtDir = Paths.get(startPath.loadStartPath(), detailNumber, username, operationName, machineName,
                getPartName(originalPrtPath.getFileName()), folderName);
        return newPrtDir.toString();
    }

    public void copyNewFiles(String oldModelsDir, String oldCncDir, String newModelDir, String newCncDir) {
        try {
            Path newModelPath = Path.of(newModelDir);
            Path newCncPath = Path.of(newCncDir);
            Path oldModelPath = Path.of(oldModelsDir).getParent();
            Path oldCncPath = Path.of(oldCncDir);

            Files.createDirectories(newModelPath);
            Files.createDirectories(newCncPath);
            copyFiles(oldModelPath, newModelPath);
            copyFiles(oldCncPath, newCncPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
