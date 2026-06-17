package io.github.nhanhv.selectionsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates the Selection Sort algorithm using a {@link java.util.List}.
 *
 * <p>Selection sort works by repeatedly finding the smallest element from the unsorted portion of
 * the input and appending it to a new sorted list. The original list is mutated (elements are
 * removed) during the process.
 *
 * <p>Time complexity: O(n²) — for each of the n elements, a linear scan ({@link #findSmallest}) is
 * performed over the remaining unsorted elements.
 *
 * <p>Space complexity: O(n) — a new list of equal size is created for the output.
 *
 * @see SelectionSort2 for an in-place variant using a raw array.
 */
public class SelectionSort {

  /**
   * Sorts the given list in ascending order using the selection sort algorithm.
   *
   * <p>The algorithm repeatedly finds the index of the smallest element in {@code arr}, appends
   * that element to a new result list, and removes it from the input list. This continues until the
   * input list is empty.
   *
   * <p><strong>Note:</strong> The input list {@code arr} is modified (emptied) as a side effect of
   * this method.
   *
   * @param arr the list of integers to sort; will be emptied after the call
   * @return a new {@link List} containing all elements of {@code arr} sorted in ascending order
   */
  private static List<Integer> selectionSort(List<Integer> arr) {
    List<Integer> newArr = new ArrayList<>(arr.size());

    int size = arr.size();
    for (int i = 0; i < size; i++) {
      int smallest = findSmallest(arr);
      newArr.add(arr.get(smallest));
      arr.remove(smallest);
    }

    return newArr;
  }

  /**
   * Finds the index of the smallest element in the given list.
   *
   * <p>Performs a linear scan through {@code arr}, tracking the current minimum value and its
   * index.
   *
   * @param arr the list to search; must be non-empty
   * @return the index of the smallest element in {@code arr}
   */
  private static int findSmallest(List<Integer> arr) {
    int smallest = arr.get(0);
    int smallestIndex = 0;
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i) < smallest) {
        smallest = arr.get(i);
        smallestIndex = i;
      }
    }
    return smallestIndex;
  }

  /**
   * Entry point demonstrating selection sort on a sample list.
   *
   * <p>Expected output: {@code [2, 3, 5, 6, 10]}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    List<Integer> arr = new ArrayList<>(Arrays.asList(5, 3, 6, 2, 10));
    System.out.println(selectionSort(arr)); // [2, 3, 5, 6, 10]
  }
}
