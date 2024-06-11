/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private BTNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(String data) {
        root = new BTNode(data);
    }

    public BTNode getRoot() {
        return root;
    }

    public void setRoot(BTNode root) {
        this.root = root;
    }

    // public void addLeaf(String name) {
    // if (root == null) {
    // root = new BTNode(name);
    // } else {
    // BTNode newNode = new BTNode(name);
    // addLeafToTree(root, newNode);
    // }
    // }

    // private void addLeafToTree(BTNode root, BTNode newNode) {
    // if (root.left == null) {
    // root.left = newNode;
    // } else if (root.right == null) {
    // root.right = newNode;
    // } else {
    // // Add the node to the side with fewer nodes for simplicity
    // int leftCount = countNodes(root.left);
    // int rightCount = countNodes(root.right);
    // if (leftCount <= rightCount) {
    // addLeafToTree(root.left, newNode);
    // } else {
    // addLeafToTree(root.right, newNode);
    // }
    // }
    // }

    // private int countNodes(BTNode node) {
    // if (node == null)
    // return 0;
    // return 1 + countNodes(node.left) + countNodes(node.right);
    // }

    public void addLeaf(String name) {
        if (root == null) {
            root = new BTNode(name);
        } else {
            addLeaf(root, name);
        }
    }

    private void addLeaf(BTNode node, String name) {
        if (node.getLeft() == null) {
            node.setLeft(new BTNode(name));
        } else if (node.getRight() == null) {
            node.setRight(new BTNode(name));
        } else {
            if (node.getLeft().getData().compareTo(name) > 0) {
                addLeaf(node.getLeft(), name);
            } else {
                addLeaf(node.getRight(), name);
            }
        }
    }

    public void getPreOrder(BTNode node, ArrayList<String> result) {
        if (node != null) {
            result.add(String.valueOf(node.getData()));
            getPreOrder(node.getRight(), result);
            getPreOrder(node.getLeft(), result);
        }
    }

    public String getPreOrder(BTNode node) {
        String str = "";
        if (node != null) {
            str += String.valueOf(node.getData()) + " ";
            str += getPreOrder(node.getRight());
            str += getPreOrder(node.getLeft());
        }
        return str;
    }

    public void getInOrder(BTNode node, ArrayList<String> result) {
        if (node != null) {
            getInOrder(node.getRight(), result);
            result.add(String.valueOf(node.getData()));
            getInOrder(node.getLeft(), result);
        }
    }

    public String getInOrder(BTNode node) {
        String str = "";
        if (node != null) {
            str += getInOrder(node.getRight());
            str += String.valueOf(node.getData()) + " ";
            str += getInOrder(node.getLeft());
        }
        return str;
    }

    public void getPostOrder(BTNode node, ArrayList<String> result) {
        if (node != null) {
            getPostOrder(node.getRight(), result);
            getPostOrder(node.getLeft(), result);
            result.add(String.valueOf(node.getData()));
        }
    }

    public String getPostOrder(BTNode node) {
        String str = "";
        if (node != null) {
            str += getPostOrder(node.getRight());
            str += getPostOrder(node.getLeft());
            str += String.valueOf(node.getData()) + " ";
        }
        return str;
    }

    public void getLevelOrder(BTNode node, ArrayList<String> result) {
        if (node != null) {
            Queue<BTNode> queue = new LinkedList<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                BTNode current = queue.poll();
                result.add(String.valueOf(current.getData()));
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
            }
        }
    }
}
