package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Formatter;
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
            put("12345", MonetaryAmount.euroCents(750).format());
        }}));
        sale.onBarcode("12345");

        sale.onTotal();

        Assert.assertEquals("Total: EUR 7.50", display.getText());
    }

    public static class MonetaryAmount {
        private final int euroCentsValue;

        public MonetaryAmount(int euroCentsValue) {
            this.euroCentsValue = euroCentsValue;
        }

        public static MonetaryAmount euroCents(int euroCentsValue) {
            return new MonetaryAmount(euroCentsValue);
        }

        private double inEuro() {
            return euroCentsValue / 100.0d;
        }

        public String format() {
            return String.format("EUR %.2f", inEuro());
        }
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

    @Ignore("refactoring to be able to do arithmetic with prices")
    @Test
    public void severalBarcodesScannedAndAllProductsFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "EUR 7.50");
            put("23456", "EUR 12.95");
            put("34567", "EUR 18.65");
        }}));
        sale.onBarcode("12345");
        sale.onBarcode("23456");
        sale.onBarcode("34567");

        sale.onTotal();

        Assert.assertEquals("Total: EUR 39.10", display.getText());
    }
}
