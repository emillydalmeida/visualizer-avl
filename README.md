# PROJECT REPORT: AVL TREE VISUALIZER

**Project:** AVL Tree Implementation and Visualization  
**Language:** Java  

---

## 1. SYSTEM FUNCTIONALITY EXPLANATION

### 1.1 Overview
The developed system implements a complete **AVL Tree** (Adelson-Velsky and Landis) in Java, providing an interactive interface for manipulation and visualization of the data structure. The AVL tree is a self-balancing binary search tree that maintains its logarithmic height through automatic rotations.

### 1.2 System Architecture
The project is organized into three main classes:

- **NoAVL.java**: Represents an individual tree node
- **ArvoreAVL.java**: Implements the tree structure and its operations
- **Main.java**: Main interface with interactive menu

### 1.3 Implemented Algorithms

#### 1.3.1 Insertion
1. Standard BST insertion
2. Node height update
3. Balance factor calculation
4. Application of rotations if necessary (4 possible cases)

#### 1.3.2 Removal  
1. Location of the node to be removed
2. Handling of 3 cases: no children, one child, two children
3. Rebalancing through rotations
4. Height updates

#### 1.3.3 Search
1. BST property-guided traversal
2. Path recording
3. Search result return

---

## 2. IMPLEMENTED FEATURES

### 2.1 Required Features ✅

#### ✅ Value Insertion
- Insertion maintaining AVL properties
- Detection and handling of duplicate values
- Automatic rebalancing after insertion
- Success/failure feedback

#### ✅ Value Removal
- Removal maintaining AVL properties
- Handling of three removal cases
- Automatic rebalancing after removal
- Success/failure feedback

#### ✅ Value Search
- Efficient search following BST property
- **Search Path**: Displays sequence of consulted nodes
- Found/not found feedback

#### ✅ Tree Visualization
- **Hierarchical Representation**: Visual tree structure
- **Parenthesized Representation**: Compact textual notation
- Automatic update after insertions/removals

### 2.2 Extra Features ✅

#### ✅ Interactive Interface
- Intuitive menu with multiple options
- Input error handling

---

## 3. EXECUTION EXAMPLES

### 3.1 Insertion with Rotation Example

```
=== INSERTION ===
Enter the value to be inserted: 10
✓ Value 10 inserted successfully!

=== Current tree state ===
└── 10 (h:1, fb:0)
==============================

Enter the value to be inserted: 20
✓ Value 20 inserted successfully!

=== Current tree state ===
└── 10 (h:2, fb:-1)
    └── 20 (h:1, fb:0)
==============================

Enter the value to be inserted: 30
Performing left rotation on node 10
✓ Value 30 inserted successfully!

=== Current tree state ===
└── 20 (h:2, fb:0)
    ├── 30 (h:1, fb:0)
    └── 10 (h:1, fb:0)
==============================
```

### 3.2 Search with Path Example

```
=== SEARCH ===
Enter the value to be searched: 25

Performing search...
Search path for 25: 20 -> 30 -> 25
✓ Value 25 found!
```

### 3.3 Parenthesized Visualization Example

```
=== Parenthesized Representation ===
20(10(_,_),30(25(_,_),40(_,_)))
=================================
```

### 3.4 Balance Factor Example

```
=== Balance Factors ===
Node 20: height=3, balance factor=0
Node 10: height=1, balance factor=0
Node 30: height=2, balance factor=0
Node 25: height=1, balance factor=0
Node 40: height=1, balance factor=0
===============================
```

---

## 4. DEVELOPMENT REPORT

### 4.1 Methodology Used
The development followed an incremental approach:

3. **Gradual Implementation**: Development by functionality
4. **Continuous Testing**: Validation at each stage
5. **Refinement**: Interface and documentation improvements

### 4.2 Design Decisions

#### 4.2.1 Class Structure
- **Separation of Concerns**: Each class has a specific function
- **Encapsulation**: Private methods for internal operations
- **Clean Interface**: Well-defined public methods

#### 4.2.2 Rotation Algorithms
I implemented the four rotation cases:
- **Simple Right Rotation (LL)**: For left-left imbalance
- **Simple Left Rotation (RR)**: For right-right imbalance  
- **Double LR Rotation**: For left-right imbalance
- **Double RL Rotation**: For right-left imbalance

#### 4.2.3 Visualization
I chose two forms of visualization:
- **Hierarchical**: More intuitive, shows visual structure
- **Parenthesized**: More compact, useful for algorithmic analysis

### 4.3 Extra Features Implemented

1. **Rich Interface**: Interactive menu with multiple options
2. **Error Handling**: Robust input validation

---


The final result is a complete and functional system that meets all specified requirements, with extra features (interactive menu) that enrich the user experience.
