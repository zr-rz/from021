package com.example.algorithm.zuochengyun.high.class1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author ryan
 * @date 2023/3/8 17:03
 */
public class RandomNum {
    public int a;
    public static void main(String[] args) {
        RandomNum randomNum = new RandomNum();
        Demo demo = new Demo();
        List<String> strings = demo.letterCombinations("23");
        System.out.println(Arrays.toString(strings.toArray(new String[0])));
        /*RandomNum randomNum = new RandomNum();
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            int i1 = randomNum.r1to7();
            if (i1 == 6) {
                count++;
            }
        }
        System.out.println(count / 10000.0);*/

    }

    public int r1to5() {
        return (int) (Math.random() * 5) + 1;
    }

    public int r0to1() {
        int res;
        do {
            res = r1to5();
        } while (res == 3);
        return res < 3 ? 0 : 1;
    }

    public int r1to7() {
        int res;
        do {
            res = (r0to1() << 2) + (r0to1() << 1) + r0to1();
        } while (res == 7);
        return res + 1;
    }
}
