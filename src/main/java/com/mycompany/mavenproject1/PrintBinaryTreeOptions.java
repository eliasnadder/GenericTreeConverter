
package com.mycompany.mavenproject1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

class PrintBinaryTreeOptions extends JFrame implements ActionListener {
    private JRadioButton preOrderRadioButton, inOrderRadioButton, postOrderRadioButton, levelOrderRadioButton,
            drawingTreeRadioButton;
    private JButton printButton;
    private BinaryTree binaryTree;
    private BTNode binaryTreeRoot;

    public void setBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    public void setBinaryTreeRoot(BTNode binaryTreeRoot) {
        this.binaryTreeRoot = binaryTreeRoot;
    }

    public PrintBinaryTreeOptions() {
        super("Print Binary Tree Options");
        setLayout(new FlowLayout());

        // خيارات الطباعة باستخدام JCheckBox
        preOrderRadioButton = new JRadioButton("Pre-order");
        inOrderRadioButton = new JRadioButton("In-order");
        postOrderRadioButton = new JRadioButton("Post-order");
        levelOrderRadioButton = new JRadioButton("Level-order");
        drawingTreeRadioButton = new JRadioButton("Drawing Tree");
        customizeCheckBox(preOrderRadioButton);
        customizeCheckBox(inOrderRadioButton);
        customizeCheckBox(postOrderRadioButton);
        customizeCheckBox(levelOrderRadioButton);
        customizeCheckBox(drawingTreeRadioButton);

        add(preOrderRadioButton);
        add(inOrderRadioButton);
        add(postOrderRadioButton);
        add(levelOrderRadioButton);
        add(drawingTreeRadioButton);

        // زر لطباعة الشجرة الثنائية
        printButton = new JButton("Print");
        customizeButton(printButton);
        printButton.addActionListener(this);
        add(printButton);

        setSize(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(64, 132, 124));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(200, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setBorderPainted(false);
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                ((AbstractButton) c).setOpaque(false);
            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                super.paint(g2, c);
                g2.dispose();
            }
        });
    }

    private void customizeCheckBox(JRadioButton checkBox) {
        checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
        checkBox.setBackground(Color.WHITE);
        checkBox.setPreferredSize(new Dimension(200, 30));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == printButton) {
            ArrayList<String> binaryTreeContent = new ArrayList<>();
            if (preOrderRadioButton.isSelected()) {
                binaryTreeContent.add("Pre-order:");
                binaryTree.getPreOrder(binaryTreeRoot, binaryTreeContent);
                try {
                    OutputWindow outputWindow = new OutputWindow(binaryTreeContent.get(0), binaryTreeContent);
                    outputWindow.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (inOrderRadioButton.isSelected()) {
                binaryTreeContent.add("In-order:");
                binaryTree.getInOrder(binaryTreeRoot, binaryTreeContent);
                try {
                    OutputWindow outputWindow = new OutputWindow(binaryTreeContent.get(0), binaryTreeContent);
                    outputWindow.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (postOrderRadioButton.isSelected()) {
                binaryTreeContent.add("Post-order:");
                binaryTree.getPostOrder(binaryTreeRoot, binaryTreeContent);
                try {
                    OutputWindow outputWindow = new OutputWindow(binaryTreeContent.get(0), binaryTreeContent);
                    outputWindow.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (levelOrderRadioButton.isSelected()) {
                binaryTreeContent.add("Level-order:");
                binaryTree.getLevelOrder(binaryTreeRoot, binaryTreeContent);
                try {
                    OutputWindow outputWindow = new OutputWindow(binaryTreeContent.get(0), binaryTreeContent);
                    outputWindow.setVisible(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (drawingTreeRadioButton.isSelected()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new JFrame("Drawing Binary Tree");
                        // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setSize(600, 400);

                        DrawingBinaryTree treePanel = new DrawingBinaryTree(binaryTree);
                        frame.add(treePanel);
                        frame.setVisible(true);
                    }
                });
            }
        }
    }
}
