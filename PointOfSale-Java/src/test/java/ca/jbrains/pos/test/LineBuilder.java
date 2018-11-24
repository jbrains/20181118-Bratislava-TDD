package ca.jbrains.pos.test;

import io.vavr.collection.LinearSeq;

public class LineBuilder {

    private final StringBuilder stringBuilder = new StringBuilder();

    public static String linesOf(LinearSeq<String> lines) {
        return lines.foldLeft(new LineBuilder(), LineBuilder::appendAsALine).toString();
    }

    public LineBuilder appendAsALine(String line) {
        this.stringBuilder.append(line).append(System.lineSeparator());
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}