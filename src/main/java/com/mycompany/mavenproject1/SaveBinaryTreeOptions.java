
package com.mycompany.mavenproject1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

class SaveBinaryTreeOptions extends JFrame implements ActionListener {
    private JRadioButton preOrderRadioButton, inOrderRadioButton, postOrderRadioButton;
    private JButton printButton;
    private BinaryTree binaryTree;
    private BTNode binaryTreeRoot;

    public void setBinaryTree(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    public void setBinaryTreeRoot(BTNode binaryTreeRoot) {
        this.binaryTreeRoot = binaryTreeRoot;
    }

    public SaveBinaryTreeOptions() {
        super("Save Binary Tree to File");
        setLayout(new FlowLayout());

        // خيارات الطباعة باستخدام JCheckBox
        preOrderRadioButton = new JRadioButton("Pre-order");
        inOrderRadioButton = new JRadioButton("In-order");
        postOrderRadioButton = new JRadioButton("Post-order");
        customizeCheckBox(preOrderRadioButton);
        customizeCheckBox(inOrderRadioButton);
        customizeCheckBox(postOrderRadioButton);

        add(preOrderRadioButton);
        add(inOrderRadioButton);
        add(postOrderRadioButton);

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

    private void printToFileMethod(String str, String fileName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");

        // تعيين اسم افتراضي للملف
        fileChooser.setSelectedFile(new File(fileName + ".txt"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (fileToSave.getName().endsWith(".txt")) {
                Methods.exportBTToFile(str, fileToSave);
                String[] options = { "OK" };
                JOptionPane.showOptionDialog(null, "File Saved Successfully: " + fileToSave.getName(), "Save File",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            } else if (!fileToSave.getName().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                Methods.exportBTToFile(str, fileToSave);
                String[] options = { "OK" };
                JOptionPane.showOptionDialog(null, "File Saved Successfully: " + fileToSave.getName(), "Save File",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == printButton) {
            if (preOrderRadioButton.isSelected()) {
                printToFileMethod(binaryTree.getPreOrder(binaryTreeRoot), "preorder_bt");
            } else if (inOrderRadioButton.isSelected()) {
                printToFileMethod(binaryTree.getInOrder(binaryTreeRoot), "inorder_bt");
            } else if (postOrderRadioButton.isSelected()) {
                printToFileMethod(binaryTree.getPostOrder(binaryTreeRoot), "postorder_bt");
            }
        }
    }
}
