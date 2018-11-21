package ca.jbrains.learn.java.io;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;

public class StreamLinesFromBufferedReaderText {
    @Test
    public void severalEmptyLinesEndingInALineSeparator() throws Exception {
        java.util.stream.Stream<String> readEmptyLines = new BufferedReader(new StringReader(
                nEmptyLines(5))
        ).lines();

        Assert.assertEquals(
                List.ofAll(Stream.continually("").take(5)),
                List.ofAll(readEmptyLines)
        );
    }

    private String nEmptyLines(int n) {
        return Stream.continually(System.lineSeparator()).take(n).fold("", (sum, each) -> sum + each);
    }
}
