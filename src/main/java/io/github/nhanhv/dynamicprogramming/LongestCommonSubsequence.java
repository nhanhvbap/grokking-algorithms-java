package io.github.nhanhv.dynamicprogramming;

import java.util.Arrays;

/**
 * Demonstrates the Longest Common Subsequence (LCS) algorithm using dynamic programming.
 *
 * <p>The LCS of two strings is the longest sequence of characters that appears in both strings in
 * the same relative order, but not necessarily contiguously.
 *
 * <p>The algorithm fills a 2-D DP table {@code cell[i][j]} using the recurrence:
 *
 * <pre>
 *   if wordA[i] == wordB[j]:
 *       cell[i][j] = cell[i-1][j-1] + 1
 *   else:
 *       cell[i][j] = max(cell[i-1][j], cell[i][j-1])
 * </pre>
 *
 * <p>Time complexity: O(m × n) — where m and n are the lengths of the two strings.
 *
 * <p>Space complexity: O(m × n) — for the DP table.
 */
public class LongestCommonSubsequence {

  /**
   * Entry point that computes and prints the LCS DP table for two sample words.
   *
   * <p>For {@code wordA = "hish"} and {@code wordB = "fish"}, the expected table is:
   *
   * <pre>
   *   [0, 0, 0, 1]
   *   [0, 1, 1, 1]
   *   [0, 1, 2, 2]
   *   [0, 1, 2, 3]
   * </pre>
   *
   * The length of the LCS is the value in the bottom-right cell (3, representing "ish").
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    //        if (word_a[i] == word_b[j]) {
    //            cell[i][j] = cell[i - 1][j - 1] + 1;
    //        } else {
    //            cell[i][j] = Math.Max(cell[i - 1][j], cell[i][j - 1]);
    //        }

    String wordA = "hish";
    String wordB = "fish";

    int[][] cell = new int[wordA.length()][wordB.length()];

    for (int i = 0; i < wordA.length(); i++) {
      for (int j = 0; j < wordB.length(); j++) {
        // The letters match
        if (wordA.charAt(i) == wordB.charAt(j)) {
          if (i > 0 && j > 0) {
            cell[i][j] = cell[i - 1][j - 1] + 1;
          } else {
            cell[i][j] = 1;
          }
        } else {
          // The letters don't match.
          if (i == 0 && j > 0) {
            cell[i][j] = cell[i][j - 1];
          } else if (i > 0 && j == 0) {
            cell[i][j] = cell[i - 1][j];
          } else if (i > 0 && j > 0) {
            cell[i][j] = Math.max(cell[i - 1][j], cell[i][j - 1]);
          } else {
            cell[i][j] = 0;
          }
        }
      }
    }

    printResult(cell);
    //      [0, 0, 0, 1]
    //      [0, 1, 1, 1]
    //      [0, 1, 2, 2]
    //      [0, 1, 2, 3]
  }

  /**
   * Prints a 2-D integer array row by row to standard output.
   *
   * <p>Each row is formatted using {@link Arrays#toString(int[])}, producing output in the form
   * {@code [a, b, c, ...]}.
   *
   * @param arr the 2-D integer array to print
   */
  private static void printResult(int[][] arr) {
    for (int[] row : arr) {
      System.out.println(Arrays.toString(row));
    }
  }
}
