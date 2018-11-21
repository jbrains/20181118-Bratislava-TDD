package ca.jbrains.pos.test;

import ca.jbrains.pos.CommandLineTextClient;
import io.vavr.collection.LinearSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class StreamStdinAsLinesTest {

    private final CommandLineTextClient commandLineTextClient = new CommandLineTextClient();
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
                commandLineTextClient.streamAsLines(System.in)
        );
    }

    @Test
    public void noText() throws Exception {
        simulateStdinWithText("");

            Assert.assertEquals(
                List.of(),
                    commandLineTextClient.streamAsLines(System.in)
        );
    }

    @Test
    public void onlyOneLineSeparator() throws Exception {
        simulateStdinWithText(System.lineSeparator());

        Assert.assertEquals(
                List.of(""),
                commandLineTextClient.streamAsLines(System.in)
        );
    }

    @Test
    public void singleLineEndingInALineSeparator() throws Exception {
        simulateStdinWithText("::the only line::" + System.lineSeparator());

        Assert.assertEquals(
                List.of("::the only line::"),
                commandLineTextClient.streamAsLines(System.in)
        );
    }

    @Test
    public void severalLinesEndingInALineSeparator() throws Exception {
        simulateStdinWithText(linesOf(List.of("::line 1::", "::line 2::", "::line 3::")));

        Assert.assertEquals(
                List.of("::line 1::", "::line 2::", "::line 3::"),
                commandLineTextClient.streamAsLines(System.in)
        );
    }

    @Test
    public void severalEmptyLinesEndingInALineSeparator() throws Exception {
        simulateStdinWithText(linesOf(Stream.continually("").take(5)));

        Assert.assertEquals(
                Stream.continually("").take(5),
                commandLineTextClient.streamAsLines(System.in)
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
}
