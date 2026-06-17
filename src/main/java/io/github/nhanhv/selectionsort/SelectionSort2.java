package io.github.nhanhv.selectionsort;

import java.util.Arrays;

/**
 * An alternative, in-place implementation of the Selection Sort algorithm using raw arrays.
 *
 * <p>Rather than building a new sorted list (as in {@link SelectionSort}), this version sorts the
 * array in place by scanning for the minimum element in the unsorted suffix and swapping it into
 * position at the front of that suffix.
 *
 * <p>Time complexity: O(n²) — two nested loops each running up to n iterations.
 *
 * <p>Space complexity: O(1) — sorting is done in place with only a temporary variable.
 *
 * @see SelectionSort for a List-based variant that builds a new sorted collection.
 */
public class SelectionSort2 {

  /**
   * Sorts the given integer array in ascending order in place using selection sort.
   *
   * <p>The outer loop advances the "sorted boundary" one position at a time. The inner loop scans
   * the remaining unsorted portion for an element smaller than the current boundary element and
   * swaps them if found.
   *
   * @param arr the integer array to sort; the array is modified in place
   */
  public static void selectionSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[i]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      }
    }
  }

  /**
   * Entry point demonstrating in-place selection sort on a sample array.
   *
   * <p>Expected output: {@code [2, 3, 5, 6, 10]}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    int[] arr = {5, 3, 6, 2, 10};
    selectionSort(arr);
    System.out.println(Arrays.toString(arr)); // [2, 3, 5, 6, 10]
  }
}
