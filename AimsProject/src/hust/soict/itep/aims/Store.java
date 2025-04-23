package hust.soict.itep.aims.store;

import hust.soict.itep.aims.media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (!itemsIn EaStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("Media đã được thêm vào cửa hàng");
        } else {
            System.out.println("Media đã tồn tại trong cửa hàng");
        }
    }

    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println("Media đã được xóa khỏi cửa hàng");
        } else {
            System.out.println("Media không tồn tại trong cửa hàng");
        }
    }

    public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.isMatch(title)) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }
}