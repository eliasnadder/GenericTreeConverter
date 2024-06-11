package com.mycompany.mavenproject1;

import javax.swing.*;
import java.awt.*;

class DrawingGenericTree extends JPanel {
    private GenericTree tree;
    private final int NODE_RADIUS = 20;
    private final int VERTICAL_SPACING = 50;

    public DrawingGenericTree(GenericTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tree.getRoot() != null) {
            drawTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, Node node, int x, int y, int horizontalSpacing) {
        g.setColor(Color.BLACK);
        g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        g.setColor(Color.WHITE);
        g.drawString(node.getData(), x - 6, y + 4);

        if (!node.getChildren().isEmpty()) {
            int startX = x - (horizontalSpacing * (node.getChildren().size() - 1)) / 2;
            for (Node child : node.getChildren()) {
                g.setColor(Color.BLACK);
                g.drawLine(x, y + NODE_RADIUS, startX, y + VERTICAL_SPACING - NODE_RADIUS);
                drawTree(g, child, startX, y + VERTICAL_SPACING, horizontalSpacing / 2);
                startX += horizontalSpacing;
            }
        }
    }
}
