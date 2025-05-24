```java
package hust.soict.itep.aims.screen.customer.controller;

import hust.soict.itep.aims.store.Store;
import hust.soict.itep.aims.cart.Cart;
import hust.soict.itep.aims.media.Media;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewStoreController {
    private Store store;
    private Cart cart;
    private static final String ITEM_FXML_FILE_PATH = "/hust/soict/itep/aims/screen/customer/view/Item.fxml";

    @FXML
    private GridPane gridPane;
    @FXML
    private Button btnViewCart;

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        int column = 0;
        int row = 0;
        for (Media media : store.getItemsInStore()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(ITEM_FXML_FILE_PATH));
                fxmlLoader.setController(new ItemController(cart));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController controller = fxmlLoader.getController();
                controller.setData(media);

                gridPane.add(anchorPane, column, row);
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnViewCartPressed(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/hust/soict/itep/aims/screen/customer/view/Cart.fxml"));
            fxmlLoader.setController(new CartController(store, cart));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) btnViewCart.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```