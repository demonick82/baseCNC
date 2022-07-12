package stp.demonick.basecncprog.utils;

import com.ibm.icu.text.Transliterator;

public class Translit {


    public static void main(String[] args) {
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        String s = "колбаса";
        System.out.println(toLatinTrans.transliterate(s));
    }
}
