package com.example.algorithm;

import com.example.algorithm.others.Node;
import com.example.algorithm.others.ReverseLinkedList;
import com.example.algorithm.others.Singleton;
import com.example.algorithm.others.TimeSection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class AlgorithmApplicationTests {

    @Autowired
    TimeSection timeSection;
    @Test
    void contextLoads() {
        System.out.println(timeSection.getName());
    }

    /**
     * 反转链表
     */
    @Test
    void testReverse() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        ReverseLinkedList.printNodeVal(node1);
        Node newHead = ReverseLinkedList.reverseRe(node1);
        ReverseLinkedList.printNodeVal(newHead);
    }

}
