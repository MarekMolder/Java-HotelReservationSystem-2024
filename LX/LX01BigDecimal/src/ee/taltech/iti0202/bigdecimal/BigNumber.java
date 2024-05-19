package ee.taltech.iti0202.bigdecimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class BigNumber {
    /**
     * Multiply 2 int values and return product in BigInteger
     * @param factor1 first factor
     * @param factor2 second factor
     * @return factor1 x factor2
     */
    public BigInteger multiplyBigInteger(int factor1, int factor2) {
        return BigInteger.valueOf((long) factor1 * factor2);
    }

    /**
     * Divide 2 values and return quotient in BigInteger
     * If divisor is 0, return 0
     * @param dividend dividend
     * @param divisor divisor
     * @return dividend / divisor
     */
    public BigInteger divideBigInteger(BigInteger dividend, int divisor) {
        if (divisor == 0) {
            return BigInteger.ZERO; // return 0 if divisor is 0
        } else {
            return dividend.divide(BigInteger.valueOf(divisor));
        }
    }

    /**
     * Add 2 values and return sum in BigInteger
     * @param add1 first value
     * @param add2 second value
     * @return add1 + add2
     */
    public BigInteger addBigInteger(int add1, int add2) {
        return BigInteger.valueOf(add1).add(BigInteger.valueOf(add2));
    }

    /**
     * Subtract 2 values and return difference in BigInteger
     * @param minuend first value
     * @param subtrahend second value
     * @return value1 - value2
     */
    public BigInteger subtractBigInteger(BigInteger minuend, int subtrahend) {
        return minuend.subtract(BigInteger.valueOf(subtrahend));
    }

    /**
     * Multiply double value with int multiplier, round according to rounding
     * and return in BigDecimal
     * @param value value to calculate
     * @param multiplier multiplier to use
     * @param rounding rounding to use
     * @return value multiplied by multiplier and rounded by rounding
     */
    public BigDecimal multiplyAndRoundBigDecimal(double value, int multiplier, int rounding) {
        BigDecimal result = BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(multiplier));
        MathContext mc = new MathContext(rounding);
        return result.round(mc);
    }

    /**
     * Calculate n factorial and return in BigInteger
     * If n < 0, it should return 1
     * @param n n-th factorial to calculate
     * @return n-th factorial
     */
    public BigInteger factorial(int n) {
        if (n < 0) {
            return BigInteger.ONE;
        } else if (n == 0) {
            return BigInteger.ONE;
        } else {
            BigInteger result = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }
    }

    /**
     * Calculate base to the power of exponent and return in BigInteger
     * @param base base
     * @param exponent exponent
     * @return base to the power of exponent
     */
    public BigInteger power(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException();
        }

        if (exponent == 0) {
            return BigInteger.ONE;
        }

        BigInteger result = BigInteger.ONE;

        for (int i = 0; i < exponent; i++) {
            result = result.multiply(BigInteger.valueOf(base));
        }

        return result;
    }

    /**
     * Round val1 and val2 with the rounding given, and check if they are equal after that
     * @param val1 first value to round
     * @param val2 second value to round
     * @param rounding rounding to use
     * @return true or false if val1 and val2 are equal after rounded with rounding
     */
    public boolean isSame(BigDecimal val1, BigDecimal val2, int rounding) {
        MathContext mc = new MathContext(rounding);
        BigDecimal bigVal1 = val1.round(mc);
        BigDecimal bigVal2 = val2.round(mc);

        return bigVal1.compareTo(bigVal2) == 0;
    }

    /**
     *
     * @param n n-th fib number to calculate
     * @return n-th fib number value
     */
    public BigInteger fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The input cannot be negative.");
        }
        if (n == 0) {
            return BigInteger.ZERO;
        }
        if (n == 1) {
            return BigInteger.ONE;
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger c;

        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }

        return b;
    }

    /**
     *
     * @param n n-th luc number to calculate
     * @return n-th lucas number
     */
    public BigInteger lucas(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("The input cannot be negative.");
        }
        if (n == 0) {
            return BigInteger.valueOf(2);
        }
        if (n == 1) {
            return BigInteger.ONE;
        }

        BigInteger a = BigInteger.valueOf(2);
        BigInteger b = BigInteger.ONE;
        BigInteger c;

        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }

        return b;
    }
}
