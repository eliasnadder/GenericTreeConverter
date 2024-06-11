package com.mycompany.mavenproject1;

import java.util.ArrayList;

class Node {
    private String data;
    private ArrayList<Node> children;
    private Node parent; // إضافة حقل للوالد

    public Node(String data) {
        this.data = data;
        this.children = new ArrayList<>();
        this.parent = null; // بداية لا يوجد والد
    }

    public Node() {
        data = null;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(Node node) {
        node.setParent(this); // تعيين الوالد عند إضافة عقدة فرعية
        children.add(node);
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void setChildren(String data) {
        Node node = new Node(data);
        node.setParent(this);
        children.add(node);
    }

    public void removeChildren(Node node) {
        children.remove(node);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}