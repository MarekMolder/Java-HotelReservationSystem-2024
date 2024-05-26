package ee.taltech.iti0202.exam;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Exam {

    /**
     * Calculate the result of the expression.
     *
     * The input contains of only digits, plus and minus sign.
     *
     * "1+2" => 3
     * "3" => 3
     * "" => 0
     * "-4+5" => 1
     */
    public static int calculate(String text) {
        List<String> numbers = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<String> math = new ArrayList<>(List.of("+", "-"));
        char[] newText = text.toCharArray();
        StringBuilder value = new StringBuilder();
        Integer total = 0;

        if (text.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < newText.length; i++) {
            String s = String.valueOf(newText[i]);
            if (numbers.contains(s)) {
                value.append(s);
            } else if (s.equals("+")) {
                Integer newValue = Integer.valueOf(value.toString());
                total = total + newValue;
                value.setLength(0);
            } else if (Objects.equals(String.valueOf(newText[0]), "-")) {
                value.append(s);
            } else if (s.equals("-")) {
                Integer newValue = Integer.valueOf(value.toString());
                total = total - newValue;
                value.setLength(0);
            }
        }
        total = total + Integer.parseInt(value.toString());
        return total;
    }

    /**
     *
     * We'll say that an element in an array is "alone"
     * if there are values before and after it, and those values are different from it.
     *
     * Return a version of the given array where every instance of the given value
     * which is alone is replaced by whichever value to its left or right is larger.
     *
     * notAlone([1, 2, 3], 2) => [1, 3, 3]
     * notAlone([1, 2, 3, 2, 5, 2], 2) => [1, 3, 3, 5, 5, 2]
     * notAlone([3, 4], 3) => [3, 4]
     */
    public static int[] notAlone(int[] numbers, int toReplace) {
        for (int i = 1; i < numbers.length - 1; i++) {
            if (numbers[i] == toReplace) {
                if (numbers[i - 1] == toReplace || numbers[i + 1] == toReplace) {
                    continue;
                } else if (numbers[i - 1] > numbers[i + 1]) {
                    numbers[i] = numbers[i - 1];
                } else if (numbers[i -1] < numbers[i + 1]) {
                    numbers[i] = numbers[i + 1];
                }
            }
            }
        return numbers;
    }

    public static void main(String[] args) {
        System.out.println(calculate("-4+5-6"));
    }
}