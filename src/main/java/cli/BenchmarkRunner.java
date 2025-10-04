package cli;

import algorithms.MaxHeap;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        // Чтение аргументов CLI
        int n = 1000; // default input size
        if (args.length > 0) {
            try {
                n = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input size, using default: 1000");
            }
        }

        System.out.println("Running MaxHeap benchmark with n = " + n);

        int[] data = generateRandomArray(n);

        // Измеряем время вставки
        MaxHeap heap = new MaxHeap(n);
        long startInsert = System.nanoTime();
        for (int value : data) {
            heap.insert(value);
        }
        long endInsert = System.nanoTime();

        System.out.println("Insertion completed.");
        System.out.println("Time for insertions: " + (endInsert - startInsert) / 1_000_000.0 + " ms");

        // Измеряем время извлечения
        long startExtract = System.nanoTime();
        while (heap.getSize() > 0) {
            heap.extractMax();
        }
        long endExtract = System.nanoTime();

        System.out.println("Extraction completed.");
        System.out.println("Time for extractions: " + (endExtract - startExtract) / 1_000_000.0 + " ms");
    }

    private static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(n * 10);
        }
        return arr;
    }
}
