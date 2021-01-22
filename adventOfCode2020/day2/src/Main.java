/*
    https://adventofcode.com/2020/day/2
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFile = "src/input_day02.txt";
        List<String> input = readInputFile(inputFile);

        System.out.println("The solution for the first part is:");
        System.out.println(partPuzzle(input, 1));
        System.out.println();
        System.out.println("The solution for the second part is:");
        System.out.println(partPuzzle(input, 2));
    }


    private static int partPuzzle(List<String> list, int puzzlePart) {
        int count = 0;

        for (String item: list) {
            if (checkPassword(item, puzzlePart))
                count++;
        }
        return count;
    }

    static boolean checkPassword (String password, int puzzlePart) {
        String[] parts = password.split(": | |-");
        int min = Integer.parseInt(parts[0]);
        int max = Integer.parseInt(parts[1]);
        char character = parts[2].charAt(0);

        if (puzzlePart == 1) {
            int count = countLetterOccurrence(parts[3], character);

            if (count >= min && count <= max)
                return true;
            return false;
        }
        else if (character == parts[3].charAt(min - 1) ^ character == parts[3].charAt(max - 1))
                return true;
        return false;
    }

    private static int countLetterOccurrence(String word, char letter) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter)
                count++;
        }
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
        catch (IOException e){
            e.printStackTrace();
        }

        return lines;
    }
}