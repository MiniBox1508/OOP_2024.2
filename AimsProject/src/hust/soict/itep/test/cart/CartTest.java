package hust.soict.itep.test.cart;

import hust.soict.itep.aims.Cart;
import hust.soict.itep.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        cart.addMedia(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addMedia(dvd2);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", null, 0, 18.99f);
        cart.addMedia(dvd3);

        cart.print();

        System.out.println("\nSearch by ID (ID=2):");
        cart.searchById(2);

        System.out.println("\nSearch by Title (Star Wars):");
        cart.searchByTitle("Star Wars");

        System.out.println("\nSearch by Title (Non-existent):");
        cart.searchByTitle("Harry Potter");
    }
}
