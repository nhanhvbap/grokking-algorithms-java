package io.github.nhanhv.recursion;

/**
 * Demonstrates a basic recursive countdown.
 *
 * <p>This is one of the simplest examples of recursion, used to illustrate the two essential
 * components of any recursive function:
 *
 * <ol>
 *   <li><strong>Base case</strong> — the condition under which the recursion stops.
 *   <li><strong>Recursive case</strong> — the condition under which the function calls itself.
 * </ol>
 *
 * <p>Time complexity: O(n) — makes n + 1 recursive calls.
 *
 * <p>Space complexity: O(n) — call stack depth is n + 1.
 */
public class Countdown {

  /**
   * Recursively counts down from {@code i} to {@code 0}, printing each value.
   *
   * <p><strong>Base case:</strong> When {@code i <= 0}, the method prints the value and returns.
   *
   * <p><strong>Recursive case:</strong> Prints the current value and calls itself with {@code i -
   * 1}.
   *
   * @param i the starting value of the countdown; values less than or equal to {@code 0} trigger
   *     the base case immediately
   */
  private static void countdown(int i) {
    System.out.println(i);

    // base case
    if (i <= 0) {
      return;
    } else {
      countdown(i - 1);
    }
  }

  /**
   * Entry point demonstrating the countdown from 5 down to 0.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    countdown(5);
  }
}
