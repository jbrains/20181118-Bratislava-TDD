package ca.jbrains.pos;

public class ConsoleDisplay implements Display {
    @Override
    public void displayPrice(Price price) {
        render(String.format("EUR %.2f", price.inEuro()));
    }

    private void render(String text) {
        System.out.println(text);
    }

    @Override
    public void displayProductNotFoundMessage(String barcode) {
        render(String.format("Product not found: %s", barcode));
    }

    @Override
    public void displayScannedEmptyBarcodeMessage() {
        render(String.format("Scanning error: empty barcode"));
    }
}
