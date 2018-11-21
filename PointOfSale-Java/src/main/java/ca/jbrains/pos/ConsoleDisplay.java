package ca.jbrains.pos;

public class ConsoleDisplay implements Display {
    private final RenderTextToConsole renderTextToConsole = new RenderTextToConsole();

    @Override
    public void displayPrice(Price price) {
        renderTextToConsole.render(String.format("EUR %.2f", price.inEuro()));
    }

    private void render(String text) {
        renderTextToConsole.render(text);
    }

    @Override
    public void displayProductNotFoundMessage(String barcode) {
        renderTextToConsole.render(String.format("Product not found: %s", barcode));
    }

    @Override
    public void displayScannedEmptyBarcodeMessage() {
        renderTextToConsole.render(String.format("Scanning error: empty barcode"));
    }
}
