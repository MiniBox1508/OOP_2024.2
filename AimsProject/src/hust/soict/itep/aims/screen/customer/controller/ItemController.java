```java
package hust.soict.itep.aims.screen.customer.controller;

import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.Media;
import hust.soict.itep.aims.media.Playable;
import hust.soict.itep.aims.exception.PlayerException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;

public class ItemController {
    private Media media;
    private Cart cart;

    @FXML
    private Label lblTitle;
    @FXML
    private Label lblCost;
    @FXML
    private Button btnAddToCart;
    @FXML
    private Button btnPlay;

    public ItemController(Cart cart) {
        this.cart = cart;
    }

    public void setData(Media media) {
        this.media = media;
        lblTitle.setText(media.getTitle());
        lblCost.setText(media.getCost() + " $");
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
            HBox.setMargin(btnAddToCart, new Insets(0, 0, 0, 60));
        }
    }

    @FXML
    void btnAddToCartClicked() {
        cart.addMedia(media);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add to Cart");
        alert.setHeaderText(null);
        alert.setContentText("Media \"" + media.getTitle() + "\" has been added to the cart.");
        alert.showAndWait();
    }

    @FXML
    void btnPlayClicked() {
        try {
            ((Playable) media).play();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Play Media");
            alert.setHeaderText(null);
            alert.setContentText("Playing: " + media.getTitle());
            alert.showAndWait();
        } catch (PlayerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Playback Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
```