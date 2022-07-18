package stp.demonick.basecncprog.utils;

import com.ibm.icu.text.Transliterator;
import org.springframework.stereotype.Component;

@Component
public class Translit {
    private final Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");

    public String setLatin(String s) {
        return toLatinTrans.transliterate(s);
    }
}
