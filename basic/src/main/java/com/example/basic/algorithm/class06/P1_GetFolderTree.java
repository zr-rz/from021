package com.example.basic.algorithm.class06;

import java.util.TreeMap;

/**
 * @author ryan
 * @date 2023/7/30 20:51
 */
public class P1_GetFolderTree {
    public static class Node {
        public String name;
        public TreeMap<String, Node> nextMap;

        public Node(String name) {
            this.name = name;
            nextMap = new TreeMap<>();
        }
    }

    public static void main(String[] args) {
        String[] paths = {"a\\b", "a\\bc", "d\\e", "d\\a"};
        Node node = generateFolderTree(paths);
        printProcess(node, 0);
    }

    public static Node generateFolderTree(String[] folderPaths) {
        Node head = new Node("");
        for (String foldPath : folderPaths) {
            String[] paths = foldPath.split("\\\\");
            Node cur = head;
            for (int i = 0; i < paths.length; i++) {
                if (!cur.nextMap.containsKey(paths[i])) {
                    cur.nextMap.put(paths[i], new Node(paths[i]));
                }
                cur = cur.nextMap.get(paths[i]);
            }
        }
        return head;
    }

    public static void printProcess(Node node, int level) {
        if (level != 0) {
            System.out.println(get2nSpace(level) + node.name);
        }
        for (Node next : node.nextMap.values()) {
            printProcess(next, level + 1);
        }
    }

    public static String get2nSpace(int n) {
        String res = "";
        for (int i = 0; i < n; i++) {
            res += "  ";
        }
        return res;
    }
}
