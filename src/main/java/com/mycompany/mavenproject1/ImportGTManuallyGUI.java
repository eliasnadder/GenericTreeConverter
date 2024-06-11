package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class ImportGTManuallyGUI extends JFrame implements ActionListener {

    private JTextField nameField;
    private JButton addButton, printToFileButton, printTreeButton, convertToGTButton, printGTButton, backButton;
    private JTextArea outputArea;
    JPanel inputPanel, buttonPanel;
    BinaryTree root = new BinaryTree();
    BTNode binaryRoot;
    Node generalNode;
    GenericTree genericTree;
    ArrayList<BTNode> nodes = new ArrayList<>();

    public ImportGTManuallyGUI() {
        setTitle("Import Binary Tree");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        inputPanel.setBackground(new Color(240, 240, 240));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        nameField.setBorder(new RoundedBorder(10));
        inputPanel.add(nameField);

        addButton = new JButton("Add Node");
        addButton.addActionListener(this);
        customizeButton(addButton);
        inputPanel.add(addButton);

        printTreeButton = new JButton("Print Tree");
        printTreeButton.addActionListener(this);
        customizeButton(printTreeButton);
        inputPanel.add(printTreeButton);

        printToFileButton = new JButton("Export Tree to File");
        printToFileButton.addActionListener(this);
        customizeButton(printToFileButton);
        inputPanel.add(printToFileButton);

        convertToGTButton = new JButton("Convert to Generic Tree");
        convertToGTButton.addActionListener(this);
        customizeButton(convertToGTButton);
        convertToGTButton.setVisible(false);
        inputPanel.add(convertToGTButton);

        printGTButton = new JButton("Print Generic Tree");
        printGTButton.setVisible(false);
        printGTButton.addActionListener(this);
        customizeButton(printGTButton);
        inputPanel.add(printGTButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0)); // Top, Left, Bottom, Right padding

        // Add the backButton to the panel
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        customizeButton(backButton);
        buttonPanel.add(backButton);
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

    private void addNode() {
        String name = nameField.getText();
        if (!name.isEmpty()) {
            try {
                root.addLeaf(name);
                nodes.add(new BTNode(name));
                outputArea.append("Node added: " + name + "\n");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
            nameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNodeToTree(BTNode node) {
        // Assuming there's a static method to access the BinaryTree instance
        root.addLeaf(node.getData());
    }

    private void printTreeMethod() {
        if (nodes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            SwingUtilities.invokeLater(() -> {
                PrintBinaryTreeOptions printBinaryTreeOptions = new PrintBinaryTreeOptions();
                printBinaryTreeOptions.setLocationRelativeTo(null);
                printBinaryTreeOptions.setBinaryTree(root);
                printBinaryTreeOptions.setBinaryTreeRoot(root.getRoot());
                printBinaryTreeOptions.setVisible(true);
            });
        }
    }

    public void convertMethod() {
        binaryRoot = root.getRoot();
        if (binaryRoot != null) {
            generalNode = Methods.convertToGeneralTree(binaryRoot);
            genericTree.setRoot(generalNode);
            JOptionPane.showMessageDialog(this, "Binary tree successfully converted back to general tree.");
            printGTButton.setVisible(true);
        }
    }

    private void printToFileMethod() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");

        // تعيين اسم افتراضي للملف
        fileChooser.setSelectedFile(new File("outputBT.txt"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (fileToSave.getName().endsWith(".txt")) {
                Methods.exportBTToFile(root.getPreOrder(root.getRoot()), fileToSave);
                String[] options = { "OK" };
                JOptionPane.showOptionDialog(null, "File Saved Successfully: " + fileToSave.getName(), "Save File",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            } else if (!fileToSave.getName().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
                Methods.exportBTToFile(root.getPreOrder(root.getRoot()), fileToSave);
                String[] options = { "OK" };
                JOptionPane.showOptionDialog(null, "File Saved Successfully: " + fileToSave.getName(), "Save File",
                        JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            }
        }
    }

    public void viewConvertToGTButton() {
        if (nodes.size() >= 1) {
            convertToGTButton.setVisible(true);
        }
    }

    private void backMethod() {
        dispose(); // Close the current window
        SwingUtilities.invokeLater(() -> {
            ConvertToBTGUI convertToBTGUI = new ConvertToBTGUI();
            convertToBTGUI.setLocationRelativeTo(null);
            convertToBTGUI.setVisible(true);
        });
    }

    public void printGTMethod() {
        OutputWindow outputWindow = new OutputWindow("File Content", genericTree.printGenericTree(generalNode));
        outputWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addNode();
            viewConvertToGTButton();
        } else if (e.getSource() == printTreeButton) {
            printTreeMethod();
        } else if (e.getSource() == printToFileButton) {
            printToFileMethod();
        } else if (e.getSource() == backButton) {
            backMethod();
        } else if (e.getSource() == convertToGTButton) {
            convertMethod();
        } else if (e.getSource() == printGTButton) {
            printGTMethod();
        }
    }
}
