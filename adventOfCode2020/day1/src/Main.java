/*
    https://adventofcode.com/2020/day/1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String inputFile = "src/input_day01.txt";
        List<String> input = readInputFile(inputFile);

        List<Integer> intInput = input.stream().map(Integer::parseInt).collect(Collectors.toList());

        System.out.println("The solution for the first part is:");
        System.out.println(part1Puzzle(intInput));
        System.out.println();
        System.out.println("The solution for the second part is:");
        System.out.println(part2Puzzle(intInput));
    }

    static Integer part1Puzzle(List<Integer> intInput){
        for (int i = 0; i < intInput.size(); i++) {
            for (int j = i+1 ; j < intInput.size(); j++) {
                if (intInput.get(i) + intInput.get(j) == 2020) {
                    return intInput.get(i) * intInput.get(j);
                }
            }
        }

        return null;
    }

    static Integer part2Puzzle(List<Integer> intInput){
        for (int i = 0; i < intInput.size(); i++) {
            for (int j = i+1; j < intInput.size(); j++) {
                for (int k = j+1; k < intInput.size(); k++) {
                    if (intInput.get(i) + intInput.get(j) + intInput.get(k) == 2020) {
                        return intInput.get(i) * intInput.get(j) * intInput.get(k);
                    }
                }
            }
        }

        return null;
    }

    static List<String> readInputFile(String inputFileName) {
        List<String> lines = new ArrayList<String>();

        try {
            BufferedReader adventInput = new BufferedReader(new FileReader(inputFileName));

            String line;
            while ((line = adventInput.readLine()) != null) {
                lines.add(line);
            }

            adventInput.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return lines;
    }
}