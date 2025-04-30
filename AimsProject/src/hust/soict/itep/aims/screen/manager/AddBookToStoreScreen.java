package hust.soict.itep.aims.screen.manager;

import hust.soict.itep.aims.store.Store;
import hust.soict.itep.aims.media.Book;

import javax.swing.*;
import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthors;

    public AddBookToStoreScreen(Store store) {
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

        center.add(new JLabel("Authors (comma-separated):"));
        tfAuthors = new JTextField(20);
        center.add(tfAuthors);

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
            String[] authors = tfAuthors.getText().split(",");

            Book book = new Book(title, category, cost);
            for (String author : authors) {
                book.addAuthor(author.trim());
            }
            store.addMedia(book);
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cost format!");
        }
    }
}