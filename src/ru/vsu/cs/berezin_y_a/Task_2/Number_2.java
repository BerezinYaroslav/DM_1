package ru.vsu.cs.berezin_y_a.Task_2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Number_2 {
    // Задание 2, 3 вариант
    // числа в файле должны быть написаны через запятую с пробелом, без каких-либо знаков и пробелов до и после

    public static void main(String[] args) throws IOException {
        int[] numbers = readArrayFromFile("src/ru/vsu/cs/berezin_y_a/Task_2/Files/input.txt");

        try (FileWriter writer = new FileWriter("src/ru/vsu/cs/berezin_y_a/Task_2/Files/output.txt")) {
            writer.write("Input array:  " + Arrays.toString(numbers) + "\n");

            int count = quickSort(numbers, 0, numbers.length - 1);

            writer.write("Sorted array: " + Arrays.toString(numbers) + "\n");
            writer.write("Number of comparisons: " + count);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int[] readArrayFromFile(String path) throws IOException {
        String file = Files.readString(Path.of(path));

        String[] stringNumbers = file.split(", ");
        int[] numbers = new int[stringNumbers.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }

        return numbers;
    }

    public static int quickSort(int[] array, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int base = array[(leftMarker + rightMarker) / 2];
        int count = 0;

        do {
            count++;

            while (array[leftMarker] < base) {
                count++;
                leftMarker++;
            }

            count++;

            while (array[rightMarker] > base) {
                count++;
                rightMarker--;
            }

            count++;

            if (leftMarker <= rightMarker) {
                count++;

                if (leftMarker < rightMarker) {
                    int tmp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = tmp;
                }

                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        count++;

        if (leftMarker < rightBorder) {
            quickSort(array, leftMarker, rightBorder);
        }

        count++;

        if (leftBorder < rightMarker) {
            quickSort(array, leftBorder, rightMarker);
        }

        return count;
    }
}
