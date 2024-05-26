package ee.taltech.iti0202.exam;

import java.util.Arrays;

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

        int result = 0;
        int currentNumber = 0;
        int sign = 1;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * currentNumber;
                currentNumber = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * currentNumber;
                currentNumber = 0;
                sign = -1;
            }
        }

        result += sign * currentNumber;

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
                } else if (numbers[i - 1] < numbers[i + 1]) {
                    numbers[i] = numbers[i + 1];
                } else if (numbers[i - 1] == numbers[i + 1]) {
                    numbers[i] = numbers[i - 1];
                }
            }
            }
        return numbers;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(notAlone(new int[]{1, 2, 3}, 2)));
        System.out.println(Arrays.toString(notAlone(new int[]{1, 2, 3, 2, 5, 2}, 2)));
        System.out.println(Arrays.toString(notAlone(new int[]{3, 3, 4, 5, 3, 5, 4, 6}, 3)));
    }
}
