package ca.jbrains.pos.test;

public class LineBuilder {

    private final StringBuilder stringBuilder = new StringBuilder();

    public LineBuilder appendAsALine(String line) {
        this.stringBuilder.append(line).append(System.lineSeparator());
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

}