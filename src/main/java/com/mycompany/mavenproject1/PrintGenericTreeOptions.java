
package com.mycompany.mavenproject1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class PrintGenericTreeOptions extends JFrame implements ActionListener {
    private JRadioButton outputArea, drawingTreeRadioButton;
    private JButton printButton;
    private GenericTree genericTree;

    public void setGenericTree(GenericTree genericTree) {
        this.genericTree = genericTree;
    }

    public PrintGenericTreeOptions() {
        super("Print Generic Tree Options");
        setLayout(new FlowLayout());

        // خيارات الطباعة باستخدام JCheckBox
        outputArea = new JRadioButton("Output Area");
        drawingTreeRadioButton = new JRadioButton("Drawing Tree");

        customizeCheckBox(outputArea);
        customizeCheckBox(drawingTreeRadioButton);

        add(outputArea);
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
            if (outputArea.isSelected()) {
                OutputWindow outputWindow = new OutputWindow("Output Area",
                        genericTree.printGenericTree(genericTree.getRoot()));
                outputWindow.setVisible(true);
            } else if (drawingTreeRadioButton.isSelected()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new JFrame("Drawing Generic Tree");
                        // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setSize(600, 400);

                        DrawingGenericTree treePanel = new DrawingGenericTree(genericTree);
                        frame.add(treePanel);
                        frame.setVisible(true);
                    }
                });
            }
        }
    }
}
