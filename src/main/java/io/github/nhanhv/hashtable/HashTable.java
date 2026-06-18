package io.github.nhanhv.hashtable;

/**
 * A simple hash table using separate chaining to handle collisions.
 *
 * <p>Internally: a fixed-size array of {@link Entry} chains. Each slot holds a linked list of
 * entries that hashed to the same index.
 *
 * <p>How it works:
 *
 * <ol>
 *   <li>Compute {@code index = hash(key) % capacity}.
 *   <li>Walk the linked list at {@code buckets[index]} to find or insert the entry.
 * </ol>
 *
 * <p>Time complexity:
 *
 * <ul>
 *   <li>put / get / remove — O(1) average (short chain), O(n) worst (all keys collide)
 * </ul>
 */
public class HashTable {

  private static final int CAPACITY = 10;

  /** A key-value pair that also serves as a linked list node for chaining. */
  private static class Entry {
    String key;
    int value;
    Entry next;

    Entry(String key, int value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }
  }

  private Entry[] buckets;

  public HashTable() {
    buckets = new Entry[CAPACITY];
  }

  /** Maps key to a bucket index. */
  private int bucketIndex(String key) {
    return Math.abs(key.hashCode()) % CAPACITY;
  }

  /**
   * Inserts or updates a key-value pair. O(1) average.
   *
   * <p>If the key exists, update its value. Otherwise prepend a new entry to the bucket chain
   * (prepend is O(1), no need to traverse to the tail).
   */
  public void put(String key, int value) {
    int index = bucketIndex(key);
    Entry current = buckets[index];

    // Check if key already exists — update
    while (current != null) {
      if (current.key.equals(key)) {
        current.value = value;
        return;
      }
      current = current.next;
    }

    // Prepend new entry to the chain
    Entry newEntry = new Entry(key, value);
    newEntry.next = buckets[index];
    buckets[index] = newEntry;
  }

  /**
   * Returns the value for the given key, or -1 if not found. O(1) average.
   *
   * <p>Walk the chain at the computed bucket until we find a matching key.
   */
  public int get(String key) {
    int index = bucketIndex(key);
    Entry current = buckets[index];
    while (current != null) {
      if (current.key.equals(key)) {
        return current.value;
      }
      current = current.next;
    }
    return -1; // not found
  }

  /**
   * Removes the entry with the given key. O(1) average.
   *
   * <p>Find the node just before the target, then unlink it from the chain.
   */
  public boolean remove(String key) {
    int index = bucketIndex(key);
    Entry current = buckets[index];
    Entry prev = null;

    while (current != null) {
      if (current.key.equals(key)) {
        if (prev == null) {
          buckets[index] = current.next; // removing the head of the chain
        } else {
          prev.next = current.next; // unlink from middle or tail
        }
        return true;
      }
      prev = current;
      current = current.next;
    }
    return false;
  }

  /** Prints all non-empty buckets and their chains. */
  public void print() {
    for (int i = 0; i < CAPACITY; i++) {
      if (buckets[i] == null) continue;
      System.out.print("bucket[" + i + "]: ");
      Entry current = buckets[i];
      while (current != null) {
        System.out.print(current.key + "=" + current.value);
        if (current.next != null) System.out.print(" → ");
        current = current.next;
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    HashTable table = new HashTable();

    // put — hash key to bucket, prepend to chain
    table.put("apple", 100);
    table.put("banana", 200);
    table.put("cherry", 300);
    table.put("date", 400);
    table.put("elderberry", 500);
    System.out.println("After 5 inserts:");
    table.print();

    // get — walk chain at bucket
    System.out.println("\nget(\"apple\"): " + table.get("apple"));
    System.out.println("get(\"unknown\"): " + table.get("unknown"));

    // update existing key
    table.put("apple", 999);
    System.out.println("\nAfter update apple=999, get(\"apple\"): " + table.get("apple"));

    // remove — unlink node from chain
    table.remove("banana");
    System.out.println("\nAfter remove(\"banana\"):");
    table.print();
  }
}
