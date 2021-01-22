/*
    https://adventofcode.com/2020/day/4
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String inputFile = "input_day04.txt";
        List<String> input = readInputFile(inputFile);

        List<String> fieldsToCheck = new ArrayList<>() {{
            add("byr:"); add("iyr:"); add("eyr:"); add("hgt:");
            add("hcl:"); add("ecl:"); add("pid:"); add("cid:");
        }};

        System.out.println("The solution for the first part is:");
        System.out.println(part1Puzzle(input, fieldsToCheck));
        System.out.println();
        System.out.println("The solution for the second part is:");
        System.out.println(part2Puzzle(input, fieldsToCheck));
    }

    private static int part1Puzzle(List<String> input, List<String> fieldsToCheck) {
        fieldsToCheck.remove("cid:");
        long count = input.stream().filter(
                it -> fieldsToCheck.stream().allMatch(it::contains)
        ).count();

        return (int) count;
    }

    private static int part2Puzzle(List<String> input, List<String> fieldsToCheck) {
        fieldsToCheck.remove("cid:");

        long count = input.stream().filter(it -> {
            if (fieldsToCheck.stream().allMatch(it::contains)) {
                List<String> fields = Arrays.asList(it.split(" "));
                return checkFields(fields, fieldsToCheck);
            }
            return false;
        }).count();

        return (int) count;
    }

    /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.
    */
    private static boolean checkFields(List<String> fields, List<String> fieldName) {
        int count = 0;
        Collections.sort(fields); //order : byr - ecl - eyr - hcl - hgt - iyr - pid

        if (fields.get(1).startsWith("cid:")) {
            String temp = fields.get(8);
            fields.get(8) = fields.get(1)
        }

        System.out.println(fields);

        fields = fields.stream().map(it -> it.substring(4)).collect(Collectors.toList());

        System.out.println(fields);
        return false;
    }

    static List<String> readInputFile(String inputFileName) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader adventInput = new BufferedReader(new FileReader(inputFileName));
            String line;
            StringBuilder temp = new StringBuilder();

            while ((line = adventInput.readLine()) != null)
                if (line.isBlank()) {
                    lines.add(temp.toString());
                    temp.setLength(0);
                }
                else
                    temp.append(line).append(" ");

            adventInput.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}