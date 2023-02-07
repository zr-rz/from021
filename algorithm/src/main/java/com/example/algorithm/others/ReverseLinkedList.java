package com.example.algorithm.others;

import java.util.Hashtable;

public class ReverseLinkedList {
    public static Node reverse(Node head) {
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur.next != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return cur;
    }

    public static Node reverseRe(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node reverse = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }

    public static void printNodeVal(Node head) {
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.val);
                break;
            }
            System.out.print(head.val + "-");
            head = head.next;
        }
        System.out.println();
    }

    public static int test() {
        try {
            int i = 1 / 0;
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }
}
