package hust.soict.itep.aims;

import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Playable;
import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED && !itemsOrdered.contains(media)) {
            itemsOrdered.add(media);
            System.out.println("Thêm media vào thành công");
        } else {
            System.out.println("Giỏ hàng đầy hoặc media đã tồn tại!");
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            itemsOrdered.remove(media);
            System.out.println("Đã gỡ bỏ media");
        } else {
            System.out.println("Media không tồn tại trong giỏ hàng");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void print() {
        System.out.println("Ordered Items:");
        int index = 1;
        for (Media media : itemsOrdered) {
            System.out.println(index++ + ". " + media.toString());
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
    }

    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with ID: " + id);
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println("Found: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with title: " + title);
        }
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Sorted by title then cost:");
        print();
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Sorted by cost then title:");
        print();
    }

    public void playMedia(String title) {
        for (Media media : itemsOrdered) {
            if (media.isMatch(title) && media instanceof Playable) {
                ((Playable) media).play();
                return;
            }
        }
        System.out.println("No playable media found with title: " + title);
    }

    public void clear() {
        itemsOrdered.clear();
        System.out.println("Cart cleared.");
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
}