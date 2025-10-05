 Assignment 2 — MaxHeap Implementation

## Overview

This project contains a **MaxHeap data structure** implementation in Java with:

- Optimized `heapifyUp` and `heapifyDown` methods for reduced swaps and array accesses.
- Support for `insert`, `extractMax`, and `increaseKey` operations.
- Performance metrics tracking: **comparisons**, **swaps**, **array accesses**.
- CLI benchmark runner for testing different input sizes.
- Comprehensive unit tests covering edge cases.

---

## Repository Structure

```bashassignment2-maxheap/
├── src/main/java/
│ ├── algorithms/MaxHeap.java
│ ├── metrics/PerformanceTracker.java
│ └── cli/BenchmarkRunner.java
├── src/test/java/algorithms/MaxHeapTest.java
├── docs/
│ ├── analysis-report.pdf
│ └── performance-plots/
├── README.md
└── pom.xml
```

---

## Usage Instructions

### 1. Compile the project

```bash
mvn compile
```
### 2.Run unit tests
```
mvn test
```

### 3. Run CLI benchmark
```
java -cp target/classes cli.BenchmarkRunner [input_size]
```

- input_size — size of the array for the benchmark (e.g., `1000`, `10000`, `100000`).
- Example:
```
java -cp target/classes cli.BenchmarkRunner 10000
```

Output will display time taken for insertions and extractions:
```sql
Running MaxHeap benchmark with n = 10000
Insertion completed.
Time for insertions: X.XXX ms
Extraction completed.
Time for extractions: Y.YYY msMaxHeap Operations & Complexity
```

## MaxHeap Operations & Complexity

| Operation          | Time Complexity | Notes |
|-------------------|----------------|-------|
| `insert(value)`    | O(log n)       | Optimized `heapifyUp` reduces swaps and array accesses. |
| `extractMax()`     | O(log n)       | Optimized `heapifyDown`. |
| `increaseKey(i,k)` | O(log n)       | Raises key and heapifies up. |
| `buildHeap(array)` | O(n)           | Builds heap from array efficiently. |
| `getMax()`         | O(1)           | Returns the root element. |
| `getSize()`        | O(1)           | Returns current heap size. |


## Performance Metrics

- **comparisons** — number of element comparisons performed.
- **swaps** — number of logical swaps (movement of values).
- **arrayAccesses** — number of reads/writes to the heap array.

These metrics are accessible via MaxHeap getters:

```java
heap.getComparisons();
heap.getSwaps();
heap.getArrayAccesses();
```
They help validate theoretical vs empirical complexity.

### Testing
 Unit tests cover:

- Empty heap
- Single element
- Duplicates
- Sorted, reverse-sorted, and random arrays
- Random insert/extract sequences


### Notes
 - Project uses JUnit 5 for testing.
 - Maven is used for compilation and dependency management.
 - Optimizations focus on reducing swaps and array accesses, improving real-world performance for large heaps.
