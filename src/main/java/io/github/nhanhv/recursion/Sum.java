package io.github.nhanhv.recursion;

/**
 * Demonstrates computing the sum of an integer array using recursion with an index pointer.
 *
 * <p>Unlike the approach in {@code RecursiveSum} (which creates a new sub-array at each recursive
 * call), this implementation passes an index to avoid array copying, making it more memory
 * efficient.
 *
 * <p>Time complexity: O(n) — one recursive call per element.
 *
 * <p>Space complexity: O(n) — call stack depth equals the number of elements remaining.
 */
public class Sum {

  /**
   * Recursively computes the sum of all elements in {@code arr} starting from the given {@code
   * startIndex}.
   *
   * <p><strong>Base case:</strong> When {@code startIndex} equals {@code arr.length}, the sub-array
   * is empty and the sum is {@code 0}.
   *
   * <p><strong>Recursive case:</strong> Returns {@code arr[startIndex]} plus the sum of the
   * remaining elements from {@code startIndex + 1}.
   *
   * @param arr the integer array to sum
   * @param startIndex the index from which to start summing (inclusive); must be in the range
   *     {@code [0, arr.length]}
   * @return the sum of elements from {@code arr[startIndex]} to the end of the array, or {@code 0}
   *     if {@code startIndex == arr.length}
   */
  public static int sum(int[] arr, int startIndex) {
    if (startIndex == arr.length) {
      return 0; // Base case: end of array
    }
    return arr[startIndex] + sum(arr, startIndex + 1);
  }

  /**
   * Entry point demonstrating the index-based recursive sum on a sample array.
   *
   * <p>Expected output: {@code 12}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    int[] numbers = new int[] {2, 4, 6};
    System.out.println(sum(numbers, 0));
  }
}
