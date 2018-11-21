package ca.jbrains.pos;

public class ConsoleDisplay implements Display {
    @Override
    public void displayPrice(Price price) {
        System.out.println(String.format("EUR %.2f", price.inEuro()));
    }

    @Override
    public void displayProductNotFoundMessage(String barcode) {

    }

    @Override
    public void displayScannedEmptyBarcodeMessage() {

    }
}
