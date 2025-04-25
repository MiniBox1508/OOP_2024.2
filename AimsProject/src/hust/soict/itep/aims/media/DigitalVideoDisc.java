package hust.soict.itep.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    public boolean isMatch(String title) {
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }

    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play DVD: " + getTitle() + " (invalid length)");
        } else {
            System.out.println("Playing DVD: " + getTitle());
            System.out.println("DVD length: " + getLength());
        }
    }

    public String toString() {
        return String.format("DVD - %s - %s - %s - %d: %.2f $",
                getTitle(), getCategory() != null ? getCategory() : "Unknown",
                getDirector() != null ? getDirector() : "Unknown",
                getLength(), getCost());
    }
}
