package ca.jbrains.pos.test;

public class MonetaryAmount {
    private final int euroCentsValue;

    public MonetaryAmount(int euroCentsValue) {
        this.euroCentsValue = euroCentsValue;
    }

    public static MonetaryAmount euroCents(int euroCentsValue) {
        return new MonetaryAmount(euroCentsValue);
    }

    private double inEuro() {
        return euroCentsValue / 100.0d;
    }

    public String format() {
        return String.format("EUR %.2f", inEuro());
    }
}
