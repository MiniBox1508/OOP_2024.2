package hust.soict.itep.aims;

import hust.soict.itep.aims.media.*;
import hust.soict.itep.aims.store.Store;

import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Thêm dữ liệu mẫu
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        Book book1 = new Book("1984", "Fiction", 9.99f);
        book1.addAuthor("George Orwell");
        CompactDisc cd1 = new CompactDisc("Abbey Road", "Rock", null, "The Beatles", 14.99f);
        cd1.addTrack(new Track("Come Together", 260));
        cd1.addTrack(new Track("Something", 182));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);
        store.addMedia(cd1);

        showMenu();
    }

    public static void showMenu() {
        while (true) {
            System.out.println("AIMS: ");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    storeMenu();
                    break;
                case 2:
                    updateStoreMenu();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void storeMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter media title:");
                    String title = scanner.nextLine();
                    Media media = store.findMediaByTitle(title);
                    if (media != null) {
                        System.out.println(media.toString());
                        mediaDetailsMenu(media);
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 2:
                    System.out.println("Enter media title to add to cart:");
                    title = scanner.nextLine();
                    media = store.findMediaByTitle(title);
                    if (media != null) {
                        cart.addMedia(media);
                        System.out.println("Current cart items: " + cart.getItemsOrdered().size());
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter media title to play:");
                    title = scanner.nextLine();
                    cart.playMedia(title);
                    break;
                case 4:
                    cartMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void mediaDetailsMenu(Media media) {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            if (media instanceof Playable) {
                System.out.println("2. Play");
            }
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: " + (media instanceof Playable ? "0-1-2" : "0-1"));

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    System.out.println("Current cart items: " + cart.getItemsOrdered().size());
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void updateStoreMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add media");
            System.out.println("2. Remove media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter media title to add:");
                    String title = scanner.nextLine();
                    DigitalVideoDisc dvd = new DigitalVideoDisc(title);
                    store.addMedia(dvd);
                    break;
                case 2:
                    System.out.println("Enter media title to remove:");
                    title = scanner.nextLine();
                    Media media = store.findMediaByTitle(title);
                    if (media != null) {
                        store.removeMedia(media);
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void cartMenu() {
        while (true) {
            cart.print();
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter media in cart");
            System.out.println("2. Sort media in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3-4-5");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Filter by: 1. ID  2. Title");
                    int filterChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (filterChoice == 1) {
                        System.out.println("Enter ID:");
                        int id = scanner.nextInt();
                        cart.searchById(id);
                    } else if (filterChoice == 2) {
                        System.out.println("Enter title:");
                        String title = scanner.nextLine();
                        cart.searchByTitle(title);
                    } else {
                        System.out.println("Invalid filter choice.");
                    }
                    break;
                case 2:
                    System.out.println("Sort by: 1. Title  2. Cost");
                    int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        cart.sortByTitleCost();
                    } else if (sortChoice == 2) {
                        cart.sortByCostTitle();
                    } else {
                        System.out.println("Invalid sort choice.");
                    }
                    break;
                case 3:
                    System.out.println("Enter media title to remove:");
                    String title = scanner.nextLine();
                    Media media = store.findMediaByTitle(title);
                    if (media != null) {
                        cart.removeMedia(media);
                    } else {
                        System.out.println("Media not found in cart.");
                    }
                    break;
                case 4:
                    System.out.println("Enter media title to play:");
                    title = scanner.nextLine();
                    cart.playMedia(title);
                    break;
                case 5:
                    System.out.println("Order created successfully!");
                    cart.clear();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
