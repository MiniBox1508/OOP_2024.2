package hust.soict.itep.aims.screen.manager;

import hust.soict.itep.aims.store.Store;
import hust.soict.itep.aims.media.CompactDisc;
import hust.soict.itep.aims.media.Track;

import javax.swing.*;
import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfArtist;
    private JTextField tfTracks;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
    }

    @Override
    JPanel createCenter() {
        JPanel center = new JPanel(new GridLayout(5, 2, 5, 5));
        center.add(new JLabel("Title:"));
        tfTitle = new JTextField(20);
        center.add(tfTitle);

        center.add(new JLabel("Category:"));
        tfCategory = new JTextField(20);
        center.add(tfCategory);

        center.add(new JLabel("Cost:"));
        tfCost = new JTextField(20);
        center.add(tfCost);

        center.add(new JLabel("Artist:"));
        tfArtist = new JTextField(20);
        center.add(tfArtist);

        center.add(new JLabel("Tracks (title:length, ...):"));
        tfTracks = new JTextField(20);
        center.add(tfTracks);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addMedia());
        center.add(addButton);

        return center;
    }

    @Override
    void addMedia() {
        try {
            String title = tfTitle.getText();
            String category = tfCategory.getText();
            float cost = Float.parseFloat(tfCost.getText());
            String artist = tfArtist.getText();
            String[] tracks = tfTracks.getText().split(",");

            CompactDisc cd = new CompactDisc(title, category, null, artist, cost);
            for (String track : tracks) {
                String[] parts = track.split(":");
                if (parts.length == 2) {
                    cd.addTrack(new Track(parts[0].trim(), Integer.parseInt(parts[1].trim())));
                }
            }
            store.addMedia(cd);
            JOptionPane.showMessageDialog(this, "CD added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost or track length format!");
        }
    }
}