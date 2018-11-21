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
    public void one() throws Exception {
        System.setIn(new ByteArrayInputStream("::the only line::".getBytes(StandardCharsets.UTF_8)));

        Assert.assertEquals(
                List.of("::the only line::"),
                streamAsLines(System.in)
        );
    }

    private Stream<String> streamAsLines(InputStream textInput) {
        return Stream.ofAll(new BufferedReader(new InputStreamReader(textInput)).lines());
    }
}
