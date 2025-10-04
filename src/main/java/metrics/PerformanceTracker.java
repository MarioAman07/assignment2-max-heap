package metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;
    private long allocations = 0;
    private long startNanos = 0;
    private long elapsedNanos = 0;

    public void reset() {
        comparisons = swaps = arrayAccesses = allocations = 0;
        elapsedNanos = startNanos = 0;
    }

    public void startTimer() { startNanos = System.nanoTime(); }
    public void stopTimer() { elapsedNanos = System.nanoTime() - startNanos; }

    public void incCompare() { comparisons++; }
    public void incSwap() { swaps++; }
    public void incArrayAccess() { arrayAccesses++; }
    public void incArrayAccess(long n) { arrayAccesses += n; }
    public void incAllocation() { allocations++; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getAllocations() { return allocations; }
    public long getElapsedNanos() { return elapsedNanos; }

    public String toCSVLine() {
        return getElapsedNanos() + "," + comparisons + "," + swaps + "," + arrayAccesses + "," + allocations;
    }

    public static String csvHeader() {
        return "elapsed_nanos,comparisons,swaps,array_accesses,allocations";
    }

    public void writeCSV(File file, String header) {
        try (FileWriter fw = new FileWriter(file, true)) {
            if (file.length() == 0 && header != null) fw.write(header + "\n");
            fw.write(toCSVLine() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
