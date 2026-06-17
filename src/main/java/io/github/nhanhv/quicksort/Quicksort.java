package io.github.nhanhv.quicksort;

import java.util.Arrays;

/**
 * Demonstrates the Quicksort algorithm — a divide-and-conquer sorting algorithm that partitions an
 * array around a pivot element and recursively sorts each partition.
 *
 * <p>This implementation uses the last element of the current sub-array as the pivot and applies
 * Lomuto's partition scheme.
 *
 * <p><strong>Average case</strong> time complexity: O(n log n) — pivot divides the array roughly in
 * half on each level.
 *
 * <p><strong>Worst case</strong> time complexity: O(n²) — occurs when the pivot is always the
 * smallest or largest element (e.g., already-sorted input with no randomization).
 *
 * <p>Space complexity: O(log n) average — for the recursive call stack.
 */
public class Quicksort {

  /**
   * Entry point demonstrating quicksort on a sample array.
   *
   * <p>Expected output:
   *
   * <pre>
   *   [10, 5, 2, 3]   (original)
   *   [2, 3, 5, 10]   (sorted)
   * </pre>
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    int[] array = {10, 5, 2, 3};
    System.out.println(Arrays.toString(array)); // Original array
    quickSort(array, 0, array.length - 1);
    System.out.println(Arrays.toString(array)); // Sorted array
  }

  /**
   * Recursively sorts the sub-array {@code array[low..high]} in ascending order using the quicksort
   * algorithm.
   *
   * <p><strong>Base case:</strong> A sub-array of zero or one element is already sorted.
   *
   * <p><strong>Recursive case:</strong> Partition the sub-array around a pivot, then recursively
   * sort the left and right partitions.
   *
   * @param array the array containing the sub-array to sort
   * @param low the inclusive lower index of the sub-array to sort
   * @param high the inclusive upper index of the sub-array to sort
   */
  private static void quickSort(int[] array, int low, int high) {
    if (low >= high) {
      // Base case: arrays with 0 or 1 element are already "sorted"
      return;
    }
    // Recursive case
    int pivotIndex = partition(array, low, high);
    quickSort(array, low, pivotIndex - 1); // Sub-array of elements less than pivot
    quickSort(array, pivotIndex + 1, high); // Sub-array of elements greater than pivot
  }

  /**
   * Partitions the sub-array {@code array[low..high]} around the pivot ({@code array[high]}) using
   * Lomuto's partition scheme.
   *
   * <p>After partitioning, all elements to the left of the pivot index are less than or equal to
   * the pivot, and all elements to the right are greater.
   *
   * @param array the array containing the sub-array to partition
   * @param low the inclusive lower index of the sub-array
   * @param high the inclusive upper index of the sub-array; {@code array[high]} is used as the
   *     pivot
   * @return the final sorted index of the pivot element
   */
  private static int partition(int[] array, int low, int high) {
    int pivot = array[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (array[j] <= pivot) {
        i++;
        swap(array, i, j);
      }
    }
    swap(array, i + 1, high);
    return i + 1;
  }

  /**
   * Swaps the elements at indices {@code i} and {@code j} in the given array.
   *
   * @param array the array in which the swap is performed
   * @param i the index of the first element
   * @param j the index of the second element
   */
  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
