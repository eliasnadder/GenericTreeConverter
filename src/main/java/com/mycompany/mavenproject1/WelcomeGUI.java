package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeGUI extends JFrame implements ActionListener {

    private JButton convertToBTButton,convertTOGButton;
    private JPanel mainPanel, buttonPanel;
    private JLabel welcomeLabel1, space;

    public WelcomeGUI() {
        setTitle("Welcome");
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

        // إنشاء عبارة الترحيب وتخطيطها في أعلى اللوحة
        welcomeLabel1 = new JLabel("Welcome");
        welcomeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel1.setFont(new Font("Flatlion", Font.BOLD, 40));
        welcomeLabel1.setForeground(Color.BLACK);
        welcomeLabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 0)); 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(welcomeLabel1, gbc);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        gbc.gridy = 1;
        mainPanel.add(buttonPanel, gbc);

        convertToBTButton = new JButton();
        convertToBTButton.setText("<html><center>Generic Tree<br>to Binary Tree</center></html>");
        convertToBTButton.addActionListener(this);
        customizeButton(convertToBTButton);
        buttonPanel.add(convertToBTButton);

        space = new JLabel(" ");
        buttonPanel.add(space);

        convertTOGButton = new JButton();
        convertTOGButton.setText("<html><center>Binary Tree<br>to Generic Tree</center></html>");
        convertTOGButton.addActionListener(this);
        customizeButton(convertTOGButton);
        buttonPanel.add(convertTOGButton);
    }

    private void customizeButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(64, 132, 124));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Flatlion", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(180, 50));
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

    public void viewConvertToBTGUI(){
        SwingUtilities.invokeLater(() -> {
            ConvertToBTGUI convertToBTGUI = new ConvertToBTGUI();
            convertToBTGUI.setLocationRelativeTo(null);
            convertToBTGUI.setVisible(true);
            dispose();
        });
    }
    public void viewConvertToGTGUI(){
        SwingUtilities.invokeLater(() -> {
            ConvertToGTGUI convertToGTGUI = new ConvertToGTGUI();
            convertToGTGUI.setLocationRelativeTo(null);
            convertToGTGUI.setVisible(true);
            dispose();
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertTOGButton) {
            viewConvertToGTGUI();
        } else if (e.getSource() == convertToBTButton) {
            viewConvertToBTGUI();
        }
    }
}
