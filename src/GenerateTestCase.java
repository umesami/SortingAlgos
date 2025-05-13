import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class GenerateTestCase {
    public static void generateArrayToFile(int size, int bound, String fileName) {
        Random rand = new Random();
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(size);
            for (int i = 0; i < size; i++) {
                writer.print(rand.nextInt(bound) + " ");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }

    public static int[] readArrayFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int size = scanner.nextInt();
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }
            return array;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return new int[0];
        }
    }

    public static void saveArrayToFile(int[] array, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.println(array.length);
            for (int val : array) {
                writer.print(val + " ");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }
}
