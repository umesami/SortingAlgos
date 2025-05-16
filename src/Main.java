import java.util.*;
public class Main {
    public static void main(String[] args) {
//        testTestingCase();
        testForN();
    }
    public static void testTestingCase(){
        int[] arr1 = new int[]{3, 1, 4, 1, 5, 9, 2};
        int k1 = 1;

        int[] arr2 = new int[]{3, 1, 4, 1, 5, 9, 2};
        int k2 = 3;

        int[] arr3 = new int[]{3, 1, 4, 1, 5, 9, 2};
        int k3 = 7;

        int[] arr4 = new int[]{7};
        int k4 = 1;

        int[] arr5 = new int[]{10, 20, 30, 40, 50};
        int k5 = 5;

        int[] arr6 =  new int[]{50, 40, 30, 20, 10};
        int k6 = 1;

        int[] arr7 = new int[]{8, 2, 2, 8, 1, 4, 9, 6, 3};
        int k7 = 4;

        int[] arr8 =  new int[]{5, 5, 5, 5, 5};
        int k8 = 3;

        int[] arr9 =  new int[]{11, 13, 17, 19, 23, 29};
        int k9 = 6;

        int[] arr10 =   new int[]{31, 10, 20, 5, 15, 25, 35, 40};
        int k10 = 5;

        int[] arr11 =  new int[]{3, 1, 4, 1, 5, 9, 2};
        int k11= 1;

        printArr(arr10);
        System.out.println("k = " + k10);
//        int[] mergeArr10 = arr10.clone();
//        int[] quickArr10 = arr10.clone();
        int[] medianArr10 = arr10.clone();

//        System.out.println("Using MergeSort Algorithm: kth smallest element = " + MergeSort.select(mergeArr10, k10));
//        System.out.println("Using QuickSort Algorithm: kth smallest element = " + QuickSortRandom.select(quickArr10, k10));
        System.out.println("Using QuickSort via Median of Medians Algorithm: kth smallest element = " + QuickSortMedianOfMedians.select(medianArr10, k10));
    }
    public static void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print (arr[i] + " ");
        }
        System.out.println();
    }
    public static void testForN(){
        String inputFileName = "./testCases/Array10.txt";
        String mergeOutputFileName = "./testCases/MergeSortedArray10.txt";
        String quickOutputFileName = "./testCases/QuickSortedArray10.txt";
        String quickMMOutputFileName = "./testCases/QuickMMSortedArray10.txt";

        // Timing for MergeSort and kth selection
        for (int i = 0; i <= 19; i++) {
            double runTotalMerge = 0;
            double runTotalQuick = 0;
            double runTotalMedian = 0;

            for (int j = 0; j < 10; j++) {
                int size = (int) Math.pow(2, i);
                GenerateTestCase.generateArrayToFile(size, 100, inputFileName);
                int[] original = GenerateTestCase.readArrayFromFile(inputFileName);

                Random rand = new Random();
                int k = rand.nextInt(size) + 1;

                int kth = 0;

                int[] arrMerge = original.clone();
                int[] arrQuick = original.clone();
                int[] arrQuickMM = original.clone();

                //MergeSort
                long start = System.nanoTime();
                kth = MergeSort.select(arrMerge, k);
                long end = System.nanoTime();
                double mergeTime = (end - start) / 1000.0;
                GenerateTestCase.saveArrayToFile(arrMerge, mergeOutputFileName);

                runTotalMerge += mergeTime;

                //QuickSortRandom
                start = System.nanoTime();
                kth = QuickSortRandom.select(arrQuick, k);
                end = System.nanoTime();
                double quickTime = (end - start) / 1000.0;
                GenerateTestCase.saveArrayToFile(arrQuick, quickOutputFileName);

                runTotalQuick += quickTime;

                //Median of Medians Kth Selection
                start = System.nanoTime();
                kth = QuickSortMedianOfMedians.select(arrQuickMM, k);
                end = System.nanoTime();
                double medianTime = (end - start) / 1000.0;

                runTotalMedian += medianTime;
            }

            System.out.println("Test Case with input size n = 2 to the " + i + "th power");

            System.out.println("MergeSort: " + runTotalMerge/5 + " microseconds");

            System.out.println("QuickSort: " + runTotalQuick/5 + " microseconds");

            System.out.println("QuickSortMM: " + runTotalMedian/5 + " microseconds\n");
        }
    }
}
