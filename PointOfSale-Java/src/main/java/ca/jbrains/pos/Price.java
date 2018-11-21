package ca.jbrains.pos;

public class Price {
    private final int euroCentsValue;

    public Price(int euroCentsValue) {
        this.euroCentsValue = euroCentsValue;
    }

    public static Price euroCents(int euroCentsValue) {
        return new Price(euroCentsValue);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Price) {
            Price that = (Price) other;
            return this.euroCentsValue == that.euroCentsValue;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return euroCentsValue;
    }

    @Override
    public String toString() {
        return String.format("Price[euroCentsValue=%d]", euroCentsValue);
    }

    public double inEuro() {
        return euroCentsValue / 100.0d;
    }
}
