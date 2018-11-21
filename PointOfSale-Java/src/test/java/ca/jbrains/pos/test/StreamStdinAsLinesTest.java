package ca.jbrains.pos.test;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class StreamStdinAsLinesTest {

    private InputStream productionStdin;

    @Before
    public void setUp() throws Exception {
        productionStdin = System.in;
    }

    @Test
    public void oneLineEndingInNoLineSeparator() throws Exception {
        simulateStdinWithText("::the only line::");

        Assert.assertEquals(
                List.of("::the only line::"),
                streamAsLines(System.in)
        );
    }

    private void simulateStdinWithText(String text) {
        System.setIn(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    public void noText() throws Exception {
        simulateStdinWithText("");

        Assert.assertEquals(
                List.of(),
                streamAsLines(System.in)
        );
    }

    @Test
    public void onlyLineSeparator() throws Exception {
        simulateStdinWithText(System.lineSeparator());

        Assert.assertEquals(
                List.of(""),
                streamAsLines(System.in)
        );
    }

    @Test
    public void singleLineEndingInLineSeparator() throws Exception {
        simulateStdinWithText("::the only line::" + System.lineSeparator());

        Assert.assertEquals(
                List.of("::the only line::"),
                streamAsLines(System.in)
        );
    }

    private Stream<String> streamAsLines(InputStream textInput) {
        return Stream.ofAll(new BufferedReader(new InputStreamReader(textInput)).lines());
    }
}
