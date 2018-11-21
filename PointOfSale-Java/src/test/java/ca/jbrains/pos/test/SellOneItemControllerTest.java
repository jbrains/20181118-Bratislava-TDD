package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.SellOneItemController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SellOneItemControllerTest {

    private Catalog catalog;
    private Display display;

    @Before
    public void createCollaborators() throws Exception {
        catalog = Mockito.mock(Catalog.class);
        display = Mockito.mock(Display.class);
    }

    @Test
    public void productFound() throws Exception {
        Price matchingPrice = anyValidPrice();
        Mockito.when(catalog.findPrice("::barcode::")).thenReturn(matchingPrice);

        new SellOneItemController(catalog, display).onBarcode("::barcode::");

        Mockito.verify(display).displayPrice(matchingPrice);
    }

    private Price anyValidPrice() {
        return Price.euroCents(795);
    }

    @Test
    public void productNotFound() throws Exception {
        Mockito.when(catalog.findPrice("::missing barcode::")).thenReturn(null);

        new SellOneItemController(catalog, display).onBarcode("::missing barcode::");

        Mockito.verify(display).displayProductNotFoundMessage("::missing barcode::");
    }

    @Test
    public void emptyBarcode() throws Exception {
        new SellOneItemController(null, display).onBarcode("");

        Mockito.verify(display).displayScannedEmptyBarcodeMessage();
    }
}
