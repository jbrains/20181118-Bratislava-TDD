package ca.jbrains.pos.test;

public class Display {
    public String text;

    public String getText() {
        return text;
    }

    public void displayScannedEmptyBarcodeMessage() {
        this.text = "Scanning error: empty barcode";
    }

    public void displayProductNotFoundMessage(String barcode) {
        this.text = String.format("Product not found: %s", barcode);
    }

    public void displayPrice(String priceAsText) {
        this.text = priceAsText;
    }

    public void displayNoPurchaseIsInProgressMessage() {
        this.text = "No purchase is in progress. Please scan a product.";
    }
}
