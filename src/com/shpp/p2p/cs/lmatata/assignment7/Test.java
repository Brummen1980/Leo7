package com.shpp.p2p.cs.lmatata.assignment7;

import com.shpp.cs.a.console.TextProgram;

import java.util.Arrays;

public class Test extends TextProgram {
    public static final int NDECADES = 12;
    private static int[] decadeArray = new int[NDECADES];

    public static void main(String[] args) {
        String line2 = "Aaron 193 208 218 274 279 232 132 36 32 31 41 77";
        String[] res = line2.split(" ");
        System.out.println(res.length);
        System.out.println(Arrays.toString(res));
        String name = line2.substring(0, line2.indexOf(" "));
        String[] numbers = line2.substring(line2.indexOf(" ") + 1).split(" ");
        System.out.println(numbers.length);
        System.out.println(Arrays.toString(numbers));
        System.out.println(name);
        for (int i = 0; i < NDECADES; i++) {
            decadeArray[i] = Integer.parseInt(numbers[i]);
            System.out.println(decadeArray[i]);
        }
        StringBuilder showRes = new StringBuilder();
        for(int i = 0 ; i < NDECADES; i++){
             showRes.append(" ").append(decadeArray[i]);
        }

        String resR = name + showRes;
        System.out.println(resR);
    }
}
