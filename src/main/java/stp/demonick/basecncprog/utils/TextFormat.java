package stp.demonick.basecncprog.utils;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class TextFormat {

    public String updateJson(StringBuilder json) {
        json.deleteCharAt(json.lastIndexOf(","));
        return json.toString().replace("\\", "\\\\");
    }


}
