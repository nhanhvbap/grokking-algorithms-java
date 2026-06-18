package io.github.nhanhv.redblacktree;

import io.github.nhanhv.redblacktree.RBNode.Color;

/**
 * A self-balancing Binary Search Tree using the Red-Black algorithm.
 *
 * <p><strong>Why self-balancing?</strong> A plain BST inserted with sorted keys degenerates into a
 * linked list (height O(n)). Red-Black Trees fix this by rebalancing after every insert using
 * rotations and recoloring, keeping height at O(log n).
 *
 * <p>After each insert, {@link #fixInsert} handles one of three cases:
 *
 * <ul>
 *   <li><strong>Case 1</strong> — uncle is RED: recolor parent + uncle BLACK, grandparent RED, move
 *       up.
 *   <li><strong>Case 2</strong> — uncle is BLACK, node is inner child: rotate parent to convert
 *       into Case 3.
 *   <li><strong>Case 3</strong> — uncle is BLACK, node is outer child: rotate grandparent, swap
 *       colors. Done.
 * </ul>
 *
 * <p>Time complexity: insert / search — O(log n).
 */
public class RedBlackTree {

  private RBNode root;

  public RedBlackTree() {
    root = null;
  }

  // ── Public API ────────────────────────────────────────────────────────────

  /**
   * Inserts a key using standard BST insert, then calls {@link #fixInsert} to restore Red-Black
   * invariants. O(log n).
   */
  public void insert(int key) {
    RBNode node = new RBNode(key);

    // Standard BST insert
    RBNode parent = null;
    RBNode current = root;
    while (current != null) {
      parent = current;
      if (key < current.key) current = current.left;
      else if (key > current.key) current = current.right;
      else return; // duplicate — ignore
    }

    node.parent = parent;
    if (parent == null) {
      root = node; // tree was empty
    } else if (key < parent.key) {
      parent.left = node;
    } else {
      parent.right = node;
    }

    fixInsert(node);
  }

  /** Searches for a key using standard BST traversal. O(log n). */
  public boolean search(int key) {
    RBNode current = root;
    while (current != null) {
      if (key == current.key) return true;
      else if (key < current.key) current = current.left;
      else current = current.right;
    }
    return false;
  }

  /** Prints keys in sorted order (in-order traversal). O(n). */
  public void inorder() {
    System.out.print("Inorder: ");
    inorderHelper(root);
    System.out.println();
  }

  /** Prints a simple visual tree showing each node's key and color. */
  public void printTree() {
    printHelper(root, "", true);
  }

  // ── Rotations ─────────────────────────────────────────────────────────────

  /**
   * Left rotation around x. O(1).
   *
   * <pre>
   *   x                y
   *  / \              / \
   * A   y    →      x   C
   *    / \         / \
   *   B   C       A   B
   * </pre>
   */
  private void rotateLeft(RBNode x) {
    RBNode y = x.right;
    x.right = y.left;
    if (y.left != null) y.left.parent = x;
    y.parent = x.parent;
    if (x.parent == null) root = y;
    else if (x == x.parent.left) x.parent.left = y;
    else x.parent.right = y;
    y.left = x;
    x.parent = y;
  }

  /**
   * Right rotation around y. O(1).
   *
   * <pre>
   *     y              x
   *    / \            / \
   *   x   C   →      A   y
   *  / \                / \
   * A   B              B   C
   * </pre>
   */
  private void rotateRight(RBNode y) {
    RBNode x = y.left;
    y.left = x.right;
    if (x.right != null) x.right.parent = y;
    x.parent = y.parent;
    if (y.parent == null) root = x;
    else if (y == y.parent.left) y.parent.left = x;
    else y.parent.right = x;
    x.right = y;
    y.parent = x;
  }

  // ── Fix-up after insert ───────────────────────────────────────────────────

  /**
   * Restores Red-Black invariants after inserting a RED node.
   *
   * <p>Loops while the parent is RED (invariant 4 violated). Each iteration applies Case 1, 2, or 3
   * to push the violation up or eliminate it.
   */
  private void fixInsert(RBNode node) {
    while (node.parent != null && node.parent.color == Color.RED) {
      RBNode grandparent = node.parent.parent;

      if (node.parent == grandparent.left) {
        RBNode uncle = grandparent.right;

        if (uncle != null && uncle.color == Color.RED) {
          // Case 1: uncle RED — recolor and move up
          node.parent.color = Color.BLACK;
          uncle.color = Color.BLACK;
          grandparent.color = Color.RED;
          node = grandparent;
        } else {
          if (node == node.parent.right) {
            // Case 2: inner child — rotate to convert to Case 3
            node = node.parent;
            rotateLeft(node);
          }
          // Case 3: outer child — rotate grandparent and swap colors
          node.parent.color = Color.BLACK;
          grandparent.color = Color.RED;
          rotateRight(grandparent);
        }
      } else {
        // Mirror: parent is right child of grandparent
        RBNode uncle = grandparent.left;

        if (uncle != null && uncle.color == Color.RED) {
          // Case 1 (mirror)
          node.parent.color = Color.BLACK;
          uncle.color = Color.BLACK;
          grandparent.color = Color.RED;
          node = grandparent;
        } else {
          if (node == node.parent.left) {
            // Case 2 (mirror)
            node = node.parent;
            rotateRight(node);
          }
          // Case 3 (mirror)
          node.parent.color = Color.BLACK;
          grandparent.color = Color.RED;
          rotateLeft(grandparent);
        }
      }
    }
    root.color = Color.BLACK; // invariant 2: root is always BLACK
  }

  // ── Helpers ───────────────────────────────────────────────────────────────

  private void inorderHelper(RBNode node) {
    if (node == null) return;
    inorderHelper(node.left);
    System.out.print(node.key + "(" + (node.color == Color.RED ? "R" : "B") + ") ");
    inorderHelper(node.right);
  }

  private void printHelper(RBNode node, String indent, boolean isLast) {
    if (node == null) return;
    System.out.println(
        indent
            + (isLast ? "└── " : "├── ")
            + node.key
            + "["
            + (node.color == Color.RED ? "R" : "B")
            + "]");
    String childIndent = indent + (isLast ? "    " : "│   ");
    printHelper(node.right, childIndent, false);
    printHelper(node.left, childIndent, true);
  }

  public static void main(String[] args) {
    RedBlackTree rbt = new RedBlackTree();

    // Insert sorted keys — plain BST would become a right-leaning chain;
    // RBT keeps height balanced via rotations.
    System.out.println("=== Insert sorted 1..7 ===");
    for (int i = 1; i <= 7; i++) {
      rbt.insert(i);
    }
    rbt.printTree();
    rbt.inorder();

    System.out.println("\n=== search ===");
    System.out.println("search(4): " + rbt.search(4));
    System.out.println("search(9): " + rbt.search(9));

    System.out.println("\n=== Insert unsorted keys ===");
    RedBlackTree rbt2 = new RedBlackTree();
    for (int k : new int[] {15, 6, 23, 4, 7, 71, 50}) {
      rbt2.insert(k);
    }
    rbt2.printTree();
    rbt2.inorder();
  }
}
