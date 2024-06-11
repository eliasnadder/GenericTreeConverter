package com.mycompany.mavenproject1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Methods {
    public static BinaryTree newBinaryTree(Node node) {
        BinaryTree tree = new BinaryTree();
        tree.setRoot(convertToBinaryTree(node));
        return tree;
    }

    // تحويل الشجرة المعممة إلى شجرة ثنائية
    public static BTNode convertToBinaryTree(Node root) {
        if (root == null) {
            return null;
        }

        BTNode binaryRoot = new BTNode(root.getData());

        List<Node> children = root.getChildren();
        if (!children.isEmpty()) {
            // الابن الأيمن هو أول ابن من اليمين في الشجرة المعممة
            binaryRoot.setRight(convertToBinaryTree(children.get(children.size() - 1)));

            BTNode sibling = binaryRoot.getRight();

            // باقي الأبناء كأخوة من اليمين إلى اليسار
            for (int i = children.size() - 2; i >= 0; i--) {
                sibling.setLeft(convertToBinaryTree(children.get(i)));
                sibling = sibling.getLeft();
            }
        }
        return binaryRoot;
    }

    public static void printBinaryTree(BTNode root, String indent) {
        if (root == null) {
            return;
        }
        System.out.println(indent + root.getData());
        if (root.getRight() != null) {
            printBinaryTree(root.getRight(), indent + "  ");
        }
        if (root.getLeft() != null) {
            printBinaryTree(root.getLeft(), indent + "  ");
        }
    }

    public static Node convertToGeneralTree(BTNode binaryRoot) {
        if (binaryRoot == null) {
            return null;
        }

        Node root = new Node(binaryRoot.getData());
        ArrayList<Node> children = new ArrayList<>();

        if (binaryRoot.getLeft() != null) {
            children.add(convertToGeneralTree(binaryRoot.getLeft()));
        }

        if (binaryRoot.getRight() != null) {
            children.add(convertToGeneralTree(binaryRoot.getRight()));
        }

        root.setChildren(children);

        return root;
    }

    public static void exportBTToFile(String str, File filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(str);
            System.out.println("Exported successfully to file: " + filename);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportGTToFile(Node root, File filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            exportToFile(root, writer);
            System.out.println("Exported successfully to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exportToFile(Node root, BufferedWriter writer) throws IOException {
        if (root.hasChildren()) {
            writer.write(root.getData() + " -> ");
            int numChildren = root.getChildren().size();
            for (int i = 0; i < numChildren; i++) {
                writer.write(root.getChildren().get(i).getData());
                if (i < numChildren - 1) {
                    writer.write(", ");
                }
            }
            writer.newLine();
        }
        for (Node child : root.getChildren()) {
            exportToFile(child, writer);
        }
    }
}