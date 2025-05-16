//Algorithm 3: QuickSort via Median of Medians approach
public class QuickSortMedianOfMedians {

    public static int select(int[] A, int k) {
        return medianOfMedians(A, 0, A.length - 1, k);
    }

    private static int medianOfMedians(int[] A, int left, int right, int k) {
        //Base case: if the array has 5 or fewer elements, sort and return the k-th element
        if (right - left + 1 <= 5) {
            selectMedian(A, left, right);
            return A[left + k - 1];
        }

        //Step 1: Partition the array into groups of 5 elements
        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            selectMedian(A, i, subRight); // Sort each group of 5
            int medianIndex = i + (subRight - i) / 2;
            swap(A, left + numMedians, medianIndex); // Move the median to the left
            numMedians++;
        }

        //Step 2: Find the median of medians recursively
        int medianOfMedians = medianOfMedians(A, left, left + numMedians - 1, (numMedians + 1) / 2);

        //Step 3: Partition the array around the median of medians
        int pivotIndex = partitionAroundPivot(A, left, right, medianOfMedians);
        int order = pivotIndex - left + 1;

        //Step 4: Recursively search for the k-th smallest element
        if (k == order) {
            return A[pivotIndex];
        } else if (k < order) {
            return medianOfMedians(A, left, pivotIndex - 1, k);
        } else {
            return medianOfMedians(A, pivotIndex + 1, right, k - order);
        }
    }

    //Use insertion sort to sort the group and find the median
    private static void selectMedian(int[] A, int left, int right) {
        // Insertion sort for a small array of 5 elements
        for (int i = left + 1; i <= right; i++) {
            int key = A[i];
            int j = i - 1;

            //Move elements of A[left..i-1] that are greater than key to one position ahead of their current position
            while (j >= left && A[j] > key) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = key;
        }
    }

    //Partition around the pivot, which is the median of medians
    private static int partitionAroundPivot(int[] A, int left, int right, int pivotValue) {
        for (int i = left; i <= right; i++) {
            if (A[i] == pivotValue) {
                swap(A, i, right); // Swap pivot with rightmost element
                break;
            }
        }
        return partition(A, left, right);
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    //Standard partitioning
    private static int partition(int[] A, int left, int right) {
        int pivot = A[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (A[j] <= pivot) {
                i++;
                swap(A, i, j);
            }
        }

        swap(A, i + 1, right);
        return i + 1;
    }
}
