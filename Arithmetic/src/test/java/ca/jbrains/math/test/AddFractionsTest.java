package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assert.assertEquals(0, sum.getNumerator());
        Assert.assertEquals(1, sum.getDenominator());
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(2).plus(new Fraction(0));
        Assert.assertEquals(2, sum.getNumerator());
        Assert.assertEquals(1, sum.getDenominator());
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(5));
        Assert.assertEquals(5, sum.getNumerator());
        Assert.assertEquals(1, sum.getDenominator());
    }

    @Test
    public void nonZeroIntegers() throws Exception {
        Fraction sum = new Fraction(4).plus(new Fraction(8));
        Assert.assertEquals(12, sum.getNumerator());
        Assert.assertEquals(1, sum.getDenominator());
    }

    @Test
    public void nonIntegersWithTheSameDenominator() throws Exception {
        Fraction sum = new Fraction(3, 5)
                .plus(new Fraction(4, 5));

        Assert.assertEquals(7, sum.getNumerator());
        Assert.assertEquals(5, sum.getDenominator());
    }

    @Test
    public void relativelyPrimeDenominators() throws Exception {
        Fraction sum = new Fraction(1, 4)
                .plus(new Fraction(1, 5));

        Assert.assertEquals(9, sum.getNumerator());
        Assert.assertEquals(20, sum.getDenominator());
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
            if (this.denominator == that.denominator)
                return new Fraction(this.numerator + that.numerator, this.denominator);
            else
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
    }
}

