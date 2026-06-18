package io.github.nhanhv.redblacktree;

/**
 * A node in a Red-Black Tree with a color and standard BST left/right/parent pointers.
 *
 * <p><strong>Red-Black Tree invariants (must hold at all times):</strong>
 *
 * <ol>
 *   <li>Every node is RED or BLACK.
 *   <li>The root is BLACK.
 *   <li>All null leaves are considered BLACK.
 *   <li>A RED node's children must both be BLACK (no two consecutive REDs on any path).
 *   <li>Every path from a node to its null leaves contains the same number of BLACK nodes
 *       (black-height).
 * </ol>
 *
 * <p>These five rules together guarantee tree height ≤ 2 log(n), so all operations stay O(log n).
 */
public class RBNode {

  enum Color {
    RED,
    BLACK
  }

  int key;
  Color color;
  RBNode left;
  RBNode right;
  RBNode parent;

  /**
   * New nodes start as RED.
   *
   * <p>Inserting RED never breaks the black-height invariant (rule 5). The only possible violation
   * is two consecutive REDs (rule 4), which {@link RedBlackTree#fixInsert} repairs.
   */
  public RBNode(int key) {
    this.key = key;
    this.color = Color.RED;
  }
}
