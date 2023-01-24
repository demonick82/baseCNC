package stp.demonick.basecncprog.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TranslitTest {

    @Autowired
    private Translit translit;


    @Test
    public void getTranslit() {
        String test = "JU";
        assertEquals(translit.setLatin("Ю"), test);
    }
}