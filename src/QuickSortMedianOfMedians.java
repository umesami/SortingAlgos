import java.util.*;
public class QuickSortMedianOfMedians {

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivotValue = select(arr, low, high, (high - low) / 2 + 1); // kth smallest (median)
        int pivotIndex = partitionAroundPivot(arr, low, high, pivotValue);
        return pivotIndex;
    }

    private static int partitionAroundPivot(int[] arr, int low, int high, int pivotValue) {
        // Move pivot to end
        int pivotIndex = low;
        for (int i = low; i <= high; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, high);

        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        swap(arr, storeIndex, high); // Move pivot to its final place
        return storeIndex;
    }

    // Median of Medians: select the kth smallest element
    private static int select(int[] arr, int low, int high, int k) {
        if (high - low < 5) {
            int[] temp = Arrays.copyOfRange(arr, low, high + 1);
            Arrays.sort(temp);
            return temp[k - 1];
        }

        int numMedians = 0;
        for (int i = low; i <= high; i += 5) {
            int subHigh = Math.min(i + 4, high);
            int[] group = Arrays.copyOfRange(arr, i, subHigh + 1);
            Arrays.sort(group);
            arr[low + numMedians] = group[group.length / 2];
            numMedians++;
        }

        // Recursively find median of the medians
        int medianOfMedians = select(arr, low, low + numMedians - 1, numMedians / 2 + 1);
        return medianOfMedians;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}