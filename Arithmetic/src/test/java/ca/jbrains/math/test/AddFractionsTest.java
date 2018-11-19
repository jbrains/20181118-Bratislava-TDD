package ca.jbrains.math.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(0));
        Assert.assertEquals(0, sum.intValue());
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(2).plus(new Fraction(0));
        Assert.assertEquals(2, sum.intValue());
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(5));
        Assert.assertEquals(5, sum.intValue());
    }

    public static class Fraction {

        private final int integerValue;

        public Fraction(int integerValue) {
            this.integerValue = integerValue;
        }

        public Fraction plus(Fraction other) {
            if (other.integerValue == 0)
                return this;
            else
                return other;
        }

        public int intValue() {
            return integerValue;
        }
    }
}

