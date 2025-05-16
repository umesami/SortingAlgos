//Algorithm 1: MergeSort-based approach
public class MergeSort {

    //Main method for selecting the kth smallest element
    public static int select(int[] A, int k) {
        mergeSort(A, 0, A.length - 1); //Sort the array using mergeSort
        return A[k - 1]; //Return the kth smallest element (k is 1-based)
    }

    //Recursive mergeSort function to sort the array
    private static void mergeSort(int[] A, int left, int right) {
        if (left < right) { //Continue dividing the array until a base case is reached
            int mid = (left + right) / 2; //Find the middle index
            mergeSort(A, left, mid); //Recursively sort the left half
            mergeSort(A, mid + 1, right); //Recursively sort the right half
            merge(A, left, mid, right); //Merge the two sorted halves
        }
    }

    //Merge function to combine two sorted subarrays into a single sorted array
    private static void merge(int[] A, int left, int mid, int right) {
        //Determine the sizes of the two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        //Create temporary arrays to hold the subarrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        //Copy data into temporary arrays
        System.arraycopy(A, left, L, 0, n1);
        System.arraycopy(A, mid + 1, R, 0, n2);

        //Merge the temporary arrays back into the original array
        int i = 0, j = 0, k = left;
        //Compare elements from L and R and insert the smaller one into the array A
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) { //If the element in L is smaller or equal
                A[k] = L[i];
                i++; //Move to the next element in L
            } else {
                A[k] = R[j];
                j++; //Move to the next element in R
            }
            k++; //Move to the next position in the merged array
        }

        //Copy any remaining elements from L into A
        while (i < n1) {
            A[k] = L[i];
            i++;
            k++;
        }

        //Copy any remaining elements from R into A
        while (j < n2) {
            A[k] = R[j];
            j++;
            k++;
        }
    }
}