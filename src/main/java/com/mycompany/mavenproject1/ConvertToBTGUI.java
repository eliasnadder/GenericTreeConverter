package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConvertToBTGUI extends JFrame implements ActionListener {

    private JButton importManuallyButton, importFromFileButton, backButton;
    private JPanel mainPanel, buttonPanel, topPanel;
    private JLabel welcomeLabel1, welcomeLabel2, space;

    public ConvertToBTGUI() {
        setTitle("Generic Tree to Binary Tree");
        setSize(600, 400); // تعديل حجم النافذة ليتناسب مع حجم الصورة
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column 0
        gbc.gridy = 1; // Row 1, directly under welcomeLabel1 which is at row 0
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0); // Top padding
        add(mainPanel);

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

        // إنشاء عبارة الترحيب وتخطيطها في أعلى اللوحة
        welcomeLabel1 = new JLabel("Convert");
        welcomeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel1.setFont(new Font("Flatlion", Font.BOLD, 24));
        welcomeLabel1.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(welcomeLabel1, gbc);

        welcomeLabel2 = new JLabel("Generic Tree to Binary Tree");
        welcomeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel2.setFont(new Font("Flatlion", Font.BOLD, 20));
        welcomeLabel2.setForeground(Color.BLACK);
        welcomeLabel2.setBorder(BorderFactory.createEmptyBorder(-3, 0, 60, 0));
        gbc.gridy = 1;
        mainPanel.add(welcomeLabel2, gbc);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        gbc.gridy = 2;
        mainPanel.add(buttonPanel, gbc);

        importManuallyButton = new JButton("Import Tree Manually");
        importManuallyButton.addActionListener(this);
        customizeButton(importManuallyButton);
        buttonPanel.add(importManuallyButton);

        space = new JLabel(" ");
        buttonPanel.add(space);

        importFromFileButton = new JButton("Import From File");
        importFromFileButton.addActionListener(this);
        customizeButton(importFromFileButton);
        buttonPanel.add(importFromFileButton);

        // add(buttonPanel, BorderLayout.SOUTH);
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

    public void viewImportFromFileGUI() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose File");
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (!selectedFile.getAbsolutePath().endsWith(".txt")) {
                JOptionPane.showMessageDialog(this, "Please select a .txt file", "Invalid File Type",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            SwingUtilities.invokeLater(() -> {
                ImportFromFileGUI importFromFileGUI = new ImportFromFileGUI();
                importFromFileGUI.setLocationRelativeTo(null);
                importFromFileGUI.setVisible(true);
                importFromFileGUI.outputArea.setText(content.toString());
                importFromFileGUI.setSelectedFile(selectedFile);
            });
        }
    }

    public void viewImportManuallyGUI() {
        dispose();
        SwingUtilities.invokeLater(() -> {
            ImportGTManuallyGUI importGTManuallyGUI = new ImportGTManuallyGUI();
            importGTManuallyGUI.setLocationRelativeTo(null);
            importGTManuallyGUI.setVisible(true);
        });
    }

    private void backMethod() {
        dispose(); // Close the current window
        SwingUtilities.invokeLater(() -> {
            WelcomeGUI welcomeGUI = new WelcomeGUI();
            welcomeGUI.setLocationRelativeTo(null);
            welcomeGUI.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == importManuallyButton) {
            viewImportManuallyGUI();
        } else if (e.getSource() == importFromFileButton) {
            viewImportFromFileGUI();
        } else if (e.getSource() == backButton) {
            backMethod();
        }
    }
}
