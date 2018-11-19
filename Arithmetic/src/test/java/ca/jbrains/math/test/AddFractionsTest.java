package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assert.assertEquals(new Fraction(0), sum);
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(2).plus(new Fraction(0));
        Assert.assertEquals(new Fraction(2), sum);
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(5));
        Assert.assertEquals(new Fraction(5), sum);
    }

    @Test
    public void nonZeroIntegers() throws Exception {
        Fraction sum = new Fraction(4).plus(new Fraction(8));
        Assert.assertEquals(new Fraction(12), sum);
    }

    @Test
    public void nonIntegersWithTheSameDenominator() throws Exception {
        Fraction sum = new Fraction(3, 5)
                .plus(new Fraction(4, 5));

        Assert.assertEquals(new Fraction(7, 5), sum);
    }

    @Test
    public void relativelyPrimeDenominators() throws Exception {
        Fraction sum = new Fraction(1, 4)
                .plus(new Fraction(1, 5));
        Assert.assertEquals(new Fraction(9, 20), sum);
    }

    @Test
    public void denominatorsWithACommonFactor() throws Exception {
        Fraction sum = new Fraction(1, 8)
                .plus(new Fraction(1, 4));
        Assert.assertEquals(new Fraction(3, 8), sum);
    }

    public static class Fraction {

        private final int numerator;
        private final int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            return new Fraction(
                    this.numerator * that.denominator
                            + this.denominator * that.numerator,
                    this.denominator * that.denominator);
        }

        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator
                        == this.denominator * that.numerator;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return 378;
        }
    }
}

