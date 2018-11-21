package ca.jbrains.pos;

import java.io.PrintStream;

public class RenderTextToPrintStream implements RenderText {

    private final PrintStream out;

    public RenderTextToPrintStream(PrintStream out) {
        this.out = out;
    }

    @Override
    public void render(String text) {
        out.println(text);
    }
}