/*
    https://adventofcode.com/2020/day/5
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String inputFile = "input_day05.txt";
        List<String> input = readInputFile(inputFile);

        List<String> rowInput = input.stream().map(it -> it.substring(0, 7)).collect(Collectors.toList());
        List<String> columnInput = input.stream().map(
                it -> it.substring(7)
                        .replace("R", "B")
                        .replace("L", "F")).collect(Collectors.toList());

        List<Integer> seatIDs = seatID(rowInput, columnInput);

        System.out.println("The solution for the first part is:");
        System.out.println(Collections.max(seatIDs));
        System.out.println();
        System.out.println("The solution for the second part is:");
        System.out.println(part2Puzzle(seatIDs));
    }

    private static int part2Puzzle(List<Integer> seatID) {
        seatID = seatID.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < seatID.size() - 1; i++) {
            if (seatID.get(i) + 2 == seatID.get(i + 1))
                return seatID.get(i) + 1;
        }
        return 0;
    }

    private static List<Integer> seatID(List<String> rowInput, List<String> columnInput) {
        List<Integer> row = rowInput.stream().map(it -> decoder(128, it)).collect(Collectors.toList());
        List<Integer> column = columnInput.stream().map(it -> decoder(8, it)).collect(Collectors.toList());

        List<Integer> seatID = new ArrayList<>();

        for (int i = 0; i < row.size(); i++) {
            seatID.add(row.get(i) * 8 + column.get(i));
        }

        return seatID;
    }

    private static int decoder(int noOfRowsOrColumns, String sequence) {
        int lower_limit = 0;
        int upper_limit = noOfRowsOrColumns;

        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == 'F')
                upper_limit = upper_limit - (upper_limit - lower_limit + 1) / 2;
            else
                lower_limit = lower_limit + (upper_limit - lower_limit + 1) / 2;
        }

        return lower_limit;
    }

    static List<String> readInputFile(String inputFileName) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader adventInput = new BufferedReader(new FileReader(inputFileName));
            String line;

            while ((line = adventInput.readLine()) != null)
                lines.add(line);

            adventInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}