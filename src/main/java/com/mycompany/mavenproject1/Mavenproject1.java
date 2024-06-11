package com.mycompany.mavenproject1;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author VISION
 */
public class Mavenproject1 {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            WelcomeInterface welcomeInterface = new WelcomeInterface();
            welcomeInterface.setLocationRelativeTo(null);
            welcomeInterface.setVisible(true);
        });

        // BinaryTree binaryTree = new BinaryTree();
        // binaryTree.addLeaf("a");
        // binaryTree.addLeaf("b");
        // binaryTree.addLeaf("c");
        // binaryTree.addLeaf("d");
        // BTNode root = binaryTree.getRoot();
        // System.out.println("BT root " + root.getData());
        // ArrayList<String> binaryTreeContent = new ArrayList<>();

        // binaryTree.getInOrder(root, binaryTreeContent);
        // Node node = Methods.convertToGeneralTree(root);
        // GenericTree tree = new GenericTree();
        // tree.setRoot(node);
        // System.out.println(tree.printGenericTree(node));
    }
}
