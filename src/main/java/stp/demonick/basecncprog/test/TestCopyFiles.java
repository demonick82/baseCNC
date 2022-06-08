package stp.demonick.basecncprog.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class TestCopyFiles {
    private final String MAIN_PATH = "D:\\work\\BaseCNC";

    public void copyPrtFiles(String fileName, String detailNumber, String operationName, String machineName,
                             String endFolderName) throws IOException {
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

    public void copyCNCFiles(String fileName, String detailNumber, String operationName, String machineName,
                             String endFolderName) throws IOException {
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

    private void copyFiles(Path oldPath, Path newPath) {

    }

    private String getPartName(Path path) {
        String fullName = path.getFileName().toString();
        return fullName.substring(0, fullName.lastIndexOf("."));
    }

    public static void main(String[] args) throws IOException {
        String s = "D:\\work\\stpProgs\\M6-AP2TM_04.02.01\\New\\OP80\\Part_Man\\M6-AP2TM_04.02.01_DOZATOR_Man_OP80_FREZAD80_SPINNERU620.prt";
        String name = "M6-AP2TM_04.02.01";
        String operationName = "OP 30";
        String machineName = "Hizion";
        String endFolder = "Part_Man";
        TestCopyFiles copyFiles = new TestCopyFiles();
        copyFiles.copyPrtFiles(s, name, operationName, machineName, endFolder);
    }
}
