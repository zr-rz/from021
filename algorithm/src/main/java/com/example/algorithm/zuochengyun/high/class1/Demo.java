package com.example.algorithm.zuochengyun.high.class1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ryan
 * @date 2023/3/12 13:37
 */
public class Demo {
    Map<Character, String> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        char[] chars = digits.toCharArray();
        List<String> res = new ArrayList<>();
        process(res, chars, 0, new StringBuilder());
        return res;
    }

    public void process(List<String> res, char[] chars, int index, StringBuilder path) {
        if (index == chars.length) {
            res.add(path.toString());
            return;
        }
        StringBuilder sb = new StringBuilder(path);
        int p = map.get(chars[index]).length();
        for (int i = 0; i < p; i++) {
            sb.append(map.get(chars[index]).charAt(i));
            process(res, chars, index + 1, sb);
            sb.deleteCharAt(index);
        }
    }
}
