/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class OutputWindow extends JFrame {

    JTextArea outputArea;

    public OutputWindow(String title) {
        setTitle(title);
        setSize(300, 300); // تعديل حجم النافذة ليتناسب مع حجم الصورة
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false); // جعل منطقة النص غير قابلة للتعديل
        outputArea.setMargin(new Insets(10, 10, 10, 10)); // إضافة حواف بيضاء حول منطقة النص

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(scrollPane, BorderLayout.CENTER);
    }

    public OutputWindow(String title, String line) {
        setTitle(title);
        setSize(300, 300); // تعديل حجم النافذة ليتناسب مع حجم الصورة
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false); // جعل منطقة النص غير قابلة للتعديل
        outputArea.setMargin(new Insets(10, 10, 10, 10)); // إضافة حواف بيضاء حول منطقة النص
        outputArea.append(line);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(scrollPane, BorderLayout.CENTER);
    }

    public OutputWindow(String title, ArrayList<String> text) throws IOException {
        setTitle(title);
        setSize(300, 300); // تعديل حجم النافذة ليتناسب مع حجم الصورة
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false); // جعل منطقة النص غير قابلة للتعديل
        outputArea.setMargin(new Insets(10, 10, 10, 10)); // إضافة حواف بيضاء حول منطقة النص

        for (String line : text) {
            outputArea.append(line + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(scrollPane, BorderLayout.CENTER);
    }
}
