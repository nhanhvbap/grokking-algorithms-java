package io.github.nhanhv.linkedlist;

/**
 * A single node in a singly linked list.
 *
 * <p>Unlike an array where elements sit in contiguous memory, each node lives anywhere on the heap.
 * The {@code next} pointer is what connects nodes into a chain.
 */
public class Node {

  int data;
  Node next;

  public Node(int data) {
    this.data = data;
    this.next = null;
  }
}
