package io.github.nhanhv.recursion.factorial;

/**
 * Demonstrates computing the factorial of a number using recursion.
 *
 * <p>The factorial of a non-negative integer {@code n}, written as {@code n!}, is defined as:
 *
 * <pre>
 *   n! = n × (n-1) × (n-2) × ... × 1,  for n > 1
 *   1! = 1                               (base case)
 * </pre>
 *
 * <p>Time complexity: O(n) — makes n recursive calls.
 *
 * <p>Space complexity: O(n) — call stack depth is n.
 *
 * @see Factorial2 for an alternative implementation with helper methods.
 */
public class Factorial {

  /**
   * Recursively computes the factorial of {@code x}.
   *
   * <p><strong>Base case:</strong> {@code fact(1) = 1}.
   *
   * <p><strong>Recursive case:</strong> {@code fact(x) = x * fact(x - 1)}.
   *
   * @param x a positive integer whose factorial is to be computed; must be greater than or equal to
   *     {@code 1}
   * @return the factorial of {@code x}
   */
  private static int fact(int x) {
    if (x == 1) {
      return 1;
    } else {
      return x * fact(x - 1);
    }
  }

  /**
   * Entry point demonstrating the factorial of 5.
   *
   * <p>Expected output: {@code 120}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println(fact(5));
  }
}
