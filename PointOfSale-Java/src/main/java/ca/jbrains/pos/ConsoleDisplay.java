package ca.jbrains.pos;

public class ConsoleDisplay implements Display {
    private final RenderText renderText;

    public ConsoleDisplay(RenderText renderText) {
        this.renderText = renderText;
    }

    @Override
    public void displayPrice(Price price) {
        renderText.render(String.format("EUR %.2f", price.inEuro()));
    }

    @Override
    public void displayProductNotFoundMessage(String barcode) {
        renderText.render(String.format("Product not found: %s", barcode));
    }

    @Override
    public void displayScannedEmptyBarcodeMessage() {
        renderText.render(String.format("Scanning error: empty barcode"));
    }
}
