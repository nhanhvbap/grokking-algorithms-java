package io.github.nhanhv.quicksort;

/**
 * Demonstrates computing the sum of an integer array using an iterative loop.
 *
 * <p>This class is provided as a baseline comparison to the recursive sum approach in {@link
 * RecursiveSum}. Both produce the same result, but this version uses a simple {@code for} loop
 * instead of recursion.
 *
 * <p>Time complexity: O(n) — iterates through every element once.
 *
 * <p>Space complexity: O(1) — uses a single accumulator variable.
 */
public class LoopSum {

  /**
   * Computes the sum of all elements in the given array using an iterative loop.
   *
   * @param arr the integer array whose elements will be summed
   * @return the total sum of all elements in {@code arr}, or {@code 0} if the array is empty
   */
  private static int sum(int[] arr) {
    int total = 0;
    for (int x = 0; x < arr.length; x++) {
      total += arr[x];
    }

    return total;
  }

  /**
   * Entry point demonstrating the loop-based sum on a sample array.
   *
   * <p>Expected output: {@code 10}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println(sum(new int[] {1, 2, 3, 4})); // 10
  }
}
