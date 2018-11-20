package ca.jbrains.pos.test;

import java.util.ArrayList;
import java.util.List;

public class Sale {
    private final List<String> purchasedItems;
    private Display display;
    private final Catalog catalog;

    public Sale(Display display, Catalog catalog) {
        this.display = display;
        this.catalog = catalog;
        this.purchasedItems = new ArrayList<>();
    }

    public void onBarcode(String barcode) {
        if ("".equals(barcode)) {
            display.displayScannedEmptyBarcodeMessage();
            return;
        }

        String priceAsText = catalog.findPrice(barcode);
        if (priceAsText == null) {
            display.displayProductNotFoundMessage(barcode);
        } else {
            purchasedItems.add(priceAsText);
            display.displayPrice(priceAsText);
        }
    }

    public void onTotal() {
        if (purchasedItems.isEmpty())
            display.displayNoPurchaseIsInProgressMessage();
        else
            display.text = String.format("Total: %s", "EUR 7.50");
    }
}
