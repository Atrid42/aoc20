/*
    https://adventofcode.com/2020/day/3
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        String inputFile = "src/input_day03.txt";
        List<String> input = readInputFile(inputFile);

        System.out.println("The solution for the first part is:");
        System.out.println(part1Puzzle(input, 3));
        System.out.println("The solution for the second part is:");
        System.out.println(part2Puzzle(input));
    }

    private static int part2Puzzle(List<String> input) {

        List<String> oddInputElements = IntStream.range(0, input.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(input::get)
                .collect(Collectors.toList());

        int right1down1 = part1Puzzle(input, 1);
        int right3down1 = part1Puzzle(input, 3);
        int right5down1 = part1Puzzle(input, 5);
        int right7down1 = part1Puzzle(input, 7);
        int right1down2 = part1Puzzle(oddInputElements, 1);

        return right1down1 * right3down1 * right5down1 * right7down1 * right1down2;
    }

    private static int part1Puzzle(List<String> input, int right_move) {
        AtomicInteger current_position = new AtomicInteger();
        int rows = input.get(0).length();

        int count = (int) input.stream().skip(1).map(line ->{
            int x = current_position.addAndGet(right_move);
            return line.charAt(x % rows); }).filter(item -> item == '#').count();

        return count;
    }

    static List<String> readInputFile(String inputFileName) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader adventInput = new BufferedReader(new FileReader(inputFileName));
            String line;

            while ((line = adventInput.readLine()) != null)
                lines.add(line);

            adventInput.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}