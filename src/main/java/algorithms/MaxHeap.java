package algorithms;

import java.util.Arrays;
import java.util.NoSuchElementException;


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
        int parent = (i - 1) / 2;
        while (i > 0 && heap[i] > heap[parent]) {
            swap(i, parent);
            i = parent;
            parent = (i - 1) / 2;
        }
    }


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


    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    private void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }


    public int[] getHeapArray() {
        return Arrays.copyOf(heap, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(getHeapArray());
    }

}

