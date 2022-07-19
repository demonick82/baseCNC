package stp.demonick.basecncprog.utils;

import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class StartPath {
    public String loadStartPath() {
        String rootPath = Thread.currentThread().getContextClassLoader()
                .getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(appConfigPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties.getProperty("startPath");
    }
}
