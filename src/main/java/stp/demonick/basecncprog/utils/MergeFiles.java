package stp.demonick.basecncprog.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MergeFiles {
    public void mergeFiles(String path) {
        File[] files = new File(path).listFiles();
        ArrayList<String> lines = new ArrayList<>();
        Pattern pattern = Pattern.compile("O\\d{4}");
        Path newPath = Paths.get(String.valueOf(Paths.get(path)), "allFiles");
        if (files != null) {
            lines.add("%");
            for (File file : files) {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.matches()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        while (br.ready()) {
                            String line = br.readLine().replace("%", "");
                            if (!line.equals("")) {
                                lines.add(line);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            lines.add("%");
            try {
                Files.write((newPath), lines);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        MergeFiles mergeFiles = new MergeFiles();
        mergeFiles.mergeFiles("C:\\Users\\stpuser11\\Desktop\\Новая папка (2)");
    }
}
