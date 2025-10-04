package algorithms;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * MaxHeap implementation (array-based)
 * Supports insert, extractMax, increaseKey, and buildHeap.
 *
 * This is the baseline version — no metrics yet.
 */
public class MaxHeap {
    private int[] heap;
    private int size;
    private final int capacity;

    public MaxHeap(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Heap capacity must be positive.");
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    /** Builds a heap from an existing array */
    public MaxHeap(int[] array) {
        this.capacity = array.length;
        this.heap = Arrays.copyOf(array, array.length);
        this.size = array.length;
        buildHeap();
    }

    /** Insert a new value into the heap */
    public void insert(int value) {
        if (size == capacity)
            throw new IllegalStateException("Heap is full.");

        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    /** Extracts and returns the maximum element */
    public int extractMax() {
        if (size == 0)
            throw new NoSuchElementException("Heap is empty.");

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    /** Increases the value at index i to newKey (must be >= current value) */
    public void increaseKey(int i, int newKey) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        if (newKey < heap[i])
            throw new IllegalArgumentException("New key must be greater than current key.");

        heap[i] = newKey;
        heapifyUp(i);
    }

    /** Returns the maximum element (without removing it) */
    public int getMax() {
        if (size == 0)
            throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }

    /** Returns current size of heap */
    public int getSize() {
        return size;
    }

    /** Helper: Restores heap property upwards */
    private void heapifyUp(int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && heap[i] > heap[parent]) {
            swap(i, parent);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    /** Helper: Restores heap property downwards */
    private void heapifyDown(int i) {
        int largest = i;

        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && heap[left] > heap[largest]) largest = left;
            if (right < size && heap[right] > heap[largest]) largest = right;

            if (largest != i) {
                swap(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    /** Helper: Swaps two elements in the heap */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /** Builds the heap in O(n) time */
    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    /** Returns the heap array (for testing/debugging) */
    public int[] getHeapArray() {
        return Arrays.copyOf(heap, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(getHeapArray());
    }
}
