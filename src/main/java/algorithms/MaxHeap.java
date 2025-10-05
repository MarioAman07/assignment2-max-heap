package algorithms;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MaxHeap {
    private int[] heap;
    private int size;
    private final int capacity;

    // Метрики для оптимизации
    private int comparisons = 0;
    private int swaps = 0;
    private int arrayAccesses = 0;

    public MaxHeap(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Heap capacity must be positive.");
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    public MaxHeap(int[] array) {
        this.capacity = array.length;
        this.heap = Arrays.copyOf(array, array.length);
        this.size = array.length;
        buildHeap();
    }

    public void insert(int value) {
        if (size == capacity)
            throw new IllegalStateException("Heap is full.");

        heap[size] = value;
        size++;
        heapifyUp(size - 1);
    }

    public int extractMax() {
        if (size == 0)
            throw new NoSuchElementException("Heap is empty.");

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    public void increaseKey(int i, int newKey) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        if (newKey < heap[i])
            throw new IllegalArgumentException("New key must be greater than current key.");

        heap[i] = newKey;
        heapifyUp(i);
    }

    public int getMax() {
        if (size == 0)
            throw new NoSuchElementException("Heap is empty.");
        return heap[0];
    }

    public int getSize() {
        return size;
    }

    private void heapifyUp(int i) {
        int value = heap[i];
        int parent = (i - 1) / 2;
        while (i > 0 && value > heap[parent]) {
            comparisons++;
            heap[i] = heap[parent];
            arrayAccesses += 2; // чтение и запись
            i = parent;
            parent = (i - 1) / 2;
            swaps++;
        }
        heap[i] = value;
        arrayAccesses++;
    }

    private void heapifyDown(int i) {
        int value = heap[i];
        int largest;

        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            largest = i;

            if (left < size) {
                comparisons++;
                if (heap[left] > value) largest = left;
            }
            if (right < size) {
                comparisons++;
                if (heap[right] > heap[largest]) largest = right;
            }

            if (largest != i) {
                heap[i] = heap[largest];
                arrayAccesses += 2; // чтение и запись
                swaps++;
                i = largest;
            } else {
                break;
            }
        }
        heap[i] = value;
        arrayAccesses++;
    }

    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public int[] getHeapArray() {
        return Arrays.copyOf(heap, size);
    }

    // Метрики для анализа производительности
    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    public int getArrayAccesses() {
        return arrayAccesses;
    }

    @Override
    public String toString() {
        return Arrays.toString(getHeapArray());
    }
}
