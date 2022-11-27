package ru.vsu.cs.berezin_y_a.Task_1;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Number_1 {
    // Задание 1, 3 вариант
    // числа в файле должны быть написаны через запятую с пробелом, без каких-либо знаков и пробелов до и после

    public static void main(String[] args) throws IOException {
        int[] numbers = readArrayFromFile("src/ru/vsu/cs/berezin_y_a/Task_1/Files/input.txt");
        String space;
        int count = 0;

        try (FileWriter writer = new FileWriter("src/ru/vsu/cs/berezin_y_a/Task_1/Files/output.txt")) {
            writer.write("Input array:  " + Arrays.toString(numbers) + "\n");
            Arrays.sort(numbers);

            writer.write("Sorted array: " + Arrays.toString(numbers) + "\n");
            writer.write("\n");

            for (int number : numbers) {
                space = number > 9 ? "  " : "   ";
                writer.write("Index of " + number + ":" + space + interpolationSearch(numbers, number)[0]);
                writer.write("\n");
                count += interpolationSearch(numbers, number)[1];
            }

            writer.write("\n");
            writer.write("Average number of comparisons: " + count / numbers.length);
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

    public static int[] interpolationSearch(int[] data, int item) {
        int highEnd = (data.length - 1);
        int lowEnd = 0;
        int count = 0;

        while (item >= data[lowEnd] && item <= data[highEnd] && lowEnd <= highEnd) {
            int position = lowEnd + (highEnd - lowEnd) * (item - data[lowEnd]) / (data[highEnd] - data[lowEnd]);

            count++;

            if (highEnd == lowEnd) {
                count++;

                if (data[lowEnd] == item) {
                    return new int[]{lowEnd, count};
                } else {
                    return new int[]{-1, count};
                }
            }

            count++;

            if (data[position] == item) {
                return new int[]{position, count};
            }

            count++;

            if (data[position] < item) {
                lowEnd = position + 1;
            } else {
                highEnd = position - 1;
            }
        }

        return new int[]{-1, count};
    }
}
