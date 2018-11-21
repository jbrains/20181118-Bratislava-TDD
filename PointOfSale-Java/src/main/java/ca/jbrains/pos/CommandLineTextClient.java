package ca.jbrains.pos;

import io.vavr.collection.Stream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

// CONTRACT Turns multiline text from an InputStream into a Stream of lines of text.
public class CommandLineTextClient {

    // CONTRACT Ignores the trailing line separator, EXCEPT the case
    // where the entire text is only 1 line separator.
    // CONTRACT A single line separator streams as 1 empty line, instead of 0 lines.
    // CONTRACT Empty string streams as 0 lines.
    public Stream<String> streamAsLines(InputStream textInput) {
        return Stream.ofAll(new BufferedReader(new InputStreamReader(textInput)).lines());
    }
}