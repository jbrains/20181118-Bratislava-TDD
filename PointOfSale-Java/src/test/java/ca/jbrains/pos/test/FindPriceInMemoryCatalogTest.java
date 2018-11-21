package ca.jbrains.pos.test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;

import java.util.HashMap;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(String barcode, Price matchingPrice) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("not " + barcode, Price.euroCents(0));
            put(barcode, matchingPrice);
            put("certainly not " + barcode, Price.euroCents(0));
            put("I told you, not " + barcode + ", you idiot", Price.euroCents(0));
        }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("not " + barcodeToAvoid, Price.euroCents(0));
            put("certainly not " + barcodeToAvoid, Price.euroCents(0));
            put("I told you, not " + barcodeToAvoid + ", you idiot", Price.euroCents(0));
        }});
    }

}
