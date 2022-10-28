package com.shpp.p2p.cs.lmatata.assignment7;

import com.shpp.cs.a.simple.SimpleProgram;

public class Test2 extends SimpleProgram {
    static String test = "Sam 58 69 99 131 168 236 278 380 467 408 466 997";

    public static void main(String[] args) {
        NameSurferEntry entry = new NameSurferEntry(test);
        System.out.println("Name: " + entry.getName());
        System.out.println("Decade: " + entry.getRank(0));
        System.out.println(entry);
    }
}
