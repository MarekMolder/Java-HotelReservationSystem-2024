package ee.taltech.iti0202.revising;

import java.util.*;

public class Revising {

    /**
     * Return true if the list contains, somewhere, three increasing adjacent numbers 
     * like .... 4, 5, 6, ... or 23, 24, 25.
     *
     * tripleUp(List.of(1, 4, 5, 6, 2)) => true
     * tripleUp(List.of(1, 2, 3)) => true
     * tripleUp(List.of(1, 2, 4)) => false
     *
     * @param numbers List of integers.
     * @return Whether the list contains adjacent numbers.
     */
    public static boolean tripleUp(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.size() - 2 <= i) {
                break;
            }
            List<Integer> sublist = numbers.subList(i, i + 3);
            if (sublist.getLast() - 1 == sublist.get(1) && sublist.get(1) - 1 == sublist.getFirst()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Given three ints, a b c, one of them is small, one is medium and one is large.
     *
     * Return true if the three values are evenly spaced,
     * so the difference between small and medium is the same as the difference between medium and large.
     *
     * evenlySpaced(2, 4, 6) => true
     * evenlySpaced(4, 6, 2) => true
     * evenlySpaced(4, 6, 3) => false
     */
    public static boolean evenlySpaced(int a, int b, int c) {
        ArrayList<Integer> list = new ArrayList<>(List.of(a, b, c));
        Integer max = list.stream().max(Integer::compare).get();
        Integer min = list.stream().min(Integer::compare).get();
        list.remove(max);
        list.remove(min);
        Integer mid = list.getFirst();

        if (max - mid == mid - min) {
            return true;
        }
        return false;
    }

    /**
     * Given a list of integers,
     * return true if the value 3 appears in the list exactly 3 times,
     * and no 3's are next to each other.
     *
     * haveThree([3, 1, 3, 1, 3]) => true
     * haveThree([3, 1, 3, 3]) => false
     * haveThree([3, 4, 3, 3, 4]) => false
     */
    public static boolean haveThree(List<Integer> numbers) {
        Integer count = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.size() - 1 <= i) {
                break;
            }
            List<Integer> sublist = numbers.subList(i, i + 2);
            if (sublist.getFirst() == 3 && sublist.getLast() == 3) {
                return false;
            }
        }
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == 3) {
                count++;
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    /**
     * Given a string, consider the prefix string made of the first N chars of the string.
     * Does that prefix string appear somewhere else in the string.
     * Assume that the string is not empty and that N is in the range 1 .. str.length().
     *
     * prefixExistsAgain("abXXabc", 1) => true
     * prefixExistsAgain("abXXabc", 2) => true
     * prefixExistsAgain("abXXabc", 3) => false
     * prefixExistsAgain("ababa", 3) => true
     */
    public static boolean prefixExistsAgain(String text, int n) {
        char[] chars = text.toCharArray();
        char[] sublist = Arrays.copyOfRange(chars, 0, n);

        for (int z = 1; z < chars.length; z++) {
            if (chars.length - n + 1 <= z) {
                break;
            }
            char[] sublist2 = Arrays.copyOfRange(chars, z, z + n);

            if (Arrays.equals(sublist2, sublist)) {
            return true;}
        }
        return false;
    }

    /**
     * Given lists nums1 and nums2 of the same length,
     * for every element in nums1, consider the corresponding
     * element in nums2 (at the same index).
     * Return the count of the number of times
     * that the two elements differ by 2 or less, but are not equal.
     *
     * matchUp([1, 2, 3], [2, 3, 10]) => 2
     * matchUp([1, 2, 3], [2, 3, 5]) => 3
     * matchUp([1, 2, 3], [2, 3, 3]) => 2
     */
    public static int matchUp(List<Integer> a, List<Integer> b) {
        Integer length = a.size();
        Integer count = 0;
        for (int i = 0; i < length; i++) {
            if (a.get(i) - b.get(i) < 3 && a.get(i) - b.get(i) > 0) {
                count++;
            } else if (b.get(i) - a.get(i) < 3 && b.get(i) - a.get(i) > 0) {
                count++;
            }
        }
        return count;
    }
}
