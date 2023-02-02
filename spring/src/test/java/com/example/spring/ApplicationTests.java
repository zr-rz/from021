package com.example.spring;

import com.example.spring.proxy.Man;
import com.example.spring.proxy.Person;
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
    void jdkProxyTest() {
        Man man = new Man();
        Person proxyInstance = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object result = method.invoke(man, args);
                System.out.println("after");
                return result;
            }
        });
        proxyInstance.say("zhang");
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
