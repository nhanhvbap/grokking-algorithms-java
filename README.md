# Grokking Algorithms — Java

Java implementations of the algorithms from the book **Grokking Algorithms** by Aditya Bhargava.

This project is adapted from the original Python samples at
[egonSchiele/grokking_algorithms](https://github.com/egonSchiele/grokking_algorithms) with the following changes:

- All examples rewritten in **Java**
- **Javadoc** added to every class and method for better understanding
- Some implementations have minor adjustments to be more idiomatic Java (e.g. in-place vs. list-based sorting variants, priority queue variant for Dijkstra's)

---

## Requirements

- Java 17+
- Gradle (wrapper included)

---

## Build & Run

```bash
# Build the project
./gradlew build

# Run a specific class
./gradlew run -PmainClass=io.github.nhanhv.binarysearch.BinarySearch
```

Or run any `main` class directly from your IDE.

---

## Algorithms

| Package | Class(es) | Description |
|---|---|---|
| `binarysearch` | `BinarySearch` | Binary search on a sorted array — O(log n) |
| `selectionsort` | `SelectionSort` | Selection sort using a List — O(n²) |
| | `SelectionSort2` | Selection sort in-place with raw array — O(n²) |
| `recursion` | `Countdown` | Basic recursion — countdown example |
| | `Sum` | Recursive sum with index pointer |
| `recursion.factorial` | `Factorial` | Recursive factorial (static) |
| | `Factorial2` | Recursive factorial (instance, with helper) |
| `quicksort` | `Quicksort` | Quicksort with Lomuto partition — O(n log n) avg |
| | `RecursiveSum` | Recursive sum via array slicing |
| | `RecursiveCount` | Recursive element count |
| | `RecursiveMax` | Recursive maximum search |
| | `LoopSum` | Iterative sum (baseline comparison) |
| `hashtables` | `CheckVoter` | Duplicate detection using a hash table |
| | `PriceOfGroceries` | Key/value lookup using a hash table |
| `breadthfirstsearch` | `BreadthFirstSearch` | BFS on a social graph — O(V + E) |
| `dijkstrasalgorithm` | `DijkstrasAlgorithm` | Dijkstra's with linear scan — O(V²) |
| | `DijkstrasAlgorithmPriorityQueue` | Dijkstra's with priority queue — O((V+E) log V) |
| `greedyalgorithms` | `SetCovering` | Greedy set cover approximation — O(n²) |
| `dynamicprogramming` | `LongestCommonSubsequence` | LCS via DP table — O(m×n) |
| `trees` | `FileTrees` | File system traversal — iterative & recursive |
| `array` | `DynamicArray` | Dynamic array with resize — O(1) amortized add, O(n) insert/delete |
| `linkedlist` | `Node` | Linked list node with data and next pointer |
| | `LinkedList` | Singly linked list — O(1) addFirst, O(n) access |
| `hashfunction` | `HashFunction` | Naive vs polynomial hash, Pigeonhole principle |
| `hashtable` | `HashTable` | Hash table with separate chaining — O(1) avg put/get/remove |
| `redblacktree` | `RBNode` | Red-Black Tree node with color (RED/BLACK) |
| | `RedBlackTree` | Self-balancing BST — O(log n) insert/search guaranteed |

---

## Project Structure

```
src/
└── main/java/io/github/nhanhv/
    ├── binarysearch/
    ├── breadthfirstsearch/
    ├── dijkstrasalgorithm/
    ├── dynamicprogramming/
    ├── greedyalgorithms/
    ├── hashtables/
    ├── quicksort/
    ├── recursion/
    │   └── factorial/
    ├── selectionsort/
    ├── trees/
    ├── array/
    ├── linkedlist/
    ├── hashfunction/
    ├── hashtable/
    └── redblacktree/
```

---

## Data Structures

The following data structures are implemented from scratch (without Java's built-in collections) to illustrate how they work internally.

### Array vs LinkedList

| | Array (`DynamicArray`) | LinkedList (`LinkedList`) |
|---|---|---|
| Memory | Contiguous block | Scattered nodes linked by pointers |
| Index access | O(1) | O(n) |
| Insert/delete at head | O(n) — elements must shift | O(1) — only pointer update |
| Resize | Automatic (×2) | Not needed |

**Use Array when** random access by index is frequent and insertions are rare (e.g. buffers, score lists).

**Use LinkedList when** frequent insertion/removal at the head is needed (e.g. queues, undo history).

### HashFunction → HashTable

- **HashFunction** illustrates how a key is mapped to a bucket index, compares naive hash (anagram collisions) with polynomial hash (position-sensitive, fewer collisions), and demonstrates the Pigeonhole Principle.
- **HashTable** uses an array of `Entry` chains (separate chaining) to handle collisions. Each bucket holds a linked list of entries that share the same index.

### Red-Black Tree

A self-balancing BST that guarantees O(log n) height in all cases by enforcing five color invariants:

1. Every node is RED or BLACK
2. The root is always BLACK
3. All null leaves are considered BLACK
4. A RED node's children must both be BLACK (no two consecutive REDs on any path)
5. Every path from a node to its null leaves contains the same number of BLACK nodes (black-height)

After each insert, `fixInsert` restores these invariants through three cases (recolor / rotate). Search ignores color entirely — it works like plain BST search, but stays O(log n) because the tree is always balanced.

| | HashTable | RedBlackTree |
|---|---|---|
| Lookup | O(1) avg | O(log n) |
| Ordered | No | Yes (in-order = sorted) |
| Use when | Fast lookup, order does not matter | Fast lookup + sorted traversal needed |

---

This project uses [Spotless](https://github.com/diffplug/spotless) with Google Java Format.

```bash
# Check formatting
./gradlew spotlessCheck

# Auto-fix formatting
./gradlew spotlessApply
```

---

## Reference

- Book: [Grokking Algorithms](https://www.manning.com/books/grokking-algorithms) by Aditya Bhargava
- Original samples: [egonSchiele/grokking_algorithms](https://github.com/egonSchiele/grokking_algorithms)
