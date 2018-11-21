package ca.jbrains.pos.test;

import io.vavr.collection.LinearSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.After;
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

    @After
    public void resetSystemIn() throws Exception {
        System.setIn(productionStdin);
    }

    @Test
    public void oneLineEndingInNoLineSeparator() throws Exception {
        simulateStdinWithText("::the only line::");

        Assert.assertEquals(
                List.of("::the only line::"),
                streamAsLines(System.in)
        );
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
        simulateStdinWithText(linesOf(List.of("::line 1::", "::line 2::", "::line 3::")));

        Assert.assertEquals(
                List.of("::line 1::", "::line 2::", "::line 3::"),
                streamAsLines(System.in)
        );
    }

    @Test
    public void severalEmptyLinesEndingInALineSeparator() throws Exception {
        simulateStdinWithText(linesOf(Stream.continually("").take(5)));

        Assert.assertEquals(
                Stream.continually("").take(5),
                streamAsLines(System.in)
        );
    }

    private void simulateStdinWithText(String text) {
        System.setIn(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8)));
    }

    // REFACTOR Rewrite this to use foldLeft() with a new StringBuilder as the "zero" and appendLine() as "add".
    private String linesOf(LinearSeq<String> lines) {
        StringBuilder stringBuilder = new StringBuilder();
        // Use forEach() in order to actually apply the side-effects as they happen.
        // map() is lazy, so we would need to collect() to resulting Stream in order to
        // apply the side-effects.
        lines.forEach(line -> stringBuilder.append(line).append(System.lineSeparator()));
        return stringBuilder.toString();
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
