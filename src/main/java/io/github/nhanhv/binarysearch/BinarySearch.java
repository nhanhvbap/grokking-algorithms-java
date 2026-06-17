package io.github.nhanhv.binarysearch;

/**
 * Demonstrates the Binary Search algorithm.
 *
 * <p>Binary search works on sorted arrays by repeatedly dividing the search interval in half. It
 * compares the target value to the middle element of the array and eliminates half of the remaining
 * elements with each comparison.
 *
 * <p>Time complexity: O(log n) — where n is the number of elements in the array.
 *
 * <p>Space complexity: O(1) — iterative approach uses constant extra space.
 */
public class BinarySearch {

  /**
   * Entry point demonstrating binary search on a sample array.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    int[] myList = {87, 21, 45, 93};

    System.out.println(binarySearch(myList, 93));
    System.out.println(binarySearch(myList, 16));
  }

  /**
   * Searches for {@code item} in the given sorted array using binary search.
   *
   * <p>The array must be sorted in ascending order for the algorithm to produce correct results. If
   * the item is not found, {@code -1} is returned.
   *
   * @param list a sorted integer array to search
   * @param item the value to search for
   * @return the index of {@code item} in {@code list}, or {@code -1} if not found
   */
  public static int binarySearch(int[] list, int item) {
    if (isListEmpty(list)) {
      return -1;
    }

    int low = 0;
    int high = list.length - 1;

    while (low <= high) {
      int mid = (low + high) / 2;
      int guess = list[mid];

      if (guessEqualsItem(guess, item)) {
        return mid;
      } else if (guessGreaterThanItem(guess, item)) {
        high = mid - 1;
      } else if (guessLessThanItem(guess, item)) {
        low = mid + 1;
      }
    }

    return -1;
  }

  /**
   * Checks whether the given array is empty (has zero elements).
   *
   * @param myList the array to check
   * @return {@code true} if the array has no elements; {@code false} otherwise
   */
  public static boolean isListEmpty(int[] myList) {
    int listSize = myList.length;
    if (listSize == 0) {
      return true;
    }
    return false;
  }

  /**
   * Checks whether the current guess equals the target item.
   *
   * @param guess the value at the current mid-point of the search range
   * @param item the target value being searched for
   * @return {@code true} if {@code guess} equals {@code item}; {@code false} otherwise
   */
  public static boolean guessEqualsItem(int guess, int item) {
    if (guess != item) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the current guess is greater than or equal to the target item.
   *
   * <p>When the guess is too high, the upper bound of the search range should be moved left to
   * {@code mid - 1}.
   *
   * @param guess the value at the current mid-point of the search range
   * @param item the target value being searched for
   * @return {@code true} if {@code guess} is greater than or equal to {@code item}; {@code false}
   *     otherwise
   */
  public static boolean guessGreaterThanItem(int guess, int item) {
    if (guess < item) {
      return false;
    }
    return true;
  }

  /**
   * Checks whether the current guess is less than or equal to the target item.
   *
   * <p>When the guess is too low, the lower bound of the search range should be moved right to
   * {@code mid + 1}.
   *
   * @param guess the value at the current mid-point of the search range
   * @param item the target value being searched for
   * @return {@code true} if {@code guess} is less than or equal to {@code item}; {@code false}
   *     otherwise
   */
  public static boolean guessLessThanItem(int guess, int item) {
    if (guess > item) {
      return false;
    }
    return true;
  }
}
