package stp.demonick.basecncprog.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import stp.demonick.basecncprog.model.*;
import stp.demonick.basecncprog.model.tools.MillingTool;
import stp.demonick.basecncprog.model.tools.Tool;

import java.io.File;
import java.io.IOException;

public class TestGson {
    public static void main(String[] args) throws IOException {
        String filename = "sourse\\jsonTest";
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        Operation operation = new Operation();
/*        Tool tool1 = new MillingTool("freza20",  20.0, 50, 0.5,
                20, 2, 1);
        Operation operation1 = new Operation("cavity", "cavity_moll", 2500.0,
                500.0, 0.2, 0.2, 2.4545);
        operation1.setTool(tool1);
        Tool tool2 = new MillingTool("freza_16",  16.0, 50, 0.5,
                20, 2, 1);
        Operation operation2 = new Operation("planar", "planar", 2500.0,
                500.0, 0.2, 0.2, 2.4545);
        operation2.setTool(tool2);
        Program program = new Program("1_UST");
        program.addOperation(operation1);
        program.addOperation(operation2);
        Detail model = new Detail("dosator", "C:\\1.prt");
        model.addProgram(program);
        String s1 = mapper.writeValueAsString(model);
        System.out.println(s1);*/
/*
        Model model1 = mapper.readValue(s1, Model.class);
        System.out.println(model1);
*/
        Detail model3 = mapper.readValue(new File(filename), Detail.class);
        System.out.println(model3);
    }
}
