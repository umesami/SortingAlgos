import java.util.*;
public class Main {
    public static void main(String[] args) {
//        int[] testCase1  = {};                                      // Empty array
//        int[] testCase2  = {5};                                     // Single element
//        int[] testCase3  = {1, 2, 3, 4, 5};                         // Already sorted
//        int[] testCase4  = {5, 4, 3, 2, 1};                         // Reverse sorted
//        int[] testCase5  = {4, 3, 3, 2, 1};                         // Contains duplicates
//        int[] testCase6  = {5, 3, 8, 4, 1, 9, 2, 7, 6};             // Random values
//        int[] testCase7  = {3, 3, 3, 3, 3};                         // All identical
//        int[] testCase8  = {3, -1, 4, -2, 5, 0};                    // Mixed positive and negative
//        int[] testCase9  = {1, 2, 3, 4, 5, 6};                      // Slightly larger sorted
//        int[] testCase10 = {9, 7, 4, 1, 3, 6, 8, 2, 5};             // Random permutation
//        int[] testCase11 = {-10, -5, -3, -2, -8};                // All negative
//
//        List<int[]> allTests = new ArrayList<>();
//        allTests.add(testCase1);
//        allTests.add(testCase2);
//        allTests.add(testCase3);
//        allTests.add(testCase4);
//        allTests.add(testCase5);
//        allTests.add(testCase6);
//        allTests.add(testCase7);
//        allTests.add(testCase8);
//        allTests.add(testCase9);
//        allTests.add(testCase10);
//        allTests.add(testCase11);
//
//        allTests.forEach(test -> QuickSortMedianOfMedians.sort(test));
//        allTests.forEach(test -> {
//            QuickSortRandom.printArr(test);
//        });
        String inputFileName = "./testCases/Array10.txt";
        String mergeOutputFileName = "./testCases/MergeSortedArray10.txt";
        String quickOutputFileName = "./testCases/QuickSortedArray10.txt";
        String quickMMOutputFileName = "./testCases/QuickMMSortedArray10.txt";

        GenerateTestCase.generateArrayToFile((int)Math.pow(2, 22), 100000, inputFileName);
        int[] original = GenerateTestCase.readArrayFromFile(inputFileName);

        int[] arrMerge = original.clone();
        int[] arrQuick = original.clone();
        int[] arrQuickMM = original.clone();

        long start = System.nanoTime();
        MergeSort.sort(arrMerge);
        long end = System.nanoTime();
        GenerateTestCase.saveArrayToFile(arrMerge, mergeOutputFileName);

        System.out.println("MergeSort: " + (end - start)/1000000.0 + "ms");

        start = System.nanoTime();
        QuickSortRandom.sort(arrQuick);
        end = System.nanoTime();
        GenerateTestCase.saveArrayToFile(arrQuick, quickOutputFileName);

        System.out.println("QuickSort: " + (end - start)/1000000.0 + "ms");

        start = System.nanoTime();
        QuickSortMedianOfMedians.sort(arrQuickMM);
        end = System.nanoTime();
        GenerateTestCase.saveArrayToFile(arrQuickMM, quickMMOutputFileName);

        System.out.println("QuickSort MM: " + (end - start)/1000000.0 + "ms");


    }
}