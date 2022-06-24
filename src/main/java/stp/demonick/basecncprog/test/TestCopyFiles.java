package stp.demonick.basecncprog.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;

public class TestCopyFiles {

    public void copyAllFiles(String prtFileName, String cncDir, String detailNumber, String operationName, String machineName) throws IOException {
        String mainPath = "D:\\work\\BaseCNC";
        Path originalPrtPath = Paths.get(prtFileName);
        Path originalCNCDir = Paths.get(cncDir);
        Path newPrtDir = Paths.get(mainPath, detailNumber, operationName, machineName,
                getPartName(originalPrtPath.getFileName()), "Part_Man");
        Path newCNCDir = Paths.get(mainPath, detailNumber, operationName, machineName,
                getPartName(originalPrtPath.getFileName()), "CNC");
        Files.createDirectories(newPrtDir);
        Files.createDirectories(newCNCDir);
        copyFiles(originalPrtPath.getParent(), newPrtDir);
        copyFiles(originalCNCDir, newCNCDir);

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

    public void deleteDirectory(String directory) throws IOException {
        Path path = Path.of(directory);
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public static void main(String[] args) throws IOException {
/*        String s = "D:\\work\\stpProgs\\M6-AP2TM_04.02.01\\New\\OP80\\Part_Man\\M6-AP2TM_04.02.01_DOZATOR_Man_OP80_FREZAD80_SPINNERU620.prt";
        String s1 = "D:\\work\\stpProgs\\M6-AP2TM_04.02.01\\New\\OP80\\CNC\\3_UST\\";
        String name = "M6-AP2TM_04.02.01";
        String operationName = "OP 30";
        String machineName = "Hizion";
        String endFolderPrt = "Part_Man";
        String endFolderCnc = "CNC";
        TestCopyFiles copyFiles = new TestCopyFiles();
        copyFiles.copyAllFiles(s, s1, name, operationName, machineName);*/
    }
}
