package io.github.nhanhv.linkedlist;

/**
 * A singly linked list built from {@link Node} objects.
 *
 * <p>Key trade-off vs arrays:
 *
 * <ul>
 *   <li>Insert at head: O(1) — just redirect the head pointer, no shifting.
 *   <li>Access by index: O(n) — must follow the chain from head to reach any node.
 * </ul>
 */
public class LinkedList {

  private Node head;
  private int size;

  public LinkedList() {
    head = null;
    size = 0;
  }

  public int size() {
    return size;
  }

  /**
   * Inserts a new node at the front of the list. O(1).
   *
   * <p>Only the head pointer changes — no traversal, no shifting.
   */
  public void addFirst(int value) {
    Node newNode = new Node(value);
    newNode.next = head; // new node points to old head
    head = newNode; // head now points to new node
    size++;
  }

  /**
   * Appends a new node at the end of the list. O(n).
   *
   * <p>Must traverse all the way to the last node to update its next pointer.
   */
  public void addLast(int value) {
    Node newNode = new Node(value);
    if (head == null) {
      head = newNode;
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next; // walk to the last node
      }
      current.next = newNode;
    }
    size++;
  }

  /**
   * Removes and returns the first element. O(1).
   *
   * <p>Only the head pointer changes.
   */
  public int removeFirst() {
    int value = head.data;
    head = head.next; // head now points to the second node
    size--;
    return value;
  }

  /**
   * Returns the element at the given index without removing it. O(n).
   *
   * <p>This is the key disadvantage vs arrays: no direct index offset, must follow the chain.
   */
  public int get(int index) {
    Node current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }

  /**
   * Removes the first node with the given value. O(n).
   *
   * <p>Traverse to find the node just before the target, then unlink it.
   */
  public boolean remove(int value) {
    if (head == null) return false;

    if (head.data == value) {
      head = head.next;
      size--;
      return true;
    }

    Node current = head;
    while (current.next != null) {
      if (current.next.data == value) {
        current.next = current.next.next; // skip over the target node
        size--;
        return true;
      }
      current = current.next;
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("head");
    Node current = head;
    while (current != null) {
      sb.append(" → ").append(current.data);
      current = current.next;
    }
    return sb.append(" → null").toString();
  }

  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    // addFirst — O(1), no traversal needed
    list.addFirst(30);
    list.addFirst(20);
    list.addFirst(10);
    System.out.println("After addFirst 10,20,30: " + list);

    // addLast — O(n), must reach the tail
    list.addLast(40);
    System.out.println("After addLast 40: " + list);

    // removeFirst — O(1)
    System.out.println("removeFirst: " + list.removeFirst());
    System.out.println("After removeFirst: " + list);

    // get — O(n), follow the chain
    System.out.println("get(1): " + list.get(1));

    // remove by value — O(n)
    list.remove(30);
    System.out.println("After remove(30): " + list);
  }
}
