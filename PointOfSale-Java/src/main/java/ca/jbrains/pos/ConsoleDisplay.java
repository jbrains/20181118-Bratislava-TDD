package ca.jbrains.pos;

public class ConsoleDisplay implements Display {
    @Override
    public void displayPrice(Price price) {
        System.out.println(String.format("EUR %.2f", price.inEuro()));
    }

    @Override
    public void displayProductNotFoundMessage(String barcode) {
        System.out.println(String.format("Product not found: %s", barcode));
    }

    @Override
    public void displayScannedEmptyBarcodeMessage() {
        System.out.println(String.format("Scanning error: empty barcode"));
    }
}
