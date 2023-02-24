package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        String str = "aaabbbb";
        char[] chars = str.toCharArray();
        Set<Character> set = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (char item : chars) {
            if (!set.contains(item)) {
                stringBuilder.append(item);
                set.add(item);
            }
        }
        System.out.println(stringBuilder.toString());
    }

}
