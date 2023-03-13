package com.example.algorithm.zuochengyun.high.class1;

import java.util.Arrays;

/**
 * @author ryan
 * @date 2023/3/8 11:15
 */
public class BuyApple {
    public static void main(String[] args) {
        BuyApple buyApple = new BuyApple();
        int[] buy = buyApple.buy(100);
        System.out.println(Arrays.toString(buy));
    }

    public int[] buy(int num) {
        int[] result = new int[2];
        int bigBagNum = num / 8;
        while (bigBagNum >= 0) {
            if ((num - bigBagNum * 8) % 6 == 0) {
                result[0] = bigBagNum;
                result[1] = (num - bigBagNum * 8) / 6;
                return result;
            }
            bigBagNum--;
        }
        return result;
    }
}
