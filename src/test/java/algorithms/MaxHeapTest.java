package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class MaxHeapTest {

    private MaxHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MaxHeap(100);
    }

    @Test
    void testInsertSingleElement() {
        heap.insert(10);
        assertEquals(10, heap.getMax());
        assertEquals(1, heap.getSize());
    }

    @Test
    void testExtractMaxFromEmptyHeap() {
        assertThrows(NoSuchElementException.class, heap::extractMax);
    }

    @Test
    void testInsertAndExtractMax() {
        heap.insert(5);
        heap.insert(15);
        heap.insert(10);

        assertEquals(15, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertEquals(5, heap.extractMax());
        assertEquals(0, heap.getSize());
    }

    @Test
    void testIncreaseKey() {
        heap.insert(5);
        heap.insert(10);
        heap.insert(8);

        heap.increaseKey(0, 12);
        assertEquals(12, heap.getMax());

        heap.increaseKey(2, 15);
        assertEquals(15, heap.getMax());
    }

    @Test
    void testDuplicateValues() {
        heap.insert(10);
        heap.insert(10);
        heap.insert(10);

        assertEquals(10, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertEquals(10, heap.extractMax());
        assertEquals(0, heap.getSize());
    }

    @Test
    void testBuildHeapFromArray() {
        int[] arr = {3, 1, 6, 5, 2, 4};
        heap = new MaxHeap(arr);

        int prev = heap.extractMax();
        while (heap.getSize() > 0) {
            int current = heap.extractMax();
            assertTrue(prev >= current);
            prev = current;
        }
    }

    @Test
    void testRandomInsertExtract() {
        Random random = new Random(42);
        int[] values = new int[50];
        for (int i = 0; i < 50; i++) {
            values[i] = random.nextInt(1000);
            heap.insert(values[i]);
        }

        int prev = heap.extractMax();
        while (heap.getSize() > 0) {
            int current = heap.extractMax();
            assertTrue(prev >= current);
            prev = current;
        }
    }
}
