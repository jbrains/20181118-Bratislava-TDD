package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public class SellMultipleItemsTest {
    @Test
    public void noItemsInPurchase() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display, null);

        // Intentionally do not scan any barcodes.

        sale.onTotal();

        Assert.assertEquals("No purchase is in progress. Please scan a product.", display.getText());
    }
}
