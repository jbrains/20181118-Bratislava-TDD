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
    public void onlyOneLineSeparator() throws Exception {
        simulateStdinWithText(System.lineSeparator());

        Assert.assertEquals(
                List.of(""),
                streamAsLines(System.in)
        );
    }

    @Test
    public void singleLineEndingInALineSeparator() throws Exception {
        simulateStdinWithText("::the only line::" + System.lineSeparator());

        Assert.assertEquals(
                List.of("::the only line::"),
                streamAsLines(System.in)
        );
    }

    @Test
    public void severalLinesEndingInALineSeparator() throws Exception {
        simulateStdinWithText(new StringBuilder()
                .append("::line 1::").append(System.lineSeparator())
                .append("::line 2::").append(System.lineSeparator())
                .append("::line 3::").append(System.lineSeparator())
                .toString());

        Assert.assertEquals(
                List.of("::line 1::", "::line 2::", "::line 3::"),
                streamAsLines(System.in)
        );
    }

    // CONTRACT Turns multiline text into a Stream of lines of text.
    // CONTRACT Ignores the trailing line separator, EXCEPT the case
    // where the entire text is only 1 line separator.
    // CONTRACT A single line separator streams as 1 empty line, instead of 0 lines.
    // CONTRACT Empty string streams as 0 lines.
    private Stream<String> streamAsLines(InputStream textInput) {
        return Stream.ofAll(new BufferedReader(new InputStreamReader(textInput)).lines());
    }
}
