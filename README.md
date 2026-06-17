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
    └── trees/
```

---

## Code Style

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
