package hust.soict.itep.test.store;

import hust.soict.itep.aims.media.DigitalVideoDisc;
import hust.soict.itep.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd1); // Thử thêm đĩa đã tồn tại

        System.out.println("\nDanh sách đĩa trong cửa hàng:");
        for (DigitalVideoDisc disc : store.getItemsInStore()) {
            System.out.printf("DVD - %s - %s - %s - %d: %.2f $\n",
                    disc.getTitle(), disc.getCategory(),
                    disc.getDirector() != null ? disc.getDirector() : "Unknown",
                    disc.getLength(), disc.getCost());
        }

        store.removeMedia(dvd1);
        store.removeMedia(dvd1); // Thử xóa đĩa không tồn tại

        System.out.println("\nDanh sách đĩa sau khi xóa:");
        for (DigitalVideoDisc disc : store.getItemsInStore()) {
            System.out.printf("DVD - %s - %s - %s - %d: %.2f $\n",
                    disc.getTitle(), disc.getCategory(),
                    disc.getDirector() != null ? disc.getDirector() : "Unknown",
                    disc.getLength(), disc.getCost());
        }
    }
}