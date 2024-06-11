package com.mycompany.mavenproject1;

import java.io.*;
import java.util.*;

public class GenericTree {
    private Node root;

    public GenericTree(String data) {
        this.root = new Node(data);
    }

    public GenericTree() {
        root = new Node();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String printGenericTree(Node root) {
        String str = "";
        if (root == null) {
            return "";
        }
        str += root.getData(); // Print node data
        for (Node child : root.getChildren()) {
            str += printGenericTree(child); // Recursively print each child node
        }
        return str;
    }

    public static void importTreeFromFile(GenericTree tree, File file) throws IOException {
        Map<String, Node> nodeMap = new HashMap<>(); // تعديل: استخدم خريطة لتتبع العقد

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                if (parts.length == 2) {
                    String parentName = parts[0].trim();
                    String[] childNames = parts[1].split(",");
                    Node parentNode = nodeMap.computeIfAbsent(parentName, Node::new);

                    for (String childName : childNames) {
                        Node childNode = nodeMap.computeIfAbsent(childName.trim(), Node::new);
                        parentNode.setChildren(childNode);
                    }
                }
            }
        }
        tree.setRoot(nodeMap.get("A")); // تعديل: تعيين العقدة الجذرية
    }

    public static void printTreeFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(line); // طباعة كل سطر يمثل عقدة في الشجرة
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
