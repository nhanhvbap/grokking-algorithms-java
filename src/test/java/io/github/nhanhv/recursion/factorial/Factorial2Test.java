package io.github.nhanhv.recursion.factorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Factorial2Test {
  @Test
  public void testIsZeroOrOne() {
    Factorial2 factorial2 = new Factorial2();

    Assertions.assertTrue(factorial2.isZeroOrOne(0));
    Assertions.assertTrue(factorial2.isZeroOrOne(1));
    Assertions.assertFalse(factorial2.isZeroOrOne(5));
  }

  @Test
  public void testFactorial() {
    Factorial2 factorial2 = new Factorial2();

    Assertions.assertEquals(120, factorial2.getFactorial(5));
  }
}
