package com.example.algorithm;

import com.example.algorithm.others.TimeSection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AlgorithmApplicationTests {

    @Autowired
    TimeSection timeSection;
    @Test
    void contextLoads() {
        System.out.println(timeSection.getName());
    }

}
