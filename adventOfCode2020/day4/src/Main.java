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
            add("byr:");
            add("iyr:");
            add("eyr:");
            add("hgt:");
            add("hcl:");
            add("ecl:");
            add("pid:");
            add("cid:");
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

        long count = input.stream().filter(it -> fieldsToCheck.stream().allMatch(it::contains) && checkFields(it))
                .count();

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
    private static boolean checkFields(String fields) {
        List<String> splitFields = Arrays.asList(fields.split(" "));

        List<String> eyeColor = new ArrayList<>() {{
            add("amb");
            add("blu");
            add("brn");
            add("gry");
            add("grn");
            add("hzl");
            add("oth");
        }};

        for (int i = 0; i < splitFields.size(); i++) {
            String[] keyValue = splitFields.get(i).split(":");
            switch (keyValue[0]) {
                case "byr":
                    try {
                        Integer value = Integer.parseInt(keyValue[1]);
                        if (value < 1920 || value > 2002)
                            return false;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    break;
                case "iyr":
                    try {
                        Integer value = Integer.parseInt(keyValue[1]);
                        if (value < 2010 || value > 2020)
                            return false;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    break;
                case "eyr":
                    try {
                        Integer value = Integer.parseInt(keyValue[1]);
                        if (value < 2020 || value > 2030)
                            return false;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                    break;
                case "hgt":
                    if (keyValue[1].endsWith("cm")) {
                        try {
                            Integer value = Integer.parseInt(keyValue[1].replace("cm", ""));
                            if (value < 150 || value > 193)
                                return false;
                            break;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    }
                    if (keyValue[1].endsWith("in")) {
                        try {
                            Integer value = Integer.parseInt(keyValue[1].replace("in", ""));
                            if (value < 59 || value > 76)
                                return false;
                            break;
                        } catch (NumberFormatException e) {
                            return false;
                        }
                    } else
                        return false;
                case "hcl":
                    if (keyValue[1].length() != 7 || !keyValue[1].startsWith("#") || !keyValue[1].substring(1).matches("^[a-z0-9]*$"))
                        return false;
                    break;
                case "ecl":
                    if (!eyeColor.contains(keyValue[1]))
                        return false;
                    break;
                case "pid":
                    if (keyValue[1].length() != 9 || !keyValue[1].matches("^[0-9]*$"))
                        return false;
                    break;
                default:
                    break;
            }
        }

        return true;
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
                } else
                    temp.append(line).append(" ");

            if (!temp.isEmpty())
                lines.add(temp.toString());

            adventInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}