package ee.taltech.iti0202.exam;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (text.isEmpty()) {
            return 0;
        }

        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+|\\+|\\-").matcher(text);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        if (tokens.get(0).equals("-")) {
            tokens.set(1, "-" + tokens.get(1));
            tokens.removeFirst();
        }

        int result = Integer.parseInt(tokens.get(0));

        for (int i = 1; i < tokens.size(); i += 2) {
            String operator = tokens.get(i);
            int nextNumber = Integer.parseInt(tokens.get(i + 1));

            if (operator.equals("+")) {
                result += nextNumber;
            } else if (operator.equals("-")) {
                result -= nextNumber;
            }
        }

        return result;
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