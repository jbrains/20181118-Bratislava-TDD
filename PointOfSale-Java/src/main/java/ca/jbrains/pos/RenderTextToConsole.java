package ca.jbrains.pos;

public class RenderTextToConsole implements RenderText {
    @Override
    public void render(String text) {
        System.out.println(text);
    }
}