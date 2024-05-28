package ee.taltech.iti0202.exam;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Exam {
    /**
     * Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray
     * in non-decreasing order, then the whole array will be sorted in non-decreasing order.
     * Find the shortest such subarray and return its length.
     *
     * findUnsortedSubarray({2, 6, 4, 8, 10, 9, 15}) => 5
     * findUnsortedSubarray({1, 2, 3, 4}) => 0
     * findUnsortedSubarray({1, 2, 4, 6, 11, 7, 12, 13}) => 2
     */
    public static int findUnsortedSubarray(int[] nums) {
        int[] sorted = Arrays.stream(nums).sorted().toArray();

        if (Arrays.equals(nums, sorted)) {
            return 0;
        }

        List<Integer> nums2 = new LinkedList<>();
        List<Integer> sorted2 = new LinkedList<>();
        List<Integer> result = new LinkedList<>();

        for (Integer num : nums) {
            nums2.add(num);
        }

        for (Integer num : sorted) {
            sorted2.add(num);
            result.add(num);
        }
        boolean dontReadFront = false;
        boolean dontReadBack = false;

        for (int i = 0; i < nums2.size(); i++) {

            if (dontReadFront && dontReadBack) {
                return result.size();
            }

            if (Objects.equals(nums2.get(i), sorted2.get(i))
                    && Objects.equals(nums2.get(nums2.size() - 1 - i), sorted2.get(sorted2.size() - 1 - i))
                    && !dontReadBack && !dontReadFront) {

                result.remove(sorted2.get(i));
                result.remove(sorted2.get(sorted2.size() - i - 1));

            } else if (!Objects.equals(nums2.get(i), sorted2.get(i))
                    && Objects.equals(nums2.get(nums2.size() - 1 - i), sorted2.get(sorted2.size() - 1 - i))
                    && !dontReadBack) {

                result.remove(sorted2.get(sorted2.size() - i - 1));
                dontReadFront = true;

            } else if (Objects.equals(nums2.get(i), sorted2.get(i))
                    && !Objects.equals(nums2.get(nums2.size() - 1 - i), sorted2.get(sorted2.size() - 1 - i))
                    && !dontReadFront) {

                result.remove(sorted2.get(i));
                dontReadBack = true;

            } else if (!Objects.equals(nums2.get(i), sorted2.get(i))
                    && !Objects.equals(nums2.get(nums2.size() - 1 - i), sorted2.get(sorted2.size() - 1 - i))) {

                dontReadBack = true;
                dontReadFront = true;
            } else if (!Objects.equals(nums2.get(i), sorted2.get(i))
                    && Objects.equals(nums2.get(nums2.size() - 1 - i), sorted2.get(sorted2.size() - 1 - i))
                    && dontReadBack) {

                return result.size();

            } else if (Objects.equals(nums2.get(i), sorted2.get(i))
                    && !Objects.equals(nums2.get(nums2.size() - 1 - i), sorted2.get(sorted2.size() - 1 - i))
                    && dontReadFront) {

                return result.size();

            }
        }
        return result.size();
    }

    /**
     * Given two strings, word and a separator sep,
     * return a big string made of count occurrences of the word,
     * separated by the separator string.
     *
     * repeatSeparator("Word", "X", 3) => "WordXWordXWord"
     * repeatSeparator("This", "And", 2) => "ThisAndThis"
     * repeatSeparator("This", "And", 1) => "This"
     */
    public static String repeatSeparator(String word, String sep, int count) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i <= count - 1; i++) {
            if (i + 1 == count) {
                result.append(word);
            } else {
                result.append(word);
                result.append(sep);
            }
        }
        return result.toString();
    }

}
