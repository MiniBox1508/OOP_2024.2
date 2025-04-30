package hust.soict.itep.aims.screen.manager;

import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Playable;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                JDialog playDialog = new JDialog();
                playDialog.setTitle("Playing: " + media.getTitle());
                playDialog.setSize(300, 200);
                playDialog.setLocationRelativeTo(null);
                JLabel playLabel = new JLabel("Playing: " + media.getTitle());
                playLabel.setHorizontalAlignment(SwingConstants.CENTER);
                playDialog.add(playLabel);
                playDialog.setVisible(true);
            });
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}