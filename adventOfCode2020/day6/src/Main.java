/*
    https://adventofcode.com/2020/day/6
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        String inputFile = "input_day06.txt";
        List<String> input = readInputFile(inputFile);

        System.out.println(input);

        System.out.println("The solution for the first part is:");
        System.out.println(part1Puzzle(input));
        System.out.println();
        System.out.println("The solution for the second part is:");
        System.out.println(part2Puzzle(input));
    }

    private static int part1Puzzle(List<String> input) {
        List<Integer> questionsAnswered = new ArrayList<>();

        questionsAnswered = input.stream().map(it -> (int) it.replace(" ","").chars().distinct().count()).collect(Collectors.toList());

        return questionsAnswered.stream().mapToInt(Integer::intValue).sum();
    }

    private static int part2Puzzle(List<String> input) {
        List<Integer> questionsAnswered = new ArrayList<>();
/*
        long count = input.stream().filter(
                it -> fieldsToCheck.stream().allMatch(it::contains)
        ).count(); */

        questionsAnswered = input.stream().map(it -> countCommonAnswer(it)).collect(Collectors.toList());
        return questionsAnswered.stream().mapToInt(Integer::intValue).sum();
    }

    private static int countCommonAnswer(String groupAnswers) {
        List<String> individualAnswers = Arrays.asList(groupAnswers.split(" "));

        List<Character> commonAnswer = individualAnswers.get(0).chars().distinct().mapToObj(it -> (char) it).collect(Collectors.toList());

        for (int i = 1; i < individualAnswers.size(); i++) {
            List<Character> testList = individualAnswers.get(i).chars().distinct().mapToObj(it -> (char) it).collect(Collectors.toList());
            commonAnswer.retainAll(testList);
        }

        return commonAnswer.size();
    }

    static List<String> readInputFile(String inputFileName) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader adventInput = new BufferedReader(new FileReader(inputFileName));
            String line;

            StringBuilder temp = new StringBuilder();
            while ((line = adventInput.readLine()) != null) {
                if (!line.isBlank())
                    temp.append(line).append(" ");
                else {
                    lines.add(temp.toString());
                    temp.setLength(0);
                }
            }

            if (!temp.isEmpty())
                lines.add(temp.toString());

            adventInput.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}