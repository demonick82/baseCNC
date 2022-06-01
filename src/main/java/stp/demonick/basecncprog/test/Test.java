package stp.demonick.basecncprog.test;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {
        AtomicInteger modelId = new AtomicInteger(0);
        System.out.println(modelId.addAndGet(1));
    }
}
