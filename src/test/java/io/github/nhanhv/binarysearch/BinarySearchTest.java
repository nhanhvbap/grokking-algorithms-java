package io.github.nhanhv.binarysearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinarySearchTest {
  @Test
  public void testListIsEmpty() {
    int[] myList = {6, 9};
    int[] emptyList = {};

    Assertions.assertFalse(BinarySearch.isListEmpty(myList));
    Assertions.assertTrue(BinarySearch.isListEmpty(emptyList));
  }

  @Test
  public void testGuessEqualsItem() {
    Assertions.assertTrue(BinarySearch.guessEqualsItem(3, 3));
    Assertions.assertFalse(BinarySearch.guessEqualsItem(0, 4));
  }

  @Test
  public void testGuessIsLessThanItem() {
    Assertions.assertTrue(BinarySearch.guessLessThanItem(2, 7));
    Assertions.assertFalse(BinarySearch.guessLessThanItem(6, 1));
  }

  @Test
  public void testGuessGreaterThanItem() {
    Assertions.assertTrue(BinarySearch.guessGreaterThanItem(17, 12));
    Assertions.assertFalse(BinarySearch.guessGreaterThanItem(13, 28));
  }

  @Test
  public void testGivenListAndItemReturnIndexOfItem() {
    int[] testList = {1, 3, 5, 7, 9};
    Assertions.assertEquals(1, BinarySearch.binarySearch(testList, 3));
    Assertions.assertEquals(-1, BinarySearch.binarySearch(testList, 77));
  }
}
