package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MaxHeapTest {

    private MaxHeap emptyHeap;
    private MaxHeap singleHeap;

    @BeforeEach
    void setUp() {
        emptyHeap = new MaxHeap(10);
        singleHeap = new MaxHeap(10);
        singleHeap.insert(42);
    }

    @Test
    void extractMaxFromEmptyHeapShouldThrow() {
        assertThrows(NoSuchElementException.class, () -> emptyHeap.extractMax());
    }

    @Test
    void getMaxFromEmptyHeapShouldThrow() {
        assertThrows(NoSuchElementException.class, () -> emptyHeap.getMax());
    }

    @Test
    void increaseKeyOnEmptyHeapShouldThrow() {
        assertThrows(IndexOutOfBoundsException.class, () -> emptyHeap.increaseKey(0, 10));
    }

    @Test
    void getSizeOfEmptyHeapShouldBeZero() {
        assertEquals(0, emptyHeap.getSize());
    }

    @Test
    void extractMaxFromSingleElementHeapShouldReturnElement() {
        int value = singleHeap.extractMax();
        assertEquals(42, value);
        assertEquals(0, singleHeap.getSize());
    }

    @Test
    void getMaxFromSingleElementHeapShouldReturnElement() {
        assertEquals(42, singleHeap.getMax());
    }

    @Test
    void increaseKeyOnSingleElementHeapShouldUpdateValue() {
        singleHeap.increaseKey(0, 100);
        assertEquals(100, singleHeap.getMax());
    }

    @Test
    void getSizeOfSingleElementHeapShouldBeOne() {
        assertEquals(1, singleHeap.getSize());
    }
}
