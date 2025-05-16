//Algorithm 2: QuickSort Approach

import java.util.Random;

public class QuickSortRandom {

    //Main method for selecting the kth smallest element
    public static int select(int[] A, int k) {
        return quickSelect(A, 0, A.length - 1, k); //Call quickSelect with initial bounds of the array
    }

    //Recursive QuickSelect function to find the kth smallest element
    private static int quickSelect(int[] A, int left, int right, int k) {
        //Base case: when the left and right pointers converge, return the element
        if (left == right) {
            return A[left];
        }

        //Choose a pivot index randomly and partition the array
        int pivotIndex = randomPartition(A, left, right);

        //Determine the order of the pivot element (its position in the sorted array)
        int order = pivotIndex - left + 1;

        //If the pivot element is the kth smallest, return it
        if (k == order) {
            return A[pivotIndex];
        }
        //If k is less than the order, search the left partition
        else if (k < order) {
            return quickSelect(A, left, pivotIndex - 1, k);
        }
        //If k is greater than the order, search the right partition
        else {
            return quickSelect(A, pivotIndex + 1, right, k - order);
        }
    }

    //Randomly partition the array around a pivot element
    private static int randomPartition(int[] A, int left, int right) {
        Random rand = new Random();
        //Choose a random pivot index within the range
        int pivotIndex = rand.nextInt(right - left + 1) + left;
        //Swap the randomly chosen pivot with the last element
        swap(A, pivotIndex, right);
        //Return the new index of the pivot after partitioning
        return partition(A, left, right);
    }

    //Partition the array around a pivot, placing elements less than the pivot to the left
    //and elements greater than the pivot to the right
    private static int partition(int[] A, int left, int right) {
        int pivot = A[right]; //The pivot is the last element
        int i = left - 1; //i will track the boundary for elements smaller than pivot

        //Iterate through the array, comparing each element with the pivot
        for (int j = left; j < right; j++) {
            if (A[j] <= pivot) { //If the current element is smaller or equal to the pivot
                i++; //Increment the boundary index
                swap(A, i, j); //Swap the element into the left part of the array
            }
        }

        //Swap the pivot into its correct position in the sorted array
        swap(A, i + 1, right);
        return i + 1; //Return the index of the pivot after partitioning
    }

    //Utility method to swap two elements in the array
    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
