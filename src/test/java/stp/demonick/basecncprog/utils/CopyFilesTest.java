package stp.demonick.basecncprog.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CopyFilesTest {
    private final CopyFiles copyFiles = new CopyFiles();

    private final String modelPath = "D:\\Work\\stp\\M6-AP2TM_04.02.01\\New\\osnastka\\M6-AP2TM_04.02.01_full.prt";
    private final String username = "polyanskiy";
    private final String detailNumber = "M6-AP2TM_04.02.01";
    private final String operationName = "OP85";
    private final String machineName = "Wele AA1165";
    private final String cncDir = "D:\\Work\\stp\\M6-AP2TM_04.02.01\\New\\test\\";

    @Test
    public void copyPrtFiles() throws IOException {
        String path = copyFiles.copyPrtFiles(modelPath, username, detailNumber, operationName, machineName);
        String testPath = "D:\\work\\BaseCNC\\M6-AP2TM_04.02.01\\polyanskiy\\OP85\\Wele AA1165\\M6-AP2TM_04.02.01_full\\Part_Man";
        Assert.assertEquals(path, testPath);
    }

    @Test
    public void copyCNCFiles() throws IOException {
        String path = copyFiles.copyCNCFiles(modelPath, username,cncDir, detailNumber, operationName, machineName);
        String testPath = "D:\\work\\BaseCNC\\M6-AP2TM_04.02.01\\polyanskiy\\OP85\\Wele AA1165\\M6-AP2TM_04.02.01_full\\CNC";
        Assert.assertEquals(path, testPath);
    }
}