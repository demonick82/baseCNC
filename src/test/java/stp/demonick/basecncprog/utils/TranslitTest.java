package stp.demonick.basecncprog.utils;

import org.junit.Assert;
import org.junit.Test;

public class TranslitTest {

    @Test
    public void getTranslit() {
        Translit translit = new Translit();
        String test = "UPRD";
        Assert.assertEquals(translit.setLatin("УПРД"),test);
    }
}