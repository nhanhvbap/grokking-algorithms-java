package io.github.nhanhv.array;

/**
 * Demonstrates a dynamic array backed by a fixed-size int array.
 *
 * <p>Core idea: elements are stored in contiguous memory. When the array is full, allocate a new
 * array of double the size and copy everything over (resize).
 *
 * <p>Time complexity:
 *
 * <ul>
 *   <li>get / set — O(1): direct index access
 *   <li>add — O(1) amortized: occasional O(n) resize is rare
 *   <li>insert / delete — O(n): elements must shift
 * </ul>
 */
public class DynamicArray {

  private int[] data;
  private int size;

  public DynamicArray() {
    data = new int[4]; // start small to trigger resize early
    size = 0;
  }

  /** Returns the number of stored elements. */
  public int size() {
    return size;
  }

  /** Returns the element at the given index. O(1). */
  public int get(int index) {
    return data[index];
  }

  /** Replaces the element at the given index. O(1). */
  public void set(int index, int value) {
    data[index] = value;
  }

  /**
   * Appends a value to the end. Resizes if full. O(1) amortized.
   *
   * <p>Doubling strategy: resize cost spreads out over many inserts, so average cost per insert is
   * still O(1).
   */
  public void add(int value) {
    if (size == data.length) {
      resize();
    }
    data[size] = value;
    size++;
  }

  /**
   * Inserts a value at the given index. Shifts elements right. O(n).
   *
   * <p>This is why arrays are slow for mid-list insertions.
   */
  public void insert(int index, int value) {
    if (size == data.length) {
      resize();
    }
    for (int i = size; i > index; i--) {
      data[i] = data[i - 1]; // shift right to make room
    }
    data[index] = value;
    size++;
  }

  /**
   * Removes the element at the given index. Shifts elements left. O(n).
   *
   * <p>This is why arrays are slow for mid-list deletions.
   */
  public void delete(int index) {
    for (int i = index; i < size - 1; i++) {
      data[i] = data[i + 1]; // shift left to fill gap
    }
    size--;
  }

  /** Allocates a new array of double size and copies all elements over. */
  private void resize() {
    int[] newData = new int[data.length * 2];
    for (int i = 0; i < size; i++) {
      newData[i] = data[i];
    }
    data = newData;
    System.out.println("[resize] capacity: " + (data.length / 2) + " → " + data.length);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < size; i++) {
      sb.append(data[i]);
      if (i < size - 1) sb.append(", ");
    }
    return sb.append("]").toString();
  }

  public static void main(String[] args) {
    DynamicArray arr = new DynamicArray();

    // add — triggers resize at capacity 4
    arr.add(10);
    arr.add(20);
    arr.add(30);
    arr.add(40);
    arr.add(50); // resize here
    System.out.println("After add: " + arr);

    // get / set — O(1)
    System.out.println("get(2): " + arr.get(2));
    arr.set(2, 99);
    System.out.println("After set(2, 99): " + arr);

    // insert — shifts elements right
    arr.insert(1, 15);
    System.out.println("After insert(1, 15): " + arr);

    // delete — shifts elements left
    arr.delete(3);
    System.out.println("After delete(3): " + arr);
  }
}
