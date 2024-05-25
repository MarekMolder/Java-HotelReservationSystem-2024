package ee.taltech.iti0202.exam;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    /**
     * 01
     *
     * For each multiple of 10 in the given array,
     * change all the values following it to be that multiple of 10,
     * until encountering another multiple of 10.
     * So {2, 10, 3, 4, 20, 5} yields {2, 10, 10, 10, 20, 20}.
     *
     * tenRun([2, 10, 3, 4, 20, 5]) => [2, 10, 10, 10, 20, 20]
     * tenRun([10, 1, 20, 2]) => [10, 10, 20, 20]
     * tenRun([10, 1, 9, 20]) => [10, 10, 10, 20]
     */
    public static List<Integer> tenRun(List<Integer> nums) {
        List<Integer> result = new ArrayList<>(nums);
        int num = -1;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) % 10 == 0) {
                num = nums.get(i);
            }
            if (num != -1) {
                result.set(i, num);
            }
        }
        return result;
    }

    /**
     * 02
     *
     * Write a method that analyzes input String and returns all pairs of
     * same letter that is present as lower-case and upper-case in input String.
     * Returned letter pairs have to be in alphabetic order.
     * If there are multiple same letter pairs, then return only one. If there are no suitable pairs, return "".
     * Take latin alphabet 'a' - 'z' as base.
     * mixedPairs("abcABD") => "AaBb" (a and b are represented by both lowe and upper cased letter)
     * mixedPairs("aaaAAAaaaa") => "Aa"
     * mixedPairs("tere") => ""
     * mixedPairs("bBaacA") => "AaBb" (result is in alphabetic order, although in input String, b is earlier than a).
     * @param input
     * @return
     */
    public static String mixedPairs(String input) {
        List<String> lowLetters = new ArrayList<>(List.of("a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));

        List<String> capsLetters = new ArrayList<>(List.of("A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lowLetters.size(); i++) {
            if (input.contains(lowLetters.get(i)) && input.contains(capsLetters.get(i))) {
                result.append(capsLetters.get(i));
                result.append(lowLetters.get(i));
            }
        }
        return result.toString();
    }

}
