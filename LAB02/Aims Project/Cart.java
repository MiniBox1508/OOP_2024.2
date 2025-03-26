package Aims;

import java.util.ArrayList;

public class Cart {
    
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ArrayList<DigitalVideoDisc> itemsOrdered = new ArrayList<DigitalVideoDisc>();
    private int qtyOrdered = 0;

    public int getQtyOrdered() {
         return this.qtyOrdered;
     }

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(disc);
            qtyOrdered++;
            System.out.println("Thêm đĩa vào thành công");
        } else {
            System.out.println("Giỏ hàng đầy!");
        }
    }

     public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
         for (DigitalVideoDisc disc : dvdList) {
             addDigitalVideoDisc(disc);
         }
     }
 
     public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
         addDigitalVideoDisc(dvd1);
         addDigitalVideoDisc(dvd2);
     }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered > 0) {
            itemsOrdered.remove(disc);
            qtyOrdered--;
            System.out.println("Đã gỡ bỏ đĩa");
        } else {
            System.out.println("Giỏ hàng trống");
        }
    }

    public float totalCost() {
        float total = 0;
        for (DigitalVideoDisc disc : itemsOrdered) {
            total += disc.getCost();
        }
        return total;
    }

    public ArrayList<DigitalVideoDisc> getItemsOrdered() {
        return itemsOrdered;
    }

}
