package com.mycompany.mavenproject1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class ImportFromFileGUI extends JFrame implements ActionListener {

    JTextArea outputArea;
    JButton convertButton, printBinaryTreeButton, exportToFileButton, backButton;
    JPanel buttonPanel, backButtonPanel, topPanel;
    JCheckBox outputFileCheckBox, outputGenericTreeCheckBox;
    String content;
    File selectedFile;
    private BTNode binaryRoot;
    private Node generalTreeRoot;
    private BinaryTree binaryTree;

    public void setContent(String content) {
        this.content = content;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public ImportFromFileGUI() {
        setTitle("Import from file");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon backIcon = new ImageIcon("Icons\\arrow_back_icon.png");
        Image backImage = backIcon.getImage();
        Image scaledBackImage = backImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(scaledBackImage);
        backButton = new JButton(backIcon);
        backButton.addActionListener(this);
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        outputArea = new JTextArea(10, 20);
        outputArea.setEditable(false);
        // outputArea.setText(content);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        buttonPanel = new JPanel();
        convertButton = new JButton("Convert To Binary Tree");
        convertButton.addActionListener(this);
        customizeButton(convertButton);
        buttonPanel.add(convertButton);

        printBinaryTreeButton = new JButton("Print Binary Tree");
        printBinaryTreeButton.addActionListener(this);
        printBinaryTreeButton.setVisible(false);
        customizeButton(printBinaryTreeButton);
        buttonPanel.add(printBinaryTreeButton);

        exportToFileButton = new JButton("Export To File");
        exportToFileButton.setVisible(false);
        exportToFileButton.addActionListener(this);
        customizeButton(exportToFileButton);
        buttonPanel.add(exportToFileButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(64, 132, 124));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Flatlion", Font.BOLD, 14));
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

    private void backMethod() {
        dispose(); // Close the current window
        SwingUtilities.invokeLater(() -> {
            ConvertToBTGUI convertToBTGUI = new ConvertToBTGUI();
            convertToBTGUI.setLocationRelativeTo(null);
            convertToBTGUI.setVisible(true);
        });
    }

    public void printFileMethod() {
        try {
            OutputWindow outputWindow = new OutputWindow("File Content", ReaderMethods.readFromFile(selectedFile));
            outputWindow.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void convertMethod() {
        generalTreeRoot = loadGeneralTreeFromFile(selectedFile);
        binaryTree = Methods.newBinaryTree(generalTreeRoot);
        // binaryRoot = Converter.convertToBinaryTree(generalTreeRoot);
        binaryRoot = binaryTree.getRoot();
        JOptionPane.showMessageDialog(this, "Generic Tree Successfully Converted to Binary Tree.");
        convertButton.setVisible(false);
        exportToFileButton.setVisible(true);
        printBinaryTreeButton.setVisible(true);
    }

    private Node loadGeneralTreeFromFile(File file) {
        GenericTree tree = new GenericTree();
        try {
            GenericTree.importTreeFromFile(tree, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tree.getRoot();
    }

    public void exportConvertedTreeMethod() {
        SwingUtilities.invokeLater(() -> {
            SaveBinaryTreeOptions saveBinaryTreeOptions = new SaveBinaryTreeOptions();
            saveBinaryTreeOptions.setLocationRelativeTo(null);
            saveBinaryTreeOptions.setBinaryTree(binaryTree);
            saveBinaryTreeOptions.setBinaryTreeRoot(binaryRoot);
            saveBinaryTreeOptions.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            convertMethod();
        } else if (e.getSource() == printBinaryTreeButton) {
            SwingUtilities.invokeLater(() -> {
                PrintBinaryTreeOptions printBinaryTreeOptions = new PrintBinaryTreeOptions();
                printBinaryTreeOptions.setLocationRelativeTo(null);
                printBinaryTreeOptions.setBinaryTree(binaryTree);
                printBinaryTreeOptions.setBinaryTreeRoot(binaryRoot);
                printBinaryTreeOptions.setVisible(true);
            });
        } else if (e.getSource() == exportToFileButton) {
            exportConvertedTreeMethod();
        } else if (e.getSource() == backButton) {
            backMethod();
        }
    }
}
