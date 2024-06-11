# Generic Tree Converter

## Introduction
This project aims to develop a Java application that includes converting generic trees to binary trees and vice versa, along with providing a graphical user interface to interact with and visualize these trees. The project focuses on importing trees from files and displaying them visually.

## Goals
1. **Develop an interactive graphical user interface**: Provide an easy-to-use GUI for managing and interacting with trees.
2. **Import data from external files**: Allow users to import tree-related data from external files and convert them into binary tree structures.
3. **Develop classes and methods for tree management**: Create classes and methods for adding, merging, and managing nodes (rectangles) within the binary tree.
4. **Provide visualization mechanisms**: Implement features for drawing and displaying the binary tree visually to users.
5. **Verify operations**: Develop functionalities to verify the correctness of tree operations and provide user feedback.
6. **Export tree data**: Allow users to export binary tree data to external files.

## Classes Used

### Main Class (MavenProject1 Class)
- **Function**: Set the main appearance of the application using `FlatLightLaf` and `Swing`.
- **Tasks**:
  - Set the look and feel of the application.

### ConvertToBTGUI Class
- **Function**: Convert generic trees to binary trees.
- **Tasks**:
  - Import trees manually.
  - Import data from files.

### ConvertToGTGUI Class
- **Function**: Convert binary trees to generic trees.
- **Tasks**:
  - Import trees manually.
  - Import data from files.

### DrawingBinaryTree Class
- **Function**: Draw binary trees.
- **Tasks**:
  - Visually represent binary trees.

### DrawingGenericTree Class
- **Function**: Draw generic trees visually.

### ImportBTManuallyGUI Class
- **Function**: Import binary trees manually.
- **Tasks**:
  - Add nodes to the binary tree.
  - Print the binary tree.
  - Convert binary tree to generic tree.
  - Export generic tree to file.

### ImportGTManuallyGUI Class
- **Function**: Import generic trees manually.
- **Tasks**:
  - Add nodes to the generic tree.
  - Print the generic tree.
  - Export generic tree to file.

### ImportFromFileGUI Class
- **Function**: Import data from files.
- **Tasks**:
  - Convert data to binary tree.
  - Print the binary tree.
  - Export the converted tree to a file.

### Methods Class
- **Function**: Contains operations for converting and exporting trees to files.
- **Tasks**:
  - Operations on trees:
    - Traverse trees (pre-order, in-order, post-order).
    - Convert between different tree types (binary to generic and vice versa).
    - Manage tree operations such as adding, removing nodes.
  - Operations on files:
    - Read tree data from files.
    - Write tree data to files.
    - Export tree structures to file formats.
  - Helper functions:
    - General helper methods related to trees.
    - Error handling and exception management.
    - Verify the correctness of tree structures.
  - User interface interaction:
    - Methods for interacting with UI components.
    - Display messages, alerts, and dialogs.
    - Handle user inputs and actions.
  - Miscellaneous operations:
    - Any other operations related to tree management.
    - Custom algorithms or logic specific to project requirements.

### SaveBinaryTreeOption Class
- **Function**: Save binary trees.
- **Tasks**:
  - Print multiple options to save the binary tree to a file.

### SaveGenericTreeOption Class
- **Function**: Save generic trees.
- **Tasks**:
  - Print multiple options to save the generic tree to a file.

### WelcomeInterface Class
- **Function**: Represent the welcome interface in the application.
- **Tasks**:
  - Set up the welcome screen for users.
  - Provide a starting point to access various functionalities.

## Data Structures Used

### BinaryTree Class
- **Function**: Represents the binary tree structure used to store nodes and manage them.
- **Tasks**:
  - Support operations such as adding nodes, traversing the tree, and printing the tree.

### GenericTree Class
- **Function**: Represents the generic tree structure used to store nodes and manage them.
- **Tasks**:
  - Support operations such as adding nodes, traversing the tree, and printing the tree.

### Node Class
- **Function**: Represents a node in the generic tree structure.
- **Tasks**:
  - Contains properties like name, and manages individual nodes in the tree.

### BTNode Class
- **Function**: Represents a node in the binary tree structure.
- **Tasks**:
  - Contains properties like name, and manages individual nodes in the tree.

### ArrayList Class
- **Function**: Used for storing lists of nodes, merged nodes, inverted nodes, etc.
- **Tasks**:
  - Provide dynamic resizing and easy access to elements.

## Main Points
- **Classes `ImportBTManuallyGUI`, `ImportFromFileGUI`, and `ImportGTManuallyGUI`**: Handle user interactions and data processing.
- **Classes `BinaryTree` and `GenericTree`**: Important for managing the hierarchical structure of nodes.
- **Class `BTNode`**: Represents individual elements in the binary tree.
- **ArrayList**: Used to store collections of nodes and related data.

## Summary
This project combines custom and standard Java data structures to manage and process tree-related data, ensuring effective functionality to meet project requirements. The design provides comprehensive tools for converting between generic and binary trees, facilitating user interaction with intuitive graphical interfaces.

---