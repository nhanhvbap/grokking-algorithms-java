package io.github.nhanhv.hashfunction;

/**
 * Demonstrates how hash functions map keys to bucket indices.
 *
 * <p>A hash function takes a key (e.g. a String) and returns an integer index into an array of
 * buckets. The goal is uniform distribution — spread keys evenly so each bucket holds roughly the
 * same number of entries.
 *
 * <p><strong>Pigeonhole Principle:</strong> if keys outnumber buckets, at least two keys must land
 * in the same bucket (collision). Collisions are unavoidable; we can only minimize them with a
 * better hash function.
 *
 * <p>Two strategies shown here:
 *
 * <ul>
 *   <li>{@link #naiveHash} — sums character values; anagrams always collide.
 *   <li>{@link #polynomialHash} — multiplies by a prime at each position; position-sensitive, far
 *       fewer collisions.
 * </ul>
 */
public class HashFunction {

  private static final int BUCKETS = 10;

  /**
   * Naive hash: sum all character ASCII values, then mod by bucket count.
   *
   * <p>Problem: "abc" and "bca" have the same sum → always same bucket (collision).
   */
  public static int naiveHash(String key) {
    int sum = 0;
    for (char c : key.toCharArray()) {
      sum += c;
    }
    return sum % BUCKETS;
  }

  /**
   * Polynomial hash: multiply running total by prime 31 before adding each character.
   *
   * <p>Using a prime multiplier makes the result position-sensitive — "abc" and "bca" now produce
   * different values. This is the same principle Java uses in {@link String#hashCode()}.
   */
  public static int polynomialHash(String key) {
    int hash = 0;
    for (char c : key.toCharArray()) {
      hash = (hash * 31 + c) % BUCKETS;
    }
    return hash;
  }

  public static void main(String[] args) {
    String[] keys = {"apple", "mango", "grape", "melon", "peach"};

    System.out.println("Buckets: " + BUCKETS);
    System.out.printf("%-10s  naive  polynomial%n", "key");
    System.out.println("-".repeat(30));
    for (String key : keys) {
      System.out.printf("%-10s  %-5d  %d%n", key, naiveHash(key), polynomialHash(key));
    }

    // Show anagram collision in naive hash
    System.out.println("\n--- Anagram collision ---");
    String a = "abc";
    String b = "bca";
    System.out.printf("naiveHash(\"%s\")       = %d%n", a, naiveHash(a));
    System.out.printf("naiveHash(\"%s\")       = %d  ← same bucket!%n", b, naiveHash(b));
    System.out.printf("polynomialHash(\"%s\") = %d%n", a, polynomialHash(a));
    System.out.printf("polynomialHash(\"%s\") = %d  ← different%n", b, polynomialHash(b));
  }
}
