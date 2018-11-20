package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", MonetaryAmount.euroCents(765).format());
        }}));

        sale.onBarcode("12345");

        Assert.assertEquals("EUR 7.65", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("23456", MonetaryAmount.euroCents(1250).format());
        }}));

        sale.onBarcode("23456");

        Assert.assertEquals("EUR 12.50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", MonetaryAmount.euroCents(765).format());
            put("23456", MonetaryAmount.euroCents(1250).format());
        }}));

        sale.onBarcode("::missing barcode::");

        Assert.assertEquals("Product not found: ::missing barcode::", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", MonetaryAmount.euroCents(765).format());
            put("23456", MonetaryAmount.euroCents(1250).format());
        }}));

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }

}
