package ca.jbrains.learn.io.vavr;

import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.Assert;
import org.junit.Test;

public class StreamTextContinuallyTest {
    @Test
    public void streamingContinuallyLetsMeTake5LinesOfTheSameThing() throws Exception {
        Assert.assertEquals(
                List.of("::line::", "::line::", "::line::", "::line::", "::line::"),
                Stream.continually("::line::").take(5).toList());
    }

    @Test
    public void streamEmptyStringContinuallyLetsMeTake5EmptyLines() throws Exception {
        Assert.assertEquals(
                List.of("", "", "", "", ""),
                Stream.continually("").take(5).toList());
    }
}
