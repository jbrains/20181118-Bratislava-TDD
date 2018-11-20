package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class SellMultipleItemsTest {
    @Test
    public void noItemsInPurchase() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, null);

        // Intentionally do not scan any barcodes.

        sale.onTotal();

        Assert.assertEquals("No purchase is in progress. Please scan a product.", display.getText());
    }

    @Test
    public void oneBarcodeScannedAndProductFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "EUR 7.50");
        }}));
        sale.onBarcode("12345");

        sale.onTotal();

        Assert.assertEquals("Total: EUR 7.50", display.getText());
    }

    @Test
    public void oneBarcodeScannedButProductNotFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "EUR 7.50");
        }}));
        sale.onBarcode("::missing barcode::");

        sale.onTotal();

        Assert.assertEquals("No purchase is in progress. Please scan a product.", display.getText());
    }
}
