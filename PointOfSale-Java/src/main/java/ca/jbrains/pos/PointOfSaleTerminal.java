package ca.jbrains.pos;

public class PointOfSaleTerminal {
    public static void main(String[] args) {
        new CommandLineTextClient().streamAsLines(System.in).forEach(System.out::println);
    }
}
