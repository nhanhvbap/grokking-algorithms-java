package io.github.nhanhv.recursion.factorial;

/**
 * An alternative implementation for computing the factorial of a number using recursion.
 *
 * <p>This version is structured as an instance class with separate helper methods, compared to the
 * static approach in {@link Factorial}. It also explicitly handles the case of {@code 0} and {@code
 * 1} through the {@link #isZeroOrOne(int)} check.
 *
 * <p>The factorial of a non-negative integer {@code n} is defined as:
 *
 * <pre>
 *   n! = n × (n-1) × (n-2) × ... × 1,  for n > 1
 *   1! = 0! = 1                          (base case)
 * </pre>
 *
 * <p>Time complexity: O(n) — makes n recursive calls.
 *
 * <p>Space complexity: O(n) — call stack depth is n.
 *
 * @see Factorial for a simpler static implementation.
 */
public class Factorial2 {

  /**
   * Entry point demonstrating the factorial of 5 using the instance-based approach.
   *
   * <p>Expected output: {@code The factorial of 5 is 120}
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Factorial2 factorial2 = new Factorial2();
    System.out.println("The factorial of 5 is " + factorial2.getFactorial(5));
  }

  /**
   * Recursively computes the factorial of the given {@code number}.
   *
   * <p><strong>Base case:</strong> Returns {@code 1} when {@code number} is {@code 0} or {@code 1}.
   *
   * <p><strong>Recursive case:</strong> Returns {@code number * getFactorial(number - 1)}.
   *
   * @param number a non-negative integer whose factorial is to be computed
   * @return the factorial of {@code number}
   */
  public int getFactorial(int number) {
    if (isZeroOrOne(number)) {
      return 1;
    }

    return number * getFactorial(number - 1);
  }

  /**
   * Checks whether the given number is zero or one — used as the base case condition for the
   * factorial recursion.
   *
   * @param number the integer to check
   * @return {@code true} if {@code number} is {@code 0} or {@code 1}; {@code false} if {@code
   *     number} is greater than {@code 1}
   */
  public boolean isZeroOrOne(int number) {
    if (number > 1) {
      return false;
    }
    return true;
  }
}
