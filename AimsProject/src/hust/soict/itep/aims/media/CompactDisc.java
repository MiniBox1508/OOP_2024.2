package hust.soict.itep.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, String director, String artist, float cost) {
        super(title, category, director, cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track " + track.getTitle() + " added.");
        } else {
            System.out.println("Track " + track.getTitle() + " already exists.");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track " + track.getTitle() + " removed.");
        } else {
            System.out.println("Track " + track.getTitle() + " not found.");
        }
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play CD: " + getTitle() + " (invalid length)");
        } else {
            System.out.println("Playing CD: " + getTitle());
            System.out.println("CD length: " + getLength());
            for (Track track : tracks) {
                track.play();
            }
        }
    }

    public boolean isMatch(String title) {
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }

    public String toString() {
        return String.format("CD - %s - %s - %s - %s: %.2f $",
                getTitle(), getCategory() != null ? getCategory() : "Unknown",
                getDirector() != null ? getDirector() : "Unknown",
                artist != null ? artist : "Unknown", getCost());
    }
}
