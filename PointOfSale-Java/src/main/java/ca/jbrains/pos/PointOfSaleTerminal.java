package ca.jbrains.pos;

import io.vavr.collection.HashMap;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        SellOneItemController sellOneItemController = new SellOneItemController(
                new InMemoryCatalog(
                        HashMap.of(
                "4004764391257", Price.euroCents(189),
                "4004764391264", Price.euroCents(239),
                "8717868162116", Price.euroCents(229),
                "8595013615447", Price.euroCents(199)
        ).toJavaMap()),
                new TextDisplay(
                        new RenderTextToPrintStream(System.out)));

        new CommandLineTextClient()
                .streamAsLines(System.in)
                .forEach(sellOneItemController::onBarcode);
    }
}
